package be.seeseemelk.tankbot.common.packets;

import be.seeseemelk.tankbot.common.devices.Device;
import be.seeseemelk.tankbot.common.devices.DeviceOutput;

public class SetOutputPacket extends BasePacket
{
	private static final long serialVersionUID = 1054351702822513430L;
	private final String device;
	private final String output;
	private final double value;

	public SetOutputPacket(Device device, DeviceOutput output, double value)
	{
		this.device = device.getName();
		this.output = output.getName();
		this.value = value;
	}
	
	/**
	 * Gets the name of the device.
	 * @return The name of the device.
	 */
	public String getDeviceName()
	{
		return device;
	}
	
	/**
	 * Gets the name of the control output of which to change the name.
	 * @return The name of the control output.
	 */
	public String getOutputName()
	{
		return output;
	}
	
	/**
	 * Gets the value to send to the output.
	 * @return The value to send to the output.
	 */
	public double getValue()
	{
		return value;
	}

}
