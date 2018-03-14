package be.seeseemelk.tankbot.control.devices;

import be.seeseemelk.tankbot.common.Connection;
import be.seeseemelk.tankbot.common.devices.Device;

public class RemoteDevice extends Device
{
	private final Connection connection;

	public RemoteDevice(Connection connection, String name)
	{
		super(name);
		this.connection = connection;
	}
	
	/**
	 * Adds a remote output to the device.
	 * @param name The name the remote output has.
	 */
	public void addRemoteOutput(String name)
	{
		RemoteOutput output = new RemoteOutput(connection, this, name);
		addOutput(output);
	}

}
