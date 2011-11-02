package blank.game.physics;

import java.util.ArrayList;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import blank.game.Tools;

/**
 * 
 * @author Kilian Helmenstein 
 * Ein Dynamisches Polygon das aus beliebig vielen
 *         Punkten bestehen kann
 */
public class PhysicsPolygon extends PhysicsObject {

	private BodyDef def;
	private FixtureDef fdef;

	public PhysicsPolygon(float x, float y, ArrayList<Vec2> positions, BodyType bodyType) {

		def = new BodyDef();
		def.position.set(x / PhysicsWorld.pixelsPerMeter, +y
				/ PhysicsWorld.pixelsPerMeter);
		def.bullet = true;
		def.type = bodyType;

		PolygonShape shape = new PolygonShape();
		Vec2[] positions_array = Tools.arrayList_to_array(positions, new Vec2(
				(float) 1 / PhysicsWorld.pixelsPerMeter, (float) 1
						/ PhysicsWorld.pixelsPerMeter));

		shape.set(positions_array, positions_array.length);
		shape.m_type = ShapeType.POLYGON;

		fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.friction = 0.3f;
		fdef.density = 0.5f;
	}

	public void init(World world) {
		body = world.createBody(def);
		body.setUserData(this);
		body.createFixture(fdef);
	}

}
