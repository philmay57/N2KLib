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

import java.util.Vector;

import N2KLib.N2KField;
import N2KLib.N2KPacket;
import N2KMsgs.N2K;
import Utils.Trace;
import Utils.Utils;

public class SimulatedMessages
{
  public double lat;
  public double lng;
  public double targetspd;
  double spd;
  public double targetdep;
  double dep;
  public double targethdg;
  double hdgoff;
  double hdg;
  public double targettid;
  double tid;
  public double targettda;
  double tda;
  public double targetws;
  double ws;
  public double targetwa;
  double wa;
  double cog;
  double sog;
  double alt = 2.5;
  double dev = 0.0;
  double var = 0.0;
  double ber = 1.0;

  double lastXte = 0;
  long   lastXteTime = 0;
  double lastDeltaXte = 0;
  long   lastDeltaXteTime = 0;
  double xtv = 0;
  double deltaAdjust = 0;
  double navBearing = -1;
  GUI.Autohelm state = GUI.Autohelm.STANDBY;
  boolean navigating = false;

  public Vector<SimulatedMessage> msgs = new Vector<SimulatedMessage>();

  // Variables for GPS simulation - yacht heading west, just south of Looe, Cornwall
  int days;
  double secsSinceMidnight;  // NMEA works in tenths of a millisecond
  long lastTime = 0;
  long lastUpdate = 0;

  public static final int[] supportedPGNs = {
    N2K.systemTime_pgn,
    N2K.timeDate_pgn,
    N2K.gnssPositionData_pgn,
    N2K.gnssDops_pgn,
    N2K.gnssSatsInView_pgn,
    N2K.positionRapidUpdate_pgn,
    N2K.cogSogRapidUpdate_pgn,
    N2K.speed_pgn,
    N2K.vesselSpeedComponents_pgn,
    N2K.windData_pgn,
    N2K.waterDepth_pgn,
    N2K.vesselHeading_pgn,
    N2K.magneticVariation_pgn,
    N2K.aisUtcAndDateReport_pgn,
    N2K.aisClassAPositionReport_pgn,
    N2K.aisClassAStaticAndVoyageRelatedData_pgn,
    N2K.aisClassBPositionReport_pgn,
    N2K.aisClassBExtendedPositionReport_pgn,
    N2K.aisClassBStaticDataMsg24PartA_pgn,
    N2K.aisClassBStaticDataMsg24PartB_pgn,
    N2K.heartbeat_pgn
  };

  public static final int[] defaultPGNs = 
  {
    N2K.systemTime_pgn,
    N2K.gnssPositionData_pgn,
    N2K.positionRapidUpdate_pgn,
    N2K.cogSogRapidUpdate_pgn,
    N2K.speed_pgn,
    N2K.windData_pgn,
    N2K.waterDepth_pgn,
    N2K.vesselHeading_pgn
  };

  public class SimulatedMessage
  {
    long lastSimTime = 0;
    public int pgn;
    public String description;
    boolean enabled = false;
    public int period = 2000;  // default to 1 second interval
    int priority = 3;
    SimulatedMessage(int pgn, String desc)
    {
      this.pgn = pgn;
      description = desc;
    }
  }

  public SimulatedMessages(Props props)
  {
    reconfigure(props);
    loadYachtParms(props);
  }

  public void reconfigure(Props props)
  {
    synchronized (msgs)
    {
      msgs.removeAllElements();

      for (int pgn :supportedPGNs)
      {
        N2KPacket p = new N2KPacket(pgn);
        SimulatedMessage msg = new SimulatedMessage(pgn, p.description);
        msgs.add(msg);
        // Override frequency for fast messages
        if ((pgn == N2K.systemTime_pgn) ||
            (pgn == N2K.positionRapidUpdate_pgn) ||
            (pgn == N2K.cogSogRapidUpdate_pgn) ||
            (pgn == N2K.timeDate_pgn) ||
            (pgn == N2K.speed_pgn))
        {
          msg.period = 1000;
        }
        else if ((pgn == N2K.aisClassAPositionReport_pgn) ||
                 (pgn == N2K.aisUtcAndDateReport_pgn) ||
                 (pgn == N2K.aisClassAStaticAndVoyageRelatedData_pgn) ||
                 (pgn == N2K.aisClassBPositionReport_pgn) ||
                 (pgn == N2K.aisClassBExtendedPositionReport_pgn) ||
                 (pgn == N2K.aisClassBStaticDataMsg24PartA_pgn) ||
                 (pgn == N2K.aisClassBStaticDataMsg24PartB_pgn))
        {
          msg.period = 30000;  // AIS packets every 30 seconds
          msg.priority = 6;
        }
        else if (pgn == N2K.heartbeat_pgn)
        {
          msg.period = 10000;  // Heartbeat every 10 seconds
          msg.priority = 7;
        }

        if (props.enabled(Props.pgn + pgn))
        {
          msg.enabled = true;
        }
        else
        {
          // If the pgn is not present in the props file then select the correct default
          for (int def : defaultPGNs)
          {
            if (def == msg.pgn)
            {
              msg.enabled = props.enabled(Props.pgn + pgn, true);
            }
          }
        }       
        //Trace.alert("msg " + msg.description + " enabled:" + msg.enabled);
      }
    }
  }

  public N2KPacket nextSimulatedMsg()
  {
    N2KPacket p = null;

    long curTime = System.currentTimeMillis();

    synchronized(msgs)
    {
      for (SimulatedMessage msg : msgs)
      {
        if ((msg.enabled) && 
            ((msg.lastSimTime + msg.period) < curTime))
        {

          // We are going to return a message - update its last injection time
          msg.lastSimTime = curTime;
          p = getMsg(msg.pgn);

          if (p != null)
          {
          	p.priority = (byte)msg.priority;
          	break;
          }
        }
      }
    }
    Utils.sleep(10); // Give slower devices a breathing space to process each message
    return(p);
  }

