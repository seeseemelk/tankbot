package be.seeseemelk.tankbot.server.devices;

import com.pi4j.io.gpio.GpioPinPwmOutput;

public class Track extends TankActiveDevice
{
	private static int count = 0;
	private final GpioPinPwmOutput pinA;
	private final GpioPinPwmOutput pinB;
	
	public Track(GpioPinPwmOutput pinA, GpioPinPwmOutput pinB)
	{
		super("Track_" + (count++));
	}

	@Override
	public double getValue()
	{
		
	}

	@Override
	public void setValue(double value)
	{
		
	}
	
}
