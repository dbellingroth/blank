package blank.game.physics;


public interface PhysicsOwner {

	public void endCollision(CollisionData data);

	public void beginCollision(CollisionData collision);

}
