package be.seeseemelk.tankbot.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import be.seeseemelk.tankbot.server.devices.Device;

public class DeviceManagement
{
	private Map<String, Device> devices = new HashMap<>();

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

}
