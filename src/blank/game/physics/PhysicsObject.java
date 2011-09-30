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
 * @author David Bellingroth
 *
 */
public abstract class PhysicsObject {
	protected Body body;
	private PhysicsOwner owner = null;
	
	public PhysicsOwner getOwner() {
		return owner;
	}

	//Initialisiert das Objekt, erzeugt den Body und liefert ihn zurück, damit PhysicsWorld in die KollisionsHashMap packen kann
	public abstract void init(World world);
	
	/**
	 * Gibt die aktuelle Position des Objekts zurück
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
		body.setTransform(Tools.convertVectorPix2Phys(position), body.getAngle());
		PhysicsWorld.releasePhysics();
	}
	
	/**
	 * Gibt den aktuellen  Drehwinkel des Objekts zurück
	 * @return Winkel in Grad
	 */
	public double getAngle() {
		PhysicsWorld.reservePhysics();
		double result = (body.getAngle()/Math.PI*180);
		PhysicsWorld.releasePhysics();
		return result;
	}
	
	public void setAngle(double angle) {
		PhysicsWorld.reservePhysics();
		body.setTransform(body.getPosition(), (float)(angle/360*Math.PI*2));
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
		Vec2 result = Tools.convertVectorPhys2Pix(body.getLinearVelocity());
		PhysicsWorld.releasePhysics();
		return result;
	}
	
	
	
	
	public void applyAngularImpulse(float impulse) {
		body.applyAngularImpulse(impulse);
	}
	
	public void applyTorque(float torque) {
		body.applyTorque(torque);
	}
	
	public void applyForce(Vec2 force, Vec2 point) {
		body.applyForce(force, Tools.convertVectorPix2Phys(point));
	}
	
	public void applyForce(Vec2 force) {
		body.applyForce(force, body.getWorldCenter());
	}
	
	public void applyLinearImpulse(Vec2 impulse, Vec2 point) {
		body.applyLinearImpulse(impulse, Tools.convertVectorPix2Phys(point));
	}
	
	public void applyLinearImpulse(Vec2 impulse) {
		body.applyLinearImpulse(impulse, body.getWorldCenter());
	}
	
	public void setOwner(PhysicsOwner owner) {
		this.owner = owner;
	}
	
	public Vec2 getMassCenter() {
		return Tools.convertVectorPhys2Pix(body.getLocalCenter());
	}
	
	public void setMassCenter(Vec2 point) {
		MassData md = new MassData();
		md.center.set(Tools.convertVectorPix2Phys(point));
		md.mass = body.getMass();
		md.I = body.getInertia();
		body.setMassData(md);
	}
	
	public void setMass(float mass) {
		MassData md = new MassData();
		md.center.set(body.getLocalCenter());
		md.mass = mass;
		md.I = body.getInertia();
		body.setMassData(md);
	}
	
	public void resetMassData() {
		body.resetMassData();
	}
	
	protected void beginCollision(final CollisionData data) {
		//System.out.println(this+" meldet:   "+data.getFirstObject() + " kollidiert mit "+data.getSecondObject()+ "   Impuls:"+data.getImpulse());
		if (owner!=null) {
			Game.addAction(new Executor() {
				public void execute() {
					owner.beginCollision(data);
				}
			}
			);
			
		}
	}
	
	protected void endCollision(final CollisionData data) {	
		if (owner!=null) {
			Game.addAction(new Executor() {
				public void execute() {
					owner.beginCollision(data);
				}
			}
			);
			
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
		body.setLinearVelocity(new Vec2(0,0));
		PhysicsWorld.releasePhysics();
	}
	
}
