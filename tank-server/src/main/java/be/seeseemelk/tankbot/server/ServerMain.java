package be.seeseemelk.tankbot.server;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;

import be.seeseemelk.tankbot.common.packets.BasePacket;
import be.seeseemelk.tankbot.server.devices.Device;
import be.seeseemelk.tankbot.server.devices.DeviceInput;
import be.seeseemelk.tankbot.server.devices.DeviceOutput;
import be.seeseemelk.tankbot.server.devices.Track;

public final class ServerMain
{
	private Logger logger;
	private ServerConnection connection;
	private DeviceManagement devices;
	
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
		devices = new DeviceManagement();
	}
	
	/**
	 * Starts the server.
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException
	{
		init();
		logDevices();
		connection.start();
	}
	
	/**
	 * Initialise the bot.
	 */
	private void init()
	{
		GpioController controller = GpioFactory.getInstance();
		devices.addDevice(new Track(
				controller.provisionPwmOutputPin(RaspiPin.GPIO_01),
				controller.provisionPwmOutputPin(RaspiPin.GPIO_26)));
	}
	
	/**
	 * Prints all devices to stdout.
	 */
	private void logDevices()
	{
		for (Device device : devices.getDevices())
		{
			System.out.format("Device name: %20sDevice type: %s%n", device.getName(), device.getClass().getName());
			System.out.println("Device inputs: ");
			for (DeviceInput control : device.getInputs())
			{
				System.out.format("  Input name: %20svalue: %d%n", control.getName(), control.getValue());
			}
			
			System.out.println("Device outputs: ");
			for (DeviceOutput control : device.getOutputs())
			{
				System.out.format("  Input name: %20svalue: %d%n", control.getName(), control.getValue());
			}
		}
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
