package com.pi4j.io.serial;

/*
public class Baud {
static public int _115200 = 115200;
static public int _57600 = 57600;
static public int _38400 = 38400;
}*/

public enum Baud {

  _38400(38400),
    _57600(57600),
    _115200(115200),
    _230400(230400);

  private int baud = 0;

  private Baud(int baud)
  {
    this.baud = baud;
  }

  public int getValue()
  {
    return (this.baud);
  }

  public static Baud getInstance(int baud_rate)
  {
    for (Baud b : Baud.values())
    {
      if (b.getValue() == baud_rate)
      {
        return (b);
      }
    }
    return (null);
  }
}
