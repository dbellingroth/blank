package blank.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;

import blank.game.physics.PhysicsBox;
import blank.game.physics.PhysicsCircle;
import blank.game.physics.PhysicsStaticBlock;
import blank.game.physics.PhysicsWorld;
import blank.game.rendering.Sprite;

/**
 * Die eigentliche Hauptklasse mit der Mainmethode.
 * 
 * @author Kilian Helmenstein, David Bellingroth
 * 
 */
public class Game {

	Sprite test, test2;

	Sprite trunk, branch;

	private static PhysicsWorld world;
	private PhysicsCircle circle;
	private PhysicsBox rect;
	private LinkedList<Ball> balls;
	private DynamicRectangle rectangle;

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

		world = new PhysicsWorld();

		world.addObject(new PhysicsStaticBlock(0, 600, 800, 100));
		world.addObject(new PhysicsStaticBlock(0, -100, 800, 100));
		world.addObject(new PhysicsStaticBlock(-100, 0, 100, 600));
		world.addObject(new PhysicsStaticBlock(800, 0, 100, 600));

		rectangle = new DynamicRectangle(105, 300, 50, 50);

		balls = new LinkedList<Ball>();
		for (int i = 1; i < 40; i++) {
			for (int j = 1; j < 30; j++) {
				balls.add(new Ball(i * 20, j * 20,
						(int) (Math.random() * 6 + 4)));
			}
		}

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

		rectangle.applyAngularImpulse(1f);
		rectangle.draw();

		for (Ball b : balls) {
			b.draw();
		}

	}

	public void stop() {
		world.stop();
	}

}
