package blank.game.physics;


import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
 
/*
 * Wrapperklasse für die JBox2D World
 */
public class PhysicsWorld implements Runnable{
    private World world;
    private boolean stop;
	
	public PhysicsWorld(Vec2 gravity) { 
        boolean doSleep = true;
        world = new World(gravity, doSleep);
	}
	
	public PhysicsWorld() {
		this(new Vec2( 0.0f, 10f));
	}

	@Override
	public void run() {
		while (!stop) {
			world.step(1000/60, 5, 5);
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
    
}
