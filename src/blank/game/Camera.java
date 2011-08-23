package blank.game;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 * Eine Klasse um die Kamera im Spiel zu kontrollieren
 * @author david
 *
 */
public class Camera {
	
	private double x,y;
	private float rotation = 0f;
	
	public Camera(int x, int y) {
		setX(x);
		setY(y);
	}
	
	
	
	public void apply() {
		
		GL11.glTranslated(-x+Display.getDisplayMode().getWidth()/2, -y+Display.getDisplayMode().getHeight()/2, 0);
		
		if (rotation != 0f) { //falls Rotation gewünscht
			GL11.glTranslated(Display.getDisplayMode().getWidth()/2,Display.getDisplayMode().getHeight()/2,0);
			GL11.glRotatef(rotation, 0f, 0f, 1f);
			GL11.glTranslated(-Display.getDisplayMode().getWidth()/2,-Display.getDisplayMode().getHeight()/2,0);
		}
	}



	public double getX() {
		return x;
	}



	public void setX(double x) {
		this.x = x;
	}



	public double getY() {
		return y;
	}



	public void setY(double y) {
		this.y = y;
	}



	public float getRotation() {
		return rotation;
	}



	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
}
