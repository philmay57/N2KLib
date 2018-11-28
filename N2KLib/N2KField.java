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
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

import N2KDefs.PGNEnumPair;
import Utils.Trace;
import Utils.Utils;
/**
 * An N2KPacket is made up of N2KFields. 
 * <br><br>
 * The N2KField provides getters and setters for the values it may contain.  Only one value is 
 * relevant to a particular field, and it will throw an N2KTypeException if an attempt is made
 * to get/set an inappropriate value.
 * 
 * @author PhilMay
 *
 */
public class N2KField
{
	/**
	 * The field definition extracted from the N2K definitions XML file
	 */
  public  N2KFieldDef fieldDef;
  
  private boolean isValid;
  private boolean  notPresent;
  private long    intval;
  private boolean intset;
  private String  stringval;
  private boolean stringset;
  private byte[]  binval;
  private boolean binset;

  /**
   * The field availability values.  Use getAvailability and setNotAvailable/setReserved/setOutOfRange
   * to access these values in the field. 
   */
  public enum Availability
  {
  	AVAILABLE, NOT_AVAILABLE, OUT_OF_RANGE, RESERVED
  }
 
  protected N2KField(N2KFieldDef flddef)
  {
    fieldDef = flddef;
    if (flddef.reserved)
    {
      Trace.normal("Setting default value for reserved field in pgn " + flddef.pgnInfo.PGN);
      setDefault();
    }
  }

  protected  N2KField(N2KPacket packet, byte[] bytes, int hdrlen, N2KFieldDef flddef)
  {
    isValid = true;
    //Trace.debug("Constructing field " + flddef.id);
    fieldDef = flddef;
    int byteoff = hdrlen + (packet.nextBitOffset / 8); 
    //          Trace.alert("Byte off now " + byteoff + " and bit off " + packet.nextBitOffset);
    int bitoff  = (hdrlen * 8) + packet.nextBitOffset; 
    int bytelen = (flddef.bitLength + 7)/8;
    if (byteoff >= bytes.length)
    {
      Trace.error("Field " + flddef.id + " starts beyond end of packet");
      notPresent = true;    
      return;
    }

    switch (flddef.type)
    {
      case STRING:
        int maxlen = Math.min(bytelen, bytes.length - byteoff);
        byte[] fld = Arrays.copyOfRange(bytes, byteoff, byteoff + maxlen);
        stringval = new String(fld);
        stringset = true;
        packet.nextBitOffset += flddef.bitLength;
        break;

      case INT:
      case FLOAT:
        intval = packet.extractBits(bytes, bitoff, flddef.bitLength, flddef.signed);
        intset = true;
        packet.nextBitOffset += flddef.bitLength;
        break;

      case STRINGSTOP:
        {
          byteoff += 2;
          int endbyteoff = byteoff ;
          while (bytes[endbyteoff] != 0)
          {
            //                          Trace.alert(String.format("Inspecting byte %02X at offset %d", bytes[endbyteoff], endbyteoff));
            endbyteoff++;
          }
          Trace.alert("Creating string from " + byteoff + " to " + endbyteoff + " of length " + (endbyteoff - byteoff));
          stringval = new String(bytes, byteoff, endbyteoff - byteoff);
          packet.nextBitOffset = (endbyteoff + 1 - hdrlen) * 8;
          //                    Trace.alert("Handled STRINGSTOP, offset now " + packet.nextBitOffset);
        }
        break;

      case LENSTRING:
      case LENCTRLSTRING:
      case ENCODED:
        Trace.alert(flddef.type + "Not implemented yet");
        isValid = false;
        break;
      default:
        Trace.error("Field " + flddef.type + " is not a valid type");
        isValid = false;
        break;
    }
  }

  /**
   * Used to get an integer value from an N2K field.  You should use getDecimal if the integer  
   * has a resolution specified in the XML file.
   * @return The integer value
   * @throws N2KTypeException If the field is not an integer or it has not been set
   */
  public int getInt() throws N2KTypeException
  {
    if (fieldDef.type == N2KFieldDef.Type.INT)
    {
      if (intset)
      {
        if (fieldDef.bitLength > 32)
        {
          throw new N2KTypeException("Need to use getLong for 64 bit integers");
        }
        else
        {
          Trace.normal("Get of field " + fieldDef.id + " returns " + (int)intval);
          return((int)intval);
        }
      }
      else
      {
        throw new N2KTypeException("Get for integer from field " + fieldDef.id + " in " + fieldDef.pgnInfo.Id + " when not set");       
      }
    }
    else
    {
      throw new N2KTypeException("Invalid get for integer from field " + fieldDef.id + " in " + fieldDef.pgnInfo.Id);
    }
  }

