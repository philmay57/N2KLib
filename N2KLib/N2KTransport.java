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
package N2KLib;
import java.io.IOException;

/**
 * This interface separates the N2K library from the underlying transport.  Any transport
 * that implements this interface can be used to send and receive N2K packets from N2KLib.
 * 
 * After receiving a complete packet of data from the network the transport should 
 * call N2KPacket(pgn, priority, dest, src, time,  rawBytes, len, hdrlen).  Hopefully if you
 * are planning to write a transport then the parameters will mostly be obvious.  Provide the 
 * offset into the packet(hdrlen) and the number of bytes to be processed(len).  The length is
 * required because some devices truncate messages and expect the recipient to substitute 
 * "not available" for the missing data, so the N2KLib needs to know how much of the data is valid.   
 * 
 * When you het a packet from N2KLib for sending you should allocate a byte array
 * and call packet.getRawData(bytearray, offset to start of N2K data).  The getRawData
 * method will fill in that part of the byte array and return the length (excluding the
 * offset to the start).  You can then insert your header and send the data.  
 * 
 * @author PhilMay
 *
 */
public interface N2KTransport
{
	/**
	 * Open the transport for communication
	 * @throws IOException If any error occurs opening the transport
	 */
  public void open() throws IOException;
  /**
   * Close the transport and release any associated resources.
   */
  public void close();  
  /**
   * Called by the N2K library to get an N2K packet for processing.  This method should not return
   * until there is a packet available.  The N2K library will call back immediately it has 
   * completed processing of the returned packet. There is no need for a separate thread in 
   * the transport. 
   * 
   * Construct the packet by calling the N2KPacket constructor passing the offset to the   
   * start of the N2K data fields. 
   * 
   * @return The constructed N2K packet
   * @throws IOException If any error occurs reading from the transport
   */
  public N2KPacket get() throws IOException;
  
  /**
   * Called by the N2K library with a packet to be sent out over the transport.
   * 
   * @param packet The N2K packet to be written.  The Transport  must allocate a byte array
   * and call packet.getRawData(bytearray, offset to start of N2K data).  The getRawData
   * method will fill in that part of the byte array and return the length (excluding the
   * offset to the start).  
   * 
   * @return  Returns -1 if the put fails for any reason
   * @throws IOException If any error occurs writing to the transport
   */
  public int put(N2KPacket packet) throws IOException;
}
