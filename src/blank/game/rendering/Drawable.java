package blank.game.rendering;

public interface Drawable {

	public void draw();

	public int getZIndex();
	public void setZIndex(int zIndex);
	public boolean getVisible();
	public void setVisible(boolean visible);

}
