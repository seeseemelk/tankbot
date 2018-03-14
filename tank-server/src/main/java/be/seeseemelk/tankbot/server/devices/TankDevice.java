package be.seeseemelk.tankbot.server.devices;

/**
 * Describes any type of peripheral attached to the bot, such as a sensor or a
 * motor.
 * 
 * @author seeseemelk
 *
 */
public abstract class TankDevice
{
	private final String name;
	
	/**
	 * Creates a new device with a given name.
	 * This name will be used to display it in the control UI.
	 * @param name The name to give to the device.
	 */
	public TankDevice(final String name)
	{
		this.name = name;
	}
	
	/**
	 * Gets the name of this device.
	 * @return The name of this device.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Gets the current value of the device.
	 * @return The current value of the device.
	 */
	public abstract double getValue();
}
