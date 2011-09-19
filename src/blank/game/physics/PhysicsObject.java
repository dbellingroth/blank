package blank.game.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

public abstract class PhysicsObject {
	protected Body body;
	
	public abstract void init(World world);
	
	public Vec2 getPosition() {
		PhysicsWorld.reservePhysics();
		Vec2 result = new Vec2(body.getPosition().x*PhysicsWorld.pixelsPerMeter,body.getPosition().y*PhysicsWorld.pixelsPerMeter);
		PhysicsWorld.releasePhysics();
		return result;
		
	}
	
	public double getAngle() {
		PhysicsWorld.reservePhysics();
		double result = (body.getAngle()/(2*Math.PI))*360;
		PhysicsWorld.releasePhysics();
		return result;
	}
	
	public void applyAngularImpulse(float impulse) {
		body.applyAngularImpulse(impulse);
	}
	
}
