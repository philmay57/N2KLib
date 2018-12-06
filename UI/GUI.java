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

// Sample GUI for N2KLib.  Provides a main window for message tracing, plus various functions to 
// control packet simulation.  It is all standard UI code, that uses Jlist and JScrollPane classes
// to handle the dynamic updating.  The JList is built on a DefaultListModel, which allows lines
// to be added as the number of pgns we see increases.
//
// The GUI also has a thread which is responsible for drawing the lists on the main window. 
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileNameExtensionFilter;

import N2KLib.N2KLib;
import N2KLib.N2KPacket;
import N2KMsgs.N2K;
import Transport.ActisenseTransport;
import Utils.Trace;
import Utils.Utils;

@SuppressWarnings({"unused"})
public class GUI extends JFrame implements Runnable
{
  static final long serialVersionUID = 1;

  private static final String OPENLOG    = "openlog";
  private static final String CLOSELOG   = "closelog";
  private static final String CONNECT    = "connect";
  private static final String SIMULATE   = "simulate";  
  private static final String IMMED      = "immediate";
  private static final String PERIOD     = "periodic";
  private static final String REALTIME   = "realtime";
  private static final String CONFIGURE  = "configure";
  private static final String SELECT     = "select";
  private static final String ALERT      = "alert";
  private static final String NORMAL     = "normal";
  private static final String MESSAGES   = "messages";
  private static final String ERROR      = "error";
  private static final String AUTO       = "auto";
  private static final String STANDBY    = "standby";
  private static final String TRACK      = "track";
  private static final String UP10       = "up10";
  private static final String DOWN10     = "down10";
  private static final String INCOMING   = "incoming";
  private static final String OUTGOING   = "outgoing";
  private static final String CLEAR      = "clear";
  private static final String EXIT       = "exit";

  public N2KLib n2klib;
  Thread rcvThread;
  boolean running = false;
  InputLog  logReader = null;
  OutputLog logWriter = new OutputLog(this);
  volatile boolean simulate = false;

  public enum Autohelm
  {
  	AUTO,TRACK,STANDBY
  }
  volatile Autohelm autohelm = Autohelm.STANDBY;
  int sendCount = 0;
  volatile boolean threadEnded;
  InputLog.Option option = InputLog.Option.REAL_TIME;
  long curTime = 0;
  long nextSend = 0; 
  JMenuItem         connectMenuItem = null;
  JCheckBoxMenuItem cbSimulateMenuItem = null;

  TreeMap<Integer,N2KPacket> lastSent = new TreeMap<Integer, N2KPacket>(); 
  TreeMap<Integer,N2KPacket> lastRcvd = new TreeMap<Integer, N2KPacket>(); 
  int numRcvLines  = 15;
  int numSendLines = 20;
  DefaultListModel<ListPacket> rcvLines   = new DefaultListModel<ListPacket>(); 
  DefaultListModel<ListPacket> sendLines  = new DefaultListModel<ListPacket>(); 
  JList<ListPacket> sendList = null;
  JList<ListPacket> rcvList = null;

  SimulatedMessages simulatedMsgs = null;

  Props props = null;

