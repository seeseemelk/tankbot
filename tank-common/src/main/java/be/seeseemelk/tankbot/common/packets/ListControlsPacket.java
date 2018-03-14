package be.seeseemelk.tankbot.common.packets;

import be.seeseemelk.tankbot.common.devices.Device;

/**
 * Asks to list the devices the server has.
 * @author seeseemelk
 *
 */
public class ListControlsPacket extends BasePacket
{
	private static final long serialVersionUID = 1L;
	private final String device;
	
	public ListControlsPacket(Device device)
	{
		this.device = device.getName();
	}
	
	/**
	 * Gets the name of the device of which the controls should be retrieved.
	 * @return The name of the device.
	 */
	public String getDevice()
	{
		return device;
	}

}
