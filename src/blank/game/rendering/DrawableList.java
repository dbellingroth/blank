package blank.game.rendering;

import java.util.LinkedList;

/**
 * Eine Liste von Drawables, die automatisch nach dem z-Index sortiert und sich
 * selbst auch wieder zeichnen lässt. Gut für hierarschische Transformationen
 * (Bäume, Pflanzen, Kram)
 * 
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
	 * 
	 * @param drawable
	 *            fuegt ein Drawable der Liste hinzu
	 */
	public void addDrawable(Drawable drawable) {

		// Falls Liste noch leer
		if (drawables.size() == 0)
			drawables.add(drawable);
		// Sortiert einfügen
		else
			for (int x = 0; x < drawables.size(); x++) {

				if (drawable.getZIndex() < drawables.get(x).getZIndex()) {

					drawables.add(x, drawable);
					break;
					// Falls die for-Schleife am Ende ankommt und drawable noch
					// keinen Platz gefunden hat
					// wird es ans Ende gesetzt
				} else if (x == drawables.size() - 1
						&& drawable.getZIndex() > drawables.get(x).getZIndex()) {

					drawables.add(drawable);

				}

			}

	}

	/**
	 * loescht das im Parameter angegebene Objekt aus der Liste
	 * 
	 * @param drawable
	 *            loescht ein Drawable aus der Liste
	 */
	public void deleteDrawable(Drawable drawable) {

		drawables.remove(drawable);

	}

	/**
	 * loescht den im Parameter angegebenen Index
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

		for (Drawable drawable : drawables) {

			drawable.draw();

		}

	}

	public int getZIndex() {
		return zIndex;
	}

	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}

}
