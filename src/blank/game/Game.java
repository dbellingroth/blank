package blank.game;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import org.jbox2d.common.Vec2;


import blank.game.physics.PhysicsStaticBlock;
import blank.game.physics.PhysicsWorld;
import blank.game.rendering.Sprite;


/**
 * Die eigentliche Hauptklasse mit der Mainmethode.
 * @author Kilian Helmenstein, David Bellingroth
 *
 */
public class Game {


	private static PhysicsWorld world;
	private static Queue<Executor> actionList = new LinkedList<Executor>(); //Qeue von Executors, die ausgeführt werden sollen
	private static Semaphore actionSem = new Semaphore(1);

	private AllObjectsList allObjects;
	
	private Sprite wald;
	
	
	public static void main(String args[]) {
		new Window(new Game(), 800, 600);
	} 

	public Game() {
		
	}
	
	public static PhysicsWorld getPhysicsWorld() {
		return world;
	}

	/**
	 * Wird vom Window nach dem initialisieren von OpenGL aufgerufen
	 * @throws InterruptedException 
	 */
	public void init() {
		
		wald = new Sprite("res/wald.jpg");
		
		world = new PhysicsWorld();
		
		
		allObjects = new AllObjectsList();
		
	
		world.addObject(new PhysicsStaticBlock(0,600,800,100));
		world.addObject(new PhysicsStaticBlock(0,-100,800,100));
		world.addObject(new PhysicsStaticBlock(-100,0,100,600));
		world.addObject(new PhysicsStaticBlock(800,0,100,600));
		
				
//		Bälle hinzufügen
		for (int i = 1; i< 40; i+=2) {
			for (int j = 1; j<30; j+=2) {
				allObjects.add(new Ball(i*20, j*20,(int)(Math.random()*12+4)));
				
				ArrayList<Vec2> positions = new ArrayList<Vec2>();
				positions.add(new Vec2(0, 0));
				positions.add(new Vec2((float)Math.random()*70+5, (float)Math.random()*70+5));
				positions.add(new Vec2(0, (float)Math.random()*70+5));
				
				allObjects.add(new DynamicPolygon(i*20, j*20, positions));
			}
		}
				
		
//		das Test-Rechteck hinzufügen
		allObjects.add(new DynamicRectangle(105, 300, 70, 70));
		
		
		world.start();
	}

	/**
	 * Wird vom Window einmal pro Frame ausgeführt
	 * @param delta Das aktuelle Delta. Sollte bei allen Animationen etc. berücksichtigt werden!
	 */
	protected void update(int delta) {
		
		executeActions();
			
		//balls.get((int)(Math.random()*(balls.size()-1)+0.9999)).getPhysicsObject().applyForce(new Vec2(-100+(float)Math.random()*200,-100+(float)Math.random()*200), new Vec2(5,5));
		
		allObjects.update(delta);
		
		
		render();
	}
	
	private void render() {
		
		wald.draw();
		allObjects.draw();
		
	}
	
	public void stop() {
		world.stop();
	}
	
	public static void addAction(Executor action) {
		try {
			actionSem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actionList.add(action);
		actionSem.release();
	}
	
	private void executeActions() {
		
		while (!actionList.isEmpty()){
			try {
				actionSem.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			actionList.remove().execute();
			
			actionSem.release();
		}
		
	}
	
	public void keyPressed(int key) {
		System.out.println(key + " pressed");
	}
	
	public void keyReleased(int key) {
		System.out.println(key + " released");
	}
	
}
