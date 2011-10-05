package blank.game.physics;


import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.WeldJoint;
import org.jbox2d.dynamics.joints.WeldJointDef;

/**
 * Verbindet zwei Bodies miteinander, diese haben
 * dann immer dieselbe relative Bewegung(Translation & Angular)
 * Ab einer gewissen Kraft bzw. Drehmoment (maxForce, maxTorque), "lösst sich
 * der Kleber" und die Verbindung wird gelöscht.
 * 
 * @author Kilian Helmenstein
 *
 */
public class GlueJoint extends PhysicsJoint {

	private WeldJointDef jointDef;
	private WeldJoint joint;
	
	
	public GlueJoint(PhysicsObject pObjectA, PhysicsObject pObjectB) {
				
		
	
		
		jointDef = new WeldJointDef();
		
		jointDef.initialize(pObjectA.getBody(), pObjectB.getBody(), 
								pObjectA.getBody().getWorldCenter());		
	}
	
	
	
	void init(World world) {
		
		joint = (WeldJoint) world.createJoint(jointDef);
		joint.setUserData(this);

	}

	
	Joint getPhysicsJoint() {
		return joint;
	}

}
