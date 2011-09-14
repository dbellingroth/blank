package blank.game;




import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.jbox2d.dynamics.Body;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;


public class Window {
	
	private PhysicsWorld physics;
	private Body testbody;
	
	private double i = 0;
	private long fps,lastFPS,lastFrame;
	private String title;
	private int delta;
	
	private Camera camera;
	
	SpriteLib sLib;
	
	private BufferedImage test = new BufferedImage(200, 100, BufferedImage.TYPE_INT_ARGB);
	private int testdings = 9;
	private Texture texture;
		
	public Window(String title, int width, int height) {
		
		this.title = title;
		
		physics = new PhysicsWorld();
		testbody = physics.addBody();
		physics.addStaticBody();
		
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
		
		//Kameraziel auf die Mitte setzen
		camera = new Camera(width/2,height/2);

		sLib = new SpriteLib();
		sLib.add("kreuz", "res/kreuz.png");
		sLib.add("sonic", "res/sonic.png");
		sLib.add("megaman", "res/megaman.png");
			 
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
			
			
			//Logik
			
			physics.update(delta);
			
			
		    // render OpenGL here
			
			// clear screen
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();
		
			
			//Kameraeinstellungen �bernehmen
			//camera.setRotation(30);
			camera.apply();
			
			//anderen Kram rendern
			render();
			
			//FPS in die Titelzeile schreiben
			updateFPS();
			
		    Display.update();

		    Display.sync(120); // FPS begrenzen


		}
			
		Display.destroy();
	}
	
	private void render() {
		
		//Kreuzhintergrund erzeugen der sich dreht
		int z = 1;
		for (int x = 0; x<20; x++) {
			for (int y = 0; y<15; y++) {
				sLib.get("kreuz").draw(64*x, 64*y, z*(float)i);
				z = -z;
			}
		}
		
		sLib.get("sonic").draw(-100+i*3,100,i/50,i/50);
		sLib.get("sonic").draw(-100+i*2.5,200,1,i/50);
		sLib.get("sonic").draw(-100+i*3.2,300,i/50,1);
		sLib.get("sonic").draw(-100+i*2.3,400,1,i/50);
		sLib.get("sonic").draw(-100+i*3.1,500-i*2,i/10,i/10);
		
		sLib.get("sonic").draw(100, -100+i*2);
		sLib.get("sonic").draw(200, -100+i*2.1);
		sLib.get("sonic").draw(300, -100+i*2.2);
		sLib.get("sonic").draw(400, -100+i*2.3);
		sLib.get("sonic").draw(500, -100+i*2.4);
		sLib.get("sonic").draw(600, -100+i*2.5);
		
		sLib.get("sonic").draw(testbody.getPosition().x,testbody.getPosition().y,(float)(testbody.getAngle()));
		
		sLib.get("megaman").draw(-100+2.6*i, 100+150*Math.sin(i/360*2*Math.PI));
		sLib.get("megaman").draw(-500+5*i, 400+150*Math.tan(i/360*2*Math.PI),(float)i*2);
		
		camera.setZoom(5-Math.sin(i/360*Math.PI)*4);
		camera.setRotation((float)i);
		

		i = (i+0.1*delta)%360;
		
		
		
		try {
			if ((testdings = (testdings + 1)%10) == 0) {
			//Canvas Test
			
			
			Graphics2D g = (Graphics2D)test.createGraphics();
			g.setColor(new java.awt.Color(0,255,0));
			g.fillOval(0, 0, 200, 100);
			g.setColor(new java.awt.Color(0,0,255));
			g.drawString("Time: "+System.currentTimeMillis(), 25, 56);
			
			texture = BufferedImageUtil.getTexture("", test);
			}
			
			GL11.glPushMatrix();	
			GL11.glTranslated(300,250,0);
			texture.bind();
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(0,0);
			GL11.glTexCoord2f(texture.getWidth(),0);
			GL11.glVertex2f(texture.getImageWidth(),0);
			GL11.glTexCoord2f(texture.getWidth(),texture.getHeight());
			GL11.glVertex2f(texture.getImageWidth(),texture.getImageHeight());
			GL11.glTexCoord2f(0,texture.getHeight());
			GL11.glVertex2f(0,texture.getImageHeight());
			GL11.glEnd();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GL11.glPopMatrix();
		
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
	
	/**
	 * 
	 * @return gibt das aktuelle Delta zur�ck
	 */
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;	
	    return delta;
	}
	
}
