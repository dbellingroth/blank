package blank.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
/**
 * Die eigentliche Hauptklasse mit der Mainmethode.
 * @author Kilian Helmenstein, David Bellingroth
 *
 */
public class Game {

	Sprite test;

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
		g2d.setColor(Color.GREEN);
		g2d.fillRect(0, 0, 100, 200);
		test.update();
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
	}

}
