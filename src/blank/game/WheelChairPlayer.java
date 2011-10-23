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
	private Vec2 direction; 
	private Vec2 ref = new Vec2(0, -1);
	float value_direction;
	float scalarproduct_direction;
	double values_product, alpha, speed, value_speed;
	int back_or_for = 1;
	
	public WheelChairPlayer(float x, float y, float width, float height) {
		this.width = width;
		this.height = height;
		phys = new PhysicsBox(x, y, width, height, BodyType.DYNAMIC);
		phys.setOwner(this);
		Game.getCurrentLevel().getPhysicsWorld().addObject(phys);

		sprite = new Sprite("res/old_wheelchair.png");
		
		speed = 200;
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
		
		value_direction = (float) Math.sqrt(Math.pow(Mouse.getX() - 400, 2) + Math.pow(Mouse.getY() - 300, 2));
		//Der Betrag des Richtungsvektors
		if (Mouse.getX() != 0 && Mouse.getY() != 0)direction = new Vec2((Mouse.getX() - 400),  (Mouse.getY() - 300));
		//Der Einheitsvektor der Richtung
		
		if (direction.x != 0 && direction.y != 0) scalarproduct_direction = (direction.x * ref.x) + (direction.y * ref.y);
		//Das Skalar-Produkt der beiden Vektoren Richtung und Referenz
		if (value_direction != 0) values_product = value_direction * (Math.sqrt(Math.pow(ref.x, 2) + Math.pow(ref.y, 2)));
		//Der Produkt der beiden Beträge der zuvor genannten Vektoren
		if (values_product != 0 && scalarproduct_direction != 0) alpha = Math.toDegrees(Math.acos(scalarproduct_direction / values_product));
		//Der Winkel zwischen den beiden Vektoren
		phys.setAngle(Mouse.getX() < 400 ? alpha-180 : 180-alpha);
		
		
		value_speed = back_or_for * Math.sqrt(Math.pow(phys.getSpeed().x, 2) + Math.pow(phys.getSpeed().y, 2));
		//Der Betrag des Geschw.-Vektors
				
		
		if (value_direction != 0 && (direction.x != 0 || direction.y != 0)) phys.setSpeed(new Vec2(
				(float) ((direction.x/value_direction) * value_speed), 
				(float) -((direction.y/value_direction) * value_speed)));
				
		
		if (up && value_direction != 0) {
			phys.applyForce(new Vec2((float) ((direction.x/value_direction) * speed), (float) ((-direction.y/value_direction) * speed)), 
								new Vec2(phys.getPosition().x-width/2, phys.getPosition().y-height/2));

			up = false;
		}
		if (down && value_direction != 0) {
			phys.applyForce(new Vec2((float) ((-direction.x/value_direction) * speed), (float) ((direction.y/value_direction) * speed)), 
								new Vec2(phys.getPosition().x-width/2, phys.getPosition().y-height/2));
			

			down = false;
		}
		
		
		phys.setSpeed(new Vec2(phys.getSpeed().x + (phys.getSpeed().x*-0.01f), 			/////////
									phys.getSpeed().y + (phys.getSpeed().y*-0.01f)));	//Reibung
		if (value_speed < 1) phys.setSpeed(new Vec2(0, 0));
		
		
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
