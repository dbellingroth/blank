package blank.game;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import blank.game.physics.CollisionData;
import blank.game.physics.PhysicsBox;
import blank.game.physics.PhysicsObject;
import blank.game.physics.PhysicsOwner;
import blank.game.rendering.Drawable;
import blank.game.rendering.Sprite;

public class Rectangle implements GameObject, PhysicsOwner, Drawable {

	private PhysicsObject phys;
	private Sprite sprite;
	public float width, height;
	private int zIndex;
	private boolean visible;

	public Rectangle(float x, float y, float width, float height,
			BodyType bodyType) {
		this.width = width;
		this.height = height;
		phys = new PhysicsBox(x, y, width, height, bodyType);
		phys.setOwner(this);
		Game.getCurrentLevel().getPhysicsWorld().addObject(phys);

		sprite = new Sprite((int)width, (int)height);
		Graphics2D g2d = sprite.getGraphics2D();
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, (int)width, (int)height);
		sprite.update();
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

}
