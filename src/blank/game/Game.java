package blank.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;

import org.jbox2d.common.Vec2;

import blank.game.physics.PhysicsBox;
import blank.game.physics.PhysicsStaticBlock;
import blank.game.physics.PhysicsWorld;
import blank.game.rendering.Sprite;
/**
 * Die eigentliche Hauptklasse mit der Mainmethode.
 * @author Kilian Helmenstein, David Bellingroth
 *
 */
public class Game {

	Sprite test,test2;
	
	Sprite trunk, branch;
	
	private static PhysicsWorld world;
	private PhysicsBox rect;
	private LinkedList<Ball> balls;

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
		
		world = new PhysicsWorld();
		
		rect = new PhysicsBox(105,300,60,60);
		world.addObject(rect);
		
		world.addObject(new PhysicsStaticBlock(0,600,800,100));
		world.addObject(new PhysicsStaticBlock(0,-100,800,100));
		world.addObject(new PhysicsStaticBlock(-100,0,100,600));
		world.addObject(new PhysicsStaticBlock(800,0,100,600));
				
		test2 = new Sprite(60, 60);
		Graphics2D g2d = test2.getGraphics2D();
		g2d.setColor(Color.MAGENTA);
		g2d.fillRect(0, 0, 60, 60);
		test2.update();
		
		balls = new LinkedList<Ball>();
		for (int i = 1; i< 40; i++) {
			for (int j = 1; j<30; j++) {
				balls.add(new Ball(i*20, j*20,(int)(Math.random()*6+4)));
			}
		}
		
		
		
		world.start();
	
	}

	/**
	 * Wird vom Window einmal pro Frame ausgeführt
	 * @param delta Das aktuelle Delta. Sollte bei allen Animationen etc. berücksichtigt werden!
	 */
	protected void update(int delta) {
		
		test2.setTranslate(new Point2D.Double(rect.getPosition().x-30,rect.getPosition().y-30));
		test2.setRotationPoint(new Point2D.Double(30,30));
		test2.setRotationAngle(rect.getAngle());
		
		//rect.applyAngularImpulse(3f);
		rect.applyForce(new Vec2(-5000+(float)Math.random()*10000,-5000+(float)Math.random()*10000), new Vec2(25,25));
		
		//balls.get((int)(Math.random()*(balls.size()-1)+0.9999)).getPhysicsObject().applyForce(new Vec2(-100+(float)Math.random()*200,-100+(float)Math.random()*200), new Vec2(5,5));
		
		render();
	}
	
	private void render() {
		test2.draw();
		
		for (Ball b : balls) {
			b.draw();
		}
	}
	
	public void stop() {
		world.stop();
	}	
	
}
