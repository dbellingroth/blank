package blank.game.levelEnviroment;

import org.jbox2d.dynamics.BodyType;
import blank.game.Game;
import blank.game.physics.PhysicsBox;
import blank.game.rendering.Sprite;

public class House1 extends LevelObject {


	public House1(float x, float y, float width, float height, float angle) {
		
		super(x, y, width, height, angle);
		
		phys = new PhysicsBox(x+width/9f, y+height/9f, width-width/4.5f, height-height/4.5f, BodyType.STATIC);
		phys.setOwner(this);
		Game.getCurrentLevel().getPhysicsWorld().addObject(phys);
		phys.setAngle(angle);
		sprite = new Sprite("res/house1.png");
		zIndex = 2;
	}
	
	
	
	public void update(int delta) {
	
		angle = (float) phys.getAngle();

	}

}
