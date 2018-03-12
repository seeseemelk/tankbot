package be.seeseemelk.tankbot.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.junit.Before;
import org.junit.Test;

import be.seeseemelk.tankbot.common.packets.BasePacket;
import be.seeseemelk.tankbot.common.packets.PingPacket;

public class ConnectionTest
{
	private MultiplexedConnection mc;

	@Before
	public void setUp() throws Exception
	{
		PipedInputStream input = new PipedInputStream();
		PipedOutputStream output = new PipedOutputStream(input);
		mc = new MultiplexedConnection(input, output);
	}

	@Test
	public void transferPingPacketOverSameConnection() throws IOException
	{
		Connection connection = mc.getConnection(0);
		assertFalse(connection.hasPacket());
		PingPacket packet = new PingPacket();
		connection.writePacket(packet);
		assertTrue(connection.hasPacket());
		BasePacket readPacket = connection.readPacket();
		assertNotNull(readPacket);
		assertTrue(readPacket instanceof PingPacket);
	}

	@Test
	public void transferPacketOverSameConnectionSecondOneDoesNotReceive() throws IOException
	{
		Connection a = mc.getConnection(0);
		Connection b = mc.getConnection(1);
		
		PingPacket packet = new PingPacket();
		a.writePacket(packet);
		
		assertTrue(a.hasPacket());
		assertFalse(b.hasPacket());
	}
	
}
