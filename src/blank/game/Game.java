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
import blank.game.rendering.Window;

/**
 * Die eigentliche Hauptklasse mit der Mainmethode.
 * 
 * @author Kilian Helmenstein, David Bellingroth
 * 
 */
public class Game {
	
	private static Level currentLevel;


	public static void main(String args[]) {
		new Window(new Game(), 800, 600);
	}


	/**
	 * Wird vom Window nach dem initialisieren von OpenGL aufgerufen
	 * 
	 * @throws InterruptedException
	 */
	public void init() {
		loadLevel(new TestLevel());
	}

	/**
	 * Wird vom Window einmal pro Frame ausgeführt
	 * 
	 * @param delta
	 *            Das aktuelle Delta. Sollte bei allen Animationen etc.
	 *            berücksichtigt werden!
	 */
	public void update(int delta) {

		currentLevel.update(delta);
		render();
	}

	private void render() {

		currentLevel.render();

	}

	public void keyPressed(int key) {
		currentLevel.keyPressed(key);
	}

	public void keyReleased(int key) {
		currentLevel.keyReleased(key);
	}

	public void mousePressed(int button) {
		currentLevel.mousePressed(button);
	}

	public void mouseReleased(int button) {
		currentLevel.mouseReleased(button);
	}
	
	public static Level getCurrentLevel() {
		return currentLevel;
	}
	
	public void loadLevel(Level level) {
		if (currentLevel != null) currentLevel.stop();
		currentLevel = level;
		level.init();
	}
	
	public void stop() {
		currentLevel.stop();
	}

}