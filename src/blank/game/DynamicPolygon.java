package blank.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import org.jbox2d.common.Vec2;

import blank.game.physics.CollisionData;
import blank.game.physics.PhysicsObject;
import blank.game.physics.PhysicsOwner;
import blank.game.physics.PhysicsPolygon;
import blank.game.physics.PhysicsWorld;
import blank.game.rendering.Drawable;
import blank.game.rendering.Sprite;



public class DynamicPolygon implements GameObject, PhysicsOwner, Drawable {
	
	private PhysicsObject phys;
	private Sprite sprite;
	public float width, height;
	public float centerX, centerY;
	private int zIndex;
	private boolean visible;

	public DynamicPolygon(int x, int y, ArrayList<Vec2> positions) {
		

		//Breite und Höhe berechnen
		for (Vec2 pos : positions) {
			width = (pos.x > width ? pos.x : width);
			height = (pos.y > height ? pos.y : height);
		}
		
		
		phys = new PhysicsPolygon(x, y, (int) width, (int) height, positions);
		phys.setOwner(this);
		Game.getPhysicsWorld().addObject(phys);
		
		
		sprite = new Sprite((int) Math.round(width), (int) Math.round(height));
		Graphics2D g2d = sprite.getGraphics2D();
		g2d.setColor(Color.MAGENTA);
		
		
		
		
		int[] xPoints = new int[positions.size()];
		int[] yPoints = new int[positions.size()];
		

		
		for (int i = 0; i < positions.size(); i++) {
			xPoints[i] = (int) positions.get(i).x;
			yPoints[i] = (int) positions.get(i).y;
		}
		
		
				
//		CenterX, CenterY berechnen
		
		int sum_xPoints = 0;
		int sum_yPoints = 0;
		for (int i = 0; i < positions.size(); i++) {
			sum_xPoints =+ (int) positions.get(i).x;
			sum_yPoints =+ (int) positions.get(i).y;
		}
		centerX = sum_xPoints/positions.size();
		centerX = sum_xPoints/positions.size();

		
		g2d.fillPolygon(xPoints, yPoints, positions.size());
		sprite.update();
		
	}

	@Override
	public void draw() {
		sprite.setTranslate(new Vec2(
				phys.getPosition().x, phys.getPosition().y));
		sprite.setRotationPoint(new Vec2(centerX, centerY));
		sprite.setRotationAngle(phys.getAngle());
		sprite.draw();
		

	}
	

	@Override
	public void update(int delta) {
//		phys.applyForce(new Vec2(-5000+(float)Math.random()*10000,-5000+(float)Math.random()*10000), new Vec2(25,25));
	}
	
	@Override
	public void beforeCollision(CollisionData data) {
		
	}

	@Override
	public void beginCollision(CollisionData collision) {

	}

	@Override
	public void endCollision(CollisionData collision) {

	
	}
	
	
	
	
	public PhysicsObject getPhysicsObject() {
		return phys;
	}
	
	
	@Override
	public boolean getVisible() {
		return visible;
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	@Override
	public int getZIndex() {
		return zIndex;
	}
	
	@Override
	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}
	
}
