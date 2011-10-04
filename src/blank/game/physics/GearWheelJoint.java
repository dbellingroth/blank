package blank.game.physics;


import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.GearJoint;
import org.jbox2d.dynamics.joints.GearJointDef;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.PrismaticJoint;
import org.jbox2d.dynamics.joints.RevoluteJoint;


public class GearWheelJoint extends PhysicsJoint {

	
	private GearJointDef jointDef;
	private GearJoint joint;
	
	
	public GearWheelJoint(PhysicsObject pObjectA, PhysicsObject pObjectB, 
						Joint joint1, Joint joint2, float ratio) {
		
		jointDef = new GearJointDef();
		
		jointDef.bodyA = pObjectA.getBody();
		jointDef.bodyB = pObjectB.getBody();
		
		jointDef.joint1 = joint1;
		jointDef.joint2 = joint2;
		
		jointDef.ratio = ratio;
	}
	
	
	void init(World world) {
		
		joint = (GearJoint) world.createJoint(jointDef);
		joint.setUserData(this);
	}


	
	Joint getPhysicsJoint() {
		return joint;
	}

	
	
	
}
