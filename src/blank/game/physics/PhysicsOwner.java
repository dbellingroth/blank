package blank.game.physics;

public interface PhysicsOwner {
	public void beginCollision(CollisionData collision);
	public void endCollision(CollisionData collision);
}
