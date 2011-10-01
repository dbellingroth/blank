package blank.game.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.PrismaticJoint;
import org.jbox2d.dynamics.joints.PrismaticJointDef;

import blank.game.Tools;

/**
 * 
 * @author Kilian Helmenstein
 * 
 * 
 */
public class PneumaticJoint extends PhysicsJoint {

	private PrismaticJointDef jointDef;
	private PrismaticJoint joint;

	public PneumaticJoint(PhysicsObject pObjectA, PhysicsObject pObjectB,
			Vec2 anchor, Vec2 axis) {

		jointDef = new PrismaticJointDef();

		Vec2 anchorPoint = new Vec2(pObjectA.getBody().getPosition().x
				+ Tools.convertVectorPix2Phys(anchor).x, pObjectA.getBody()
				.getPosition().y + Tools.convertVectorPix2Phys(anchor).y);

		jointDef.initialize(pObjectA.getBody(), pObjectB.getBody(),
				Tools.convertVectorPix2Phys(anchorPoint), axis);
		jointDef.collideConnected = false;

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

	public void enableTransLimit(boolean enable) {
		if (joint != null)
			joint.enableLimit(enable);
		else
			jointDef.enableLimit = enable;
	}

	public void enableMotor(boolean enable) {
		if (joint != null)
			joint.m_enableMotor = enable;
		else
			joint.m_enableLimit = enable;
	}

	public void setMotorForce(float torque) {
		joint.m_maxMotorForce = torque;
	}

	public void setMotorSpeed(float speed) {
		joint.m_motorSpeed = speed;
	}

	
	public PrismaticJoint getJoint() {
		if (joint != null) return joint;
		else return null;
	}
	
	public void init(World world) {

		joint = (PrismaticJoint) world.createJoint(jointDef);
		joint.setUserData(this);

	}

}
