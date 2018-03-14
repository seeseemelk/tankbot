package be.seeseemelk.tankbot.common.packets;

import be.seeseemelk.tankbot.common.devices.Device;
import be.seeseemelk.tankbot.common.devices.DeviceInput;
import be.seeseemelk.tankbot.common.devices.DeviceOutput;

/**
 * Contains a list of all supported devices.
 * @author seeseemelk
 *
 */
public class ControlsListPacket extends BasePacket
{
	private static final long serialVersionUID = 3276801915178465976L;
	private String device;
	private String[] inputs;
	private String[] outputs;

	public ControlsListPacket(Device device)
	{
		int i = 0;
		this.device = device.getName();
		
		inputs = new String[device.getInputs().size()];
		for (DeviceInput input : device.getInputs())
			inputs[i++] = input.getName();

		i = 0;
		outputs = new String[device.getOutputs().size()];
		for (DeviceOutput output : device.getOutputs())
			outputs[i++] = output.getName();
	}
	
	/**
	 * Gets the name of the device to which the controls belong.
	 * @return The name of the device.
	 */
	public String getDeviceName()
	{
		return device;
	}
	
	/**
	 * Gets an array of all inputs the device has.
	 * @return An array of all inputs the device has.
	 */
	public String[] getInputs()
	{
		return inputs;
	}
	
	/**
	 * Gets an array of all outputs the device has.
	 * @return An array of all outputs the device has.
	 */
	public String[] getOutputs()
	{
		return outputs;
	}

}
