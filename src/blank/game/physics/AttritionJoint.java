package blank.game.physics;


import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.FrictionJoint;
import org.jbox2d.dynamics.joints.FrictionJointDef;
import org.jbox2d.dynamics.joints.Joint;

/**
 * Weiss ich auch noch nicht so richtig...
 * Schaltet irgendwie die Collision der beiden Bodies aus...
 * 
 * @author Kilian Helmenstein
 *
 */
public class AttritionJoint extends PhysicsJoint {

	private FrictionJointDef jointDef;
	private FrictionJoint joint;
		
	public AttritionJoint(PhysicsObject pObjectA, PhysicsObject pObjectB) {
		
		jointDef = new FrictionJointDef();
		
		jointDef.maxForce = 0f;
		jointDef.maxTorque = 1000000f;
		
		jointDef.initialize(pObjectA.getBody(), pObjectB.getBody(), 
				pObjectB.getBody().getWorldCenter());
		
	}
	
	
	public void setMaxTorque(float torque) {
		if (joint != null) joint.setMaxTorque(torque);
		else jointDef.maxTorque = torque;
	}
	
	
	public void setMaxForce(float force) {
		if (joint != null) joint.setMaxForce(force);
		else jointDef.maxForce = force;
	}
	
		
	void init(World world) {
		
		joint = (FrictionJoint) world.createJoint(jointDef);
		joint.setUserData(this);
		
	}

	
	Joint getPhysicsJoint() {
		return joint;
	}

}
