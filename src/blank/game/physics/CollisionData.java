/**
 * 
 */
package blank.game.physics;

import org.jbox2d.dynamics.contacts.Contact;

/**
 * @author Kilian Helmenstein, David Bellingroth Speicher Informationen über
 *         eine Kollision (PhysicsObjects die beteiligt sind etc...)
 */
public class CollisionData {
	private Contact contact;
	private float impulse;
	private boolean switchBodies;

	public CollisionData(Contact contact, float impulse, boolean switchBodies) {
		this.contact = contact;
		this.impulse = impulse;
		this.switchBodies = switchBodies;
	}

	public PhysicsObject getFirstObject() {
		if (switchBodies) return (PhysicsObject) contact.getFixtureB().getBody().getUserData();
		else return (PhysicsObject) contact.getFixtureA().getBody().getUserData();
	}

	public PhysicsObject getSecondObject() {
		if (switchBodies) return (PhysicsObject) contact.getFixtureA().getBody().getUserData();
		else return (PhysicsObject) contact.getFixtureB().getBody().getUserData();
	}

	public float getImpulse() {
		return impulse;
	}
	
	/**
	 * Kollision ignorieren
	 */
	public void disable() {
		contact.setEnabled(false);
	}

}
