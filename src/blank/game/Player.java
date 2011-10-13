package blank.game;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import blank.game.physics.CollisionData;
import blank.game.physics.PhysicsBox;
import blank.game.physics.PhysicsCircle;
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
	boolean up, down, left, right; // nur zum testen...
	int key_up = 200, key_down = 208, key_left = 203, key_right = 205;

	public Player(float x, float y, float width, float height,
			BodyType bodyType) {
		this.width = width;
		this.height = height;
		phys = new PhysicsCircle(x+width, y+height, width/2, bodyType);
		phys.setOwner(this);
		Game.getCurrentLevel().getPhysicsWorld().addObject(phys);

		// sprite = new Sprite((int) width, (int) height);
		// Graphics2D g2d = sprite.getGraphics2D();
		// g2d.setColor(Color.MAGENTA);
		// g2d.fillRect(0, 0, (int) width, (int) height);
		// sprite.update();

		sprite = new Sprite("res/player_two.png");
	}

	@Override
	public void draw() {

		sprite.setTranslate(new Vec2(phys.getPosition().x - width / 2, phys
				.getPosition().y - height / 2));

		sprite.setRotationPoint(new Vec2(width / 2, height / 2));
		sprite.setRotationAngle(phys.getAngle());
		sprite.setScaleFactor(new Vec2(width / sprite.getWidth(), height
				/ sprite.getHeight()));
		sprite.draw();

	}

	public void update(int delta) {
		// phys.applyForce(new Vec2((Mouse.getX()-phys.getPosition().x)*100,
		// ((600-Mouse.getY())-phys.getPosition().y)*100), new
		// Vec2(width/2,height/2));
		// phys.stop();
		
		if (left) phys.setAngle(phys.getAngle()-1);
		if (right) phys.setAngle(phys.getAngle()+1);
		
		if (phys.getSpeed().x > 50 && phys.getSpeed().y > 50) {
			phys.setSpeed(new Vec2(50, 50));
		} else if (phys.getSpeed().x < -50 && phys.getSpeed().y < -50) {
			phys.setSpeed(new Vec2(-50, -50));
		}
		
		
		if (up) phys.applyForce(new Vec2((float) -(5*Math.sin(Math.toRadians(-phys.getAngle()))), 
												(float) -(5*Math.cos(Math.toRadians(-phys.getAngle())))));
	
		if (!up) phys.setSpeed(new Vec2(0, 0));
		if (!right && !left) phys.setAngularSpeed(0);
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

	public void mousePressed(int button) {

	}

	public void mouseReleased(int button) {

	}

}
