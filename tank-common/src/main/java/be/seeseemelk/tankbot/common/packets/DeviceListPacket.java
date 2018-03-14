package be.seeseemelk.tankbot.common.packets;

import java.util.Collection;

import be.seeseemelk.tankbot.common.devices.Device;

/**
 * Contains a list of all supported devices.
 * @author seeseemelk
 *
 */
public class DeviceListPacket extends BasePacket
{
	private static final long serialVersionUID = 4111633030069837102L;
	private String[] names;

	public DeviceListPacket(Collection<Device> devices)
	{
		names = new String[devices.size()];
		int i = 0;
		for (Device device : devices)
		{
			names[i++] = device.getName();
		}
	}
	
	/**
	 * Gets an array of all names of all devices.
	 * @return An array of all names of all devices.
	 */
	public String[] getDeviceNames()
	{
		return names;
	}

}
