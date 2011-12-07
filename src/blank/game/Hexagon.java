package blank.game;

import java.util.ArrayList;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import blank.game.physics.CollisionData;
import blank.game.physics.PhysicsObject;
import blank.game.physics.PhysicsOwner;
import blank.game.physics.PhysicsPolygon;
import blank.game.rendering.Drawable;
import blank.game.rendering.Sprite;

public class Hexagon implements GameObject, PhysicsOwner, Drawable {

	private PhysicsObject phys;
	private Sprite sprite;
	public float width, height;
	private int zIndex;
	private boolean visible;
	boolean up, down, left, right, m_pressed, m_released; // nur zum testen...
	int key_up = 200, key_down = 208, key_left = 203, key_right = 205;
	Vec2 positionOnDisplay, click_pos;
	
	public Hexagon(float x, float y, float width, float height, BodyType bodyType) {
		
		this.width = width;
		this.height = height;
	
		///////////////////////im Uhrzeigersinn///////////////////////
		ArrayList<Vec2> points = new ArrayList<Vec2>();
		points.add(new Vec2(1f/3f*width, 0));
		points.add(new Vec2(2f/3f*width, 0));
		points.add(new Vec2(width, 1f/2f*height));
		points.add(new Vec2(2f/3f*width, height));
		points.add(new Vec2(1f/3f*height, height));
		points.add(new Vec2(0, 1f/2f*height));
		
		phys = new PhysicsPolygon(x, y, points, bodyType);
		phys.setOwner(this);
		Game.getCurrentLevel().getPhysicsWorld().addObject(phys);
		
		sprite = new Sprite("res/hexagon2.png");
	}
	
	
	public void draw() {
		sprite.setTranslate(new Vec2(phys.getPosition().x, phys.getPosition().y));
		sprite.setRotationPoint(new Vec2(0, 0));
		sprite.setRotationAngle(phys.getAngle());
		sprite.setScaleFactor(new Vec2(width / sprite.getWidth(), height / sprite.getHeight()));
		sprite.draw();
	}
	
	public void update(int delta) {

	}
	
	
	public void beginCollision(CollisionData collision) {
	
	}
	
	public void endCollision(CollisionData collision) {
	
	}
	
	public PhysicsObject getPhysicsObject() {
		return phys;
	}
	
	public boolean getVisible() {
		return visible;
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
		// TODO Auto-generated method stub
		
	}


}

