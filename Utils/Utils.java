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
package Utils;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class Utils
{
  public static int unsignedShort(short val)
  {
    int res;
    if (val < 0)
    {
      res = 65536 + val;
    }
    else
    {
      res = val;
    }
    return(res);
  }

  public static double radstodegs(double rads)
  {
    return((rads * 180)/Math.PI);
  }

  public static double degstorads(double degs)
  {
    return((degs * Math.PI) / 180);
  }

  public static int unsignedByte(byte x)
  {
    return((int)x & 0xff);
  }

  public static int getShort(byte[] input, int off)
  {
    return(((int)unsignedByte(input[off + 1]) * 256) + (int)Utils.unsignedByte(input[off]));
  }

  public static int getInt(byte[] input, int off, int numbytes)
  {
    return((int)getLong(input, off, numbytes));
  }

  public static long getLong(byte[] input, int off, int numbytes)
  {
    long val = 0;
    for (int i = 0; i < numbytes; i++)
    {
      val += ((long)unsignedByte(input[off + i])) << (8*i);
    }
    return(val);
  }

  public static int getShortSigned(byte[] input, int off)
  {
    int val = ((int)unsignedByte(input[off + 1]) * 256) + (int)Utils.unsignedByte(input[off]);
    if (val > 0x7fff)
    {
      val = val - 65536;
    }
    return(val);
  }

  public static int getShortBE(byte[] input, int off)
  {
    return(((int)unsignedByte(input[off]) * 256) + (int)Utils.unsignedByte(input[off+1]));
  }

  public static int getInt(byte[] input, int off)
  {
    int res = (int)unsignedByte(input[off + 3]);
    res = res << 8 | unsignedByte(input[off + 2]);
    res = res << 8 | unsignedByte(input[off + 1]);
    res = res << 8 | unsignedByte(input[off + 0]);
    return(res);
  }

  public static int getIntBE(byte[] input, int off)
  {
    int res = (int)unsignedByte(input[off + 0]);
    res = res << 8 | unsignedByte(input[off + 1]);
    res = res << 8 | unsignedByte(input[off + 2]);
    res = res << 8 | unsignedByte(input[off + 3]);
    return(res);
  }

  public static long getLong(byte[] input, int off)
  {
    long res = (int)unsignedByte(input[off + 7]);
    res = res << 8 | unsignedByte(input[off + 6]);
    res = res << 8 | unsignedByte(input[off + 5]);
    res = res << 8 | unsignedByte(input[off + 4]);
    res = res << 8 | unsignedByte(input[off + 3]);
    res = res << 8 | unsignedByte(input[off + 2]);
    res = res << 8 | unsignedByte(input[off + 1]);
    res = res << 8 | unsignedByte(input[off + 0]);
    return(res);
  }
  
  public static void setInt(byte[] input, int off, int val)
  {
    input[off]     = (byte)(val & 0xff);
    input[off + 1] = (byte)((val >>  8) & 0xff);
    input[off + 2] = (byte)((val >> 16) & 0xff);
    input[off + 3] = (byte)((val >> 24) & 0xff);
  }

  public static void setInt(byte[] input, int off, long val, int numbytes)
  {
    if (numbytes > 8)
    {
      Trace.error("Too many bytes for integer : " + numbytes);
    }
    else
    {
      for (int i = 0; i < numbytes; i++)
      {
        input[off + i] = (byte)((val >>  (i*8)) & 0xff);
      }
    }
  }

  public static void setIntBE(byte[] input, int off, int val)
  {
    input[off]     = (byte)((val >> 24) & 0xff);
    input[off + 1] = (byte)((val >> 16) & 0xff);
    input[off + 2] = (byte)((val >>  8) & 0xff);
    input[off + 3] = (byte)((val      ) & 0xff);
  }

  public static void setLong(byte[] input, int off, long val)
  {
    input[off]     = (byte)(val & 0xff);
    input[off + 1] = (byte)((val >>  8) & 0xff);
    input[off + 2] = (byte)((val >> 16) & 0xff);
    input[off + 3] = (byte)((val >> 24) & 0xff);
    input[off + 4] = (byte)((val >> 32) & 0xff);
    input[off + 5] = (byte)((val >> 40) & 0xff);
    input[off + 6] = (byte)((val >> 48) & 0xff);
    input[off + 7] = (byte)((val >> 54) & 0xff);
  }

  public static void setShort(byte[] bytes, int off, int val)
  {
    bytes[off]   = (byte)(val & 0xff);
    bytes[off+1] = (byte)(val >> 8);
  }

  public static void setShortBE(byte[] bytes, int off, int val)
  {
    bytes[off+1] = (byte)(val & 0xff);
    bytes[off]   = (byte)(val >> 8);
  }

  static double PiBy2 = Math.PI/2;
  static double RadsToDegrees = 180.0 / Math.PI; 
  static double LatFudge = 1.0067642927D;
  static double EquatorAxis = 6378388.0D;

  public static double metresToLat(double mtrs)
  {
    double lat = mtrs;
    lat = (Math.atan(Math.exp(lat / EquatorAxis)) * 2) - PiBy2;
    lat = Math.atan(Math.tan(lat) * LatFudge) * RadsToDegrees;
    return(lat);
  }

  public static double metresToLong(double mtrs, double lat)
  {
    double lng = (mtrs * 180.0 / Math.PI / EquatorAxis / Math.cos(lat));
    return(lng);
  }

  public static int random(int bound)
  {
    int val = (int)(bound * Math.random());
    return(val);
  }

  public static void mkdir(String dir)
  {
    if (dir.endsWith(File.separator))
    {
      dir = dir.substring(0, dir.length() - 1);
    }
    File directory = new File(dir);
    if (!directory.exists())
    {
      directory.mkdirs();
    }
  }

  public static boolean fileExists(String fileName)
  {
    boolean exists = false; 
    try
    {

      File f = new File(fileName);
      exists = f.exists();
      if (!exists)
      {
        Trace.alert("File " + fileName + " does not exist");
      }
    }
    catch (Exception ex)
    {
      Trace.error("Exception checking for file " + fileName);
    }
    return(exists);
  }

  public static byte[] readWholeFile(String fileName)
  {
    byte[] data = null;
    boolean badfile = false;
    RandomAccessFile file = null;
    try
    {
      File f = new File(fileName);
      if (!f.exists())
      {
        Trace.error("File not found: " + fileName);
        badfile = true;
      }

      if (!badfile)
      {
        file = new RandomAccessFile(f, "r");
        int fileSize = (int)(file.length());
        data = new byte[fileSize];
        file.read(data);
        //Trace.debug("Read entire file");
      }
    }
    catch (Exception ex)
    {
      Trace.stack(ex, "Exception reading file " + fileName);  
    }
    finally
    {
      try
      {
        file.close();
      }
      catch (Exception e)
      {
      }
    }
    return(data);
  }

  public static byte[] readWholeFile(InputStream s)
  {
    byte[] buf = new byte[1024];
    int len;
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    try
    {
      while ((len = s.read(buf)) != -1)
      {
          os.write(buf, 0, len);
      }
    }
    catch (Exception ex)
    {
        Trace.stack(ex, "Exception reading from input stream");
    }
    return(os.toByteArray());
  }

  public static boolean writeWholeFile(String fileName, byte[] fileData, int off, int len)
  {
    RandomAccessFile file = null;
    boolean badfile = false;
    try
    {
      File f = new File(fileName);
      if (f.exists())
      {
        Trace.normal("File already exists");
        f.delete();
      }
      if (!f.createNewFile())
      {
        Trace.error("Count not create new file");
        badfile = true;
      }

      if (!badfile)
      {
        file = new RandomAccessFile(f, "rw");
        file.write(fileData, off, len);
        Trace.normal("Wrote to file " + fileName);
      }
    }
    catch (Exception e)
    {
      Trace.stack(e, "Failed to write file " + fileName);
    }
    finally
    {
      try
      {
        file.close();
      }
      catch (Exception e)
      {
      }
    }
    return(badfile);      
  }

  public static void writeTextFile(String fileName, String content)
  {
    FileWriter dataFile = null;
    try
    {
      File f = new File(fileName);
      if (f.exists())
      {
        Trace.normal("File already exists:" + fileName);
        f.delete();
      }
      if (!f.createNewFile())
      {
        Trace.error("Count not create new file " + fileName);
      }
      else
      {
        dataFile = new FileWriter(fileName);
        dataFile.write(content);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        dataFile.close();
      }
      catch (Exception e)
      {
      }
    }   
  }

  public static void sleep(long time)
  {
    try
    {
      Thread.sleep(time);
    }
    catch (InterruptedException ex)
    {
    }
  }

  public static long getNotAvailable(int len, boolean signed)
  {
    long na;
    if (len > 2)
    {
      if (len == 64)
      {
        if (signed)
        {
          na = 0x7fffffffffffffffL;
        }
        else
        {
          na = 0xffffffffffffffffL;       
        }
      }
      else
      {
        na = 0x7fffffffffffffffL;
        if (signed)
        {
          na = na >> (64 - len);
        }
        else
        {
          na = na >> (63 - len);
        }       
      }
    }
    else
    {
      na = -1;
    }
    return(na);
  }

  public static String padRight(String s, int n) 
  {
    return (String.format("%1$-" + n + "s", s));  
  }

  public static String getHomePath()
  {
    String home =       System.getProperty("user.home");
    home = home + File.separator + "N2KLib" + File.separator;
    
        // Check our directory exists and create if not
        mkdir(home);

    return(home);
  }
  
  public static InputStream getResourceStream(String name)
  {
    if (!name.startsWith("/")) name = "/" + name;
    
    InputStream s = Utils.class.getResourceAsStream(name);
    return(s);
  }    
  
  public static File copyResourceToHome(String name)
  {
    String homePath = getHomePath();
    String fileName = homePath + name;
    File file = new File(fileName);
    if (!file.exists() || (file.length() == 0))
    {
      // Extract the file from resource location and store in our home directory
      InputStream s = Utils.class.getResourceAsStream("/" + name);
      byte[] fileData = Utils.readWholeFile(s);
      Utils.writeWholeFile(fileName, fileData, 0, fileData.length);
    }
    return(file);
  }
}