  /**
   * Used to get a decimal integer value from an N2K field.  The integer is multiplied by the 
   * resolution to give the double value.
   * @return The decimal value
   * @throws N2KTypeException If the field is not an integer with a resolution, or it has not been set
   */
  public double getDecimal() throws N2KTypeException
  {
    if (fieldDef.resolution == 0)
    {
      throw new N2KTypeException("Invalid get of decimal when no resolution for field " + fieldDef.id + " in " + fieldDef.pgnInfo.Id);        
    }
    else
    {
      long val = getLong();
      return(val * fieldDef.resolution);
    }
  }

  /**
   * Used to get a long value from an N2K field.  You should use getDecimal if the integer  
   * has a resolution specified in the XML file.
   * @return The integer value
   * @throws N2KTypeException If the field is not an integer/long or it has not been set
   */
  public long getLong() throws N2KTypeException
  {
    if (fieldDef.type == N2KFieldDef.Type.INT)
    {
      if (intset)
      {
        return(intval);
      }
      else
      {
        throw new N2KTypeException("Get for long integer from field " + fieldDef.id + " in " + fieldDef.pgnInfo.Id + " when not set");        
      }
    }
    else
    {
      throw new N2KTypeException("Invalid get for long integer from field " + fieldDef.id + " in " + fieldDef.pgnInfo.Id);
    }
  }

  /**
   * Used to set an integer value in an N2K field.  You should use setDecimal if the integer  
   * has a resolution specified in the XML file.
   * @param val The value to be set
   * @throws N2KTypeException If the field is not an integer
   */
  public void setInt(int val) throws N2KTypeException
  {
  	setLong(val);
  }

  /**
   * Used to set a decimal value in an N2K field.  The supplied value is divided by 
   * the resolution and then cast to an integer which is stored as an integer in the 
   * field.
   * @param val The value to be set
   * @throws N2KTypeException If the field is not an integer with resolution
   */
  public void setDecimal(double val) throws N2KTypeException
  {
    if (fieldDef.type == N2KFieldDef.Type.INT)
    {
      if (fieldDef.resolution != 0)
      {
        intval = (long)(val/fieldDef.resolution);
        intset = true;
        isValid = true;
        Trace.normal("Set of field " + fieldDef.id + " to " + val);
      }
      else
      {
        throw new N2KTypeException("Invalid set of decimal when no resolution for field " + fieldDef.id + " in " + fieldDef.pgnInfo.Id);        
      }
    }
    else
    {
      throw new N2KTypeException("Invalid set of integer for non integer field " + fieldDef.id + " in " + fieldDef.pgnInfo.Id);
    }
  }
  
  /**
   * Used to set an long value in an N2K field.  You should use setDecimal if the integer  
   * has a resolution specified in the XML file.
   * @param val The value to be set
   * @throws N2KTypeException If the field is not an integer
   */
  public void setLong(long val) throws N2KTypeException
  {
    if (fieldDef.type == N2KFieldDef.Type.INT)
    {
      if ((fieldDef.resolution != 0) &&
          (fieldDef.resolution != 1))
      {
        Trace.alert("Field " + fieldDef.id + " - Setint used to set value with resolution - are you sure?");
      }
      long maxval;
      long minval;
      long longval = val;
      if (fieldDef.signed)
      {
        maxval = 0x7fffffffffffffffL >> (64 - fieldDef.bitLength);
        minval = 0x8000000000000000L >> (64 - fieldDef.bitLength);
      }
      else
      {
        longval = longval & 0x00ffffffff;  // make positive
        maxval = 0x7fffffffffffffffL >> (63 - fieldDef.bitLength);
        minval = 0;
      }
      // Warn if the value will be truncated
      if ((longval > maxval) || (longval < minval))
      {
        Trace.error("WARNING: Value " + longval + " will not fit in " + fieldDef.bitLength + 
                    " bit field " + fieldDef.id);
      }
      intval = longval;
      intset = true;
      isValid = true;
      Trace.normal("Set of field " + fieldDef.id + " to " + longval);
    }
    else
    {
      throw new N2KTypeException("Invalid set of integer from field " + fieldDef.id + " in " + fieldDef.pgnInfo.Id);
    }
  }

