package be.seeseemelk.tankbot.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import be.seeseemelk.tankbot.server.devices.Device;
import be.seeseemelk.tankbot.server.devices.DeviceInput;
import be.seeseemelk.tankbot.server.devices.DeviceOutput;

public class DeviceManagement
{
	private Map<String, Device> devices = new HashMap<>();
	private Logger logger = ServerMain.getLogger("Devices");

	public DeviceManagement()
	{
		
	}
	
	/**
	 * Add a device to the manager.
	 * @param device The device to add.
	 */
	public void addDevice(Device device)
	{
		devices.put(device.getName(), device);
	}
	
	/**
	 * Returns a collection of registered devices.
	 * @return A collection of registered devices.
	 */
	public Collection<Device> getDevices()
	{
		return devices.values();
	}
	
	/**
	 * Gets a specific device.
	 * @param name The name of the device to get.
	 * @return The device or {@code null} if the device does not exist.
	 */
	public Device getDevice(String name)
	{
		return devices.get(name);
	}
	
	/**
	 * Causes all devices to stop.
	 */
	public synchronized void stop()
	{
		for (Device device : getDevices())
		{
			for (DeviceOutput output : device.getOutputs())
			{
				output.setValue(0);
			}
		}
	}
	
	/**
	 * Prints all devices to stdout.
	 */
	public void logDevices()
	{
		for (Device device : getDevices())
		{
			logger.info(String.format("Device name: %-10sDevice type: %s", device.getName(), device.getClass().getName()));
			logger.info("Device inputs: ");
			for (DeviceInput control : device.getInputs())
				logger.info(String.format("  Input name: %-10svalue: %f", control.getName(), control.getValue()));

			logger.info("Device outputs: ");
			for (DeviceOutput control : device.getOutputs())
				logger.info(String.format("  Output name: %-10svalue: %f", control.getName(), control.getValue()));
		}
	}

}
