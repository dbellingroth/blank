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
	
	private Texture megaman;
	
	public Window(String title, int width, int height) {
		this.title = title;
		try {
		    Display.setDisplayMode(new DisplayMode(width, height));
		    Display.setFullscreen(true);
		    Display.setVSyncEnabled(true);
		    Display.setTitle(title);
		    Display.create();
		} catch (LWJGLException e) {
		    e.printStackTrace();
		    System.exit(0);
		}

		
		
		try {
			megaman = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/megaman.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// init OpenGL here
		
		// enable textures since we're going to use these for our sprites
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		// enable alpha blending
		// Damit man auch Transparente Texturen haben kann...
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					
		// disable the OpenGL depth test since we're rendering 2D graphics
		GL11.glDisable(GL11.GL_DEPTH_TEST);
					
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
					
		GL11.glOrtho(0, width, height, 0, -1, 1);
		
		lastFPS = getTime();
		
		
		while (!Display.isCloseRequested()) {
			
			delta = getDelta();
			
		    // render OpenGL here
			// clear screen
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();	
			
			render();
			
			updateFPS();
			
		    Display.update();
		    //Display.sync(60); // cap fps to 60fps


		}
			
		Display.destroy();
	}
	
	private void render() {
		
		Color.white.bind();
		megaman.bind(); // or GL11.glBind(texture.getTextureID());
		
		System.out.println(delta);
		i+=0.001*delta;
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f((int)Math.round(Math.sin(i)*200)+400,(int)Math.round(Math.cos(i)*200)+300);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f((int)Math.round(Math.sin(i)*200)+400+megaman.getTextureWidth(),(int)Math.round(Math.cos(i)*200)+300);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f((int)Math.round(Math.sin(i)*200)+400+megaman.getTextureWidth(),(int)Math.round(Math.cos(i)*200)+300+megaman.getTextureHeight());
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f((int)Math.round(Math.sin(i)*200)+400,(int)Math.round(Math.cos(i)*200)+300+megaman.getTextureHeight());
		GL11.glEnd();
		
	}
	
	public void updateFPS() {
	    if (getTime() - lastFPS > 1000) {
	        Display.setTitle(title+"    FPS: " + fps); 
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