  /**
   * Use this method to access the field's "availability".
   * N2K integer values have a "not available" value which is defined as the largest positive
   * integer that can be stored in the field.  So for a three bit unsigned field the "not available"
   * value is 7, for a four bit field it is 15 etc.  Then there are "out of range" which is N/A-1 and
   * "reserved" which is N/A-2.  If the field is signed then these values exclude the sign bit so
   * "not available" for a 16 bit integer is 0x7fff.
   * @return The availability of this field
   */
  public Availability getAvailability()
  {
    if ((fieldDef.type != N2KFieldDef.Type.INT) &&
        (fieldDef.type != N2KFieldDef.Type.FLOAT))
    {
      return(Availability.AVAILABLE);
    }

    Availability avail = Availability.AVAILABLE;

    long na = Utils.getNotAvailable(fieldDef.bitLength, fieldDef.signed);
    
    if (intval == na)
    {
      avail = Availability.NOT_AVAILABLE;
    }
    else if (fieldDef.bitLength > 3)
    {
      if (intval == (na - 1))
      {
        avail = Availability.OUT_OF_RANGE;
      }
      else if (intval == (na - 2))
      {
        avail = Availability.RESERVED;
      }
    }
    return(avail);
  }

  /**
   * The string value of this field (NOT the string representation, which is toString)
   * @return The string value
   * @throws N2KTypeException if this is not a string field or it has not been set
   */
  public String getString() throws N2KTypeException
  {
    if (fieldDef.type == N2KFieldDef.Type.STRING)
    {
      if (stringset)
      {
        return(stringval);
      }
      else
      {
        throw new N2KTypeException("Get for string from field " + fieldDef.id + " in " + fieldDef.pgnInfo.Id + " when not set");        
      }
    }
    else
    {
      throw new N2KTypeException("Invalid get of string from field " + fieldDef.id + " in " + fieldDef.pgnInfo.Id);
    }
  }

  /**
   * Sets the string value of this field
   * @param val The string to set in the field
   * @throws N2KTypeException if this is not a string field
   */
  public void setString(String val) throws N2KTypeException
  {
    if (fieldDef.type == N2KFieldDef.Type.STRING)
    {
      int len = fieldDef.bitLength/8;
      int inlen = val.length();
      if (inlen < len)
      {
        stringval = Utils.padRight(val, len);
      }
      else if (inlen > len)
      {
        throw new N2KTypeException("String " + val + " too long for field " + fieldDef.id + " in " + fieldDef.pgnInfo.Id);
      }
      else
      {
        stringval = val;
      }
      stringset = true;
      isValid = true;
      Trace.normal("Set of field " + fieldDef.id + " to " + val);
    }
    else
    {
      throw new N2KTypeException("Invalid set of string for field " + fieldDef.id + " in " + fieldDef.pgnInfo.Id);
    }
  }

  /**
   * Sets the byte value of this field
   * @param val The byte value
   * @throws N2KTypeException If the field is not a byte field
   */
  public void setBytes(byte[] val) throws N2KTypeException
  {
    if (fieldDef.type == N2KFieldDef.Type.BYTES)
    {
      binval = val;
      binset = true;
      isValid = true;
      Trace.hex("Set of field " + fieldDef.id + " to:", val, 0, val.length);
    }
    else if ((fieldDef.type == N2KFieldDef.Type.INT) && fieldDef.binint)
    {
      intval = Utils.getInt(val, 0,val.length);
      intset = true;
      isValid = true;
      Trace.hex("Set of field " + fieldDef.id + " to (integer representation of):", val, 0, val.length);
    }
    else
    {
      throw new N2KTypeException("Invalid set of string for field " + fieldDef.id + " in " + fieldDef.pgnInfo.Id);
    }
  }

  /**
   * returns the byte value of the field
   * @return The byte value
   * @throws N2KTypeException If the value has not been set or this is not a byte field
   */
  public byte[] getBytes() throws N2KTypeException
  {
    byte[] bytes = null;
    if ((fieldDef.type == N2KFieldDef.Type.BYTES) ||
        ((fieldDef.type == N2KFieldDef.Type.INT) && fieldDef.binint))
    {
      if (fieldDef.type == N2KFieldDef.Type.BYTES)
      {
        if (binset)
        {
          bytes = binval;
        }
        else
        {
          throw new N2KTypeException("Get of bytes when not set for field " + fieldDef.id + " in " + fieldDef.pgnInfo.Id);
        }
      }
      else
      {
        if (intset)
        {
          int bytelen = (fieldDef.bitLength + 7)/8;
          bytes = new byte[bytelen];
          Utils.setInt(bytes, 0, intval, bytelen);
        }
        else
        {
          throw new N2KTypeException("Get of bytes when not set for field " + fieldDef.id + " in " + fieldDef.pgnInfo.Id);
        }       
      }
    }
    else
    {
      throw new N2KTypeException("Invalid get of bytes for field " + fieldDef.id + " in " + fieldDef.pgnInfo.Id);
    }
    Trace.hex("Get of field " + fieldDef.id + " gives:", bytes, 0, bytes.length);
    return(bytes);
  }

