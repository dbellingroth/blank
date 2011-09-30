package blank.game.physics;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.JointDef;

public class PhysicsJoint {

	JointDef jDef;
	Joint joint;
	
	public PhysicsJoint(Body bodyA, Body bodyB) {
		
		jDef.bodyA = bodyA;
		jDef.bodyB = bodyB;	
		
		joint = PhysicsWorld.world.createJoint(jDef);
	}
	
}
