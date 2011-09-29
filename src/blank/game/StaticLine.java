package blank.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import org.jbox2d.common.Vec2;

import blank.game.physics.CollisionData;
import blank.game.physics.PhysicsLine;
import blank.game.physics.PhysicsObject;
import blank.game.physics.PhysicsOwner;
import blank.game.rendering.Drawable;
import blank.game.rendering.Sprite;

public class StaticLine implements GameObject, PhysicsOwner, Drawable {

	
	private PhysicsObject phys;
	private Sprite sprite;
	public float x, y, width, height;
	private int zIndex;
	private boolean visible;
	boolean up, down, left, right; //nur zum testen...
	int key_up = 200, key_down = 208, key_left = 203, key_right = 205;

	public StaticLine(ArrayList<Vec2> points) {

		phys = new PhysicsLine(points);
		phys.setOwner(this);
		Game.getPhysicsWorld().addObject(phys);

		x = points.get(0).x;
		y = points.get(0).y;
		for (Vec2 pos : points) {
			width = (pos.x > width ? pos.x : width);
			height = (pos.y > height ? pos.y : height);
		}
		
		sprite = new Sprite((int) width, (int) height);
		Graphics2D g2d = sprite.getGraphics2D();
		g2d.setColor(Color.MAGENTA);
		for (int i = 1; i < points.size(); i++) {
			g2d.drawLine((int) points.get(i-1).x, (int) points.get(i-1).y, (int) points.get(i).x, (int) points.get(i).y);
		}
		sprite.update();
		
		
	}

	
	public void draw() {	
		
		sprite.setTranslate(new Vec2(
				phys.getPosition().x , phys.getPosition().y));
		
		sprite.setRotationPoint(new Vec2(width / 2, height / 2));
		sprite.setRotationAngle(phys.getAngle());
		sprite.draw();
		
	}

	
	public void update(int delta) {
//		phys.applyForce(new Vec2((Mouse.getX()-phys.getPosition().x)*100, ((600-Mouse.getY())-phys.getPosition().y)*100), new Vec2(width/2,height/2));
//		phys.stop();
		
	}

	
	public void beginCollision(CollisionData collision) {

	}

	
	public void endCollision(CollisionData collision) {

	}
	
	
	
	
	public PhysicsObject getPhysicsObject() {
		return phys;
	}
	
	

	public boolean getVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	

	public int getZIndex() {
		return zIndex;
	}
	

	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}


	public void beforeCollision(CollisionData data) {
	
		
	}	
}