  /**
   * The string representation of this field.  "Name : Value"
   */
  public String toString()
  {
    StringBuffer res = new StringBuffer();
    if (fieldDef.id.equals(N2KFieldDef.FD_RESERVED))
    {
      res.append(fieldDef.id);
    }
    else if (!isValid)
    {
      res.append(fieldDef.id + ": INVALID");      
    }
    else if (notPresent)
    {
      res.append(fieldDef.id + ": NOT PRESENT");      
    }
    else
    {
      res.append(fieldDef.id + ":");
      switch (fieldDef.type)
      {
        case LENSTRING:
        case LENCTRLSTRING:
        case STRINGSTOP:
        case STRING:
          res.append(stringval);
          break;
        case FLOAT:
        case INT:
          Availability avail = getAvailability();
          if (avail == Availability.AVAILABLE)
          {
            // If an enum then get the text first
            if (fieldDef.pgnField.EnumValues != null)
            {
              String enumVal = null;
              for (PGNEnumPair enumPair : fieldDef.pgnField.EnumValues.EnumPair)
              {
                if (intval == enumPair.Value)
                {
                  enumVal = enumPair.Name;
                  break;
                }
              }
              if (enumVal == null)
              {
                res.append("UNDEFINED(");
              }
              else
              {
                res.append(enumVal);
                res.append("(");
              }
              res.append(intval);
              res.append(")");
            }
            else
            {
              if (fieldDef.type == N2KFieldDef.Type.FLOAT)
              {
                res.append(String.format("%f", intval));
              }
              else // integer, but may have precision
              {
                if ((fieldDef.resolution == 0) ||
                    (fieldDef.resolution == 1))
                {
                  // Simple int
                  res.append(String.format("%d", intval));
                }
                else
                {
                  res.append(String.format("%f", intval * fieldDef.resolution));
                }
              }
            }
          }
          else
          {
          	switch (avail)
          	{
          	case AVAILABLE:
          		res.append("Available");
          		break;
          	case NOT_AVAILABLE:
          		res.append("N/A");
          		break;
          	case OUT_OF_RANGE:
          		res.append("OUT OF RANGE");
          		break;
          	case RESERVED:
          		res.append("RESERVED");
          		break;
          	}
          }

          break;
        case ENCODED:
          res.append(DatatypeConverter.printHexBinary(binval));
          break;
        default:
          res.append("UNSUPPORTED");
          break;
      }
    }

    return(res.toString());
  }

  /**
   * Set the field to the value that indicates a value is not available
   */
  public void setNotAvailable()
  {
    if ((fieldDef.type == N2KFieldDef.Type.INT) ||
        (fieldDef.type == N2KFieldDef.Type.FLOAT) ||
        ((fieldDef.type == N2KFieldDef.Type.BYTES) && fieldDef.binint))
    {
      intval = Utils.getNotAvailable(fieldDef.bitLength, fieldDef.signed);
      intset = true;
      isValid = true;
    }
    else
    {
      Trace.error("Can only set N/A for integer or float");
    }
  }

  /**
   * Set the field to the value that indicates the field is reserved
   */
  public void setReserved()
  {
    if ((fieldDef.type == N2KFieldDef.Type.INT) ||
        (fieldDef.type == N2KFieldDef.Type.FLOAT) ||
        ((fieldDef.type == N2KFieldDef.Type.BYTES) && fieldDef.binint))
    {
      intval = Utils.getNotAvailable(fieldDef.bitLength, fieldDef.signed);
      intval -= 2;
      intset = true;
      isValid = true;
    }
    else
    {
      Trace.error("Can only set Reserved for integer or float");
    }

  }

  /**
   * Set the field to the value that indicates the field is out of range
   */
  public void setOutOfRange()
  {
    if ((fieldDef.type == N2KFieldDef.Type.INT) ||
        (fieldDef.type == N2KFieldDef.Type.FLOAT) ||
        ((fieldDef.type == N2KFieldDef.Type.BYTES) && fieldDef.binint))
    {
      intval = Utils.getNotAvailable(fieldDef.bitLength, fieldDef.signed);
      intval -= 1;
      intset = true;
      isValid = true;
    }
    else
    {
      Trace.error("Can only set Out Of Range for integer or float");
    }

  }

  /**
   * Set the field to the default value for its type.
   */
  public void setDefault()
  {
    try
    {
      switch (fieldDef.type)
      {
        case FLOAT:
        case INT:
          setReserved();
          break;
        case STRING:
          setString("");
          break;
        default:
          Trace.error("Invalid type " + fieldDef.name + " for reserved field");
          break;
      }
    }
    catch (Exception ex)
    {
      Trace.error("Could not set reserved");
    }
    Trace.normal("Set of field " + fieldDef.id + " to DEFAULT");
  }
  
  /**
   * Indicates whether the field has a valid value set or not
   * @return true if the field has a good value 
   */
  public boolean isValid()
  {
  	return(isValid);
  }
}
