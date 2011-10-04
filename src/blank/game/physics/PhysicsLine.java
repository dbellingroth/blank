package blank.game.physics;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import blank.game.Tools;

public class PhysicsLine extends PhysicsObject {
	
	private BodyDef def;
	private FixtureDef fdef;

	public PhysicsLine(Vec2 p1, Vec2 p2, BodyType bodyType) {

		def = new BodyDef();
		def.type = bodyType;
		
		
		PolygonShape shape = new PolygonShape();
		shape.setAsEdge(Tools.convertVectorPix2Phys(p1), 
									Tools.convertVectorPix2Phys(p2));
		
		fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.friction = 0.3f;
		fdef.density = 1f;
	}

	public void init(World world) {

		body = world.createBody(def);
		body.setUserData(this);
		body.createFixture(fdef);
	}

}
