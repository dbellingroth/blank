package blank.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;


import blank.game.physics.PhysicsBox;
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

	Sprite test,test2;
	
	Sprite trunk, branch;
	
	private PhysicsWorld world;
	private PhysicsCircle circle;
	private PhysicsBox rect;

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
		
		rect = new PhysicsBox(105,-20,60,60);
		world.addObject(rect);
		
		world.addObject(new PhysicsStaticBlock(0,600,800,100));
		
		test = new Sprite(50, 50);
		Graphics2D g2d = test.getGraphics2D();
		g2d.setColor(Color.BLUE);
		g2d.fillOval(0, 0, 50, 50);
		g2d.setColor(Color.RED);
		g2d.drawLine(25, 0, 25, 50);
		g2d.drawLine(0, 25, 50, 25);
		test.update();
		
		test2 = new Sprite(60, 60);
		g2d = test2.getGraphics2D();
		g2d.setColor(Color.MAGENTA);
		g2d.fillRect(0, 0, 60, 60);
		test2.update();
		
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
		
		test2.setTranslate(new Point2D.Double(rect.getPosition().x-30,rect.getPosition().y-30));
		test2.setRotationPoint(new Point2D.Double(30,30));
		test2.setRotationAngle(rect.getAngle());
		
		circle.applyAngularImpulse(0.1f);
		test.draw();
		test2.draw();
		
	}
	
	public void stop() {
		world.stop();
	}

}
