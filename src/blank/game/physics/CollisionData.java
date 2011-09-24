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
		if (switchBodies) return PhysicsWorld.getObjectForBody(contact.getFixtureB().getBody());
		else return PhysicsWorld.getObjectForBody(contact.getFixtureA().getBody());
	}

	public PhysicsObject getSecondObject() {
		if (switchBodies) return PhysicsWorld.getObjectForBody(contact.getFixtureA().getBody());
		else return PhysicsWorld.getObjectForBody(contact.getFixtureB().getBody());
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
