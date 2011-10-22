package blank.game;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import blank.game.physics.GearWheelJoint;
import blank.game.physics.NailJoint;
import blank.game.physics.PhysicsStaticBlock;
import blank.game.physics.PhysicsWorld;
import blank.game.physics.PneumaticJoint;
import blank.game.rendering.Camera;
import blank.game.rendering.Sprite;

public class WheelChairIdea extends Level {


	private Sprite backround;
	private WheelChairPlayer player;
	private Camera cam;
	
	@Override
	protected void init() {
		backround = new Sprite("res/streets.png");

		world = new PhysicsWorld(new Vec2(0f, 0f));

		allObjects = new AllObjectsList();

		// das Test-Rechteck hinzufügen
		player = new WheelChairPlayer(100, 300, 40, 40, BodyType.DYNAMIC);
		player.getPhysicsObject().setMass(10f);
		allObjects.add(player);

//		cam = new Camera(player.getPhys().getPosition());
		
		world.start();
	}
	
	@Override
	public void update(int delta) {
		super.update(delta);
	}
	
	@Override
	public void render() {
//		cam.setPosition(player.getPhys().getPosition());
//		cam.apply();
		backround.draw();
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
	
	public void mouseWheel(int direction) {
		player.mouseWheel(direction);
	}
}
