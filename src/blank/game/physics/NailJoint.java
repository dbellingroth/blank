package blank.game.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

import blank.game.Tools;

public class NailJoint extends PhysicsJoint {

	private RevoluteJointDef rJointDef;
	private RevoluteJoint rJoint;
	
	public NailJoint(PhysicsObject pObjectA, PhysicsObject pObjectB, Vec2 anchor) {
		
		rJointDef = new RevoluteJointDef();
		
		
		Vec2 anchorPoint = new Vec2(pObjectA.getBody().getPosition().x + Tools.convertVectorPix2Phys(anchor).x, 
								pObjectA.getBody().getPosition().y + Tools.convertVectorPix2Phys(anchor).y);
		
		rJointDef.initialize(pObjectA.getBody(), pObjectB.getBody(), 
				anchorPoint);
		
		
		
	}
	

	public void enableMotor(float torque, float speed) {
		rJoint.m_enableMotor = true;
		rJoint.m_maxMotorTorque = 10f;
		rJoint.m_motorSpeed = 10f;
	}
	
	
	public void setMotorTorque(float torque) {
		rJoint.m_maxMotorTorque = torque;
	}
	
	public void setMotorSpeed(float speed) {
		rJoint.m_motorSpeed = speed;
	}
	
	public void stopMotor() {
		rJoint.m_enableMotor = false;
	}
	
	public void init(World world) {
			
		rJoint = (RevoluteJoint) world.createJoint(rJointDef);
		rJoint.setUserData(this);
		
	}

	
	
	
	
}
