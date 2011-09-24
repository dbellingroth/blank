package blank.game;

import java.awt.Color;
import java.awt.Graphics2D;
import org.jbox2d.common.Vec2;
import blank.game.physics.CollisionData;
import blank.game.physics.PhysicsCircle;
import blank.game.physics.PhysicsObject;
import blank.game.physics.PhysicsOwner;
import blank.game.rendering.Drawable;
import blank.game.rendering.Sprite;

public class Ball implements GameObject, PhysicsOwner, Drawable {
	
	private PhysicsObject phys;
	private Sprite	sprite;
	private int r;
	private int zIndex;
	private boolean visible;
	private long time;
	
	public Ball(float x, float y, int r) {
		this.r = r;
		phys = new PhysicsCircle(x, y, r);
		phys.setOwner(this);
		Game.getPhysicsWorld().addObject(phys);
		
		sprite = new Sprite(r*2, r*2);
		Graphics2D g2d = sprite.getGraphics2D();
		g2d.setColor(new Color((float)Math.random(), (float)Math.random(), (float)Math.random()));
		g2d.fillOval(0, 0, r*2, r*2);
		g2d.setColor(new Color((float)Math.random(), (float)Math.random(), (float)Math.random()));
		g2d.drawLine(r, 0, r, r*2);
		g2d.drawLine(0, r, r*2, r);
		sprite.update();
		time = System.currentTimeMillis();
	}
	
	@Override
	public void draw() {
		sprite.setTranslate(new Vec2(phys.getPosition().x-r,phys.getPosition().y-r));
		sprite.setRotationPoint(new Vec2(r,r));
		sprite.setRotationAngle(phys.getAngle());
		sprite.draw();
	}
	
	@Override
	public void update(int delta) {
		
	}

	@Override
	public void beginCollision(CollisionData collision) {
		
		if (collision.getImpulse() > 3/*collision.getSecondObject() instanceof PhysicsBox*/) {
			Graphics2D g2d = sprite.getGraphics2D();
			g2d.setColor(Color.WHITE);
			g2d.fillOval(0, 0, r*2, r*2);
			sprite.update();
		}
	}

	@Override
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
		//if (data.getSecondObject().getOwner() instanceof Ball && (System.currentTimeMillis() - time) > 10000) data.disable();
	}

}
