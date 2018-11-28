# N2KLib
A Java Library and UI for NMEA 2000 message logging and simulation

This library takes an XML file that defines the format of the N2K messages and provides functions for constructing messages either from input data or from a raw binary stream. (Thanks to the Canbus project for their excellent N2K definitions).

The library is structured in three layers.

- The UI package contains a sample user interface which displays received packets and also provides simulation of a subset of N2K messages sufficient to drive a chart plotter (tested against a Raymarine E120 Classic), including a simple autohelm function.
See http://blog.mailasail.com/anastasia/493 for more details.

- The N2KLib package is the N2K engine, which handles construction and validation of messages and their fields.

- The Transport package is what transfers the N2K messages to/from their destination.  The N2KTransport interface isolates the implementation of the transport from N2KLib.  The product includes a transport implementation for running over the Actisense serial to N2K interface.  It is written to the Raspberry Pi pi4j serial interface, but the product includes a minimal implementation of com.pi4j.io.serial that binds to the Windows jSerialComm  library for running on Windows systems.

There are three additional packages.

N2KDefs contains the classes that map to the pgns.xml definition file.  These are simply containers that carry the fields that appear in the XML.  They are populated using JAXB.

N2KMsgs containsa single class, N2K, which defines constants for all the fields in the XML file.  See below for example of use.

Utils contains general utility functions plus tracing.
