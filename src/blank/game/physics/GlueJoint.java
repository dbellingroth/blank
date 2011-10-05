package blank.game.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.WeldJoint;
import org.jbox2d.dynamics.joints.WeldJointDef;

import blank.game.Tools;

public class GlueJoint extends PhysicsJoint {

	private WeldJointDef jointDef;
	private WeldJoint joint;
	
	
	public GlueJoint(PhysicsObject pObjectA, PhysicsObject pObjectB, 
									Vec2 anchor) {
				
		Vec2 anchorPoint = new Vec2(pObjectA.getBody().getPosition().x
				+ Tools.convertVectorPix2Phys(anchor).x, pObjectA.getBody()
				.getPosition().y + Tools.convertVectorPix2Phys(anchor).y);
	
		
		jointDef = new WeldJointDef();
		
			
		
		jointDef.initialize(pObjectA.getBody(), pObjectB.getBody(), anchorPoint);		
		

	
	
	
	
	}
	
	
	
	void init(World world) {
		
		joint = (WeldJoint) world.createJoint(jointDef);
		joint.setUserData(this);

	}

	
	Joint getPhysicsJoint() {
		return joint;
	}

}