  GUI()
  {
    super("N2KLib Demo UI");

    props = new Props();
    ActisenseTransport xport = new ActisenseTransport();
    n2klib = new N2KLib(xport);
    simulatedMsgs = new SimulatedMessages(props);

    //Create the menu bar.
    JMenuBar menuBar = new JMenuBar();

    //Build the first menu.
    JMenuItem menuItem = null;
    JMenu menu = new JMenu("File");
    menu.setMnemonic(KeyEvent.VK_F);
    menu.getAccessibleContext().setAccessibleDescription("N2KLib Demo Connection settings");
    connectMenuItem = new JMenuItem("Connect");
    connectMenuItem.setMnemonic(KeyEvent.VK_C);
    connectMenuItem.setActionCommand(CONNECT);
    connectMenuItem.addActionListener(new MyActionListener(this));
    menu.add(connectMenuItem);
    
    menuItem = new JMenuItem("Clear display");
    menuItem.setMnemonic(KeyEvent.VK_E);
    menuItem.setActionCommand(CLEAR);
    menuItem.addActionListener(new MyActionListener(this));
    menu.add(menuItem);
    menu.add(new JSeparator());

    menuItem = new JMenuItem("Open input log file");
    menuItem.setMnemonic(KeyEvent.VK_O);
    menuItem.setActionCommand(OPENLOG);
    menuItem.addActionListener(new MyActionListener(this));
    menu.add(menuItem);

    menuItem = new JMenuItem("Close input log file");
    menuItem.setMnemonic(KeyEvent.VK_L);
    menuItem.setActionCommand(CLOSELOG);
    menuItem.addActionListener(new MyActionListener(this));
    menu.add(menuItem);

    //a group of radio button menu items for the logfile mode
    ButtonGroup group = new ButtonGroup();
    JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem("Input using original timings");
    rbMenuItem.setMnemonic(KeyEvent.VK_T);
    rbMenuItem.setActionCommand(REALTIME);
    rbMenuItem.addActionListener(new MyActionListener(this));
    group.add(rbMenuItem);
    group.setSelected(rbMenuItem.getModel(),  true);
    menu.add(rbMenuItem);

    rbMenuItem = new JRadioButtonMenuItem("Input immediately");
    rbMenuItem.setMnemonic(KeyEvent.VK_I);
    rbMenuItem.setActionCommand(IMMED);
    rbMenuItem.addActionListener(new MyActionListener(this));
    group.add(rbMenuItem);
    menu.add(rbMenuItem);

    rbMenuItem = new JRadioButtonMenuItem("Input five packets per second");
    rbMenuItem.setMnemonic(KeyEvent.VK_P);
    rbMenuItem.setActionCommand(PERIOD);
    rbMenuItem.addActionListener(new MyActionListener(this));
    group.add(rbMenuItem);
    menu.add(rbMenuItem);
    menu.add(new JSeparator());
    
    JCheckBoxMenuItem cbMenuItem = new JCheckBoxMenuItem("Log received packets");
    cbMenuItem.setMnemonic(KeyEvent.VK_R);
    cbMenuItem.setActionCommand(INCOMING);
    cbMenuItem.addActionListener(new MyActionListener(this));
    menu.add(cbMenuItem);

    cbMenuItem = new JCheckBoxMenuItem("Log sent packets");
    cbMenuItem.setMnemonic(KeyEvent.VK_S);
    cbMenuItem.setActionCommand(OUTGOING);
    cbMenuItem.addActionListener(new MyActionListener(this));
    menu.add(cbMenuItem);
    menu.add(new JSeparator());
    
    menuItem = new JMenuItem("Exit");
    menuItem.setMnemonic(KeyEvent.VK_X);
    menuItem.setActionCommand(EXIT);
    menuItem.addActionListener(new MyActionListener(this));
    menu.add(menuItem);

    menuBar.add(menu);

    //Build the simulation
    JMenu selectMenu = new JMenu("Simulation");
    selectMenu.setMnemonic(KeyEvent.VK_S);
    selectMenu.getAccessibleContext().setAccessibleDescription("N2KLib Demo - Message Simulation");
    menuItem = new JMenuItem("Configure simulation parameters...");
    menuItem.setMnemonic(KeyEvent.VK_C);
    menuItem.setActionCommand(CONFIGURE);
    menuItem.addActionListener(new MyActionListener(this));
    selectMenu.add(menuItem);
    menuItem = new JMenuItem("Select messages to be sent...");
    menuItem.setMnemonic(KeyEvent.VK_S);
    menuItem.setActionCommand(SELECT);
    menuItem.addActionListener(new MyActionListener(this));
    selectMenu.add(menuItem);
    cbSimulateMenuItem = new JCheckBoxMenuItem("Inject simulated packets");
    cbSimulateMenuItem.setMnemonic(KeyEvent.VK_I);
    cbSimulateMenuItem.setActionCommand(SIMULATE);
    cbSimulateMenuItem.addActionListener(new MyActionListener(this));
    selectMenu.add(cbSimulateMenuItem);
    menuBar.add(selectMenu);
    
    //Build the autohelm menu
    JMenu helmMenu = new JMenu("Autohelm");
    helmMenu.setMnemonic(KeyEvent.VK_A);
    helmMenu.getAccessibleContext().setAccessibleDescription("N2KLib Demo - Autohelm");
    group = new ButtonGroup();
    rbMenuItem = new JRadioButtonMenuItem("Standby");
    rbMenuItem.setMnemonic(KeyEvent.VK_S);
    rbMenuItem.setActionCommand(STANDBY);
    rbMenuItem.addActionListener(new MyActionListener(this));
    group.add(rbMenuItem);;
    group.setSelected(rbMenuItem.getModel(),  true);
    helmMenu.add(rbMenuItem);
    rbMenuItem = new JRadioButtonMenuItem("Auto");
    rbMenuItem.setMnemonic(KeyEvent.VK_A);
    rbMenuItem.setActionCommand(AUTO);
    rbMenuItem.addActionListener(new MyActionListener(this));
    group.add(rbMenuItem);;
    helmMenu.add(rbMenuItem);
    rbMenuItem = new JRadioButtonMenuItem("Track");
    rbMenuItem.setMnemonic(KeyEvent.VK_T);
    rbMenuItem.setActionCommand(TRACK);
    rbMenuItem.addActionListener(new MyActionListener(this));
    helmMenu.add(rbMenuItem);
    group.add(rbMenuItem);
    menuItem = new JMenuItem("+10 (Up 10 degrees)");
    menuItem.setMnemonic(KeyEvent.VK_U);
    menuItem.setActionCommand(UP10);
    menuItem.addActionListener(new MyActionListener(this));
    helmMenu.add(menuItem);
    menuItem = new JMenuItem("-10 (Down 10 degrees)");
    menuItem.setMnemonic(KeyEvent.VK_D);
    menuItem.setActionCommand(DOWN10);
    menuItem.addActionListener(new MyActionListener(this));
    helmMenu.add(menuItem);
    menuBar.add(helmMenu);

    //Build the debug menu.
    JMenu debugMenu = new JMenu("Debug");
    debugMenu.setMnemonic(KeyEvent.VK_D);
    debugMenu.getAccessibleContext().setAccessibleDescription("N2KLib Debugging");
    //a group of radio button menu items for the logfile mode
    group = new ButtonGroup();
    rbMenuItem = new JRadioButtonMenuItem("Errors only");
    rbMenuItem.setMnemonic(KeyEvent.VK_E);
    rbMenuItem.setActionCommand(ERROR);
    rbMenuItem.addActionListener(new MyActionListener(this));
    group.add(rbMenuItem);
    group.setSelected(rbMenuItem.getModel(), true);
    debugMenu.add(rbMenuItem);

    rbMenuItem = new JRadioButtonMenuItem("Errors and NMEA messages");
    rbMenuItem.setMnemonic(KeyEvent.VK_M);
    rbMenuItem.setActionCommand(MESSAGES);
    rbMenuItem.addActionListener(new MyActionListener(this));
    group.add(rbMenuItem);
    debugMenu.add(rbMenuItem);

    rbMenuItem = new JRadioButtonMenuItem("High priority tracing");
    rbMenuItem.setMnemonic(KeyEvent.VK_H);
    rbMenuItem.setActionCommand(ALERT);
    rbMenuItem.addActionListener(new MyActionListener(this));
    group.add(rbMenuItem);
    debugMenu.add(rbMenuItem);

    rbMenuItem = new JRadioButtonMenuItem("Detailed tracing");
    rbMenuItem.setMnemonic(KeyEvent.VK_D);
    rbMenuItem.setActionCommand(NORMAL);
    rbMenuItem.addActionListener(new MyActionListener(this));
    group.add(rbMenuItem);
    debugMenu.add(rbMenuItem);

    menuBar.add(debugMenu);

    setJMenuBar(menuBar);
    //setPreferredSize(new Dimension(640,480));

    Container pane = this.getContentPane();
    GridBagLayout gb = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 1;
    pane.setLayout(gb);

    JPanel rcvPanel = new JPanel();
    rcvPanel.setLayout(new BorderLayout());
    rcvList = new JList<ListPacket>(rcvLines);
    rcvList.addMouseListener(new MyMouseAdapter(this));
    JScrollPane rcvScrollPanel = new JScrollPane(rcvList); 
    rcvScrollPanel.setPreferredSize(new Dimension(1000,numRcvLines*20));
    rcvPanel.add(rcvScrollPanel, BorderLayout.CENTER);

    // Add title
    JLabel label = new JLabel("Received messages");
    gb.setConstraints(label, c);
    pane.add(label);

    // Add received message panel
    c.gridy = 1;
    c.weighty = 0.3;
    gb.setConstraints(rcvPanel, c);
    pane.add(rcvPanel);

    JPanel sendPanel = new JPanel();
    sendPanel.setLayout(new BorderLayout());
    sendList = new JList<ListPacket>(sendLines);
    sendList.addMouseListener(new MyMouseAdapter(this));
    JScrollPane sendScrollPanel = new JScrollPane(sendList); 
    sendScrollPanel.setPreferredSize(new Dimension(1000,numSendLines*20));
    sendPanel.add(sendScrollPanel, BorderLayout.CENTER);

    // Add title
    label = new JLabel("Sent messages"); 
    c.gridy = 2;
    c.weighty = 0;
    gb.setConstraints(label, c);
    pane.add(label);

    // Add the sent messages box
    c.gridy = 3;
    c.weighty = 0.7;
    gb.setConstraints(sendPanel, c);
    pane.add(sendPanel);

    for (int i = 0; i < numRcvLines; i++)
    {
      rcvLines.addElement(new ListPacket(null));
    }
    for (int i = 0; i < numSendLines; i++)
    {
      sendLines.addElement(new ListPacket(null));
    }
    pack();
    setVisible(true);

    connect();
    connectMenuItem.setText(n2klib.isOpen() ? "Disconnect" : "Connect");
  }

