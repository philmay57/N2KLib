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
import N2KDefs.PGNInfo;

public class N2KPacketDef
{
  public PGNInfo pgnInfo;
  N2KFieldDef[] fieldDefArray;
  int totalLength = 0;
  int matchFields = 0;
  int numBaseFields;
  public int numRepFields;

  public N2KPacketDef(int numDefs, PGNInfo pgnInfo)
  {
    fieldDefArray = new N2KFieldDef[numDefs];
    this.pgnInfo = pgnInfo;
    this.numRepFields = pgnInfo.RepeatingFields;
    numBaseFields = numDefs - numRepFields;
  }

  void setLength(int length)
  {
    totalLength = length;
  }
}
