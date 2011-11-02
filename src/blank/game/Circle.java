package blank.game;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import blank.game.physics.CollisionData;
import blank.game.physics.PhysicsCircle;
import blank.game.physics.PhysicsObject;
import blank.game.physics.PhysicsOwner;
import blank.game.rendering.Drawable;
import blank.game.rendering.Sprite;

public class Circle implements GameObject, PhysicsOwner, Drawable {

	private PhysicsObject phys;
	private Sprite sprite;
	private int r;
	private int zIndex;
	private boolean visible;


	public Circle(float x, float y, int r, BodyType bodyType) {
		this.r = r;
		phys = new PhysicsCircle(x, y, r, bodyType);
		phys.setOwner(this);
		Game.getCurrentLevel().getPhysicsWorld().addObject(phys);

		sprite = new Sprite("res/smiley.png");
		
	}


	public void draw() {
		sprite.setTranslate(new Vec2(phys.getPosition().x - r, phys.getPosition().y - r));
		sprite.setRotationPoint(new Vec2(r, r));
		sprite.setRotationAngle(phys.getAngle());
		sprite.setScaleFactor(new Vec2(2*r / ((float) sprite.getWidth()), 2*r / ((float) sprite.getHeight())));		
		sprite.draw();
	}


	public void update(int delta) {
		phys.applyTorque(-10f);
	}


	public void beginCollision(CollisionData collision) {

		// if (collision.getImpulse() > 10/*collision.getSecondObject()
		// instanceof PhysicsBox*/) {
		// Graphics2D g2d = sprite.getGraphics2D();
		// g2d.setColor(Color.WHITE);
		// g2d.fillOval(0, 0, r*2, r*2);
		// sprite.update();
		// }
	}


	public void endCollision(CollisionData collision) {

	}

	public PhysicsObject getPhysicsObject() {
		return phys;
	}

	@Override
	public boolean getVisible() {
		return visible;
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public int getZIndex() {
		return zIndex;
	}

	@Override
	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	@Override
	public void beforeCollision(CollisionData data) {
		// if (data.getSecondObject().getOwner() instanceof Ball &&
		// (System.currentTimeMillis() - time) > 10000) data.disable();
	}

}