  private void start()
  {
  }

  public void run()
  {
    running = true;
    threadEnded = false;
    while (running)
    {
      if (n2klib.isOpen())
      {
        try
        {
          N2KPacket packet = n2klib.get(10);
          if (packet != null)
          {
            logWriter.incomingPacket(packet);
            lastRcvd.put(new Integer(packet.pgn), packet);

            if (packet.pgn == N2K.navigationData_pgn)
            {
              simulatedMsgs.handleNavMsg(packet);
            }
            else if (packet.pgn == N2K.crossTrackError_pgn)
            {
              simulatedMsgs.handleXTE(packet);
            }
          }

          if ((logReader != null) && logReader.active())
          {
            packet = logReader.nextPacket();
            if (packet != null)
            {
              Trace.normal("Sending packet to Actisense");
              sendPacket(packet);
            }
          }

          // Inject any simulated packets that are pending
          if (simulate)
          {
            N2KPacket p = simulatedMsgs.nextSimulatedMsg();
            while (p != null)
            {
              sendPacket(p);
              p = simulatedMsgs.nextSimulatedMsg();
            }
          }
        }
        catch (Exception ex)
        {
          Trace.stack(ex, "Error from N2K lib");
          running = false;
        }

      }
      else
      {
        Trace.alert("N2KLib is not open");
        Utils.sleep(1000);
      }

      // Update the UI with the current packet lists
      synchronized (lastSent)
      {
        int i = 0;
        for (Integer pgnInt : lastRcvd.keySet())
        {
          N2KPacket packet = lastRcvd.get(pgnInt);
          if (i >= rcvLines.size())
          {
            Trace.alert("Adding " + packet.toString());
            rcvLines.addElement(new ListPacket(packet));
          }
          else
          {
            rcvLines.set(i++, new ListPacket(packet));
          }
        }

        i = 0;
        for (Integer pgnInt : lastSent.keySet())
        {
          N2KPacket packet = lastSent.get(pgnInt);
          if (i >= sendLines.size())
          {
            Trace.alert("Adding " + packet.toString());
            sendLines.addElement(new ListPacket(packet));
          }
          else
          {
            sendLines.set(i++, new ListPacket(packet));
          }
        }
        sendList.revalidate();
        sendList.repaint();
        rcvList.revalidate();
        rcvList.repaint();
      }
    }
    threadEnded = true;
  }

