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
package N2KLib;
import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import N2KDefs.PGNDefinitions;
import N2KDefs.PGNField;
import N2KDefs.PGNInfo;
import N2KDefs.PGNs;
import Utils.Trace;
import Utils.Utils;
/**
 * A Java NMEA 2000 library.  This library takes an XML file that defines the format of the N2K messages
 * and provides functions for constructing messages either from input data or from a raw binary stream.
 * 
 * The library is structured in three layers.
 * <ul><li>
 * The UI package contains a sample user interface which displays received packets and also 
 * provides simulation of a subset of N2K messages sufficient to drive a chart plotter (tested
 * against a Raymarine E120 Classic), including a simple autohelm function. <br>  
 * See http://blog.mailasail.com/anastasia/493 for more details.
 * </li><li>
 * The N2KLib package is the N2K engine, which handles construction and validation of messages and
 * their fields.
 * </li><li>
 * The Transport package is what transfers the N2K messages to/from their destination.  
 * The N2KTransport interface isolates the implementation of the transport from N2KLib.  The 
 * product includes a transport implementation for running over the Actisense serial to N2K
 * interface.  It is written to the Raspberry Pi pi4j serial interface, but the product includes
 * a minimal implementation of com.pi4j.io.serial that binds to the Windows jSerialComm  library for 
 * running on Windows systems.
 * </li></ul>  
 * <br>
 * There are three additional packages.
 * <ul><li>
 * N2KDefs contains the classes that map to the pgns.xml definition file.  These are simply
 * containers that carry the fields that appear in the XML.  They are populated using JAXB.
 * </li><li>
 * N2KMsgs containsa single class, N2K, which defines constants for all the fields in the
 * XML file.  See below for example of use.
 * </li><li>
 * Utils contains general utility functions plus tracing.
 * </li></ul>
 * <br>
 * Here is an example of the UI creating an N2K timedate packet for sending:<br>
 * <pre>
 *      p = new N2KPacket(N2K.timeDate_pgn);            
 *      p.fields[N2K.timeDate.date].setInt(days);
 *      p.fields[N2K.timeDate.time].setDecimal(secsSinceMidnight);
 *      p.fields[N2K.timeDate.localOffset].setInt(0);
 * </pre>
 * You can see that this approach to packet construction relies heavily on the constants defined
 * in the N2KDefs.N2K class (which is auto-generated from the XML file).  As well as the fields, 
 * the N2K class also includes
 * enums for all the enumerated types defined in the XML file. 
 * <br><br>
 * See N2KTransport for example of constructing packets from raw bytes.
 * <br><br>
 * If the XML definition file (pgns.xml) does not exist in users_home/n2klib then it is copied there 
 * out of the jar file, and can then be edited as required.  Also UI settings are stored in
 * users_home/n2klib/n2kproperties.txt.  Tracing is written to logfile.txt in the same location,
 * as well as to the console.   
 * 
 * @author PhilMay
 *
 */
public class N2KLib implements Runnable
{
  boolean opened = false;

  N2KTransport transport = null;
  Thread rcvThread = null;
  volatile boolean stopRunning = false;

  Vector<N2KPacket> packets = new Vector<N2KPacket>();
  Vector<N2KPacketDef> dupDefs = new Vector<N2KPacketDef>();

  public static PGNs pgns;
  public static List<PGNInfo> pgnInfo;
  long startTime = System.currentTimeMillis();

  // We construct a map of field definitions indexed by pgn so we can
  // encode/decode efficiently
  public static MultivaluedHashMap<Integer, N2KPacketDef> pgnDefs= new MultivaluedHashMap<>(); 

