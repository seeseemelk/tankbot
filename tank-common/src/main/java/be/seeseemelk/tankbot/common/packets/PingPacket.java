package be.seeseemelk.tankbot.common.packets;

/**
 * A ping packet can be used to test if the network connection is still alive
 * and responding. As a response to a ping packet, a pong packet will always be
 * returned by the peer.
 */
public class PingPacket extends BasePacket
{
	private static final long serialVersionUID = 1L;
	
}
