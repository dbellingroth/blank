package blank.game;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import blank.game.levelEnviroment.House1;
import blank.game.levelEnviroment.Street;
import blank.game.physics.PhysicsWorld;
import blank.game.rendering.Camera;
import blank.game.rendering.Sprite;
public class WheelChairIdea extends Level {


	private Sprite backround;
	private WheelChairPlayer player;
	private Camera cam;
	
	@Override
	protected void init() {
		backround = new Sprite("res/default_backround.png");

		world = new PhysicsWorld(new Vec2(0f, 0f));

		allObjects = new AllObjectsList();

		// das Test-Rechteck hinzufügen
		player = new WheelChairPlayer(100, 300, 90, 80);
		player.getPhysicsObject().setMass(10f);
		allObjects.add(player);

		cam = new Camera(player.getPhys().getPosition());
		
		generateLevel();
		
		world.start();		
	}
	
	
	private void generateLevel() {
		
		allObjects.add(new House1(0, 0, 500, 600, 0));
		allObjects.add(new Street(0, 500, 500, 350, 0));
		
	}
	
	
	
	
	public void update(int delta) {
		super.update(delta);
	}
	
	@Override
	public void render() {
		cam.setPosition(player.getPhys().getPosition());
		cam.apply();
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
