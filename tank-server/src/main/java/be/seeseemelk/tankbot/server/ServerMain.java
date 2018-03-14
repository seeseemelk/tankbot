package be.seeseemelk.tankbot.server;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;

import be.seeseemelk.tankbot.common.packets.BasePacket;
import be.seeseemelk.tankbot.server.devices.Track;

public final class ServerMain
{
	private Logger logger;
	private ServerConnection connection;
	private DeviceManagement devices;

	/**
	 * Gets a named logger.
	 * 
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
		devices = new DeviceManagement();

		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				devices.stop();
			};
		});
	}

	/**
	 * Starts the server.
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException
	{
		init();
		devices.logDevices();
		connection.start();
	}

	/**
	 * Initialise the bot.
	 */
	private void init()
	{
		GpioController controller = GpioFactory.getInstance();
		devices.addDevice(new Track(controller.provisionDigitalOutputPin(RaspiPin.GPIO_02),
				controller.provisionDigitalOutputPin(RaspiPin.GPIO_00)));
		devices.addDevice(new Track(controller.provisionDigitalOutputPin(RaspiPin.GPIO_12),
				controller.provisionDigitalOutputPin(RaspiPin.GPIO_03)));
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
	 * @param packet The packet to handle.
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
