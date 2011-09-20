package blank.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import org.jbox2d.common.Vec2;

import blank.game.physics.CollisionData;
import blank.game.physics.PhysicsCircle;
import blank.game.physics.PhysicsObject;
import blank.game.physics.PhysicsOwner;
import blank.game.rendering.Drawable;
import blank.game.rendering.Sprite;

public class Ball implements GameObject, PhysicsOwner, Drawable {

	private PhysicsObject phys;
	private Sprite sprite;
	private int r;

	public Ball(float x, float y, int r) {
		this.r = r;
		phys = new PhysicsCircle(x, y, r);
		phys.setOwner(this);
		Game.getPhysicsWorld().addObject(phys);

		sprite = new Sprite(r * 2, r * 2);
		Graphics2D g2d = sprite.getGraphics2D();
		g2d.setColor(new Color((float) Math.random(), (float) Math.random(),
				(float) Math.random()));
		g2d.fillOval(0, 0, r * 2, r * 2);
		g2d.setColor(new Color((float) Math.random(), (float) Math.random(),
				(float) Math.random()));
		g2d.drawLine(r, 0, r, r * 2);
		g2d.drawLine(0, r, r * 2, r);
		sprite.update();
	}

	@Override
	public void draw() {
		sprite.setTranslate(new Point2D.Double(phys.getPosition().x - r, phys
				.getPosition().y - r));
		sprite.setRotationPoint(new Point2D.Double(r, r));
		sprite.setRotationAngle(phys.getAngle());
		sprite.draw();
	}

	@Override
	public void update(int delta) {

	}

	@Override
	public void beginCollision(CollisionData collision) {
		/*
		 * Graphics2D g2d = sprite.getGraphics2D(); g2d.setColor(new
		 * Color((float)Math.random(), (float)Math.random(),
		 * (float)Math.random())); g2d.fillOval(0, 0, r*2, r*2);
		 * g2d.setColor(new Color((float)Math.random(), (float)Math.random(),
		 * (float)Math.random())); g2d.drawLine(r, 0, r, r*2); g2d.drawLine(0,
		 * r, r*2, r); sprite.update();
		 */
	}

	@Override
	public void endCollision(CollisionData collision) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getZIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	public PhysicsObject getPhysicsObject() {
		return phys;
	}

	@Override
	public void applyAngularImpulse(float impulse) {
		// TODO Auto-generated method stub

	}

	@Override
	public void applyForce(Vec2 force, Vec2 point) {
		// TODO Auto-generated method stub

	}

	@Override
	public void applyLinearImpulse(Vec2 impulse, Vec2 point) {
		// TODO Auto-generated method stub

	}

}
