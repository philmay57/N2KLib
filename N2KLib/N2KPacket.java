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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import Utils.Trace;
import Utils.Utils;
/**
 * The N2KPacket is the primary object handled by N2KLib.  It contains all the fields
 * associated with a specific NMEA 2000 message, such that the message can be handed to the 
 * transport for sending, or to the UI for tracing/logging.  It can be constructed from 
 * raw bytes coming via the network, or field by field from an application that is the source 
 * of nautical data.
 * <br><br>
 * The base set of fields are contained in the "fields" array.  These can be indexed using the 
 * definitions in the N2K class for the specific PGN.
 * <br><br>
 * Some packet have additional fields which are added to the repSet vector of arrays.  There can be
 * multiple instances of these fields.  For example:
 * <pre>
 *   		p = new N2KPacket(N2K.gnssSatsInView_pgn);
 *			p.fields[N2K.gnssSatsInView.sid].setInt(N2KPacket.getNewSid());
 *			p.fields[N2K.gnssSatsInView.mode].setInt(2); 
 *			p.fields[N2K.gnssSatsInView.satsInView].setInt(2);
 *			N2KField[] repset = p.addRepSet();
 *			repset[N2K.gnssSatsInView.rep.prn].setInt(2);
 *			repset[N2K.gnssSatsInView.rep.elevation].setDecimal(0.6283);
 *			repset[N2K.gnssSatsInView.rep.azimuth].setDecimal(1.274);
 *			repset[N2K.gnssSatsInView.rep.snr].setDecimal(41.0);
 *			repset[N2K.gnssSatsInView.rep.rangeResiduals].setInt(0);
 *			repset[N2K.gnssSatsInView.rep.status].setInt(N2K.gnssSatsInView.rep.status_values.Used);
 *			repset = p.addRepSet();
 *			repset[N2K.gnssSatsInView.rep.prn].setInt(6);
 *			repset[N2K.gnssSatsInView.rep.elevation].setDecimal(0.2268);
 *			repset[N2K.gnssSatsInView.rep.azimuth].setDecimal(0.5585);
 *			repset[N2K.gnssSatsInView.rep.snr].setDecimal(35.0);
 *			repset[N2K.gnssSatsInView.rep.rangeResiduals].setInt(0);
 *			repset[N2K.gnssSatsInView.rep.status].setInt(N2K.gnssSatsInView.rep.status_values.Used);
 *
 * </pre>
 * @author PhilMay
 *
 */
public class N2KPacket
{
  // An N2K packet has the follwoing form (all bytes unless specified)
  // Priority, PGN(3), dest, source, timestamp(4), length
  private boolean testEncoding = false;
  /**
   * The PGN of this N2K message
   */
  public int  pgn;
  /**
   * The priority of this N2K message 
   */
  public byte priority;
  /**
   * The source address of thie N2K message.  Set to zero if using a transport that negotiates
   * the N2K address internally (such as Actisense)
   */
  public byte src;
  /**
   * The destination address for this N2K message.  Set to 255 for broadcasting to all N2K
   * devices on the network.   
   */
  public byte dest;
  /**
   * The time this packet was sent
   */
  public long  time;
  /**
   * The description, looked up by PGN in the XML file the defines all the N2K messages  
   */
  public String description;
  /**
   * The base set of fields associalted with theis N2K message
   */
  public N2KField[] fields = null;
  /**
   * A vector containing one or more arrays of repeating fields (those fields at the end of
   * an N2K message that are identified as repeating.  The list of GPS satellite parameters, for example.
   */
  public Vector<N2KField[]> repSet = null;
  
  protected int nextBitOffset;
  
  private N2KPacketDef fldDefs = null;
  private boolean isValid = false;
  private boolean unknown = false;
  
  private static int staticSid;
  private static int staticHeartbeat;

