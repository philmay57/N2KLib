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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import Utils.Trace;
import Utils.Utils;

public class Props extends Properties 
{
  static final long serialVersionUID = 1;       
  static final String pgn = "pgn";

  public static final String sLat = "Latitude";
  public static final String sLng = "Longitude";
  public static final String sSpd = "Speed";
  public static final String sDep = "Depth";
  public static final String sHdg = "Heading";
  public static final String sTid = "Tide";
  public static final String sTda = "TideAngle";
  public static final String sCog = "COG";
  public static final String sWs  = "WindSpeed";
  public static final String sWa  = "WindAngle";


  File file;

  Props()
  {
    super();
    String location = Utils.getHomePath();
    Trace.alert("props file is:" +location + "n2kproperties.txt");
    file = new File(location + "n2kproperties.txt");
    if (!file.exists())
    {
      try
      {
        file.createNewFile();
      }
      catch (Exception ex)
      {
        Trace.stack(ex,"Error creating props file");
      }
    }
    load();
  }

  void load()
  {
    try
    {
      FileInputStream in = new FileInputStream(file);
      load(in);
      in.close();
    }
    catch (Exception ex)
    {
      Trace.error("Exception loading n2kproperties.txt:" + ex.getMessage());
    }
  }

  void store()
  {
    try
    {
      FileOutputStream out = new FileOutputStream(file);
      store(out, "N2KLib Tester properties");
      out.close();
      Trace.alert("Saved config");
    }
    catch (Exception ex)
    {
      Trace.error("Exception saving n2k config file:" + ex.getMessage());
    }
  }

  Boolean enabled(String key)
  {
    Trace.normal("Property " + key + " is " + getProperty(key));  
    return("Y".equals(getProperty(key)));
  }

  Boolean enabled(String key, boolean def)
  {
    String keyval = getProperty(key);
    Trace.normal("Property " + key + " is " + keyval);
    if (keyval == null)
    {
      Trace.normal("For " + key + " using default of " + def);
      keyval = (def ? "Y" : "N");
    }
    return("Y".equals(keyval));
  }
}
