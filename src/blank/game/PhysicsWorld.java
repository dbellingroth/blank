package blank.game;

import java.util.LinkedList;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class PhysicsWorld {
	
    private World world;  
	
	public PhysicsWorld() {
        Vec2 gravity = new Vec2((float) 0.0, (float) 9.81); 
        boolean doSleep = true;
        world = new World(gravity, doSleep);
	}
	
	public void update(int delta) {
		world.step(delta, 5, 5);
	}
	
	public Body addBody() {
		BodyDef def = new BodyDef();
		def.position.set((float)Math.random()*10+10, (float)Math.random()*10+10);
		def.type = BodyType.DYNAMIC;
		Body body = world.createBody(def);
		
		CircleShape c = new CircleShape();
		c.m_radius = 10;
		
		FixtureDef fd = new FixtureDef();
		fd.shape = c;
		fd.friction = 1f;
		fd.density = 0.5f;
		body.createFixture(fd);
		//body.m_mass = 10;
		return body;
	}
	
	public Body addStaticBody() {
		BodyDef def = new BodyDef();
		def.position.set(0,300);
		def.type = BodyType.STATIC;
		Body body = world.createBody(def);
		
		CircleShape c = new CircleShape();
		c.m_radius = 200;
		
		FixtureDef fd = new FixtureDef();
		fd.shape = c;
		fd.friction = 0.5f;
		fd.density = 0.5f;
		body.createFixture(fd);
		//body.m_mass = 10;
		return body;
	}
	
}
