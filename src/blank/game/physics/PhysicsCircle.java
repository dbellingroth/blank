package blank.game.physics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class PhysicsCircle extends PhysicsObject {

	
	@Override
	public void init(World world) {
		
		BodyDef def = new BodyDef();
		def.position.set((float)Math.random()*10+10, (float)Math.random()*10+10);
		def.type = BodyType.DYNAMIC;
		body = world.createBody(def);

		CircleShape c = new CircleShape();
		c.m_radius = 50;
		
		FixtureDef fd = new FixtureDef();
		fd.shape = c;
		fd.friction = 1f;
		fd.density = 0.5f;
		body.createFixture(fd);
		body.m_mass = 1000;
		
	}
	
}
