package be.seeseemelk.tankbot.common.devices;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
	private final Map<String, DeviceInput> inputs = new HashMap<>();
	private final Map<String, DeviceOutput> outputs = new HashMap<>();
	
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
		inputs.put(input.getName(), input);
	}
	
	/**
	 * Adds an output to the device.
	 * @param output The output to add.
	 */
	protected void addOutput(DeviceOutput output)
	{
		outputs.put(output.getName(), output);
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
		return Collections.unmodifiableCollection(inputs.values());
	}
	
	/**
	 * Gets a collection of all outputs that the device supports.
	 * @return The collection of all outputs.
	 */
	public Collection<DeviceOutput> getOutputs()
	{
		return Collections.unmodifiableCollection(outputs.values());
	}
	
	/**
	 * Gets an input with a given name.
	 * @param name The name of the input.
	 * @return The input or {@code null} if there is no input.
	 */
	public DeviceInput getInput(String name)
	{
		return inputs.get(name);
	}
	
	/**
	 * Gets an output with a given name.
	 * @param name The name of the output.
	 * @return The output or {@code null} if there is no output.
	 */
	public DeviceOutput getOutput(String name)
	{
		return outputs.get(name);
	}
	
}

















