package blank.game;

import java.awt.geom.Point2D;

import org.lwjgl.opengl.GL11;

public abstract class Transformable implements Drawable{

	private Point2D translate = new Point2D.Double(0,0);
	private Point2D rotationPoint = new Point2D.Double(0,0);
	private double rotationAngle = 0;
	private Point2D scaleFactor = new Point2D.Double(1,1);
	
	
	protected void transform() {
		GL11.glTranslated(translate.getX(),translate.getY(),0);
		GL11.glScaled(scaleFactor.getX(), scaleFactor.getY(), 1);
		if (rotationAngle != 0) { //falls Rotation gew�nscht
			GL11.glTranslated(rotationPoint.getX(),rotationPoint.getY(),0);
			GL11.glRotatef((float)rotationAngle, 0f, 0f, 1);
			GL11.glTranslated(-rotationPoint.getX(),-rotationPoint.getY(),0);
		}
	}
	
}
