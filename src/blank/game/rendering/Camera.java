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
	
	private Vec2 upper_limit, lower_limit;
	private boolean enableLimit;
	
	private float rotation = 0f;
	private double zoomfactor = 1f;

	public Camera(Vec2 position) {
		this.position = position;
		lower_limit = new Vec2(100, 100);
		upper_limit = new Vec2(700, 600);
		enableLimit = true;
		setX(position.x);
		setY(position.y);
	}

	public void apply() {

		if (rotation != 0f) { // falls Rotation gewünscht
			GL11.glTranslated(Display.getDisplayMode().getWidth() / 2, Display
					.getDisplayMode().getHeight() / 2, 0);
			GL11.glRotatef(-rotation, 0f, 0f, 1f);
			GL11.glTranslated(-Display.getDisplayMode().getWidth() / 2,
					-Display.getDisplayMode().getHeight() / 2, 0);
		}

		if (zoomfactor != 1) { // falls Rotation gewünscht
			GL11.glTranslated(Display.getDisplayMode().getWidth() / 2, Display
					.getDisplayMode().getHeight() / 2, 0);
			GL11.glScaled(zoomfactor, zoomfactor, 1);
			GL11.glTranslated(-Display.getDisplayMode().getWidth() / 2,
					-Display.getDisplayMode().getHeight() / 2, 0);
		}

		GL11.glTranslated(-position.x + Display.getDisplayMode().getWidth() / 2, 
				-position.y	+ Display.getDisplayMode().getHeight() / 2, 0);

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
		if (enableLimit) {
			if (position.x <= upper_limit.x && position.x >= lower_limit.x) {
				setX(position.x);
			}
			if (position.y <= upper_limit.y && position.y >= lower_limit.y) {
				setY(position.y);
			}
		} else if (!enableLimit) {
			setX(position.x);
			setY(position.y);
		}
	}
	
	public void lowerLimit(Vec2 lower_limit) {
		this.lower_limit.x = lower_limit.x;
		this.lower_limit.y = lower_limit.y;
	}
	
	public void upperLimit(Vec2 upper_limit) {
		this.upper_limit.x = upper_limit.x;
		this.upper_limit.y = upper_limit.y;
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