  /**
   * The constructor used by the transport to pass an incoming N2K message into the library.
   * @param pgn The message PGN
   * @param priority The message priority
   * @param dest The message destination
   * @param src The message source
   * @param time The message timestamp
   * @param rawBytes  The raw data which is used to construct the set of N2K fields
   * @param len The length of the raw bytes (excluding the transport header)
   * @param hdrlen The length of the transport header.  The library ignores these bytes
   * at the start of the byte array.
   */
  public N2KPacket(int pgn, byte priority, byte dest, byte src, int time,  byte[] rawBytes, int len, int hdrlen)
  {
    nextBitOffset = 0;
    this.pgn = pgn;
    this.priority = priority;
    this.dest = dest;
    this.src = src;
    this.time = time;

    // Check for multiple instanced of the same PGN
    fldDefs = locatePGN(pgn, null, rawBytes, hdrlen);

    if (fldDefs != null)
    {
      // locatePGN updates nextBitOffset when scanning so reset to start of the packet
      nextBitOffset = 0;
      Trace.normal("Constructing a " + fldDefs.pgnInfo.Id + "(" + fldDefs.pgnInfo.PGN + ")");
      Trace.hexdbg("Packet data", rawBytes, 0, rawBytes.length);
      N2KFieldDef[] fldDefArray = fldDefs.fieldDefArray;
      isValid = true;
      if (fldDefArray == null)
      {
        Trace.alert("No fields on this one");
      }
      else
      {
        fields = new N2KField[fldDefs.numBaseFields];
        for (int i = 0; i < fldDefs.numBaseFields; i++)
        {
          N2KFieldDef flddef = fldDefArray[i];
          fields[i] = new N2KField(this, rawBytes, hdrlen, flddef);
          //                    Trace.alert("Bit offset now " + nextBitOffset);
          if (!fields[i].isValid())
          {
            isValid = false;
          }
        }

        if (fldDefs.numRepFields > 0)
        {
          int repCount = 0;
          repSet = new Vector<N2KField[]>();
          while (len > ((nextBitOffset + 7)/8))
          {
            Trace.normal("Processing repeat index #" + repCount++);
            N2KField[] repFields = new N2KField[fldDefs.numRepFields];
            repSet.add(repFields);

            for (int i = 0; i < fldDefs.numRepFields; i++)
            {
              N2KFieldDef flddef = fldDefArray[fldDefs.numBaseFields + i];
              repFields[i] = new N2KField(this, rawBytes, hdrlen, flddef);
              //                        Trace.alert("Bit offset now " + nextBitOffset);
              if (!repFields[i].isValid())
              {
                isValid = false;
              }
            }
          }
        }

        if (!isValid)
        {
          Trace.alert("!!! INVALID PGN " + pgn);
        }
      }
    }
    else
    {
      unknown = true;
      Trace.alert("!!! UNDEFINED PGN " + pgn);
    }
  }

  private N2KPacketDef locatePGN(int pgn, int[] matchInts, byte[] matchData, int hdrlen)
  {
    N2KPacketDef fldDefs = null;
    List<N2KPacketDef> packetDefs = N2KLib.pgnDefs.get(new Integer(pgn));

    int numpgns = 0;
    if (packetDefs != null)
    {
      numpgns = packetDefs.size();
    }
    if (numpgns == 0)
    {
      Trace.alert("Undefined packet pgn" + pgn);    
      return(null);
    }

    fldDefs = packetDefs.get(0);
    if (fldDefs.matchFields > 0)
    {
      Trace.normal("PGN " + pgn + " has multiple definitions");
      if ((matchInts == null) && (matchData == null))
      {
        Trace.error("Need to specify match array for pgn with multiple versions");
        return(null);
      }

      fldDefs = null;
      for (N2KPacketDef fd : packetDefs)
      {
        N2KFieldDef[] fldDefArray = fd.fieldDefArray;
        if (fldDefArray == null)
        {
          Trace.error("Cannot match pgn " + pgn + " when no fields present in definition");
        }
        else
        {
          int matchCount = 0;
          for (N2KFieldDef flddef : fldDefArray)
          {
            if (flddef.hasMatch)
            {
              int matchInt = 0;
              if (matchInts != null)
              {
                matchInt = matchInts[matchCount];
              }
              else
              {
                nextBitOffset = flddef.bitOff;
                N2KField test = new N2KField(this, matchData, hdrlen, flddef);
                try
                {
                  matchInt = test.getInt();
                }
                catch (Exception ex)
                {
                  Trace.error("Error getting int to test for field " + flddef.id + ": " + ex.getMessage());
                }
              }

              Trace.alert("Check match of field " + flddef.id + " value " + matchInt + " against " + flddef.match);
              if (flddef.match != matchInt)
              {
                Trace.normal("No match - quitting");
                matchCount = 0;
                break;
              }
              else
              {
                matchCount++;
              }
            }
          }
          if (matchCount > 0)
          {
            Trace.alert("Matched!");
            fldDefs = fd;
            break;
          }
        }
      }
    }
    return(fldDefs);
  }

