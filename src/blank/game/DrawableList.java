package blank.game;

import java.util.LinkedList;

public class DrawableList implements Drawable {

	private LinkedList<Drawable> drawables;
	private int zIndex;
	
	
	
	
	DrawableList() {
		
		drawables = new LinkedList<Drawable>();
		
	}
	
	
	
	
	/**
	 * setzt das neue Drwbl auf den richtigen Index, sortiert nach dem ZIndex
	 */
	public void addDrawable(Drawable drawable) {
		
		for (int x = 0; x < drawables.size(); x++) {
			
			
			if (drawable.getZIndex() < drawables.get(x).getZIndex()) {
				drawables.add(x, drawable);
				break;
			}
			
		}
		
	}
	
	
	
	
	/**
	 * löscht das im Parameter angegebene Objekt aus der Liste
	 * @param drawable
	 */
	public void deleteDrawable(Drawable drawable) {
		
		drawables.remove(drawable);
		
	}
	
	
	
	
	/**
	 * löscht den im Parameter angegebenen Index
	 * @param index
	 */
	public void deleteDrawable(int index) {
		
		drawables.remove(index);
		
	}
	

	
	/**
	 * ruft auf alle Objekete der Liste die draw()-Methode auf
	 */
	public void draw() {
		
		for(int i = 0; i < drawables.size(); i++) {
			
			drawables.get(i).draw();
			
		}
		
	}
	
	
	

	
	
	public int getZIndex() {
		return zIndex;
	}
	
	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}
	
}
