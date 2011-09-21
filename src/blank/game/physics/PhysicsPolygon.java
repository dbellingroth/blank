package blank.game.physics;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class PhysicsPolygon extends PhysicsObject {


	private BodyDef def;
	private FixtureDef fdef;

	
	public PhysicsPolygon(int x, int y, int width, int height, ArrayList<Vec2> positions) {
		
		
		def = new BodyDef();
		def.position.set(x / PhysicsWorld.pixelsPerMeter
				+ (width / (PhysicsWorld.pixelsPerMeter * 2)), y
				/ PhysicsWorld.pixelsPerMeter
				+ (height / (PhysicsWorld.pixelsPerMeter * 2)));
		def.type = BodyType.DYNAMIC;
		def.fixedRotation = true;

		
		PolygonShape shape = new PolygonShape();

		Vec2[] positions_array = new Vec2[positions.size()-1];
		
		for (int i = 0; i < positions.size(); i++) {
			System.out.println(i);
			positions_array[i].x = positions.get(i).x;
			positions_array[i].y = positions.get(i).y;
		}
		
		shape.set(positions_array, positions.size());
		
		fdef = new FixtureDef();

		fdef.shape = shape;
		fdef.friction = 0.3f;
		fdef.density = 1f;

	}

	@Override
	public void init(World world) {
		body = world.createBody(def);
		body.createFixture(fdef);
	}
	
}
