package blank.game;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.lwjgl.input.Mouse;

import blank.game.physics.CollisionData;
import blank.game.physics.PhysicsBox;
import blank.game.physics.PhysicsObject;
import blank.game.physics.PhysicsOwner;
import blank.game.rendering.Drawable;
import blank.game.rendering.Sprite;

public class WheelChairPlayer  implements GameObject, PhysicsOwner, Drawable, InputListener {
	
	private PhysicsObject phys;
	private Sprite sprite;
	public float width, height;
	private int zIndex;
	private boolean visible;
	boolean up, down, left, right;
	
	
	public WheelChairPlayer(float x, float y, float width, float height,
		BodyType bodyType) {
		this.width = width;
		this.height = height;
		phys = new PhysicsBox(x, y, width, height, bodyType);
		phys.setOwner(this);
		Game.getCurrentLevel().getPhysicsWorld().addObject(phys);

		sprite = new Sprite("res/old_wheelchair.png");
	}
	
	
	public void draw() {
		
		sprite.setTranslate(new Vec2(phys.getPosition().x - width / 2, 
												phys.getPosition().y - height / 2));
		sprite.setRotationPoint(new Vec2(width / 2, height / 2));
		sprite.setRotationAngle(phys.getAngle());
		sprite.setScaleFactor(new Vec2(width / sprite.getWidth(), height
				/ sprite.getHeight()));
		sprite.draw();
		
	}
	
	public void update(int delta) {
		Vec2 direction = new Vec2((Mouse.getX() - (phys.getPosition().x-width/2)), 
												Mouse.getY() - (phys.getPosition().y-height/2));
		
		
		Vec2 ref = new Vec2(0, -1);
		float scalar_product = (direction.x * ref.x) + (direction.y * ref.y);
		double values_product = Math.sqrt(Math.pow(direction.x, 2) + Math.pow(direction.y, 2))
										* Math.sqrt(Math.pow(ref.x, 2) + Math.pow(ref.y, 2));
		double alpha = Math.toDegrees(Math.acos(scalar_product / values_product));
		phys.setAngle(Mouse.getX() < phys.getPosition().x-width/2 ? alpha+180 : 180-alpha);
	
		
		if (up) {
			phys.applyForce(direction, new Vec2((phys.getPosition().x-width/2), (phys.getPosition().y-height/2)));
			
			up = false;
		}
		
		
		if (down) {
			phys.applyForce(new Vec2(-direction.x, -direction.y), 
						new Vec2((phys.getPosition().x-width/2), (phys.getPosition().y-height/2)));
			
			down = false;
		}	
		

		
	}
	
	public void beginCollision(CollisionData collision) {
	
	}
	
	public void endCollision(CollisionData collision) {
	
	}
	
	public PhysicsObject getPhysicsObject() {
	return phys;
	}
	
	public boolean getVisible() {
	return	visible;
	}
	
	public void setVisible(boolean visible) {
	this.visible = visible;
	}
	
	public int getZIndex() {
	return zIndex;
	}
	
	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}
	
	public void beforeCollision(CollisionData data) {
	
	}
	
	
	public void keyPressed(int key) {
		
		
	}
	
	
	public void keyReleased(int key) {
		
		
	}
	
	
	public void mousePressed(int button) {
		
		
	}
	
	
	public void mouseReleased(int button) {
	
		
	}
	
	public void mouseWheel(int direction) {
		if (direction > 0) up = true;
		else if (direction < 0) down = true;
	}
	
	
	public PhysicsObject getPhys() {
		return phys;		
	}

}
