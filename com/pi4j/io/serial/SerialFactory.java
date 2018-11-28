package com.pi4j.io.serial;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class SerialFactory 
{
  static public Serial createInstance()
  {
    Serial serial = null;
    com.fazecast.jSerialComm.SerialPort[] ports = com.fazecast.jSerialComm.SerialPort.getCommPorts();
    if (ports.length > 0)
    {
      String[] options = new String[ports.length];
      int numPorts = 0;
      for (com.fazecast.jSerialComm.SerialPort port : ports)
      {
        options[numPorts++] = port.getSystemPortName();
      }
      String s;
      if (numPorts > 1)
      {
        s = (String)JOptionPane.showInputDialog(
                                               new JFrame(),
                                               "Choose serial port:",
                                               "Customized Dialog",
                                               JOptionPane.PLAIN_MESSAGE,
                                               null,
                                               (Object[])options,
                                               options[0]);
      }
      else
      {
        s = options[0];
      }

      //If a string was returned, say so.
      if ((s != null) && (s.length() > 0))
      {
        for (com.fazecast.jSerialComm.SerialPort port : ports)
        {
          if (s.equals(port.getSystemPortName()))
          {
            serial = new Serial(port);
            break;
          }
        } 
      }

      if ((serial == null) || (serial.comPort == null))
      {
        JOptionPane.showMessageDialog(new JFrame(), "No com port selected.");
      }
      else
      {
        serial.comPort.setBaudRate(115200);
        serial.comPort.setParity(com.fazecast.jSerialComm.SerialPort.NO_PARITY);
        serial.comPort.setNumStopBits(com.fazecast.jSerialComm.SerialPort.ONE_STOP_BIT);
        serial.comPort.setNumDataBits(8);  
      }  
    }
    else
    {
      JOptionPane.showMessageDialog(new JFrame(), "No com ports available.");
    }

    return (serial);
  }
}
