package blank.game;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import blank.game.physics.GearWheelJoint;
import blank.game.physics.NailJoint;
import blank.game.physics.PhysicsStaticBlock;
import blank.game.physics.PhysicsWorld;
import blank.game.physics.PneumaticJoint;
import blank.game.rendering.Sprite;

public class TestLevel extends Level {

	private Sprite test_backround;
	private Rectangle player;
	
	@Override
	protected void init() {
		test_backround = new Sprite("res/backround_test.png");

		world = new PhysicsWorld();

		allObjects = new AllObjectsList();

		world.addObject(new PhysicsStaticBlock(0, 530, 800, 100));
		world.addObject(new PhysicsStaticBlock(0, -100, 800, 100));
		world.addObject(new PhysicsStaticBlock(-100, 0, 100, 600));
		world.addObject(new PhysicsStaticBlock(800, 0, 100, 600));

		// Bälle hinzufügen
		// for (int i = 1; i< 40; i+=2) {
		// for (int j = 1; j<30; j+=2) {
		// allObjects.add(new Ball(i*20, j*20,(int)(Math.random()*12+4)));
		//
		// ArrayList<Vec2> positions = new ArrayList<Vec2>();
		// positions.add(new Vec2(0, 0));
		// positions.add(new Vec2((float)Math.random()*70+5,
		// (float)Math.random()*70+5));
		// positions.add(new Vec2(0, (float)Math.random()*70+5));
		//
		// allObjects.add(new DynamicPolygon(i*20, j*20, positions));
		// }
		// }

		// das Test-Rechteck hinzufügen
		player = new Rectangle(100, 300, 40, 40, BodyType.DYNAMIC);
		player.getPhysicsObject().setMass(10f);
		// //left
		// inputHandler.addKeyPressedListener(dr, 203, 1);
		// inputHandler.addKeyReleasedListener(dr, 203, 1);
		// //right
		// inputHandler.addKeyPressedListener(dr, 205, 2);
		// inputHandler.addKeyReleasedListener(dr, 205, 2);
		// //up
		// inputHandler.addKeyPressedListener(dr, 200, 3);
		// inputHandler.addKeyReleasedListener(dr, 200, 3);
		// //down
		// inputHandler.addKeyPressedListener(dr, 208, 4);
		// inputHandler.addKeyReleasedListener(dr, 208, 4);
		allObjects.add(player);

		Circle bodyA = new Circle(100, 300, 10, BodyType.STATIC);
		Circle bodyB = new Circle(100, 300, 40, BodyType.DYNAMIC);
		
		Circle bodyC = new Circle(300, 300, 10, BodyType.STATIC);
		Rectangle bodyD = new Rectangle(280, 300, 40, 70, BodyType.DYNAMIC);
		
		Circle bodyE = new Circle(500, 300, 10, BodyType.STATIC);
		Rectangle bodyF = new Rectangle(480, 300, 40, 70, BodyType.DYNAMIC);
		
		Circle bodyG = new Circle(700, 300, 10, BodyType.STATIC);
		Circle bodyH = new Circle(700, 300, 40, BodyType.DYNAMIC);
		
		allObjects.add(bodyA);
		allObjects.add(bodyB);
		allObjects.add(bodyC);
		allObjects.add(bodyD);
		allObjects.add(bodyE);
		allObjects.add(bodyF);
		allObjects.add(bodyG);
		allObjects.add(bodyH);

		
		NailJoint joint1 = new NailJoint(bodyA.getPhysicsObject(), bodyB.getPhysicsObject(),
								new Vec2(0, 0));
		world.addJoint(joint1);
	
				
		PneumaticJoint joint2 = new PneumaticJoint(bodyC.getPhysicsObject(), bodyD.getPhysicsObject(),
								new Vec2(0, 0), new Vec2(0f, -1f));
		joint2.setUpperTranslation(2f);
		joint2.setLowerTranslation(-2f);
		joint2.enableTransLimit(true);
		world.addJoint(joint2);	
		
		
		PneumaticJoint joint3 = new PneumaticJoint(bodyE.getPhysicsObject(), bodyF.getPhysicsObject(),
				new Vec2(0, 0), new Vec2(0f, 1f));
		joint3.setUpperTranslation(2f);
		joint3.setLowerTranslation(-2f);
		joint3.enableTransLimit(true);
		world.addJoint(joint3);	
		
		
		NailJoint joint4 = new NailJoint(bodyG.getPhysicsObject(), bodyH.getPhysicsObject(),
				new Vec2(0, 0));
		world.addJoint(joint4);
		joint4.enableMotor(true);
		joint4.setMotorSpeed(10);
		joint4.setMotorTorque(10);

		
		GearWheelJoint gwJoint1 = new GearWheelJoint(bodyA.getPhysicsObject(), bodyC.getPhysicsObject(),
				joint1.getPhysicsJoint(), joint2.getPhysicsJoint(), 1f);
//		world.addJoint(gwJoint1);
		
		GearWheelJoint gwJoint2 = new GearWheelJoint(bodyC.getPhysicsObject(), bodyE.getPhysicsObject(),
				joint2.getPhysicsJoint(), joint3.getPhysicsJoint(), -1f);
		world.addJoint(gwJoint2);
		
		GearWheelJoint gwJoint3 = new GearWheelJoint(bodyA.getPhysicsObject(), bodyG.getPhysicsObject(),
				joint1.getPhysicsJoint(), joint4.getPhysicsJoint(), 2f);
		world.addJoint(gwJoint3);
		
		
//		ArrayList<Vec2> points = new ArrayList<Vec2>();
//		points.add(new Vec2(100, 100));
//		points.add(new Vec2(200, 100));
//		points.add(new Vec2(200, 450));
//		points.add(new Vec2(450, 450));
//		allObjects.add(new Lines(points));	
		
		world.start();
	}
	
	@Override
	public void update(int delta) {
		super.update(delta);
	}
	
	@Override
	public void render() {
		test_backround.draw();
		super.render();
	}
	
	public void keyPressed(int key) {
		player.keyPressed(key);
	}

	public void keyReleased(int key) {
		player.keyReleased(key);
	}

	public void mousePressed(int button) {

	}

	public void mouseReleased(int button) {

	}

}
