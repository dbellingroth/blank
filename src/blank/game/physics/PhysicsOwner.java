package blank.game.physics;

public interface PhysicsOwner {

	public void endCollision(CollisionData data);

	public void beginCollision(CollisionData collision);

	/**
	 * Wird aufgerufen bevor die Kollision berechnet wird. Kann benutzt werden
	 * um die Kollision zu ignorieren. Da Die Methode vom Physikthread
	 * aufgerufen werden muss, hier bitte keine OpenGL Befehle oder nicht
	 * threadgeeignete Methodenaufrufe.
	 */
	public void beforeCollision(CollisionData data);

}
