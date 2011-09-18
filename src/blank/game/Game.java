package blank.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

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
	
	

	public static void main(String args[]) {
		new Window(new Game(), 800, 600);
	}

	public Game() {

	}

	/**
	 * Wird vom Window nach dem initialisieren von OpenGL aufgerufen
	 */
	public void init() {
		test = new Sprite(100, 200);
		Graphics2D g2d = test.getGraphics2D();
		g2d.setColor(Color.BLUE);
		g2d.fillRect(0, 0, 100, 200);
		test.update();
		
		
		
		//Stamm	
		trunk = new Sprite(50, 200);
		Graphics2D trunk_g2d = trunk.getGraphics2D();
		trunk_g2d.setColor(Color.GREEN);
		trunk_g2d.fillRect(0, 0, 50, 200);
		trunk.setZIndex(0);
		trunk.update();
		//Ast
		branch = new Sprite(20, 50);
		Graphics2D branch_g2d = branch.getGraphics2D();
		branch_g2d.setColor(Color.RED);
		branch_g2d.fillRect(0, 0, 50, 200);
		branch.setZIndex(1);
		branch.update();
	
	
	}

	/**
	 * Wird vom Window einmal pro Frame ausgeführt
	 * @param delta Das aktuelle Delta. Sollte bei allen Animationen etc. berücksichtigt werden!
	 */
	protected void update(int delta) {
		test.setRotationAngle((System.currentTimeMillis() / 10) % 360);
		test.setRotationPoint(new Point2D.Double(50, 100));
		test.setTranslate(new Point2D.Double(200, 200));
		test.setScaleFactor(new Point2D.Double(((double)System.currentTimeMillis()%1000)/500,((double)System.currentTimeMillis()%1000)/500));
		test.draw();
		
		test.setTranslate(new Point2D.Double(0, 0));
		
		TransformationGroup testgroup = new TransformationGroup();
		testgroup.addChild(test);
		testgroup.setTranslate(new Point2D.Double(200, 200));
		testgroup.setRotationAngle((System.currentTimeMillis() / 20) % 360);
		testgroup.setRotationPoint(new Point2D.Double(50,100));
		testgroup.draw();
		
		
		
		trunk.setTranslate(new Point2D.Double(500, 350));
		trunk.draw();
		
		TransformationGroup tree = new TransformationGroup();

		tree.setTranslate(trunk.getTranslate());
		
		tree.addChild(branch);
		branch.setRotationAngle((System.currentTimeMillis() / 20) % 20);
		branch.setRotationPoint(new Point2D.Double(10, -25));

		tree.draw();
		
		
		
	}

}
