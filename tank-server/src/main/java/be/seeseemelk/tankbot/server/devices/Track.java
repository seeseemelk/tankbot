package be.seeseemelk.tankbot.server.devices;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class Track extends Device
{
	private static int count = 0;
	
	public Track(GpioPinDigitalOutput pinForward, GpioPinDigitalOutput pinBackward)
	{
		super("track_" + (count++));
		addOutput(new Control(pinForward, pinBackward));
	}
	
}

class Control extends DeviceOutput
{
	private final GpioPinDigitalOutput pinForward;
	private final GpioPinDigitalOutput pinBackward;
	private int currentValue = 0;
	
	protected Control(GpioPinDigitalOutput pinForward, GpioPinDigitalOutput pinBackward)
	{
		super("control");
		this.pinForward = pinForward;
		this.pinBackward = pinBackward;
	}

	@Override
	public void setValue(double value)
	{
		currentValue = (int) value;
		if (currentValue == 0)
		{
			pinForward.setState(false);
			pinBackward.setState(false);
		}
		else if (currentValue > 0)
		{
			pinForward.setState(false);
			pinBackward.setState(true);
		}
		else
		{
			pinForward.setState(true);
			pinBackward.setState(false);
		}
	}

	@Override
	public double getValue()
	{
		return currentValue;
	}
	
}