package blank.game.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.PulleyJoint;
import org.jbox2d.dynamics.joints.PulleyJointDef;

import blank.game.Tools;

public class LiftJoint extends PhysicsJoint {

	private PulleyJointDef jointDef;
	private PulleyJoint joint;
	
	
	public LiftJoint(PhysicsObject pObjectA, PhysicsObject pObjectB, 
			Vec2 groundAnchorA, Vec2 groundAnchorB,  Vec2 anchorA, Vec2 anchorB, float ratio) {
		
		
		jointDef = new PulleyJointDef();
		
		Vec2 anchorPointA = new Vec2(pObjectA.getBody().getPosition().x
				+ Tools.convertVectorPix2Phys(anchorA).x, 
				pObjectA.getBody().getPosition().y + Tools.convertVectorPix2Phys(anchorA).y);
		Vec2 anchorPointB = new Vec2(pObjectB.getBody().getPosition().x
				+ Tools.convertVectorPix2Phys(anchorB).x, pObjectB.getBody()
				.getPosition().y + Tools.convertVectorPix2Phys(anchorB).y);
		
		jointDef.initialize(pObjectA.getBody(), pObjectB.getBody(), Tools.convertVectorPix2Phys(groundAnchorA), 
								Tools.convertVectorPix2Phys(groundAnchorB), anchorPointA, anchorPointB, ratio);	
		
		jointDef.lengthA = 10f;
		jointDef.lengthB = 10f;
		
		
	}


	void init(World world) {
		
		joint = (PulleyJoint) world.createJoint(jointDef);
		joint.setUserData(this);
		
	}

	
	public void setLengthA(float lenghtA) {
		

	}
	
}
