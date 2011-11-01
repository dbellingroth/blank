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
	private int zIndex = 1;
	private boolean visible;
	boolean up, down, left, right;

	private Vec2 ref = new Vec2(0, 1);
	private Vec2 display_pos = new Vec2(400, 300);
	Vec2 to_mouse;
	float alpha;
	
	//Rollstuhl-Simulation:
	float rolling_friction = 0.01f;
	float rotating_speed = 1f;
	
	public WheelChairPlayer(float x, float y, float width, float height) {
		this.width = width;
		this.height = height;
		phys = new PhysicsBox(x, y, width, height, BodyType.DYNAMIC);
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

		movement(delta);
			
	}
	
	
	
	private void movement(int delta) {
		to_mouse = new Vec2(Mouse.getX()-display_pos.x, Mouse.getY()-display_pos.y);
		
		float cos_alpha = Tools.getScalarProduct(ref, to_mouse) / (Tools.getValue(ref) * Tools.getValue(to_mouse));
		float alpha = (float) Math.toDegrees(Math.acos(cos_alpha));
		alpha = (Mouse.getX() < display_pos.x ? 360-alpha : alpha);
		
		if (Math.abs(alpha-phys.getAngle()) < 1 ) phys.setAngle(alpha); 
		else phys.setAngularSpeed(
				1f * (float) ((alpha-phys.getAngle())/Math.abs(alpha-phys.getAngle())));
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
