package blank.game.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.DistanceJoint;
import org.jbox2d.dynamics.joints.DistanceJointDef;

import blank.game.Tools;

/**
 * 
 * @author Kilian Helmenstein
 * 
 *         Mit dieser KLasse könne zwei Physic-Bodies so verbunden werden, dass
 *         ihre Anchor-Punkte immer den gleichen Abstand zueinander haben
 *         (length).
 * 
 */
public class StickJoint extends PhysicsJoint {

	private DistanceJointDef jointDef;
	private DistanceJoint joint;

	public StickJoint(PhysicsObject pObjectA, PhysicsObject pObjectB,
			Vec2 anchorA, Vec2 anchorB) {

		jointDef = new DistanceJointDef();

		Vec2 anchorPointA = new Vec2(pObjectA.getBody().getPosition().x
				+ Tools.convertVectorPix2Phys(anchorA).x, pObjectA.getBody()
				.getPosition().y + Tools.convertVectorPix2Phys(anchorA).y);
		Vec2 anchorPointB = new Vec2(pObjectA.getBody().getPosition().x
				+ Tools.convertVectorPix2Phys(anchorB).x, pObjectA.getBody()
				.getPosition().y + Tools.convertVectorPix2Phys(anchorB).y);

		jointDef.initialize(pObjectA.getBody(), pObjectB.getBody(),
				anchorPointA, anchorPointB);
		jointDef.frequencyHz = 10;
		jointDef.dampingRatio = 0.1f;

	}

	public void setLength(float length) {
		if (joint != null)
			joint.m_length = length;
		else
			jointDef.length = length;
	}

	public void setDampingRation(float dampingRatio) {
		if (joint != null)
			joint.m_dampingRatio = dampingRatio;
		else
			jointDef.dampingRatio = dampingRatio;
	}

	public void setFrequency(float frequencyHz) {
		if (joint != null)
			joint.m_frequencyHz = frequencyHz;
		else
			jointDef.frequencyHz = frequencyHz;
	}

	
	public DistanceJoint getJoint() {
		if (joint != null) return joint;
		else return null;
	}
	
	void init(World world) {
		joint = (DistanceJoint) world.createJoint(jointDef);
		joint.setUserData(this);
	}
}