  /**
   * The library is constructed by providing a communications object that implements 
   * the N2KTransport interface. 
   * @param transport The communication object
   */
  public N2KLib(N2KTransport transport)
  {
    this.transport = transport;

    // Read in PGN definitions
    try
    {
      File file = Utils.copyResourceToHome("pgns.xml");
      
      JAXBContext jaxbContext = JAXBContext.newInstance(PGNDefinitions.class);

      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      PGNDefinitions pgndefs = (PGNDefinitions)jaxbUnmarshaller.unmarshal(file);
      pgns = pgndefs.pgns;
      pgnInfo = pgns.pgnInfo;
      Trace.alert("Pgninfo size is " + pgnInfo.size());

    }
    catch (JAXBException ex)
    {
      Trace.stack(ex,  "Loading PGNs");
    }

    // Now convert the field definitions into something more usable.  We produce a 
    // hashmap of pgns with an array of fields so we can go straight to a pgn and 
    // access field definitions directly.
    for (PGNInfo info : pgnInfo)
    {
      //Trace.debug("Processing PGN:" + info.toString());
      if ((info.pgnFields == null) || (info.pgnFields.pgnField == null))
      {
        Trace.normal("Pgn " + info.PGN + " has no fields");
        N2KPacketDef fldDefs = new N2KPacketDef(0, info);
        pgnDefs.add(new Integer(info.PGN), fldDefs);
        fldDefs.setLength(0);
      }
      else
      {
        List<PGNField> flds = info.pgnFields.pgnField;
        int numFieldDefs = flds.size();
        N2KPacketDef fldDefs = new N2KPacketDef(numFieldDefs, info);
        pgnDefs.add(new Integer(info.PGN), fldDefs);
        int maxLen = 0;
        int lastOffset = 0;
        for (int i = 0; i < numFieldDefs; i++)
        {
          PGNField fld = flds.get(i);
          if (fld.Match != null)
          {
            fldDefs.matchFields++;
          }
          if (fld.BitOffset + fld.BitLength > maxLen)
          {
            maxLen = fld.BitOffset + fld.BitLength;
          }
          if (fld.Order != (i+1))
          {
            Trace.error("ERROR IN PGN " + info.PGN + " field# " + fld.Order +
                        " - field is out of order, should be " + (i+1));
          }
          else
          {
            N2KFieldDef fd = new N2KFieldDef(info, fld, lastOffset);
            lastOffset = fd.bitOff + fd.bitLength;
            fldDefs.fieldDefArray[i] = fd; 
            //Trace.debug("Validating pgn " + info.PGN + " field " + fld.Order + " aligned " + fd.aligned);
            if (fd.reserved)
            {
              // We do not touch reserved fields so no need to validate them (some have unsupported alignment) 
              continue;
            }
            if (!fd.aligned)
            {
              if (fd.type != N2KFieldDef.Type.INT)
              {
                Trace.error("ERROR IN PGN " + info.PGN + " field# " + fld.Order +
                            " - invalid field alignment");
              }
              else
              {
                if ((fd.bitLength + (fd.bitOff % 8)) > 32)
                {
                  Trace.error("ERROR IN PGN " + info.PGN + " field# " + fld.Order +
                              " - we only support unaligned integers within a 4 byte area");
                }
              }
            }
            else
            {
              if ( fld.Type.equals(N2KFieldDef.FD_TIME) && 
                   (fld.BitLength != 32))
              {
                Trace.error("ERROR IN PGN " + info.PGN + " field# " + fld.Order +
                            " - we only support 4 byte time");
              }
              else if (fld.Type.equals(N2KFieldDef.FD_DATE) &&
                       (fld.BitLength != 16))
              {
                Trace.error("ERROR IN PGN " + info.PGN + " field# " + fld.Order +
                            " - We only support 2 byte dates");
              }
              if ((fd.type == N2KFieldDef.Type.INT) ||
                  (fd.type == N2KFieldDef.Type.FLOAT))
              {
                if (fld.BitLength > 64)
                {
                  Trace.error("ERROR IN PGN " + info.PGN + " field# " + fld.Order +
                              "- integer length " + fld.BitLength + " not supported");
                }
              }
            }
          }
        }

        // Check that fields are all contiguous
        int checkContig = 0;
        for (int i = 0; i < numFieldDefs; i++)
        {
          N2KFieldDef fd = fldDefs.fieldDefArray[i];
          if (fd.bitOff != checkContig)
          {
            Trace.error("ERROR IN PGN " + info.PGN + " field# " + i +
                        " - repeating field is not contiguous");
            break;
          }
          checkContig = fd.bitOff + fd.bitLength;
        }

        fldDefs.setLength(maxLen);
      }
    }
  }

  /**
   * The thread that receives packets from the transport
   */
  public void run()
  {
    Trace.alert("N2K thread started");
    while (!stopRunning)
    {
      try
      {
        N2KPacket p = (N2KPacket)transport.get(); // Blocking read of transport
        if (p == null)
        {
        	Trace.error("Blocking get() from transport should not return null");
        }
        else
        {
          Trace.normal("Got N2k packet from transport");
          synchronized(packets)
          {
            packets.addElement(p);
            packets.notify();
          }
        }
      }
      catch (Exception ex)
      {
        Trace.stack(ex, "Exception reading packet from transport:" + ex.getMessage());
      }
    }
  }

  /*
   * The first call to tye library will generally be open() to open the communications
   * and start the receive thread.
   */
  public void open() throws Exception
  {
    if (opened)
    {
      throw new Exception("Trying to open a transport that is already open");
    }

    try
    {
      transport.open();
      stopRunning = false;
      rcvThread = new Thread(this);
      rcvThread.start();
      opened = true;
    }
    catch (Exception ex)
    {
      throw new Exception(" ==>> Exception opening N2K library for " + transport.getClass().getName() + ": " + ex.getMessage());
    }
  }

  /**
   * Ends a library sessoin
   * @throws Exception any exception thrown from the transport
   */
  public void close() throws Exception
  {
    opened = false;
    stopRunning = true;
    Trace.alert("Closing transport");
    transport.close();
  }

  /**
   * The method for getting received N2K packets.  The request will either return a packet
   * or null, if the timeout arrives before a packet is available.
   * @param timeout In milliseconds
   * @return The packet, or null if timed out
   * @throws Exception If the transport is not open
   */
  public N2KPacket get(int timeout) throws Exception
  {
    if (!opened)
    {
      throw new Exception("Cannot get from a transport that is not opened");
    }

    N2KPacket packet = null;

    synchronized (packets)
    {
      if (packets.isEmpty())
      {
        packets.wait(timeout);
        // receive thread will notify us if packet comes in
      }
      if (!packets.isEmpty())
      {
        packet = packets.remove(0);
        Trace.msg("==> " + packet.toString());
      }
    }
    return(packet);
  }

  /**
   * To sens an N2K packet 
   * @param packet The packet
   * @throws Exception If the transport is not open
   */
  public void put(N2KPacket packet) throws Exception
  {
    if (!opened)
    {
      throw new Exception("Cannot put to a transport that is not opened");
    }
    packet.time = System.currentTimeMillis() - startTime;

    Trace.msg("<== " + packet.toString());
    transport.put(packet);
  } 

  /**
   * test to see if the library has been opened
   * @return true if the library has been opened
   */
  public boolean isOpen()
  {
    return(opened);
  }
}
