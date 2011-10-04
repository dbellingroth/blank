package blank.game;


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