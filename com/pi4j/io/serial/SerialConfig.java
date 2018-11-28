package com.pi4j.io.serial;

public class SerialConfig 
{
  public SerialConfig device(String port)
  {
    return(this);  
  }
  public SerialConfig baud(Baud baud)
  {
    return(this);  
  }
  public SerialConfig dataBits(DataBits bits)
  {
    return(this);  
  }
  public SerialConfig parity(Parity parity)
  {
    return(this);  
  }
  public SerialConfig stopBits(StopBits bits)
  {
    return(this);  
  }
  public SerialConfig flowControl(FlowControl option)
  {
    return(this);  
  }

}
