package blank.game.rendering;

import org.jbox2d.common.Vec2;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 * Eine Klasse um die Kamera im Spiel zu kontrollieren
 * 
 * @author david
 * 
 */
public class Camera {

	private Vec2 position;
	private float rotation = 0f;
	private double zoomfactor = 1f;

	public Camera(Vec2 position) {
		setX(position.x);
		setY(position.y);
	}

	public void apply() {

		if (rotation != 0f) { // falls Rotation gew�nscht
			GL11.glTranslated(Display.getDisplayMode().getWidth() / 2, Display
					.getDisplayMode().getHeight() / 2, 0);
			GL11.glRotatef(-rotation, 0f, 0f, 1f);
			GL11.glTranslated(-Display.getDisplayMode().getWidth() / 2,
					-Display.getDisplayMode().getHeight() / 2, 0);
		}

		if (zoomfactor != 1) { // falls Rotation gew�nscht
			GL11.glTranslated(Display.getDisplayMode().getWidth() / 2, Display
					.getDisplayMode().getHeight() / 2, 0);
			GL11.glScaled(zoomfactor, zoomfactor, 1);
			GL11.glTranslated(-Display.getDisplayMode().getWidth() / 2,
					-Display.getDisplayMode().getHeight() / 2, 0);
		}

		GL11.glTranslated(-position.x + Display.getDisplayMode().getWidth() / 2, -position.y
				+ Display.getDisplayMode().getHeight() / 2, 0);

	}

	public double getX() {
		return position.x;
	}

	public void setX(float x) {
		this.position.x = x;
	}

	public double getY() {
		return position.y;
	}

	public void setY(float y) {
		this.position.y = y;
	}
	
	
	public void setPosition(Vec2 position) {
		setX(position.x);
		setY(position.y);
	}
	

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public void setZoom(double zoomfactor) {
		this.zoomfactor = zoomfactor;
	}

	public double getZoom() {
		return this.zoomfactor;
	}

}
