package blank.game.physics;

import org.jbox2d.common.Vec2;

public interface PhysicsOwner {

	public void endCollision(CollisionData data);

	public void beginCollision(CollisionData collision);

	public void applyAngularImpulse(float impulse);

	public void applyForce(Vec2 force, Vec2 point);

	public void applyLinearImpulse(Vec2 impulse, Vec2 point);

}
