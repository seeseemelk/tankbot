package be.seeseemelk.tankbot.control;

import java.io.IOException;

import be.seeseemelk.tankbot.common.Connection;
import be.seeseemelk.tankbot.common.packets.BasePacket;
import be.seeseemelk.tankbot.common.packets.ControlsListPacket;
import be.seeseemelk.tankbot.common.packets.DeviceListPacket;
import be.seeseemelk.tankbot.common.packets.ListControlsPacket;
import be.seeseemelk.tankbot.common.packets.ListDevicesPacket;
import be.seeseemelk.tankbot.control.devices.RemoteDevice;

public final class ControlMain
{
	private ControlConnection connection;
	private DeviceManagement devices;

	public ControlMain()
	{
		this.connection = new ControlConnection(this);
		devices = new DeviceManagement();
	}
	
	public void connect(String ip, int port) throws IOException
	{
		connection.start(ip, port);
		
		new Thread(() -> {
			try
			{
				while (true)
				{
					connection.mainLoop();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}).start();
		
		connection.getConnection(0).writePacket(new ListDevicesPacket());
	}
	
	/**
	 * Gets the device manager.
	 * @return The device manager.
	 */
	public DeviceManagement getDeviceManagement()
	{
		return devices;
	}
	
	/**
	 * Handles any received packets from the server.
	 * @param connection The connection on which the packet was received.
	 * @throws IOException 
	 */
	public void handlePacket(Connection connection, BasePacket packet) throws IOException
	{
		if (packet instanceof DeviceListPacket)
		{
			DeviceListPacket dlp = (DeviceListPacket) packet;
			for (String name : dlp.getDeviceNames())
			{
				RemoteDevice device = new RemoteDevice(connection, name);
				devices.addDevice(device);
				System.out.format("Device: %s%n", name);
				connection.writePacket(new ListControlsPacket(device));
			}
		}
		else if (packet instanceof ControlsListPacket)
		{
			ControlsListPacket clp = (ControlsListPacket) packet;
			RemoteDevice device = (RemoteDevice) devices.getDevice(clp.getDeviceName());
			for (String name : clp.getOutputs())
			{
				device.addRemoteOutput(name);
				System.out.format("Device %s has output '%s'%n", device.getName(), name);
			}
		}
	}

	public static void main(String[] args) throws IOException
	{
		ControlMain main = new ControlMain();
		main.connect("192.168.0.73", 28000);
		
		ControlWindow window = new ControlWindow(main);
		window.setVisible(true);
	}

}
