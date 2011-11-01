package blank.game.levelEnviroment;

import blank.game.rendering.Sprite;

public class Street extends LevelObject {

	
	
	
	public Street(float x, float y, float width, float height, float angle) {
		
		super(x, y, width, height, angle);

		sprite = new Sprite("res/street.png");		
	}
	
	
	
	public void update(int delta) {
	
		
	}

}