  /**
   * The constructor for getting an empty N2K message, ready for adding the fields.
   * @param pgn The PGN used to look up the N2K field set for this message
   */
  public N2KPacket(int pgn)
  {
    this(pgn, null);
  }

  /**
   * This constructor must be used for N2K messages that have multiple definitions.  Most commonly
   * used for manufacturer specific messages, where the constructor has to match on the manufacturer
   * id.  Supply the match values in the integer array, in the order the appear in the XML definition
   * file.
   * @param pgn The N2K PGN
   * @param match  Used to identify a message when there are duplicates.  The library checks each
   * array entry against the corresponding field in the N2K message definitions to find one
   * that matches.
   */
  public N2KPacket(int pgn, int[] match)
  {
    this.pgn = pgn;
    // Set default priority of 3
    this.priority = (byte)3;
    // Default destination = broadcast
    this.dest = (byte)0xff;
    // Default origin 0
    this.src = 0;

    fldDefs = locatePGN(pgn, match, null, 0);
    if (fldDefs != null)
    {
      N2KFieldDef[] fldDefArray = fldDefs.fieldDefArray;
      if (fldDefArray != null)
      {
        Trace.normal("PGN " + pgn + " has " + fldDefs.numBaseFields + 
                     " base fields, plus " + fldDefs.numRepFields + " repeating");

        fields = new N2KField[fldDefs.numBaseFields];
        for (int i = 0; i < fldDefs.numBaseFields; i++)
        {
          //Trace.debug("Processing field " + i);
          N2KFieldDef fd = fldDefArray[i];
          fields[i] = new N2KField(fd);
        }
      }
    }
  }
  /**
   * Use by the transport to request that the library constructs byte data for transmission.
   * It is important that the offset provided is greater than 6.  This is because the method
   * assumes it can manipulate bit values by loading and storing the bit value into a long (8 bytes),
   * so there must be at least 7 addressable bytes before the first bit value in the data. 
   * @param rawBytes The byte array to be populated by the library
   * @param hdrlen The offset at which the library will start filling in the data
   * @return The number of bytes filled in (which excludes the initial offset)
   */
  public int getRawData(byte[] rawBytes, int hdrlen)
  {
    nextBitOffset = 0;
    if (fields != null)
    {
      for (N2KField fld : fields)
      {
        addField(fld, rawBytes, hdrlen);
      }
    }

    if (repSet != null)
    {
      for (N2KField[] repFields : repSet)
      {
        for (N2KField fld : repFields)
        {
          addField(fld, rawBytes, hdrlen);
        }
      }
    }

    int packetLen = (nextBitOffset + 7) /8;

    // Following is to check that what we would decode from the raw data matches what we just encoded
    if (testEncoding)
    {
      N2KPacket dummy = new N2KPacket(pgn, (byte)2, (byte)255, (byte)0, 0, rawBytes, packetLen, hdrlen);
      String dumstr = dummy.toString();
      Trace.alert("Test packet data: " + dummy.toString());
      String test1 = dumstr.substring(50);
      String test2 = toString().substring(50);
      for (int i = 0; i < test1.length(); i++)
      {
        if (test1.charAt(i) != test2.charAt(i))
        {
          Trace.error("Packet mismatch at offset " + (i + 48));
          Trace.error("Input :" + test1);
          Trace.error("Output:" + test2);
          Trace.hexerr("Raw data :", rawBytes, hdrlen, packetLen);
          break;
        }
      }
    }

    return(packetLen);
  }  

