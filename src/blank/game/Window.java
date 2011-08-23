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
	
	private Camera camera;
	
	Sprite megaman;
	Sprite sonic;
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
		
		camera = new Camera(width/2,height/2);

		
		 kreuz = new Sprite("res/kreuz.png");
		 sonic = new Sprite("res/sonic.png");
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
			
			camera.apply();
			
			render();
			
			updateFPS();
			
		    Display.update();

		    Display.sync(200); // cap fps to 200fps


		}
			
		Display.destroy();
	}
	
	private void render() {
		
		int z = 1;
		for (int x = 0; x<20; x++) {
			for (int y = 0; y<15; y++) {
				kreuz.draw(64*x, 64*y, z*(float)i);
				z = -z;
			}
		}
		
		sonic.draw(-100+i*3,100);
		sonic.draw(-100+i*2.5,200);
		sonic.draw(-100+i*3.2,300);
		sonic.draw(-100+i*2.3,400);
		sonic.draw(-100+i*3.1,500);
		sonic.draw(-100+i*2.4,600);
		
		sonic.draw(100, -100+i*2);
		sonic.draw(200, -100+i*2.1);
		sonic.draw(300, -100+i*2.2);
		sonic.draw(400, -100+i*2.3);
		sonic.draw(500, -100+i*2.4);
		sonic.draw(600, -100+i*2.5);
		
		megaman.draw(-100+2.6*i, 100+150*Math.sin(i/360*2*Math.PI));
		megaman.draw(-500+5*i, 400+150*Math.tan(i/360*2*Math.PI),(float)i*2);
		
		i = (i+0.1*delta)%360;
		
		/*
		camera.setX(400+Math.sin((i/360)*Math.PI*2)*100);
		camera.setY(300+Math.cos((i/360)*Math.PI*2)*100);
		*/
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
