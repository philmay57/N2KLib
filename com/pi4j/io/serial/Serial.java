package com.pi4j.io.serial;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Serial 
{
  public static String DEFAULT_COM_PORT = "wibble";

  protected com.fazecast.jSerialComm.SerialPort comPort;

  public Serial(com.fazecast.jSerialComm.SerialPort port)
  {
    comPort = port;
  }

  public int available() throws IOException
  {
    return(comPort.bytesAvailable());
  }

  public void open(SerialConfig cfg) throws IOException
  {
    if (!comPort.openPort())
    {
      JOptionPane.showMessageDialog(new JFrame(), 
      		"Failed to open " + comPort.getSystemPortName() + "\r\n" +
          "It may already have been opened by another application.\r\n");
    }
  }

  public void discardInput() throws IOException
  {
    int avail = comPort.bytesAvailable();
    byte[] buffer = new byte[avail];
    comPort.readBytes(buffer, avail);
  }

  public InputStream getInputStream()
  {
    return(comPort.getInputStream());
  }

  public OutputStream getOutputStream()
  {
    return(comPort.getOutputStream());
  }

  public byte[] read(int len) throws IOException
  {
    byte[] buf = new byte[len];
    comPort.readBytes(buf, len);
    return(buf);
  }

  public void write(byte b) throws IOException
  {
    byte[] buf = {0};
    buf[0] = b;
    comPort.writeBytes(buf, 1);
  }

  public int write(byte[] buf) throws IOException
  {
    int rc = 0;
    while (comPort.bytesAwaitingWrite() > 0)
    {
      try
      {
        Thread.sleep(10);
      }
      catch (Exception ex)
      {
      }
    }
    rc = comPort.writeBytes(buf, buf.length);
    return(rc);
  }

  public int write(byte[] buf, int off, int len) throws IOException
  {
    int rc = 0;
    while (comPort.bytesAwaitingWrite() > 0)
    {
      try
      {
        Thread.sleep(10);
      }
      catch (Exception ex)
      {
      }
    }
    rc = comPort.writeBytes(buf, len, off);
    return(rc);
  }

  public void flush()
  {
    try
    {
      comPort.getOutputStream().flush();
    }
    catch (Exception ex)
    {
    }
  }

  public boolean isOpen()
  {
    return(comPort.isOpen());
  }

  public void close()
  {
    comPort.closePort();
  }
}