  private void connect()
  {
    try
    {
      n2klib.open();

      running = true;
      rcvThread = new Thread(this);
      Trace.alert("Starting main thread");
      rcvThread.start();
      Trace.alert("Main thread started");
    }
    catch (Exception ex)
    {
      Trace.stack(ex, "Error in N2K lib");
    }
  }

  private void disconnect()
  {
    try
    {
      running = false;
      for (int i = 0; i < 500; i++)
      {
        if (threadEnded) break;
        Utils.sleep(2);
      }
      n2klib.close();
    }
    catch (Exception ex)
    {
      Trace.stack(ex, "Error in N2K lib");
    }
  }

  public static void main(String[] args) 
  {
    EventQueue.invokeLater(() -> {
                             GUI gui = new GUI();
                             gui.setVisible(true);
                             gui.addWindowListener(new MyWindowAdapter(gui));
                             gui.addKeyListener(new MyKeyAdapter(gui)); 
                             gui.start();
                           });
  }

  public void handleCmd(ActionEvent e)
  {
    String cmd = e.getActionCommand();
    Trace.alert("Got UI command " + cmd);
    JMenuItem menuItem = (JMenuItem)e.getSource();
    switch (cmd)
    {
      case OPENLOG:
        if ((logReader != null) && logReader.active())
        {
          JOptionPane.showMessageDialog(null, 
                                        "An input log is currently being processed.  You must close that one first.", 
                                        "Logfile warning message", 
                                        JOptionPane.INFORMATION_MESSAGE);       
        }
        else
        {
       		Utils.copyResourceToHome("sample.log");
          logReader = new InputLog(this, option, 200);
        }
        break;
      case CLOSELOG:
        closeInputLogFile();
        break;
      case CONNECT:
        if (n2klib.isOpen())
        {
          disconnect();
          connectMenuItem.setText("Connect");
        }
        else
        {
          connect();
          if (n2klib.isOpen())
          {
            connectMenuItem.setText("Disconnect");
          }
        }
        break;
      case SIMULATE:
        simulate = cbSimulateMenuItem.getState();
        break;

      case IMMED:
        option = InputLog.Option.IMMEDIATE;
        if (logReader != null)
        {
          logReader.setOption(option);
        }
        break;
      case PERIOD:
        option = InputLog.Option.PERIODIC;
        if (logReader != null)
        {
          logReader.setOption(option);
        }
        break;
      case REALTIME:
        option = InputLog.Option.REAL_TIME;
        if (logReader != null)
        {
          logReader.setOption(option);
        }
        break;
      case CONFIGURE:
        MsgConfig cfg = new MsgConfig(this, props, simulatedMsgs);
        break;
      case SELECT:
        MsgSelection sel = new MsgSelection(this, props, simulatedMsgs);
        break;
      case NORMAL:
        Trace.level = 1;
        break;
      case ALERT:
        Trace.level = 2;
        break;
      case MESSAGES:
        Trace.level = 3;
        break;
      case ERROR:
        Trace.level = 4;
        break;

      case AUTO:
      	autohelm = Autohelm.AUTO;
       	// Check message injection is turned on
       	simulate = true;
       	cbSimulateMenuItem.setState(true);
        simulatedMsgs.autoHelmSwitch(autohelm);
        GUIUtils.messageBox("Autohelm", "Heading locked");
        break;

      case STANDBY:
      	autohelm = Autohelm.STANDBY;
       	// Check message injection is turned on
        simulatedMsgs.autoHelmSwitch(autohelm);
        GUIUtils.messageBox("Autohelm", "Standing by");
        break;

      case TRACK:
    		// Check message injection is turned on
    		simulate = true;
    		cbSimulateMenuItem.setState(true);
    		/*
    		if (!simulatedMsgs.navigating)
    		{
    			// Try activating tracking
    			N2KPacket p = simulatedMsgs.getMsg(N2K.headingTrackControl_pgn);
    			try
    			{
    				sendPacket(p);
    				for (int i = 0; i < 8; i++)
    				{
    					Utils.sleep(500);
    					if (simulatedMsgs.navigating) break;
    				}
    			}
    			catch (Exception ex)
    			{
    				Trace.stack(ex, "Failed to send headingTrackControl pgn command to activate tracking");
    			}
    		}
        */

  			autohelm = Autohelm.TRACK;
    		if (simulatedMsgs.autoHelmSwitch(Autohelm.TRACK))
    		{
      		GUIUtils.messageBox("Autohelm", "Tracking");
      	}
      	else
      	{
      		GUIUtils.messageBox("Autohelm", 
      				"Tracking information is not currently available.  The autohelm will start working when\r\n" +
      		    "it receives a 'crossTrackError' message which has 'navigationTerminated' set to No\r\n" +
      				"Until then the helm is locked.\r\n");		
      		
      	}
        break;

      case UP10:
      	simulatedMsgs.adjustHeading(10);
      	break;
      	
      case DOWN10:
      	simulatedMsgs.adjustHeading(-10);
      	break;

      case INCOMING:
        JCheckBoxMenuItem mi = (JCheckBoxMenuItem) e.getSource();
    		if (mi.getState())
    		{
   				logWriter.setOption(OutputLog.INCOMING);
    		}
    		else
    		{
    			logWriter.unsetOption(OutputLog.INCOMING);
    		}
      	break;

      case OUTGOING:
        mi = (JCheckBoxMenuItem) e.getSource();
    		if (mi.getState())
    		{
   				logWriter.setOption(OutputLog.OUTGOING);
    		}
    		else
    		{
    			logWriter.unsetOption(OutputLog.OUTGOING);
    		}
      	break;

      case CLEAR:
        synchronized (lastSent)
        {
        	lastSent.clear();
        	lastRcvd.clear();
        	rcvLines.clear();
        	sendLines.clear();
          sendList.revalidate();
          sendList.repaint();
          rcvList.revalidate();
          rcvList.repaint();
        }
      	break;
      	
      case EXIT:
      	logWriter.unsetOption(OutputLog.INCOMING|OutputLog.OUTGOING);
      	logWriter.close();
      	if (logReader != null)
      	{
      		logReader.close();
      	}
      	if (n2klib.isOpen())
      	{
      		disconnect();
      	}
      	System.exit(0);
      	break;

      default:
        Trace.error("Unknown command " + cmd);
        break;
    }

  }

