package blank.game;

import org.jbox2d.common.Vec2;

import blank.game.rendering.Drawable;
import blank.game.rendering.Sprite;

public class Snow implements Drawable, GameObject {

	private Sprite sprite;
	private Vec2 pos;
	private float radius;
	private float speed;
	
	
	public Snow(Sprite sprite, Vec2 pos, float radius, float speed) {
		
		this.sprite = sprite;
		this.pos = pos;
		this.radius = radius;
		this.speed = speed;
		
		
	}
	
	public void update(int delta) {
		
		pos.y += speed;

		if (pos.y > 600) {
			pos.y = -20;
		}
	}

	@Override
	public void draw() {
		sprite.setTranslate(pos);
		sprite.setScaleFactor(new Vec2((radius*2)/sprite.getWidth(), (radius*2)/sprite.getHeight()));
		sprite.draw();
	}

	@Override
	public int getZIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setZIndex(int zIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub

	}

}
