package blank.game;


import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import blank.game.physics.AttritionJoint;
import blank.game.physics.GearWheelJoint;
import blank.game.physics.GlibberJoint;
import blank.game.physics.GlueJoint;
import blank.game.physics.NailJoint;
import blank.game.physics.PhysicsStaticBlock;
import blank.game.physics.PhysicsWorld;
import blank.game.physics.PneumaticJoint;
import blank.game.rendering.Sprite;

public class TestLevel extends Level {

	private Sprite test_backround;
	private Player player;
	
	@Override
	protected void init() {
		test_backround = new Sprite("res/backround_test.png");

		world = new PhysicsWorld();

		allObjects = new AllObjectsList();

		world.addObject(new PhysicsStaticBlock(0, -100, 800, 100));
		world.addObject(new PhysicsStaticBlock(-100, 0, 100, 600));
		world.addObject(new PhysicsStaticBlock(800, 0, 100, 600));

		PhysicsStaticBlock ground = new PhysicsStaticBlock(0, 530, 800, 100);
		world.addObject(ground);
		
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
		player = new Player(100, 300, 40, 40, BodyType.DYNAMIC);
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


		Circle bodyA = new Circle(100, 300, 40, BodyType.DYNAMIC);
		
		Rectangle bodyB = new Rectangle(280, 300, 40, 70, BodyType.DYNAMIC);
		
		Rectangle bodyC = new Rectangle(480, 300, 100, 70, BodyType.DYNAMIC);
		
		Circle bodyD = new Circle(700, 300, 40, BodyType.DYNAMIC);
		
		allObjects.add(bodyA);
		allObjects.add(bodyB);
		allObjects.add(bodyC);
		allObjects.add(bodyD);
		
		
		NailJoint joint1 = new NailJoint(ground, bodyA.getPhysicsObject(),
								new Vec2(0, 0));
		joint1.enableMotor(true);
		joint1.setMotorSpeed(10);
		joint1.setMotorTorque(100);
		joint1.setMotorSpeed(5);
		joint1.setMotorTorque(1000);
		world.addJoint(joint1);
	
				
		PneumaticJoint joint2 = new PneumaticJoint(ground, bodyB.getPhysicsObject(),
								new Vec2(0, 0), new Vec2(0f, -1f));
		joint2.setUpperTranslation(3f);
		joint2.setLowerTranslation(-3f);
		joint2.enableTransLimit(true);
		world.addJoint(joint2);	
		
		
		PneumaticJoint joint3 = new PneumaticJoint(ground, bodyC.getPhysicsObject(),
				new Vec2(0, 0), new Vec2(0f, 1f));
		joint3.setUpperTranslation(1);
		joint3.setLowerTranslation(-1);
		joint3.enableTransLimit(true);
		world.addJoint(joint3);	
		
		NailJoint joint4 = new NailJoint(ground, bodyD.getPhysicsObject(),
				new Vec2(0, 0));
		world.addJoint(joint4);
	



		GearWheelJoint gwJoint1 = new GearWheelJoint(ground, ground,
							joint1, joint2, 3f);
		world.addJoint(gwJoint1);
		
		
		GearWheelJoint gwJoint2 = new GearWheelJoint(ground, ground,
				joint1, joint3, 3f);
		world.addJoint(gwJoint2);
		
		
		
//		ArrayList<Vec2> points = new ArrayList<Vec2>();
//		points.add(new Vec2(100, 100));
//		points.add(new Vec2(200, 100));
//		points.add(new Vec2(200, 450));
//		points.add(new Vec2(450, 450));
//		allObjects.add(new Lines(points));	
		

		
		Rectangle rec1 = new Rectangle(30, 30, 20, 20, BodyType.DYNAMIC);
		Rectangle rec2 = new Rectangle(100, 100, 20, 20, BodyType.DYNAMIC);
		allObjects.add(rec1);
		allObjects.add(rec2);

		
//		GlueJoint glueJoint = new GlueJoint(rec1.getPhysicsObject(), rec2.getPhysicsObject());
//		world.addJoint(glueJoint);
//		AttritionJoint attritionJoint = new AttritionJoint(player.getPhysicsObject(), ground);
//		world.addJoint(attritionJoint);
		
		
//		GlibberJoint lJoint = new GlibberJoint();				
//		for (int i = 0; i < 40; i++) {
//			
//			int x = (int) (Math.cos(Math.PI/20 * i) * 50);
//			int y = (int) (Math.sin(Math.PI/20 * i) * 50);
//					
//			Circle circle = new Circle(200+x, 200+y, 5, BodyType.DYNAMIC);
//			
//			lJoint.addPhysicsObject(circle.getPhysicsObject());
//			allObjects.add(circle);			
//		}
//		world.addJoint(lJoint);
		
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
