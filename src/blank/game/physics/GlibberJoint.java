package blank.game.physics;

import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.ConstantVolumeJoint;
import org.jbox2d.dynamics.joints.ConstantVolumeJointDef;
import org.jbox2d.dynamics.joints.Joint;


/**
 * Verbindet Bodies (mind. 3) zu einer geschlossenen Form,
 * die ihr Volumen immer beibehält.
 * 
 * @author Kilian Helmenstein
 *
 */
public class GlibberJoint extends PhysicsJoint {

	private ConstantVolumeJointDef jointDef;
	private ConstantVolumeJoint joint;
	
	
	public GlibberJoint() {
		jointDef = new ConstantVolumeJointDef();
		
		jointDef.dampingRatio = 0.1f;
		jointDef.frequencyHz = 10;

	}
	
	
	public GlibberJoint(PhysicsObject pObjectA, PhysicsObject pObjectB) {
		
		this();
		
		jointDef.addBody(pObjectA.getBody());
		jointDef.addBody(pObjectB.getBody());

			
	}
	
	public GlibberJoint(PhysicsObject pObjectA, PhysicsObject pObjectB, PhysicsObject pObjectC) {
		
		this(pObjectA, pObjectB);
		jointDef.addBody(pObjectC.getBody());
			
	}
	
	
	
	public void addPhysicsObject(PhysicsObject pObject) {
		jointDef.addBody(pObject.getBody());
	}
	
	
	void init(World world) {
		
		joint = (ConstantVolumeJoint) world.createJoint(jointDef);
		joint.setUserData(this);
		
	}
	
	Joint getPhysicsJoint() {
		return joint;		
	}

}
