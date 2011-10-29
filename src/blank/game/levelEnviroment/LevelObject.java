package blank.game.levelEnviroment;

import org.jbox2d.common.Vec2;

import blank.game.GameObject;
import blank.game.physics.CollisionData;
import blank.game.physics.PhysicsObject;
import blank.game.physics.PhysicsOwner;
import blank.game.rendering.Drawable;
import blank.game.rendering.Sprite;

public abstract class LevelObject implements GameObject, Drawable, PhysicsOwner {

	protected PhysicsObject phys;
	protected Sprite sprite;
	protected float x, y, width, height, angle;
	protected int zIndex;
	protected boolean visible;
	

	public LevelObject(float x, float y, float width, float height, float angle) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.angle = angle;
	}
	
	
	public void draw() {
	
		sprite.setTranslate(new Vec2(x , y));
		sprite.setRotationPoint(new Vec2(width / 2, height / 2));
		sprite.setRotationAngle(angle);
		sprite.setScaleFactor(new Vec2(width / sprite.getWidth(), height / sprite.getHeight()));
		sprite.draw();
		
	}


	public int getZIndex() {
		
		return zIndex;
	}

	
	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	
	public boolean getVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	
	
	
	@Override
	public void endCollision(CollisionData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beginCollision(CollisionData collision) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeCollision(CollisionData data) {
		// TODO Auto-generated method stub
		
	}

	
	public abstract void update(int delta);
	
	
	
}
