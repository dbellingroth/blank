package blank.game;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * Das Spielfenster, welches den OpenGL Kontext initialisiert und die
 * eigentliche Mainloop innehat. Es ruft einmal pro Frame die Update Methode von
 * Game auf...
 * 
 * @author David Bellingroth
 * 
 */
public class Window {

	private int width, height;
	private long fps, lastFPS, lastFrame;
	private int delta;
	private Game parent;

	public Window(Game parent, int width, int height) {
		try {
			this.parent = parent;
			this.width = width;
			this.height = height;
			Display.setDisplayMode(new DisplayMode(width, height));
			// Display.setFullscreen(true);
			// Display.setVSyncEnabled(true);
			Display.setTitle("Misery Block");
			Display.create();
			initGL();
			parent.init();
			lastFPS = getTime();
			delta = getDelta();
			renderLoop();
		} catch (LWJGLException e) {
			System.err.printf("Display konnte nicht geöffnet werden :(");
			e.printStackTrace();
			System.exit(0);
		}

	}

	/**
	 * initialisiert den OpenGL Kontext
	 */
	private void initGL() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glViewport(0, 0, width, height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		/*
		 * GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
		 * GL11.GL_CLAMP); GL11.glTexParameteri(GL11.GL_TEXTURE_2D,
		 * GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
		 */
	}

	/**
	 * Die eigentliche renderloop, die das ganze Spiel hindurch läuft
	 */
	private void renderLoop() {

		while (!Display.isCloseRequested()) {

			delta = getDelta();
			updateFPS();

			// Clear screen
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();

			handleInput();

			// Spiel kümmert sich um den Rest
			parent.update(delta);

			Display.update();
			Display.sync(120); // FPS begrenzen

		}
		parent.stop();
		Display.destroy();
	}

	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("Misery Block running on " + Display.getAdapter()
					+ "    FPS: " + fps);
			fps = 0; // reset the FPS counter
			lastFPS += 1000; // add one second
		}
		fps++;
	}

	/**
	 * Get the time in milliseconds
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * 
	 * @return gibt das aktuelle Delta zurück
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	private void handleInput() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState())
				parent.keyPressed(Keyboard.getEventKey());
			else
				parent.keyReleased(Keyboard.getEventKey());
		}

		while (Mouse.next()) {
			if (Mouse.getEventButtonState() && Mouse.getEventButton() >= 0)
				parent.mousePressed(Mouse.getEventButton());
			else if (Mouse.getEventButton() >= 0)
				parent.mouseReleased(Mouse.getEventButton());
		}

	}

}
