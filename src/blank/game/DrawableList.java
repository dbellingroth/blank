package blank.game;

import java.util.LinkedList;

/**
 * Eine Liste von Drawables, die automatisch nach dem z-Index sortiert und sich selbst auch wieder zeichnen lässt.
 * Gut für hierarschische Transformationen (Bäume, Pflanzen, Kram)
 * @author Kilian Helmenstein
 *
 */
public class DrawableList implements Drawable {

	private LinkedList<Drawable> drawables;
	private int zIndex = 0;

	DrawableList() {

		drawables = new LinkedList<Drawable>();

	}

	/**
	 * setzt das neue Drawable auf den richtigen Index, sortiert nach dem ZIndex
	 * @param drawable fögt ein Drawable der Liste hinzu
	 */
	public void addDrawable(Drawable drawable) {
		
		//Falls Liste noch leer
		if (drawables.size() == 0) drawables.add(drawable);
		//Sortiert einfügen
		else for (int x = 0; x < drawables.size(); x++) {

			if (drawable.getZIndex() < drawables.get(x).getZIndex()) {
				drawables.add(x, drawable);
				break;
			}

		}

	}

	/**
	 * löscht das im Parameter angegebene Objekt aus der Liste
	 * 
	 * @param drawable löscht ein Drawable aus der Liste
	 */
	public void deleteDrawable(Drawable drawable) {

		drawables.remove(drawable);

	}

	/**
	 * löscht den im Parameter angegebenen Index
	 * 
	 * @param index
	 */
	public void removeDrawable(int index) {

		drawables.remove(index);

	}

	/**
	 * ruft auf alle Objekete der Liste die draw()-Methode auf
	 */
	public void draw() {

		for (int i = 0; i < drawables.size(); i++) {

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
