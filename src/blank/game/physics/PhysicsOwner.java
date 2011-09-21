package blank.game.physics;

import org.jbox2d.common.Vec2;

public interface PhysicsOwner {

	public void endCollision(CollisionData data);

	public void beginCollision(CollisionData collision);

}