  private void addField(N2KField fld, byte[] rawBytes, int hdrlen)
  {
    N2KFieldDef fd = fld.fieldDef;
    byte[] bytes = null;
    int off;
    switch (fd.type)
    {
      case STRING:
        String str = "";
        try
        {
          str = fld.getString();          
        }
        catch (Exception ex)
        {
          Trace.error("Error getting string to set in field " + fd.name + ":" + ex.getLocalizedMessage());
          str = "";
        }
        bytes = str.getBytes();
        int copylen = bytes.length;
        if (copylen > fd.bitLength/8)
        {
          Trace.error("String too long - truncating");
          copylen = fd.bitLength/8;
        }

        off = hdrlen + nextBitOffset/8;
        for (int i = 0; i < copylen; i++)
        {
          rawBytes[off + i] = bytes[i];
        }
        int len = fd.bitLength/8;
        for (int i = copylen; i < len; i++)
        {
          rawBytes[off + i] = (byte)' ';
        }

        //                      Trace.alert("Next bit offset is "+ nextBitOffset);
        nextBitOffset += (len * 8);
        //                              Trace.alert("After string next bit offset is "+ nextBitOffset);
        break;

      case INT:
      case FLOAT:
        try
        {
          //                            Trace.alert("Next bit offset is "+ nextBitOffset);
          //                            if (nextBitOffset == 256)
          //                            {
          //                            Trace.alert("Next bit offset is "+ nextBitOffset);
          //                            }
          insertBits(rawBytes, (hdrlen * 8) + nextBitOffset, fd.bitLength, fld.getLong());
        }
        catch (Exception ex)
        {
          Trace.error("Error setting int in field " + fd.name + ":" + ex.getCause());
        }
        nextBitOffset += fd.bitLength;
        //                              Trace.alert("After INT next bit offset is "+ nextBitOffset);
        break;

      case BYTES:
        {
          try
          {
            bytes = fld.getBytes();         
          }
          catch (Exception ex)
          {
            Trace.error("Error getting bytes to set in field " + fd.name + ":" + ex.getLocalizedMessage());
            bytes = new byte[fd.bitLength/8];
          }
          len = bytes.length;
          if (len > fd.bitLength/8)
          {
            Trace.error("Bytes array of " + len + " too long - truncating to " + (fd.bitLength/8));
            len = fd.bitLength/8;
          }
          Trace.normal("Processing bytes of length " + len + " offset " + nextBitOffset/8 +
          		        " target length " + rawBytes.length);
          //Trace.alert("Next bit offset is "+ nextBitOffset);
          System.arraycopy(bytes, 0, rawBytes, hdrlen + nextBitOffset/8, len);
          nextBitOffset += fd.bitLength;
          //                    Trace.alert("After bytes[] next bit offset is "+ nextBitOffset);
        }
        break;

      case STRINGSTOP:
        {
          str = "";
          try
          {
            str = fld.getString();          
          }
          catch (Exception ex)
          {
            Trace.error("Error getting string with stop to set in field " + fd.name + ":" + ex.getLocalizedMessage());
            str = "";
          }
          len = str.length();
          //            Trace.alert("Next bit offset is "+ nextBitOffset);
          off = hdrlen + nextBitOffset / 8;
          Trace.alert("STRINGSTOP at offset " + off);
          rawBytes[off++] = 0x03;
          rawBytes[off++] = (byte)(len + 1);      
          System.arraycopy(str.getBytes(), 0, rawBytes, off, len);
          off += len;
          rawBytes[off] = 0x00;
          nextBitOffset += ((len + 3) * 8);
          //            Trace.alert("After Stringstop next bit offset is now "+ nextBitOffset);
        }
        break;
      case LENSTRING:
      case LENCTRLSTRING:
      case ENCODED:
        Trace.alert("Not implemented yet");
        break;

    }
  }

