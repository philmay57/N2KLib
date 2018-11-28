/*
Copyright 2018 Phil May
This file is part of N2KLib
N2KLib is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
N2KLib is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with N2KLib.  If not, see <https://www.gnu.org/licenses/>.
*/
package Transport;
import java.io.IOException;
import java.util.Vector;

import com.pi4j.io.serial.Baud;
import com.pi4j.io.serial.DataBits;
import com.pi4j.io.serial.FlowControl;
import com.pi4j.io.serial.Parity;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialConfig;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.StopBits;

import N2KLib.N2KPacket;
import N2KLib.N2KTransport;
import Utils.Trace;
import Utils.Utils;
/**
 * This is an implementation of a serial transport that interfaces between N2KLib and the 
 * Actisense serial to NMEA2000 (NGT) gateway.  It is implemented to use the pi4j serial
 * interface, but the N2KLib product also includes a skinny implementation of pi4j for Windows.  
 * 
 * @author PhilMay
 *
 */
public class ActisenseTransport implements N2KTransport
{

  // Messages from the device are formatted as follows.
  // CONTROL,  START_DATA, <command>, <length>, ... data ..., <checksum>, CONTROL, END_DATA  
  // If CONTROL occurs in the data then it is repeated (although the repeat is not included 
  // in the length count).
  // The checksum is such that (byte)(checksum + command + length + data) == 0 

  public static final int N2K_PREFIX_LEN = 6;  // Priority, pgn, dest, fieldlen 
  public static final int N2K_INCOMING_HEADER_LEN = 11;

  // Packet delimiters
  static final byte CONTROL     = 0x10;
  static final byte START_DATA  = 0x02;
  static final byte END_DATA    = 0x03;

  // Commands
  static final byte N2K_MSG     = (byte)0x93;
  static final byte N2K_MSG_OUT = (byte)0x94;
  static final byte NGT_MSG     = (byte)0xA0;
  static final byte NGT_MSG_OUT = (byte)0xA1;


  transient boolean opened = false;
  transient boolean threadRunning = false;

  Vector<byte[]> byteStream = new Vector<byte[]>();
  int streamOff = 0;

  Vector<N2KPacket> writePackets = new Vector<N2KPacket>();

  byte[] msgData    = null;
  MsgState msgState = MsgState.SCANNING; 
  int    msgLength  = 0;
  int    msgOffset  = 0;
  int    msgType    = NGT_MSG;
  byte[] n2kData   = new byte[2000];  // Max should be 1785
  byte[] writeData = new byte[4000];  // Includes any control character that need inserting.

  // Message processing states.  We ignore the end bytes of the message because if the checksum 
  // is good then we can assume the packet is good.
  enum MsgState
  {
    SCANNING,STARTING,COMMAND,LENGTH,DATA,SKIPCONTROLCONTROL,SKIPCONTROLLENGTH,SKIPCONTROLCHECKSUM,CHECKSUM;
  }

  Serial serial = null;

  byte[] NGT_INIT = 
  {
    (byte)0x11,
    (byte)0x02,
    (byte)0x00 
  };

  public ActisenseTransport()
  {
  }
  public void open() throws IOException
  {
    if (opened)
    {
      throw new IOException("Already opened");
    }

    serial = SerialFactory.createInstance();
    if (serial == null)
    {
      throw new IOException("Unable to select com port");
    }
    Trace.alert("Done serialfactory createinstance");
    Trace.alert("Default port is now " + Serial.DEFAULT_COM_PORT);
    // create serial config object
    SerialConfig config = new SerialConfig();
    Trace.alert("Got serialconfig");

    // For all Raspberry Pi models except the 3B the port is "/dev/ttyAMA0"
    // NOTE for Windows simulation these values are ignored, the port is set by the 
    // chooser dialog popped up by the factory instance and the parameter on
    // config.device is ignored.
    config.device(Serial.DEFAULT_COM_PORT);
    Trace.alert("Configured port");
    config.baud(Baud._115200);
    config.dataBits(DataBits._8);
    config.parity(Parity.NONE);
    config.stopBits(StopBits._1);
    config.flowControl(FlowControl.NONE);
    Trace.alert("Done config of port");
    // open the default serial device/port with the configuration settings
    if (serial != null)
    {
      serial.open(config);
      Trace.alert("Serial port opened successfully");
      /*
        Trace.alert("Opened com port at " + serial.comPort.getBaudRate() + " baud " + 
                    serial.comPort.getNumDataBits() + " bits " + 
                    serial.comPort.getNumStopBits() + " stops " + 
                    serial.comPort.getParity() + " parity "+ 
                    serial.comPort.getFlowControlSettings() + " flow");
       */            

      Utils.sleep(200);
      Trace.alert("Writing init packet");
      writeRawPacket(NGT_MSG_OUT, NGT_INIT, NGT_INIT.length);
      opened = true;
    }
  }

