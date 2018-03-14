package be.seeseemelk.tankbot.common.devices;

public abstract class DeviceInput extends DeviceControl
{
	public DeviceInput(String name)
	{
		super(name);
	}

	/**
	 * Gets the current value that is being read from the device.
	 * @return The current value that is being read.
	 */
	public abstract double getValue();
}
