package blank.game.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.PrismaticJoint;
import org.jbox2d.dynamics.joints.PrismaticJointDef;

import blank.game.Tools;

/**
 * Ein Body kann auf einer angegeben Schiene hin und her fahren.
 * Außerdem kann ein Motor aktiviert werden: Das Objekt wird dann
 * mit den angegebenen Werten (motorTorque, direction*motorSpeed) "gedrückt".
 * 
 * @author Kilian Helmenstein
 * 
 */
public class PneumaticJoint extends PhysicsJoint {

	private PrismaticJointDef jointDef;
	private PrismaticJoint joint;

	public PneumaticJoint(PhysicsObject pObjectA, PhysicsObject pObjectB,
			Vec2 anchor, Vec2 axis) {

		jointDef = new PrismaticJointDef();

		Vec2 anchorPoint = new Vec2(pObjectB.getBody().getPosition().x
				+ Tools.convertVectorPix2Phys(anchor).x, pObjectB.getBody()
				.getPosition().y + Tools.convertVectorPix2Phys(anchor).y);

		jointDef.collideConnected = false;
		jointDef.initialize(pObjectA.getBody(), pObjectB.getBody(),
				Tools.convertVectorPix2Phys(anchorPoint), axis);
		
	}
	
	

	public void enableTransLimit(boolean enable) {
		if (joint != null)
			joint.enableLimit(enable);
		else
			jointDef.enableLimit = enable;
	}
	
	public void setLowerTranslation(float trans) {
		if (joint != null)
			joint.m_lowerTranslation = trans;
		else
			jointDef.lowerTranslation = trans;
	}

	public void setUpperTranslation(float trans) {
		if (joint != null)
			joint.m_upperTranslation = trans;
		else
			jointDef.upperTranslation = trans;
	}

	
	
	
	
	

	public void enableMotor(boolean enable) {
		if (joint != null)
			joint.m_enableMotor = enable;
		else
			jointDef.enableMotor = enable;
	}

	public void setMotorForce(float torque) {
		if (joint != null) joint.m_maxMotorForce = torque;
		else jointDef.maxMotorForce = torque;
	}

	public void setMotorSpeed(float speed) {
		if (joint != null) joint.m_motorSpeed = speed;
		else jointDef.motorSpeed = speed;
	}

	
	
	public Joint getPhysicsJoint() {
		return joint;
	}
	
	
	
	public void init(World world) {

		joint = (PrismaticJoint) world.createJoint(jointDef);
		joint.setUserData(this);

	}

}
