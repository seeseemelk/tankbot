package be.seeseemelk.tankbot.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class ControlWindow extends JFrame
{
	private final ControlMain main;

	public ControlWindow(ControlMain main)
	{
		super("Tank Bot Control Window");
		this.main = main;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		DeviceManagement devices = main.getDeviceManagement();
		
		addKeyListener(new KeyListener()
		{
			
			@Override
			public void keyTyped(KeyEvent e)
			{
			}
			
			@Override
			public void keyPressed(KeyEvent e)
			{
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_UP)
				{
					devices.getDevice("motor_0").getOutput("control").setValue(1);
					devices.getDevice("motor_1").getOutput("control").setValue(1);
				}
				else if (key == KeyEvent.VK_DOWN)
				{
					devices.getDevice("motor_0").getOutput("control").setValue(-1);
					devices.getDevice("motor_1").getOutput("control").setValue(-1);
				}
				else if (key == KeyEvent.VK_LEFT)
				{
					devices.getDevice("motor_0").getOutput("control").setValue(-1);
					devices.getDevice("motor_1").getOutput("control").setValue(1);
				}
				else if (key == KeyEvent.VK_RIGHT)
				{
					devices.getDevice("motor_0").getOutput("control").setValue(1);
					devices.getDevice("motor_1").getOutput("control").setValue(-1);
				}
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN)
				{
					devices.getDevice("motor_0").getOutput("control").setValue(0);
					devices.getDevice("motor_1").getOutput("control").setValue(0);
				}
			}
		});
	}

}
