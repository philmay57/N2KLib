package N2KMsgs;

public class N2K
{
  // PGN(59392) - ISO Acknowledgement
  public final static int isoAcknowledgement_pgn = 59392;
  public final static class isoAcknowledgement
  {
    public final static int control = 0;
    // control permitted values
    public static final class control_values
    {
      public static final int ACK = 0;
      public static final int NAK = 1;
      public static final int AccessDenied = 2;
      public static final int AddressBusy = 3;
    }

    public final static int groupFunction = 1;
    // Reserved
    public final static int reserved = 2;
    // Parameter Group Number of requested information
    public final static int pgn = 3;
  }

  // PGN(59904) - ISO Request
  public final static int isoRequest_pgn = 59904;
  public final static class isoRequest
  {
    public final static int pgn = 0;
  }

  // PGN(60160) - ISO Transport Protocol, Data Transfer
  public final static int isoTransportProtocolDataTransfer_pgn = 60160;
  public final static class isoTransportProtocolDataTransfer
  {
    public final static int sid = 0;
    public final static class rep
    {
      public final static int data = 0;
    }
  }

  // PGN(60416) - ISO Transport Protocol, Connection Management - Request To Send
  public final static int isoTransportProtocolConnectionManagementRequestToSend_pgn = 60416;
  public final static class isoTransportProtocolConnectionManagementRequestToSend
  {
    // RTS
    public final static int groupFunctionCode = 0;
    // bytes
    public final static int messageSize = 1;
    // packets
    public final static int packets = 2;
    // packets sent in response to CTS
    public final static int packetsReply = 3;
    // PGN
    public final static class rep
    {
      public final static int pgn = 0;
    }
  }

  // PGN(60416) - ISO Transport Protocol, Connection Management - Clear To Send
  public final static int isoTransportProtocolConnectionManagementClearToSend_pgn = 60416;
  public final static class isoTransportProtocolConnectionManagementClearToSend
  {
    // CTS
    public final static int groupFunctionCode = 0;
    // packets before waiting for next CTS
    public final static int maxPackets = 1;
    // packet
    public final static int nextSid = 2;
    public final static int reserved = 3;
    // PGN
    public final static class rep
    {
      public final static int pgn = 0;
    }
  }

  // PGN(60416) - ISO Transport Protocol, Connection Management - End Of Message
  public final static int isoTransportProtocolConnectionManagementEndOfMessage_pgn = 60416;
  public final static class isoTransportProtocolConnectionManagementEndOfMessage
  {
    // EOM
    public final static int groupFunctionCode = 0;
    // bytes
    public final static int totalMessageSize = 1;
    // packets
    public final static int totalNumberOfPacketsReceived = 2;
    public final static int reserved = 3;
    // PGN
    public final static class rep
    {
      public final static int pgn = 0;
    }
  }

  // PGN(60416) - ISO Transport Protocol, Connection Management - Broadcast Announce
  public final static int isoTransportProtocolConnectionManagementBroadcastAnnounce_pgn = 60416;
  public final static class isoTransportProtocolConnectionManagementBroadcastAnnounce
  {
    // BAM
    public final static int groupFunctionCode = 0;
    // bytes
    public final static int messageSize = 1;
    // frames
    public final static int packets = 2;
    public final static int reserved = 3;
    // PGN
    public final static class rep
    {
      public final static int pgn = 0;
    }
  }

  // PGN(60416) - ISO Transport Protocol, Connection Management - Abort
  public final static int isoTransportProtocolConnectionManagementAbort_pgn = 60416;
  public final static class isoTransportProtocolConnectionManagementAbort
  {
    // Abort
    public final static int groupFunctionCode = 0;
    public final static int reason = 1;
    public final static int reserved = 2;
    // PGN
    public final static class rep
    {
      public final static int pgn = 0;
    }
  }

  // PGN(60928) - ISO Address Claim
  public final static int isoAddressClaim_pgn = 60928;
  public final static class isoAddressClaim
  {
    // ISO Identity Number
    public final static int uniqueNumber = 0;
    public final static int manufacturerCode = 1;
    // ISO ECU Instance
    public final static int deviceInstanceLower = 2;
    // ISO Function Instance
    public final static int deviceInstanceUpper = 3;
    // ISO Function
    public final static int deviceFunction = 4;
    public final static int reserved = 5;
    public final static int deviceClass = 6;
    // deviceClass permitted values
    public static final class deviceClass_values
    {
      public static final int Reservedfor2000Use = 0;
      public static final int Systemtools = 10;
      public static final int Safetysystems = 20;
      public static final int Internetworkdevice = 25;
      public static final int ElectricalDistribution = 30;
      public static final int ElectricalGeneration = 35;
      public static final int SteeringandControlsurfaces = 40;
      public static final int Propulsion = 50;
      public static final int Navigation = 60;
      public static final int Communication = 70;
      public static final int SensorCommunicationInterface = 75;
      public static final int Instrumentation_generalsystems = 80;
      public static final int ExternalEnvironment = 85;
      public static final int InternalEnvironment = 90;
      public static final int Deck_cargo_fishingequipmentsystems = 100;
      public static final int Display = 120;
      public static final int Entertainment = 125;
    }

    // ISO Device Class Instance
    public final static int systemInstance = 7;
    public final static int industryGroup = 8;
    // industryGroup permitted values
    public static final class industryGroup_values
    {
      public static final int Global = 0;
      public static final int Highway = 1;
      public static final int Agriculture = 2;
      public static final int Construction = 3;
      public static final int Marine = 4;
      public static final int Industrial = 5;
    }

    // ISO Self Configurable
    public final static int reserved2 = 9;
  }

  // PGN(61184) - Seatalk: Wireless Keypad Light Control
  public final static int seatalkWirelessKeypadLightControl_pgn = 61184;
  public final static class seatalkWirelessKeypadLightControl
  {
    // Raymarine
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Wireless Keypad Light Control
    public final static int proprietaryId = 3;
    public final static int variant = 4;
    public final static int wirelessSetting = 5;
    public final static int wiredSetting = 6;
  }

  // PGN(61184) - Seatalk: Wireless Keypad Light Control
  public final static int seatalkWirelessKeypadLightControl2_pgn = 61184;
  public final static class seatalkWirelessKeypadLightControl2
  {
    // Raymarine
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int pid = 3;
    public final static int variant = 4;
    public final static int beepControl = 5;
  }

  // PGN(61184) - Victron Battery Register
  public final static int victronBatteryRegister_pgn = 61184;
  public final static class victronBatteryRegister
  {
    // Victron
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int registerId = 3;
    public final static int payload = 4;
  }

  // PGN(61184) - Manufacturer Proprietary single-frame addressed
  public final static int manufacturerProprietarySingleFrameAddressed_pgn = 61184;
  public final static class manufacturerProprietarySingleFrameAddressed
  {
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    public final static int industryCode = 2;
    // industryCode permitted values
    public static final class industryCode_values
    {
      public static final int Global = 0;
      public static final int Highway = 1;
      public static final int Agriculture = 2;
      public static final int Construction = 3;
      public static final int Marine = 4;
      public static final int Industrial = 5;
    }

    public final static int data = 3;
  }

  // PGN(61440) - Unknown single-frame non-addressed
  public final static int unknownSingleFrameNonAddressed_pgn = 61440;
  public final static class unknownSingleFrameNonAddressed
  {
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    public final static int industryCode = 2;
    // industryCode permitted values
    public static final class industryCode_values
    {
      public static final int Global = 0;
      public static final int Highway = 1;
      public static final int Agriculture = 2;
      public static final int Construction = 3;
      public static final int Marine = 4;
      public static final int Industrial = 5;
    }

    public final static int data = 3;
  }

  // PGN(65001) - Bus #1 Phase C Basic AC Quantities
  public final static int bus1PhaseCBasicAcQuantities_pgn = 65001;
  public final static class bus1PhaseCBasicAcQuantities
  {
    public final static int lineLineAcRmsVoltage = 0;
    public final static int lineNeutralAcRmsVoltage = 1;
    public final static int acFrequency = 2;
  }

  // PGN(65002) - Bus #1 Phase B Basic AC Quantities
  public final static int bus1PhaseBBasicAcQuantities_pgn = 65002;
  public final static class bus1PhaseBBasicAcQuantities
  {
    public final static int lineLineAcRmsVoltage = 0;
    public final static int lineNeutralAcRmsVoltage = 1;
    public final static int acFrequency = 2;
  }

  // PGN(65003) - Bus #1 Phase A Basic AC Quantities
  public final static int bus1PhaseABasicAcQuantities_pgn = 65003;
  public final static class bus1PhaseABasicAcQuantities
  {
    public final static int lineLineAcRmsVoltage = 0;
    public final static int lineNeutralAcRmsVoltage = 1;
    public final static int acFrequency = 2;
  }

  // PGN(65004) - Bus #1 Average Basic AC Quantities
  public final static int bus1AverageBasicAcQuantities_pgn = 65004;
  public final static class bus1AverageBasicAcQuantities
  {
    public final static int lineLineAcRmsVoltage = 0;
    public final static int lineNeutralAcRmsVoltage = 1;
    public final static int acFrequency = 2;
  }

  // PGN(65005) - Utility Total AC Energy
  public final static int utilityTotalAcEnergy_pgn = 65005;
  public final static class utilityTotalAcEnergy
  {
    public final static int totalEnergyExport = 0;
    public final static int totalEnergyImport = 1;
  }

  // PGN(65006) - Utility Phase C AC Reactive Power
  public final static int utilityPhaseCAcReactivePower_pgn = 65006;
  public final static class utilityPhaseCAcReactivePower
  {
    public final static int reactivePower = 0;
    public final static int powerFactor = 1;
    public final static int powerFactorLagging = 2;
    // powerFactorLagging permitted values
    public static final class powerFactorLagging_values
    {
      public static final int Leading = 0;
      public static final int Lagging = 1;
      public static final int Error = 2;
    }

  }

  // PGN(65007) - Utility Phase C AC Power
  public final static int utilityPhaseCAcPower_pgn = 65007;
  public final static class utilityPhaseCAcPower
  {
    public final static int realPower = 0;
    public final static int apparentPower = 1;
  }

  // PGN(65008) - Utility Phase C Basic AC Quantities
  public final static int utilityPhaseCBasicAcQuantities_pgn = 65008;
  public final static class utilityPhaseCBasicAcQuantities
  {
    public final static int lineLineAcRmsVoltage = 0;
    public final static int lineNeutralAcRmsVoltage = 1;
    public final static int acFrequency = 2;
    public final static int acRmsCurrent = 3;
  }

  // PGN(65009) - Utility Phase B AC Reactive Power
  public final static int utilityPhaseBAcReactivePower_pgn = 65009;
  public final static class utilityPhaseBAcReactivePower
  {
    public final static int reactivePower = 0;
    public final static int powerFactor = 1;
    public final static int powerFactorLagging = 2;
    // powerFactorLagging permitted values
    public static final class powerFactorLagging_values
    {
      public static final int Leading = 0;
      public static final int Lagging = 1;
      public static final int Error = 2;
    }

  }

  // PGN(65010) - Utility Phase B AC Power
  public final static int utilityPhaseBAcPower_pgn = 65010;
  public final static class utilityPhaseBAcPower
  {
    public final static int realPower = 0;
    public final static int apparentPower = 1;
  }

  // PGN(65011) - Utility Phase B Basic AC Quantities
  public final static int utilityPhaseBBasicAcQuantities_pgn = 65011;
  public final static class utilityPhaseBBasicAcQuantities
  {
    public final static int lineLineAcRmsVoltage = 0;
    public final static int lineNeutralAcRmsVoltage = 1;
    public final static int acFrequency = 2;
    public final static int acRmsCurrent = 3;
  }

  // PGN(65012) - Utility Phase A AC Reactive Power
  public final static int utilityPhaseAAcReactivePower_pgn = 65012;
  public final static class utilityPhaseAAcReactivePower
  {
    public final static int reactivePower = 0;
    public final static int powerFactor = 1;
    public final static int powerFactorLagging = 2;
    // powerFactorLagging permitted values
    public static final class powerFactorLagging_values
    {
      public static final int Leading = 0;
      public static final int Lagging = 1;
      public static final int Error = 2;
    }

  }

  // PGN(65013) - Utility Phase A AC Power
  public final static int utilityPhaseAAcPower_pgn = 65013;
  public final static class utilityPhaseAAcPower
  {
    public final static int realPower = 0;
    public final static int apparentPower = 1;
  }

  // PGN(65014) - Utility Phase A Basic AC Quantities
  public final static int utilityPhaseABasicAcQuantities_pgn = 65014;
  public final static class utilityPhaseABasicAcQuantities
  {
    public final static int lineLineAcRmsVoltage = 0;
    public final static int lineNeutralAcRmsVoltage = 1;
    public final static int acFrequency = 2;
    public final static int acRmsCurrent = 3;
  }

  // PGN(65015) - Utility Total AC Reactive Power
  public final static int utilityTotalAcReactivePower_pgn = 65015;
  public final static class utilityTotalAcReactivePower
  {
    public final static int reactivePower = 0;
    public final static int powerFactor = 1;
    public final static int powerFactorLagging = 2;
    // powerFactorLagging permitted values
    public static final class powerFactorLagging_values
    {
      public static final int Leading = 0;
      public static final int Lagging = 1;
      public static final int Error = 2;
    }

  }

  // PGN(65016) - Utility Total AC Power
  public final static int utilityTotalAcPower_pgn = 65016;
  public final static class utilityTotalAcPower
  {
    public final static int realPower = 0;
    public final static int apparentPower = 1;
  }

  // PGN(65017) - Utility Average Basic AC Quantities
  public final static int utilityAverageBasicAcQuantities_pgn = 65017;
  public final static class utilityAverageBasicAcQuantities
  {
    public final static int lineLineAcRmsVoltage = 0;
    public final static int lineNeutralAcRmsVoltage = 1;
    public final static int acFrequency = 2;
    public final static int acRmsCurrent = 3;
  }

  // PGN(65018) - Generator Total AC Energy
  public final static int generatorTotalAcEnergy_pgn = 65018;
  public final static class generatorTotalAcEnergy
  {
    public final static int totalEnergyExport = 0;
    public final static int totalEnergyImport = 1;
  }

  // PGN(65019) - Generator Phase C AC Reactive Power
  public final static int generatorPhaseCAcReactivePower_pgn = 65019;
  public final static class generatorPhaseCAcReactivePower
  {
    public final static int reactivePower = 0;
    public final static int powerFactor = 1;
    public final static int powerFactorLagging = 2;
    // powerFactorLagging permitted values
    public static final class powerFactorLagging_values
    {
      public static final int Leading = 0;
      public static final int Lagging = 1;
      public static final int Error = 2;
    }

  }

  // PGN(65020) - Generator Phase C AC Power
  public final static int generatorPhaseCAcPower_pgn = 65020;
  public final static class generatorPhaseCAcPower
  {
    public final static int realPower = 0;
    public final static int apparentPower = 1;
  }

  // PGN(65021) - Generator Phase C Basic AC Quantities
  public final static int generatorPhaseCBasicAcQuantities_pgn = 65021;
  public final static class generatorPhaseCBasicAcQuantities
  {
    public final static int lineLineAcRmsVoltage = 0;
    public final static int lineNeutralAcRmsVoltage = 1;
    public final static int acFrequency = 2;
    public final static int acRmsCurrent = 3;
  }

  // PGN(65022) - Generator Phase B AC Reactive Power
  public final static int generatorPhaseBAcReactivePower_pgn = 65022;
  public final static class generatorPhaseBAcReactivePower
  {
    public final static int reactivePower = 0;
    public final static int powerFactor = 1;
    public final static int powerFactorLagging = 2;
    // powerFactorLagging permitted values
    public static final class powerFactorLagging_values
    {
      public static final int Leading = 0;
      public static final int Lagging = 1;
      public static final int Error = 2;
    }

  }

  // PGN(65023) - Generator Phase B AC Power
  public final static int generatorPhaseBAcPower_pgn = 65023;
  public final static class generatorPhaseBAcPower
  {
    public final static int realPower = 0;
    public final static int apparentPower = 1;
  }

  // PGN(65024) - Generator Phase B Basic AC Quantities
  public final static int generatorPhaseBBasicAcQuantities_pgn = 65024;
  public final static class generatorPhaseBBasicAcQuantities
  {
    public final static int lineLineAcRmsVoltage = 0;
    public final static int lineNeutralAcRmsVoltage = 1;
    public final static int acFrequency = 2;
    public final static int acRmsCurrent = 3;
  }

  // PGN(65025) - Generator Phase A AC Reactive Power
  public final static int generatorPhaseAAcReactivePower_pgn = 65025;
  public final static class generatorPhaseAAcReactivePower
  {
    public final static int reactivePower = 0;
    public final static int powerFactor = 1;
    public final static int powerFactorLagging = 2;
    // powerFactorLagging permitted values
    public static final class powerFactorLagging_values
    {
      public static final int Leading = 0;
      public static final int Lagging = 1;
      public static final int Error = 2;
    }

  }

  // PGN(65026) - Generator Phase A AC Power
  public final static int generatorPhaseAAcPower_pgn = 65026;
  public final static class generatorPhaseAAcPower
  {
    public final static int realPower = 0;
    public final static int apparentPower = 1;
  }

  // PGN(65027) - Generator Phase A Basic AC Quantities
  public final static int generatorPhaseABasicAcQuantities_pgn = 65027;
  public final static class generatorPhaseABasicAcQuantities
  {
    public final static int lineLineAcRmsVoltage = 0;
    public final static int lineNeutralAcRmsVoltage = 1;
    public final static int acFrequency = 2;
    public final static int acRmsCurrent = 3;
  }

  // PGN(65028) - Generator Total AC Reactive Power
  public final static int generatorTotalAcReactivePower_pgn = 65028;
  public final static class generatorTotalAcReactivePower
  {
    public final static int reactivePower = 0;
    public final static int powerFactor = 1;
    public final static int powerFactorLagging = 2;
    // powerFactorLagging permitted values
    public static final class powerFactorLagging_values
    {
      public static final int Leading = 0;
      public static final int Lagging = 1;
      public static final int Error = 2;
    }

  }

  // PGN(65029) - Generator Total AC Power
  public final static int generatorTotalAcPower_pgn = 65029;
  public final static class generatorTotalAcPower
  {
    public final static int realPower = 0;
    public final static int apparentPower = 1;
  }

  // PGN(65030) - Generator Average Basic AC Quantities
  public final static int generatorAverageBasicAcQuantities_pgn = 65030;
  public final static class generatorAverageBasicAcQuantities
  {
    public final static int lineLineAcRmsVoltage = 0;
    public final static int lineNeutralAcRmsVoltage = 1;
    public final static int acFrequency = 2;
    public final static int acRmsCurrent = 3;
  }

  // PGN(65240) - ISO Commanded Address
  public final static int isoCommandedAddress_pgn = 65240;
  public final static class isoCommandedAddress
  {
    // ISO Identity Number
    public final static int uniqueNumber = 0;
    public final static int manufacturerCode = 1;
    // ISO ECU Instance
    public final static int deviceInstanceLower = 2;
    // ISO Function Instance
    public final static int deviceInstanceUpper = 3;
    // ISO Function
    public final static int deviceFunction = 4;
    public final static int reserved = 5;
    public final static int deviceClass = 6;
    // deviceClass permitted values
    public static final class deviceClass_values
    {
      public static final int Reservedfor2000Use = 0;
      public static final int Systemtools = 10;
      public static final int Safetysystems = 20;
      public static final int Internetworkdevice = 25;
      public static final int ElectricalDistribution = 30;
      public static final int ElectricalGeneration = 35;
      public static final int SteeringandControlsurfaces = 40;
      public static final int Propulsion = 50;
      public static final int Navigation = 60;
      public static final int Communication = 70;
      public static final int SensorCommunicationInterface = 75;
      public static final int Instrumentation_generalsystems = 80;
      public static final int ExternalEnvironment = 85;
      public static final int InternalEnvironment = 90;
      public static final int Deck_cargo_fishingequipmentsystems = 100;
      public static final int Display = 120;
      public static final int Entertainment = 125;
    }

    // ISO Device Class Instance
    public final static int systemInstance = 7;
    public final static int industryCode = 8;
    // industryCode permitted values
    public static final class industryCode_values
    {
      public static final int Global = 0;
      public static final int Highway = 1;
      public static final int Agriculture = 2;
      public static final int Construction = 3;
      public static final int Marine = 4;
      public static final int Industrial = 5;
    }

    // ISO Self Configurable
    public final static int reserved2 = 9;
    public final static int newSourceAddress = 10;
  }

  // PGN(65280) - Manufacturer Proprietary single-frame non-addressed
  public final static int manufacturerProprietarySingleFrameNonAddressed_pgn = 65280;
  public final static class manufacturerProprietarySingleFrameNonAddressed
  {
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    public final static int industryCode = 2;
    // industryCode permitted values
    public static final class industryCode_values
    {
      public static final int Global = 0;
      public static final int Highway = 1;
      public static final int Agriculture = 2;
      public static final int Construction = 3;
      public static final int Marine = 4;
      public static final int Industrial = 5;
    }

    public final static int data = 3;
  }

