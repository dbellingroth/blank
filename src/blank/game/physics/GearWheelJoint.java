package blank.game.physics;

import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.GearJoint;
import org.jbox2d.dynamics.joints.GearJointDef;


public class GearWheelJoint extends PhysicsJoint {

	
	private GearJointDef jointDef;
	private GearJoint joint;
	
	
	public GearWheelJoint(PhysicsObject pObjectA, PhysicsObject pObjectB, 
						NailJoint nJoint, PneumaticJoint pJoint, float ratio) {
		
		jointDef = new GearJointDef();
		
		jointDef.bodyA = pObjectA.getBody();
		jointDef.bodyB = pObjectB.getBody();
		jointDef.joint1 = pJoint.getJoint();
		jointDef.joint2 = nJoint.getJoint();
		jointDef.ratio = ratio;

	}
	
	
	
	
	void init(World world) {
		
		joint = (GearJoint) world.createJoint(jointDef);
		joint.setUserData(this);
		
	}

	
	
	
}
