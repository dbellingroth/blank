package blank.game.rendering;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 * Eine Klasse um die Kamera im Spiel zu kontrollieren
 * 
 * @author david
 * 
 */
public class Camera {

	private double x, y;
	private float rotation = 0f;
	private double zoomfactor = 1;

	public Camera(int x, int y) {
		setX(x);
		setY(y);
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

		GL11.glTranslated(-x + Display.getDisplayMode().getWidth() / 2, -y
				+ Display.getDisplayMode().getHeight() / 2, 0);

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

	public void setZoom(double zoomfactor) {
		this.zoomfactor = zoomfactor;
	}

	public double getZoom() {
		return this.zoomfactor;
	}

}
