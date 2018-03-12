package be.seeseemelk.tankbot.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import be.seeseemelk.tankbot.common.packets.BasePacket;

public class MultiplexedConnection
{
	private InputStream input;
	private OutputStream output;
	private ObjectInputStream objInput;
	private ObjectOutputStream objOutput;
	private Map<Integer, Queue<BasePacket>> readPackets = new HashMap<>();

	public MultiplexedConnection(InputStream input, OutputStream output) throws IOException
	{
		this.output = output;
		this.input = input;
		objOutput = new ObjectOutputStream(output);
		objOutput.flush();
		objInput = new ObjectInputStream(input);
	}
	
	public InputStream getInputStream()
	{
		return input;
	}
	
	public OutputStream getOutputStream()
	{
		return output;
	}
	
	public ObjectInputStream getObjectInputStream() throws IOException
	{
		return objInput;
	}
	
	public ObjectOutputStream getObjectOutputStream() throws IOException
	{
		return objOutput;
	}
	
	/**
	 * Gets a numbered connection.
	 * @param number The number of the connection to get.
	 * @return The connection.
	 */
	public Connection getConnection(int number)
	{
		return new Connection(this, number);
	}
	
	/**
	 * Reads the next packet.
	 * @return The channel for which a packet has been read.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private int readAnyPacket() throws IOException
	{
		try
		{
			Integer connectionNumber = objInput.readInt();
			BasePacket packet = (BasePacket) objInput.readObject();
			
			if (!readPackets.containsKey(connectionNumber))
				readPackets.put(connectionNumber, new LinkedList<>());
			
			Queue<BasePacket> queue = readPackets.get(connectionNumber);
			queue.add(packet);
			return connectionNumber;
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Checks if there is a packet ready to be read for a given connection.
	 * @param connectionNumber The number of the connection.
	 * @return {@code true} if there is a packet ready for the connection, {@code false} if there isn't one.
	 * @throws IOException 
	 */
	protected boolean hasPacket(int connectionNumber) throws IOException
	{
		if (readPackets.containsKey(connectionNumber) && !readPackets.get(connectionNumber).isEmpty())
			return true;
		else
		{
			while (objInput.available() > 0)
			{
				int channel = readAnyPacket();
				if (connectionNumber == channel)
					return true;
			}
			return false;
		}
	}
	
	/**
	 * Reads a packet for a given connection.
	 * Blocks until one is received.
	 * @param connectionNumber The number of the connection to read from.
	 * @return The read packet.
	 * @throws IOException
	 */
	protected BasePacket getPacket(int connectionNumber) throws IOException
	{
		while (!hasPacket(connectionNumber))
			readAnyPacket();
		return readPackets.get(connectionNumber).poll();
	}

}