  @Override
  public String toString()
  {
    StringBuffer sb = new StringBuffer(); 
    final SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss.SSS");
    String strtime = f.format(new Date(time));
    String msg = String.format("%s  PGN %5d PRI %2d DST %02x SRC %02x  ", strtime, pgn, priority, dest, src);
    sb.append(msg);
    if ((fldDefs != null) && (fldDefs.pgnInfo != null))
    {
      sb.append(fldDefs.pgnInfo.Id);
    }
    else
    {
      sb.append("UNDEFINED");
    }
    sb.append(",");
    if (fields != null)
    {
      for (N2KField field : fields)
      {
        if (field == null) break;
        sb.append(field.toString());
        sb.append(",");
      }
      if (repSet != null)
      {
        for (N2KField[] repFields : repSet)
        {
          for (N2KField field : repFields)
          {
            if (field == null) break;
            sb.append(field.toString());
            sb.append(",");           
          }
        }
      }
    }
    else
    {
      sb.append("No fields");
    }
    return(sb.toString());
  }

  /**
   * Whether the packet contains good data or not
   * @return True if the packet is good
   */
  public boolean isValid()
  {
    return(isValid);
  }
  /**
   * Whether the packet was found in the XML definition file
   * @return True if the lookup succeeded
   */
  public boolean unknown()
  {
    return(unknown);
  }

  // Utility function to extract N2K integer bits from raw data
  protected long extractBits(byte[] bytes, int off, int len, boolean signed)
  {
    long res;
    int byteoff = off/8;
    int endbyteoff = (off + len + 7) / 8;
    if (endbyteoff > bytes.length)
    {
      Trace.alert("Packet " + fldDefs.pgnInfo.Id + " extracting at offset " + byteoff + 
      		        " length " + (len/8) + " is beyond end of message, end offset " + endbyteoff);
      // Return not available
      res = Utils.getNotAvailable(len, signed);
      return(res);
    }

    if (((off % 8) == 0) && ((len % 8) == 0))
    {
      //                Trace.alert("Simple case bit extraction at offset " + (off-88) + " length " + len);
      int bytelen = len/8;
      switch (bytelen)
      {
        case 1:
          res = bytes[byteoff];
          if (!signed)
          {
            res = res & 0x00ff;
          }
          break;
        case 2:
          res = Utils.getShort(bytes, byteoff);
          if (!signed)
          {
            res = res & 0x00ffff;
          }
          break;
        case 4:
          res = Utils.getInt(bytes, byteoff);
          if (!signed)
          {
            res = res & 0x00ffffffffL;
          }
          break;
        default:
          res = Utils.getLong(bytes, byteoff, bytelen);
          if ((!signed) && (len < 64))
          {
            res = res & (0x7fffffffffffffffL >> (63 -len));
          }
          break;
      }
    }
    else
    {
      // we have to be a  bit careful extracting bits at the start because we
      // can't assume we can load a full integer preceding the byte we want
      int fieldlen = endbyteoff - byteoff;
      switch (fieldlen)
      {
        case 1:
          res = bytes[byteoff];
          res = res & 0x00ff;
          break;
        case 2:
          res = Utils.getShort(bytes,  byteoff);
          res = res & 0x00ffff;
          break;
        case 3:
          res = Utils.getInt(bytes,  byteoff, 3);
          res = res & 0x00ffffff;
          break;
        case 4:
          res = Utils.getInt(bytes, byteoff);
          break;
        default:
          Trace.error("Zero length integer");
          res = 0;
          break;
      }

      // We are adding bytes to the end of the field for alignment, so allow for this
      // in the shifting 
      // int extraBytes = byteoff - startbyteoff;
      int shift = off % 8;
      int maskshift = 32 - len;
      //Trace.debug("At offset " + off + " len " + len +  " shift is " + shift + " maskshift is " + maskshift);
      //Trace.debug(String.format("Extracted %08X",(int)res));
      res = res >> shift;
      long mask = 0x00ffffffffL >> maskshift;
      //Trace.debug(String.format("Mask is %08X",(int)mask));
      res = res & mask;
      //Trace.debug(String.format("Masked result is %08X",(int)res));
      if (signed)
      {
        res = (res << (32 - len)) >> (32 - len);
        //Trace.debug(String.format("Signed so result is %08X",(int)res));
      }
    }

    //     Trace.alert(String.format("Result of extract %08x", res));
    return(res);
  }

