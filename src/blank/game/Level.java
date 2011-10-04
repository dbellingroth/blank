package blank.game;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import blank.game.physics.PhysicsWorld;

//Die abstrake Oberklasse für alle Levels
public abstract class Level {
	
	protected PhysicsWorld world;
	protected Queue<Executor> actionList = new LinkedList<Executor>();
	protected Semaphore actionSem = new Semaphore(1);
	protected AllObjectsList allObjects;
	
	public Level() {
		world = new PhysicsWorld();
		allObjects = new AllObjectsList();
		world.start();
	}
	
	public PhysicsWorld getPhysicsWorld() {
		return this.world;
	}
	
	protected abstract void init();
	
	public void update(int delta) {
		executeActions();
		allObjects.update(delta);
	}
	
	public void render() {
		allObjects.draw();
	}
	
	public void stop() {
		world.stop();
	}
	
	
	public void keyPressed(int key) {
		
	}

	public void keyReleased(int key) {

	}

	public void mousePressed(int button) {

	}

	public void mouseReleased(int button) {

	}
	
	
	public void addAction(Executor action) {
		try {
			actionSem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		actionList.add(action);
		actionSem.release();
	}

	private void executeActions() {

		while (!actionList.isEmpty()) {
			try {
				actionSem.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			actionList.remove().execute();

			actionSem.release();
		}

	}
	
}
