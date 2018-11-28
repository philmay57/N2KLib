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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Utils.Trace;

public class MsgConfig extends JDialog
{
  private final String chooseMessage = 
  		"Configure the position/speed etc for your yacht\r\n\r\n" + 
      "These will have some randomness added to make the simulation more realistic.\r\n" +
      "The defaults place a yacht south of Cornwall heading to give a COG due west\r\n";
  private Props props = null;
  private static final long serialVersionUID = 1L;
  private static final String Default = "Restore defaults";
  GUI gui;
  MyActionListener actionListener = new MyActionListener(); 


  JTextField lat = new JTextField();
  JTextField lng = new JTextField();
  JTextField spd = new JTextField();
  JTextField dep = new JTextField();
  JTextField hdg = new JTextField();
  JTextField tid = new JTextField();
  JTextField tda = new JTextField();
  JTextField ws  = new JTextField();
  JTextField wa  = new JTextField();

  public MsgConfig(GUI parent, Props props, SimulatedMessages sim)
  {
    super(parent, "NMEALib Demo - Yacht Parameters");

    gui = parent;
    this.props = props;
    setSize(600,500);

    Container pane = getContentPane();

    // Create a message
    JPanel messagePane = new JPanel();
    JTextArea text = new JTextArea(chooseMessage);
    text.setEditable(false);
    text.setBackground(messagePane.getBackground());
    text.setPreferredSize(new Dimension(550, 80));
    messagePane.add(text);
    text.setLineWrap(true);
    pane.add(messagePane, BorderLayout.PAGE_START);

    // Create config area
    JPanel configPane   = new JPanel();
    GridBagLayout gb = new GridBagLayout();
    configPane.setLayout(gb);
    pane.add(configPane, BorderLayout.CENTER);
    pane.add(new JLabel("      "), BorderLayout.LINE_START);
    pane.add(new JLabel("      "), BorderLayout.LINE_END);

    GUIUtils.addDecEntryLine(configPane, gb, 0, "Latitude (degrees, -90(S) to 90(N))", lat, -90.0, 90.0);
    lat.setText(props.getProperty(Props.sLat, "49.5"));
    GUIUtils.addDecEntryLine(configPane, gb, 1, "Longitude (degrees, -180(W) to 180(E))", lng, -180.0, 180.0);
    lng.setText(props.getProperty(Props.sLng, "-4.5"));
    GUIUtils.addDecEntryLine(configPane, gb, 2, "Speed (knots, 0 to 1000)", spd, 0.0, 1000.0);
    spd.setText(props.getProperty(Props.sSpd, "6.0"));
    GUIUtils.addDecEntryLine(configPane, gb, 3, "Depth (metres, 0 to 1000)", dep, 0.0, 1000.0);
    dep.setText(props.getProperty(Props.sDep, "100.0"));
    GUIUtils.addDecEntryLine(configPane, gb, 4, "Heading (degrees, 0 to 360)", hdg, 0.0, 360.0);
    hdg.setText(props.getProperty(Props.sHdg, "270.0"));
    GUIUtils.addDecEntryLine(configPane, gb, 5, "Tide Speed (knots, 0 to 20)", tid, 0.0, 20.0);
    tid.setText(props.getProperty(Props.sTid, "1.0"));
    GUIUtils.addDecEntryLine(configPane, gb, 6, "Tide Angle (degrees, 0 to 360)", tda, 0.0, 360.0);
    tda.setText(props.getProperty(Props.sTda, "0.0"));
    GUIUtils.addDecEntryLine(configPane, gb, 7, "Wind Speed (knots, 0 to 100)", ws, 0, 100.0);
    ws.setText(props.getProperty(Props.sWs, "15.0"));
    GUIUtils.addDecEntryLine(configPane, gb, 8, "Apparent Wind Angle (degrees, 0 to 360)", wa, 0.0, 360.0);
    wa.setText(props.getProperty(Props.sWa, "100"));
    JLabel lab = new JLabel("    ");
    GridBagConstraints c = new GridBagConstraints();
    c.fill  = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 9;
    c.weightx = 1;
    gb.setConstraints(lab, c);
    configPane.add(lab);


    // Create OK and Cancel buttons
    JPanel donePane = new JPanel();
    donePane.setLayout(new FlowLayout());
    donePane.setPreferredSize(new Dimension(400,50));
    GUIUtils.addActionButton(donePane, GUIUtils.OK, actionListener);
    GUIUtils.addActionButton(donePane, Default, actionListener);
    GUIUtils.addActionButton(donePane, GUIUtils.Cancel, actionListener);
    pane.add(donePane, BorderLayout.PAGE_END);

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    pack();
    setVisible(true);
  }

  /*
  JCheckBox addCheckBoxLine(JPanel pane, String checkText, Boolean checked)
  {
    JCheckBox box = null;  
    // Add check box
    if (checkText != null)
    {
      box = GUIUtils.addCheckBox(pane, checkText, checked, actionListener); 
    }
    else
    {
      pane.add(new JLabel("  "));
    }
    return(box);
  }
*/

  // an action listener to be used when an action is performed
  // (e.g. button is pressed)
  class MyActionListener implements ActionListener 
  {
    //close and dispose of the window.
    public void actionPerformed(ActionEvent e) 
    {
      String cmd = e.getActionCommand();

      switch (cmd)
      {
        case GUIUtils.OK:
          Trace.alert("OK button");
          // Save to ini file
          saveProps();
          gui.simulatedMsgs.loadYachtParms(props);
          setVisible(false);
          dispose();
          break;

        case GUIUtils.Cancel:
          Trace.alert("Cancel button");
          props.load();
          setVisible(false);
          dispose();
          break;

        case Default:
          Trace.alert("Default button");
          lat.setText("49.5");
          lng.setText("-4.5");
          spd.setText("6.0");
          dep.setText("100.0");
          tid.setText("1.0");
          tda.setText("0.0");
          hdg.setText("270.0");
          ws.setText("15.0");
          wa.setText("100");
          break;

        default:
          Trace.error("Unsupported command " + cmd);
          break;
      }
    }

    void saveProps()
    {
      props.setProperty(Props.sLat, lat.getText());
      props.setProperty(Props.sLng, lng.getText());
      props.setProperty(Props.sSpd, spd.getText());
      props.setProperty(Props.sDep, dep.getText());
      props.setProperty(Props.sTid, tid.getText());
      props.setProperty(Props.sTda, tda.getText());
      props.setProperty(Props.sHdg, hdg.getText());
      props.setProperty(Props.sWs,   ws.getText());
      props.setProperty(Props.sWa,   wa.getText());
      props.store();
    }

    /*
    private boolean setSelected(String key, ActionEvent e)
    {
      AbstractButton abstractButton = (AbstractButton) e.getSource();
      boolean selected = abstractButton.getModel().isSelected();  
      Trace.alert("Set " + key + ":" + selected);
      props.setProperty(key, selected ? "Y" : "N");
      return(selected);
    }
    */
  }

}
