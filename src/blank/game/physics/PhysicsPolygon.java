package blank.game.physics;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import blank.game.rendering.Tools;

/**
 * 
 * @author Kilian Helmenstein
 * Eine Dynamisches Polygon das aus beliebig vielen Punkten bestehen kann
 */
public class PhysicsPolygon extends PhysicsObject {


	private BodyDef def;
	private FixtureDef fdef;

	
	public PhysicsPolygon(int x, int y, int width, int height, ArrayList<Vec2> positions) {
		
		def = new BodyDef();

		def.position.set(x / PhysicsWorld.pixelsPerMeter + (width / (PhysicsWorld.pixelsPerMeter * 2)), 
							+ y / PhysicsWorld.pixelsPerMeter + (height / (PhysicsWorld.pixelsPerMeter * 2)));
		def.type = BodyType.DYNAMIC;
		def.position.set(x / PhysicsWorld.pixelsPerMeter + (width / (PhysicsWorld.pixelsPerMeter * 2)), 
				y / PhysicsWorld.pixelsPerMeter + (height / (PhysicsWorld.pixelsPerMeter * 2)));
		def.type = BodyType.DYNAMIC;
		

		
		PolygonShape shape = new PolygonShape();

		Vec2[] positions_array = Tools.arrayList_to_array(positions);		
		shape.set(positions_array, positions_array.length);
		
		
		fdef = new FixtureDef();

		fdef.shape = shape;
		fdef.friction = 0.3f;
		fdef.density = 0.5f;

	}

	@Override
	public void init(World world) {
		body = world.createBody(def);
		body.createFixture(fdef);
	}
	
}
