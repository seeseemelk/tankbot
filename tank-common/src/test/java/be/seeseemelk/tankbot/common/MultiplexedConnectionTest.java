package be.seeseemelk.tankbot.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assume.assumeNotNull;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.junit.Before;
import org.junit.Test;

public class MultiplexedConnectionTest
{
	private MultiplexedConnection connection;
	private PipedInputStream input;
	private PipedOutputStream output;
	
	@Before
	public void setUp() throws IOException
	{
		input = new PipedInputStream();
		output = new PipedOutputStream(input);
		connection = new MultiplexedConnection(input, output);
	}
	
	@Test
	public void getInput_InputSet_GetsInput()
	{
		assertSame(input, connection.getInputStream());
	}
	
	@Test
	public void getOutput_OutputSet_GetsOutput()
	{
		assertSame(output, connection.getOutputStream());
	}
	
	@Test
	public void getConnection_AnyNumber_GetsConnection()
	{
		assertNotNull("Connection was null", connection.getConnection(0));
	}
	
	@Test
	public void getConnection_SameNumberTwice_EqualConnections()
	{
		Connection a = connection.getConnection(3);
		Connection b = connection.getConnection(3);
		assumeNotNull(a);
		assumeNotNull(b);
		assertEquals(a, b);
	}
	
	@Test
	public void getConnection_DifferentNumber_DifferentConnections()
	{
		Connection a = connection.getConnection(3);
		Connection b = connection.getConnection(5);
		assumeNotNull(a);
		assumeNotNull(b);
		assertNotEquals(a, b);
	}
	
	@Test
	public void getConnection_AnyNumber_HasSameNumber()
	{
		Connection conn = connection.getConnection(6);
		assumeNotNull(conn);
		assertEquals(6, conn.getConnectionNumber());
	}
}












