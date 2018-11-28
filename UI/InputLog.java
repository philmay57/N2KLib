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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import N2KLib.N2KPacket;
import Utils.Trace;
import Utils.Utils;
/*
 * This class provides the capability to read messages from a log file and inject them onto
 * an N2K network for testing/stressing a network.
 */
class InputLog
{
  long interval;
  long nextTime = 0;
  long lastLogTime = -1;
  String nextLine;
	protected long lastTime = 0;
	protected BufferedReader nmeaFile = null;

	protected enum Option
	{
	  REAL_TIME,
	    PERIODIC,
	    IMMEDIATE
	}
	protected Option option;

  protected InputLog(GUI ui, Option option, long interval)
  {
  	String fileName = "";
    try
    {
      JFileChooser chooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Log files", "log");
      chooser.setFileFilter(filter);
      chooser.setCurrentDirectory(new File(Utils.getHomePath()));
      int returnVal = chooser.showOpenDialog(ui);
      if (returnVal == JFileChooser.APPROVE_OPTION)
      {
        fileName =  chooser.getSelectedFile().getAbsolutePath();

        Trace.alert("Selected file : " + fileName);
      }

      if ((fileName != null) && !fileName.equals(""))
      {
        nmeaFile = new BufferedReader(new FileReader(fileName));
        nextLine = nmeaFile.readLine();
        
        Date date = null;
        date = getLogFileDate(nextLine);
        if (date != null)
        {
        	this.option = option;
          this.interval = interval;
        }
      }
    }
    catch (Exception ex)
    {
      JOptionPane.showMessageDialog(ui, "Log file " + fileName + " is invalid.\r\n" +
          "We check the date at the start of the first line matches the format of 2018-01-01T15:30:00.000");
    }
  }

  protected N2KPacket nextPacket()
  {
    N2KPacket packet = null;
    long curTime = System.currentTimeMillis();

    if ((nmeaFile != null) && (curTime > nextTime))
    {
      lastTime = curTime;
      try
      {
        while (packet == null)
        {
          if (nextLine == null) break;

          // We construct tha packet by recreating the hex bytes and then
          // reading as if an incoming N2K packet
          //Trace.debug("==>Log file input:" + nextLine);
          StringTokenizer st = new StringTokenizer(nextLine, ",");
          String logtime = st.nextToken(); 
          byte pri = (byte)(Integer.parseInt(st.nextToken()) & 0x000000ff);
          int pgn = Integer.parseInt(st.nextToken());
          //Trace.alert("Incoming PGN is " + pgn);
          byte src = (byte)(Integer.parseInt(st.nextToken()) & 0x000000ff);
          byte dst = (byte)(Integer.parseInt(st.nextToken()) & 0x000000ff);
          int len = (Integer.parseInt(st.nextToken()) & 0x000000ff);
          byte[] data = new byte[len];
          for (int i = 0; i < len; i++)
          {
            String hexchar = st.nextToken();
            int work1 = hexchar.charAt(0) - '0';
            if (work1 > 9) work1 -= 7;
            if (work1 > 15) work1 -= 32;
            int work2 = hexchar.charAt(1) - '0';
            if (work2 > 9) work2 -= 7;
            if (work2 > 15) work2 -= 32;
            data[i] = (byte)(work1 << 4 | work2);
          }

          if (dst != (byte)0xff)
          {
            Trace.alert("Overriding destination to broadcast on packet:" + nextLine);
            dst = (byte)0xff;
          }

          Trace.hex("Constructing from pgn " + pgn + " priority " + pri + " dest " + dst, data, 0,  data.length);
          packet = new N2KPacket(pgn, pri, dst, src, 0, data, data.length, 0);
          if (packet.unknown())
          {
            Trace.alert("PGN " + packet.pgn + " does not match any definition in the XML file");
            packet = null;
          }
          else if (!packet.isValid())
          {
            Trace.error("Injector could not reconstruct packet:" + packet.toString());
            Trace.hexerr("Raw data", data, 0, data.length);
            packet = null;
          }
          else
          {
            Trace.hexalt("Processing N2K data for " + pgn + " from log time " + logtime, data, 0, data.length);
            Trace.alert("Injector built packet " + packet.toString());
          }

          // Prime the next line whether we are breaking out or not
          getNextLine(curTime);
        }

        if (packet == null)
        {
          Trace.alert("End of injector file");
          nmeaFile.close();
          nmeaFile = null;
        }
      }
      catch (Exception ex)
      {
        Trace.stack(ex,  "Exception reading injector file");  
        try
        {
          nmeaFile.close();
        }
        catch (Exception e)
        {
        }
        nmeaFile = null;
      }
    }

    return(packet);
  }

  void getNextLine(long curTime)
  {
    // We are pasing a packet back so 
    // work out when to inject the next packet
    try
    {
      nextLine = nmeaFile.readLine();
      if (nextLine == null) return;
    }
    catch (Exception ex)
    {
      Trace.stack(ex, "Error reading from log file");
    }

    if (option == Option.REAL_TIME)
    {
      Date date = getLogFileDate(nextLine);
      if (date != null)
      {
        long msecs = date.getTime();
        if (lastLogTime == -1) lastLogTime = msecs;
        Trace.alert("Milliseconds from new date : " + msecs + ", old : " + lastLogTime);
        nextTime = curTime + (msecs - lastLogTime);
        Trace.alert("Injection delay (msecs) : " + (msecs-lastLogTime));
        lastLogTime = msecs;
      }
      else
      {
        nextTime = curTime + interval;
      }
    }
    else if (option == Option.IMMEDIATE)
    {
      nextTime = curTime;
    }
    else // PERIODIC
    {
      nextTime = curTime + interval;
    }
  }


  Date getLogFileDate(String nextLine)
  {
    Date date = null;
    if (nextLine.length() < 24)
    {
      return(null);
    }
    String str = nextLine.substring(0,10) + "T" + nextLine.substring(11,23);
    Trace.alert("Log file date is " + str);

    // date format should be  2016-04-09T16:41:09.079
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    try
    {
      date = fmt.parse(str);
    }
    catch (Exception ex)
    {
      Trace.stack(ex, "Log file date format error");
    }
    return(date);
  }
  
	protected void close()
	{
	  if (nmeaFile != null)
	  {
	    try
	    {
	      lastLogTime = -1;
	      nmeaFile.close();
	    }
	    catch (Exception ex)
	    {
	    }
	  }
	}

	protected void setOption(Option option)
	{
	  lastLogTime = -1;
	  this.option = option;
	}

  protected boolean active()
  {
    return(nmeaFile != null);
  }
}