  public void close()
  {
    opened = false;
    for (int i = 0; i < 500; i++)
    {
      Utils.sleep(2);
      if (!threadRunning) break;
    }
    if ((serial != null) && serial.isOpen())
    {
      serial.close();
    }
    Trace.alert("Closed receove thread and serial port");
  }

  public N2KPacket get() throws IOException
  {
    N2KPacket packet = null;

    // Receive more data.  Return packet or block until data comes in
    while (opened && (packet == null))
    {
      int moredata = readFromNGT();
      ////Trace.debug(String.format("Read byte %02x from gateway", (byte)moredata));
      while (opened && (moredata < 0))
      {
        Utils.sleep(5);
        moredata = readFromNGT();
      }

      byte data = (byte)moredata;

      switch (msgState)
      {
        case SCANNING:
          // Every interaction with the NGT must start with CONTROL + START_DATA, so 
          // skip to first CONTROL
          if (data == CONTROL)
          {
            // Found DLE so switch state
            msgState = MsgState.STARTING;
            //Trace.debug("To starting state");
          }
          else
          {
            Trace.normal(String.format("Skipping byte %02X",data));
          }         
          break;

          // Found CONTROL so check for START
        case STARTING:
          if (data == START_DATA)
          {
            // Found START so switch state
            msgState = MsgState.COMMAND;
            //Trace.debug("To command state");
          }
          else if (data == END_DATA)
          {
            // Found END so back to scanning without warning
            msgState = MsgState.SCANNING;
            //Trace.debug("To scanning state");
          }
          else
          {
            Trace.alert(" Expected start but got (" + data + ") so aborting packet ");
            msgState = MsgState.SCANNING;
          }         
          break;

          // Found START so decode command
        case COMMAND:
          switch (data)
          {
            case N2K_MSG:
              //Trace.debug("Got start of N2K message");
              msgType = N2K_MSG;
              msgState = MsgState.LENGTH;           
              break;
            case NGT_MSG:
              //Trace.debug("Got start of NGT message");
              msgType = NGT_MSG;
              msgState = MsgState.LENGTH;           
              break;
            default:
              Trace.alert("Unknown command " + data + " so aborting packet");
              msgState = MsgState.SCANNING;
              break;
          }
          break;

          // Found start of message 
        case LENGTH:
          if (data > 0)
          {
            msgLength = data;
            msgData = new byte[msgLength];
            msgOffset = 0;
            if (data == CONTROL)
            {
              Trace.normal("Length is control!");
              msgState = MsgState.SKIPCONTROLLENGTH;
            }
            else
            {
              msgState = MsgState.DATA;
            }
            //Trace.debug("To data state");
          }
          else
          {
            Trace.alert("Zero message length so aborting receive");
            msgState = MsgState.SCANNING;
          }
          break;

          // Found start of message 
        case DATA:          
          if (data == CONTROL)
          {
            msgState = MsgState.SKIPCONTROLCONTROL;
            //Trace.debug("To SKIPCONTROLCONTROL");
          }
          else
          {
            msgData[msgOffset++] = data;
            if (msgOffset == msgLength)
            {
              msgState = MsgState.CHECKSUM;             
              //Trace.debug("To checksum state");
            }
            else
            {
              msgState = MsgState.DATA;
              //Trace.debug("To data state");
            }
          }
          break;

          // Skip double up of control in data part  
        case SKIPCONTROLCONTROL:          
        case SKIPCONTROLLENGTH:         
        case SKIPCONTROLCHECKSUM:         
          if (data != CONTROL)
          {
            Trace.alert(String.format("Invalid control byte %02X within data - aborting receive",data));
            Trace.hexalt("Data to this point", msgData, 0, msgOffset);
            msgState = MsgState.SCANNING;
          }
          else
          {
            if (msgState == MsgState.SKIPCONTROLLENGTH)
            {
              msgState = MsgState.DATA;
            }
            else if (msgState == MsgState.SKIPCONTROLCHECKSUM)
            {
              msgState = MsgState.SCANNING;
            }
            else
            {
              msgData[msgOffset++] = data;
              if (msgOffset == msgLength)
              {
                msgState = MsgState.CHECKSUM;             
                //Trace.debug("To checksum state");
              }
              else
              {
                msgState = MsgState.DATA;
                //Trace.debug("To data state");
              }
            }
          }
          break;

          // Found end of message
        case CHECKSUM:
          int checksum = 0;
          for (byte add : msgData)
          {
            checksum += add;
          }
          checksum += msgType;
          checksum += msgLength;

          if (((data + checksum) & 0x000000FF) != 0)
          {
            Trace.alert("Invalid checksum byte " + data + " does not match " + checksum + " aborting receive");
            msgState = MsgState.SCANNING;
          }
          else
          {
            Trace.hexdbg("Transport packet:", msgData, 0, msgData.length);
            if (msgLength >= N2K_INCOMING_HEADER_LEN)
            {
              // Go a good packet!
              if (msgType == N2K_MSG)
              {
                Trace.normal("Process N2K packet");
                packet = processN2KPacket(msgData);
              }
              else
              {
                Trace.normal("Process GW packet");
                processNGTPacket(msgData);              
              }
            }
            else
            {
              Trace.hexerr("Ignoring short packet", msgData, 0, msgData.length);
            }

            if (checksum == CONTROL)
            {
              Trace.alert("Checksum is CONTROL");
              msgState = MsgState.SKIPCONTROLCHECKSUM;
            }
            else
            {
              msgState = MsgState.SCANNING;
            }
          }
          break;

        default:
          Trace.error("Unknown state " + msgState);
          break;
      }         
    }

    if (!opened)
    {
      threadRunning = false;
    }
    else
    {
    	// Trigger another write of a packet before we return this packet
      put(null);
    }

    return(packet);
  }

