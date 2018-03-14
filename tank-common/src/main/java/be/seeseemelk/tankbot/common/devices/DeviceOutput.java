package be.seeseemelk.tankbot.common.devices;

public abstract class DeviceOutput extends DeviceControl
{
	public DeviceOutput(String name)
	{
		super(name);
	}

	/**
	 * Writes a value to the device.
	 * @return The value to write to the device.
	 */
	public abstract void setValue(double value);
	
	/**
	 * Gets the last written value.
	 * @return The last written value.
	 */
	public abstract double getValue();
}
