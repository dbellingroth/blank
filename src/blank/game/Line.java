package blank.game;

import java.awt.Color;
import java.awt.Graphics2D;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import blank.game.physics.CollisionData;
import blank.game.physics.PhysicsLine;
import blank.game.physics.PhysicsObject;
import blank.game.physics.PhysicsOwner;
import blank.game.rendering.Drawable;
import blank.game.rendering.Sprite;

public class Line implements GameObject, PhysicsOwner, Drawable {

	private PhysicsObject phys;
	private Sprite sprite;
	public float x, y, width, height;
	private int zIndex;
	private boolean visible;
	boolean up, down, left, right; // nur zum testen...
	int key_up = 200, key_down = 208, key_left = 203, key_right = 205;

	public Line(Vec2 p1, Vec2 p2, BodyType bodyType) {

		phys = new PhysicsLine(p1, p2, bodyType);
		phys.setOwner(this);
		Game.getPhysicsWorld().addObject(phys);

		x = p1.x <= p2.x ? p1.x : p2.x;
		y = p1.y <= p2.y ? p1.y : p2.y;
	
		width = (float) Math.abs((int) (p1.x - p2.x));
		height = (float) Math.abs((int) (p1.y - p2.y));

		Vec2 localPosP1 = new Vec2(p1.x <= p2.x ? 0 : width,
				p1.y <= p2.y ? 0 : height);	
		Vec2 localPosP2 = new Vec2(p2.x < p1.x ? 0 : width,
				p2.y < p1.y ? 0 : height);	

		sprite = new Sprite((int) width+1, (int) height+1);
		Graphics2D g2d = sprite.getGraphics2D();
		g2d.setColor(Color.MAGENTA);
		g2d.drawLine((int) localPosP1.x, (int) localPosP1.y, 
				(int) localPosP2.x, (int) localPosP2.y);

		sprite.update();
	}

	public void draw() {

		sprite.setTranslate(new Vec2(phys.getPosition().x+width, 
				phys.getPosition().y+height));
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
