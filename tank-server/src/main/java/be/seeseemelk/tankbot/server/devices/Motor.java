package be.seeseemelk.tankbot.server.devices;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

import be.seeseemelk.tankbot.common.devices.Device;
import be.seeseemelk.tankbot.common.devices.DeviceOutput;

public class Motor extends Device
{
	private static int count = 0;
	
	public Motor(GpioPinDigitalOutput pinForward, GpioPinDigitalOutput pinBackward)
	{
		super("motor_" + (count++));
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
			pinForward.setState(true);
			pinBackward.setState(false);
		}
		else
		{
			pinForward.setState(false);
			pinBackward.setState(true);
		}
	}

	@Override
	public double getValue()
	{
		return currentValue;
	}
	
}