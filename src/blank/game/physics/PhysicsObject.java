package blank.game.physics;

import org.jbox2d.collision.shapes.MassData;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import blank.game.Executor;
import blank.game.Game;
import blank.game.Tools;

/**
 * Die abstrakte Oberklasse aller Physikalischen Körper.
 * 
 * @author David Bellingroth
 * 
 */
public abstract class PhysicsObject {
	protected Body body;
	private PhysicsOwner owner = null;

	public PhysicsOwner getOwner() {
		return owner;
	}

	// Initialisiert das Objekt, erzeugt den Body
	protected abstract void init(World world);

	/**
	 * Gibt die aktuelle Position des Objekts zurück
	 * 
	 * @return Position
	 */
	public Vec2 getPosition() {
		PhysicsWorld.reservePhysics();
		Vec2 result = Tools.convertVectorPhys2Pix(body.getPosition());
		PhysicsWorld.releasePhysics();
		return result;
	}

	public void setPosition(Vec2 position) {
		PhysicsWorld.reservePhysics();
		body.setTransform(Tools.convertVectorPix2Phys(position),
				body.getAngle());
		PhysicsWorld.releasePhysics();
	}

	/**
	 * Gibt den aktuellen Drehwinkel des Objekts zurück
	 * 
	 * @return Winkel in Grad
	 */
	public double getAngle() {
		PhysicsWorld.reservePhysics();
		double result = (body.getAngle() / Math.PI * 180);
		PhysicsWorld.releasePhysics();
		return result;
	}

	public void setAngle(double angle) {
		PhysicsWorld.reservePhysics();
		body.setTransform(body.getPosition(),
				(float) (angle / 360 * Math.PI * 2));
		PhysicsWorld.releasePhysics();
	}

	public float getAngularSpeed() {
		PhysicsWorld.reservePhysics();
		float result = body.getAngularVelocity();
		PhysicsWorld.releasePhysics();
		return result;
	}

	public Vec2 getSpeed() {
		PhysicsWorld.reservePhysics();
		Vec2 result = Tools.convertVectorPhys2Pix(body.getLinearVelocity()).clone();
		PhysicsWorld.releasePhysics();
		return result;
	}
	
	public void setAngularSpeed(float w) {
		PhysicsWorld.reservePhysics();
		body.setAngularVelocity(w);
		PhysicsWorld.releasePhysics();
	}

	public void setSpeed(Vec2 speed) {
		PhysicsWorld.reservePhysics();
		body.setLinearVelocity(Tools.convertVectorPix2Phys(speed));
		PhysicsWorld.releasePhysics();
	}

	public void applyAngularImpulse(float impulse) {
		PhysicsWorld.reservePhysics();
		body.applyAngularImpulse(impulse);
		PhysicsWorld.releasePhysics();
	}

	public void applyTorque(float torque) {
		PhysicsWorld.reservePhysics();
		body.applyTorque(torque);
		PhysicsWorld.releasePhysics();
	}

	public void applyForce(Vec2 force, Vec2 point) {
		PhysicsWorld.reservePhysics();
		body.applyForce(force, Tools.convertVectorPix2Phys(point));
		PhysicsWorld.releasePhysics();
	}

	public void applyForce(Vec2 force) {
		PhysicsWorld.reservePhysics();
		body.applyForce(force, body.getWorldCenter());
		PhysicsWorld.releasePhysics();
	}

	public void applyLinearImpulse(Vec2 impulse, Vec2 point) {
		PhysicsWorld.reservePhysics();
		body.applyLinearImpulse(impulse, Tools.convertVectorPix2Phys(point));
		PhysicsWorld.releasePhysics();
	}

	public void applyLinearImpulse(Vec2 impulse) {
		PhysicsWorld.reservePhysics();
		body.applyLinearImpulse(impulse, body.getWorldCenter());
		PhysicsWorld.releasePhysics();
	}

	public void setOwner(PhysicsOwner owner) {
		this.owner = owner;
	}

	public Vec2 getMassCenter() {
		PhysicsWorld.reservePhysics();
		Vec2 result = Tools.convertVectorPhys2Pix(body.getLocalCenter()).clone();
		PhysicsWorld.releasePhysics();
		return result;
	}

	public void setMassCenter(Vec2 point) {
		PhysicsWorld.reservePhysics();
		MassData md = new MassData();
		md.center.set(Tools.convertVectorPix2Phys(point));
		md.mass = body.getMass();
		md.I = body.getInertia();
		body.setMassData(md);
		PhysicsWorld.releasePhysics();
	}

	public void setMass(float mass) {
		PhysicsWorld.reservePhysics();
		MassData md = new MassData();
		md.center.set(body.getLocalCenter());
		md.mass = mass;
		md.I = body.getInertia();
		body.setMassData(md);
		PhysicsWorld.releasePhysics();
	}
	
	public void setBullet(boolean bullet) {
		
	}

	public void resetMassData() {
		PhysicsWorld.reservePhysics();
		body.resetMassData();
		PhysicsWorld.releasePhysics();
	}

	protected void beginCollision(final CollisionData data) {
		// System.out.println(this+" meldet:   "+data.getFirstObject() +
		// " kollidiert mit "+data.getSecondObject()+
		// "   Impuls:"+data.getImpulse());
		if (owner != null) {
			Game.getCurrentLevel().addAction(new Executor() {
				public void execute() {
					owner.beginCollision(data);
				}
			});

		}
	}

	protected void endCollision(final CollisionData data) {
		if (owner != null) {
			Game.getCurrentLevel().addAction(new Executor() {
				public void execute() {
					owner.beginCollision(data);
				}
			});

		}
	}

	public Body getBody() {
		return this.body;
	}

	public void beforeCollision(CollisionData data) {
		if (owner != null) {
			owner.beforeCollision(data);
		}
	}

	public void stop() {
		PhysicsWorld.reservePhysics();
		body.setLinearVelocity(new Vec2(0, 0));
		PhysicsWorld.releasePhysics();
	}

}
