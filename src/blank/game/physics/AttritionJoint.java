package blank.game.physics;

import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.FrictionJoint;
import org.jbox2d.dynamics.joints.FrictionJointDef;
import org.jbox2d.dynamics.joints.Joint;

public class AttritionJoint extends PhysicsJoint {

	private FrictionJointDef jointDef;
	private FrictionJoint joint;
	
	
	
	
	
	
	
	
	
	
	void init(World world) {
		
		joint = (FrictionJoint) world.createJoint(jointDef);
		joint.setUserData(this);
		
	}

	
	Joint getPhysicsJoint() {
		return joint;
	}

}
