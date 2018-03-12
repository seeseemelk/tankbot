package be.seeseemelk.tankbot.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import be.seeseemelk.tankbot.common.Connection;
import be.seeseemelk.tankbot.common.MultiplexedConnection;
import be.seeseemelk.tankbot.common.packets.BasePacket;
import be.seeseemelk.tankbot.common.packets.PingPacket;
import be.seeseemelk.tankbot.common.packets.PongPacket;

public class ServerConnection
{
	private Logger logger;
	private ServerMain server;
	private ServerSocket serverSocket;
	private MultiplexedConnection connection;

	public ServerConnection(ServerMain server)
	{
		logger = Logger.getLogger("ServerSocket");
		this.server = server;
	}
	
	/**
	 * Starts the server and accepts connection.
	 * @throws IOException 
	 */
	public void start() throws IOException
	{
		serverSocket = new ServerSocket(28000);
		Socket socket = serverSocket.accept();
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
			server.handlePacket(packet);
	}

}



















