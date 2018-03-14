package be.seeseemelk.tankbot.control.devices;

import java.io.IOException;

import be.seeseemelk.tankbot.common.Connection;
import be.seeseemelk.tankbot.common.devices.DeviceOutput;
import be.seeseemelk.tankbot.common.packets.SetOutputPacket;

class RemoteOutput extends DeviceOutput
{
	private final RemoteDevice device;
	private final Connection connection;

	public RemoteOutput(Connection connection, RemoteDevice device, String name)
	{
		super(name);
		this.device = device;
		this.connection = connection;
	}

	@Override
	public void setValue(double value)
	{
		try
		{
			SetOutputPacket packet = new SetOutputPacket(device, this, value);
			connection.writePacket(packet);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public double getValue()
	{
		return 0;
	}

}
