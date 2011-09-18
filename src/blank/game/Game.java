package blank.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import physics.PhysicsCircle;
import physics.PhysicsWorld;

import blank.game.rendering.Sprite;
import blank.game.rendering.TransformationGroup;
/**
 * Die eigentliche Hauptklasse mit der Mainmethode.
 * @author Kilian Helmenstein, David Bellingroth
 *
 */
public class Game {

	Sprite test;
	
	Sprite trunk, branch;
	
	private PhysicsWorld world;
	private PhysicsCircle circle;

	public static void main(String args[]) {
		new Window(new Game(), 800, 600);
	}

	public Game() {
		
	}

	/**
	 * Wird vom Window nach dem initialisieren von OpenGL aufgerufen
	 * @throws InterruptedException 
	 */
	public void init() {
		
		world = new PhysicsWorld();
		circle = new PhysicsCircle();
		world.addObject(circle);
		
		
		test = new Sprite(50, 50);
		Graphics2D g2d = test.getGraphics2D();
		g2d.setColor(Color.BLUE);
		g2d.fillOval(0, 0, 50, 50);
		test.update();
		
		world.start();
	
	}

	/**
	 * Wird vom Window einmal pro Frame ausgeführt
	 * @param delta Das aktuelle Delta. Sollte bei allen Animationen etc. berücksichtigt werden!
	 */
	protected void update(int delta) {
		
		test.setTranslate(new Point2D.Double(circle.getPosition().x,circle.getPosition().y));
		test.draw();
		
		
		
		
	}
	
	public void stop() {
		world.stop();
	}

}
