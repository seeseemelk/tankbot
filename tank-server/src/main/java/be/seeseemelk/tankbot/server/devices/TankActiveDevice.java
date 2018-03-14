package be.seeseemelk.tankbot.server.devices;

/**
 * A {@code TankActiveDevice} is a type of {@link TankDevice} that allows
 * writing values to the device. This is used for device that can output a value
 * instead of only reading.
 * 
 * @author seeseemelk
 *
 */
public abstract class TankActiveDevice extends TankDevice
{
	public TankActiveDevice(String name)
	{
		super(name);
	}
	
	/**
	 * Sets a new value to the device.
	 * @param value The value to give to the device. 
	 */
	public abstract void setValue(double value);
	
}
