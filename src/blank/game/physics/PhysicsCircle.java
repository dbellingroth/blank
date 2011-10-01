package blank.game.physics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class PhysicsCircle extends PhysicsObject {

	private BodyDef def;
	private FixtureDef fdef;

	public PhysicsCircle(float x, float y, float r, BodyType bodyType) {
		def = new BodyDef();
		def.position.set(x / 30, y / 30);
		def.type = bodyType;
		def.fixedRotation = false;
		CircleShape c = new CircleShape();
		c.m_radius = r / PhysicsWorld.pixelsPerMeter;
		fdef = new FixtureDef();
		fdef.shape = c;
		fdef.friction = 1f;
		fdef.density = 0.1f;
	}

	@Override
	public void init(World world) {

		body = world.createBody(def);
		body.setUserData(this);
		body.createFixture(fdef);

	}

}
