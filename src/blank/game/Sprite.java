package blank.game;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
/**
 * Eine Klasse um Bilder zu laden und zu zeichnen
 * @author David Bellingroth
 * 
 */
public class Sprite {
	
	protected Texture texture;
	
	/**
	 * 
	 * @param pfad Pfad eines PNG Bildes, das geladen werden soll
	 */
	public Sprite(String pfad) {
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(pfad));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param x x-Position
	 * @param y y-Position
	 * @param rot Rotationswinkel in Grad
	 * @param rotx x Koordinate Rotationspunktes Relativ zur oberen Linken Ecke des Sprites
	 * @param roty y Koordinate Rotationspunktes Relativ zur oberen Linken Ecke des Sprites
	 */
	public void draw(double x, double y, float rot, double rotx, double roty, double scalex, double scaley) {
		
		Color.white.bind();
		
		texture.bind(); //Textur binden
				
		GL11.glPushMatrix();
		
		GL11.glTranslated(x,y,0);
		GL11.glScaled(scalex, scaley, 1);
		
		if (rot != 0f) { //falls Rotation gewünscht
			GL11.glTranslated(rotx,roty,0);
			GL11.glRotatef(rot, 0f, 0f, 1f);
			GL11.glTranslated(-rotx,-roty,0);
		}
		
		
		
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2d(0,0);
			GL11.glTexCoord2d(1,0);
			GL11.glVertex2d(texture.getTextureWidth(),0);
			GL11.glTexCoord2d(1,1);
			GL11.glVertex2d(texture.getTextureWidth(),texture.getTextureHeight());
			GL11.glTexCoord2d(0,1);
			GL11.glVertex2d(0,texture.getTextureHeight());
		GL11.glEnd();
		
		GL11.glPopMatrix();
		
	}
	
	/**
	 * 
	 * @param x x-Position
	 * @param y y-Position
	 * @param rot Rotationwinkel in Grad
	 */
	
	public void draw(double x, double y, float rot) {
		draw(x,y,rot,getWidth()/2,getHeight()/2,1,1);
	}
	
	/**
	 * 
	 * @param x x-Position
	 * @param y y-Position
	 * @param scalex Skalierungsfaktor X
	 * @param scalex Skalierungsfaktor Y
	 */
	
	public void draw(double x, double y, double scalex, double scaley) {
		draw(x,y,0,0,0,scalex,scaley);
	}
	
	/**
	 * 
	 * @param x x-Position
	 * @param y y-Position
	 **/
	public void draw(double x, double y) {
		draw(x,y,0f);
	}
	
	public int getWidth() {
		return texture.getImageWidth();
	}
	
	public int getHeight() {
		return texture.getImageHeight();
	}
	
}
