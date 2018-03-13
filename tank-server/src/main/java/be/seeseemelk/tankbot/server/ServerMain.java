package be.seeseemelk.tankbot.server;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import be.seeseemelk.tankbot.common.packets.BasePacket;

public final class ServerMain
{
	private Logger logger;
	private ServerConnection connection;
	
	/**
	 * Gets a named logger.
	 * @param name The name to give to the logger.
	 * @return A newly created named logger.
	 */
	public static Logger getLogger(String name)
	{
		return LogManager.getLogger(name);
	}

	public ServerMain()
	{
		logger = getLogger("Server");
		logger.info("Starting server...");
		connection = new ServerConnection(this);
	}
	
	/**
	 * Starts the server.
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException
	{
		connection.start();
	}
	
	/**
	 * Returns the registered logger for this server instance.
	 * 
	 * @return The registered logger.
	 */
	public Logger getLogger()
	{
		return logger;
	}
	
	/**
	 * Handles a single packet.
	 * 
	 * @param packet
	 *            The packet to handle.
	 */
	public void handlePacket(BasePacket packet)
	{
		
	}
	
	public static void main(String[] args) throws IOException
	{
		ServerMain main = new ServerMain();
		main.start();
	}
	
}