  void openInputLogFile()
  {
  }

  void closeInputLogFile()
  {
    if (logReader != null)
    {
      logReader.close();
      logReader = null;
    }
  }

  void sendPacket(N2KPacket packet)
  {
    try
    {
      lastSent.put(new Integer(packet.pgn), packet);
      n2klib.put(packet);
      logWriter.outgoingPacket(packet);
    }
    catch (Exception ex)
    {
      Trace.stack(ex,"Error sending packet");
    }
  }
}

class MyActionListener implements ActionListener
{
  private GUI gui;
  public MyActionListener(GUI gui)
  {
    this.gui = gui;
  }

  public void actionPerformed(ActionEvent e) 
  {
    gui.handleCmd(e);
  }
}

class MyWindowAdapter extends WindowAdapter
{
  private GUI gui;
  MyWindowAdapter(GUI gui)
  {
    this.gui = gui;
  }
  @Override
    public void windowClosing(WindowEvent e) 
  {
    Trace.alert("Closing app now");
    try
    {
      gui.n2klib.close();
    }
    catch (Exception ex)
    {
    }
    Utils.sleep(1000);
    System.exit(0);
  }
}

@SuppressWarnings("unused")
class MyKeyAdapter extends KeyAdapter
{
  private GUI gui;
  MyKeyAdapter(GUI gui)
  {
    this.gui = gui;
  }
  @Override
    public void keyPressed(KeyEvent e) 
  {
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
    }
  }  
}

class MyMouseAdapter extends MouseAdapter
{
  private GUI gui;
  MyMouseAdapter(GUI gui)
  {
    this.gui = gui;
  }

  @SuppressWarnings({"unused","unchecked"})
  @Override
    public void mouseClicked(MouseEvent e) 
  {
    JList<N2KPacket> theList = (JList<N2KPacket>) e.getSource();
    if (e.getClickCount() == 2)
    {
      int index = theList.locationToIndex(e.getPoint());
      if (index >= 0)
      {
        Object o = theList.getModel().getElementAt(index);
        Trace.alert("Double-clicked on: " + o.toString());
        N2KPacket packet = ((ListPacket)o).packet;
        if (packet != null)
        {
          JDialog MsgDetails = new MsgDetails(gui, packet);
        }
        //JOptionPane.showMessageDialog(theList, "Double-clicked on: " + o.toString());
      }
    }
  }
}

// Container for packets displayed in the UI
class ListPacket 
{
  N2KPacket packet;
  String    displayable;

  ListPacket(N2KPacket packet)
  {
    this.packet = packet;
    displayable = (packet == null) ? "" : packet.toString();
  }

  @Override
    public String toString()
  {
    return(displayable);
  }
}



