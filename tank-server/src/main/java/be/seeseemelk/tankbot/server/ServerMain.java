package be.seeseemelk.tankbot.server;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;

import be.seeseemelk.tankbot.common.Connection;
import be.seeseemelk.tankbot.common.packets.BasePacket;
import be.seeseemelk.tankbot.common.packets.ControlsListPacket;
import be.seeseemelk.tankbot.common.packets.DeviceListPacket;
import be.seeseemelk.tankbot.common.packets.ListControlsPacket;
import be.seeseemelk.tankbot.common.packets.ListDevicesPacket;
import be.seeseemelk.tankbot.common.packets.SetOutputPacket;
import be.seeseemelk.tankbot.server.devices.Motor;

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

		Runtime.getRuntime().addShutdownHook(new Thread(() ->
		{
			devices.stop();
		}));
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
		
		new Thread(() -> {
			try
			{
				while (true)
				{
					connection.mainLoop();
				}
			}
			catch (IOException e)
			{
				logger.throwing(e);
				throw new RuntimeException(e);
			}
		}).start();
	}

	/**
	 * Initialise the bot.
	 */
	private void init()
	{
		GpioController controller = GpioFactory.getInstance();
		devices.addDevice(new Motor(controller.provisionDigitalOutputPin(RaspiPin.GPIO_02),
				controller.provisionDigitalOutputPin(RaspiPin.GPIO_00)));
		devices.addDevice(new Motor(controller.provisionDigitalOutputPin(RaspiPin.GPIO_03),
				controller.provisionDigitalOutputPin(RaspiPin.GPIO_12)));
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
	 * @throws IOException 
	 */
	public void handlePacket(Connection connection, BasePacket packet) throws IOException
	{
		if (packet instanceof ListDevicesPacket)
		{
			connection.writePacket(new DeviceListPacket(devices.getDevices()));
		}
		else if (packet instanceof ListControlsPacket)
		{
			ListControlsPacket lcp = (ListControlsPacket) packet;
			ControlsListPacket clp = new ControlsListPacket(devices.getDevice(lcp.getDevice()));
			connection.writePacket(clp);
		}
		else if (packet instanceof SetOutputPacket)
		{
			SetOutputPacket sop = (SetOutputPacket) packet;
			String device = sop.getDeviceName();
			String output = sop.getOutputName();
			double value = sop.getValue();
			logger.info(String.format("Setting output '%s' of device %s to %f", device, output, value));
			devices.getDevice(device).getOutput(output).setValue(value);
		}
	}

	public static void main(String[] args) throws IOException
	{
		ServerMain main = new ServerMain();
		main.start();
	}

}
