package be.seeseemelk.tankbot.server.devices;

import com.pi4j.io.gpio.GpioPinPwmOutput;

public class Track extends Device
{
	private static int count = 0;
	
	public Track(GpioPinPwmOutput pinA, GpioPinPwmOutput pinB)
	{
		super("Track_" + (count++));
		addOutput(new Control(pinA, pinB));
	}
	
}

class Control extends DeviceOutput
{
	private final GpioPinPwmOutput pinA;
	private final GpioPinPwmOutput pinB;
	
	protected Control(GpioPinPwmOutput pinA, GpioPinPwmOutput pinB)
	{
		super("control");
		this.pinA = pinA;
		this.pinB = pinB;
	}

	@Override
	public void setValue(double value)
	{
	}

	@Override
	public double getValue()
	{
		return 0;
	}
	
}