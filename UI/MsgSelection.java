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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import N2KMsgs.N2K;
import Utils.Trace;

public class MsgSelection extends JDialog
{
  private final String chooseMessage = 
    "Choose from the following list of NMEA 2000 packets for simulation.\r\n\r\n" + 
    "To add more to the list edit SimulatedMessages.java\r\n\r\n" +  
    "Heading, Speed, COG/SOG and Position are required for the autohelm function to work\r\n" +
    "Deselecting these messages will break the tracking";

  private Props props = null;
  private SimulatedMessages simulatedMsgs = null;
  private static final long serialVersionUID = 1L;
  private static final String Default = "Restore defaults";
  static final int[] defaultPGNs = 
  {
    N2K.systemTime_pgn,
    N2K.gnssPositionData_pgn,
    N2K.cogSogRapidUpdate_pgn,
    N2K.speed_pgn,
    N2K.windData_pgn,
    N2K.waterDepth_pgn,
    N2K.vesselHeading_pgn,
  };


  GUI gui;
  MyActionListener actionListener = new MyActionListener(); 
  JPanel configPane;

  public MsgSelection(GUI parent, Props props, SimulatedMessages sim)
  {
    super(parent, "Choose NMEA200 Messages");

    gui = parent;
    this.props = props;
    this.simulatedMsgs = sim;
    setSize(450,450);

    Container pane = getContentPane();

    // Create a message
    JPanel messagePane = new JPanel();
    JTextArea text = new JTextArea(chooseMessage);
    text.setEditable(false);
    text.setBackground(messagePane.getBackground());
    text.setPreferredSize(new Dimension(500, 120));
    messagePane.add(text);
    text.setLineWrap(true);
    pane.add(messagePane, BorderLayout.PAGE_START);

    // Create config area
    configPane   = new JPanel();
    int msgcount = sim.msgs.size();
    configPane.setLayout(new GridLayout(msgcount,2));
    pane.add(configPane, BorderLayout.CENTER);
    pane.add(new JLabel("      "), BorderLayout.LINE_START);
    pane.add(new JLabel("      "), BorderLayout.LINE_END);

    for (int i = 0; i < msgcount; i++)
    {
      SimulatedMessages.SimulatedMessage msg = sim.msgs.elementAt(i);
      boolean defaultEnabled = false;
      for (int def : defaultPGNs)
      {
        if (def == msg.pgn)
        {
          Trace.normal("This is a default PGN:" + def);
          defaultEnabled = true;
          break;
        }
      }
      addCheckBoxLine(configPane, 
                      "PGN " + msg.pgn + ":" + msg.description,  
                      props.enabled(Props.pgn + msg.pgn, defaultEnabled));
    }

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

  // an action listener to be used when an action is performed
  // (e.g. button is pressed)
  class MyActionListener implements ActionListener 
  {
    //close and dispose of the window.
    public void actionPerformed(ActionEvent e) 
    {
      String cmd = e.getActionCommand();
      if (cmd.startsWith("PGN "))
      {
        cmd = cmd.substring(4, cmd.indexOf(':'));
        setSelected(Props.pgn+cmd, e);
      }
      else
      {
        switch (cmd)
        {
          case GUIUtils.OK:
            Trace.alert("OK button");
            // Save to ini file
            props.store();
            simulatedMsgs.reconfigure(props);
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
            for (int present : SimulatedMessages.supportedPGNs)
            {
              setSelected(Props.pgn+present, false);
            }
            for (int active : defaultPGNs)
            {
              setSelected(Props.pgn+active, true);
            }
            // Redo config pane
            configPane.removeAll();
            for (int i = 0; i < simulatedMsgs.msgs.size(); i++)
            {
              SimulatedMessages.SimulatedMessage msg = simulatedMsgs.msgs.elementAt(i);
              addCheckBoxLine(configPane, 
                              "PGN " + msg.pgn + ":" + msg.description,  
                              props.enabled(Props.pgn + msg.pgn));
            }
            pack();
            break;

          default:
            Trace.error("Unsupported command " + cmd);
            break;
        }
      }
    }

    private boolean setSelected(String key, ActionEvent e)
    {
      AbstractButton abstractButton = (AbstractButton) e.getSource();
      boolean selected = abstractButton.getModel().isSelected();  
      return(setSelected(key, selected));
    }

    private boolean setSelected(String key, boolean selected)
    {
      Trace.alert("Set " + key + ":" + selected);
      props.setProperty(key, selected ? "Y" : "N");
      return(selected);
    }
  }

}
