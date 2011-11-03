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

public class Player implements GameObject, PhysicsOwner, Drawable,
		InputListener {

	private PhysicsObject phys;
	private Sprite sprite;
	public float width, height;
	private int zIndex;
	private boolean visible;
	boolean up, down, left, right, m_pressed, m_released; // nur zum testen...
	int key_up = 200, key_down = 208, key_left = 203, key_right = 205;
	Vec2 positionOnDisplay, click_pos;
	
	public Player(float x, float y, float width, float height,
			BodyType bodyType) {
		this.width = width;
		this.height = height;
		phys = new PhysicsBox(x, y, width, height, bodyType);
		phys.setOwner(this);
		Game.getCurrentLevel().getPhysicsWorld().addObject(phys);

		sprite = new Sprite("res/player_sil.png");
	}


	public void draw() {
		sprite.setTranslate(new Vec2(phys.getPosition().x - width / 2, phys.getPosition().y - height / 2));
		sprite.setRotationPoint(new Vec2(width / 2, height / 2));
		sprite.setRotationAngle(phys.getAngle());
		sprite.setScaleFactor(new Vec2(width / sprite.getWidth(), height / sprite.getHeight()));
		sprite.draw();

	}

	public void update(int delta) {
		// phys.applyForce(new Vec2((Mouse.getX()-phys.getPosition().x)*100,
		// ((600-Mouse.getY())-phys.getPosition().y)*100), new
		// Vec2(width/2,height/2));
		// phys.stop();

		
		if (up)
			phys.applyForce(new Vec2(0, -4000f));
		up = false;
		// if (down) phys.applyForce(new Vec2(0, 40f), new
		// Vec2(width/2,height/2));
		if (left && phys.getAngularSpeed() > -6)
			phys.applyTorque(-50);
		if (right && phys.getAngularSpeed() < 6)
			phys.applyTorque(50);
		if (down) phys.applyForce(new Vec2(0, 500f));
		
		if (m_pressed) {
			
			click_pos = new Vec2(Mouse.getX(), 600-Mouse.getY());
			m_pressed = false;
			
		} else if (m_released) {
			
			if (click_pos.x > positionOnDisplay.x-width && click_pos.x < positionOnDisplay.x+width 
					&& click_pos.y > positionOnDisplay.y-height && click_pos.y < positionOnDisplay.y+height) {
			
			phys.applyForce(new Vec2(40 *(Mouse.getX()-positionOnDisplay.x), 40 * (600-Mouse.getY()-positionOnDisplay.y)));
			phys.applyForce(new Vec2(0.1f*(Mouse.getX()-positionOnDisplay.x), 0.1f*(600-Mouse.getY()-positionOnDisplay.y)), click_pos);
			m_released = false;
			}
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

	}

	public void keyPressed(int key) {
		// if (key == 1) left = true;
		// if (key == 2) right = true;
		//
		// if (key == 3) up = true;
		// if (key == 4) down = true;
		if (key == key_up)
			up = true;
		if (key == key_down)
			down = true;
		if (key == key_left)
			left = true;
		if (key == key_right)
			right = true;
	}

	public void keyReleased(int key) {

		// if (key == 1) left = false;
		// if (key == 2) right = false;
		//
		// if (key == 3) up = false;
		// if (key == 4) down = false;

		if (key == key_up)
			up = false;
		if (key == key_down)
			down = false;
		if (key == key_left)
			left = false;
		if (key == key_right)
			right = false;
	}

	public PhysicsObject getPhys() {
		return phys;		
	}
	
	
	
	public void mousePressed(int button) {
		m_pressed = true;
	}

	public void mouseReleased(int button) {
		m_released = true;
	}
	
	public void mouseWheel(int direction) {
		
	}

}
