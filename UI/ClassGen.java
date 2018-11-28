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
package UI;
//
//  This class auto-generates the n2K class file from the XML file.  It can be run whenever a new XML
// file is to be supported. 
//
//  The XML file needs to be massaged into shape, particularly in the area of enums, where they
// contain spaces and invalid characters.  Also any duplicates need to be suffixed (eg reserved1,
// reserved2..)
//
import java.util.HashMap;
import java.util.List;

import N2KDefs.PGNEnumPair;
import N2KDefs.PGNField;
import N2KDefs.PGNInfo;
import N2KLib.N2KLib;
import Utils.Utils;

// Construct the N2K class definitions from the XML file
// We write the class files to c:\temp\N2K.java.  When ready for deployment 
// it should be copied to the N2KMsgs package in the build path.

public class ClassGen 
{

  public static void main(String[] parms)
  {
        @SuppressWarnings("unused")
    N2KLib n2klib = new N2KLib(null);
    List<PGNInfo> pgnInfo = N2KLib.pgnInfo;

    StringBuffer hdr = new StringBuffer();
    hdr.append("package N2KMsgs;\r\n\r\n");
    hdr.append("public class N2K\r\n{\r\n");
    for (int i = 0; i < pgnInfo.size(); i++)
    {
      PGNInfo inf = pgnInfo.get(i);
      // If this is a duplicate name then we need to suffix it
      int infCount = 1;
      for (int j = 0; j < i; j++)
      {
        PGNInfo inf2 = pgnInfo.get(j);
        if (inf2.Id.equals(inf.Id))
        {
          infCount++;
        }
      }
      String pgnName = inf.Id + (infCount > 1 ? infCount : "");

      hdr.append("  // PGN(" + inf.PGN + ") - " + inf.Description + "\r\n");
      hdr.append("  public final static int " + pgnName + "_pgn = " + inf.PGN + ";\r\n");
      hdr.append("  public final static class " + pgnName + "\r\n  {\r\n");
      if ((inf.pgnFields != null) && (inf.pgnFields.pgnField != null))
      {
        int numFields = inf.pgnFields.pgnField.size();
        //Trace.debug("Got " + numFields + " fields");
        for (int k = 0; k < numFields; k++)
        {
          PGNField fld = inf.pgnFields.pgnField.get(k);
          // If this is a duplicate field name then we need to suffix it
          int fldCount = 1;
          for (int l = 0; l < k; l++)
          {
            PGNField fld2 = inf.pgnFields.pgnField.get(l);
            if (fld2.Id.equals(fld.Id))
            {
              fldCount++;
            }
          }
          String fldName = fld.Id + (fldCount > 1 ? fldCount : "");
          if (fldName.equals("switch")) fldName = "switch_field";
          if (fldName.equals("")) fldName = "blank";
          char firstchar = fldName.charAt(0);
          if (((firstchar >= '0') && (firstchar <= '9')) || (firstchar == '_'))
          {
            fldName = "A" + fldName;
          }
          if (fld.Description != null)
          {
            hdr.append("    // " + fld.Description);
            if (fld.Units != null)
            {
              hdr.append(" (" + fld.Units + ")\r\n");
            }
            else
            {
              hdr.append("\r\n");
            }
          }
          int firstRep = numFields - inf.RepeatingFields;

          // We put repeating fields into a separate subclass called "rep"
          if (fld.Order > firstRep)
          {
            if (fld.Order == firstRep + 1)
            {
              hdr.append("    public final static class rep\r\n    {\r\n");
            }

            int fldIndex = fld.Order - firstRep - 1;
            hdr.append("      public final static int " + fldName + " = " + fldIndex + ";\r\n"); 
          }
          else
          {
            hdr.append("    public final static int " + fldName + " = " + (fld.Order - 1) + ";\r\n"); 
          }

          if (fld.EnumValues != null)
          {
            int instance = 2;
            HashMap<String, String> used = new HashMap<String, String>();
            hdr.append("      // " + fldName + " permitted values\r\n");
            hdr.append("      public static final class " + fldName + "_values {\r\n");
            for (PGNEnumPair pair : fld.EnumValues.EnumPair)
            {
              String cleanName = pair.Name.replaceAll(" ", "");
              cleanName = cleanName.replaceAll("[\\+-\\/\\\\\\(\\):#]", "_");
              while (cleanName.startsWith("_")) cleanName = "A" + cleanName;
              firstchar = cleanName.charAt(0);
              if (((firstchar >= '0') && (firstchar <= '9')) || (firstchar == '_'))
              {
                cleanName = "A" + cleanName;
              }
              // Add a suffix to duplicates
              if (used.get(cleanName) != null)
              {
                cleanName = cleanName + instance;
                instance++;
              }
              else
              {
                used.put(cleanName, cleanName);
              }
              hdr.append("        public static final int " + cleanName + " = " + pair.Value + ";\r\n"); 
            }
            hdr.append("      }\r\n\r\n");
          }

        }
        if (inf.RepeatingFields > 0)
        {
          hdr.append("    }\r\n");
        }
      }
      hdr.append("  }\r\n\r\n");
    }
    hdr.append("}\r\n");
    byte[] bytes = hdr.toString().getBytes();
    Utils.writeWholeFile("c:\\temp\\N2K.java", bytes, 0, bytes.length);

  }
}