  public N2KPacket getMsg(int pgn)
  {
  	N2KPacket p = null;

  	long curTime = System.currentTimeMillis();

  	updateParameters(curTime);  // Move the yacht around, set wind etc

  	int daymillis = 24*60*60*1000;
  	days = (int)(curTime/daymillis);
  	secsSinceMidnight = ((double)(curTime % daymillis)) / 1000;

  	try
  	{
  		switch (pgn)
  		{
  		// For GPS send the set of pgns: 126992, 129025, 129026, 129029, 129033, 129539                                           
  		// 126992 systemtime
  		case N2K.systemTime_pgn:
  			p = new N2KPacket(N2K.systemTime_pgn);            
  			p.fields[N2K.systemTime.sid].setInt(N2KPacket.getNewSid());
  			p.fields[N2K.systemTime.source].setInt(N2K.systemTime.source_values.GPS);
  			p.fields[N2K.systemTime.date].setInt(days);
  			p.fields[N2K.systemTime.time].setDecimal(secsSinceMidnight);
  			break;

  			// 129025 positionrapidupdate 
  		case N2K.positionRapidUpdate_pgn:
  			p = new N2KPacket(N2K.positionRapidUpdate_pgn);           
  			p.fields[N2K.positionRapidUpdate.latitude].setDecimal(lat);
  			p.fields[N2K.positionRapidUpdate.longitude].setDecimal(lng); 
  			break;

  			// 129026 cogsograpidupdate 
  		case N2K.cogSogRapidUpdate_pgn:
  			p = new N2KPacket(N2K.cogSogRapidUpdate_pgn);           
  			p.fields[N2K.cogSogRapidUpdate.sid].setInt(N2KPacket.getNewSid());
  			p.fields[N2K.cogSogRapidUpdate.cogReference].setInt(N2K.cogSogRapidUpdate.cogReference_values.True);
  			p.fields[N2K.cogSogRapidUpdate.cog].setDecimal(cog);
  			p.fields[N2K.cogSogRapidUpdate.sog].setDecimal(sog);
  			break;

  			// 129029 gnsspositiondata (required for E120 positioning, but causes crash if stations added)
  		case N2K.gnssPositionData_pgn:
  			p = new N2KPacket(N2K.gnssPositionData_pgn);            
  			p.fields[N2K.gnssPositionData.sid].setInt(N2KPacket.getNewSid());
  			p.fields[N2K.gnssPositionData.date].setInt(days);
  			p.fields[N2K.gnssPositionData.time].setDecimal(secsSinceMidnight);
  			p.fields[N2K.gnssPositionData.latitude].setDecimal(lat);
  			p.fields[N2K.gnssPositionData.longitude].setDecimal(lng); 
  			p.fields[N2K.gnssPositionData.altitude].setDecimal(alt); 
  			p.fields[N2K.gnssPositionData.gnssType].setInt(N2K.gnssPositionData.gnssType_values.GPS);
  			p.fields[N2K.gnssPositionData.method].setInt(N2K.gnssPositionData.method_values.GNSSfix); 
  			p.fields[N2K.gnssPositionData.integrity].setInt(N2K.gnssPositionData.integrity_values.Safe);
  			p.fields[N2K.gnssPositionData.numberOfSvs].setInt(6); // 6 satellites
  			p.fields[N2K.gnssPositionData.hdop].setDecimal(0.6); // ??
  			p.fields[N2K.gnssPositionData.pdop].setDecimal(1.0); // ??
  			p.fields[N2K.gnssPositionData.geoidalSeparation].setDecimal(46.9); // ??
  			p.fields[N2K.gnssPositionData.referenceStations].setInt(0); // 0 reference stations
  			/*
        p.fields[N2K.gnssPositionData.referenceStations].setInt(2); // 2 reference stations
        N2KField[] repset = p.addRepSet();
          repset[N2K.gnssPositionData.rep.referenceStationType].setInt(0);  //GPS
          repset[N2K.gnssPositionData.rep.referenceStationId].setInt(1);
          repset[N2K.gnssPositionData.rep.ageOfDgnssCorrections].setDecimal(5.0);
        repset = p.addRepSet();
          repset[N2K.gnssPositionData.rep.referenceStationType].setInt(0);  //GPS
          repset[N2K.gnssPositionData.rep.referenceStationId].setInt(2);
          repset[N2K.gnssPositionData.rep.ageOfDgnssCorrections].setDecimal(3.0);
  			 */
  			break;

  			// 129033 timedate 
  		case N2K.timeDate_pgn:
  			p = new N2KPacket(N2K.timeDate_pgn);            
  			p.fields[N2K.timeDate.date].setInt(days);
  			p.fields[N2K.timeDate.time].setDecimal(secsSinceMidnight);
  			p.fields[N2K.timeDate.localOffset].setInt(0);
  			break;

  			// 129539 gnss dops
  		case N2K.gnssDops_pgn:
  			p = new N2KPacket(N2K.gnssDops_pgn);            
  			p.fields[N2K.gnssDops.sid].setInt(N2KPacket.getNewSid());
  			p.fields[N2K.gnssDops.desiredMode].setInt(N2K.gnssDops.desiredMode_values.A3D); 
  			p.fields[N2K.gnssDops.actualMode].setInt(N2K.gnssDops.actualMode_values.A3D); 
  			p.fields[N2K.gnssDops.hdop].setDecimal(0.6); // ??
  			p.fields[N2K.gnssDops.vdop].setDecimal(0.8); // ??
  			p.fields[N2K.gnssDops.tdop].setDecimal(0.5); // ??
  			break;

  			// gnss sats in view
  		case N2K.gnssSatsInView_pgn:
  			p = new N2KPacket(N2K.gnssSatsInView_pgn);
  			p.fields[N2K.gnssSatsInView.sid].setInt(N2KPacket.getNewSid());
  			p.fields[N2K.gnssSatsInView.mode].setInt(2); //?? Not defined in XML
  			p.fields[N2K.gnssSatsInView.satsInView].setInt(12);
  			N2KField[] repset = p.addRepSet();
  			repset[N2K.gnssSatsInView.rep.prn].setInt(2);
  			repset[N2K.gnssSatsInView.rep.elevation].setDecimal(0.6283);
  			repset[N2K.gnssSatsInView.rep.azimuth].setDecimal(1.274);
  			repset[N2K.gnssSatsInView.rep.snr].setDecimal(41.0);
  			repset[N2K.gnssSatsInView.rep.rangeResiduals].setInt(0);
  			repset[N2K.gnssSatsInView.rep.status].setInt(N2K.gnssSatsInView.rep.status_values.Used);
  			repset = p.addRepSet();
  			repset[N2K.gnssSatsInView.rep.prn].setInt(6);
  			repset[N2K.gnssSatsInView.rep.elevation].setDecimal(0.2268);
  			repset[N2K.gnssSatsInView.rep.azimuth].setDecimal(0.5585);
  			repset[N2K.gnssSatsInView.rep.snr].setDecimal(35.0);
  			repset[N2K.gnssSatsInView.rep.rangeResiduals].setInt(0);
  			repset[N2K.gnssSatsInView.rep.status].setInt(N2K.gnssSatsInView.rep.status_values.Used);
  			repset = p.addRepSet();
  			repset[N2K.gnssSatsInView.rep.prn].setInt(12);
  			repset[N2K.gnssSatsInView.rep.elevation].setDecimal(0.7155);
  			repset[N2K.gnssSatsInView.rep.azimuth].setDecimal(1.5184);
  			repset[N2K.gnssSatsInView.rep.snr].setDecimal(43.0);
  			repset[N2K.gnssSatsInView.rep.rangeResiduals].setInt(0);
  			repset[N2K.gnssSatsInView.rep.status].setInt(N2K.gnssSatsInView.rep.status_values.Used);
  			repset = p.addRepSet();
  			repset[N2K.gnssSatsInView.rep.prn].setInt(14);
  			repset[N2K.gnssSatsInView.rep.elevation].setDecimal(0.4712);
  			repset[N2K.gnssSatsInView.rep.azimuth].setDecimal(4.1713);
  			repset[N2K.gnssSatsInView.rep.snr].setDecimal(39.0);
  			repset[N2K.gnssSatsInView.rep.rangeResiduals].setInt(0);
  			repset[N2K.gnssSatsInView.rep.status].setInt(N2K.gnssSatsInView.rep.status_values.Used);
  			repset = p.addRepSet();
  			repset[N2K.gnssSatsInView.rep.prn].setInt(24);
  			repset[N2K.gnssSatsInView.rep.elevation].setDecimal(0.1221);
  			repset[N2K.gnssSatsInView.rep.azimuth].setDecimal(2.5656);
  			repset[N2K.gnssSatsInView.rep.snr].setDecimal(31.0);
  			repset[N2K.gnssSatsInView.rep.rangeResiduals].setInt(0);
  			repset[N2K.gnssSatsInView.rep.status].setInt(N2K.gnssSatsInView.rep.status_values.Used);
  			repset = p.addRepSet();
  			repset[N2K.gnssSatsInView.rep.prn].setInt(25);
  			repset[N2K.gnssSatsInView.rep.elevation].setDecimal(1.3788);
  			repset[N2K.gnssSatsInView.rep.azimuth].setDecimal(1.2042);
  			repset[N2K.gnssSatsInView.rep.snr].setDecimal(46.0);
  			repset[N2K.gnssSatsInView.rep.rangeResiduals].setInt(0);
  			repset[N2K.gnssSatsInView.rep.status].setInt(N2K.gnssSatsInView.rep.status_values.Used);
  			repset = p.addRepSet();
  			repset[N2K.gnssSatsInView.rep.prn].setInt(29);
  			repset[N2K.gnssSatsInView.rep.elevation].setDecimal(1.0297);
  			repset[N2K.gnssSatsInView.rep.azimuth].setDecimal(3.5604);
  			repset[N2K.gnssSatsInView.rep.snr].setDecimal(44.0);
  			repset[N2K.gnssSatsInView.rep.rangeResiduals].setInt(0);
  			repset[N2K.gnssSatsInView.rep.status].setInt(N2K.gnssSatsInView.rep.status_values.Used);
  			repset = p.addRepSet();
  			repset[N2K.gnssSatsInView.rep.prn].setInt(31);
  			repset[N2K.gnssSatsInView.rep.elevation].setDecimal(0.7504);
  			repset[N2K.gnssSatsInView.rep.azimuth].setDecimal(5.2708);
  			repset[N2K.gnssSatsInView.rep.snr].setDecimal(39.0);
  			repset[N2K.gnssSatsInView.rep.rangeResiduals].setInt(0);
  			repset[N2K.gnssSatsInView.rep.status].setInt(N2K.gnssSatsInView.rep.status_values.Used);
  			repset = p.addRepSet();
  			repset[N2K.gnssSatsInView.rep.prn].setInt(32);
  			repset[N2K.gnssSatsInView.rep.elevation].setDecimal(0.4188);
  			repset[N2K.gnssSatsInView.rep.azimuth].setDecimal(5.2236);
  			repset[N2K.gnssSatsInView.rep.snr].setDecimal(43.0);
  			repset[N2K.gnssSatsInView.rep.rangeResiduals].setInt(0);
  			repset[N2K.gnssSatsInView.rep.status].setInt(N2K.gnssSatsInView.rep.status_values.Used);
  			repset = p.addRepSet();
  			repset[N2K.gnssSatsInView.rep.prn].setInt(74);
  			repset[N2K.gnssSatsInView.rep.elevation].setDecimal(0.5934);
  			repset[N2K.gnssSatsInView.rep.azimuth].setDecimal(5.4803);
  			repset[N2K.gnssSatsInView.rep.snr].setDecimal(37.0);
  			repset[N2K.gnssSatsInView.rep.rangeResiduals].setInt(0);
  			repset[N2K.gnssSatsInView.rep.status].setInt(N2K.gnssSatsInView.rep.status_values.Used);
  			repset = p.addRepSet();
  			repset[N2K.gnssSatsInView.rep.prn].setInt(73);
  			repset[N2K.gnssSatsInView.rep.elevation].setDecimal(1.5009);
  			repset[N2K.gnssSatsInView.rep.azimuth].setDecimal(0.7504);
  			repset[N2K.gnssSatsInView.rep.snr].setDecimal(0.0);
  			repset[N2K.gnssSatsInView.rep.rangeResiduals].setInt(0);
  			repset[N2K.gnssSatsInView.rep.status].setInt(N2K.gnssSatsInView.rep.status_values.Used);
  			repset = p.addRepSet();
  			repset[N2K.gnssSatsInView.rep.prn].setInt(82);
  			repset[N2K.gnssSatsInView.rep.elevation].setDecimal(0.7155);
  			repset[N2K.gnssSatsInView.rep.azimuth].setDecimal(0.7155);
  			repset[N2K.gnssSatsInView.rep.snr].setDecimal(34.0);
  			repset[N2K.gnssSatsInView.rep.rangeResiduals].setInt(0);
  			repset[N2K.gnssSatsInView.rep.status].setInt(N2K.gnssSatsInView.rep.status_values.Used);
  			break;

  		case N2K.speed_pgn:
  			p = new N2KPacket(N2K.speed_pgn);           
  			p.fields[N2K.speed.sid].setInt(N2KPacket.getNewSid());
  			p.fields[N2K.speed.speedWaterReferenced].setDecimal(spd);  // speed in m/s
  			p.fields[N2K.speed.speedGroundReferenced].setNotAvailable();     
  			p.fields[N2K.speed.speedWaterReferencedType].setInt(
  					N2K.speed.speedWaterReferencedType_values.Paddlewheel);
  			p.fields[N2K.speed.speedDirection].setReserved();  
  			break;

  			// 130306 Wind Data (apparent)
  		case N2K.windData_pgn:
  			p = new N2KPacket(N2K.windData_pgn);            
  			p.fields[N2K.windData.sid].setInt(N2KPacket.getNewSid());
  			p.fields[N2K.windData.windSpeed].setDecimal(ws);  // speed in m/s
  			p.fields[N2K.windData.windAngle].setDecimal(wa);  // in rads
  			p.fields[N2K.windData.reference].setInt(N2K.windData.reference_values.Apparent); 
  			// True values are not accepted by E120                                                 
  			// p.fields[N2K.windData.reference].setInt(N2K.windData.reference_values.True_boatreferenced_); 
  			// p.fields[N2K.windData.reference].setInt(N2K.windData.reference_values.True_waterreferenced_);  
  			break;

  			// 128267 Water Depth 
  		case N2K.waterDepth_pgn:
  			p = new N2KPacket(N2K.waterDepth_pgn);            
  			p.fields[N2K.waterDepth.sid].setInt(N2KPacket.getNewSid());
  			p.fields[N2K.waterDepth.depth].setDecimal(dep);  // depth in m
  			p.fields[N2K.waterDepth.offset].setDecimal(1.0);  // to surface
  			p.fields[N2K.waterDepth.range].setDecimal(500.0); // max depth range
  			break;

  			// 127250 Heading
  		case N2K.vesselHeading_pgn:
  			p = new N2KPacket(N2K.vesselHeading_pgn);
  			p.fields[N2K.vesselHeading.sid].setInt(N2KPacket.getNewSid());
  			p.fields[N2K.vesselHeading.heading].setDecimal(hdg); 
  			p.fields[N2K.vesselHeading.deviation].setNotAvailable(); 
  			p.fields[N2K.vesselHeading.variation].setNotAvailable(); 
  			p.fields[N2K.vesselHeading.reference].setInt(N2K.vesselHeading.reference_values.Magnetic); 
  			break;

  			// 127258 Magnetic Variation
  		case N2K.magneticVariation_pgn:
  			p = new N2KPacket(N2K.magneticVariation_pgn);
  			p.fields[N2K.magneticVariation.sid].setInt(N2KPacket.getNewSid());
  			p.fields[N2K.magneticVariation.source].setInt(N2K.magneticVariation.source_values.WMM2000); 
  			p.fields[N2K.magneticVariation.ageOfService].setInt(days); 
  			p.fields[N2K.magneticVariation.variation].setDecimal(var); 
  			break;                

  			// 130578 Vessel speed components
  		case N2K.vesselSpeedComponents_pgn:
  			p = new N2KPacket(N2K.vesselSpeedComponents_pgn);
  			p.fields[N2K.vesselSpeedComponents.longitudinalSpeedWaterReferenced].setDecimal(spd); 
  			p.fields[N2K.vesselSpeedComponents.transverseSpeedWaterReferenced].setDecimal(0.0); 
  			p.fields[N2K.vesselSpeedComponents.longitudinalSpeedGroundReferenced].setNotAvailable(); 
  			p.fields[N2K.vesselSpeedComponents.transverseSpeedGroundReferenced].setNotAvailable(); 
  			p.fields[N2K.vesselSpeedComponents.sternSpeedWaterReferenced].setNotAvailable(); 
  			p.fields[N2K.vesselSpeedComponents.sternSpeedGroundReferenced].setNotAvailable(); 
  			break;          

  		case N2K.aisUtcAndDateReport_pgn:
  			Trace.alert("Constructing new aisUTC packet");
  			p = new N2KPacket(N2K.aisUtcAndDateReport_pgn);
  			p.fields[N2K.aisUtcAndDateReport.messageId].setInt(23);
  			p.fields[N2K.aisUtcAndDateReport.repeatIndicator].setInt(
  					N2K.aisUtcAndDateReport.repeatIndicator_values.Initial);
  			p.fields[N2K.aisUtcAndDateReport.userId].setInt(244740922);
  			p.fields[N2K.aisUtcAndDateReport.longitude].setDecimal(lng - 0.2);
  			p.fields[N2K.aisUtcAndDateReport.latitude].setDecimal(lat + 0.2);
  			p.fields[N2K.aisUtcAndDateReport.positionAccuracy].setInt(
  					N2K.aisUtcAndDateReport.positionAccuracy_values.High);
  			p.fields[N2K.aisUtcAndDateReport.raim].setInt(
  					N2K.aisUtcAndDateReport.raim_values.inuse);
  			p.fields[N2K.aisUtcAndDateReport.positionTime].setDecimal(secsSinceMidnight);
  			p.fields[N2K.aisUtcAndDateReport.communicationState].setInt(0);
  			p.fields[N2K.aisUtcAndDateReport.aisTransceiverInformation].setInt(
  					N2K.aisUtcAndDateReport.aisTransceiverInformation_values.ChannelAVDLreception);
  			p.fields[N2K.aisUtcAndDateReport.positionDate].setInt(days);
  			p.fields[N2K.aisUtcAndDateReport.gnssType].setInt(
  					N2K.aisUtcAndDateReport.gnssType_values.GPS);
  			p.fields[N2K.aisUtcAndDateReport.spare].setInt(0);
  			break;


  		case N2K.aisClassAPositionReport_pgn:
  			p = new N2KPacket(N2K.aisClassAPositionReport_pgn);
  			p.fields[N2K.aisClassAPositionReport.messageId].setInt(23);
  			p.fields[N2K.aisClassAPositionReport.repeatIndicator].setInt(
  					N2K.aisClassAPositionReport.repeatIndicator_values.Initial);
  			p.fields[N2K.aisClassAPositionReport.userId].setInt(244740922);
  			p.fields[N2K.aisClassAPositionReport.longitude].setDecimal(lng - 0.2);
  			p.fields[N2K.aisClassAPositionReport.latitude].setDecimal(lat + 0.2);
  			p.fields[N2K.aisClassAPositionReport.positionAccuracy].setInt(
  					N2K.aisClassAPositionReport.positionAccuracy_values.High);
  			p.fields[N2K.aisClassAPositionReport.raim].setInt(
  					N2K.aisClassAPositionReport.raim_values.inuse);
  			p.fields[N2K.aisClassAPositionReport.timeStamp].setInt(0);
  			p.fields[N2K.aisClassAPositionReport.cog].setDecimal(cog);
  			p.fields[N2K.aisClassAPositionReport.sog].setDecimal(sog);
  			p.fields[N2K.aisClassAPositionReport.communicationState].setInt(0);
  			p.fields[N2K.aisClassAPositionReport.aisTransceiverInformation].setInt(
  					N2K.aisClassAPositionReport.aisTransceiverInformation_values.ChannelAVDLreception);
  			p.fields[N2K.aisClassAPositionReport.heading].setDecimal(hdg);
  			p.fields[N2K.aisClassAPositionReport.rateOfTurn].setDecimal(0);
  			p.fields[N2K.aisClassAPositionReport.navStatus].setInt(
  					N2K.aisClassAPositionReport.navStatus_values.Restrictedmanoeuverability);
  			p.fields[N2K.aisClassAPositionReport.regionalApplication].setInt(1);
  			Trace.alert(p.toString());
  			byte[] x = new byte[2000];
  			int l = p.getRawData(x, 10);
  			Trace.hexalt("Raw Data:",x, 10, l+10);
  			break;

  		case N2K.aisClassAStaticAndVoyageRelatedData_pgn:
  			p = new N2KPacket(N2K.aisClassAStaticAndVoyageRelatedData_pgn);
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.messageId].setInt(23);
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.repeatIndicator].setInt(
  					N2K.aisClassAStaticAndVoyageRelatedData.repeatIndicator_values.Initial);
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.userId].setInt(244740922);
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.imoNumber].setNotAvailable();
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.callsign].setString("PG9182@");
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.name].setString("GAASTERLAND@@@@@@@@@");
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.typeOfShip].setInt(
  					N2K.aisClassAStaticAndVoyageRelatedData.typeOfShip_values.Engagedindredgingorunderwateroperations);
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.length].setDecimal(86.0);
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.beam].setDecimal(11.0);
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.positionReferenceFromStarboard].setDecimal(5.0);
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.positionReferenceFromBow].setDecimal(76.0);
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.etaDate].setInt(17207);
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.etaTime].setDecimal(44100.0);
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.draft].setNotAvailable();
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.destination].setString("");
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.aisVersionIndicator].setInt(
  					N2K.aisClassAStaticAndVoyageRelatedData.aisVersionIndicator_values.ITU_RM_1371_3);
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.gnssType].setInt(
  					N2K.aisClassAStaticAndVoyageRelatedData.gnssType_values.GPS);
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.dte].setInt(
  					N2K.aisClassAStaticAndVoyageRelatedData.dte_values.available);
  			p.fields[N2K.aisClassAStaticAndVoyageRelatedData.aisTransceiverInformation].setInt(
  					N2K.aisClassAStaticAndVoyageRelatedData.aisTransceiverInformation_values.ChannelAVDLreception);
  			break;

  		case N2K.aisClassBPositionReport_pgn:
  			p = new N2KPacket(N2K.aisClassBPositionReport_pgn);
  			p.fields[N2K.aisClassBPositionReport.messageId].setInt(24);
  			p.fields[N2K.aisClassBPositionReport.repeatIndicator].setInt(
  					N2K.aisClassBPositionReport.repeatIndicator_values.Initial);
  			p.fields[N2K.aisClassBPositionReport.userId].setInt(367417740);
  			p.fields[N2K.aisClassBPositionReport.longitude].setDecimal(lng+0.2);
  			p.fields[N2K.aisClassBPositionReport.latitude].setDecimal(lat-0.2);
  			p.fields[N2K.aisClassBPositionReport.positionAccuracy].setInt(
  					N2K.aisClassBPositionReport.positionAccuracy_values.High);
  			p.fields[N2K.aisClassBPositionReport.raim].setInt(
  					N2K.aisClassBPositionReport.raim_values.inuse);
  			p.fields[N2K.aisClassBPositionReport.timeStamp].setInt((int)((curTime/1000) % 60));
  			p.fields[N2K.aisClassBPositionReport.cog].setDecimal(cog);
  			p.fields[N2K.aisClassBPositionReport.sog].setDecimal(sog);
  			p.fields[N2K.aisClassBPositionReport.communicationState].setInt(
  					N2K.aisClassBPositionReport.aisCommunicationState_values.ITDMA);
  			p.fields[N2K.aisClassBPositionReport.aisTransceiverInformation].setInt(
  					N2K.aisClassBPositionReport.aisTransceiverInformation_values.ChannelAVDLreception);
  			p.fields[N2K.aisClassBPositionReport.heading].setDecimal(hdg);
  			p.fields[N2K.aisClassBPositionReport.regionalApplication].setInt(1);
  			p.fields[N2K.aisClassBPositionReport.regionalApplication2].setInt(1);
  			p.fields[N2K.aisClassBPositionReport.unitType].setInt(
  					N2K.aisClassBPositionReport.unitType_values.CS);
  			p.fields[N2K.aisClassBPositionReport.integratedDisplay].setInt(
  					N2K.aisClassBPositionReport.integratedDisplay_values.Yes);
  			p.fields[N2K.aisClassBPositionReport.dsc].setInt(
  					N2K.aisClassBPositionReport.dsc_values.Yes);
  			p.fields[N2K.aisClassBPositionReport.band].setInt(
  					N2K.aisClassBPositionReport.band_values.top525kHzofmarineband);
  			p.fields[N2K.aisClassBPositionReport.canHandleMsg22].setInt(
  					N2K.aisClassBPositionReport.canHandleMsg22_values.Yes);
  			p.fields[N2K.aisClassBPositionReport.aisMode].setInt(
  					N2K.aisClassBPositionReport.aisMode_values.Autonomous);
  			p.fields[N2K.aisClassBPositionReport.aisCommunicationState].setInt(
  					N2K.aisClassBPositionReport.aisCommunicationState_values.ITDMA);
  			break;

  		case N2K.aisClassBExtendedPositionReport_pgn:
  			p = new N2KPacket(N2K.aisClassBExtendedPositionReport_pgn);
  			p.fields[N2K.aisClassBExtendedPositionReport.messageId].setInt(24);
  			p.fields[N2K.aisClassBExtendedPositionReport.repeatIndicator].setInt(
  					N2K.aisClassBExtendedPositionReport.repeatIndicator_values.Initial);
  			p.fields[N2K.aisClassBExtendedPositionReport.userId].setInt(367417740);
  			p.fields[N2K.aisClassBExtendedPositionReport.longitude].setDecimal(lng+0.2);
  			p.fields[N2K.aisClassBExtendedPositionReport.latitude].setDecimal(lat-0.2);
  			p.fields[N2K.aisClassBExtendedPositionReport.positionAccuracy].setInt(
  					N2K.aisClassBExtendedPositionReport.positionAccuracy_values.High);
  			p.fields[N2K.aisClassBExtendedPositionReport.aisRaimFlag].setInt(
  					N2K.aisClassBExtendedPositionReport.aisRaimFlag_values.inuse);
  			p.fields[N2K.aisClassBExtendedPositionReport.timeStamp].setInt((int)((curTime/1000) % 60));
  			p.fields[N2K.aisClassBExtendedPositionReport.cog].setDecimal(cog);
  			p.fields[N2K.aisClassBExtendedPositionReport.sog].setDecimal(sog);
  			p.fields[N2K.aisClassBExtendedPositionReport.regionalApplication].setInt(1);
  			p.fields[N2K.aisClassBExtendedPositionReport.regionalApplication2].setInt(1);
  			p.fields[N2K.aisClassBExtendedPositionReport.typeOfShip].setInt(
  					N2K.aisClassBExtendedPositionReport.typeOfShip_values.Pleasure);
  			p.fields[N2K.aisClassBExtendedPositionReport.trueHeading].setDecimal(hdg);
  			p.fields[N2K.aisClassBExtendedPositionReport.gnssType].setInt(
  					N2K.aisClassBExtendedPositionReport.gnssType_values.GPS);
  			p.fields[N2K.aisClassBExtendedPositionReport.length].setDecimal(86.0);
  			p.fields[N2K.aisClassBExtendedPositionReport.beam].setDecimal(11.0);
  			p.fields[N2K.aisClassBExtendedPositionReport.positionReferenceFromStarboard].setDecimal(5.0);
  			p.fields[N2K.aisClassBExtendedPositionReport.positionReferenceFromBow].setDecimal(76.0);
  			p.fields[N2K.aisClassBExtendedPositionReport.name].setString("ANASTASIA@@@@@@@@@@@");
  			p.fields[N2K.aisClassBExtendedPositionReport.dte].setInt(
  					N2K.aisClassBExtendedPositionReport.dte_values.Available);
  			p.fields[N2K.aisClassBExtendedPositionReport.aisMode].setInt(0);
  			p.fields[N2K.aisClassBExtendedPositionReport.aisTransceiverInformation].setInt(
  					N2K.aisClassBExtendedPositionReport.aisTransceiverInformation_values.ChannelAVDLreception);
  			break;

  		case N2K.aisClassBStaticDataMsg24PartA_pgn:
  			p = new N2KPacket(N2K.aisClassBStaticDataMsg24PartA_pgn);
  			p.fields[N2K.aisClassBStaticDataMsg24PartA.messageId].setInt(24);
  			p.fields[N2K.aisClassBStaticDataMsg24PartA.repeatIndicator].setInt(
  					N2K.aisClassBStaticDataMsg24PartA.repeatIndicator_values.Initial);
  			p.fields[N2K.aisClassBStaticDataMsg24PartA.userId].setInt(367417740);
  			p.fields[N2K.aisClassBStaticDataMsg24PartA.name].setString("ANASTASIA@@@@@@@@@@@");
  			break;

  		case N2K.aisClassBStaticDataMsg24PartB_pgn:
  			p = new N2KPacket(N2K.aisClassBStaticDataMsg24PartB_pgn);
  			p.fields[N2K.aisClassBStaticDataMsg24PartB.messageId].setInt(24);
  			p.fields[N2K.aisClassBStaticDataMsg24PartB.repeatIndicator].setInt(
  					N2K.aisClassBStaticDataMsg24PartB.repeatIndicator_values.Initial);
  			p.fields[N2K.aisClassBStaticDataMsg24PartB.userId].setInt(367417740);
  			p.fields[N2K.aisClassBStaticDataMsg24PartB.typeOfShip].setInt(
  					N2K.aisClassBStaticDataMsg24PartB.typeOfShip_values.Pleasure);
  			p.fields[N2K.aisClassBStaticDataMsg24PartB.vendorId].setString("WIBBLE@");
  			p.fields[N2K.aisClassBStaticDataMsg24PartB.callsign].setString("WDE9481");
  			p.fields[N2K.aisClassBStaticDataMsg24PartB.length].setDecimal(14.0);
  			p.fields[N2K.aisClassBStaticDataMsg24PartB.beam].setDecimal(4.0);
  			p.fields[N2K.aisClassBStaticDataMsg24PartB.positionReferenceFromBow].setDecimal(7.0);
  			p.fields[N2K.aisClassBStaticDataMsg24PartB.positionReferenceFromStarboard].setDecimal(2.0);
  			p.fields[N2K.aisClassBStaticDataMsg24PartB.mothershipUserId].setInt(0);
  			p.fields[N2K.aisClassBStaticDataMsg24PartB.reserved].setNotAvailable();
  			p.fields[N2K.aisClassBStaticDataMsg24PartB.spare].setNotAvailable();
  			break;

  		case N2K.heartbeat_pgn:
  			p = new N2KPacket(N2K.heartbeat_pgn);
  			p.fields[N2K.heartbeat.sequenceCounter].setInt(N2KPacket.getNewHeartbeat());
  			p.fields[N2K.heartbeat.dataTransmitOffset].setInt(0);

  		case N2K.headingTrackControl_pgn:
  			p = new N2KPacket(N2K.headingTrackControl_pgn);
  			p.fields[N2K.headingTrackControl.commandedRudderAngle].setDecimal(0.0);
  			p.fields[N2K.headingTrackControl.commandedRudderDirection].setInt(0);
  			p.fields[N2K.headingTrackControl.headingReference].setInt(1);
  			p.fields[N2K.headingTrackControl.headingToSteerCourse].setDecimal(Math.PI);
  			p.fields[N2K.headingTrackControl.offHeadingLimit].setDecimal(0.1);
  			p.fields[N2K.headingTrackControl.offHeadingLimitExceeded].setInt(1);
  			p.fields[N2K.headingTrackControl.offTrackLimit].setInt(200);
  			p.fields[N2K.headingTrackControl.offTrackLimitExceeded].setInt(1);
  			p.fields[N2K.headingTrackControl.override].setInt(0);
  			p.fields[N2K.headingTrackControl.radiusOfTurnOrder].setInt(0);
  			p.fields[N2K.headingTrackControl.rateOfTurnOrder].setInt(0);
  			p.fields[N2K.headingTrackControl.rudderLimit].setDecimal(0.5);
  			p.fields[N2K.headingTrackControl.rudderLimitExceeded].setInt(1);
  			p.fields[N2K.headingTrackControl.steeringMode].setInt(1);
  			p.fields[N2K.headingTrackControl.track].setDecimal(Math.PI);
  			p.fields[N2K.headingTrackControl.turnMode].setInt(1);
  			p.fields[N2K.headingTrackControl.vesselHeading].setDecimal(Math.PI);
  			break;
  		}
  	}
  	catch (Exception ex)
  	{
  		Trace.stack(ex, "Error building packet " + pgn);
  	}

    return(p);
  }

  void updateParameters(long curTime)
  {
    if ((curTime - lastUpdate) < 200) return;
    // Move the yacht a little at a time
    tid = randomize(targettid, tid, 4.0, 1000);
    tda = randomizeAngle(targettda, tda, 0.1, 30);
    hdg = randomizeAngle(targethdg, hdg, 0.3, 10);
    if (state == GUI.Autohelm.STANDBY)
    {
    	targethdg = hdg;
    }
    spd = randomize(targetspd, spd, 2, 5);
    wa = randomizeAngle(targetwa, wa, 1, 20);
    ws = randomize(targetws, ws, 5, 1);
    dep = randomize(targetdep, dep, 90, 100);

    if (lastTime != 0)
    {
      // Calculate sog and cog from heading, speed and tides
      double hdgvx = Math.sin(hdg) * spd;
      double tidvx = Math.sin(tda) * tid;
      double hdgvy = Math.cos(hdg) * spd;
      double tidvy = Math.cos(tda) * tid;
      Trace.normal("Heading  " + hdg + "(" + Utils.radstodegs(hdg) + ") speed " + spd + " vx " + hdgvx + " vy " + hdgvy);
      Trace.normal("Tide     " + tda + "(" + Utils.radstodegs(tda) + ") speed " + tid + " vx " + tidvx + " vy " + tidvy);
      hdgvx += tidvx;
      hdgvy += tidvy;
      //Trace.debug("Combined  vx " + hdgvx + " vy " + hdgvy);
      sog = Math.sqrt((hdgvx*hdgvx) + (hdgvy*hdgvy));
      cog = Math.atan(hdgvx/hdgvy);       // -90 to 90
      if (hdgvx < 0)
      {
        if (hdgvy > 0)
        {
          cog += Math.PI * 2;
        }
        else
        {
          cog += Math.PI;
        }
      }
      else
      {
        if (hdgvy < 0)
        {
          cog += Math.PI;
        }
      }
      Trace.normal("SOG and COG calculated as " + sog + "," + Utils.radstodegs(cog) + ")");
      double timeDelta = ((double)(curTime - lastTime))/1000;
      Trace.normal("Time delta is " + timeDelta);
      Trace.normal("Distance travelled is " + (hdgvx * timeDelta) + "," + (hdgvy * timeDelta) + ") metres");
      lat = lat + Utils.metresToLat(hdgvy * timeDelta);
      lng = lng + Utils.metresToLong(hdgvx * timeDelta, lat);   
    }

    lastTime = curTime;
  }

  // Function to give a value that varies around a given point in a given time period
  // To simulate wind for example we use 8m/s +- 3m/s over a fast period
  double randomize(double target, double current, double variance, int rate)
  {
    double res = current + ((Math.random() - 0.5) * variance / rate) + ((target - current)/rate/2);
    //Trace.alert("Target " + target + " current " + current + 
    //                  " variance " + variance + " rate " + rate + " gives " + res);
    if (res < 0) res = 0;
    return(res);
  }

  double randomizeAngle(double target, double current, double variance, int rate)
  {
    double res = current + ((Math.random() - 0.5) * variance / rate) + 
                 (GUIUtils.normalizeAngle180(target - current)/rate/2);
    //Trace.alert("Target " + target + " current " + current + 
    //                  " variance " + variance + " rate " + rate + " gives " + res);
    res = GUIUtils.normalizeAngle360(res);
    return(res);
  }

  void loadYachtParms(Props props)
  {
    lat = GUIUtils.getBigDecimalValue(props, Props.sLat, 49.5);
    lng = GUIUtils.getBigDecimalValue(props, Props.sLng, -4.5);
    targetspd = GUIUtils.getBigDecimalValue(props, Props.sSpd, 6.0) * 0.5144;
    targetdep = GUIUtils.getBigDecimalValue(props, Props.sDep, 100.0);
    targethdg = Utils.degstorads(GUIUtils.getBigDecimalValue(props, Props.sHdg, 270.0));
    targettid = GUIUtils.getBigDecimalValue(props, Props.sTid, 1);
    targettda = Utils.degstorads(GUIUtils.getBigDecimalValue(props, Props.sTda, 0.0));
    targetws  = GUIUtils.getBigDecimalValue(props, Props.sWs, 15.0) * 0.5144;
    targetwa  = Utils.degstorads(GUIUtils.getBigDecimalValue(props, Props.sWa, 100.0));
    spd = targetspd;
    dep = targetdep;
    cog = targethdg;
    ws = targetws;
    wa = targetwa;
    sog = targetspd;
    tid = targettid;
    tda = targettda;
  }

  void handleNavMsg(N2KPacket packet) throws Exception
  {
    navBearing = packet.fields[N2K.navigationData.bearingPositionToDestinationWaypoint].getDecimal();
    Trace.alert("Autohelm on and got nav bearing " + navBearing);
  }

  void handleXTE(N2KPacket packet) throws Exception
  {
    long curTime = System.currentTimeMillis();
    double timedelta = curTime - lastXteTime;     
    if (timedelta > 10000)
    {
      // Handle startup condition
      lastXteTime = curTime;
      return;
    }

    Trace.normal("Autohelm on and got XTE");
    navigating = (packet.fields[N2K.crossTrackError.navigationTerminated].getInt() != 
                                       N2K.crossTrackError.navigationTerminated_values.Yes);
    if (!navigating || (state != GUI.Autohelm.TRACK))
    {
      Trace.normal("Plotter is not following a track");
    }
    else
    {
      if (navBearing == -1)
      {
        Trace.alert("No navigation data yet");
      }
      else
      {
        // We calculate the target angle X from  sog*sin(X-bearing to waypoint) = min(K * xte, sog*.9)
        // so X = asin(Math.min(K*xte, sog*.9)/sog) + bearing to waypoint
        // Then we apply a small change to the target heading to make it match this value 
        double xte = packet.fields[N2K.crossTrackError.xte].getDecimal();
        double xteDelta = xte - lastDeltaXte;
        //Trace.debug("Time since last XTE " + timedelta);
        if (xteDelta != 0)
        {
          //Trace.debug("Got an XTE delta, time since last " + (curTime - lastDeltaXteTime));
          if (lastDeltaXteTime > 0)
          {
            // Work out rate of change of XTE so we can get an intermediate value
            xtv = xteDelta / (curTime - lastDeltaXteTime);
            //Trace.debug("XTE delta " + xteDelta + " over " + (curTime - lastDeltaXteTime) + 
            //           "ms, cross track rate of change is " + xtv);
          }
          lastDeltaXte = xte;
          lastDeltaXteTime = curTime;
        }
        else
        {
          // Set the xte to be an intermediate value
          xte = lastXte + (xtv * timedelta);
        }

        //Trace.debug("Navigating to " + ((int)(Utils.radstodegs(navBearing))) +
        //            " current heading " + ((int)(Utils.radstodegs(targethdg))) +
        //            " XTE is " + xte);
        double cta = GUIUtils.normalizeAngle180(targethdg - navBearing);
        double targetChange;
        if (Math.abs(cta) > Math.PI/2)
        {
          Trace.normal("CTA is " + Utils.radstodegs(cta) + 
                       ", more than 90 degrees off - just turning towards objective");
          targetChange = -(cta /10); // - (Math.min(xte,100)/1000);
          if (targetChange > 0.2) targetChange = 0.2;
          if (targetChange < -0.2) targetChange = -0.2;
        }
        else
        {
          int sign = 1;
          if (xte < 0) sign = -1;
          double kxte = sign*Math.log(1 + Math.abs(xte)/10/sog);
          //double kxte = xte/5/sog;
          if (xte > 0)
          {
            if (kxte > 0.9) kxte = 0.9;
          }
          else
          {
            if (kxte < -0.9) kxte = -0.9;
          }
          //Trace.debug("Sog " + sog + " xte / sog / 5 is " + (-kxte) + 
          //                    " arcsin is " + Math.asin(-kxte));
          double newTarget = GUIUtils.normalizeAngle360(Math.asin(-kxte) + navBearing);
          targetChange   = GUIUtils.normalizeAngle180(newTarget - targethdg);
          Trace.normal("Xte is " + xte +  " new target heading " + Utils.radstodegs(newTarget) + 
                       " target change is " + Utils.radstodegs(targetChange) + " degrees");
          if (targetChange > 0.2) targetChange = 0.2;
          if (targetChange < -0.2) targetChange = -0.2;
        }

        Trace.normal("XTE now " + xte + " target change is " + Utils.radstodegs(targetChange) + " degrees");
        targethdg = GUIUtils.normalizeAngle360(targethdg + targetChange);
        Trace.normal("Target heading is now " + Utils.radstodegs(targethdg) + " degrees");
        lastXte = xte;
      }
    }
    lastXteTime = curTime;
  }

  boolean autoHelmSwitch(GUI.Autohelm state)
  {
    lastXte = 0.0;
    navBearing = -1.0;
    this.state = state;
    return(navigating);
  }
  
  void adjustHeading(int degrees)
  {
  	targethdg = GUIUtils.normalizeAngle360(targethdg + Utils.degstorads(degrees));
  }
}
