package blank.game.physics;


import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.GearJoint;
import org.jbox2d.dynamics.joints.GearJointDef;
import org.jbox2d.dynamics.joints.Joint;


/**
 * Verbindet PneumaticJoints und/oder NailJoints zu 
 * einem Getriebe/Zahnradwerk.
 * Bsp.: Dreht sich der Kreis, bewegt sich das pneum. Object entsprechend.
 * 
 * @author Kilian Helmenstein
 *
 */
public class GearWheelJoint extends PhysicsJoint {

	
	private GearJointDef jointDef;
	private GearJoint joint;
	
	
	public GearWheelJoint(PhysicsObject pObjectA, PhysicsObject pObjectB, 
						PhysicsJoint joint1, PhysicsJoint joint2, float ratio) {
		
		jointDef = new GearJointDef();
		
		jointDef.bodyA = pObjectA.getBody();
		jointDef.bodyB = pObjectB.getBody();
		
		jointDef.joint1 = joint1.getPhysicsJoint();
		jointDef.joint2 = joint2.getPhysicsJoint();
		
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