  void processNGTPacket(byte[] data)
  {
    if (msgLength > 11)
    {
      Trace.hexdbg("Gateway packet", msgData, 0, msgData.length);
    }
    else
    {
      Trace.hexerr("Ignoring short gateway packet", msgData, 0, msgData.length);
    }
  }

  N2KPacket processN2KPacket(byte[] rawBytes)
  {
    byte priority = rawBytes[0];
    int  pgn  = Utils.getInt(rawBytes, 1, 3);
    byte dest = rawBytes[4];
    byte src  = rawBytes[5];
    int  time = Utils.getInt(rawBytes, 6);
    int  n2kLen = rawBytes.length - N2K_INCOMING_HEADER_LEN;
    if (rawBytes[10] != (byte)n2kLen)
    {
      Trace.error("Inconsistent length in Actisense packet, message " + ((int)rawBytes[10] & 0x000000ff) + 
                  " actual " + (rawBytes.length - N2K_INCOMING_HEADER_LEN));
    }
    Trace.hexalt("Incoming packet raw N2K bytes:", rawBytes, N2K_INCOMING_HEADER_LEN, n2kLen);

    N2KPacket packet = new N2KPacket(pgn, 
    		priority, 
    		dest, 
    		src, 
    		time,  
    		rawBytes, 
    		n2kLen, 
    		N2K_INCOMING_HEADER_LEN);

    return(packet);
  }


  public int put(N2KPacket packet) throws IOException
  {
    int rc = -1;

    if (!opened || (serial == null))
    {
      Trace.error("Put when not opened or no serial port");
      return(rc);
    }

    synchronized(writePackets)
    {
    	if (packet != null)
    	{
    		writePackets.add(packet);
    	}

    	if (writePackets.size() > 50)
    	{
    		// If stack is too deep then discard the first one
    		writePackets.remove(0);
    	}

    	if (writePackets.isEmpty())
    	{
    		//Trace.debug("Nothing to send");
    		rc = 0;
    	}
    	else
    	{
    		packet = writePackets.remove(0);
    		Trace.alert("Processing write for packet:" + packet.toString());  
    		//Trace.alert("Writing packet " + bytes[0]);
    		writeN2KPacket(packet);
    	}
    }
    return(rc);
  }

  void writeRawPacket(byte[] packet, int off, int len)
  {
    Trace.hexalt("Writing RAW packet", packet, off , len);
    try
    {
      serial.write(packet, off, len);
    }
    catch (Exception ex)
    {
      Trace.stack(ex, "Error writing raw data to serial");
    }
  }

