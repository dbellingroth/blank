package blank.game;

import java.awt.Color;
import java.awt.Graphics2D;
import org.jbox2d.common.Vec2;
import blank.game.physics.CollisionData;
import blank.game.physics.PhysicsBox;
import blank.game.physics.PhysicsObject;
import blank.game.physics.PhysicsOwner;
import blank.game.rendering.Drawable;
import blank.game.rendering.Sprite;

public class DynamicRectangle implements GameObject, PhysicsOwner, Drawable {

	private PhysicsObject phys;
	private Sprite sprite;
	public float width, height;

	public DynamicRectangle(float x, float y, float width, float height) {
		this.width = width;
		this.height = height;
		phys = new PhysicsBox(x, y, width, height);
		phys.setOwner(this);
		Game.getPhysicsWorld().addObject(phys);

		sprite = new Sprite((int) width, (int) height);
		Graphics2D g2d = sprite.getGraphics2D();
		g2d.setColor(Color.MAGENTA);
		g2d.fillRect(0, 0, (int) width, (int) height);
		sprite.update();
	}

	@Override
	public void draw() {
		sprite.setTranslate(new Vec2(
				phys.getPosition().x - width / 2, phys.getPosition().y - height
						/ 2));
		sprite.setRotationPoint(new Vec2(width / 2, height / 2));
		sprite.setRotationAngle(phys.getAngle());
		sprite.draw();
	}

	@Override
	public void update(int delta) {

	}

	@Override
	public void beginCollision(CollisionData collision) {

	}

	@Override
	public void endCollision(CollisionData collision) {

	}

	@Override
	public int getZIndex() {
		// TODO Auto-generated method stub
		return 0;
	}



}
