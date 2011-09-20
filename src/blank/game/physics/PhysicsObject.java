package blank.game.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

/**
 * Die abstrakte Oberklasse aller Physikalischen Körper.
 * 
 * @author David Bellingroth
 * 
 */
public abstract class PhysicsObject {
	protected Body body;
	private PhysicsOwner owner = null;

	// Initialisiert das Objekt, erzeugt den Body und liefert ihn zurück, damit
	// PhysicsWorld in die KollisionsHashMap packen kann
	public abstract void init(World world);

	/**
	 * Gibt die aktuelle Position des Objekts zurück
	 * 
	 * @return Position
	 */
	public Vec2 getPosition() {
		PhysicsWorld.reservePhysics();
		Vec2 result = new Vec2(body.getPosition().x
				* PhysicsWorld.pixelsPerMeter, body.getPosition().y
				* PhysicsWorld.pixelsPerMeter);
		PhysicsWorld.releasePhysics();
		return result;

	}

	/**
	 * Gibt den aktuellen Drehwinkel des Objekts zurück
	 * 
	 * @return Winkel in Grad
	 */
	public double getAngle() {
		PhysicsWorld.reservePhysics();
		double result = (body.getAngle() / (2 * Math.PI)) * 360;
		PhysicsWorld.releasePhysics();
		return result;
	}

	public void applyAngularImpulse(float impulse) {
		body.applyAngularImpulse(impulse);
	}

	public void applyForce(Vec2 force, Vec2 point) {
		body.applyForce(force, point);
	}

	public void applyLinearImpulse(Vec2 impulse, Vec2 point) {
		body.applyLinearImpulse(impulse, point);
	}

	public void setOwner(PhysicsOwner owner) {
		this.owner = owner;
	}

	protected void beginCollision(CollisionData data) {
		// System.out.println(this+" meldet:   "+data.getFirstObject() +
		// " kollidiert mit "+data.getSecondObject()+
		// "   Impuls:"+data.getImpulse());
		if (owner != null)
			owner.beginCollision(data);
	}

	protected void endCollision(CollisionData data) {
		if (owner != null)
			owner.endCollision(data);
	}

	protected Body getBody() {
		return this.body;
	}

}
