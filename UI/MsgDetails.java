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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import N2KLib.N2KField;
import N2KLib.N2KPacket;
import Utils.Trace;

public class MsgDetails extends JDialog 
{
  private static final long serialVersionUID = 1L;
  private MyActionListener actionListener = new MyActionListener();

  public MsgDetails(GUI parent, N2KPacket packet) 
  {
    super(parent, "NMEA 2000 PGN " + packet.pgn + " (" + 
          (packet.description != null ? packet.description : "UNKNOWN") + 
          ") message details", true);
    setPreferredSize(new Dimension(900,500));
    DefaultTableModel model = new DefaultTableModel();

    // Get the fields into a single vector array
    Vector<Vector<String>> rows = new Vector<Vector<String>>();
    if (packet.fields != null)
    {
      for (N2KField field : packet.fields)
      {
        addRow(rows, field);
      }
    }
    if (packet.repSet != null)
    {
      int i = 1;
      for (N2KField[] fields : packet.repSet)
      {
        Vector<String> row = new Vector<String>();
        row.addElement("Entry # " + i++ + " *******************************");
        row.addElement("******************************************");
        row.addElement("******************************************");
        row.addElement("******************************************");
        rows.addElement(row);
        for (N2KField field : fields)
        {
          addRow(rows, field);
        }
      }
    }

    Vector<String> colNames = new Vector<String>();
    colNames.addElement("Field Id");
    colNames.addElement("Field Name");
    colNames.addElement("Descripton");
    colNames.addElement("Value");
    model.setDataVector(rows, colNames);
    TipTable table = new TipTable(model);
    JScrollPane scrollPane = new JScrollPane(table);
    Container pane = getContentPane();
    pane.add(scrollPane, BorderLayout.PAGE_START);

    // Create OK and Cancel buttons
    JPanel donePane = new JPanel();
    donePane.setLayout(new FlowLayout());
    GUIUtils.addActionButton(donePane, GUIUtils.OK, actionListener);
    //donePane.setPreferredSize(new Dimension(100,30));
    pane.add(donePane, BorderLayout.PAGE_END);

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    pack();
    setVisible(true);
  }

  void addRow(Vector<Vector<String>> rows, N2KField field)
  {
    Vector<String> row = new Vector<String>();
    String id = field.toString();
    String val = "";
    int off = id.indexOf(':');
    if (off > 0)
    {
      val = id.substring(off + 1);
      id = id.substring(0, off);
    }
    String name = field.fieldDef.name;
    if (name == null)
    {
      name = "";
    }
    String desc = field.fieldDef.pgnField.Description;
    if (desc == null)
    {
      desc = "";
    }
    row.addElement(id);
    row.addElement(name);
    row.addElement(desc);
    row.addElement(val);
    rows.addElement(row);
  }

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
          setVisible(false);
          dispose();
          break;

      }
    }
  }

  public class TipTable extends JTable
  {
  	static final long serialVersionUID = 0;
    TipTable(DefaultTableModel model)
    {
      super(model);
    }

    //Implement table cell tool tips.           
    @Override
      public String getToolTipText(MouseEvent e) 
    {
      String tip = null;
      java.awt.Point p = e.getPoint();
      Trace.alert("Into getTooltiptext for col " + columnAtPoint(p));
      int rowIndex = rowAtPoint(p);
      int colIndex = columnAtPoint(p);
      try
      {
        tip = getValueAt(rowIndex, colIndex).toString();
      }
      catch (RuntimeException e1)
      {
        //catch null pointer exception if mouse is over an empty line
      }
      return(tip);
    }
  }
}
