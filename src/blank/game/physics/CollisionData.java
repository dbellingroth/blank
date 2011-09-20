/**
 * 
 */
package blank.game.physics;

/**
 * @author Kilian Helmenstein, David Bellingroth Speicher Informationen über
 *         eine Kollision (PhysicsObjects die beteiligt sind etc...)
 */
public class CollisionData {
	private PhysicsObject firstObject, secondObject;
	private float impulse;

	public CollisionData(PhysicsObject firstObject, PhysicsObject secondObject,
			float impulse) {
		this.firstObject = firstObject;
		this.secondObject = secondObject;
		this.impulse = impulse;
	}

	public PhysicsObject getFirstObject() {
		return firstObject;
	}

	public PhysicsObject getSecondObject() {
		return secondObject;
	}

	public float getImpulse() {
		return impulse;
	}

}