  void writeN2KPacket(N2KPacket n2kPacket) throws IOException
  {
    int n2klen = n2kPacket.getRawData(n2kData, N2K_PREFIX_LEN);
    n2kData[0] = n2kPacket.priority;
    n2kData[1] = (byte)(n2kPacket.pgn);
    n2kData[2] = (byte)(n2kPacket.pgn >> 8);
    n2kData[3] = (byte)(n2kPacket.pgn >> 16);
    n2kData[4] = n2kPacket.dest;
    n2kData[5] = (byte)n2klen;

    writeRawPacket(N2K_MSG_OUT, n2kData, n2klen + N2K_PREFIX_LEN);
  }

  void writeRawPacket(byte cmd, byte[] rawData, int datalen) throws IOException
  {
    // CONTROL,  START_DATA, <command>, <length>, ... data ..., <checksum>, CONTROL, END_DATA  

    int msgoff = 0;
    int checksum = cmd + datalen; 
    writeData[msgoff++] = CONTROL;
    writeData[msgoff++] = START_DATA;
    writeData[msgoff++] = cmd;
    msgoff = writeDataEscaped(msgoff, (byte)datalen);

    for (int i = 0; i < datalen; i++)
    {
      msgoff = writeDataEscaped(msgoff, rawData[i]);
      checksum += rawData[i];
      //Trace.alert(String.format("CRC now %02X", checksum));
    }
    writeData[msgoff++] = (byte)(256 - (checksum & 0x000000ff));
    //Trace.debug(String.format("CRC now %02X", writeData[msgoff-1]));
    writeData[msgoff++] = CONTROL;
    writeData[msgoff++] = END_DATA;

    // For testing our encoding
    // Trace.hexalt("Writing escaped message", writeData, 0, msgoff);
    // testPacket(writeData);
    serial.write(writeData, 0, msgoff);
  }

  private int bytesAvailable() throws IOException
  {
    int avail = 0;
    avail = serial.available();
    //Trace.debug("Serial bytes available:" + avail);
    return(avail);
  }

  int writeDataEscaped(int off, byte val)
  {
    if (val == CONTROL)
    {
      writeData[off++] = CONTROL; 
    }
    writeData[off++] = val;
    return(off);
  }

  // Read all bytes available to bytestream and returns first chunk of data  
  int readFromNGT() throws IOException
  {
    int val = -1;

    // First get any serial data that is available
    int avail = bytesAvailable();
    if (avail > 0)
    {
      byte[] data = serial.read(avail);
      byteStream.add(data);
      //Trace.hexalt("Read bytes", data, 0, data.length);
    }

    // Now return a byte from the stream
    byte[] chunk = null;
    if (!byteStream.isEmpty())
    {
      chunk = byteStream.firstElement();
      if (streamOff >= chunk.length)
      {
        byteStream.removeElementAt(0);
        if (byteStream.isEmpty())
        {
          chunk = null;
        }
        else
        {
          chunk = byteStream.firstElement();
        }
        streamOff = 0;
      }
    }

    if (chunk != null)
    {
      val = (chunk[streamOff++] & 0x000000ff);
    }

    return(val);
  }

