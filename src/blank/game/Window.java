package blank.game;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Window {
	
	private int i = 0;
	private long fps,lastFPS,lastFrame;
	
	public Window(String title, int width, int height) {
		try {
		    Display.setDisplayMode(new DisplayMode(width, height));
		    Display.setFullscreen(true);
		    Display.setTitle(title);
		    Display.create();
		} catch (LWJGLException e) {
		    e.printStackTrace();
		    System.exit(0);
		}

		// init OpenGL here
		
		// enable textures since we're going to use these for our sprites
		GL11.glEnable(GL11.GL_TEXTURE_2D);
					
		// disable the OpenGL depth test since we're rendering 2D graphics
		GL11.glDisable(GL11.GL_DEPTH_TEST);
					
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
					
		GL11.glOrtho(0, width, height, 0, -1, 1);
		
		lastFPS = getTime();
		
		while (!Display.isCloseRequested()) {
		
		    // render OpenGL here
			// clear screen
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();	
			
			render();
			
		    Display.update();
		    Display.sync(60); // cap fps to 60fps


		}
			
		Display.destroy();
	}
	
	private void render() {
		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(0.5f,0.5f,1.0f);
			
		// draw quad
		GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(i+100,100);
		    GL11.glVertex2f(i+100+200,100);
		    GL11.glVertex2f(i+100+200,100+200);
		    GL11.glVertex2f(i+100,100+200);
		GL11.glEnd();
		i+=getDelta();
		updateFPS();
	}
	
	public void updateFPS() {
	    if (getTime() - lastFPS > 1000) {
	        Display.setTitle("blank    FPS: " + fps); 
	        fps = 0; //reset the FPS counter
	        lastFPS += 1000; //add one second
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
	
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	    	
	    return delta;
	}
	
}
