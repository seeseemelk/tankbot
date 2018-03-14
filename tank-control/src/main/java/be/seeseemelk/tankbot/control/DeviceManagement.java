package be.seeseemelk.tankbot.control;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import be.seeseemelk.tankbot.common.devices.Device;
import be.seeseemelk.tankbot.common.devices.DeviceInput;
import be.seeseemelk.tankbot.common.devices.DeviceOutput;

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
			System.out.format("Device name: %-10sDevice type: %s", device.getName(), device.getClass().getName());
			System.out.println("Device inputs: ");
			for (DeviceInput control : device.getInputs())
				System.out.format("  Input name: %-10svalue: %f", control.getName(), control.getValue());

			System.out.println("Device outputs: ");
			for (DeviceOutput control : device.getOutputs())
				System.out.format("  Output name: %-10svalue: %f", control.getName(), control.getValue());
		}
	}

}