  // Function to check our encoding decodes OK
  /*
  public void testPacket(byte[] packet)
  {
    MsgState msgState = MsgState.SCANNING;
    byte[] msgData = null;
    int msgOffset = 0;
    int msgLength = 0;
    int msgType = 0;
    int packix = 0;
    Trace.hexalt("testing", packet,  0,  packet.length);
    while(true)
    {   
      if (packix == packet.length) 
      {
        break;
      }
      byte data = packet[packix++];
      switch(msgState)
      {
        case SCANNING:
        // Every interaction with the NGT must start with CONTROL + START_DATA, so 
        // skip to first CONTROL
        if (data == CONTROL)
        {
          // Found DLE so switch state
          msgState = MsgState.STARTING;
          Trace.alert("!!!TESTTo starting state");
        }
        else
        {
          Trace.error("Unexpected byte when scanning");
        }                           
        break;
      
        // Found CONTROL so check for START
        case STARTING:
          if (data == START_DATA)
          {
            // Found START so switch state
            msgState = MsgState.COMMAND;
            Trace.alert("!!!TEST-To command state");
          }
          else if (data == END_DATA)
            {
              // Found END so back to scanning without warning
              msgState = MsgState.SCANNING;
              Trace.alert("!!!TEST-To scanning state");
            }
            else
          {
            Trace.error("!!!TEST - Expected start but got (" + data + ") so aborting packet ");
            msgState = MsgState.SCANNING;
          }                         
          break;
          
        // Found START so decode command
        case COMMAND:
          switch (data)
          {
          case N2K_MSG_OUT:
            Trace.alert("!!!TEST-Got start of N2K message");
            msgType = N2K_MSG_OUT;
            msgState = MsgState.LENGTH;                                 
            break;
          case NGT_MSG_OUT:
            Trace.alert("!!!TEST-Got start of NGT message");
            msgType = NGT_MSG_OUT;
            msgState = MsgState.LENGTH;                                 
            break;
            default:
              Trace.error("!!!TEST -Unknown command " + data + " so aborting packet");
              msgState = MsgState.SCANNING;
              break;
          }
          break;
          
        // Found start of message 
        case LENGTH:
          if (data > 0)
          {     
            msgLength = data;
            msgData = new byte[msgLength];
            msgOffset = 0;
            if (data == CONTROL)
            {
              Trace.alert("!!!TEST -Length is control!");
              msgState = MsgState.SKIPCONTROLLENGTH;
            }
            else
            {
              msgState = MsgState.DATA;
            }
            //Trace.debug("!!!TEST-To data state");
          }
          else
          {
            Trace.error("!!!TEST -Zero message length so aborting receive");
            msgState = MsgState.SCANNING;
          }
        break;
        
        // Found start of message 
        case DATA:                      
          if (data == CONTROL)
          {
            msgState = MsgState.SKIPCONTROLCONTROL;
            Trace.alert("!!!TEST-To SKIPCONTROLCONTROL");
          }
          else
          {
            msgData[msgOffset++] = data;
            if (msgOffset == msgLength)
            {
              msgState = MsgState.CHECKSUM;                                     
              Trace.alert("!!!TEST-To checksum state");
            }
            else
            {
              msgState = MsgState.DATA;
              Trace.debug("!!!TEST-To data state");
            }
          }
        break;
        
        // Skip double up of control in data part  
        case SKIPCONTROLCONTROL:                        
        case SKIPCONTROLLENGTH:                 
        case SKIPCONTROLCHECKSUM:                       
          if (data != CONTROL)
          {
            Trace.alert(String.format("Invalid control byte %02X within data - aborting receive",data));
            Trace.hexalt("Data to this point", msgData, 0, msgOffset);
            packix = packet.length;
            msgState = MsgState.SCANNING;
          }
          else
          {
            if (msgState == MsgState.SKIPCONTROLLENGTH)
            {
              msgState = MsgState.DATA;
            }
            else if (msgState == MsgState.SKIPCONTROLCHECKSUM)
            {
              packix = packet.length;
              msgState = MsgState.SCANNING;
            }
            else
            {
              msgData[msgOffset++] = data;
              if (msgOffset == msgLength)
              {
                msgState = MsgState.CHECKSUM;                                   
                Trace.alert("!!!TEST-To checksum state");
              }
              else
              {
                msgState = MsgState.DATA;
                Trace.debug("!!!TEST-To data state");
              }
            }
          }
        break;
        
        // Found end of message
        case CHECKSUM:
          int checksum = 0;
          for (byte add : msgData)
          {
            checksum += add;
            Trace.alert("Checksum now " + checksum);
          }
          Trace.alert("Adding " + msgType + " and " + msgLength);
          checksum += msgType;
          Trace.alert("Checksum now " + checksum);
          checksum += msgLength;
          Trace.alert("Checksum now " + checksum);
          
          if (((data + checksum) & 0x000000FF) != 0)
          {
            Trace.error("!!!TEST -Invalid checksum byte " + data + " does not match " + checksum + " aborting receive");
            msgState = MsgState.SCANNING;
          }
          else
          {
            Trace.alert("###GOOD PACKET");
            
            if (checksum == CONTROL)
            {
              Trace.alert("!!!TEST -Checksum is CONTROL");
              msgState = MsgState.SKIPCONTROLCHECKSUM;
            }
            else
            {   
              packix = packet.length;
              msgState = MsgState.SCANNING;
            }
          }
        break;
        
        default:
          Trace.error("Unknown state " + msgState);
          break;
      }               
    }
  }
  */
}
