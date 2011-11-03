package blank.game;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import blank.game.physics.GearWheelJoint;
import blank.game.physics.NailJoint;
import blank.game.physics.PhysicsStaticBlock;
import blank.game.physics.PhysicsWorld;
import blank.game.physics.PneumaticJoint;
import blank.game.rendering.Camera;
import blank.game.rendering.Sprite;

public class TestLevel extends Level {

	private Sprite test_backround, test_ground;
	private Player player;
	PhysicsStaticBlock ground;
	private Camera cam;

	protected void init() {
		test_backround = new Sprite("res/backround_test.png");
		test_ground = new Sprite("res/ground_test.png");
			
		world = new PhysicsWorld();

		allObjects = new AllObjectsList();
		
		world.addObject(new PhysicsStaticBlock(0, -100, 800, 100));
		world.addObject(new PhysicsStaticBlock(-100, 0, 100, 600));
		world.addObject(new PhysicsStaticBlock(1600, 0, 100, 600));
		ground = new PhysicsStaticBlock(0, 530, 1600, 100);
		world.addObject(ground);

		// das Test-Rechteck hinzufügen
		player = new Player(100, 0, 40, 40, BodyType.DYNAMIC);
		player.getPhysicsObject().setMass(10f);
		allObjects.add(player);
		
		allObjects.add(new Circle(100, 100, 30, BodyType.DYNAMIC));
		allObjects.add(new BadlyOctagon(500, 100, 50, 50, BodyType.DYNAMIC));
		
		
		cam = new Camera(player.getPhys().getPosition());
		
//		ArrayList<Vec2> line_points = new ArrayList<Vec2>(); 
//		for (int x=0 ; x < 500 ; x+=50) {
//			
//			float px = (float) ((Math.random()*50) + x);
//			float py = (float) (Math.random()*300) + 100;
//			
//			line_points.add(new Vec2(px, py));			
//		}
//		allObjects.add(new Lines(line_points, true));
		world.start();
	}
	

	public void update(int delta) {
		super.update(delta);
	}
	
	@Override
	public void render() {
		cam.setPosition(player.getPhys().getPosition());
		player.positionOnDisplay = new Vec2(player.getPhys().getPosition().x-cam.getX()+400,
												player.getPhys().getPosition().y-cam.getY()+300);
		cam.apply();
		test_backround.draw();
		test_backround.setTranslate(new Vec2(test_backround.getWidth()-2 + cam.getX()*0.2f, 0));
		test_backround.draw();
		test_backround.setTranslate(new Vec2(cam.getX()*0.2f, 0));
		test_backround.draw();
		test_backround.setTranslate(new Vec2(-795 + cam.getX()*0.2f, 0));
		
		
		test_ground.setTranslate(new Vec2(800, 330));
		test_ground.draw();
		test_ground.setTranslate(new Vec2(-10, 330));
		test_ground.draw();
		super.render();
	}
	
	public void keyPressed(int key) {
		player.keyPressed(key);
	}

	public void keyReleased(int key) {
		player.keyReleased(key);
	}

	public void mousePressed(int button) {
		player.mousePressed(button);
	}

	public void mouseReleased(int button) {
		player.mouseReleased(button);
	}


	@SuppressWarnings("unused")
	private void jointTest() {
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
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
