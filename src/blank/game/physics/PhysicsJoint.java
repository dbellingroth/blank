package blank.game.physics;

import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;

public abstract class PhysicsJoint {

	abstract void init(World world);
	abstract Joint getPhysicsJoint();
	
}
