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
import N2KDefs.PGNField;
import N2KDefs.PGNInfo;
import Utils.Trace;
/**
 * Contains definitions of N2K fields
 * @author PhilMay
 *
 */
public class N2KFieldDef
{
	/**
	 * The original N2K message definition extracted from the XML file using JAXB
	 */
  public PGNInfo pgnInfo;
	/**
	 * The original N2K field definition extracted from the XML file using JAXB
	 */
  public PGNField pgnField;
	/**
	 * The id from the XML file
	 */
  public String id;
	/**
	 * The name from the XML file
	 */
  public String name;
	/**
	 * The bit length from the XML file
	 */
  int bitLength;
	/**
	 * The bit offset from the XML file
	 */
  int bitOff;
	/**
	 * The bit start from the XML file
	 */
  int bitStart;
	/**
	 * Whather the field is a signed or unsigned value
	 */
  boolean signed;
	/**
	 * The match value from the XML file
	 */
  int match;
	/**
	 * The resolution from the XML file
	 */
  double  resolution;

	/**
	 * Whather this field is byte aligned or not
	 */
  boolean aligned = false;
	/**
	 * Whether the match value should be used
	 */
  boolean hasMatch = false;
	/**
	 * true if this field is a binary of less than 8 bytes that has been treated as integer
	 */
  boolean binint = false;  
	/**
	 * true if this field is reserved space
	 */
  boolean reserved = false;

  enum Type
  {
    STRING,INT,FLOAT,BYTES,LENSTRING,LENCTRLSTRING,STRINGSTOP,ENCODED
  }
  Type type;

  public static final String FD_INTEGER = "Integer";
  public static final String FD_BINARY  = "Binary data";
  public static final String FD_LOOKUP  = "Lookup table";
  public static final String FD_MANF    = "Manufacturer code";
  public static final String FD_BITFIELD= "Bitfield";
  public static final String FD_FLOAT   = "IEEE Float";
  public static final String FD_TIME    = "Time";
  public static final String FD_DATE    = "Date";
  public static final String FD_RESERVED = "reserved";

  static final String[] strType =
  {FD_BINARY,FD_LOOKUP,FD_INTEGER,FD_MANF,
    "Temperature","ASCII text",FD_TIME,FD_DATE,
    "Latitude","Longitude","Pressure",FD_BITFIELD,
    "ASCII or UNICODE string starting with length and control byte",FD_FLOAT,
    "String with start/stop byte","Decimal encoded number",
    "ASCII string starting with length byte",
    "Pressure (hires)","Temperature (hires)"};
  static final Type[] enumType =    
  {Type.BYTES, Type.INT, Type.INT, Type.INT,
    Type.INT, Type.STRING, Type.INT, Type.INT,
    Type.INT, Type.INT, Type.INT, Type.INT,
    Type.LENCTRLSTRING, Type.FLOAT,
    Type.STRINGSTOP, Type.ENCODED,
    Type.LENSTRING,
    Type.INT, Type.INT
  };

  protected N2KFieldDef(PGNInfo info, PGNField fld, int offset)
  {
    pgnInfo = info;
    pgnField = fld;

    int ix;
    if (fld.Type == null)
    {
      // For unspecified data type we infer integer if it has any elements unique to integers 
      if ((fld.Signed || fld.Resolution != 0 || fld.Units != null) || (fld.BitLength <= 32))
      {
        fld.Type = FD_INTEGER;
      }
      else
      {
        // Unspecified types are handled as byte arrays
      	Trace.alert("Packet " + pgnInfo.Id + " field " + fld.Id + 
      			        " handling unspecified non-integer thype as a bitfield");
        fld.Type = FD_BITFIELD;
      }
    }

    for (ix = 0; ix < strType.length; ix++)
    {
      if (strType[ix].equals(fld.Type)) break;
    }
    if (ix == strType.length)
    {
      Trace.error("Error: In pgn " + info.PGN + " field " + fld.Id + " undefined field type " + fld.Type);
    }
    else
    {
      type = enumType[ix];
      id = fld.Id;
      name = fld.Name;
      bitLength = fld.BitLength;
      bitOff = fld.BitOffset;
      if (bitOff != offset)
      {
        // If offset is not present then just set it
        if (bitOff == 0)
        {
          bitOff = offset;
        }
        else
        {
          // Log an error in the definitions
          Trace.error("ERROR IN FIELD DEF, pgn " + pgnInfo.PGN + " field " + id + 
                      " - offset " + bitOff + " is not contiguous ");
        }
      }
      bitStart = fld.BitStart;
      signed = fld.Signed;
      resolution = fld.Resolution;
      if (fld.Match != null)
      {
        match = Integer.parseInt(fld.Match);
        hasMatch = true;
      }
      if (id.equals(FD_RESERVED))
      {
        reserved = true;
      }
      if (((bitOff %8) == 0) && (((bitOff + bitStart) % 8) == 0))
      {
        aligned = true;
      }
      if ((bitLength + (bitOff % 8)) <= 32)
      {
        // We store binary data that we can load into a 32 bit integer as integer internally
        type = Type.INT;
        binint = true;
      }
    }
  }
}
