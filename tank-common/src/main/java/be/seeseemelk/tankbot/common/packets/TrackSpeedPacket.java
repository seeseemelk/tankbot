package be.seeseemelk.tankbot.common.packets;

/**
 * A {@code TrackSpeedPacket} describes the speed of both tracks. Each speed
 * should be a value between {@code -1.0} and {@code 1.0}. A value of
 * {@code 1.0} is the fastest speed forward, {@code -1.0} is the fastest speed
 * in reverse. A value of {@code NaN} should be specified if the speed should
 * not be changed for the given track.
 * 
 * @author seeseemelk
 *
 */
public class TrackSpeedPacket extends BasePacket
{
	private static final long serialVersionUID = 9049879704290896830L;
	private final double speedLeft;
	private final double speedRight;
	
	public TrackSpeedPacket(final double speedLeft, final double speedRight)
	{
		this.speedLeft = speedLeft;
		this.speedRight = speedRight;
	}
	
	/**
	 * Gets the speed at which the left track should move.
	 * 
	 * @return The speed at which the left track should move.
	 */
	public double getSpeedLeft()
	{
		return speedLeft;
	}
	
	/**
	 * Gets the speed at which the right track should move.
	 * 
	 * @return The speed at which the right track should move.
	 */
	public double getSpeedRight()
	{
		return speedRight;
	}
	
}
