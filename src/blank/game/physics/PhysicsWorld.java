package blank.game.physics;

import java.util.concurrent.Semaphore;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

/**
 * @author David Bellingroth Wrapperklasse für die JBox2D World
 */
public class PhysicsWorld implements Runnable, ContactListener {
	static World world;
	private boolean stop;
	private static Semaphore physicsSemaphore = new Semaphore(1);
	public static int pixelsPerMeter = 30;

	public PhysicsWorld(Vec2 gravity) {
		boolean doSleep = true;
		world = new World(gravity, doSleep);
		world.setContactListener(this);
	}

	public PhysicsWorld() {
		this(new Vec2(0, 10f));
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				physicsSemaphore.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			world.step(1f / 60, 8, 3);
			physicsSemaphore.release();
			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * Startet die Physiksimulation
	 */
	public void start() {
		stop = false;
		new Thread(this).start();
	}

	/*
	 * Stoppt die Physiksimulation
	 */
	public void stop() {
		stop = true;
	}

	public void addObject(PhysicsObject obj) {
		reservePhysics();
		obj.init(world);
		releasePhysics();
	}

	public void addJoint(PhysicsJoint joint) {
		reservePhysics();
		joint.init(world);
		releasePhysics();
	}

	public void removeObject(PhysicsObject obj) {
		reservePhysics();
		world.destroyBody(obj.getBody());
		releasePhysics();
	}

	public static void reservePhysics() {
		try {
			physicsSemaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void releasePhysics() {
		physicsSemaphore.release();
	}

	@Override
	public void beginContact(Contact contact) {

	}

	@Override
	public void endContact(Contact contact) {
		PhysicsObject first = (PhysicsObject) contact.getFixtureA().getBody()
				.getUserData();
		PhysicsObject second = (PhysicsObject) contact.getFixtureB().getBody()
				.getUserData();
		first.endCollision(new CollisionData(contact, 0, false));
		second.endCollision(new CollisionData(contact, 0, true));
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		PhysicsObject first = (PhysicsObject) contact.getFixtureA().getBody()
				.getUserData();
		PhysicsObject second = (PhysicsObject) contact.getFixtureB().getBody()
				.getUserData();
		first.beginCollision(new CollisionData(contact,
				impulse.normalImpulses[0], false));
		second.beginCollision(new CollisionData(contact,
				impulse.normalImpulses[0], true));
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		PhysicsObject first = (PhysicsObject) contact.getFixtureA().getBody()
				.getUserData();
		PhysicsObject second = (PhysicsObject) contact.getFixtureB().getBody()
				.getUserData();
		first.beforeCollision(new CollisionData(contact, 0, false));
		second.beforeCollision(new CollisionData(contact, 0, true));
	}

	public void step() {
		world.step(1f / 60, 5, 5);
	}

}
