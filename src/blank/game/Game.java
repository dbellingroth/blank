package blank.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;


import blank.game.physics.PhysicsCircle;
import blank.game.physics.PhysicsStaticBlock;
import blank.game.physics.PhysicsWorld;
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
		circle = new PhysicsCircle(100,30);
		world.addObject(circle);
		world.addObject(new PhysicsCircle(200,100));
		world.addObject(new PhysicsStaticBlock(0,600,800,100));
		
		test = new Sprite(50, 50);
		Graphics2D g2d = test.getGraphics2D();
		g2d.setColor(Color.BLUE);
		g2d.fillOval(0, 0, 50, 50);
		g2d.setColor(Color.RED);
		g2d.drawLine(25, 0, 25, 50);
		g2d.drawLine(0, 25, 50, 25);
		test.update();
		
		world.start();
	
	}

	/**
	 * Wird vom Window einmal pro Frame ausgeführt
	 * @param delta Das aktuelle Delta. Sollte bei allen Animationen etc. berücksichtigt werden!
	 */
	protected void update(int delta) {
		
		test.setTranslate(new Point2D.Double(circle.getPosition().x-25,circle.getPosition().y-25));
		test.setRotationPoint(new Point2D.Double(25,25));
		test.setRotationAngle(circle.getAngle());
		circle.applyAngularImpulse(0.01f);
		test.draw();
		
	}
	
	public void stop() {
		world.stop();
	}

}
