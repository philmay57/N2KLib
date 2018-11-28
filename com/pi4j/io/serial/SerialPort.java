package com.pi4j.io.serial;

public class SerialPort 
{
  static public int NO_PARITY = 1;
  static public int ONE_STOP_BIT = 1;
  public static SerialPort serial = new SerialPort(); 
  public static String getDefaultPort()
  {
    return("port");
  }

  public void open(SerialConfig cfg)
  {

  }
}
