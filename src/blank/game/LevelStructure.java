package blank.game;



import blank.game.levelEnviroment.LevelObject;
import blank.game.rendering.Drawable;


public class LevelStructure implements GameObject, Drawable {

	protected String name;
	protected String author;
	protected AllObjectsList allObjects;
	
	private boolean visible;
	private int zIndex;
	
	
	public LevelStructure() {
		
		allObjects = new AllObjectsList();
		
	}
	

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}
	

	public void addLevelObject(LevelObject levelObject) {
		allObjects.add((GameObject) levelObject);
	}

	
	public void update(int delta) {
		allObjects.update(delta);
	}
	
	public void draw() {
		allObjects.draw();
	}


	public int getZIndex() {
		return zIndex;
	}


	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}


	public boolean getVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
	public AllObjectsList getList() {
		return allObjects;
	}
	
	
}
