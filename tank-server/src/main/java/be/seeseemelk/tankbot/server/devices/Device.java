package be.seeseemelk.tankbot.server.devices;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * Describes any type of peripheral attached to the bot, such as a sensor or a
 * motor.
 * 
 * @author seeseemelk
 *
 */
public abstract class Device
{
	private final String name;
	private final HashSet<DeviceInput> inputs = new HashSet<>();
	private final HashSet<DeviceOutput> outputs = new HashSet<>();
	
	/**
	 * Creates a new device with a given name.
	 * This name will be used to display it in the control UI.
	 * @param name The name to give to the device.
	 */
	public Device(final String name)
	{
		this.name = name;
	}
	
	/**
	 * Adds an input to the device.
	 * @param input The input to add.
	 */
	protected void addInput(DeviceInput input)
	{
		inputs.add(input);
	}
	
	/**
	 * Adds an output to the device.
	 * @param output The output to add.
	 */
	protected void addOutput(DeviceOutput output)
	{
		outputs.add(output);
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
	 * Gets a collection of all inputs that the device supports.
	 * @return The collection of all inputs.
	 */
	public Collection<DeviceInput> getInputs()
	{
		return Collections.unmodifiableCollection(inputs);
	}
	
	/**
	 * Gets a collection of all outputs that the device supports.
	 * @return The collection of all outputs.
	 */
	public Collection<DeviceOutput> getOutputs()
	{
		return Collections.unmodifiableCollection(outputs);
	}
	
}

















