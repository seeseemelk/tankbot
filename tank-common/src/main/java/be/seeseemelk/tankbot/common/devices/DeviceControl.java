package be.seeseemelk.tankbot.common.devices;

public abstract class DeviceControl
{
	private final String name;

	public DeviceControl(final String name)
	{
		this.name = name;
	}
	
	/**
	 * Gets the name of this device.
	 * @return The name of the device.
	 */
	public String getName()
	{
		return name;
	}

}