  // PGN(65285) - Airmar: Boot State Acknowledgment
  public final static int airmarBootStateAcknowledgment_pgn = 65285;
  public final static class airmarBootStateAcknowledgment
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int bootState = 3;
    // bootState permitted values
    public static final class bootState_values
    {
      public static final int inStartupMonitor = 0;
      public static final int runningBootloader = 1;
      public static final int runningApplication = 2;
    }

  }

  // PGN(65285) - Lowrance: Temperature
  public final static int lowranceTemperature_pgn = 65285;
  public final static class lowranceTemperature
  {
    // Lowrance
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int ftemperatureSource = 3;
    // ftemperatureSource permitted values
    public static final class ftemperatureSource_values
    {
      public static final int SeaTemperature = 0;
      public static final int OutsideTemperature = 1;
      public static final int InsideTemperature = 2;
      public static final int EngineRoomTemperature = 3;
      public static final int MainCabinTemperature = 4;
      public static final int LiveWellTemperature = 5;
      public static final int BaitWellTemperature = 6;
      public static final int RefridgerationTemperature = 7;
      public static final int HeatingSystemTemperature = 8;
      public static final int DewPointTemperature = 9;
      public static final int ApparentWindChillTemperature = 10;
      public static final int TheoreticalWindChillTemperature = 11;
      public static final int HeatIndexTemperature = 12;
      public static final int FreezerTemperature = 13;
      public static final int ExhaustGasTemperature = 14;
    }

    public final static int actualTemperature = 4;
  }

  // PGN(65286) - Airmar: Boot State Request
  public final static int airmarBootStateRequest_pgn = 65286;
  public final static class airmarBootStateRequest
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(65287) - Airmar: Access Level
  public final static int airmarAccessLevel_pgn = 65287;
  public final static class airmarAccessLevel
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int formatCode = 3;
    // formatCode permitted values
    public static final class formatCode_values
    {
      public static final int Formatcode1 = 1;
    }

    public final static int accessLevel = 4;
    // accessLevel permitted values
    public static final class accessLevel_values
    {
      public static final int Locked = 0;
      public static final int unlockedlevel1 = 1;
      public static final int unlockedlevel2 = 2;
    }

    public final static int reserved2 = 5;
    // When transmitted, it provides a seed for an unlock operation. It is used to provide the key during PGN 126208.
    public final static int accessSeedKey = 6;
  }

  // PGN(65287) - Simnet: Configure Temperature Sensor
  public final static int simnetConfigureTemperatureSensor_pgn = 65287;
  public final static class simnetConfigureTemperatureSensor
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(65288) - Seatalk: Alarm
  public final static int seatalkAlarm_pgn = 65288;
  public final static class seatalkAlarm
  {
    // Raymarine
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int sid = 3;
    public final static int alarmStatus = 4;
    // alarmStatus permitted values
    public static final class alarmStatus_values
    {
      public static final int Alarmconditionnotmet = 0;
      public static final int Alarmconditionmetandnotsilenced = 1;
      public static final int Alarmconditionmetandsilenced = 2;
    }

    public final static int alarmId = 5;
    // alarmId permitted values
    public static final class alarmId_values
    {
      public static final int NoAlarm = 0;
      public static final int ShallowDepth = 1;
      public static final int DeepDepth = 2;
      public static final int ShallowAnchor = 3;
      public static final int DeepAnchor = 4;
      public static final int OffCourse = 5;
      public static final int AWAHigh = 6;
      public static final int AWALow = 7;
      public static final int AWSHigh = 8;
      public static final int AWSLow = 9;
      public static final int TWAHigh = 10;
      public static final int TWALow = 11;
      public static final int TWSHigh = 12;
      public static final int TWSLow = 13;
      public static final int WPArrival = 14;
      public static final int BoatSpeedHigh = 15;
      public static final int BoatSpeedLow = 16;
      public static final int SeaTempHigh = 17;
      public static final int SeaTempLow = 18;
      public static final int PilotWatch = 19;
      public static final int PilotOffCourse = 20;
      public static final int PilotWindShift = 21;
      public static final int PilotLowBattery = 22;
      public static final int PilotLastMinuteOfWatch = 23;
      public static final int PilotNoNMEAData = 24;
      public static final int PilotLargeXTE = 25;
      public static final int PilotNMEADataError = 26;
      public static final int PilotCUDisconnected = 27;
      public static final int PilotAutoRelease = 28;
      public static final int PilotWayPointAdvance = 29;
      public static final int PilotDriveStopped = 30;
      public static final int PilotTypeUnspecified = 31;
      public static final int PilotCalibrationRequired = 32;
      public static final int PilotLastHeading = 33;
      public static final int PilotNoPilot = 34;
      public static final int PilotRouteComplete = 35;
      public static final int PilotVariableText = 36;
      public static final int GPSFailure = 37;
      public static final int MOB = 38;
      public static final int Seatalk1Anchor = 39;
      public static final int PilotSwappedMotorPower = 40;
      public static final int PilotStandbyTooFastToFish = 41;
      public static final int PilotNoGPSFix = 42;
      public static final int PilotNoGPSCOG = 43;
      public static final int PilotStartUp = 44;
      public static final int PilotTooSlow = 45;
      public static final int PilotNoCompass = 46;
      public static final int PilotRateGyroFault = 47;
      public static final int PilotCurrentLimit = 48;
      public static final int PilotWayPointAdvancePort = 49;
      public static final int PilotWayPointAdvanceStbd = 50;
      public static final int PilotNoWindData = 51;
      public static final int PilotNoSpeedData = 52;
      public static final int PilotSeatalkFail1 = 53;
      public static final int PilotSeatalkFail2 = 54;
      public static final int PilotWarningTooFastToFish = 55;
      public static final int PilotAutoDocksideFail = 56;
      public static final int PilotTurnTooFast = 57;
      public static final int PilotNoNavData = 58;
      public static final int PilotLostWaypointData = 59;
      public static final int PilotEEPROMCorrupt = 60;
      public static final int PilotRudderFeedbackFail = 61;
      public static final int PilotAutolearnFail1 = 62;
      public static final int PilotAutolearnFail2 = 63;
      public static final int PilotAutolearnFail3 = 64;
      public static final int PilotAutolearnFail4 = 65;
      public static final int PilotAutolearnFail5 = 66;
      public static final int PilotAutolearnFail6 = 67;
      public static final int PilotWarningCalRequired = 68;
      public static final int PilotWarningOffCourse = 69;
      public static final int PilotWarningXTE = 70;
      public static final int PilotWarningWindShift = 71;
      public static final int PilotWarningDriveShort = 72;
      public static final int PilotWarningClutchShort = 73;
      public static final int PilotWarningSolenoidShort = 74;
      public static final int PilotJoystickFault = 75;
      public static final int PilotNoJoystickData = 76;
      public static final int notassigned = 77;
      public static final int notassigned2 = 78;
      public static final int notassigned3 = 79;
      public static final int PilotInvalidCommand = 80;
      public static final int AISTXMalfunction = 81;
      public static final int AISAntennaVSWRfault = 82;
      public static final int AISRxchannel1malfunction = 83;
      public static final int AISRxchannel2malfunction = 84;
      public static final int AISNosensorpositioninuse = 85;
      public static final int AISNovalidSOGinformation = 86;
      public static final int AISNovalidCOGinformation = 87;
      public static final int AIS12Valarm = 88;
      public static final int AIS6Valarm = 89;
      public static final int AISNoisethresholdexceededchannelA = 90;
      public static final int AISNoisethresholdexceededchannelB = 91;
      public static final int AISTransmitterPAfault = 92;
      public static final int AIS3V3alarm = 93;
      public static final int AISRxchannel70malfunction = 94;
      public static final int AISHeadinglost_invalid = 95;
      public static final int AISinternalGPSlost = 96;
      public static final int AISNosensorposition = 97;
      public static final int AISLockfailure = 98;
      public static final int AISInternalGGAtimeout = 99;
      public static final int AISProtocolstackrestart = 100;
      public static final int PilotNoIPScommunications = 101;
      public static final int PilotPower_OnorSleep_SwitchResetWhileEngaged = 102;
      public static final int PilotUnexpectedResetWhileEngaged = 103;
      public static final int AISDangerousTarget = 104;
      public static final int AISLostTarget = 105;
      public static final int AISSafetyRelatedMessage_usedtosilence_ = 106;
      public static final int AISConnectionLost = 107;
      public static final int NoFix = 108;
    }

    public final static int alarmGroup = 6;
    // alarmGroup permitted values
    public static final class alarmGroup_values
    {
      public static final int Instrument = 0;
      public static final int Autopilot = 1;
      public static final int Radar = 2;
      public static final int ChartPlotter = 3;
      public static final int AIS = 4;
    }

    public final static int alarmPriority = 7;
  }

  // PGN(65289) - Simnet: Trim Tab Sensor Calibration
  public final static int simnetTrimTabSensorCalibration_pgn = 65289;
  public final static class simnetTrimTabSensorCalibration
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(65290) - Simnet: Paddle Wheel Speed Configuration
  public final static int simnetPaddleWheelSpeedConfiguration_pgn = 65290;
  public final static class simnetPaddleWheelSpeedConfiguration
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(65292) - Simnet: Clear Fluid Level Warnings
  public final static int simnetClearFluidLevelWarnings_pgn = 65292;
  public final static class simnetClearFluidLevelWarnings
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(65293) - Simnet: LGC-2000 Configuration
  public final static int simnetLgc2000Configuration_pgn = 65293;
  public final static class simnetLgc2000Configuration
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(65309) - Navico: Wireless Battery Status
  public final static int navicoWirelessBatteryStatus_pgn = 65309;
  public final static class navicoWirelessBatteryStatus
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int status = 3;
    public final static int batteryStatus = 4;
    public final static int batteryChargeStatus = 5;
    public final static int reserved2 = 6;
  }

  // PGN(65312) - Navico: Wireless Signal Status
  public final static int navicoWirelessSignalStatus_pgn = 65312;
  public final static class navicoWirelessSignalStatus
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int unknown = 3;
    public final static int signalStrength = 4;
    public final static int reserved2 = 5;
  }

  // PGN(65325) - Simnet: Reprogram Status
  public final static int simnetReprogramStatus_pgn = 65325;
  public final static class simnetReprogramStatus
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(65341) - Simnet: Autopilot Mode
  public final static int simnetAutopilotMode_pgn = 65341;
  public final static class simnetAutopilotMode
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(65345) - Seatalk: Pilot Wind Datum
  public final static int seatalkPilotWindDatum_pgn = 65345;
  public final static class seatalkPilotWindDatum
  {
    // Raymarine
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int windDatum = 3;
    public final static int rollingAverageWindAngle = 4;
    public final static int reserved2 = 5;
  }

  // PGN(65359) - Seatalk: Pilot Heading
  public final static int seatalkPilotHeading_pgn = 65359;
  public final static class seatalkPilotHeading
  {
    // Raymarine
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int sid = 3;
    public final static int headingTrue = 4;
    public final static int headingMagnetic = 5;
    public final static int reserved2 = 6;
  }

  // PGN(65360) - Seatalk: Pilot Locked Heading
  public final static int seatalkPilotLockedHeading_pgn = 65360;
  public final static class seatalkPilotLockedHeading
  {
    // Raymarine
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int sid = 3;
    public final static int targetHeadingTrue = 4;
    public final static int targetHeadingMagnetic = 5;
    public final static int reserved2 = 6;
  }

  // PGN(65361) - Seatalk: Silence Alarm
  public final static int seatalkSilenceAlarm_pgn = 65361;
  public final static class seatalkSilenceAlarm
  {
    // Raymarine
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int alarmId = 3;
    // alarmId permitted values
    public static final class alarmId_values
    {
      public static final int NoAlarm = 0;
      public static final int ShallowDepth = 1;
      public static final int DeepDepth = 2;
      public static final int ShallowAnchor = 3;
      public static final int DeepAnchor = 4;
      public static final int OffCourse = 5;
      public static final int AWAHigh = 6;
      public static final int AWALow = 7;
      public static final int AWSHigh = 8;
      public static final int AWSLow = 9;
      public static final int TWAHigh = 10;
      public static final int TWALow = 11;
      public static final int TWSHigh = 12;
      public static final int TWSLow = 13;
      public static final int WPArrival = 14;
      public static final int BoatSpeedHigh = 15;
      public static final int BoatSpeedLow = 16;
      public static final int SeaTempHigh = 17;
      public static final int SeaTempLow = 18;
      public static final int PilotWatch = 19;
      public static final int PilotOffCourse = 20;
      public static final int PilotWindShift = 21;
      public static final int PilotLowBattery = 22;
      public static final int PilotLastMinuteOfWatch = 23;
      public static final int PilotNoNMEAData = 24;
      public static final int PilotLargeXTE = 25;
      public static final int PilotNMEADataError = 26;
      public static final int PilotCUDisconnected = 27;
      public static final int PilotAutoRelease = 28;
      public static final int PilotWayPointAdvance = 29;
      public static final int PilotDriveStopped = 30;
      public static final int PilotTypeUnspecified = 31;
      public static final int PilotCalibrationRequired = 32;
      public static final int PilotLastHeading = 33;
      public static final int PilotNoPilot = 34;
      public static final int PilotRouteComplete = 35;
      public static final int PilotVariableText = 36;
      public static final int GPSFailure = 37;
      public static final int MOB = 38;
      public static final int Seatalk1Anchor = 39;
      public static final int PilotSwappedMotorPower = 40;
      public static final int PilotStandbyTooFastToFish = 41;
      public static final int PilotNoGPSFix = 42;
      public static final int PilotNoGPSCOG = 43;
      public static final int PilotStartUp = 44;
      public static final int PilotTooSlow = 45;
      public static final int PilotNoCompass = 46;
      public static final int PilotRateGyroFault = 47;
      public static final int PilotCurrentLimit = 48;
      public static final int PilotWayPointAdvancePort = 49;
      public static final int PilotWayPointAdvanceStbd = 50;
      public static final int PilotNoWindData = 51;
      public static final int PilotNoSpeedData = 52;
      public static final int PilotSeatalkFail1 = 53;
      public static final int PilotSeatalkFail2 = 54;
      public static final int PilotWarningTooFastToFish = 55;
      public static final int PilotAutoDocksideFail = 56;
      public static final int PilotTurnTooFast = 57;
      public static final int PilotNoNavData = 58;
      public static final int PilotLostWaypointData = 59;
      public static final int PilotEEPROMCorrupt = 60;
      public static final int PilotRudderFeedbackFail = 61;
      public static final int PilotAutolearnFail1 = 62;
      public static final int PilotAutolearnFail2 = 63;
      public static final int PilotAutolearnFail3 = 64;
      public static final int PilotAutolearnFail4 = 65;
      public static final int PilotAutolearnFail5 = 66;
      public static final int PilotAutolearnFail6 = 67;
      public static final int PilotWarningCalRequired = 68;
      public static final int PilotWarningOffCourse = 69;
      public static final int PilotWarningXTE = 70;
      public static final int PilotWarningWindShift = 71;
      public static final int PilotWarningDriveShort = 72;
      public static final int PilotWarningClutchShort = 73;
      public static final int PilotWarningSolenoidShort = 74;
      public static final int PilotJoystickFault = 75;
      public static final int PilotNoJoystickData = 76;
      public static final int notassigned = 77;
      public static final int notassigned2 = 78;
      public static final int notassigned3 = 79;
      public static final int PilotInvalidCommand = 80;
      public static final int AISTXMalfunction = 81;
      public static final int AISAntennaVSWRfault = 82;
      public static final int AISRxchannel1malfunction = 83;
      public static final int AISRxchannel2malfunction = 84;
      public static final int AISNosensorpositioninuse = 85;
      public static final int AISNovalidSOGinformation = 86;
      public static final int AISNovalidCOGinformation = 87;
      public static final int AIS12Valarm = 88;
      public static final int AIS6Valarm = 89;
      public static final int AISNoisethresholdexceededchannelA = 90;
      public static final int AISNoisethresholdexceededchannelB = 91;
      public static final int AISTransmitterPAfault = 92;
      public static final int AIS3V3alarm = 93;
      public static final int AISRxchannel70malfunction = 94;
      public static final int AISHeadinglost_invalid = 95;
      public static final int AISinternalGPSlost = 96;
      public static final int AISNosensorposition = 97;
      public static final int AISLockfailure = 98;
      public static final int AISInternalGGAtimeout = 99;
      public static final int AISProtocolstackrestart = 100;
      public static final int PilotNoIPScommunications = 101;
      public static final int PilotPower_OnorSleep_SwitchResetWhileEngaged = 102;
      public static final int PilotUnexpectedResetWhileEngaged = 103;
      public static final int AISDangerousTarget = 104;
      public static final int AISLostTarget = 105;
      public static final int AISSafetyRelatedMessage_usedtosilence_ = 106;
      public static final int AISConnectionLost = 107;
      public static final int NoFix = 108;
    }

    public final static int alarmGroup = 4;
    // alarmGroup permitted values
    public static final class alarmGroup_values
    {
      public static final int Instrument = 0;
      public static final int Autopilot = 1;
      public static final int Radar = 2;
      public static final int ChartPlotter = 3;
      public static final int AIS = 4;
    }

    public final static int reserved2 = 5;
  }

  // PGN(65371) - Seatalk: Keypad Message
  public final static int seatalkKeypadMessage_pgn = 65371;
  public final static class seatalkKeypadMessage
  {
    // Raymarine
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int proprietaryId = 3;
    public final static int firstKey = 4;
    public final static int secondKey = 5;
    public final static int firstKeyState = 6;
    public final static int secondKeyState = 7;
    public final static int reserved2 = 8;
    public final static int encoderPosition = 9;
  }

  // PGN(65374) - SeaTalk: Keypad Heartbeat
  public final static int seatalkKeypadHeartbeat_pgn = 65374;
  public final static class seatalkKeypadHeartbeat
  {
    // Raymarine
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int proprietaryId = 3;
    public final static int variant = 4;
    public final static int status = 5;
  }

  // PGN(65379) - Seatalk: Pilot Mode
  public final static int seatalkPilotMode_pgn = 65379;
  public final static class seatalkPilotMode
  {
    // Raymarine
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int pilotMode = 3;
    public final static int subMode = 4;
    public final static int pilotModeData = 5;
    public final static int reserved2 = 6;
  }

  // PGN(65408) - Airmar: Depth Quality Factor
  public final static int airmarDepthQualityFactor_pgn = 65408;
  public final static class airmarDepthQualityFactor
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int sid = 3;
    public final static int depthQualityFactor = 4;
    // depthQualityFactor permitted values
    public static final class depthQualityFactor_values
    {
      public static final int NoDepthLock = 0;
    }

  }

  // PGN(65409) - Airmar: Speed Pulse Count
  public final static int airmarSpeedPulseCount_pgn = 65409;
  public final static class airmarSpeedPulseCount
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int sid = 3;
    public final static int durationOfInterval = 4;
    public final static int numberOfPulsesReceived = 5;
    public final static int reserved2 = 6;
  }

  // PGN(65410) - Airmar: Device Information
  public final static int airmarDeviceInformation_pgn = 65410;
  public final static class airmarDeviceInformation
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int sid = 3;
    public final static int internalDeviceTemperature = 4;
    public final static int supplyVoltage = 5;
    public final static int reserved2 = 6;
  }

  // PGN(65480) - Simnet: Autopilot Mode
  public final static int simnetAutopilotMode2_pgn = 65480;
  public final static class simnetAutopilotMode2
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(65536) - Unknown fast-packet addressed
  public final static int unknownFastPacketAddressed_pgn = 65536;
  public final static class unknownFastPacketAddressed
  {
    public final static int data = 0;
  }

  // PGN(126208) - NMEA - Request group function
  public final static int nmeaRequestGroupFunction_pgn = 126208;
  public final static class nmeaRequestGroupFunction
  {
    // Request
    public final static int functionCode = 0;
    // Requested PGN
    public final static int pgn = 1;
    public final static int transmissionInterval = 2;
    public final static int transmissionIntervalOffset = 3;
    // How many parameter pairs will follow
    public final static int OfParameters = 4;
    // Parameter index
    public final static class rep
    {
      public final static int parameter = 0;
      // Parameter value, variable length
      public final static int value = 1;
    }
  }

  // PGN(126208) - NMEA - Command group function
  public final static int nmeaCommandGroupFunction_pgn = 126208;
  public final static class nmeaCommandGroupFunction
  {
    // Command
    public final static int functionCode = 0;
    // Commanded PGN
    public final static int pgn = 1;
    public final static int priority = 2;
    public final static int reserved = 3;
    // How many parameter pairs will follow
    public final static int OfParameters = 4;
    // Parameter index
    public final static class rep
    {
      public final static int parameter = 0;
      // Parameter value, variable length
      public final static int value = 1;
    }
  }

  // PGN(126208) - NMEA - Acknowledge group function
  public final static int nmeaAcknowledgeGroupFunction_pgn = 126208;
  public final static class nmeaAcknowledgeGroupFunction
  {
    // Acknowledge
    public final static int functionCode = 0;
    // Commanded PGN
    public final static int pgn = 1;
    public final static int pgnErrorCode = 2;
    // pgnErrorCode permitted values
    public static final class pgnErrorCode_values
    {
      public static final int Acknowledge = 0;
      public static final int PGNnotsupported = 1;
      public static final int PGNnotavailable = 2;
      public static final int Accessdenied = 3;
      public static final int Notsupported = 4;
      public static final int Tagnotsupported = 5;
      public static final int ReadorWritenotsupported = 6;
    }

    public final static int transmissionIntervalPriorityErrorCode = 3;
    // transmissionIntervalPriorityErrorCode permitted values
    public static final class transmissionIntervalPriorityErrorCode_values
    {
      public static final int Acknowledge = 0;
      public static final int TransmitInterval_Prioritynotsupported = 1;
      public static final int TransmitIntervaltolow = 2;
      public static final int Accessdenied = 3;
      public static final int Notsupported = 4;
    }

    public final static int OfParameters = 4;
    public final static class rep
    {
      public final static int parameter = 0;
      // parameter permitted values
      public static final class parameter_values
      {
        public static final int Acknowledge = 0;
        public static final int Invalidparameterfield = 1;
        public static final int Temporaryerror = 2;
        public static final int Parameteroutofrange = 3;
        public static final int Accessdenied = 4;
        public static final int Notsupported = 5;
        public static final int ReadorWritenotsupported = 6;
      }

    }
  }

  // PGN(126208) - NMEA - Read Fields group function
  public final static int nmeaReadFieldsGroupFunction_pgn = 126208;
  public final static class nmeaReadFieldsGroupFunction
  {
    // Read Fields
    public final static int functionCode = 0;
    // Commanded PGN
    public final static int pgn = 1;
    public final static int manufacturerCode = 2;
    public final static int reserved = 3;
    public final static int industryCode = 4;
    // industryCode permitted values
    public static final class industryCode_values
    {
      public static final int Global = 0;
      public static final int Highway = 1;
      public static final int Agriculture = 2;
      public static final int Construction = 3;
      public static final int Marine = 4;
      public static final int Industrial = 5;
    }

    public final static int uniqueId = 5;
    public final static int OfSelectionPairs = 6;
    public final static int OfParameters = 7;
    public final static int selectionParameter = 8;
    public final static int selectionValue = 9;
    public final static int parameter = 10;
  }

  // PGN(126208) - NMEA - Read Fields reply group function
  public final static int nmeaReadFieldsReplyGroupFunction_pgn = 126208;
  public final static class nmeaReadFieldsReplyGroupFunction
  {
    // Read Fields Reply
    public final static int functionCode = 0;
    // Commanded PGN
    public final static int pgn = 1;
    // Only for proprietary PGNs
    public final static int manufacturerCode = 2;
    // Only for proprietary PGNs
    public final static int reserved = 3;
    // Only for proprietary PGNs
    public final static int industryCode = 4;
    // industryCode permitted values
    public static final class industryCode_values
    {
      public static final int Global = 0;
      public static final int Highway = 1;
      public static final int Agriculture = 2;
      public static final int Construction = 3;
      public static final int Marine = 4;
      public static final int Industrial = 5;
    }

    public final static int uniqueId = 5;
    public final static int OfSelectionPairs = 6;
    public final static int OfParameters = 7;
    public final static int selectionParameter = 8;
    public final static int selectionValue = 9;
    public final static int parameter = 10;
    public final static int value = 11;
  }

  // PGN(126208) - NMEA - Write Fields group function
  public final static int nmeaWriteFieldsGroupFunction_pgn = 126208;
  public final static class nmeaWriteFieldsGroupFunction
  {
    // Write Fields
    public final static int functionCode = 0;
    // Commanded PGN
    public final static int pgn = 1;
    // Only for proprietary PGNs
    public final static int manufacturerCode = 2;
    // Only for proprietary PGNs
    public final static int reserved = 3;
    // Only for proprietary PGNs
    public final static int industryCode = 4;
    // industryCode permitted values
    public static final class industryCode_values
    {
      public static final int Global = 0;
      public static final int Highway = 1;
      public static final int Agriculture = 2;
      public static final int Construction = 3;
      public static final int Marine = 4;
      public static final int Industrial = 5;
    }

    public final static int uniqueId = 5;
    public final static int OfSelectionPairs = 6;
    public final static int OfParameters = 7;
    public final static int selectionParameter = 8;
    public final static int selectionValue = 9;
    public final static int parameter = 10;
    public final static int value = 11;
  }

  // PGN(126208) - NMEA - Write Fields reply group function
  public final static int nmeaWriteFieldsReplyGroupFunction_pgn = 126208;
  public final static class nmeaWriteFieldsReplyGroupFunction
  {
    // Write Fields Reply
    public final static int functionCode = 0;
    // Commanded PGN
    public final static int pgn = 1;
    // Only for proprietary PGNs
    public final static int manufacturerCode = 2;
    // Only for proprietary PGNs
    public final static int reserved = 3;
    // Only for proprietary PGNs
    public final static int industryCode = 4;
    // industryCode permitted values
    public static final class industryCode_values
    {
      public static final int Global = 0;
      public static final int Highway = 1;
      public static final int Agriculture = 2;
      public static final int Construction = 3;
      public static final int Marine = 4;
      public static final int Industrial = 5;
    }

    public final static int uniqueId = 5;
    public final static int OfSelectionPairs = 6;
    public final static int OfParameters = 7;
    public final static int selectionParameter = 8;
    public final static int selectionValue = 9;
    public final static int parameter = 10;
    public final static int value = 11;
  }

  // PGN(126270) - Maretron: Slave Response
  public final static int maretronSlaveResponse_pgn = 126270;
  public final static class maretronSlaveResponse
  {
    // Maretron
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // 0x1b2=SSC200
    public final static int productCode = 3;
    public final static int softwareCode = 4;
    // 0x50=Deviation calibration result
    public final static int command = 5;
    public final static int status = 6;
  }

  // PGN(126464) - PGN List (Transmit and Receive)
  public final static int pgnListTransmitAndReceive_pgn = 126464;
  public final static class pgnListTransmitAndReceive
  {
    // Transmit or receive PGN Group Function Code
    public final static int functionCode = 0;
    // functionCode permitted values
    public static final class functionCode_values
    {
      public static final int TransmitPGNlist = 0;
      public static final int ReceivePGNlist = 1;
    }

    public final static class rep
    {
      public final static int pgn = 0;
    }
  }

  // PGN(126720) - Seatalk1: Keystroke
  public final static int seatalk1Keystroke_pgn = 126720;
  public final static class seatalk1Keystroke
  {
    // Raymarine
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // 0x81f0
    public final static int proprietaryId = 3;
    // 0x86
    public final static int command = 4;
    public final static int device = 5;
    // device permitted values
    public static final class device_values
    {
      public static final int S100 = 33;
    }

    public final static int key = 6;
    // key permitted values
    public static final class key_values
    {
      public static final int A_1 = 64005;
      public static final int A_12 = 63495;
      public static final int Standby = 64770;
      public static final int Auto = 65025;
      public static final int A_10 = 63240;
      public static final int A_103 = 63750;
      public static final int A_1and_10 = 56865;
      public static final int A_1and_104 = 56610;
    }

    public final static int unknownData = 7;
  }

  // PGN(126720) - Seatalk1: Device Indentification
  public final static int seatalk1DeviceIndentification_pgn = 126720;
  public final static class seatalk1DeviceIndentification
  {
    // Raymarine
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // 0x81f0
    public final static int proprietaryId = 3;
    // 0x90
    public final static int command = 4;
    public final static int reserved2 = 5;
    public final static int device = 6;
    // device permitted values
    public static final class device_values
    {
      public static final int S100 = 3;
      public static final int CourseComputer = 5;
    }

  }

  // PGN(126720) - Airmar: Attitude Offset
  public final static int airmarAttitudeOffset_pgn = 126720;
  public final static class airmarAttitudeOffset
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Attitude Offsets
    public final static int proprietaryId = 3;
    // Positive: sensor rotated to port, negative: sensor rotated to starboard (rad)
    public final static int azimuthOffset = 4;
    // Positive: sensor tilted to bow, negative: sensor tilted to stern (rad)
    public final static int pitchOffset = 5;
    // Positive: sensor tilted to port, negative: sensor tilted to starboard (rad)
    public final static int rollOffset = 6;
  }

  // PGN(126720) - Airmar: Calibrate Compass
  public final static int airmarCalibrateCompass_pgn = 126720;
  public final static class airmarCalibrateCompass
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Calibrate Compass
    public final static int proprietaryId = 3;
    public final static int calibrateFunction = 4;
    // calibrateFunction permitted values
    public static final class calibrateFunction_values
    {
      public static final int Normal_cancelcalibration = 0;
      public static final int Entercalibrationmode = 1;
      public static final int Resetcalibrationto0 = 2;
      public static final int Verify = 3;
      public static final int Resetcompasstodefaults = 4;
      public static final int Resetdampingtodefaults = 5;
    }

    public final static int calibrationStatus = 5;
    // calibrationStatus permitted values
    public static final class calibrationStatus_values
    {
      public static final int Queried = 0;
      public static final int Passed = 1;
      public static final int Failed_timeout = 2;
      public static final int Failed_tilterror = 3;
      public static final int Failed_other = 4;
      public static final int Inprogress = 5;
    }

    // TBD
    public final static int verifyScore = 6;
    // default 100, range 50 to 500
    public final static int xAxisGainValue = 7;
    // default 100, range 50 to 500
    public final static int yAxisGainValue = 8;
    // default 100, range 50 to 500
    public final static int zAxisGainValue = 9;
    // default 0, range -320.00 to 320.00 (Tesla)
    public final static int xAxisLinearOffset = 10;
    // default 0, range -320.00 to 320.00 (Tesla)
    public final static int yAxisLinearOffset = 11;
    // default 0, range -320.00 to 320.00 (Tesla)
    public final static int zAxisLinearOffset = 12;
    // default 0, range 0 to 3600 (deg)
    public final static int xAxisAngularOffset = 13;
    // default 30, range 0 to 200 (s)
    public final static int pitchAndRollDamping = 14;
    // default -30, range -2400 to 2400, negative indicates rate gyro is to be used in compass calculations (s)
    public final static int compassRateGyroDamping = 15;
  }

  // PGN(126720) - Airmar: True Wind Options
  public final static int airmarTrueWindOptions_pgn = 126720;
  public final static class airmarTrueWindOptions
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // True Wind Options
    public final static int proprietaryId = 3;
    // Allow use of COG when HDG not available?
    public final static int cogSubstitionForHdg = 4;
    // cogSubstitionForHdg permitted values
    public static final class cogSubstitionForHdg_values
    {
      public static final int UseHDGonly = 0;
      public static final int AllowCOGtoreplaceHDG = 1;
    }

    public final static int calibrationStatus = 5;
    // calibrationStatus permitted values
    public static final class calibrationStatus_values
    {
      public static final int Queried = 0;
      public static final int Passed = 1;
      public static final int Failed_timeout = 2;
      public static final int Failed_tilterror = 3;
      public static final int Failed_other = 4;
      public static final int Inprogress = 5;
    }

    // TBD
    public final static int verifyScore = 6;
    // default 100, range 50 to 500
    public final static int xAxisGainValue = 7;
    // default 100, range 50 to 500
    public final static int yAxisGainValue = 8;
    // default 100, range 50 to 500
    public final static int zAxisGainValue = 9;
    // default 0, range -320.00 to 320.00 (Tesla)
    public final static int xAxisLinearOffset = 10;
    // default 0, range -320.00 to 320.00 (Tesla)
    public final static int yAxisLinearOffset = 11;
    // default 0, range -320.00 to 320.00 (Tesla)
    public final static int zAxisLinearOffset = 12;
    // default 0, range 0 to 3600 (deg)
    public final static int xAxisAngularOffset = 13;
    // default 30, range 0 to 200 (s)
    public final static int pitchAndRollDamping = 14;
    // default -30, range -2400 to 2400, negative indicates rate gyro is to be used in compass calculations (s)
    public final static int compassRateGyroDamping = 15;
  }

  // PGN(126720) - Airmar: Simulate Mode
  public final static int airmarSimulateMode_pgn = 126720;
  public final static class airmarSimulateMode
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Simulate Mode
    public final static int proprietaryId = 3;
    public final static int simulateMode = 4;
    // simulateMode permitted values
    public static final class simulateMode_values
    {
      public static final int Off = 0;
      public static final int On = 1;
    }

    // Reserved
    public final static int reserved2 = 5;
  }

  // PGN(126720) - Airmar: Calibrate Depth
  public final static int airmarCalibrateDepth_pgn = 126720;
  public final static class airmarCalibrateDepth
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Calibrate Depth
    public final static int proprietaryId = 3;
    // actual allowed range is 1350.0 to 1650.0 m/s (m/s)
    public final static int speedOfSoundMode = 4;
    // Reserved
    public final static int reserved2 = 5;
  }

  // PGN(126720) - Airmar: Calibrate Speed
  public final static int airmarCalibrateSpeed_pgn = 126720;
  public final static class airmarCalibrateSpeed
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Calibrate Speed
    public final static int proprietaryId = 3;
    // actual range is 0 to 25. 254=restore default speed curve
    public final static int numberOfPairsOfDataPoints = 4;
    public final static class rep
    {
      public final static int inputFrequency = 0;
      public final static int outputSpeed = 1;
    }
  }

  // PGN(126720) - Airmar: Calibrate Temperature
  public final static int airmarCalibrateTemperature_pgn = 126720;
  public final static class airmarCalibrateTemperature
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Calibrate Temperature
    public final static int proprietaryId = 3;
    public final static int temperatureInstance = 4;
    // temperatureInstance permitted values
    public static final class temperatureInstance_values
    {
      public static final int DeviceSensor = 0;
      public static final int OnboardWaterSensor = 1;
      public static final int OptionalWaterSensor = 2;
    }

    // Reserved
    public final static int reserved2 = 5;
    public final static class rep
    {
      public final static int temperatureOffset = 0;
      // actual range is -9.999 to +9.999 K (K)
      public final static int temperatureOffset2 = 1;
    }
  }

  // PGN(126720) - Airmar: Speed Filter
  public final static int airmarSpeedFilter_pgn = 126720;
  public final static class airmarSpeedFilter
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Speed Filter
    public final static int proprietaryId = 3;
    public final static int filterType = 4;
    // filterType permitted values
    public static final class filterType_values
    {
      public static final int nofilter = 0;
      public static final int basicIIRfilter = 1;
    }

    // Reserved
    public final static int reserved2 = 5;
    public final static class rep
    {
      public final static int sampleInterval = 0;
      public final static int filterDuration = 1;
    }
  }

  // PGN(126720) - Airmar: Temperature Filter
  public final static int airmarTemperatureFilter_pgn = 126720;
  public final static class airmarTemperatureFilter
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Temperature Filter
    public final static int proprietaryId = 3;
    public final static int filterType = 4;
    // filterType permitted values
    public static final class filterType_values
    {
      public static final int nofilter = 0;
      public static final int basicIIRfilter = 1;
      public static final int datanotavailable = 15;
    }

    // Reserved
    public final static int reserved2 = 5;
    public final static class rep
    {
      public final static int sampleInterval = 0;
      public final static int filterDuration = 1;
    }
  }

  // PGN(126720) - Airmar: NMEA 2000 options
  public final static int airmarNmea2000Options_pgn = 126720;
  public final static class airmarNmea2000Options
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // NMEA 2000 options
    public final static int proprietaryId = 3;
    public final static class rep
    {
      public final static int transmissionInterval = 0;
      // transmissionInterval permitted values
      public static final class transmissionInterval_values
      {
        public static final int MeasureInterval = 0;
        public static final int Requestedbyuser = 1;
        public static final int reserved = 2;
        public static final int datanotavailable = 3;
      }

      // Reserved
      public final static int reserved2 = 1;
    }
  }

  // PGN(126720) - Airmar: Addressable Multi-Frame
  public final static int airmarAddressableMultiFrame_pgn = 126720;
  public final static class airmarAddressableMultiFrame
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int proprietaryId = 3;
  }

  // PGN(126720) - Manufacturer Proprietary fast-packet addressed
  public final static int manufacturerProprietaryFastPacketAddressed_pgn = 126720;
  public final static class manufacturerProprietaryFastPacketAddressed
  {
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    public final static int industryCode = 2;
    // industryCode permitted values
    public static final class industryCode_values
    {
      public static final int Global = 0;
      public static final int Highway = 1;
      public static final int Agriculture = 2;
      public static final int Construction = 3;
      public static final int Marine = 4;
      public static final int Industrial = 5;
    }

    public final static int data = 3;
  }

  // PGN(126976) - Unknown fast-packet non-addressed
  public final static int unknownFastPacketNonAddressed_pgn = 126976;
  public final static class unknownFastPacketNonAddressed
  {
    public final static int data = 0;
  }

  // PGN(126983) - Alert
  public final static int alert_pgn = 126983;
  public final static class alert
  {
  }

  // PGN(126984) - Alert Response
  public final static int alertResponse_pgn = 126984;
  public final static class alertResponse
  {
  }

  // PGN(126985) - Alert Text
  public final static int alertText_pgn = 126985;
  public final static class alertText
  {
  }

  // PGN(126986) - Alert Configuration
  public final static int alertConfiguration_pgn = 126986;
  public final static class alertConfiguration
  {
  }

  // PGN(126987) - Alert Threshold
  public final static int alertThreshold_pgn = 126987;
  public final static class alertThreshold
  {
  }

  // PGN(126988) - Alert Value
  public final static int alertValue_pgn = 126988;
  public final static class alertValue
  {
  }

  // PGN(126992) - System Time
  public final static int systemTime_pgn = 126992;
  public final static class systemTime
  {
    public final static int sid = 0;
    public final static int source = 1;
    // source permitted values
    public static final class source_values
    {
      public static final int GPS = 0;
      public static final int GLONASS = 1;
      public static final int RadioStation = 2;
      public static final int LocalCesiumclock = 3;
      public static final int LocalRubidiumclock = 4;
      public static final int LocalCrystalclock = 5;
    }

    // Reserved
    public final static int reserved = 2;
    // Days since January 1, 1970 (days)
    public final static int date = 3;
    // Seconds since midnight (s)
    public final static int time = 4;
  }

  // PGN(126993) - Heartbeat
  public final static int heartbeat_pgn = 126993;
  public final static class heartbeat
  {
    // Offset in transmit time from time of request command: 0x0 = transmit immediately, 0xFFFF = Do not change offset. (s)
    public final static int dataTransmitOffset = 0;
    public final static int sequenceCounter = 1;
    // Reserved
    public final static int reserved = 2;
  }

  // PGN(126996) - Product Information
  public final static int productInformation_pgn = 126996;
  public final static class productInformation
  {
    public final static int nmea2000Version = 0;
    public final static int productCode = 1;
    public final static int modelId = 2;
    public final static int softwareVersionCode = 3;
    public final static int modelVersion = 4;
    public final static int modelSerialCode = 5;
    public final static int certificationLevel = 6;
    public final static int loadEquivalency = 7;
  }

  // PGN(126998) - Configuration Information
  public final static int configurationInformation_pgn = 126998;
  public final static class configurationInformation
  {
    public final static int stationId = 0;
    public final static int stationName = 1;
    public final static int a = 2;
    public final static int manufacturer = 3;
    public final static int installationDescription1 = 4;
    public final static int installationDescription2 = 5;
  }

  // PGN(127233) - Man Overboard Notification
  public final static int manOverboardNotification_pgn = 127233;
  public final static class manOverboardNotification
  {
    public final static int sid = 0;
    // Identifier for each MOB emitter, unique to the vessel
    public final static int mobEmitterId = 1;
    public final static int manOverboardStatus = 2;
    // manOverboardStatus permitted values
    public static final class manOverboardStatus_values
    {
      public static final int MOBEmitterActivated = 0;
      public static final int Manualon_boardMOBButtonActivation = 1;
      public static final int TestMode = 2;
      public static final int MOBNotActive = 3;
    }

    public final static int reserved = 3;
    // Time of day (UTC) when MOB was activated (s)
    public final static int activationTime = 4;
    public final static int positionSource = 5;
    // positionSource permitted values
    public static final class positionSource_values
    {
      public static final int PositionestimatedbytheVessel = 0;
      public static final int PositionreportedbyMOBemitter = 1;
    }

    public final static int reserved2 = 6;
    // Date of MOB position ()
    public final static int positionDate = 7;
    // Time of day of MOB position (UTC) (s)
    public final static int positionTime = 8;
    public final static int latitude = 9;
    public final static int longitude = 10;
    public final static int cogReference = 11;
    // cogReference permitted values
    public static final class cogReference_values
    {
      public static final int True = 0;
      public static final int Magnetic = 1;
    }

    public final static int reserved3 = 12;
    public final static int cog = 13;
    public final static int sog = 14;
    public final static int mmsiOfVesselOfOrigin = 15;
    public final static int mobEmitterBatteryStatus = 16;
    // mobEmitterBatteryStatus permitted values
    public static final class mobEmitterBatteryStatus_values
    {
      public static final int Good = 0;
      public static final int Low = 1;
    }

    public final static int reserved4 = 17;
  }

  // PGN(127237) - Heading/Track control
  public final static int headingTrackControl_pgn = 127237;
  public final static class headingTrackControl
  {
    public final static int rudderLimitExceeded = 0;
    public final static int offHeadingLimitExceeded = 1;
    public final static int offTrackLimitExceeded = 2;
    public final static int override = 3;
    public final static int steeringMode = 4;
    public final static int turnMode = 5;
    public final static int headingReference = 6;
    public final static int reserved = 7;
    public final static int commandedRudderDirection = 8;
    public final static int commandedRudderAngle = 9;
    public final static int headingToSteerCourse = 10;
    public final static int track = 11;
    public final static int rudderLimit = 12;
    public final static int offHeadingLimit = 13;
    public final static int radiusOfTurnOrder = 14;
    public final static int rateOfTurnOrder = 15;
    public final static int offTrackLimit = 16;
    public final static int vesselHeading = 17;
  }

  // PGN(127245) - Rudder
  public final static int rudder_pgn = 127245;
  public final static class rudder
  {
    public final static int instance = 0;
    public final static int directionOrder = 1;
    // Reserved
    public final static int reserved = 2;
    public final static int angleOrder = 3;
    public final static int position = 4;
  }

  // PGN(127250) - Vessel Heading
  public final static int vesselHeading_pgn = 127250;
  public final static class vesselHeading
  {
    public final static int sid = 0;
    public final static int heading = 1;
    public final static int deviation = 2;
    public final static int variation = 3;
    public final static int reference = 4;
    // reference permitted values
    public static final class reference_values
    {
      public static final int True = 0;
      public static final int Magnetic = 1;
    }

  }

  // PGN(127251) - Rate of Turn
  public final static int rateOfTurn_pgn = 127251;
  public final static class rateOfTurn
  {
    public final static int sid = 0;
    public final static int rate = 1;
  }

  // PGN(127257) - Attitude
  public final static int attitude_pgn = 127257;
  public final static class attitude
  {
    public final static int sid = 0;
    public final static int yaw = 1;
    public final static int pitch = 2;
    public final static int roll = 3;
  }

  // PGN(127258) - Magnetic Variation
  public final static int magneticVariation_pgn = 127258;
  public final static class magneticVariation
  {
    public final static int sid = 0;
    public final static int source = 1;
    // source permitted values
    public static final class source_values
    {
      public static final int Manual = 0;
      public static final int AutomaticChart = 1;
      public static final int AutomaticTable = 2;
      public static final int AutomaticCalculation = 3;
      public static final int WMM2000 = 4;
      public static final int WMM2005 = 5;
      public static final int WMM2010 = 6;
      public static final int WMM2015 = 7;
      public static final int WMM2020 = 8;
    }

    // Reserved
    public final static int reserved = 2;
    // Days since January 1, 1970 (days)
    public final static int ageOfService = 3;
    public final static int variation = 4;
  }

  // PGN(127488) - Engine Parameters, Rapid Update
  public final static int engineParametersRapidUpdate_pgn = 127488;
  public final static class engineParametersRapidUpdate
  {
    public final static int instance = 0;
    // instance permitted values
    public static final class instance_values
    {
      public static final int SingleEngineorDualEnginePort = 0;
      public static final int DualEngineStarboard = 1;
    }

    public final static int speed = 1;
    public final static int boostPressure = 2;
    public final static int tiltTrim = 3;
  }

  // PGN(127489) - Engine Parameters, Dynamic
  public final static int engineParametersDynamic_pgn = 127489;
  public final static class engineParametersDynamic
  {
    public final static int instance = 0;
    // instance permitted values
    public static final class instance_values
    {
      public static final int SingleEngineorDualEnginePort = 0;
      public static final int DualEngineStarboard = 1;
    }

    public final static int oilPressure = 1;
    public final static int oilTemperature = 2;
    public final static int temperature = 3;
    public final static int alternatorPotential = 4;
    public final static int fuelRate = 5;
    public final static int totalEngineHours = 6;
    public final static int coolantPressure = 7;
    public final static int fuelPressure = 8;
    public final static int reserved = 9;
    public final static int discreteStatus1 = 10;
    public final static int discreteStatus2 = 11;
    public final static int percentEngineLoad = 12;
    public final static int percentEngineTorque = 13;
  }

  // PGN(127493) - Transmission Parameters, Dynamic
  public final static int transmissionParametersDynamic_pgn = 127493;
  public final static class transmissionParametersDynamic
  {
    public final static int instance = 0;
    // instance permitted values
    public static final class instance_values
    {
      public static final int SingleEngineorDualEnginePort = 0;
      public static final int DualEngineStarboard = 1;
    }

    public final static int transmissionGear = 1;
    // transmissionGear permitted values
    public static final class transmissionGear_values
    {
      public static final int Forward = 0;
      public static final int Neutral = 1;
      public static final int Reverse = 2;
    }

    public final static int reserved = 2;
    public final static int oilPressure = 3;
    public final static int oilTemperature = 4;
    public final static int discreteStatus1 = 5;
    public final static int reserved2 = 6;
  }

  // PGN(127496) - Trip Parameters, Vessel
  public final static int tripParametersVessel_pgn = 127496;
  public final static class tripParametersVessel
  {
    public final static int timeToEmpty = 0;
    public final static int distanceToEmpty = 1;
    public final static int estimatedFuelRemaining = 2;
    public final static int tripRunTime = 3;
  }

  // PGN(127497) - Trip Parameters, Engine
  public final static int tripParametersEngine_pgn = 127497;
  public final static class tripParametersEngine
  {
    public final static int instance = 0;
    // instance permitted values
    public static final class instance_values
    {
      public static final int SingleEngineorDualEnginePort = 0;
      public static final int DualEngineStarboard = 1;
    }

    public final static int tripFuelUsed = 1;
    public final static int fuelRateAverage = 2;
    public final static int fuelRateEconomy = 3;
    public final static int instantaneousFuelEconomy = 4;
  }

  // PGN(127498) - Engine Parameters, Static
  public final static int engineParametersStatic_pgn = 127498;
  public final static class engineParametersStatic
  {
    public final static int instance = 0;
    // instance permitted values
    public static final class instance_values
    {
      public static final int SingleEngineorDualEnginePort = 0;
      public static final int DualEngineStarboard = 1;
    }

    public final static int ratedEngineSpeed = 1;
    public final static int vin = 2;
    public final static int softwareId = 3;
  }

  // PGN(127501) - Binary Switch Bank Status
  public final static int binarySwitchBankStatus_pgn = 127501;
  public final static class binarySwitchBankStatus
  {
    public final static int instance = 0;
    public final static int indicator1 = 1;
    // indicator1 permitted values
    public static final class indicator1_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator2 = 2;
    // indicator2 permitted values
    public static final class indicator2_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator3 = 3;
    // indicator3 permitted values
    public static final class indicator3_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator4 = 4;
    // indicator4 permitted values
    public static final class indicator4_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator5 = 5;
    // indicator5 permitted values
    public static final class indicator5_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator6 = 6;
    // indicator6 permitted values
    public static final class indicator6_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator7 = 7;
    // indicator7 permitted values
    public static final class indicator7_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator8 = 8;
    // indicator8 permitted values
    public static final class indicator8_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator9 = 9;
    // indicator9 permitted values
    public static final class indicator9_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator10 = 10;
    // indicator10 permitted values
    public static final class indicator10_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator11 = 11;
    // indicator11 permitted values
    public static final class indicator11_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator12 = 12;
    // indicator12 permitted values
    public static final class indicator12_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator13 = 13;
    // indicator13 permitted values
    public static final class indicator13_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator14 = 14;
    // indicator14 permitted values
    public static final class indicator14_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator15 = 15;
    // indicator15 permitted values
    public static final class indicator15_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator16 = 16;
    // indicator16 permitted values
    public static final class indicator16_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator17 = 17;
    // indicator17 permitted values
    public static final class indicator17_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator18 = 18;
    // indicator18 permitted values
    public static final class indicator18_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator19 = 19;
    // indicator19 permitted values
    public static final class indicator19_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator20 = 20;
    // indicator20 permitted values
    public static final class indicator20_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator21 = 21;
    // indicator21 permitted values
    public static final class indicator21_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator22 = 22;
    // indicator22 permitted values
    public static final class indicator22_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator23 = 23;
    // indicator23 permitted values
    public static final class indicator23_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator24 = 24;
    // indicator24 permitted values
    public static final class indicator24_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator25 = 25;
    // indicator25 permitted values
    public static final class indicator25_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator26 = 26;
    // indicator26 permitted values
    public static final class indicator26_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator27 = 27;
    // indicator27 permitted values
    public static final class indicator27_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

    public final static int indicator28 = 28;
    // indicator28 permitted values
    public static final class indicator28_values
    {
      public static final int Off = 0;
      public static final int On = 1;
      public static final int Failed = 2;
    }

  }

  // PGN(127502) - Switch Bank Control
  public final static int switchBankControl_pgn = 127502;
  public final static class switchBankControl
  {
    public final static int switchBankInstance = 0;
    public final static class rep
    {
      public final static int switch_field = 0;
      // switch_field permitted values
      public static final class switch_field_values
      {
        public static final int Off = 0;
        public static final int On = 1;
      }

    }
  }

  // PGN(127503) - AC Input Status
  public final static int acInputStatus_pgn = 127503;
  public final static class acInputStatus
  {
    public final static int instance = 0;
    public final static int numberOfLines = 1;
    public final static class rep
    {
      public final static int line = 0;
      // line permitted values
      public static final class line_values
      {
        public static final int Line1 = 0;
        public static final int Line2 = 1;
        public static final int Line3 = 2;
        public static final int Reserved = 3;
      }

      public final static int acceptability = 1;
      // acceptability permitted values
      public static final class acceptability_values
      {
        public static final int BadLevel = 0;
        public static final int BadFrequency = 1;
        public static final int BeingQualified = 2;
        public static final int Good = 3;
      }

      public final static int reserved = 2;
      public final static int voltage = 3;
      public final static int current = 4;
      public final static int frequency = 5;
      public final static int breakerSize = 6;
      public final static int realPower = 7;
      public final static int reactivePower = 8;
      public final static int powerFactor = 9;
    }
  }

  // PGN(127504) - AC Output Status
  public final static int acOutputStatus_pgn = 127504;
  public final static class acOutputStatus
  {
    public final static int instance = 0;
    public final static int numberOfLines = 1;
    public final static class rep
    {
      public final static int line = 0;
      // line permitted values
      public static final class line_values
      {
        public static final int Line1 = 0;
        public static final int Line2 = 1;
        public static final int Line3 = 2;
      }

      public final static int waveform = 1;
      // waveform permitted values
      public static final class waveform_values
      {
        public static final int SineWave = 0;
        public static final int ModifiedSineWave = 1;
        public static final int Error = 6;
        public static final int DataNotAvailable = 7;
      }

      public final static int reserved = 2;
      public final static int voltage = 3;
      public final static int current = 4;
      public final static int frequency = 5;
      public final static int breakerSize = 6;
      public final static int realPower = 7;
      public final static int reactivePower = 8;
      public final static int powerFactor = 9;
    }
  }

  // PGN(127505) - Fluid Level
  public final static int fluidLevel_pgn = 127505;
  public final static class fluidLevel
  {
    public final static int instance = 0;
    public final static int type = 1;
    // type permitted values
    public static final class type_values
    {
      public static final int Fuel = 0;
      public static final int Water = 1;
      public static final int Graywater = 2;
      public static final int Livewell = 3;
      public static final int Oil = 4;
      public static final int Blackwater = 5;
    }

    public final static int level = 2;
    public final static int capacity = 3;
    // Reserved
    public final static int reserved = 4;
  }

  // PGN(127506) - DC Detailed Status
  public final static int dcDetailedStatus_pgn = 127506;
  public final static class dcDetailedStatus
  {
    public final static int sid = 0;
    public final static int instance = 1;
    public final static int dcType = 2;
    public final static int stateOfCharge = 3;
    public final static int stateOfHealth = 4;
    public final static int timeRemaining = 5;
    public final static int rippleVoltage = 6;
  }

  // PGN(127507) - Charger Status
  public final static int chargerStatus_pgn = 127507;
  public final static class chargerStatus
  {
    public final static int instance = 0;
    public final static int batteryInstance = 1;
    public final static int operatingState = 2;
    // operatingState permitted values
    public static final class operatingState_values
    {
      public static final int Notcharging = 0;
      public static final int Bulk = 1;
      public static final int Absorption = 2;
      public static final int Overcharge = 3;
      public static final int Equalise = 4;
      public static final int Float = 5;
      public static final int NoFloat = 6;
      public static final int ConstantVI = 7;
      public static final int Disabled = 8;
      public static final int Fault = 9;
    }

    public final static int chargeMode = 3;
    // chargeMode permitted values
    public static final class chargeMode_values
    {
      public static final int Standalonemode = 0;
      public static final int Primarymode = 1;
      public static final int Secondarymode = 2;
      public static final int Echomode = 3;
    }

    public final static int operatingState2 = 4;
    // operatingState2 permitted values
    public static final class operatingState2_values
    {
      public static final int Off = 0;
      public static final int On = 1;
    }

    public final static int equalizationPending = 5;
    // equalizationPending permitted values
    public static final class equalizationPending_values
    {
      public static final int Off = 0;
      public static final int On = 1;
    }

    public final static int reserved = 6;
    public final static int equalizationTimeRemaining = 7;
  }

  // PGN(127508) - Battery Status
  public final static int batteryStatus_pgn = 127508;
  public final static class batteryStatus
  {
    public final static int instance = 0;
    public final static int voltage = 1;
    public final static int current = 2;
    public final static int temperature = 3;
    public final static int sid = 4;
  }

  // PGN(127509) - Inverter Status
  public final static int inverterStatus_pgn = 127509;
  public final static class inverterStatus
  {
    public final static int instance = 0;
    public final static int acInstance = 1;
    public final static int dcInstance = 2;
    public final static int operatingState = 3;
    // operatingState permitted values
    public static final class operatingState_values
    {
      public static final int Standby = 0;
      public static final int On = 1;
    }

    public final static int inverter = 4;
    // inverter permitted values
    public static final class inverter_values
    {
      public static final int Standby = 0;
      public static final int On = 1;
    }

  }

  // PGN(127510) - Charger Configuration Status
  public final static int chargerConfigurationStatus_pgn = 127510;
  public final static class chargerConfigurationStatus
  {
    public final static int instance = 0;
    public final static int batteryInstance = 1;
    public final static int chargerEnableDisable = 2;
    public final static int reserved = 3;
    public final static int chargeCurrentLimit = 4;
    public final static int chargingAlgorithm = 5;
    public final static int chargerMode = 6;
    // When no sensor present
    public final static int estimatedTemperature = 7;
    public final static int equalizeOneTimeEnableDisable = 8;
    public final static int overChargeEnableDisable = 9;
    public final static int equalizeTime = 10;
  }

  // PGN(127511) - Inverter Configuration Status
  public final static int inverterConfigurationStatus_pgn = 127511;
  public final static class inverterConfigurationStatus
  {
    public final static int instance = 0;
    public final static int acInstance = 1;
    public final static int dcInstance = 2;
    public final static int inverterEnableDisable = 3;
    public final static int inverterMode = 4;
    public final static int loadSenseEnableDisable = 5;
    public final static int loadSensePowerThreshold = 6;
    public final static int loadSenseInterval = 7;
  }

  // PGN(127512) - AGS Configuration Status
  public final static int agsConfigurationStatus_pgn = 127512;
  public final static class agsConfigurationStatus
  {
    public final static int instance = 0;
    public final static int generatorInstance = 1;
    public final static int agsMode = 2;
  }

  // PGN(127513) - Battery Configuration Status
  public final static int batteryConfigurationStatus_pgn = 127513;
  public final static class batteryConfigurationStatus
  {
    public final static int instance = 0;
    public final static int batteryType = 1;
    public final static int supportsEqualization = 2;
    public final static int reserved = 3;
    public final static int nominalVoltage = 4;
    public final static int chemistry = 5;
    public final static int capacity = 6;
    public final static int temperatureCoefficient = 7;
    public final static int peukertExponent = 8;
    public final static int chargeEfficiencyFactor = 9;
  }

  // PGN(127514) - AGS Status
  public final static int agsStatus_pgn = 127514;
  public final static class agsStatus
  {
    public final static int instance = 0;
    public final static int generatorInstance = 1;
    public final static int agsOperatingState = 2;
    public final static int generatorState = 3;
    public final static int generatorOnReason = 4;
    public final static int generatorOffReason = 5;
  }

  // PGN(128000) - Leeway Angle
  public final static int leewayAngle_pgn = 128000;
  public final static class leewayAngle
  {
    public final static int sid = 0;
    public final static int leewayAngle = 1;
    public final static int reserved = 2;
  }

  // PGN(128259) - Speed
  public final static int speed_pgn = 128259;
  public final static class speed
  {
    public final static int sid = 0;
    public final static int speedWaterReferenced = 1;
    public final static int speedGroundReferenced = 2;
    public final static int speedWaterReferencedType = 3;
    // speedWaterReferencedType permitted values
    public static final class speedWaterReferencedType_values
    {
      public static final int Paddlewheel = 0;
      public static final int Pitottube = 1;
      public static final int Doppler = 2;
      public static final int Correlation_ultrasound_ = 3;
      public static final int ElectroMagnetic = 4;
    }

    public final static int speedDirection = 4;
  }

  // PGN(128267) - Water Depth
  public final static int waterDepth_pgn = 128267;
  public final static class waterDepth
  {
    public final static int sid = 0;
    // Depth below transducer (m)
    public final static int depth = 1;
    // Distance between transducer and surface (positive) or keel (negative) (m)
    public final static int offset = 2;
    // Max measurement range (m)
    public final static int range = 3;
  }

  // PGN(128275) - Distance Log
  public final static int distanceLog_pgn = 128275;
  public final static class distanceLog
  {
    // Timestamp of last reset in Days since January 1, 1970 (days)
    public final static int date = 0;
    // Timestamp of last reset Seconds since midnight (s)
    public final static int time = 1;
    // Total cumulative distance (m)
    public final static int log = 2;
    // Distance since last reset (m)
    public final static int tripLog = 3;
  }

  // PGN(128520) - Tracked Target Data
  public final static int trackedTargetData_pgn = 128520;
  public final static class trackedTargetData
  {
    public final static int sid = 0;
    // Number of route, waypoint, event, mark, etc.
    public final static int targetId = 1;
    public final static int trackStatus = 2;
    // trackStatus permitted values
    public static final class trackStatus_values
    {
      public static final int Cancelled = 0;
      public static final int Acquiring = 1;
      public static final int Tracking = 2;
      public static final int Lost = 3;
    }

    public final static int reportedTarget = 3;
    // reportedTarget permitted values
    public static final class reportedTarget_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int targetAcquisition = 4;
    // targetAcquisition permitted values
    public static final class targetAcquisition_values
    {
      public static final int Manual = 0;
      public static final int Automatic = 1;
    }

    public final static int bearingReference = 5;
    // bearingReference permitted values
    public static final class bearingReference_values
    {
      public static final int True = 0;
      public static final int Magnetic = 1;
    }

    public final static int reserved = 6;
    public final static int bearing = 7;
    public final static int distance = 8;
    public final static int course = 9;
    public final static int speed = 10;
    public final static int cpa = 11;
    // negative = time elapsed since event, positive = time to go (s)
    public final static int tcpa = 12;
    // Seconds since midnight (s)
    public final static int utcOfFix = 13;
    public final static int name = 14;
  }

  // PGN(129025) - Position, Rapid Update
  public final static int positionRapidUpdate_pgn = 129025;
  public final static class positionRapidUpdate
  {
    public final static int latitude = 0;
    public final static int longitude = 1;
  }

  // PGN(129026) - COG & SOG, Rapid Update
  public final static int cogSogRapidUpdate_pgn = 129026;
  public final static class cogSogRapidUpdate
  {
    public final static int sid = 0;
    public final static int cogReference = 1;
    // cogReference permitted values
    public static final class cogReference_values
    {
      public static final int True = 0;
      public static final int Magnetic = 1;
    }

    // Reserved
    public final static int reserved = 2;
    public final static int cog = 3;
    public final static int sog = 4;
    // Reserved
    public final static int reserved2 = 5;
  }

  // PGN(129027) - Position Delta, Rapid Update
  public final static int positionDeltaRapidUpdate_pgn = 129027;
  public final static class positionDeltaRapidUpdate
  {
    public final static int sid = 0;
    public final static int timeDelta = 1;
    public final static int latitudeDelta = 2;
    public final static int longitudeDelta = 3;
  }

  // PGN(129028) - Altitude Delta, Rapid Update
  public final static int altitudeDeltaRapidUpdate_pgn = 129028;
  public final static class altitudeDeltaRapidUpdate
  {
    public final static int sid = 0;
    public final static int timeDelta = 1;
    public final static int gnssQuality = 2;
    public final static int direction = 3;
    // Reserved
    public final static int reserved = 4;
    public final static int courseOverGround = 5;
    public final static int altitudeDelta = 6;
  }

  // PGN(129029) - GNSS Position Data
  public final static int gnssPositionData_pgn = 129029;
  public final static class gnssPositionData
  {
    public final static int sid = 0;
    // Days since January 1, 1970 (days)
    public final static int date = 1;
    // Seconds since midnight (s)
    public final static int time = 2;
    public final static int latitude = 3;
    public final static int longitude = 4;
    // Altitude referenced to WGS-84 (m)
    public final static int altitude = 5;
    public final static int gnssType = 6;
    // gnssType permitted values
    public static final class gnssType_values
    {
      public static final int GPS = 0;
      public static final int GLONASS = 1;
      public static final int GPS_GLONASS = 2;
      public static final int GPS_SBAS_WAAS = 3;
      public static final int GPS_SBAS_WAAS_GLONASS = 4;
      public static final int Chayka = 5;
      public static final int integrated = 6;
      public static final int surveyed = 7;
      public static final int Galileo = 8;
    }

    public final static int method = 7;
    // method permitted values
    public static final class method_values
    {
      public static final int noGNSS = 0;
      public static final int GNSSfix = 1;
      public static final int DGNSSfix = 2;
      public static final int PreciseGNSS = 3;
      public static final int RTKFixedInteger = 4;
      public static final int RTKfloat = 5;
      public static final int Estimated_DR_mode = 6;
      public static final int ManualInput = 7;
      public static final int Simulatemode = 8;
    }

    public final static int integrity = 8;
    // integrity permitted values
    public static final class integrity_values
    {
      public static final int Nointegritychecking = 0;
      public static final int Safe = 1;
      public static final int Caution = 2;
    }

    // Reserved
    public final static int reserved = 9;
    // Number of satellites used in solution
    public final static int numberOfSvs = 10;
    // Horizontal dilution of precision
    public final static int hdop = 11;
    // Probable dilution of precision
    public final static int pdop = 12;
    // Geoidal Separation (m)
    public final static int geoidalSeparation = 13;
    // Number of reference stations
    public final static int referenceStations = 14;
    public final static class rep
    {
      public final static int referenceStationType = 0;
      // referenceStationType permitted values
      public static final class referenceStationType_values
      {
        public static final int GPS = 0;
        public static final int GLONASS = 1;
        public static final int GPS_GLONASS = 2;
        public static final int GPS_SBAS_WAAS = 3;
        public static final int GPS_SBAS_WAAS_GLONASS = 4;
        public static final int Chayka = 5;
        public static final int integrated = 6;
        public static final int surveyed = 7;
        public static final int Galileo = 8;
      }

      public final static int referenceStationId = 1;
      public final static int ageOfDgnssCorrections = 2;
    }
  }

  // PGN(129033) - Time & Date
  public final static int timeDate_pgn = 129033;
  public final static class timeDate
  {
    // Days since January 1, 1970 (days)
    public final static int date = 0;
    // Seconds since midnight (s)
    public final static int time = 1;
    // Minutes (minutes)
    public final static int localOffset = 2;
  }

  // PGN(129038) - AIS Class A Position Report
  public final static int aisClassAPositionReport_pgn = 129038;
  public final static class aisClassAPositionReport
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int userId = 2;
    public final static int longitude = 3;
    public final static int latitude = 4;
    public final static int positionAccuracy = 5;
    // positionAccuracy permitted values
    public static final class positionAccuracy_values
    {
      public static final int Low = 0;
      public static final int High = 1;
    }

    public final static int raim = 6;
    // raim permitted values
    public static final class raim_values
    {
      public static final int notinuse = 0;
      public static final int inuse = 1;
    }

    // 0-59 = UTC second when the report was generated
    public final static int timeStamp = 7;
    // timeStamp permitted values
    public static final class timeStamp_values
    {
      public static final int Notavailable = 60;
      public static final int Manualinputmode = 61;
      public static final int Deadreckoningmode = 62;
      public static final int Positioningsystemisinoperative = 63;
    }

    public final static int cog = 8;
    public final static int sog = 9;
    // Information used by the TDMA slot allocation algorithm and synchronization information
    public final static int communicationState = 10;
    public final static int aisTransceiverInformation = 11;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

    // True heading (rad)
    public final static int heading = 12;
    public final static int rateOfTurn = 13;
    public final static int navStatus = 14;
    // navStatus permitted values
    public static final class navStatus_values
    {
      public static final int Underwayusingengine = 0;
      public static final int Atanchor = 1;
      public static final int Notundercommand = 2;
      public static final int Restrictedmanoeuverability = 3;
      public static final int Constrainedbyherdraught = 4;
      public static final int Moored = 5;
      public static final int Aground = 6;
      public static final int EngagedinFishing = 7;
      public static final int Underwaysailing = 8;
      public static final int Hazardousmaterial_HighSpeed = 9;
      public static final int Hazardousmaterial_WinginGround = 10;
      public static final int AIS_SART = 14;
    }

    // reserved
    public final static int reserved = 15;
    public final static int regionalApplication = 16;
    // reserved
    public final static int reserved2 = 17;
  }

  // PGN(129039) - AIS Class B Position Report
  public final static int aisClassBPositionReport_pgn = 129039;
  public final static class aisClassBPositionReport
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int userId = 2;
    public final static int longitude = 3;
    public final static int latitude = 4;
    public final static int positionAccuracy = 5;
    // positionAccuracy permitted values
    public static final class positionAccuracy_values
    {
      public static final int Low = 0;
      public static final int High = 1;
    }

    public final static int raim = 6;
    // raim permitted values
    public static final class raim_values
    {
      public static final int notinuse = 0;
      public static final int inuse = 1;
    }

    // 0-59 = UTC second when the report was generated
    public final static int timeStamp = 7;
    // timeStamp permitted values
    public static final class timeStamp_values
    {
      public static final int Notavailable = 60;
      public static final int Manualinputmode = 61;
      public static final int Deadreckoningmode = 62;
      public static final int Positioningsystemisinoperative = 63;
    }

    public final static int cog = 8;
    public final static int sog = 9;
    // Information used by the TDMA slot allocation algorithm and synchronization information
    public final static int communicationState = 10;
    public final static int aisTransceiverInformation = 11;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

    // True heading (rad)
    public final static int heading = 12;
    public final static int regionalApplication = 13;
    public final static int regionalApplication2 = 14;
    public final static int unitType = 15;
    // unitType permitted values
    public static final class unitType_values
    {
      public static final int SOTDMA = 0;
      public static final int CS = 1;
    }

    // Whether the unit can show messages 12 and 14
    public final static int integratedDisplay = 16;
    // integratedDisplay permitted values
    public static final class integratedDisplay_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int dsc = 17;
    // dsc permitted values
    public static final class dsc_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int band = 18;
    // band permitted values
    public static final class band_values
    {
      public static final int top525kHzofmarineband = 0;
      public static final int entiremarineband = 1;
    }

    // Whether device supports message 22
    public final static int canHandleMsg22 = 19;
    // canHandleMsg22 permitted values
    public static final class canHandleMsg22_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int aisMode = 20;
    // aisMode permitted values
    public static final class aisMode_values
    {
      public static final int Autonomous = 0;
      public static final int Assigned = 1;
    }

    public final static int aisCommunicationState = 21;
    // aisCommunicationState permitted values
    public static final class aisCommunicationState_values
    {
      public static final int SOTDMA = 0;
      public static final int ITDMA = 1;
    }

  }

  // PGN(129040) - AIS Class B Extended Position Report
  public final static int aisClassBExtendedPositionReport_pgn = 129040;
  public final static class aisClassBExtendedPositionReport
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int userId = 2;
    public final static int longitude = 3;
    public final static int latitude = 4;
    public final static int positionAccuracy = 5;
    // positionAccuracy permitted values
    public static final class positionAccuracy_values
    {
      public static final int Low = 0;
      public static final int High = 1;
    }

    public final static int aisRaimFlag = 6;
    // aisRaimFlag permitted values
    public static final class aisRaimFlag_values
    {
      public static final int notinuse = 0;
      public static final int inuse = 1;
    }

    // 0-59 = UTC second when the report was generated
    public final static int timeStamp = 7;
    // timeStamp permitted values
    public static final class timeStamp_values
    {
      public static final int Notavailable = 60;
      public static final int Manualinputmode = 61;
      public static final int Deadreckoningmode = 62;
      public static final int Positioningsystemisinoperative = 63;
    }

    public final static int cog = 8;
    public final static int sog = 9;
    public final static int regionalApplication = 10;
    public final static int regionalApplication2 = 11;
    // reserved
    public final static int reserved = 12;
    public final static int typeOfShip = 13;
    // typeOfShip permitted values
    public static final class typeOfShip_values
    {
      public static final int unavailable = 0;
      public static final int WingInGround = 20;
      public static final int WingInGround_nootherinformation_ = 29;
      public static final int Fishing = 30;
      public static final int Towing = 31;
      public static final int Towingexceeds200morwiderthan25m = 32;
      public static final int Engagedindredgingorunderwateroperations = 33;
      public static final int Engagedindivingoperations = 34;
      public static final int Engagedinmilitaryoperations = 35;
      public static final int Sailing = 36;
      public static final int Pleasure = 37;
      public static final int Highspeedcraft = 40;
      public static final int Highspeedcraftcarryingdangerousgoods = 41;
      public static final int HighspeedcrafthazardcatB = 42;
      public static final int HighspeedcrafthazardcatC = 43;
      public static final int HighspeedcrafthazardcatD = 44;
      public static final int Highspeedcraft_noadditionalinformation_ = 49;
      public static final int Pilotvessel = 50;
      public static final int SAR = 51;
      public static final int Tug = 52;
      public static final int Porttender = 53;
      public static final int Anti_pollution = 54;
      public static final int Lawenforcement = 55;
      public static final int Spare = 56;
      public static final int Spare_2 = 57;
      public static final int Medical = 58;
      public static final int RRResolutionNo_18 = 59;
      public static final int Passengership = 60;
      public static final int Passengership_noadditionalinformation_ = 69;
      public static final int Cargoship = 70;
      public static final int Cargoshipcarryingdangerousgoods = 71;
      public static final int CargoshiphazardcatB = 72;
      public static final int CargoshiphazardcatC = 73;
      public static final int CargoshiphazardcatD = 74;
      public static final int Cargoship_noadditionalinformation_ = 79;
      public static final int Tanker = 80;
      public static final int Tankercarryingdangerousgoods = 81;
      public static final int TankerhazardcatB = 82;
      public static final int TankerhazardcatC = 83;
      public static final int TankerhazardcatD = 84;
      public static final int Tanker_noadditionalinformation_ = 89;
      public static final int Other = 90;
      public static final int Othercarryingdangerousgoods = 91;
      public static final int OtherhazardcatB = 92;
      public static final int OtherhazardcatC = 93;
      public static final int OtherhazardcatD = 94;
      public static final int Other_noadditionalinformation_ = 99;
    }

    public final static int trueHeading = 14;
    public final static int reserved2 = 15;
    public final static int gnssType = 16;
    // gnssType permitted values
    public static final class gnssType_values
    {
      public static final int undefined = 0;
      public static final int GPS = 1;
      public static final int GLONASS = 2;
      public static final int GPS_GLONASS = 3;
      public static final int Loran_C = 4;
      public static final int Chayka = 5;
      public static final int integrated = 6;
      public static final int surveyed = 7;
      public static final int Galileo = 8;
    }

    public final static int length = 17;
    public final static int beam = 18;
    public final static int positionReferenceFromStarboard = 19;
    public final static int positionReferenceFromBow = 20;
    public final static int name = 21;
    public final static int dte = 22;
    // dte permitted values
    public static final class dte_values
    {
      public static final int Available = 0;
      public static final int Notavailable = 1;
    }

    public final static int aisMode = 23;
    public final static int reserved3 = 24;
    public final static int aisTransceiverInformation = 25;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

  }

  // PGN(129041) - AIS Aids to Navigation (AtoN) Report
  public final static int aisAidsToNavigationAtonReport_pgn = 129041;
  public final static class aisAidsToNavigationAtonReport
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int userId = 2;
    public final static int longitude = 3;
    public final static int latitude = 4;
    public final static int positionAccuracy = 5;
    // positionAccuracy permitted values
    public static final class positionAccuracy_values
    {
      public static final int Low = 0;
      public static final int High = 1;
    }

    public final static int aisRaimFlag = 6;
    // aisRaimFlag permitted values
    public static final class aisRaimFlag_values
    {
      public static final int notinuse = 0;
      public static final int inuse = 1;
    }

    // 0-59 = UTC second when the report was generated
    public final static int timeStamp = 7;
    // timeStamp permitted values
    public static final class timeStamp_values
    {
      public static final int Notavailable = 60;
      public static final int Manualinputmode = 61;
      public static final int Deadreckoningmode = 62;
      public static final int Positioningsystemisinoperative = 63;
    }

    public final static int lengthDiameter = 8;
    public final static int beamDiameter = 9;
    public final static int positionReferenceFromStarboardEdge = 10;
    public final static int positionReferenceFromTrueNorthFacingEdge = 11;
    public final static int atonType = 12;
    // atonType permitted values
    public static final class atonType_values
    {
      public static final int Default_TypeofAtoNnotspecified = 0;
      public static final int Referecepoint = 1;
      public static final int RACON = 2;
      public static final int Fixedstructureoff_shore = 3;
      public static final int Reservedforfutureuse = 4;
      public static final int Fixedlight_withoutsectors = 5;
      public static final int Fixedlight_withsectors = 6;
      public static final int Fixedleadinglightfront = 7;
      public static final int Fixedleadinglightrear = 8;
      public static final int Fixedbeacon_cardinalN = 9;
      public static final int Fixedbeacon_cardinalE = 10;
      public static final int Fixedbeacon_cardinalS = 11;
      public static final int Fixedbeacon_cardinalW = 12;
      public static final int Fixedbeacon_porthand = 13;
      public static final int Fixedbeacon_starboardhand = 14;
      public static final int Fixedbeacon_preferredchannelporthand = 15;
      public static final int Fixedbeacon_preferredchannelstarboardhand = 16;
      public static final int Fixedbeacon_isolateddanger = 17;
      public static final int Fixedbeacon_safewater = 18;
      public static final int FloatingAtoN_cardinalN = 20;
      public static final int FloatingAtoN_cardinalE = 21;
      public static final int FloatingAtoN_cardinalS = 22;
      public static final int FloatingAtoN_cardinalW = 23;
      public static final int FloatingAtoN_porthandmark = 24;
      public static final int FloatingAtoN_starboardhandmark = 25;
      public static final int FloatingAtoN_preferredchannelporthand = 26;
      public static final int FloatingAtoN_preferredchannelstarboardhand = 27;
      public static final int FloatingAtoN_isolateddanger = 28;
      public static final int FloatingAtoN_safewater = 29;
      public static final int FloatingAtoN_specialmark = 30;
      public static final int FloatingAtoN_lightvessel_LANBY_rigs = 31;
    }

    public final static int offPositionIndicator = 13;
    // offPositionIndicator permitted values
    public static final class offPositionIndicator_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int virtualAtonFlag = 14;
    // virtualAtonFlag permitted values
    public static final class virtualAtonFlag_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int assignedModeFlag = 15;
    // assignedModeFlag permitted values
    public static final class assignedModeFlag_values
    {
      public static final int Autonomousandcontinuous = 0;
      public static final int Assignedmode = 1;
    }

    public final static int aisSpare = 16;
    public final static int positionFixingDeviceType = 17;
    // positionFixingDeviceType permitted values
    public static final class positionFixingDeviceType_values
    {
      public static final int Default_undefined = 0;
      public static final int GPS = 1;
      public static final int GLONASS = 2;
      public static final int CombinedGPS_GLONASS = 3;
      public static final int Loran_C = 4;
      public static final int Chayka = 5;
      public static final int Integratednavigationsystem = 6;
      public static final int Surveyed = 7;
      public static final int Galileo = 8;
      public static final int InternalGNSS = 15;
    }

    public final static int reserved = 18;
    // 00000000 = default
    public final static int atonStatus = 19;
    public final static int aisTransceiverInformation = 20;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

    public final static int reserved2 = 21;
    public final static int atonName = 22;
  }

  // PGN(129044) - Datum
  public final static int datum_pgn = 129044;
  public final static class datum
  {
    // defined in IHO Publication S-60, Appendices B and C. First three chars are datum ID as per IHO tables. Fourth char is local datum subdivision code.
    public final static int localDatum = 0;
    public final static int deltaLatitude = 1;
    public final static int deltaLongitude = 2;
    public final static int deltaAltitude = 3;
    // defined in IHO Publication S-60, Appendices B and C. First three chars are datum ID as per IHO tables. Fourth char is local datum subdivision code.
    public final static int referenceDatum = 4;
  }

  // PGN(129045) - User Datum
  public final static int userDatum_pgn = 129045;
  public final static class userDatum
  {
    // Delta shift in X axis from WGS 84 (m)
    public final static int deltaX = 0;
    // Delta shift in Y axis from WGS 84 (m)
    public final static int deltaY = 1;
    // Delta shift in Z axis from WGS 84 (m)
    public final static int deltaZ = 2;
    // Rotational shift in X axis from WGS 84. Rotations presented use the geodetic sign convention.  When looking along the positive axis towards the origin, counter-clockwise rotations are positive.
    public final static int rotationInX = 3;
    // Rotational shift in Y axis from WGS 84. Rotations presented use the geodetic sign convention.  When looking along the positive axis towards the origin, counter-clockwise rotations are positive.
    public final static int rotationInY = 4;
    // Rotational shift in Z axis from WGS 84. Rotations presented use the geodetic sign convention.  When looking along the positive axis towards the origin, counter-clockwise rotations are positive.
    public final static int rotationInZ = 5;
    // Scale factor expressed in parts-per-million (ppm)
    public final static int scale = 6;
    // Semi-major axis (a) of the User Datum ellipsoid (m)
    public final static int ellipsoidSemiMajorAxis = 7;
    // Flattening (1/f) of the User Datum ellipsoid
    public final static int ellipsoidFlatteningInverse = 8;
    // 4 character code from IHO Publication S-60,Appendices B and C. First three chars are datum ID as per IHO tables. Fourth char is local datum subdivision code.
    public final static int datumName = 9;
  }

  // PGN(129283) - Cross Track Error
  public final static int crossTrackError_pgn = 129283;
  public final static class crossTrackError
  {
    public final static int sid = 0;
    public final static int xteMode = 1;
    // xteMode permitted values
    public static final class xteMode_values
    {
      public static final int Autonomous = 0;
      public static final int Differentialenhanced = 1;
      public static final int Estimated = 2;
      public static final int Simulator = 3;
      public static final int Manual = 4;
    }

    // reserved
    public final static int reserved = 2;
    public final static int navigationTerminated = 3;
    // navigationTerminated permitted values
    public static final class navigationTerminated_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int xte = 4;
  }

  // PGN(129284) - Navigation Data
  public final static int navigationData_pgn = 129284;
  public final static class navigationData
  {
    public final static int sid = 0;
    public final static int distanceToWaypoint = 1;
    public final static int courseBearingReference = 2;
    // courseBearingReference permitted values
    public static final class courseBearingReference_values
    {
      public static final int True = 0;
      public static final int Magnetic = 1;
    }

    public final static int perpendicularCrossed = 3;
    // perpendicularCrossed permitted values
    public static final class perpendicularCrossed_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int arrivalCircleEntered = 4;
    // arrivalCircleEntered permitted values
    public static final class arrivalCircleEntered_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int calculationType = 5;
    // calculationType permitted values
    public static final class calculationType_values
    {
      public static final int GreatCircle = 0;
      public static final int RhumbLine = 1;
    }

    // Seconds since midnight (s)
    public final static int etaTime = 6;
    // Days since January 1, 1970 (days)
    public final static int etaDate = 7;
    public final static int bearingOriginToDestinationWaypoint = 8;
    public final static int bearingPositionToDestinationWaypoint = 9;
    public final static int originWaypointNumber = 10;
    public final static int destinationWaypointNumber = 11;
    public final static int destinationLatitude = 12;
    public final static int destinationLongitude = 13;
    public final static int waypointClosingVelocity = 14;
  }

  // PGN(129285) - Navigation - Route/WP Information
  public final static int navigationRouteWpInformation_pgn = 129285;
  public final static class navigationRouteWpInformation
  {
    public final static int startRps = 0;
    public final static int nitems = 1;
    public final static int databaseId = 2;
    public final static int routeId = 3;
    public final static int navigationDirectionInRoute = 4;
    public final static int supplementaryRouteWpDataAvailable = 5;
    // Reserved
    public final static int reserved = 6;
    public final static int routeName = 7;
    // Reserved
    public final static int reserved2 = 8;
    public final static class rep
    {
      public final static int wpId = 0;
      public final static int wpName = 1;
      public final static int wpLatitude = 2;
      public final static int wpLongitude = 3;
    }
  }

  // PGN(129291) - Set & Drift, Rapid Update
  public final static int setDriftRapidUpdate_pgn = 129291;
  public final static class setDriftRapidUpdate
  {
    public final static int sid = 0;
    public final static int setReference = 1;
    // setReference permitted values
    public static final class setReference_values
    {
      public static final int True = 0;
      public static final int Magnetic = 1;
    }

    // Reserved
    public final static int reserved = 2;
    public final static int set = 3;
    public final static int drift = 4;
  }

  // PGN(129301) - Navigation - Route / Time to+from Mark
  public final static int navigationRouteTimeToFromMark_pgn = 129301;
  public final static class navigationRouteTimeToFromMark
  {
    public final static int sid = 0;
    // negative = elapsed since event, positive = time to go (s)
    public final static int timeToMark = 1;
    public final static int markType = 2;
    // markType permitted values
    public static final class markType_values
    {
      public static final int Collision = 0;
      public static final int Turningpoint = 1;
      public static final int Reference = 2;
      public static final int Wheelover = 3;
      public static final int Waypoint = 4;
    }

    // Reserved
    public final static int reserved = 3;
    public final static int markId = 4;
  }

  // PGN(129302) - Bearing and Distance between two Marks
  public final static int bearingAndDistanceBetweenTwoMarks_pgn = 129302;
  public final static class bearingAndDistanceBetweenTwoMarks
  {
    public final static int sid = 0;
    public final static int bearingReference = 1;
    public final static int calculationType = 2;
    // Reserved
    public final static int reserved = 3;
    public final static int bearingOriginToDestination = 4;
    public final static int distance = 5;
    public final static int originMarkType = 6;
    public final static int destinationMarkType = 7;
    public final static int originMarkId = 8;
    public final static int destinationMarkId = 9;
  }

  // PGN(129538) - GNSS Control Status
  public final static int gnssControlStatus_pgn = 129538;
  public final static class gnssControlStatus
  {
    // Will not use SV below this elevation
    public final static int svElevationMask = 0;
    // Will not report position above this PDOP
    public final static int pdopMask = 1;
    // Will report 2D position above this PDOP
    public final static int pdopSwitch = 2;
    // Will not use SV below this SNR
    public final static int snrMask = 3;
    public final static int gnssModeDesired = 4;
    // gnssModeDesired permitted values
    public static final class gnssModeDesired_values
    {
      public static final int A1D = 0;
      public static final int A2D = 1;
      public static final int A3D = 2;
      public static final int Auto = 3;
      public static final int Reserved = 4;
      public static final int Reserved2 = 5;
      public static final int Error = 6;
    }

    public final static int dgnssModeDesired = 5;
    // dgnssModeDesired permitted values
    public static final class dgnssModeDesired_values
    {
      public static final int noSBAS = 0;
      public static final int SBAS = 1;
      public static final int SBAS2 = 3;
    }

    public final static int positionVelocityFilter = 6;
    public final static int maxCorrectionAge = 7;
    public final static int antennaAltitudeFor2dMode = 8;
    public final static int useAntennaAltitudeFor2dMode = 9;
    // useAntennaAltitudeFor2dMode permitted values
    public static final class useAntennaAltitudeFor2dMode_values
    {
      public static final int uselast3Dheight = 0;
      public static final int Useantennaaltitude = 1;
    }

  }

  // PGN(129539) - GNSS DOPs
  public final static int gnssDops_pgn = 129539;
  public final static class gnssDops
  {
    public final static int sid = 0;
    public final static int desiredMode = 1;
    // desiredMode permitted values
    public static final class desiredMode_values
    {
      public static final int A1D = 0;
      public static final int A2D = 1;
      public static final int A3D = 2;
      public static final int Auto = 3;
      public static final int Reserved = 4;
      public static final int Reserved2 = 5;
      public static final int Error = 6;
    }

    public final static int actualMode = 2;
    // actualMode permitted values
    public static final class actualMode_values
    {
      public static final int A1D = 0;
      public static final int A2D = 1;
      public static final int A3D = 2;
      public static final int Auto = 3;
      public static final int Reserved = 4;
      public static final int Reserved2 = 5;
      public static final int Error = 6;
    }

    // Reserved
    public final static int reserved = 3;
    // Horizontal dilution of precision
    public final static int hdop = 4;
    // Vertical dilution of precision
    public final static int vdop = 5;
    // Time dilution of precision
    public final static int tdop = 6;
  }

  // PGN(129540) - GNSS Sats in View
  public final static int gnssSatsInView_pgn = 129540;
  public final static class gnssSatsInView
  {
    public final static int sid = 0;
    public final static int mode = 1;
    // mode permitted values
    public static final class mode_values
    {
      public static final int Rangeresidualsusedtocalculateposition = 3;
    }

    // Reserved
    public final static int reserved = 2;
    public final static int satsInView = 3;
    public final static class rep
    {
      public final static int prn = 0;
      public final static int elevation = 1;
      public final static int azimuth = 2;
      public final static int snr = 3;
      public final static int rangeResiduals = 4;
      public final static int status = 5;
      // status permitted values
      public static final class status_values
      {
        public static final int Nottracked = 0;
        public static final int Tracked = 1;
        public static final int Used = 2;
        public static final int Nottracked_Diff = 3;
        public static final int Tracked_Diff = 4;
        public static final int Used_Diff = 5;
      }

      // Reserved
      public final static int reserved2 = 6;
    }
  }

  // PGN(129541) - GPS Almanac Data
  public final static int gpsAlmanacData_pgn = 129541;
  public final static class gpsAlmanacData
  {
    public final static int prn = 0;
    public final static int gpsWeekNumber = 1;
    public final static int svHealthBits = 2;
    public final static int eccentricity = 3;
    public final static int almanacReferenceTime = 4;
    public final static int inclinationAngle = 5;
    public final static int rightOfRightAscension = 6;
    public final static int rootOfSemiMajorAxis = 7;
    public final static int argumentOfPerigee = 8;
    public final static int longitudeOfAscensionNode = 9;
    public final static int meanAnomaly = 10;
    public final static int clockParameter1 = 11;
    public final static int clockParameter2 = 12;
  }

  // PGN(129542) - GNSS Pseudorange Noise Statistics
  public final static int gnssPseudorangeNoiseStatistics_pgn = 129542;
  public final static class gnssPseudorangeNoiseStatistics
  {
    public final static int sid = 0;
    public final static int rmsOfPositionUncertainty = 1;
    public final static int stdOfMajorAxis = 2;
    public final static int stdOfMinorAxis = 3;
    public final static int orientationOfMajorAxis = 4;
    public final static int stdOfLatError = 5;
    public final static int stdOfLonError = 6;
    public final static int stdOfAltError = 7;
  }

  // PGN(129545) - GNSS RAIM Output
  public final static int gnssRaimOutput_pgn = 129545;
  public final static class gnssRaimOutput
  {
    public final static int sid = 0;
    public final static int integrityFlag = 1;
    // Reserved
    public final static int reserved = 2;
    public final static int latitudeExpectedError = 3;
    public final static int longitudeExpectedError = 4;
    public final static int altitudeExpectedError = 5;
    public final static int svIdOfMostLikelyFailedSat = 6;
    public final static int probabilityOfMissedDetection = 7;
    public final static int estimateOfPseudorangeBias = 8;
    public final static int stdDeviationOfBias = 9;
  }

  // PGN(129546) - GNSS RAIM Settings
  public final static int gnssRaimSettings_pgn = 129546;
  public final static class gnssRaimSettings
  {
    public final static int radialPositionErrorMaximumThreshold = 0;
    public final static int probabilityOfFalseAlarm = 1;
    public final static int probabilityOfMissedDetection = 2;
    public final static int pseudorangeResidualFilteringTimeConstant = 3;
  }

  // PGN(129547) - GNSS Pseudorange Error Statistics
  public final static int gnssPseudorangeErrorStatistics_pgn = 129547;
  public final static class gnssPseudorangeErrorStatistics
  {
    public final static int sid = 0;
    public final static int rmsStdDevOfRangeInputs = 1;
    public final static int stdDevOfMajorErrorEllipse = 2;
    public final static int stdDevOfMinorErrorEllipse = 3;
    public final static int orientationOfErrorEllipse = 4;
    public final static int stdDevLatError = 5;
    public final static int stdDevLonError = 6;
    public final static int stdDevAltError = 7;
  }

  // PGN(129549) - DGNSS Corrections
  public final static int dgnssCorrections_pgn = 129549;
  public final static class dgnssCorrections
  {
    public final static int sid = 0;
    public final static int referenceStationId = 1;
    public final static int referenceStationType = 2;
    public final static int timeOfCorrections = 3;
    public final static int stationHealth = 4;
    public final static int reservedBits = 5;
    public final static int satelliteId = 6;
    public final static int prc = 7;
    public final static int rrc = 8;
    public final static int udre = 9;
    public final static int iod = 10;
  }

  // PGN(129550) - GNSS Differential Correction Receiver Interface
  public final static int gnssDifferentialCorrectionReceiverInterface_pgn = 129550;
  public final static class gnssDifferentialCorrectionReceiverInterface
  {
    public final static int channel = 0;
    public final static int frequency = 1;
    public final static int serialInterfaceBitRate = 2;
    public final static int serialInterfaceDetectionMode = 3;
    public final static int differentialSource = 4;
    public final static int differentialOperationMode = 5;
  }

  // PGN(129551) - GNSS Differential Correction Receiver Signal
  public final static int gnssDifferentialCorrectionReceiverSignal_pgn = 129551;
  public final static class gnssDifferentialCorrectionReceiverSignal
  {
    public final static int sid = 0;
    public final static int channel = 1;
    public final static int signalStrength = 2;
    public final static int signalSnr = 3;
    public final static int frequency = 4;
    public final static int stationType = 5;
    public final static int stationId = 6;
    public final static int differentialSignalBitRate = 7;
    public final static int differentialSignalDetectionMode = 8;
    public final static int usedAsCorrectionSource = 9;
    // Reserved
    public final static int reserved = 10;
    public final static int differentialSource = 11;
    public final static int timeSinceLastSatDifferentialSync = 12;
    public final static int satelliteServiceIdNo = 13;
  }

  // PGN(129556) - GLONASS Almanac Data
  public final static int glonassAlmanacData_pgn = 129556;
  public final static class glonassAlmanacData
  {
    public final static int prn = 0;
    public final static int na = 1;
    public final static int cna = 2;
    public final static int hna = 3;
    public final static int EpsilonNa = 4;
    public final static int DeltatnaDot = 5;
    public final static int OmegaNa = 6;
    public final static int DeltaTna = 7;
    public final static int tna = 8;
    public final static int LambdaNa = 9;
    public final static int DeltaIna = 10;
    public final static int tca = 11;
    public final static int tna2 = 12;
  }

  // PGN(129792) - AIS DGNSS Broadcast Binary Message
  public final static int aisDgnssBroadcastBinaryMessage_pgn = 129792;
  public final static class aisDgnssBroadcastBinaryMessage
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    public final static int sourceId = 2;
    public final static int nmea2000Reserved = 3;
    public final static int aisTranceiverInformation = 4;
    public final static int spare = 5;
    public final static int longitude = 6;
    public final static int latitude = 7;
    public final static int nmea2000Reserved2 = 8;
    public final static int spare2 = 9;
    public final static int numberOfBitsInBinaryDataField = 10;
    public final static int binaryData = 11;
  }

  // PGN(129793) - AIS UTC and Date Report
  public final static int aisUtcAndDateReport_pgn = 129793;
  public final static class aisUtcAndDateReport
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int userId = 2;
    public final static int longitude = 3;
    public final static int latitude = 4;
    public final static int positionAccuracy = 5;
    // positionAccuracy permitted values
    public static final class positionAccuracy_values
    {
      public static final int Low = 0;
      public static final int High = 1;
    }

    public final static int raim = 6;
    // raim permitted values
    public static final class raim_values
    {
      public static final int notinuse = 0;
      public static final int inuse = 1;
    }

    // NMEA reserved to align next data on byte boundary
    public final static int reserved = 7;
    // Seconds since midnight (s)
    public final static int positionTime = 8;
    // Information used by the TDMA slot allocation algorithm and synchronization information
    public final static int communicationState = 9;
    public final static int aisTransceiverInformation = 10;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

    // Days since January 1, 1970 (days)
    public final static int positionDate = 11;
    // NMEA reserved to align next data on byte boundary
    public final static int reserved2 = 12;
    public final static int gnssType = 13;
    // gnssType permitted values
    public static final class gnssType_values
    {
      public static final int undefined = 0;
      public static final int GPS = 1;
      public static final int GLONASS = 2;
      public static final int GPS_GLONASS = 3;
      public static final int Loran_C = 4;
      public static final int Chayka = 5;
      public static final int integrated = 6;
      public static final int surveyed = 7;
      public static final int Galileo = 8;
    }

    public final static int spare = 14;
  }

  // PGN(129794) - AIS Class A Static and Voyage Related Data
  public final static int aisClassAStaticAndVoyageRelatedData_pgn = 129794;
  public final static class aisClassAStaticAndVoyageRelatedData
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int userId = 2;
    public final static int imoNumber = 3;
    public final static int callsign = 4;
    public final static int name = 5;
    public final static int typeOfShip = 6;
    // typeOfShip permitted values
    public static final class typeOfShip_values
    {
      public static final int unavailable = 0;
      public static final int WingInGround = 20;
      public static final int WingInGround_nootherinformation_ = 29;
      public static final int Fishing = 30;
      public static final int Towing = 31;
      public static final int Towingexceeds200morwiderthan25m = 32;
      public static final int Engagedindredgingorunderwateroperations = 33;
      public static final int Engagedindivingoperations = 34;
      public static final int Engagedinmilitaryoperations = 35;
      public static final int Sailing = 36;
      public static final int Pleasure = 37;
      public static final int Highspeedcraft = 40;
      public static final int Highspeedcraftcarryingdangerousgoods = 41;
      public static final int HighspeedcrafthazardcatB = 42;
      public static final int HighspeedcrafthazardcatC = 43;
      public static final int HighspeedcrafthazardcatD = 44;
      public static final int Highspeedcraft_noadditionalinformation_ = 49;
      public static final int Pilotvessel = 50;
      public static final int SAR = 51;
      public static final int Tug = 52;
      public static final int Porttender = 53;
      public static final int Anti_pollution = 54;
      public static final int Lawenforcement = 55;
      public static final int Spare = 56;
      public static final int Spare_2 = 57;
      public static final int Medical = 58;
      public static final int RRResolutionNo_18 = 59;
      public static final int Passengership = 60;
      public static final int Passengership_noadditionalinformation_ = 69;
      public static final int Cargoship = 70;
      public static final int Cargoshipcarryingdangerousgoods = 71;
      public static final int CargoshiphazardcatB = 72;
      public static final int CargoshiphazardcatC = 73;
      public static final int CargoshiphazardcatD = 74;
      public static final int Cargoship_noadditionalinformation_ = 79;
      public static final int Tanker = 80;
      public static final int Tankercarryingdangerousgoods = 81;
      public static final int TankerhazardcatB = 82;
      public static final int TankerhazardcatC = 83;
      public static final int TankerhazardcatD = 84;
      public static final int Tanker_noadditionalinformation_ = 89;
      public static final int Other = 90;
      public static final int Othercarryingdangerousgoods = 91;
      public static final int OtherhazardcatB = 92;
      public static final int OtherhazardcatC = 93;
      public static final int OtherhazardcatD = 94;
      public static final int Other_noadditionalinformation_ = 99;
    }

    public final static int length = 7;
    public final static int beam = 8;
    public final static int positionReferenceFromStarboard = 9;
    public final static int positionReferenceFromBow = 10;
    // Days since January 1, 1970 (days)
    public final static int etaDate = 11;
    // Seconds since midnight (s)
    public final static int etaTime = 12;
    public final static int draft = 13;
    public final static int destination = 14;
    public final static int aisVersionIndicator = 15;
    // aisVersionIndicator permitted values
    public static final class aisVersionIndicator_values
    {
      public static final int ITU_RM_1371_1 = 0;
      public static final int ITU_RM_1371_3 = 1;
    }

    public final static int gnssType = 16;
    // gnssType permitted values
    public static final class gnssType_values
    {
      public static final int undefined = 0;
      public static final int GPS = 1;
      public static final int GLONASS = 2;
      public static final int GPS_GLONASS = 3;
      public static final int Loran_C = 4;
      public static final int Chayka = 5;
      public static final int integrated = 6;
      public static final int surveyed = 7;
      public static final int Galileo = 8;
    }

    public final static int dte = 17;
    // dte permitted values
    public static final class dte_values
    {
      public static final int available = 0;
      public static final int notavailable = 1;
    }

    // reserved
    public final static int reserved = 18;
    public final static int aisTransceiverInformation = 19;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

  }

  // PGN(129795) - AIS Addressed Binary Message
  public final static int aisAddressedBinaryMessage_pgn = 129795;
  public final static class aisAddressedBinaryMessage
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int sourceId = 2;
    // reserved
    public final static int reserved = 3;
    public final static int aisTransceiverInformation = 4;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

    public final static int sequenceNumber = 5;
    public final static int destinationId = 6;
    // reserved
    public final static int reserved2 = 7;
    public final static int retransmitFlag = 8;
    // reserved
    public final static int reserved3 = 9;
    public final static int numberOfBitsInBinaryDataField = 10;
    public final static int binaryData = 11;
  }

  // PGN(129796) - AIS Acknowledge
  public final static int aisAcknowledge_pgn = 129796;
  public final static class aisAcknowledge
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int sourceId = 2;
    // reserved
    public final static int reserved = 3;
    public final static int aisTransceiverInformation = 4;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

    // reserved
    public final static int reserved2 = 5;
    public final static int destinationId1 = 6;
    // reserved
    public final static int sequenceNumberForId1 = 7;
    // reserved
    public final static int reserved3 = 8;
    // reserved
    public final static int sequenceNumberForIdN = 9;
  }

  // PGN(129797) - AIS Binary Broadcast Message
  public final static int aisBinaryBroadcastMessage_pgn = 129797;
  public final static class aisBinaryBroadcastMessage
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int sourceId = 2;
    // reserved
    public final static int reserved = 3;
    public final static int aisTransceiverInformation = 4;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

    // reserved
    public final static int reserved2 = 5;
    public final static int numberOfBitsInBinaryDataField = 6;
    public final static int binaryData = 7;
  }

  // PGN(129798) - AIS SAR Aircraft Position Report
  public final static int aisSarAircraftPositionReport_pgn = 129798;
  public final static class aisSarAircraftPositionReport
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int userId = 2;
    public final static int longitude = 3;
    public final static int latitude = 4;
    public final static int positionAccuracy = 5;
    // positionAccuracy permitted values
    public static final class positionAccuracy_values
    {
      public static final int Low = 0;
      public static final int High = 1;
    }

    public final static int raim = 6;
    // raim permitted values
    public static final class raim_values
    {
      public static final int notinuse = 0;
      public static final int inuse = 1;
    }

    // 0-59 = UTC second when the report was generated
    public final static int timeStamp = 7;
    // timeStamp permitted values
    public static final class timeStamp_values
    {
      public static final int Notavailable = 60;
      public static final int Manualinputmode = 61;
      public static final int Deadreckoningmode = 62;
      public static final int Positioningsystemisinoperative = 63;
    }

    public final static int cog = 8;
    public final static int sog = 9;
    // Information used by the TDMA slot allocation algorithm and synchronization information
    public final static int communicationState = 10;
    public final static int aisTransceiverInformation = 11;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

    public final static int altitude = 12;
    public final static int reservedForRegionalApplications = 13;
    public final static int dte = 14;
    // dte permitted values
    public static final class dte_values
    {
      public static final int Available = 0;
      public static final int Notavailable = 1;
    }

    // reserved
    public final static int reserved = 15;
  }

  // PGN(129799) - Radio Frequency/Mode/Power
  public final static int radioFrequencyModePower_pgn = 129799;
  public final static class radioFrequencyModePower
  {
    public final static int rxFrequency = 0;
    public final static int txFrequency = 1;
    public final static int radioChannel = 2;
    public final static int txPower = 3;
    public final static int mode = 4;
    public final static int channelBandwidth = 5;
  }

  // PGN(129800) - AIS UTC/Date Inquiry
  public final static int aisUtcDateInquiry_pgn = 129800;
  public final static class aisUtcDateInquiry
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int sourceId = 2;
    // reserved
    public final static int reserved = 3;
    public final static int aisTransceiverInformation = 4;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

    // reserved
    public final static int reserved2 = 5;
    public final static int destinationId = 6;
    // reserved
    public final static int reserved3 = 7;
  }

  // PGN(129801) - AIS Addressed Safety Related Message
  public final static int aisAddressedSafetyRelatedMessage_pgn = 129801;
  public final static class aisAddressedSafetyRelatedMessage
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int sourceId = 2;
    // reserved
    public final static int reserved = 3;
    public final static int aisTransceiverInformation = 4;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

    public final static int sequenceNumber = 5;
    public final static int destinationId = 6;
    // reserved
    public final static int reserved2 = 7;
    public final static int retransmitFlag = 8;
    // reserved
    public final static int reserved3 = 9;
    public final static int safetyRelatedText = 10;
  }

  // PGN(129802) - AIS Safety Related Broadcast Message
  public final static int aisSafetyRelatedBroadcastMessage_pgn = 129802;
  public final static class aisSafetyRelatedBroadcastMessage
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int sourceId = 2;
    // reserved
    public final static int reserved = 3;
    public final static int aisTransceiverInformation = 4;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

    // reserved
    public final static int reserved2 = 5;
    public final static int safetyRelatedText = 6;
  }

  // PGN(129803) - AIS Interrogation
  public final static int aisInterrogation_pgn = 129803;
  public final static class aisInterrogation
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int sourceId = 2;
    // reserved
    public final static int reserved = 3;
    public final static int aisTransceiverInformation = 4;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

    // reserved
    public final static int reserved2 = 5;
    public final static class rep
    {
      public final static int destinationId = 0;
      // reserved
      public final static int reserved3 = 1;
      public final static int messageIdA = 2;
      public final static int slotOffsetA = 3;
      // reserved
      public final static int reserved4 = 4;
      public final static int messageIdB = 5;
      public final static int slotOffsetB = 6;
      // reserved
      public final static int reserved5 = 7;
    }
  }

  // PGN(129804) - AIS Assignment Mode Command
  public final static int aisAssignmentModeCommand_pgn = 129804;
  public final static class aisAssignmentModeCommand
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int sourceId = 2;
    // reserved
    public final static int reserved = 3;
    public final static int aisTransceiverInformation = 4;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

    // reserved
    public final static int reserved2 = 5;
    public final static class rep
    {
      public final static int destinationId = 0;
      public final static int offset = 1;
      public final static int increment = 2;
    }
  }

  // PGN(129805) - AIS Data Link Management Message
  public final static int aisDataLinkManagementMessage_pgn = 129805;
  public final static class aisDataLinkManagementMessage
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int sourceId = 2;
    // reserved
    public final static int reserved = 3;
    public final static int aisTransceiverInformation = 4;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

    // reserved
    public final static int reserved2 = 5;
    public final static class rep
    {
      public final static int offset = 0;
      public final static int numberOfSlots = 1;
      public final static int timeout = 2;
      public final static int increment = 3;
    }
  }

  // PGN(129806) - AIS Channel Management
  public final static int aisChannelManagement_pgn = 129806;
  public final static class aisChannelManagement
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int sourceId = 2;
    // reserved
    public final static int reserved = 3;
    public final static int aisTransceiverInformation = 4;
    // aisTransceiverInformation permitted values
    public static final class aisTransceiverInformation_values
    {
      public static final int ChannelAVDLreception = 0;
      public static final int ChannelBVDLreception = 1;
      public static final int ChannelAVDLtransmission = 2;
      public static final int ChannelBVDLtransmission = 3;
      public static final int Owninformationnotbroadcast = 4;
      public static final int Reserved = 5;
    }

    // reserved
    public final static int reserved2 = 5;
    public final static int channelA = 6;
    public final static int channelB = 7;
    // reserved
    public final static int reserved3 = 8;
    // reserved
    public final static int power = 9;
    public final static int txRxMode = 10;
    public final static int northEastLongitudeCorner1 = 11;
    public final static int northEastLatitudeCorner1 = 12;
    public final static int southWestLongitudeCorner1 = 13;
    public final static int southWestLatitudeCorner2 = 14;
    // reserved
    public final static int reserved4 = 15;
    public final static int addressedOrBroadcastMessageIndicator = 16;
    public final static int channelABandwidth = 17;
    public final static int channelBBandwidth = 18;
    // reserved
    public final static int reserved5 = 19;
    public final static int transitionalZoneSize = 20;
  }

  // PGN(129807) - AIS Class B Group Assignment
  public final static int aisClassBGroupAssignment_pgn = 129807;
  public final static class aisClassBGroupAssignment
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int sourceId = 2;
    // reserved
    public final static int reserved = 3;
    public final static int txRxMode = 4;
    // reserved
    public final static int reserved2 = 5;
    public final static int northEastLongitudeCorner1 = 6;
    public final static int northEastLatitudeCorner1 = 7;
    public final static int southWestLongitudeCorner1 = 8;
    public final static int southWestLatitudeCorner2 = 9;
    public final static int stationType = 10;
    // reserved
    public final static int reserved3 = 11;
    public final static int shipAndCargoFilter = 12;
    // reserved
    public final static int reserved4 = 13;
    public final static int reportingInterval = 14;
    public final static int quietTime = 15;
  }

  // PGN(129808) - DSC Distress Call Information
  public final static int dscDistressCallInformation_pgn = 129808;
  public final static class dscDistressCallInformation
  {
    public final static int dscFormat = 0;
    // dscFormat permitted values
    public static final class dscFormat_values
    {
      public static final int Geographicalarea = 102;
      public static final int Distress = 112;
      public static final int Commoninterest = 114;
      public static final int Allships = 116;
      public static final int Individualstations = 120;
      public static final int Non_callingpurpose = 121;
      public static final int Individualstationautomatic = 123;
    }

    // Distress
    public final static int dscCategory = 1;
    // MMSI, Geographic Area or blank
    public final static int dscMessageAddress = 2;
    public final static int natureOfDistress = 3;
    // natureOfDistress permitted values
    public static final class natureOfDistress_values
    {
      public static final int Fire = 100;
      public static final int Flooding = 101;
      public static final int Collision = 102;
      public static final int Grounding = 103;
      public static final int Listing = 104;
      public static final int Sinking = 105;
      public static final int Disabledandadrift = 106;
      public static final int Undesignated = 107;
      public static final int Abandoningship = 108;
      public static final int Piracy = 109;
      public static final int Manoverboard = 110;
      public static final int EPIRBemission = 112;
    }

    public final static int subsequentCommunicationModeOr2ndTelecommand = 4;
    // subsequentCommunicationModeOr2ndTelecommand permitted values
    public static final class subsequentCommunicationModeOr2ndTelecommand_values
    {
      public static final int Noreasongiven = 100;
      public static final int CongestionatMSC = 101;
      public static final int Busy = 102;
      public static final int Queueindication = 103;
      public static final int Stationbarred = 104;
      public static final int Nooperatoravailable = 105;
      public static final int Operatortemporarilyunavailable = 106;
      public static final int Equipmentdisabled = 107;
      public static final int Unabletouseproposedchannel = 108;
      public static final int Unabletouseproposedmode = 109;
      public static final int ShipsandaircraftofStatesnotpartiestoanarmedconflict = 110;
      public static final int Medicaltransports = 111;
      public static final int Payphone_publiccalloffice = 112;
      public static final int Fax_data = 113;
      public static final int Noinformation = 126;
    }

    public final static int proposedRxFrequencyChannel = 5;
    public final static int proposedTxFrequencyChannel = 6;
    public final static int telephoneNumber = 7;
    // offset depends on previous field, as do all following fields (deg)
    public final static int latitudeOfVesselReported = 8;
    public final static int longitudeOfVesselReported = 9;
    // Seconds since midnight (s)
    public final static int timeOfPosition = 10;
    public final static int mmsiOfShipInDistress = 11;
    public final static int dscEosSymbol = 12;
    public final static int expansionEnabled = 13;
    // expansionEnabled permitted values
    public static final class expansionEnabled_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    // reserved
    public final static int reserved = 14;
    public final static int callingRxFrequencyChannel = 15;
    public final static int callingTxFrequencyChannel = 16;
    // Seconds since midnight (s)
    public final static int timeOfReceipt = 17;
    // Days since January 1, 1970 (days)
    public final static int dateOfReceipt = 18;
    public final static int dscEquipmentAssignedMessageId = 19;
    public final static class rep
    {
      public final static int dscExpansionFieldSymbol = 0;
      // dscExpansionFieldSymbol permitted values
      public static final class dscExpansionFieldSymbol_values
      {
        public static final int Enhancedposition = 100;
        public static final int Sourceanddatumofposition = 101;
        public static final int SOG = 102;
        public static final int COG = 103;
        public static final int Additionalstationidentification = 104;
        public static final int Enhancedgeographicarea = 105;
        public static final int Numberofpersonsonboard = 106;
      }

      public final static int dscExpansionFieldData = 1;
    }
  }

  // PGN(129808) - DSC Call Information
  public final static int dscCallInformation_pgn = 129808;
  public final static class dscCallInformation
  {
    public final static int dscFormatSymbol = 0;
    // dscFormatSymbol permitted values
    public static final class dscFormatSymbol_values
    {
      public static final int Geographicalarea = 102;
      public static final int Distress = 112;
      public static final int Commoninterest = 114;
      public static final int Allships = 116;
      public static final int Individualstations = 120;
      public static final int Non_callingpurpose = 121;
      public static final int Individualstationautomatic = 123;
    }

    public final static int dscCategorySymbol = 1;
    // dscCategorySymbol permitted values
    public static final class dscCategorySymbol_values
    {
      public static final int Routine = 100;
      public static final int Safety = 108;
      public static final int Urgency = 110;
      public static final int Distress = 112;
    }

    // MMSI, Geographic Area or blank
    public final static int dscMessageAddress = 2;
    public final static int A1stTelecommand = 3;
    // A1stTelecommand permitted values
    public static final class A1stTelecommand_values
    {
      public static final int F3E_G3EAllmodesTP = 100;
      public static final int F3E_G3EduplexTP = 101;
      public static final int Polling = 103;
      public static final int Unabletocomply = 104;
      public static final int Endofcall = 105;
      public static final int Data = 106;
      public static final int J3ETP = 109;
      public static final int Distressacknowledgement = 110;
      public static final int Distressrelay = 112;
      public static final int F1B_J2BTTY_FEC = 113;
      public static final int F1B_J2BTTY_ARQ = 115;
      public static final int Test = 118;
      public static final int Shippositionorlocationregistrationupdating = 121;
      public static final int Noinformation = 126;
    }

    public final static int subsequentCommunicationModeOr2ndTelecommand = 4;
    // subsequentCommunicationModeOr2ndTelecommand permitted values
    public static final class subsequentCommunicationModeOr2ndTelecommand_values
    {
      public static final int Noreasongiven = 100;
      public static final int CongestionatMSC = 101;
      public static final int Busy = 102;
      public static final int Queueindication = 103;
      public static final int Stationbarred = 104;
      public static final int Nooperatoravailable = 105;
      public static final int Operatortemporarilyunavailable = 106;
      public static final int Equipmentdisabled = 107;
      public static final int Unabletouseproposedchannel = 108;
      public static final int Unabletouseproposedmode = 109;
      public static final int ShipsandaircraftofStatesnotpartiestoanarmedconflict = 110;
      public static final int Medicaltransports = 111;
      public static final int Payphone_publiccalloffice = 112;
      public static final int Fax_data = 113;
      public static final int Noinformation = 126;
    }

    public final static int proposedRxFrequencyChannel = 5;
    public final static int proposedTxFrequencyChannel = 6;
    public final static int telephoneNumber = 7;
    // offset depends on previous field, as do all following fields (deg)
    public final static int latitudeOfVesselReported = 8;
    public final static int longitudeOfVesselReported = 9;
    // Seconds since midnight (s)
    public final static int timeOfPosition = 10;
    public final static int mmsiOfShipInDistress = 11;
    public final static int dscEosSymbol = 12;
    public final static int expansionEnabled = 13;
    // expansionEnabled permitted values
    public static final class expansionEnabled_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    // reserved
    public final static int reserved = 14;
    public final static int callingRxFrequencyChannel = 15;
    public final static int callingTxFrequencyChannel = 16;
    // Seconds since midnight (s)
    public final static int timeOfReceipt = 17;
    // Days since January 1, 1970 (days)
    public final static int dateOfReceipt = 18;
    public final static int dscEquipmentAssignedMessageId = 19;
    public final static class rep
    {
      public final static int dscExpansionFieldSymbol = 0;
      // dscExpansionFieldSymbol permitted values
      public static final class dscExpansionFieldSymbol_values
      {
        public static final int Enhancedposition = 100;
        public static final int Sourceanddatumofposition = 101;
        public static final int SOG = 102;
        public static final int COG = 103;
        public static final int Additionalstationidentification = 104;
        public static final int Enhancedgeographicarea = 105;
        public static final int Numberofpersonsonboard = 106;
      }

      public final static int dscExpansionFieldData = 1;
    }
  }

  // PGN(129809) - AIS Class B static data (msg 24 Part A)
  public final static int aisClassBStaticDataMsg24PartA_pgn = 129809;
  public final static class aisClassBStaticDataMsg24PartA
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int userId = 2;
    public final static int name = 3;
  }

  // PGN(129810) - AIS Class B static data (msg 24 Part B)
  public final static int aisClassBStaticDataMsg24PartB_pgn = 129810;
  public final static class aisClassBStaticDataMsg24PartB
  {
    public final static int messageId = 0;
    public final static int repeatIndicator = 1;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int userId = 2;
    public final static int typeOfShip = 3;
    // typeOfShip permitted values
    public static final class typeOfShip_values
    {
      public static final int unavailable = 0;
      public static final int WingInGround = 20;
      public static final int WingInGround_nootherinformation_ = 29;
      public static final int Fishing = 30;
      public static final int Towing = 31;
      public static final int Towingexceeds200morwiderthan25m = 32;
      public static final int Engagedindredgingorunderwateroperations = 33;
      public static final int Engagedindivingoperations = 34;
      public static final int Engagedinmilitaryoperations = 35;
      public static final int Sailing = 36;
      public static final int Pleasure = 37;
      public static final int Highspeedcraft = 40;
      public static final int Highspeedcraftcarryingdangerousgoods = 41;
      public static final int HighspeedcrafthazardcatB = 42;
      public static final int HighspeedcrafthazardcatC = 43;
      public static final int HighspeedcrafthazardcatD = 44;
      public static final int Highspeedcraft_noadditionalinformation_ = 49;
      public static final int Pilotvessel = 50;
      public static final int SAR = 51;
      public static final int Tug = 52;
      public static final int Porttender = 53;
      public static final int Anti_pollution = 54;
      public static final int Lawenforcement = 55;
      public static final int Spare = 56;
      public static final int Spare_2 = 57;
      public static final int Medical = 58;
      public static final int RRResolutionNo_18 = 59;
      public static final int Passengership = 60;
      public static final int Passengership_noadditionalinformation_ = 69;
      public static final int Cargoship = 70;
      public static final int Cargoshipcarryingdangerousgoods = 71;
      public static final int CargoshiphazardcatB = 72;
      public static final int CargoshiphazardcatC = 73;
      public static final int CargoshiphazardcatD = 74;
      public static final int Cargoship_noadditionalinformation_ = 79;
      public static final int Tanker = 80;
      public static final int Tankercarryingdangerousgoods = 81;
      public static final int TankerhazardcatB = 82;
      public static final int TankerhazardcatC = 83;
      public static final int TankerhazardcatD = 84;
      public static final int Tanker_noadditionalinformation_ = 89;
      public static final int Other = 90;
      public static final int Othercarryingdangerousgoods = 91;
      public static final int OtherhazardcatB = 92;
      public static final int OtherhazardcatC = 93;
      public static final int OtherhazardcatD = 94;
      public static final int Other_noadditionalinformation_ = 99;
    }

    public final static int vendorId = 4;
    public final static int callsign = 5;
    public final static int length = 6;
    public final static int beam = 7;
    public final static int positionReferenceFromStarboard = 8;
    public final static int positionReferenceFromBow = 9;
    // MMSI of mother ship sent by daughter vessels (MMSI)
    public final static int mothershipUserId = 10;
    // reserved
    public final static int reserved = 11;
    public final static int spare = 12;
  }

  // PGN(130060) - Label
  public final static int label_pgn = 130060;
  public final static class label
  {
  }

  // PGN(130061) - Channel Source Configuration
  public final static int channelSourceConfiguration_pgn = 130061;
  public final static class channelSourceConfiguration
  {
  }

  // PGN(130064) - Route and WP Service - Database List
  public final static int routeAndWpServiceDatabaseList_pgn = 130064;
  public final static class routeAndWpServiceDatabaseList
  {
    public final static int startDatabaseId = 0;
    public final static int nitems = 1;
    public final static int numberOfDatabasesAvailable = 2;
    public final static class rep
    {
      public final static int databaseId = 0;
      public final static int databaseName = 1;
      // Seconds since midnight (s)
      public final static int databaseTimestamp = 2;
      // Days since January 1, 1970 (days)
      public final static int databaseDatestamp = 3;
      public final static int wpPositionResolution = 4;
      // reserved
      public final static int reserved = 5;
      public final static int numberOfRoutesInDatabase = 6;
      public final static int numberOfWpsInDatabase = 7;
      public final static int numberOfBytesInDatabase = 8;
    }
  }

  // PGN(130065) - Route and WP Service - Route List
  public final static int routeAndWpServiceRouteList_pgn = 130065;
  public final static class routeAndWpServiceRouteList
  {
    public final static int startRouteId = 0;
    public final static int nitems = 1;
    public final static int numberOfRoutesInDatabase = 2;
    public final static class rep
    {
      public final static int databaseId = 0;
      public final static int routeId = 1;
      public final static int routeName = 2;
      // reserved
      public final static int reserved = 3;
      public final static int wpIdentificationMethod = 4;
      public final static int routeStatus = 5;
    }
  }

  // PGN(130066) - Route and WP Service - Route/WP-List Attributes
  public final static int routeAndWpServiceRouteWpListAttributes_pgn = 130066;
  public final static class routeAndWpServiceRouteWpListAttributes
  {
    public final static int databaseId = 0;
    public final static int routeId = 1;
    public final static int routeWpListName = 2;
    // Seconds since midnight (s)
    public final static int routeWpListTimestamp = 3;
    // Days since January 1, 1970 (days)
    public final static int routeWpListDatestamp = 4;
    public final static int changeAtLastTimestamp = 5;
    public final static int numberOfWpsInTheRouteWpList = 6;
    public final static int criticalSupplementaryParameters = 7;
    public final static int navigationMethod = 8;
    public final static int wpIdentificationMethod = 9;
    public final static int routeStatus = 10;
    public final static int xteLimitForTheRoute = 11;
  }

  // PGN(130067) - Route and WP Service - Route - WP Name & Position
  public final static int routeAndWpServiceRouteWpNamePosition_pgn = 130067;
  public final static class routeAndWpServiceRouteWpNamePosition
  {
    public final static int startRps = 0;
    public final static int nitems = 1;
    public final static int numberOfWpsInTheRouteWpList = 2;
    public final static int databaseId = 3;
    public final static int routeId = 4;
    public final static class rep
    {
      public final static int wpId = 0;
      public final static int wpName = 1;
      public final static int wpLatitude = 2;
      public final static int wpLongitude = 3;
    }
  }

  // PGN(130068) - Route and WP Service - Route - WP Name
  public final static int routeAndWpServiceRouteWpName_pgn = 130068;
  public final static class routeAndWpServiceRouteWpName
  {
    public final static int startRps = 0;
    public final static int nitems = 1;
    public final static int numberOfWpsInTheRouteWpList = 2;
    public final static int databaseId = 3;
    public final static int routeId = 4;
    public final static class rep
    {
      public final static int wpId = 0;
      public final static int wpName = 1;
    }
  }

  // PGN(130069) - Route and WP Service - XTE Limit & Navigation Method
  public final static int routeAndWpServiceXteLimitNavigationMethod_pgn = 130069;
  public final static class routeAndWpServiceXteLimitNavigationMethod
  {
    public final static int startRps = 0;
    public final static int nitems = 1;
    public final static int numberOfWpsWithASpecificXteLimitOrNavMethod = 2;
    public final static class rep
    {
      public final static int databaseId = 0;
      public final static int routeId = 1;
      public final static int rps = 2;
      public final static int xteLimitInTheLegAfterWp = 3;
      public final static int navMethodInTheLegAfterWp = 4;
      public final static int reserved = 5;
    }
  }

  // PGN(130070) - Route and WP Service - WP Comment
  public final static int routeAndWpServiceWpComment_pgn = 130070;
  public final static class routeAndWpServiceWpComment
  {
    public final static int startId = 0;
    public final static int nitems = 1;
    public final static int numberOfWpsWithComments = 2;
    public final static int databaseId = 3;
    public final static int routeId = 4;
    public final static class rep
    {
      public final static int wpIdRps = 0;
      public final static int comment = 1;
    }
  }

  // PGN(130071) - Route and WP Service - Route Comment
  public final static int routeAndWpServiceRouteComment_pgn = 130071;
  public final static class routeAndWpServiceRouteComment
  {
    public final static int startRouteId = 0;
    public final static int nitems = 1;
    public final static int numberOfRoutesWithComments = 2;
    public final static int databaseId = 3;
    public final static class rep
    {
      public final static int routeId = 0;
      public final static int comment = 1;
    }
  }

  // PGN(130072) - Route and WP Service - Database Comment
  public final static int routeAndWpServiceDatabaseComment_pgn = 130072;
  public final static class routeAndWpServiceDatabaseComment
  {
    public final static int startDatabaseId = 0;
    public final static int nitems = 1;
    public final static int numberOfDatabasesWithComments = 2;
    public final static class rep
    {
      public final static int databaseId = 0;
      public final static int comment = 1;
    }
  }

  // PGN(130073) - Route and WP Service - Radius of Turn
  public final static int routeAndWpServiceRadiusOfTurn_pgn = 130073;
  public final static class routeAndWpServiceRadiusOfTurn
  {
    public final static int startRps = 0;
    public final static int nitems = 1;
    public final static int numberOfWpsWithASpecificRadiusOfTurn = 2;
    public final static int databaseId = 3;
    public final static int routeId = 4;
    public final static class rep
    {
      public final static int rps = 0;
      public final static int radiusOfTurn = 1;
    }
  }

  // PGN(130074) - Route and WP Service - WP List - WP Name & Position
  public final static int routeAndWpServiceWpListWpNamePosition_pgn = 130074;
  public final static class routeAndWpServiceWpListWpNamePosition
  {
    public final static int startWpId = 0;
    public final static int nitems = 1;
    public final static int numberOfValidWpsInTheWpList = 2;
    public final static int databaseId = 3;
    // reserved
    public final static int reserved = 4;
    public final static class rep
    {
      public final static int wpId = 0;
      public final static int wpName = 1;
      public final static int wpLatitude = 2;
      public final static int wpLongitude = 3;
    }
  }

  // PGN(130306) - Wind Data
  public final static int windData_pgn = 130306;
  public final static class windData
  {
    public final static int sid = 0;
    public final static int windSpeed = 1;
    public final static int windAngle = 2;
    public final static int reference = 3;
    // reference permitted values
    public static final class reference_values
    {
      public static final int True_groundreferencedtoNorth_ = 0;
      public static final int Magnetic_groundreferencedtoMagneticNorth_ = 1;
      public static final int Apparent = 2;
      public static final int True_boatreferenced_ = 3;
      public static final int True_waterreferenced_ = 4;
    }

  }

  // PGN(130310) - Environmental Parameters
  public final static int environmentalParameters_pgn = 130310;
  public final static class environmentalParameters
  {
    public final static int sid = 0;
    public final static int waterTemperature = 1;
    public final static int outsideAmbientAirTemperature = 2;
    public final static int atmosphericPressure = 3;
  }

  // PGN(130311) - Environmental Parameters
  public final static int environmentalParameters2_pgn = 130311;
  public final static class environmentalParameters2
  {
    public final static int sid = 0;
    public final static int temperatureSource = 1;
    // temperatureSource permitted values
    public static final class temperatureSource_values
    {
      public static final int SeaTemperature = 0;
      public static final int OutsideTemperature = 1;
      public static final int InsideTemperature = 2;
      public static final int EngineRoomTemperature = 3;
      public static final int MainCabinTemperature = 4;
      public static final int LiveWellTemperature = 5;
      public static final int BaitWellTemperature = 6;
      public static final int RefridgerationTemperature = 7;
      public static final int HeatingSystemTemperature = 8;
      public static final int DewPointTemperature = 9;
      public static final int ApparentWindChillTemperature = 10;
      public static final int TheoreticalWindChillTemperature = 11;
      public static final int HeatIndexTemperature = 12;
      public static final int FreezerTemperature = 13;
      public static final int ExhaustGasTemperature = 14;
    }

    public final static int humiditySource = 2;
    // humiditySource permitted values
    public static final class humiditySource_values
    {
      public static final int Inside = 0;
      public static final int Outside = 1;
    }

    public final static int temperature = 3;
    public final static int humidity = 4;
    public final static int atmosphericPressure = 5;
  }

  // PGN(130312) - Temperature
  public final static int temperature_pgn = 130312;
  public final static class temperature
  {
    public final static int sid = 0;
    public final static int instance = 1;
    public final static int source = 2;
    // source permitted values
    public static final class source_values
    {
      public static final int SeaTemperature = 0;
      public static final int OutsideTemperature = 1;
      public static final int InsideTemperature = 2;
      public static final int EngineRoomTemperature = 3;
      public static final int MainCabinTemperature = 4;
      public static final int LiveWellTemperature = 5;
      public static final int BaitWellTemperature = 6;
      public static final int RefridgerationTemperature = 7;
      public static final int HeatingSystemTemperature = 8;
      public static final int DewPointTemperature = 9;
      public static final int ApparentWindChillTemperature = 10;
      public static final int TheoreticalWindChillTemperature = 11;
      public static final int HeatIndexTemperature = 12;
      public static final int FreezerTemperature = 13;
      public static final int ExhaustGasTemperature = 14;
    }

    public final static int actualTemperature = 3;
    public final static int setTemperature = 4;
  }

  // PGN(130313) - Humidity
  public final static int humidity_pgn = 130313;
  public final static class humidity
  {
    public final static int sid = 0;
    public final static int instance = 1;
    public final static int source = 2;
    // source permitted values
    public static final class source_values
    {
      public static final int Inside = 0;
      public static final int Outside = 1;
    }

    public final static int actualHumidity = 3;
    public final static int setHumidity = 4;
  }

  // PGN(130314) - Actual Pressure
  public final static int actualPressure_pgn = 130314;
  public final static class actualPressure
  {
    public final static int sid = 0;
    public final static int instance = 1;
    public final static int source = 2;
    // source permitted values
    public static final class source_values
    {
      public static final int Atmospheric = 0;
      public static final int Water = 1;
      public static final int Steam = 2;
      public static final int CompressedAir = 3;
      public static final int Hydraulic = 4;
    }

    public final static int pressure = 3;
  }

  // PGN(130315) - Set Pressure
  public final static int setPressure_pgn = 130315;
  public final static class setPressure
  {
    public final static int sid = 0;
    public final static int instance = 1;
    public final static int source = 2;
    // source permitted values
    public static final class source_values
    {
      public static final int Atmospheric = 0;
      public static final int Water = 1;
      public static final int Steam = 2;
      public static final int CompressedAir = 3;
      public static final int Hydraulic = 4;
    }

    public final static int pressure = 3;
  }

  // PGN(130316) - Temperature Extended Range
  public final static int temperatureExtendedRange_pgn = 130316;
  public final static class temperatureExtendedRange
  {
    public final static int sid = 0;
    public final static int instance = 1;
    public final static int source = 2;
    // source permitted values
    public static final class source_values
    {
      public static final int SeaTemperature = 0;
      public static final int OutsideTemperature = 1;
      public static final int InsideTemperature = 2;
      public static final int EngineRoomTemperature = 3;
      public static final int MainCabinTemperature = 4;
      public static final int LiveWellTemperature = 5;
      public static final int BaitWellTemperature = 6;
      public static final int RefridgerationTemperature = 7;
      public static final int HeatingSystemTemperature = 8;
      public static final int DewPointTemperature = 9;
      public static final int ApparentWindChillTemperature = 10;
      public static final int TheoreticalWindChillTemperature = 11;
      public static final int HeatIndexTemperature = 12;
      public static final int FreezerTemperature = 13;
      public static final int ExhaustGasTemperature = 14;
    }

    public final static int temperature = 3;
    public final static int setTemperature = 4;
  }

  // PGN(130320) - Tide Station Data
  public final static int tideStationData_pgn = 130320;
  public final static class tideStationData
  {
    public final static int mode = 0;
    // mode permitted values
    public static final class mode_values
    {
      public static final int Autonomous = 0;
      public static final int Differentialenhanced = 1;
      public static final int Estimated = 2;
      public static final int Simulator = 3;
      public static final int Manual = 4;
    }

    public final static int tideTendency = 1;
    // tideTendency permitted values
    public static final class tideTendency_values
    {
      public static final int Falling = 0;
      public static final int Rising = 1;
    }

    public final static int reserved = 2;
    // Days since January 1, 1970 (days)
    public final static int measurementDate = 3;
    // Seconds since midnight (s)
    public final static int measurementTime = 4;
    public final static int stationLatitude = 5;
    public final static int stationLongitude = 6;
    // Relative to MLLW (m)
    public final static int tideLevel = 7;
    public final static int tideLevelStandardDeviation = 8;
    public final static int stationId = 9;
    public final static int stationName = 10;
  }

  // PGN(130321) - Salinity Station Data
  public final static int salinityStationData_pgn = 130321;
  public final static class salinityStationData
  {
    public final static int mode = 0;
    // mode permitted values
    public static final class mode_values
    {
      public static final int Autonomous = 0;
      public static final int Differentialenhanced = 1;
      public static final int Estimated = 2;
      public static final int Simulator = 3;
      public static final int Manual = 4;
    }

    public final static int reserved = 1;
    // Days since January 1, 1970 (days)
    public final static int measurementDate = 2;
    // Seconds since midnight (s)
    public final static int measurementTime = 3;
    public final static int stationLatitude = 4;
    public final static int stationLongitude = 5;
    // The average Salinity of ocean water is about 35 grams of salts per kilogram of sea water (g/kg), usually written as 35 ppt which is read as 35 parts per thousand. (ppt)
    public final static int salinity = 6;
    public final static int waterTemperature = 7;
    public final static int stationId = 8;
    public final static int stationName = 9;
  }

  // PGN(130322) - Current Station Data
  public final static int currentStationData_pgn = 130322;
  public final static class currentStationData
  {
    public final static int mode = 0;
    public final static int reserved = 1;
    // Days since January 1, 1970 (days)
    public final static int measurementDate = 2;
    // Seconds since midnight (s)
    public final static int measurementTime = 3;
    public final static int stationLatitude = 4;
    public final static int stationLongitude = 5;
    // Depth below transducer (m)
    public final static int measurementDepth = 6;
    public final static int currentSpeed = 7;
    public final static int currentFlowDirection = 8;
    public final static int waterTemperature = 9;
    public final static int stationId = 10;
    public final static int stationName = 11;
  }

  // PGN(130323) - Meteorological Station Data
  public final static int meteorologicalStationData_pgn = 130323;
  public final static class meteorologicalStationData
  {
    public final static int mode = 0;
    public final static int reserved = 1;
    // Days since January 1, 1970 (days)
    public final static int measurementDate = 2;
    // Seconds since midnight (s)
    public final static int measurementTime = 3;
    public final static int stationLatitude = 4;
    public final static int stationLongitude = 5;
    public final static int windSpeed = 6;
    public final static int windDirection = 7;
    public final static int windReference = 8;
    // windReference permitted values
    public static final class windReference_values
    {
      public static final int True_groundreferencedtoNorth_ = 0;
      public static final int Magnetic_groundreferencedtoMagneticNorth_ = 1;
      public static final int Apparent = 2;
      public static final int True_boatreferenced_ = 3;
      public static final int True_waterreferenced_ = 4;
    }

    // reserved ()
    public final static int reserved2 = 9;
    public final static int windGusts = 10;
    public final static int atmosphericPressure = 11;
    public final static int ambientTemperature = 12;
    public final static int stationId = 13;
    public final static int stationName = 14;
  }

  // PGN(130324) - Moored Buoy Station Data
  public final static int mooredBuoyStationData_pgn = 130324;
  public final static class mooredBuoyStationData
  {
    public final static int mode = 0;
    public final static int reserved = 1;
    // Days since January 1, 1970 (days)
    public final static int measurementDate = 2;
    // Seconds since midnight (s)
    public final static int measurementTime = 3;
    public final static int stationLatitude = 4;
    public final static int stationLongitude = 5;
    public final static int windSpeed = 6;
    public final static int windDirection = 7;
    public final static int windReference = 8;
    // windReference permitted values
    public static final class windReference_values
    {
      public static final int True_groundreferencedtoNorth_ = 0;
      public static final int Magnetic_groundreferencedtoMagneticNorth_ = 1;
      public static final int Apparent = 2;
      public static final int True_boatreferenced_ = 3;
      public static final int True_waterreferenced_ = 4;
    }

    // reserved ()
    public final static int reserved2 = 9;
    public final static int windGusts = 10;
    public final static int waveHeight = 11;
    public final static int dominantWavePeriod = 12;
    public final static int atmosphericPressure = 13;
    public final static int pressureTendencyRate = 14;
    public final static int airTemperature = 15;
    public final static int waterTemperature = 16;
    public final static int stationId = 17;
  }

  // PGN(130560) - Payload Mass
  public final static int payloadMass_pgn = 130560;
  public final static class payloadMass
  {
  }

  // PGN(130567) - Watermaker Input Setting and Status
  public final static int watermakerInputSettingAndStatus_pgn = 130567;
  public final static class watermakerInputSettingAndStatus
  {
    public final static int watermakerOperatingState = 0;
    // watermakerOperatingState permitted values
    public static final class watermakerOperatingState_values
    {
      public static final int Stopped = 0;
      public static final int Starting = 1;
      public static final int Running = 2;
      public static final int Stopping = 3;
      public static final int Flushing = 4;
      public static final int Rinsing = 5;
      public static final int Initiating = 6;
      public static final int ManualMode = 7;
      public static final int Error = 62;
      public static final int Unavailable = 63;
    }

    public final static int productionStartStop = 1;
    // productionStartStop permitted values
    public static final class productionStartStop_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int rinseStartStop = 2;
    // rinseStartStop permitted values
    public static final class rinseStartStop_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int lowPressurePumpStatus = 3;
    // lowPressurePumpStatus permitted values
    public static final class lowPressurePumpStatus_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int highPressurePumpStatus = 4;
    // highPressurePumpStatus permitted values
    public static final class highPressurePumpStatus_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int emergencyStop = 5;
    // emergencyStop permitted values
    public static final class emergencyStop_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int productSolenoidValveStatus = 6;
    // productSolenoidValveStatus permitted values
    public static final class productSolenoidValveStatus_values
    {
      public static final int OK = 0;
      public static final int Warning = 1;
    }

    public final static int flushModeStatus = 7;
    // flushModeStatus permitted values
    public static final class flushModeStatus_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int salinityStatus = 8;
    // salinityStatus permitted values
    public static final class salinityStatus_values
    {
      public static final int OK = 0;
      public static final int Warning = 1;
    }

    public final static int sensorStatus = 9;
    // sensorStatus permitted values
    public static final class sensorStatus_values
    {
      public static final int OK = 0;
      public static final int Warning = 1;
    }

    public final static int oilChangeIndicatorStatus = 10;
    // oilChangeIndicatorStatus permitted values
    public static final class oilChangeIndicatorStatus_values
    {
      public static final int OK = 0;
      public static final int Warning = 1;
    }

    public final static int filterStatus = 11;
    // filterStatus permitted values
    public static final class filterStatus_values
    {
      public static final int OK = 0;
      public static final int Warning = 1;
    }

    public final static int systemStatus = 12;
    // systemStatus permitted values
    public static final class systemStatus_values
    {
      public static final int OK = 0;
      public static final int Warning = 1;
    }

    // Reserved
    public final static int reserved = 13;
    public final static int salinity = 14;
    public final static int productWaterTemperature = 15;
    public final static int preFilterPressure = 16;
    public final static int postFilterPressure = 17;
    public final static int feedPressure = 18;
    public final static int systemHighPressure = 19;
    public final static int productWaterFlow = 20;
    public final static int brineWaterFlow = 21;
    public final static int runTime = 22;
  }

  // PGN(130570) - Library Data File
  public final static int libraryDataFile_pgn = 130570;
  public final static class libraryDataFile
  {
    public final static int source = 0;
    // source permitted values
    public static final class source_values
    {
      public static final int Vesselalarm = 0;
      public static final int AM = 1;
      public static final int FM = 2;
      public static final int Weather = 3;
      public static final int DAB = 4;
      public static final int Aux = 5;
      public static final int USB = 6;
      public static final int CD = 7;
      public static final int MP3 = 8;
      public static final int AppleiOS = 9;
      public static final int Android = 10;
      public static final int Bluetooth = 11;
      public static final int SiriusXM = 12;
      public static final int Pandora = 13;
      public static final int Spotify = 14;
      public static final int Slacker = 15;
      public static final int Songza = 16;
      public static final int AppleRadio = 17;
      public static final int LastFM = 18;
      public static final int Ethernet = 19;
      public static final int VideoMP4 = 20;
      public static final int VideoDVD = 21;
      public static final int VideoBluRay = 22;
      public static final int HDMI = 23;
      public static final int Video = 24;
    }

    // Source number per type
    public final static int number = 1;
    // Unique file ID
    public final static int id = 2;
    public final static int type = 3;
    // type permitted values
    public static final class type_values
    {
      public static final int File = 0;
      public static final int PlaylistName = 1;
      public static final int GenreName = 2;
      public static final int AlbumName = 3;
      public static final int ArtistName = 4;
      public static final int TrackName = 5;
      public static final int StationName = 6;
      public static final int StationNumber = 7;
      public static final int FavouriteNumber = 8;
      public static final int PlayQueue = 9;
      public static final int ContentInfo = 10;
    }

    public final static int name = 4;
    public final static int track = 5;
    public final static int station = 6;
    public final static int favorite = 7;
    public final static int radioFrequency = 8;
    public final static int hdFrequency = 9;
    public final static int zone = 10;
    // zone permitted values
    public static final class zone_values
    {
      public static final int Allzones = 0;
      public static final int Zone1 = 1;
      public static final int Zone2 = 2;
      public static final int Zone3 = 3;
      public static final int Zone4 = 4;
    }

    public final static int inPlayQueue = 11;
    // inPlayQueue permitted values
    public static final class inPlayQueue_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    // Sirius XM only
    public final static int lockStatus = 12;
    // lockStatus permitted values
    public static final class lockStatus_values
    {
      public static final int Unlocked = 0;
      public static final int Locked = 1;
    }

    // Reserved
    public final static int reserved = 13;
    public final static int artist = 14;
    public final static int album = 15;
    public final static int station2 = 16;
  }

  // PGN(130571) - Library Data Group
  public final static int libraryDataGroup_pgn = 130571;
  public final static class libraryDataGroup
  {
    public final static int source = 0;
    // source permitted values
    public static final class source_values
    {
      public static final int Vesselalarm = 0;
      public static final int AM = 1;
      public static final int FM = 2;
      public static final int Weather = 3;
      public static final int DAB = 4;
      public static final int Aux = 5;
      public static final int USB = 6;
      public static final int CD = 7;
      public static final int MP3 = 8;
      public static final int AppleiOS = 9;
      public static final int Android = 10;
      public static final int Bluetooth = 11;
      public static final int SiriusXM = 12;
      public static final int Pandora = 13;
      public static final int Spotify = 14;
      public static final int Slacker = 15;
      public static final int Songza = 16;
      public static final int AppleRadio = 17;
      public static final int LastFM = 18;
      public static final int Ethernet = 19;
      public static final int VideoMP4 = 20;
      public static final int VideoDVD = 21;
      public static final int VideoBluRay = 22;
      public static final int HDMI = 23;
      public static final int Video = 24;
    }

    // Source number per type
    public final static int number = 1;
    public final static int zone = 2;
    // zone permitted values
    public static final class zone_values
    {
      public static final int Allzones = 0;
      public static final int Zone1 = 1;
      public static final int Zone2 = 2;
      public static final int Zone3 = 3;
      public static final int Zone4 = 4;
    }

    // Unique group ID
    public final static int groupId = 3;
    // First ID in this PGN
    public final static int idOffset = 4;
    // Number of IDs in this PGN
    public final static int idCount = 5;
    // Total IDs in group
    public final static int totalIdCount = 6;
    public final static int idType = 7;
    // idType permitted values
    public static final class idType_values
    {
      public static final int Group = 0;
      public static final int File = 1;
      public static final int Encryptedgroup = 2;
      public static final int Encryptedfile = 3;
    }

    public final static class rep
    {
      public final static int id = 0;
      public final static int name = 1;
    }
  }

  // PGN(130572) - Library Data Search
  public final static int libraryDataSearch_pgn = 130572;
  public final static class libraryDataSearch
  {
    public final static int source = 0;
    // source permitted values
    public static final class source_values
    {
      public static final int Vesselalarm = 0;
      public static final int AM = 1;
      public static final int FM = 2;
      public static final int Weather = 3;
      public static final int DAB = 4;
      public static final int Aux = 5;
      public static final int USB = 6;
      public static final int CD = 7;
      public static final int MP3 = 8;
      public static final int AppleiOS = 9;
      public static final int Android = 10;
      public static final int Bluetooth = 11;
      public static final int SiriusXM = 12;
      public static final int Pandora = 13;
      public static final int Spotify = 14;
      public static final int Slacker = 15;
      public static final int Songza = 16;
      public static final int AppleRadio = 17;
      public static final int LastFM = 18;
      public static final int Ethernet = 19;
      public static final int VideoMP4 = 20;
      public static final int VideoDVD = 21;
      public static final int VideoBluRay = 22;
      public static final int HDMI = 23;
      public static final int Video = 24;
    }

    // Source number per type
    public final static int number = 1;
    // Unique group ID
    public final static int groupId = 2;
    public final static int groupType1 = 3;
    // groupType1 permitted values
    public static final class groupType1_values
    {
      public static final int File = 0;
      public static final int PlaylistName = 1;
      public static final int GenreName = 2;
      public static final int AlbumName = 3;
      public static final int ArtistName = 4;
      public static final int TrackName = 5;
      public static final int StationName = 6;
      public static final int StationNumber = 7;
      public static final int FavouriteNumber = 8;
      public static final int PlayQueue = 9;
      public static final int ContentInfo = 10;
    }

    public final static int groupName1 = 4;
    public final static int groupType2 = 5;
    // groupType2 permitted values
    public static final class groupType2_values
    {
      public static final int File = 0;
      public static final int PlaylistName = 1;
      public static final int GenreName = 2;
      public static final int AlbumName = 3;
      public static final int ArtistName = 4;
      public static final int TrackName = 5;
      public static final int StationName = 6;
      public static final int StationNumber = 7;
      public static final int FavouriteNumber = 8;
      public static final int PlayQueue = 9;
      public static final int ContentInfo = 10;
    }

    public final static int groupName2 = 6;
    public final static int groupType3 = 7;
    // groupType3 permitted values
    public static final class groupType3_values
    {
      public static final int File = 0;
      public static final int PlaylistName = 1;
      public static final int GenreName = 2;
      public static final int AlbumName = 3;
      public static final int ArtistName = 4;
      public static final int TrackName = 5;
      public static final int StationName = 6;
      public static final int StationNumber = 7;
      public static final int FavouriteNumber = 8;
      public static final int PlayQueue = 9;
      public static final int ContentInfo = 10;
    }

    public final static int groupName3 = 8;
  }

  // PGN(130573) - Supported Source Data
  public final static int supportedSourceData_pgn = 130573;
  public final static class supportedSourceData
  {
    // First ID in this PGN
    public final static int idOffset = 0;
    // Number of IDs in this PGN
    public final static int idCount = 1;
    // Total IDs in group
    public final static int totalIdCount = 2;
    // Source ID
    public final static class rep
    {
      public final static int id = 0;
      public final static int source = 1;
      // source permitted values
      public static final class source_values
      {
        public static final int Vesselalarm = 0;
        public static final int AM = 1;
        public static final int FM = 2;
        public static final int Weather = 3;
        public static final int DAB = 4;
        public static final int Aux = 5;
        public static final int USB = 6;
        public static final int CD = 7;
        public static final int MP3 = 8;
        public static final int AppleiOS = 9;
        public static final int Android = 10;
        public static final int Bluetooth = 11;
        public static final int SiriusXM = 12;
        public static final int Pandora = 13;
        public static final int Spotify = 14;
        public static final int Slacker = 15;
        public static final int Songza = 16;
        public static final int AppleRadio = 17;
        public static final int LastFM = 18;
        public static final int Ethernet = 19;
        public static final int VideoMP4 = 20;
        public static final int VideoDVD = 21;
        public static final int VideoBluRay = 22;
        public static final int HDMI = 23;
        public static final int Video = 24;
      }

      // Source number per type
      public final static int number = 2;
      public final static int name = 3;
      public final static int playSupport = 4;
      public final static int browseSupport = 5;
      public final static int thumbsSupport = 6;
      // thumbsSupport permitted values
      public static final class thumbsSupport_values
      {
        public static final int No = 0;
        public static final int Yes = 1;
      }

      public final static int connected = 7;
      // connected permitted values
      public static final class connected_values
      {
        public static final int No = 0;
        public static final int Yes = 1;
      }

      public final static int repeatSupport = 8;
      public final static int shuffleSupport = 9;
    }
  }

  // PGN(130574) - Supported Zone Data
  public final static int supportedZoneData_pgn = 130574;
  public final static class supportedZoneData
  {
    // First Zone in this PGN
    public final static int firstZoneId = 0;
    // Number of Zones in this PGN
    public final static int zoneCount = 1;
    // Total Zones supported by this device
    public final static int totalZoneCount = 2;
    public final static class rep
    {
      public final static int zoneId = 0;
      // zoneId permitted values
      public static final class zoneId_values
      {
        public static final int Allzones = 0;
        public static final int Zone1 = 1;
        public static final int Zone2 = 2;
        public static final int Zone3 = 3;
        public static final int Zone4 = 4;
      }

      public final static int name = 1;
    }
  }

  // PGN(130576) - Small Craft Status
  public final static int smallCraftStatus_pgn = 130576;
  public final static class smallCraftStatus
  {
    public final static int portTrimTab = 0;
    public final static int starboardTrimTab = 1;
  }

  // PGN(130577) - Direction Data
  public final static int directionData_pgn = 130577;
  public final static class directionData
  {
    public final static int dataMode = 0;
    // dataMode permitted values
    public static final class dataMode_values
    {
      public static final int Autonomous = 0;
      public static final int Differentialenhanced = 1;
      public static final int Estimated = 2;
      public static final int Simulator = 3;
      public static final int Manual = 4;
    }

    public final static int cogReference = 1;
    // cogReference permitted values
    public static final class cogReference_values
    {
      public static final int True = 0;
      public static final int Magnetic = 1;
    }

    // Reserved
    public final static int reserved = 2;
    public final static int sid = 3;
    public final static int cog = 4;
    public final static int sog = 5;
    public final static int heading = 6;
    public final static int speedThroughWater = 7;
    public final static int set = 8;
    public final static int drift = 9;
  }

  // PGN(130578) - Vessel Speed Components
  public final static int vesselSpeedComponents_pgn = 130578;
  public final static class vesselSpeedComponents
  {
    public final static int longitudinalSpeedWaterReferenced = 0;
    public final static int transverseSpeedWaterReferenced = 1;
    public final static int longitudinalSpeedGroundReferenced = 2;
    public final static int transverseSpeedGroundReferenced = 3;
    public final static int sternSpeedWaterReferenced = 4;
    public final static int sternSpeedGroundReferenced = 5;
  }

  // PGN(130579) - System Configuration
  public final static int systemConfiguration_pgn = 130579;
  public final static class systemConfiguration
  {
    public final static int power = 0;
    // power permitted values
    public static final class power_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int defaultSettings = 1;
    // defaultSettings permitted values
    public static final class defaultSettings_values
    {
      public static final int Savecurrentsettingsasuserdefault = 0;
      public static final int Loaduserdefault = 1;
      public static final int LoadManufacturerdefault = 2;
    }

    public final static int tunerRegions = 2;
    // tunerRegions permitted values
    public static final class tunerRegions_values
    {
      public static final int USA = 0;
      public static final int Europe = 1;
      public static final int Asia = 2;
      public static final int MiddleEast = 3;
      public static final int LatinAmerica = 4;
      public static final int Australia = 5;
      public static final int Russia = 6;
      public static final int Japan = 7;
    }

    public final static int maxFavorites = 3;
    public final static int videoProtocols = 4;
    // Reserved
    public final static int reserved = 5;
  }

  // PGN(130580) - System Configuration (deprecated)
  public final static int systemConfigurationDeprecated_pgn = 130580;
  public final static class systemConfigurationDeprecated
  {
    public final static int power = 0;
    // power permitted values
    public static final class power_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int defaultSettings = 1;
    // defaultSettings permitted values
    public static final class defaultSettings_values
    {
      public static final int Savecurrentsettingsasuserdefault = 0;
      public static final int Loaduserdefault = 1;
      public static final int LoadManufacturerdefault = 2;
    }

    public final static int tunerRegions = 2;
    // tunerRegions permitted values
    public static final class tunerRegions_values
    {
      public static final int USA = 0;
      public static final int Europe = 1;
      public static final int Asia = 2;
      public static final int MiddleEast = 3;
      public static final int LatinAmerica = 4;
      public static final int Australia = 5;
      public static final int Russia = 6;
      public static final int Japan = 7;
    }

    public final static int maxFavorites = 3;
  }

  // PGN(130581) - Zone Configuration (deprecated)
  public final static int zoneConfigurationDeprecated_pgn = 130581;
  public final static class zoneConfigurationDeprecated
  {
    // First Zone in this PGN
    public final static int firstZoneId = 0;
    // Number of Zones in this PGN
    public final static int zoneCount = 1;
    // Total Zones supported by this device
    public final static int totalZoneCount = 2;
    public final static class rep
    {
      public final static int zoneId = 0;
      // zoneId permitted values
      public static final class zoneId_values
      {
        public static final int Allzones = 0;
        public static final int Zone1 = 1;
        public static final int Zone2 = 2;
        public static final int Zone3 = 3;
        public static final int Zone4 = 4;
      }

      public final static int zoneName = 1;
    }
  }

  // PGN(130582) - Zone Volume
  public final static int zoneVolume_pgn = 130582;
  public final static class zoneVolume
  {
    public final static int zoneId = 0;
    // zoneId permitted values
    public static final class zoneId_values
    {
      public static final int Allzones = 0;
      public static final int Zone1 = 1;
      public static final int Zone2 = 2;
      public static final int Zone3 = 3;
      public static final int Zone4 = 4;
    }

    public final static int volume = 1;
    // Write only
    public final static int volumeChange = 2;
    // volumeChange permitted values
    public static final class volumeChange_values
    {
      public static final int Up = 0;
      public static final int Down = 1;
    }

    public final static int mute = 3;
    // mute permitted values
    public static final class mute_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    // Reserved
    public final static int reserved = 4;
    public final static int channel = 5;
    // channel permitted values
    public static final class channel_values
    {
      public static final int Allchannels = 0;
      public static final int Stereofullrange = 1;
      public static final int Stereofront = 2;
      public static final int Stereoback = 3;
      public static final int Stereosurround = 4;
      public static final int Center = 5;
      public static final int Subwoofer = 6;
      public static final int Frontleft = 7;
      public static final int Frontright = 8;
      public static final int Backleft = 9;
      public static final int Backright = 10;
      public static final int Surroundleft = 11;
      public static final int Surroundright = 12;
    }

  }

  // PGN(130583) - Available Audio EQ presets
  public final static int availableAudioEqPresets_pgn = 130583;
  public final static class availableAudioEqPresets
  {
    // First preset in this PGN
    public final static int firstPreset = 0;
    public final static int presetCount = 1;
    public final static int totalPresetCount = 2;
    public final static class rep
    {
      public final static int presetType = 0;
      // presetType permitted values
      public static final class presetType_values
      {
        public static final int Flat = 0;
        public static final int Rock = 1;
        public static final int Hall = 2;
        public static final int Jazz = 3;
        public static final int Pop = 4;
        public static final int Live = 5;
        public static final int Classic = 6;
        public static final int Vocal = 7;
        public static final int Arena = 8;
        public static final int Cinema = 9;
        public static final int Custom = 10;
      }

      public final static int presetName = 1;
    }
  }

  // PGN(130584) - Available Bluetooth addresses
  public final static int availableBluetoothAddresses_pgn = 130584;
  public final static class availableBluetoothAddresses
  {
    // First address in this PGN
    public final static int firstAddress = 0;
    public final static int addressCount = 1;
    public final static int totalAddressCount = 2;
    public final static int bluetoothAddress = 3;
    public final static class rep
    {
      public final static int status = 0;
      // status permitted values
      public static final class status_values
      {
        public static final int Connected = 0;
        public static final int Notconnected = 1;
        public static final int Notpaired = 2;
      }

      public final static int deviceName = 1;
      public final static int signalStrength = 2;
    }
  }

  // PGN(130585) - Bluetooth source status
  public final static int bluetoothSourceStatus_pgn = 130585;
  public final static class bluetoothSourceStatus
  {
    public final static int sourceNumber = 0;
    public final static int status = 1;
    // status permitted values
    public static final class status_values
    {
      public static final int Reserved = 0;
      public static final int Connected = 1;
      public static final int Connecting = 2;
      public static final int Notconnected = 3;
    }

    public final static int forgetDevice = 2;
    // forgetDevice permitted values
    public static final class forgetDevice_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int discovering = 3;
    // discovering permitted values
    public static final class discovering_values
    {
      public static final int No = 0;
      public static final int Yes = 1;
    }

    public final static int bluetoothAddress = 4;
  }

  // PGN(130586) - Zone Configuration
  public final static int zoneConfiguration_pgn = 130586;
  public final static class zoneConfiguration
  {
    public final static int zoneId = 0;
    // zoneId permitted values
    public static final class zoneId_values
    {
      public static final int Allzones = 0;
      public static final int Zone1 = 1;
      public static final int Zone2 = 2;
      public static final int Zone3 = 3;
      public static final int Zone4 = 4;
    }

    public final static int volumeLimit = 1;
    public final static int fade = 2;
    public final static int balance = 3;
    public final static int subVolume = 4;
    public final static int eqTreble = 5;
    public final static int eqMidRange = 6;
    public final static int eqBass = 7;
    public final static int presetType = 8;
    // presetType permitted values
    public static final class presetType_values
    {
      public static final int Flat = 0;
      public static final int Rock = 1;
      public static final int Hall = 2;
      public static final int Jazz = 3;
      public static final int Pop = 4;
      public static final int Live = 5;
      public static final int Classic = 6;
      public static final int Vocal = 7;
      public static final int Arena = 8;
      public static final int Cinema = 9;
      public static final int Custom = 10;
    }

    public final static int audioFilter = 9;
    // audioFilter permitted values
    public static final class audioFilter_values
    {
      public static final int Fullrange = 0;
      public static final int Highpass = 1;
      public static final int Lowpass = 2;
      public static final int Bandpass = 3;
      public static final int Notchfilter = 4;
    }

    public final static int highPassFilterFrequency = 10;
    public final static class rep
    {
      public final static int lowPassFilterFrequency = 0;
      public final static int channel = 1;
      // channel permitted values
      public static final class channel_values
      {
        public static final int Allchannels = 0;
        public static final int Stereofullrange = 1;
        public static final int Stereofront = 2;
        public static final int Stereoback = 3;
        public static final int Stereosurround = 4;
        public static final int Center = 5;
        public static final int Subwoofer = 6;
        public static final int Frontleft = 7;
        public static final int Frontright = 8;
        public static final int Backleft = 9;
        public static final int Backright = 10;
        public static final int Surroundleft = 11;
        public static final int Surroundright = 12;
      }

    }
  }

  // PGN(130816) - SonicHub: Init #2
  public final static int sonichubInit2_pgn = 130816;
  public final static class sonichubInit2
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Init #2
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int a = 6;
    public final static int b = 7;
  }

  // PGN(130816) - SonicHub: AM Radio
  public final static int sonichubAmRadio_pgn = 130816;
  public final static class sonichubAmRadio
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // AM Radio
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int item = 6;
    // item permitted values
    public static final class item_values
    {
      public static final int Seekingup = 1;
      public static final int Tuned = 2;
      public static final int Seekingdown = 3;
    }

    public final static int frequency = 7;
    public final static int noiseLevel = 8;
    public final static int signalLevel = 9;
    public final static int reserved3 = 10;
    public final static int text = 11;
  }

  // PGN(130816) - SonicHub: Zone info
  public final static int sonichubZoneInfo_pgn = 130816;
  public final static class sonichubZoneInfo
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Zone info
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int zone = 6;
  }

  // PGN(130816) - SonicHub: Source
  public final static int sonichubSource_pgn = 130816;
  public final static class sonichubSource
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Source
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int source = 6;
    // source permitted values
    public static final class source_values
    {
      public static final int AM = 0;
      public static final int FM = 1;
      public static final int iPod = 2;
      public static final int USB = 3;
      public static final int AUX = 4;
      public static final int AUX2 = 5;
      public static final int Mic = 6;
    }

  }

  // PGN(130816) - SonicHub: Source List
  public final static int sonichubSourceList_pgn = 130816;
  public final static class sonichubSourceList
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Source list
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int sourceId = 6;
    public final static int a = 7;
    public final static int text = 8;
  }

  // PGN(130816) - SonicHub: Control
  public final static int sonichubControl_pgn = 130816;
  public final static class sonichubControl
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Control
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int item = 6;
    // item permitted values
    public static final class item_values
    {
      public static final int Muteon = 1;
      public static final int Muteoff = 2;
    }

  }

  // PGN(130816) - SonicHub: Unknown
  public final static int sonichubUnknown_pgn = 130816;
  public final static class sonichubUnknown
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Unknown
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int a = 6;
    public final static int b = 7;
  }

  // PGN(130816) - SonicHub: FM Radio
  public final static int sonichubFmRadio_pgn = 130816;
  public final static class sonichubFmRadio
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // FM Radio
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int item = 6;
    // item permitted values
    public static final class item_values
    {
      public static final int Seekingup = 1;
      public static final int Tuned = 2;
      public static final int Seekingdown = 3;
    }

    public final static int frequency = 7;
    public final static int noiseLevel = 8;
    public final static int signalLevel = 9;
    public final static int reserved3 = 10;
    public final static int text = 11;
  }

  // PGN(130816) - SonicHub: Playlist
  public final static int sonichubPlaylist_pgn = 130816;
  public final static class sonichubPlaylist
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Playlist
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int item = 6;
    // item permitted values
    public static final class item_values
    {
      public static final int Report = 1;
      public static final int NextSong = 4;
      public static final int PreviousSong = 6;
    }

    public final static int a = 7;
    public final static int currentTrack = 8;
    public final static int tracks = 9;
    public final static int length = 10;
    public final static int positionInTrack = 11;
  }

  // PGN(130816) - SonicHub: Track
  public final static int sonichubTrack_pgn = 130816;
  public final static class sonichubTrack
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Track
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int item = 6;
    public final static int text = 7;
  }

  // PGN(130816) - SonicHub: Artist
  public final static int sonichubArtist_pgn = 130816;
  public final static class sonichubArtist
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Artist
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int item = 6;
    public final static int text = 7;
  }

  // PGN(130816) - SonicHub: Album
  public final static int sonichubAlbum_pgn = 130816;
  public final static class sonichubAlbum
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Album
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int item = 6;
    public final static int text = 7;
  }

  // PGN(130816) - SonicHub: Menu Item
  public final static int sonichubMenuItem_pgn = 130816;
  public final static class sonichubMenuItem
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Menu Item
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int item = 6;
    public final static int c = 7;
    public final static int d = 8;
    public final static int e = 9;
    public final static int text = 10;
  }

  // PGN(130816) - SonicHub: Zones
  public final static int sonichubZones_pgn = 130816;
  public final static class sonichubZones
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Zones
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int zones = 6;
  }

  // PGN(130816) - SonicHub: Max Volume
  public final static int sonichubMaxVolume_pgn = 130816;
  public final static class sonichubMaxVolume
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Max Volume
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int zone = 6;
    // zone permitted values
    public static final class zone_values
    {
      public static final int Zone1 = 0;
      public static final int Zone2 = 1;
      public static final int Zone3 = 2;
    }

    public final static int level = 7;
  }

  // PGN(130816) - SonicHub: Volume
  public final static int sonichubVolume_pgn = 130816;
  public final static class sonichubVolume
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Volume
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int zone = 6;
    // zone permitted values
    public static final class zone_values
    {
      public static final int Zone1 = 0;
      public static final int Zone2 = 1;
      public static final int Zone3 = 2;
    }

    public final static int level = 7;
  }

  // PGN(130816) - SonicHub: Init #1
  public final static int sonichubInit1_pgn = 130816;
  public final static class sonichubInit1
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Init #1
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

  }

  // PGN(130816) - SonicHub: Position
  public final static int sonichubPosition_pgn = 130816;
  public final static class sonichubPosition
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Position
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int position = 6;
  }

  // PGN(130816) - SonicHub: Init #3
  public final static int sonichubInit3_pgn = 130816;
  public final static class sonichubInit3
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Init #3
    public final static int proprietaryId = 4;
    public final static int control = 5;
    // control permitted values
    public static final class control_values
    {
      public static final int Set = 0;
      public static final int Ack = 128;
    }

    public final static int a = 6;
    public final static int b = 7;
  }

  // PGN(130816) - Simrad: Text Message
  public final static int simradTextMessage_pgn = 130816;
  public final static class simradTextMessage
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    // Init #3
    public final static int proprietaryId = 4;
    public final static int a = 5;
    public final static int b = 6;
    public final static int c = 7;
    public final static int sid = 8;
    public final static int prio = 9;
    public final static int text = 10;
  }

  // PGN(130816) - Manufacturer Proprietary fast-packet non-addressed
  public final static int manufacturerProprietaryFastPacketNonAddressed_pgn = 130816;
  public final static class manufacturerProprietaryFastPacketNonAddressed
  {
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    public final static int industryCode = 2;
    // industryCode permitted values
    public static final class industryCode_values
    {
      public static final int Global = 0;
      public static final int Highway = 1;
      public static final int Agriculture = 2;
      public static final int Construction = 3;
      public static final int Marine = 4;
      public static final int Industrial = 5;
    }

    public final static int data = 3;
  }

  // PGN(130817) - Navico: Product Information
  public final static int navicoProductInformation_pgn = 130817;
  public final static class navicoProductInformation
  {
    // Navico
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int productCode = 3;
    public final static int model = 4;
    public final static int a = 5;
    public final static int b = 6;
    public final static int c = 7;
    public final static int firmwareVersion = 8;
    public final static int firmwareDate = 9;
    public final static int firmwareTime = 10;
  }

  // PGN(130818) - Simnet: Reprogram Data
  public final static int simnetReprogramData_pgn = 130818;
  public final static class simnetReprogramData
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int version = 3;
    public final static int sequence = 4;
    public final static int data = 5;
  }

  // PGN(130819) - Simnet: Request Reprogram
  public final static int simnetRequestReprogram_pgn = 130819;
  public final static class simnetRequestReprogram
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(130820) - Simnet: Reprogram Status
  public final static int simnetReprogramStatus2_pgn = 130820;
  public final static class simnetReprogramStatus2
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int reserved2 = 3;
    public final static int status = 4;
    public final static int reserved3 = 5;
  }

  // PGN(130820) - Furuno: Unknown
  public final static int furunoUnknown_pgn = 130820;
  public final static class furunoUnknown
  {
    // Furuno
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int a = 3;
    public final static int b = 4;
    public final static int c = 5;
    public final static int d = 6;
    public final static int e = 7;
  }

  // PGN(130820) - Fusion: Source Name
  public final static int fusionSourceName_pgn = 130820;
  public final static class fusionSourceName
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Source
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int sourceId = 5;
    public final static int currentSourceId = 6;
    public final static int d = 7;
    public final static int e = 8;
    public final static int source = 9;
  }

  // PGN(130820) - Fusion: Track Info
  public final static int fusionTrackInfo_pgn = 130820;
  public final static class fusionTrackInfo
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Track Info
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int transport = 5;
    // transport permitted values
    public static final class transport_values
    {
      public static final int Playing = 1;
      public static final int Paused = 2;
    }

    public final static int x = 6;
    public final static int b = 7;
    public final static int track = 8;
    public final static int c = 9;
    public final static int trackCount = 10;
    public final static int e = 11;
    public final static int trackLength = 12;
    public final static int g = 13;
    public final static int h = 14;
  }

  // PGN(130820) - Fusion: Track
  public final static int fusionTrack_pgn = 130820;
  public final static class fusionTrack
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Track Title
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int b = 5;
    public final static int track = 6;
  }

  // PGN(130820) - Fusion: Artist
  public final static int fusionArtist_pgn = 130820;
  public final static class fusionArtist
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Track Artist
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int b = 5;
    public final static int artist = 6;
  }

  // PGN(130820) - Fusion: Album
  public final static int fusionAlbum_pgn = 130820;
  public final static class fusionAlbum
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Track Album
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int b = 5;
    public final static int album = 6;
  }

  // PGN(130820) - Fusion: Unit Name
  public final static int fusionUnitName_pgn = 130820;
  public final static class fusionUnitName
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Unit Name
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int name = 5;
  }

  // PGN(130820) - Fusion: Zone Name
  public final static int fusionZoneName_pgn = 130820;
  public final static class fusionZoneName
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Zone Name
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int number = 5;
    public final static int name = 6;
  }

  // PGN(130820) - Fusion: Play Progress
  public final static int fusionPlayProgress_pgn = 130820;
  public final static class fusionPlayProgress
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Track Progress
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int b = 5;
    public final static int progress = 6;
  }

  // PGN(130820) - Fusion: AM/FM Station
  public final static int fusionAmFmStation_pgn = 130820;
  public final static class fusionAmFmStation
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // AM/FM Station
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int amFm = 5;
    // amFm permitted values
    public static final class amFm_values
    {
      public static final int AM = 0;
      public static final int FM = 1;
    }

    public final static int b = 6;
    public final static int frequency = 7;
    public final static int c = 8;
    public final static int track = 9;
  }

  // PGN(130820) - Fusion: VHF
  public final static int fusionVhf_pgn = 130820;
  public final static class fusionVhf
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // VHF
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int b = 5;
    public final static int channel = 6;
    public final static int d = 7;
  }

  // PGN(130820) - Fusion: Squelch
  public final static int fusionSquelch_pgn = 130820;
  public final static class fusionSquelch
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Squelch
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int b = 5;
    public final static int squelch = 6;
  }

  // PGN(130820) - Fusion: Scan
  public final static int fusionScan_pgn = 130820;
  public final static class fusionScan
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Scan
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int b = 5;
    public final static int scan = 6;
    // scan permitted values
    public static final class scan_values
    {
      public static final int Off = 0;
      public static final int Scan = 1;
    }

  }

  // PGN(130820) - Fusion: Menu Item
  public final static int fusionMenuItem_pgn = 130820;
  public final static class fusionMenuItem
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Menu Item
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int b = 5;
    public final static int line = 6;
    public final static int e = 7;
    public final static int f = 8;
    public final static int g = 9;
    public final static int h = 10;
    public final static int i = 11;
    public final static int text = 12;
  }

  // PGN(130820) - Fusion: Replay
  public final static int fusionReplay_pgn = 130820;
  public final static class fusionReplay
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Replay
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int mode = 5;
    // mode permitted values
    public static final class mode_values
    {
      public static final int USBRepeat = 9;
      public static final int USBShuffle = 10;
      public static final int iPodRepeat = 12;
      public static final int iPodShuffle = 13;
    }

    public final static int c = 6;
    public final static int d = 7;
    public final static int e = 8;
    public final static int status = 9;
    // status permitted values
    public static final class status_values
    {
      public static final int Off = 0;
      public static final int One_Track = 1;
      public static final int All_Album = 2;
    }

    public final static int h = 10;
    public final static int i = 11;
    public final static int j = 12;
  }

  // PGN(130820) - Fusion: Mute
  public final static int fusionMute_pgn = 130820;
  public final static class fusionMute
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Mute
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int mute = 5;
    // mute permitted values
    public static final class mute_values
    {
      public static final int Muted = 1;
      public static final int NotMuted = 2;
    }

  }

  // PGN(130820) - Fusion: Sub Volume
  public final static int fusionSubVolume_pgn = 130820;
  public final static class fusionSubVolume
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Sub Volume
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int zone1 = 5;
    public final static int zone2 = 6;
    public final static int zone3 = 7;
    public final static int zone4 = 8;
  }

  // PGN(130820) - Fusion: Tone
  public final static int fusionTone_pgn = 130820;
  public final static class fusionTone
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Tone
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int b = 5;
    public final static int bass = 6;
    public final static int mid = 7;
    public final static int treble = 8;
  }

  // PGN(130820) - Fusion: Volume
  public final static int fusionVolume_pgn = 130820;
  public final static class fusionVolume
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Volume
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int zone1 = 5;
    public final static int zone2 = 6;
    public final static int zone3 = 7;
    public final static int zone4 = 8;
  }

  // PGN(130820) - Fusion: Transport
  public final static int fusionTransport_pgn = 130820;
  public final static class fusionTransport
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Transport
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int transport = 5;
    // transport permitted values
    public static final class transport_values
    {
      public static final int Paused = 1;
      public static final int Play = 2;
    }

  }

  // PGN(130820) - Fusion: SiriusXM Channel
  public final static int fusionSiriusxmChannel_pgn = 130820;
  public final static class fusionSiriusxmChannel
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // SiriusXM Channel
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int channel = 5;
  }

  // PGN(130820) - Fusion: SiriusXM Title
  public final static int fusionSiriusxmTitle_pgn = 130820;
  public final static class fusionSiriusxmTitle
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // SiriusXM Title
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int title = 5;
  }

  // PGN(130820) - Fusion: SiriusXM Artist
  public final static int fusionSiriusxmArtist_pgn = 130820;
  public final static class fusionSiriusxmArtist
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // SiriusXM Artist
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int artist = 5;
  }

  // PGN(130820) - Fusion: SiriusXM Genre
  public final static int fusionSiriusxmGenre_pgn = 130820;
  public final static class fusionSiriusxmGenre
  {
    // Fusion
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // SiriusXM Genre
    public final static int messageId = 3;
    public final static int a = 4;
    public final static int genre = 5;
  }

  // PGN(130821) - Furuno: Unknown
  public final static int furunoUnknown2_pgn = 130821;
  public final static class furunoUnknown2
  {
    // Furuno
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int sid = 3;
    public final static int a = 4;
    public final static int b = 5;
    public final static int c = 6;
    public final static int d = 7;
    public final static int e = 8;
    public final static int f = 9;
    public final static int g = 10;
    public final static int h = 11;
    public final static int i = 12;
  }

  // PGN(130824) - B&G: Wind data
  public final static int bGWindData_pgn = 130824;
  public final static class bGWindData
  {
    // B&G
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int field4 = 3;
    public final static int field5 = 4;
    // Increasing field, what else can it be?
    public final static int timestamp = 5;
  }

  // PGN(130824) - Maretron: Annunciator
  public final static int maretronAnnunciator_pgn = 130824;
  public final static class maretronAnnunciator
  {
    // Maretron
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int field4 = 3;
    public final static int field5 = 4;
    public final static int field6 = 5;
    public final static int field7 = 6;
    public final static int field8 = 7;
  }

  // PGN(130827) - Lowrance: unknown
  public final static int lowranceUnknown_pgn = 130827;
  public final static class lowranceUnknown
  {
    // Lowrance
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int a = 3;
    public final static int b = 4;
    public final static int c = 5;
    public final static int d = 6;
    public final static int e = 7;
    public final static int f = 8;
  }

  // PGN(130828) - Simnet: Set Serial Number
  public final static int simnetSetSerialNumber_pgn = 130828;
  public final static class simnetSetSerialNumber
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(130831) - Suzuki: Engine and Storage Device Config
  public final static int suzukiEngineAndStorageDeviceConfig_pgn = 130831;
  public final static class suzukiEngineAndStorageDeviceConfig
  {
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(130832) - Simnet: Fuel Used - High Resolution
  public final static int simnetFuelUsedHighResolution_pgn = 130832;
  public final static class simnetFuelUsedHighResolution
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(130834) - Simnet: Engine and Tank Configuration
  public final static int simnetEngineAndTankConfiguration_pgn = 130834;
  public final static class simnetEngineAndTankConfiguration
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(130835) - Simnet: Set Engine and Tank Configuration
  public final static int simnetSetEngineAndTankConfiguration_pgn = 130835;
  public final static class simnetSetEngineAndTankConfiguration
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(130836) - Simnet: Fluid Level Sensor Configuration
  public final static int simnetFluidLevelSensorConfiguration_pgn = 130836;
  public final static class simnetFluidLevelSensorConfiguration
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int c = 3;
    public final static int device = 4;
    public final static int instance = 5;
    public final static int f = 6;
    public final static int tankType = 7;
    // tankType permitted values
    public static final class tankType_values
    {
      public static final int Fuel = 0;
      public static final int Water = 1;
      public static final int Graywater = 2;
      public static final int Livewell = 3;
      public static final int Oil = 4;
      public static final int Blackwater = 5;
    }

    public final static int capacity = 8;
    public final static int g = 9;
    public final static int h = 10;
    public final static int i = 11;
  }

  // PGN(130837) - Simnet: Fuel Flow Turbine Configuration
  public final static int simnetFuelFlowTurbineConfiguration_pgn = 130837;
  public final static class simnetFuelFlowTurbineConfiguration
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(130838) - Simnet: Fluid Level Warning
  public final static int simnetFluidLevelWarning_pgn = 130838;
  public final static class simnetFluidLevelWarning
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(130839) - Simnet: Pressure Sensor Configuration
  public final static int simnetPressureSensorConfiguration_pgn = 130839;
  public final static class simnetPressureSensorConfiguration
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(130840) - Simnet: Data User Group Configuration
  public final static int simnetDataUserGroupConfiguration_pgn = 130840;
  public final static class simnetDataUserGroupConfiguration
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(130842) - Simnet: AIS Class B static data (msg 24 Part A)
  public final static int simnetAisClassBStaticDataMsg24PartA_pgn = 130842;
  public final static class simnetAisClassBStaticDataMsg24PartA
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Msg 24 Part A
    public final static int messageId = 3;
    public final static int repeatIndicator = 4;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int d = 5;
    public final static int e = 6;
    public final static int userId = 7;
    public final static int name = 8;
  }

  // PGN(130842) - Simnet: AIS Class B static data (msg 24 Part B)
  public final static int simnetAisClassBStaticDataMsg24PartB_pgn = 130842;
  public final static class simnetAisClassBStaticDataMsg24PartB
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // Msg 24 Part B
    public final static int messageId = 3;
    public final static int repeatIndicator = 4;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int d = 5;
    public final static int e = 6;
    public final static int userId = 7;
    public final static int typeOfShip = 8;
    // typeOfShip permitted values
    public static final class typeOfShip_values
    {
      public static final int unavailable = 0;
      public static final int WingInGround = 20;
      public static final int WingInGround_nootherinformation_ = 29;
      public static final int Fishing = 30;
      public static final int Towing = 31;
      public static final int Towingexceeds200morwiderthan25m = 32;
      public static final int Engagedindredgingorunderwateroperations = 33;
      public static final int Engagedindivingoperations = 34;
      public static final int Engagedinmilitaryoperations = 35;
      public static final int Sailing = 36;
      public static final int Pleasure = 37;
      public static final int Highspeedcraft = 40;
      public static final int Highspeedcraftcarryingdangerousgoods = 41;
      public static final int HighspeedcrafthazardcatB = 42;
      public static final int HighspeedcrafthazardcatC = 43;
      public static final int HighspeedcrafthazardcatD = 44;
      public static final int Highspeedcraft_noadditionalinformation_ = 49;
      public static final int Pilotvessel = 50;
      public static final int SAR = 51;
      public static final int Tug = 52;
      public static final int Porttender = 53;
      public static final int Anti_pollution = 54;
      public static final int Lawenforcement = 55;
      public static final int Spare = 56;
      public static final int Spare_2 = 57;
      public static final int Medical = 58;
      public static final int RRResolutionNo_18 = 59;
      public static final int Passengership = 60;
      public static final int Passengership_noadditionalinformation_ = 69;
      public static final int Cargoship = 70;
      public static final int Cargoshipcarryingdangerousgoods = 71;
      public static final int CargoshiphazardcatB = 72;
      public static final int CargoshiphazardcatC = 73;
      public static final int CargoshiphazardcatD = 74;
      public static final int Cargoship_noadditionalinformation_ = 79;
      public static final int Tanker = 80;
      public static final int Tankercarryingdangerousgoods = 81;
      public static final int TankerhazardcatB = 82;
      public static final int TankerhazardcatC = 83;
      public static final int TankerhazardcatD = 84;
      public static final int Tanker_noadditionalinformation_ = 89;
      public static final int Other = 90;
      public static final int Othercarryingdangerousgoods = 91;
      public static final int OtherhazardcatB = 92;
      public static final int OtherhazardcatC = 93;
      public static final int OtherhazardcatD = 94;
      public static final int Other_noadditionalinformation_ = 99;
    }

    public final static int vendorId = 9;
    public final static int callsign = 10;
    public final static int length = 11;
    public final static int beam = 12;
    public final static int positionReferenceFromStarboard = 13;
    public final static int positionReferenceFromBow = 14;
    // Id of mother ship sent by daughter vessels (MMSI)
    public final static int mothershipUserId = 15;
    public final static int blank = 16;
    public final static int spare = 17;
  }

  // PGN(130843) - Simnet: Sonar Status, Frequency and DSP Voltage
  public final static int simnetSonarStatusFrequencyAndDspVoltage_pgn = 130843;
  public final static class simnetSonarStatusFrequencyAndDspVoltage
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
  }

  // PGN(130845) - Simnet: Compass Heading Offset
  public final static int simnetCompassHeadingOffset_pgn = 130845;
  public final static class simnetCompassHeadingOffset
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int messageId = 3;
    public final static int repeatIndicator = 4;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int unused = 5;
    // Heading Offset
    public final static int type = 6;
    public final static int a = 7;
    public final static int angle = 8;
    public final static int unused2 = 9;
  }

  // PGN(130845) - Simnet: Compass Local Field
  public final static int simnetCompassLocalField_pgn = 130845;
  public final static class simnetCompassLocalField
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int messageId = 3;
    public final static int repeatIndicator = 4;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int unused = 5;
    // Local field
    public final static int type = 6;
    public final static int a = 7;
    public final static int localField = 8;
    public final static int unused2 = 9;
  }

  // PGN(130845) - Simnet: Compass Field Angle
  public final static int simnetCompassFieldAngle_pgn = 130845;
  public final static class simnetCompassFieldAngle
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int messageId = 3;
    public final static int repeatIndicator = 4;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int unused = 5;
    // Local field
    public final static int type = 6;
    public final static int a = 7;
    public final static int fieldAngle = 8;
    public final static int unused2 = 9;
  }

  // PGN(130845) - Simnet: Parameter Handle
  public final static int simnetParameterHandle_pgn = 130845;
  public final static class simnetParameterHandle
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int messageId = 3;
    public final static int repeatIndicator = 4;
    // repeatIndicator permitted values
    public static final class repeatIndicator_values
    {
      public static final int Initial = 0;
      public static final int Firstretransmission = 1;
      public static final int Secondretransmission = 2;
      public static final int Finalretransmission = 3;
    }

    public final static int d = 5;
    public final static int group = 6;
    public final static int f = 7;
    public final static int g = 8;
    public final static int h = 9;
    public final static int i = 10;
    public final static int j = 11;
    public final static int backlight = 12;
    // backlight permitted values
    public static final class backlight_values
    {
      public static final int DayMode = 1;
      public static final int NightMode = 4;
      public static final int Level1 = 11;
      public static final int Level2 = 22;
      public static final int Level3 = 33;
      public static final int Level4 = 44;
      public static final int Level5 = 55;
      public static final int Level6 = 66;
      public static final int Level7 = 77;
      public static final int Level8 = 88;
      public static final int Level9 = 99;
    }

    public final static int l = 13;
  }

  // PGN(130847) - SeaTalk: Node Statistics
  public final static int seatalkNodeStatistics_pgn = 130847;
  public final static class seatalkNodeStatistics
  {
    // Raymarine
    public final static int manufacturerCode = 0;
    public final static int softwareRelease = 1;
    public final static int developmentVersion = 2;
    public final static int productCode = 3;
    public final static int year = 4;
    public final static int month = 5;
    public final static int deviceNumber = 6;
    public final static int nodeVoltage = 7;
  }

  // PGN(130850) - Simnet: Event Command: AP command
  public final static int simnetEventCommandApCommand_pgn = 130850;
  public final static class simnetEventCommandApCommand
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // AP command
    public final static int proprietaryId = 3;
    public final static int b = 4;
    public final static int controllingDevice = 5;
    public final static int event = 6;
    // event permitted values
    public static final class event_values
    {
      public static final int Standby = 6;
      public static final int Automode = 9;
      public static final int Navmode = 10;
      public static final int NonFollowUpmode = 13;
      public static final int Windmode = 15;
      public static final int ChangeCourse = 26;
    }

    public final static int direction = 7;
    // direction permitted values
    public static final class direction_values
    {
      public static final int Port = 2;
      public static final int Starboard = 3;
      public static final int Leftrudder_port_ = 4;
      public static final int Rightrudder_starboard_ = 5;
    }

    public final static int angle = 8;
    public final static int g = 9;
  }

  // PGN(130850) - Simnet: Event Command: Alarm?
  public final static int simnetEventCommandAlarm_pgn = 130850;
  public final static class simnetEventCommandAlarm
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int a = 3;
    // Alarm command
    public final static int proprietaryId = 4;
    public final static int c = 5;
    public final static int alarm = 6;
    // alarm permitted values
    public static final class alarm_values
    {
      public static final int Raise = 57;
      public static final int Clear = 56;
    }

    public final static int messageId = 7;
    public final static int f = 8;
    public final static int g = 9;
  }

  // PGN(130850) - Simnet: Event Command: Unknown
  public final static int simnetEventCommandUnknown_pgn = 130850;
  public final static class simnetEventCommandUnknown
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int a = 3;
    // Alarm command
    public final static int proprietaryId = 4;
    public final static int b = 5;
    public final static int c = 6;
    public final static int d = 7;
    public final static int e = 8;
  }

  // PGN(130851) - Simnet: Event Reply: AP command
  public final static int simnetEventReplyApCommand_pgn = 130851;
  public final static class simnetEventReplyApCommand
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    // AP command
    public final static int proprietaryId = 3;
    public final static int b = 4;
    public final static int controllingDevice = 5;
    public final static int event = 6;
    // event permitted values
    public static final class event_values
    {
      public static final int Standby = 6;
      public static final int Automode = 9;
      public static final int Navmode = 10;
      public static final int NonFollowUpmode = 13;
      public static final int Windmode = 15;
      public static final int ChangeCourse = 26;
    }

    public final static int direction = 7;
    // direction permitted values
    public static final class direction_values
    {
      public static final int Port = 2;
      public static final int Starboard = 3;
      public static final int Leftrudder_port_ = 4;
      public static final int Rightrudder_starboard_ = 5;
    }

    public final static int angle = 8;
    public final static int g = 9;
  }

  // PGN(130856) - Simnet: Alarm Message
  public final static int simnetAlarmMessage_pgn = 130856;
  public final static class simnetAlarmMessage
  {
    // Simrad
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int messageId = 3;
    public final static int b = 4;
    public final static int c = 5;
    public final static int text = 6;
  }

  // PGN(130880) - Airmar: Additional Weather Data
  public final static int airmarAdditionalWeatherData_pgn = 130880;
  public final static class airmarAdditionalWeatherData
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int c = 3;
    public final static int apparentWindchillTemperature = 4;
    public final static int trueWindchillTemperature = 5;
    public final static int dewpoint = 6;
  }

  // PGN(130881) - Airmar: Heater Control
  public final static int airmarHeaterControl_pgn = 130881;
  public final static class airmarHeaterControl
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int c = 3;
    public final static int plateTemperature = 4;
    public final static int airTemperature = 5;
    public final static int dewpoint = 6;
  }

  // PGN(130944) - Airmar: POST
  public final static int airmarPost_pgn = 130944;
  public final static class airmarPost
  {
    // Airmar
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int control = 3;
    // control permitted values
    public static final class control_values
    {
      public static final int Reportpreviousvalues = 0;
      public static final int Generatenewvalues = 1;
    }

    public final static int reserved2 = 4;
    public final static int numberOfIdTestResultPairsToFollow = 5;
    // See Airmar docs for table of IDs and failure codes; these lookup values are for DST200
    public final static int testId = 6;
    // testId permitted values
    public static final class testId_values
    {
      public static final int FormatCode = 1;
      public static final int FactoryEEPROM = 2;
      public static final int UserEEPROM = 3;
      public static final int WaterTempSensor = 4;
      public static final int SonarTransceiver = 5;
      public static final int Speedsensor = 6;
      public static final int Internaltemperaturesensor = 7;
      public static final int Batteryvoltagesensor = 8;
    }

    // Values other than 0 are failure codes
    public final static int testResult = 7;
    // testResult permitted values
    public static final class testResult_values
    {
      public static final int Pass = 0;
    }

  }

  // PGN(65311) - Raymarine proprietary magnetic variation
  public final static int MagneticVariationRaymarine_pgn = 65311;
  public final static class MagneticVariationRaymarine
  {
    // Raymarine
    public final static int manufacturerCode = 0;
    public final static int reserved = 1;
    // Marine Industry
    public final static int industryCode = 2;
    public final static int control = 3;
    public final static int reserved2 = 4;
    public final static int someInt = 5;
  }

  // PGN(130846) - Raymarine Set Alarm State
  public final static int SetAlarmState_pgn = 130846;
  public final static class SetAlarmState
  {
  }

}
