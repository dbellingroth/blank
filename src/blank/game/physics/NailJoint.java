package blank.game.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

import blank.game.Tools;

/**
 * 
 * @author Kilian Helmenstein
 * 
 *         Mit dieser Klasse können zwei Physic-Bodies aneinander genagelt
 *         werden. Zusätzlich kann der Motor aktiviert werden: Ein Drehmoment am
 *         Nagelpunkt
 */
public class NailJoint extends PhysicsJoint {

	private RevoluteJointDef jointDef;
	private RevoluteJoint joint;

	public NailJoint(PhysicsObject pObjectA, PhysicsObject pObjectB, Vec2 anchor) {

		jointDef = new RevoluteJointDef();

		Vec2 anchorPoint = new Vec2(pObjectA.getBody().getPosition().x
				+ Tools.convertVectorPix2Phys(anchor).x, pObjectA.getBody()
				.getPosition().y + Tools.convertVectorPix2Phys(anchor).y);

		jointDef.initialize(pObjectA.getBody(), pObjectB.getBody(), anchorPoint);

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

}
