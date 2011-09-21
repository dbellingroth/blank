package blank.game.rendering;

import org.jbox2d.common.Vec2;
import org.lwjgl.opengl.GL11;

/**
 * Erlaubt das beliebige Verschieben, Drehen und Skalieren seiner
 * Unterklassen...
 * 
 * @author David Bellingroth
 * 
 */
public abstract class Transformable implements Drawable {

	private Vec2 translate = new Vec2(0, 0);
	private Vec2 rotationPoint = new Vec2(0, 0);
	private double rotationAngle = 0;
	private Vec2 scaleFactor = new Vec2(1, 1);

	public Vec2 getTranslate() {
		return translate;
	}

	public void setTranslate(Vec2 translate) {
		this.translate = translate;
	}

	public Vec2 getRotationPoint() {
		return rotationPoint;
	}

	public void setRotationPoint(Vec2 rotationPoint) {
		this.rotationPoint = rotationPoint;
	}

	public double getRotationAngle() {
		return rotationAngle;
	}

	public void setRotationAngle(double rotationAngle) {
		this.rotationAngle = rotationAngle;
	}

	public Vec2 getScaleFactor() {
		return scaleFactor;
	}

	public void setScaleFactor(Vec2 scaleFactor) {
		this.scaleFactor = scaleFactor;
	}

	/**
	 * Transformiert die aktuelle Matrix so wie gewünscht. Vorher bitte
	 * GL11.glPushMatrix() und nachher GL11.glPopMatrix() aufrufen Diese Methode
	 * muss von allen Klassen, die von Transformable erben in ihrer Draw-Methode
	 * aufgerufen werden!
	 */
	protected void transform() {
		GL11.glTranslated(translate.x, translate.y, 0);
		GL11.glTranslated(rotationPoint.x, rotationPoint.y, 0);
		// falls Rotation gewünscht
		if (rotationAngle != 0)
			GL11.glRotatef((float) rotationAngle, 0f, 0f, 1);

		GL11.glScaled(scaleFactor.x, scaleFactor.y, 1);

		GL11.glTranslated(-rotationPoint.x, -rotationPoint.y, 0);

	}

}
