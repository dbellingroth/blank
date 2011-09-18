package blank.game.rendering;

import org.lwjgl.opengl.GL11;

/**
 * 
 * @author David Bellingroth
 *
 */
public class TransformationGroup extends Transformable {

	private DrawableList list = new DrawableList();
	private int zIndex = 0;
	
	@Override
	public void draw() {
		GL11.glPushMatrix();
		super.transform();
		list.draw();
		GL11.glPopMatrix();
	}

	@Override
	public int getZIndex() {
		return zIndex;
	}

	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}
	
	public void addChild(Drawable child) {
		list.addDrawable(child);
	}
	
	public void removeChild(Drawable child) {
		list.deleteDrawable(child);
	}

}
