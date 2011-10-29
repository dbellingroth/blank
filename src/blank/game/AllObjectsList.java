package blank.game;

import java.util.ArrayList;

import blank.game.rendering.Drawable;
import blank.game.rendering.DrawableList;

public class AllObjectsList {

	ArrayList<GameObject> gameObjects;
	DrawableList drawers;

	AllObjectsList() {

		gameObjects = new ArrayList<GameObject>();
		drawers = new DrawableList();

	}

	public void add(GameObject go) {

		if (!gameObjects.contains(go)) {
			gameObjects.add(go);
		}

		if (go instanceof Drawable) {
			if (!drawers.contains((Drawable) go)) {
				drawers.addDrawable((Drawable) go);
			}
		}
	}

	public void removeGameObject(GameObject go) {

		if (gameObjects.contains(go)) {
			gameObjects.remove(go);
		}

		if (go instanceof Drawable) {
			if (drawers.contains((Drawable) go)) {
				drawers.removeDrawable((Drawable) go);
			}
		}

	}
	
	
	public int size() {
		return gameObjects.size();
	}
	

	public void update(int delta) {

		for (GameObject go : gameObjects) {
			go.update(delta);
		}

	}

	public void draw() {

		drawers.draw();

	}

}
