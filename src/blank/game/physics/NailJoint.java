package blank.game.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

import blank.game.Tools;

/**
 * 
 * @author Kilian Helmenstein
 * 
 *         Mit dieser Klasse können zwei Physic-Bodies aneinander "genagelt/geheftet"
 *         werden. 
 *         Zusätzlich kann der Motor aktiviert werden: Ein Drehmoment am
 *         Nagelpunkt.
 */
public class NailJoint extends PhysicsJoint {

	private RevoluteJointDef jointDef;
	private RevoluteJoint joint;

	public NailJoint(PhysicsObject pObjectA, PhysicsObject pObjectB, Vec2 anchor) {

		jointDef = new RevoluteJointDef();

		Vec2 anchorPoint = new Vec2(pObjectA.getBody().getPosition().x
				+ Tools.convertVectorPix2Phys(anchor).x, pObjectA.getBody()
				.getPosition().y + Tools.convertVectorPix2Phys(anchor).y);
		jointDef.collideConnected = false;
		jointDef.initialize(pObjectA.getBody(), pObjectB.getBody(), anchorPoint);

	}

	public void enableLimit(boolean enable) {
		if (joint != null) joint.enableLimit(enable);
		else jointDef.enableLimit = enable;		
	}
	
	public void setUpperLimit(float uLimit) {
		if (joint != null) joint.setLimits(joint.getLowerLimit(), uLimit);
		else jointDef.upperAngle = uLimit;
	}
	
	public void setLowerLimit(float lLimit) {
		if (joint != null) joint.setLimits(lLimit, joint.getUpperLimit());
		else jointDef.lowerAngle = lLimit;
	}
	
	
	public void enableMotor(boolean enable) {
		if (joint != null)
			joint.m_enableMotor = enable;
		else
			jointDef.enableMotor = enable;
	}

	public void setMotorTorque(float torque) {
		if (joint != null)
			joint.m_maxMotorTorque = torque;
		else
			jointDef.maxMotorTorque = torque;
	}

	public void setMotorSpeed(float speed) {
		if (joint != null)
			joint.m_motorSpeed = speed;
		else
			jointDef.motorSpeed = speed;
	}
	
	

	public void init(World world) {
		joint = (RevoluteJoint) world.createJoint(jointDef);
		joint.setUserData(this);
	}
	
	
	public Joint getPhysicsJoint() {
		return joint;
	}
	
	
	

}
