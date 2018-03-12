package be.seeseemelk.tankbot.common;

import java.io.IOException;
import java.io.ObjectOutputStream;

import be.seeseemelk.tankbot.common.packets.BasePacket;

public class Connection
{
	private MultiplexedConnection root;
	private int connectionNumber;

	protected Connection(MultiplexedConnection root, int number)
	{
		this.root = root;
		connectionNumber = number;
	}
	
	/**
	 * Gets the number of this connection.
	 * @return The number of this connection.
	 */
	public int getConnectionNumber()
	{
		return connectionNumber;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + connectionNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connection other = (Connection) obj;
		if (connectionNumber != other.connectionNumber)
			return false;
		return true;
	}
	
	/**
	 * Writes a single packet to the connection.
	 * @param packet A read packet.
	 * @throws IOException 
	 */
	public void writePacket(BasePacket packet) throws IOException
	{
		ObjectOutputStream output = root.getObjectOutputStream();
		output.writeInt(connectionNumber);
		output.writeObject(packet);
		output.flush();
	}
	
	/**
	 * Checks if there is a packet to read.
	 * @return {@code true} if a packet was read, {@code false} if there was no packet read.
	 * @throws IOException 
	 */
	public boolean hasPacket() throws IOException
	{
		return root.hasPacket(connectionNumber);
	}
	
	/**
	 * Reads a packet from the connection.
	 * @return A packet read.
	 * @throws IOException 
	 */
	public BasePacket readPacket() throws IOException
	{
		return root.getPacket(connectionNumber);
	}

}



















