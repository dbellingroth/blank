package physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

public abstract class PhysicsObject {
	protected Body body;
	
	public abstract void init(World world);
	
	public Vec2 getPosition() {
		return body.getPosition();
	}
	
	public float getAngle() {
		return body.getAngle();
	}
	
}
