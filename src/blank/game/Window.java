package blank.game;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Window {
	
	private double i = 0;
	private long fps,lastFPS,lastFrame;
	private String title;
	private int delta;
	
	Sprite megaman;
	Sprite kreuz;
		
	public Window(String title, int width, int height) {
		this.title = title;
		try {
		    Display.setDisplayMode(new DisplayMode(width, height));
		    //Display.setFullscreen(true);
		    //Display.setVSyncEnabled(true);
		    Display.setTitle(title);
		    Display.create();
		} catch (LWJGLException e) {
		    e.printStackTrace();
		    System.exit(0);
		}

		
		 kreuz = new Sprite("res/kreuz.png");
		 megaman = new Sprite("res/megaman.png");
		
		// init OpenGL here
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);               
        
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          
        
        // enable alpha blending
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
					
		// disable the OpenGL depth test since we're rendering 2D graphics
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		
		lastFPS = getTime();
		delta = getDelta();
		
		
		while (!Display.isCloseRequested()) {
			
			delta = getDelta();
			
		    // render OpenGL here
			// clear screen
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();	
			
			render();
			
			updateFPS();
			
		    Display.update();

		    Display.sync(200); // cap fps to 200fps


		}
			
		Display.destroy();
	}
	
	private void render() {
		
		kreuz.draw(0, 0, (float)i);
		kreuz.draw(64, 64, -(float)i);
		kreuz.draw(128, 128, (float)i);
		
		megaman.draw(2*i, 300+50*Math.sin(i/360*2*Math.PI));
		
		i = (i+0.1*delta)%360;
		
	}
	
	public void updateFPS() {
	    if (getTime() - lastFPS > 1000) {
	        Display.setTitle(title+" running on "+Display.getAdapter()+"    FPS: " + fps); 
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
