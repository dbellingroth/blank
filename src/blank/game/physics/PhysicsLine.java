package blank.game.physics;


import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class PhysicsLine extends PhysicsObject {

	private BodyDef def;
	private FixtureDef fdef;
		
	
	public PhysicsLine(Vec2 p1, Vec2 p2) {
		
		float x = p1.x < p2.x ? p1.x : p2.x;
		float y = p1.y < p2.y ? p1.x : p1.y;
	
		def = new BodyDef();
		def.position = new Vec2(x / PhysicsWorld.pixelsPerMeter, y / PhysicsWorld.pixelsPerMeter);
		def.bullet = true;
		def.fixedRotation = true;
		def.type = BodyType.STATIC;
		
		
		PolygonShape shape = new PolygonShape();
		shape.setAsEdge(new Vec2(p1.x / PhysicsWorld.pixelsPerMeter, p1.y / PhysicsWorld.pixelsPerMeter), new Vec2(p2.x / PhysicsWorld.pixelsPerMeter, p2.y / PhysicsWorld.pixelsPerMeter));		
		
		fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.friction = 0.3f;
		fdef.density = 1f;
		
		
	}
		
	
	public void init(World world) {

		body = world.createBody(def);
		body.createFixture(fdef);
	
	}

	
}