  // Utility function to insert N2K integer bits into raw data
  void insertBits(byte[] bytes, int off, int len, long insert)
  {
    int byteoff = off/8;
    if (((off % 8) == 0) && ((len % 8) == 0))
    {
      int bytelen = len/8;

      //Trace.debug("Simple insert of " + bytelen + " bytes at byte offset " + byteoff);
      switch (bytelen)
      {
        case 1:
          bytes[byteoff] = (byte)insert;
          break;
        case 2:
          Utils.setShort(bytes, byteoff, (int)insert);
          break;
        case 4:
          Utils.setInt(bytes, byteoff, (int)insert);
          break;
        default:
          Utils.setInt(bytes, byteoff, insert, bytelen);
          break;
      }
      return;
    }
    else
    {
      int endbyteoff = (off + len + 7) / 8;  // exclusive
      if (endbyteoff > bytes.length)
      {
        Trace.error("Inserting at offset " + off + " length " + len + " is beyond end of message, end offset " + endbyteoff);
        return;
      }
      int numbytes = endbyteoff - byteoff;
      if (numbytes > 4)
      {
        Trace.error("Invalid unaligned integer byte length " + numbytes);
        return;
      }
      int startbyteoff = endbyteoff - 4;
      int extrabits = (byteoff - startbyteoff) * 8;
      int shift = (off % 8) + extrabits;
      long mask = (0x00ffffffffL >> (32 - len)) << shift;
      int clearmask = (int)(~mask);
      //Trace.debug(String.format("Store %08X at offset %d len %d end byte (off+len+7)/8 %d load from " +
      //                          "%d extra bits %d shift (off mod 8 + extrabits) is %d mask is %08X",
      //                          (int)insert,off,len,endbyteoff,startbyteoff,extrabits,shift,(int)mask));      
      int val;
      val = Utils.getInt(bytes, startbyteoff);
      //Trace.debug(String.format("Extracted %08X",val));
      val = val & clearmask;
      val = val | ((int)insert << shift);
      //Trace.debug(String.format("Combined value %08X", val));
      Utils.setInt(bytes, startbyteoff, (int)val);
      //Trace.hexalt("Msg is now", bytes, 0, endbyteoff);
    }
  }

  /**
   * Generate a new N2K SID  (Just increment a static integer)
   * @return The new SID
   */
  public static int getNewSid()
  {
    staticSid++;
    if (staticSid > 251)
    {
      staticSid = 0;
    }
    return(staticSid);
  }
  /**
   * Returns the current SID, if the UI wishes to link N2K messages together.
   * @return The current SID value
   */
  public static int getSid()
  {
    return(staticSid);
  }

  /**
   * Generate a new value for the N2K heartbeat message (Just increment a static integer)
   * @return The new heartbeat ID
   */
  public static int getNewHeartbeat()
  {
    staticHeartbeat++;
    if (staticHeartbeat > 251)
    {
      staticHeartbeat = 0;
    }
    return(staticHeartbeat);
  }

  /**
   * Used to construct an array of fields at the end of the N2K message.  
   * The field objects are all allocated with the correct types, so the caller can 
   * just use the setInt/setDecimal... to fill in the necessary values. 
   * @return the array of fields to be filled in
   */
  public N2KField[] addRepSet()
  {
    N2KField[] repFields = null;
    if (fldDefs.numRepFields == 0)
    {
      Trace.error("Cannot add repeats to a non-repeating PGN");
    }
    else
    {
      if (repSet == null)
      {
        repSet = new Vector<N2KField[]>();
      }
      repFields = new N2KField[fldDefs.numRepFields];
      repSet.add(repFields);
      N2KFieldDef[] fldDefArray = fldDefs.fieldDefArray;
      for (int j = 0; j < fldDefs.numRepFields; j++)
      {
        N2KFieldDef fd = fldDefArray[fldDefs.numBaseFields + j];
        //Trace.debug("Processing repeating field " + fd.id);
        repFields[j] = new N2KField(fd);
      }
    }
    return(repFields);
  }
}