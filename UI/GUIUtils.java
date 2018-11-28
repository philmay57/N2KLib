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

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.swing.BoxLayout;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Utils.Trace;

public class GUIUtils
{
  static final String OK = "OK";
  static final String Cancel = "Cancel";

  static JLabel addIntEntryLine(JPanel pane, GridBagLayout gb, int index, String label, JTextField field, int min, int max)
  {
    return(addEntryLine(pane, gb, index, label, field, new CheckInteger(min, max)));
  }

  static JLabel addDecEntryLine(JPanel pane, GridBagLayout gb, int index, String label, JTextField field, double min, double max)
  {
    return(addEntryLine(pane, gb, index, label, field, new CheckDecimal(min, max)));
  }

  static private JLabel addEntryLine(JPanel pane, GridBagLayout gb, int index, String label, JTextField field, InputVerifier iv)
  {
    // Add label
    JLabel lab = new JLabel(label);
    GridBagConstraints c = new GridBagConstraints();
    c.fill  = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = index;
    c.weightx = 0.5;
    gb.setConstraints(lab, c);
    pane.add(lab);
    c.gridx = 1;
    c.weightx = 0.5;
    gb.setConstraints(field, c);
    pane.add(field);
    field.setInputVerifier(iv);
    return(lab);
  }

  static JCheckBox addCheckBox(JPanel pane, String text, Boolean checked, ActionListener listener) 
  {
    JCheckBox checkBox = new JCheckBox(text);
    checkBox.setSelected(checked);
    checkBox.addActionListener(listener);
    pane.add(checkBox);
    return(checkBox);
  }

  static void addActionButton(JPanel pane, String text, ActionListener listener) 
  {
    // Create the button
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    JButton button = new JButton(text);

    // set action listener on the button
    button.addActionListener(listener);
    panel.add(button);
    pane.add(panel);
  }

  public static void messageBox(String title, String message)
  {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
  }

  public static BigDecimal getBigDecimal(Props props, String text)
  {
  	return(getBigDecimal(props, text, true));
  }

  public static BigDecimal getBigDecimal(Props props, String text, boolean traceError)
  {
    BigDecimal  bd = null;
    try
    {
      if (props != null)
      {
        bd = new BigDecimal(props.getProperty(text));
      }
      else
      {
        bd = new BigDecimal(text);
      }
    }
    catch (Exception ex)
    {
    	if (traceError)
    	{
        Trace.error(text + " is not a valid decimal value");
    	}
    }
    return(bd);
  }

  public static BigInteger getBigInteger(Props props, String text)
  {
    BigInteger  bi = null;
    try
    {
      if (props != null)
      {
        bi = new BigInteger(props.getProperty(text));
      }
      else
      {
        bi = new BigInteger(text);
      }
    }
    catch (Exception ex)
    {
      Trace.error(text + " is not a valid integer value");
    }
    return(bi);
  }

  public static double getBigDecimalValue(Props props, String str, double dflt)
  {
    BigDecimal x = getBigDecimal(props, str, false);
    double xval;
    if (x == null)
    {
      xval = dflt;
    }
    else
    {
      xval = x.doubleValue();
    }
    return(xval);
  }

  public static int getBigIntValue(Props props, String str, int dflt)
  {
    BigInteger x = getBigInteger(props, str);
    int xval;
    if (x == null)
    {
      xval = dflt;
    }
    else
    {
      xval = x.intValue();
    }
    return(xval);
  }

  static void sleep(long millis)
  {
    try
    {
      Thread.sleep(millis);
    }
    catch (Exception ex)
    {
      Trace.alert("Unexpected exception while sleeping:" + ex.getMessage());
    }
  }

  static double toRadians(int degs)
  {
    return((((double)degs)/180)*3.14159); 
  }

  static public double normalizeAngle360(double res)
  {
    res = res % (Math.PI * 2);
    if (res < 0) res += (Math.PI * 2);
    return(res);
  }

  static public double normalizeAngle180(double res)
  {
    res = res % (Math.PI * 2);
    if (res > Math.PI) res -= (Math.PI * 2);
    if (res < -Math.PI) res += (Math.PI * 2);
    return(res);
  }
}

class CheckDecimal extends InputVerifier 
{
  Boolean rc;
  BigDecimal bd;
  double min;
  double max;

  public CheckDecimal(double min, double max)
  {
    this.min = min;
    this.max = max;
  }

  public boolean verify(JComponent input) 
  {
    rc = true;
    JTextField tf = (JTextField) input;
    bd = GUIUtils.getBigDecimal(null, tf.getText(), false);
    if (bd == null)
    {
      rc = false;  
      GUIUtils.messageBox("Invalid input", tf.getText() + " is not a valid decimal number");
    }
    else
    {
      double val = bd.doubleValue();
      if ((val < min) || (val > max))
      {
        rc = false;
        GUIUtils.messageBox("Invalid input", tf.getText() + " is outside range (" + min + " to " + max);
      }
    }
    return(rc);
  }  
}    

class CheckInteger extends InputVerifier 
{
  Boolean rc;
  BigInteger bi;
  int min;
  int max;

  public CheckInteger(int min, int max) 
  {
    this.min = min;
    this.max = max;
  }
  public boolean verify(JComponent input) 
  {
    rc = true;  
    JTextField tf = (JTextField) input;
    bi = GUIUtils.getBigInteger(null, tf.getText());
    if (bi == null)
    {
      rc = false;
      GUIUtils.messageBox("Invalid input", tf.getText() + " is not a valid integer number");
    }
    else
    {
      int val = bi.intValue();
      if ((val < min) || (val > max))
      {
        rc = false;
        GUIUtils.messageBox("Invalid input", tf.getText() + " is outside range (" + min + " to " + max);
      }
    }
    return(rc);
  }  
}

