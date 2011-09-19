package blank.game.physics;


import java.util.concurrent.Semaphore;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
 
/*
 * Wrapperklasse für die JBox2D World
 */
public class PhysicsWorld implements Runnable{
    private World world;
    private boolean stop;
    private static Semaphore physicsSemaphore = new Semaphore(1);
    public static int pixelsPerMeter = 30;
	
	public PhysicsWorld(Vec2 gravity) { 
        boolean doSleep = true;
        world = new World(gravity, doSleep);
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
			world.step(1f/60, 5, 5);
			physicsSemaphore.release();
			try {
				Thread.sleep(1000/60);
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
		obj.init(world);
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
    
}
