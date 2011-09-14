package blank.trash;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.newdawn.slick.Color;

import blank.game.Tools;


public class TextureExample2 {
	
	/** The texture that will hold the image details */
	private IntBuffer textureIDBuffer;
	
	/**
	 * Start the example
	 */
	public void start() {
		initGL(800,600);
		init();
		
		while (true) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			render();
			
			Display.update();
			Display.sync(100);

			if (Display.isCloseRequested()) {
				Display.destroy();
				System.exit(0);
			}
		}
	}
	
	/**
	 * Initialise the GL display
	 * 
	 * @param width The width of the display
	 * @param height The height of the display
	 */
	private void initGL(int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

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
	}
	
	/**
	 * Initialise resources
	 */
	public void init() {
		
		textureIDBuffer = BufferUtils.createIntBuffer(1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureIDBuffer.get(0));
		
		
		BufferedImage image = new BufferedImage(200, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D)image.createGraphics();
		g.setColor(new java.awt.Color(255,0,0));
		g.fillRect(0, 0, 200, 100);
		g.setColor(new java.awt.Color(0,255,0));
		g.fillOval(0, 0, 200, 100);
		g.setColor(new java.awt.Color(0,0,255));
		g.drawString("Time: "+System.currentTimeMillis(), 10, 55);
		
	
		ByteBuffer buf = Tools.convertImageData(image);
		
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR); 
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR); 
		
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, 200, 100, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);
	}

	/**
	 * draw a quad with the image on it
	 */
	public void render() {
		Color.white.bind();
		//texture.bind(); // or GL11.glBind(texture.getTextureID());
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureIDBuffer.get(0));
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(100,100);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(100+200,100);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(100+200,100+100);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(100,100+100);
		GL11.glEnd();
	}
	
	/**
	 * Main Class
	 */
	public static void main(String[] argv) {
		TextureExample2 textureExample = new TextureExample2();
		textureExample.start();
	}
}