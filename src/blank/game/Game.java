package blank.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import blank.game.physics.GearWheelJoint;
import blank.game.physics.LiftJoint;
import blank.game.physics.NailJoint;
import blank.game.physics.PhysicsStaticBlock;
import blank.game.physics.PhysicsWorld;
import blank.game.physics.PneumaticJoint;
import blank.game.physics.StickJoint;
import blank.game.rendering.Sprite;

/**
 * Die eigentliche Hauptklasse mit der Mainmethode.
 * 
 * @author Kilian Helmenstein, David Bellingroth
 * 
 */
public class Game {

	private static PhysicsWorld world;
	private static Queue<Executor> actionList = new LinkedList<Executor>(); // Qeue
																			// von
																			// Executors,
																			// die
																			// ausgeführt
																			// werden
																			// sollen
	private static Semaphore actionSem = new Semaphore(1);

	public static InputHandler inputHandler = new InputHandler();

	private AllObjectsList allObjects;

	private Sprite test_backround;
	private Rectangle player;

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
	 * 
	 * @throws InterruptedException
	 */
	public void init() {

		test_backround = new Sprite("res/backround_test.png");

		world = new PhysicsWorld();

		allObjects = new AllObjectsList();

		world.addObject(new PhysicsStaticBlock(0, 530, 800, 100));
		world.addObject(new PhysicsStaticBlock(0, -100, 800, 100));
		world.addObject(new PhysicsStaticBlock(-100, 0, 100, 600));
		world.addObject(new PhysicsStaticBlock(800, 0, 100, 600));

		// Bälle hinzufügen
		// for (int i = 1; i< 40; i+=2) {
		// for (int j = 1; j<30; j+=2) {
		// allObjects.add(new Ball(i*20, j*20,(int)(Math.random()*12+4)));
		//
		// ArrayList<Vec2> positions = new ArrayList<Vec2>();
		// positions.add(new Vec2(0, 0));
		// positions.add(new Vec2((float)Math.random()*70+5,
		// (float)Math.random()*70+5));
		// positions.add(new Vec2(0, (float)Math.random()*70+5));
		//
		// allObjects.add(new DynamicPolygon(i*20, j*20, positions));
		// }
		// }

		// das Test-Rechteck hinzufügen
		player = new Rectangle(100, 300, 40, 40, BodyType.DYNAMIC);
		player.getPhysicsObject().setMass(10f);
		// //left
		// inputHandler.addKeyPressedListener(dr, 203, 1);
		// inputHandler.addKeyReleasedListener(dr, 203, 1);
		// //right
		// inputHandler.addKeyPressedListener(dr, 205, 2);
		// inputHandler.addKeyReleasedListener(dr, 205, 2);
		// //up
		// inputHandler.addKeyPressedListener(dr, 200, 3);
		// inputHandler.addKeyReleasedListener(dr, 200, 3);
		// //down
		// inputHandler.addKeyPressedListener(dr, 208, 4);
		// inputHandler.addKeyReleasedListener(dr, 208, 4);
		allObjects.add(player);

		Circle bodyA = new Circle(100, 300, 40, BodyType.DYNAMIC);
		Circle bodyB = new Circle(100, 300, 10, BodyType.STATIC);
		
		Circle bodyC = new Circle(400, 300, 30, BodyType.DYNAMIC);
		Circle bodyD = new Circle(400, 300, 10, BodyType.STATIC);
		
		allObjects.add(bodyA);
		allObjects.add(bodyB);
		allObjects.add(bodyC);
		allObjects.add(bodyD);
		
		NailJoint nJoint = new NailJoint(bodyA.getPhysicsObject(), bodyB.getPhysicsObject(),
								new Vec2(0, 0));
		world.addJoint(nJoint);
		
		PneumaticJoint pJoint = new PneumaticJoint(bodyC.getPhysicsObject(), bodyD.getPhysicsObject(),
								new Vec2(0, 0), new Vec2(0f, 1f));
		pJoint.setUpperTranslation(2f);
		pJoint.setLowerTranslation(-2f);
		pJoint.enableTransLimit(true);
		world.addJoint(pJoint);		
		
//		LiftJoint liftJoint = new LiftJoint(bodyA.getPhysicsObject(), bodyB.getPhysicsObject(), 
//					new Vec2(100, 100), new Vec2(350, 100), new Vec2(0, 0), new Vec2(0, 0), 2);
		
		GearWheelJoint gwJoint = new GearWheelJoint(bodyC.getPhysicsObject(), bodyD.getPhysicsObject(),
					nJoint, pJoint, 0.1f);
		world.addJoint(gwJoint);
		

		ArrayList<Vec2> points = new ArrayList<Vec2>();

		points.add(new Vec2(100, 100));
		points.add(new Vec2(200, 100));
		points.add(new Vec2(200, 450));
		points.add(new Vec2(450, 400));
		
		
		allObjects.add(new Lines(points, BodyType.STATIC));
//		allObjects.add(new Line(new Vec2(100, 100), new Vec2(200, 200), BodyType.STATIC));
		
		world.start();
	}

	/**
	 * Wird vom Window einmal pro Frame ausgeführt
	 * 
	 * @param delta
	 *            Das aktuelle Delta. Sollte bei allen Animationen etc.
	 *            berücksichtigt werden!
	 */
	protected void update(int delta) {

		executeActions();

		// balls.get((int)(Math.random()*(balls.size()-1)+0.9999)).getPhysicsObject().applyForce(new
		// Vec2(-100+(float)Math.random()*200,-100+(float)Math.random()*200),
		// new Vec2(5,5));

		allObjects.update(delta);

		render();
	}

	private void render() {

		test_backround.draw();
		allObjects.draw();

	}

	public void stop() {
		world.stop();
	}

	public static void addAction(Executor action) {
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

	// die handleInput-Methoden weiterschleifen and den InputHandler,
	// dieser verwaltet dann die Weiterleitung an die richtigen InputListeners.
	public void keyPressed(int key) {
		// inputHandler.keyPressed(key);
		player.keyPressed(key);
	}

	public void keyReleased(int key) {
		// inputHandler.keyReleased(key);
		player.keyReleased(key);
	}

	public void mousePressed(int button) {
		inputHandler.mousePressed(button);
	}

	public void mouseReleased(int button) {
		inputHandler.mouseReleased(button);
	}

}