package be.seeseemelk.tankbot.control;

import java.io.IOException;
import java.net.Socket;

import be.seeseemelk.tankbot.common.Connection;
import be.seeseemelk.tankbot.common.MultiplexedConnection;
import be.seeseemelk.tankbot.common.packets.BasePacket;
import be.seeseemelk.tankbot.common.packets.PingPacket;
import be.seeseemelk.tankbot.common.packets.PongPacket;

public class ControlConnection
{
	private Socket socket;
	private ControlMain main;
	private MultiplexedConnection connection;

	public ControlConnection(ControlMain main)
	{
		this.main = main;
	}
	
	/**
	 * Starts the server and accepts connection.
	 * @throws IOException 
	 */
	public void start(String ip, int port) throws IOException
	{
		socket = new Socket(ip, port);
		connection = new MultiplexedConnection(socket.getInputStream(), socket.getOutputStream());
	}
	
	/**
	 * Gets the established multiplexed connection.
	 * @return The established multiplexed connection.
	 */
	public MultiplexedConnection getMultiplexedConnection()
	{
		return connection;
	}
	
	/**
	 * Gets a numbered connection.
	 * @param number The number of the connection to get.
	 * @return The connection.
	 */
	public Connection getConnection(int number)
	{
		return connection.getConnection(number);
	}
	
	/**
	 * Run the server loop
	 * @throws IOException 
	 */
	public void mainLoop() throws IOException
	{
		Connection connection = getConnection(0);
		BasePacket packet = connection.readPacket();
		if (packet instanceof PingPacket)
			connection.writePacket(new PongPacket());
		else
			main.handlePacket(connection, packet);
	}

}



















