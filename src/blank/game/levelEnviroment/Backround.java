package blank.game.levelEnviroment;

import blank.game.rendering.Sprite;

public class Backround extends LevelObject {

	
	public Backround(float x, float y, float width, float height, float angle) {
		
		super(x, y, width, height, angle);

		sprite = new Sprite("res/default_backround.png");		
	}
	
	
	
	public void update(int delta) {
	
		
	}

	
}
