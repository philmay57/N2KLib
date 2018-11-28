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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import N2KLib.N2KPacket;
import Utils.Utils;
/*
 * This class provides the capability to read messages from a log file and inject them onto
 * an N2K network for testing/stressing a network.
 */
class OutputLog
{
	protected BufferedWriter nmeaFile = null;
	protected String fileName = "";

  protected int option;
	protected static final int INCOMING = 0x01; 
	protected static final int OUTGOING = 0x02; 
	
	byte[] buffer = new byte[2000];
	GUI ui;

  protected OutputLog(GUI ui)
  {
  	this.ui = ui;
  }
  
  private void openLog()
  {
    try
    {
    	File file = null;
    	int i;
    	for (i = 0; i < 100; i++)
    	{
    		fileName = logName(i);
    		file = new File(fileName);
    		if (!file.exists()) break;
    	}
    	if (i == 100)
    	{
        JOptionPane.showMessageDialog(ui, "No free captureXX.log names.  Please clean up");
    	}
    	else
    	{	
        nmeaFile = new BufferedWriter(new FileWriter(file));
        JOptionPane.showMessageDialog(ui, "Log file " + fileName + " opened");
    	}
    }
    catch (Exception ex)
    {
      JOptionPane.showMessageDialog(ui, "Faile to open log file " + fileName + ".\r\n");
    }
  }
  
  protected String logName(int i)
  {
  	return(Utils.getHomePath() + "capture" + i + ".log");
  }

  protected void incomingPacket(N2KPacket packet)
  {
  	if ((option & INCOMING) != 0)
  	{
  		output(packet);
  	}
  }
  
  protected void outgoingPacket(N2KPacket packet)
  {
  	if ((option & OUTGOING) != 0)
  	{
  		output(packet);
  	}
  }
  
  protected void output(N2KPacket packet)
  {
  	if (nmeaFile == null)
  	{
  		openLog();
  	}

  	if (nmeaFile != null)
  	{
  		try
  		{
  			//Get the raw bytes
  			int len = packet.getRawData(buffer, 7);

  			//Output the header
  			nmeaFile.write(getLogfileDate());
  			String hdr = String.format(",%01d,%06d,%03d,%03d,%04d", 
  					packet.priority&0x00ff, packet.pgn, packet.src&0x00ff, packet.dest&0x00ff, len);
  			nmeaFile.write(hdr);
  			StringBuffer data = new StringBuffer();
  			for (int i = 0; i < len; i++)
  			{
  				data.append(String.format(",%02X", buffer[i+ 7]));
  			}
  			data.append("\r\n");
  			nmeaFile.write(data.toString());
  		}
  		catch (Exception ex)
  		{
        JOptionPane.showMessageDialog(ui, "Exception writing to log file " + fileName);
  			close();
  		}
  	}

  }

  String getLogfileDate()
  {
    Date date = new Date();
    // date format should be  2016-04-09T16:41:09.079
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    return(fmt.format(date));
  }
  
	protected void close()
	{
	  if (nmeaFile != null)
	  {
	    try
	    {
	      nmeaFile.close();
	    }
	    catch (Exception ex)
	    {
	    }
	    nmeaFile = null;
      JOptionPane.showMessageDialog(ui, "Log file " + fileName + " closed");
	  }
	}

	protected void setOption(int option)
	{
		if (this.option == 0)
		{
			openLog();
		}
	  this.option |= option;
	}

	protected void unsetOption(int option)
	{
		if (this.option != 0)
		{
	    this.option &= ~option;
	    if (this.option == 0)
	    {
	  	  close();
	    }
	  }
	}
	
}