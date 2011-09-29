package blank.game.physics;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class PhysicsLine extends PhysicsObject {

	private BodyDef def;
	private FixtureDef fdef;
	private ArrayList<PolygonShape> shapes;
	private ArrayList<Body> bodies;
	
	
	public PhysicsLine(ArrayList<Vec2> points) {
		
		shapes = new ArrayList<PolygonShape>();
		bodies = new ArrayList<Body>();
		
		
		def = new BodyDef();
		def.position = new Vec2(points.get(0).x, points.get(0).y);
		def.bullet = true;
		def.fixedRotation = true;
		def.type = BodyType.STATIC;
		
		
		for (int i = 0; i < points.size()-1; i++) {
			shapes.add(new PolygonShape());
			shapes.get(i).setAsEdge(points.get(i), points.get(i+1));
		}
		
		
		fdef = new FixtureDef();
		fdef.friction = 0.3f;
		fdef.density = 1f;
		
		
	}
		
	
	public void init(World world) {
		
		for (int i = 0; i < shapes.size(); i++) {
			fdef.shape = shapes.get(i);
			bodies.add(world.createBody(def));
			bodies.get(i).createFixture(fdef);
		}
	
	}

	
	
	public Vec2 getPosition() {
		PhysicsWorld.reservePhysics();
		Vec2 result = bodies.get(0).getPosition();
		PhysicsWorld.releasePhysics();
		return result;	
	}
	
	public double getAngle() {
		PhysicsWorld.reservePhysics();
		double result = 0;
		PhysicsWorld.releasePhysics();
		return result;
	}
	
}
