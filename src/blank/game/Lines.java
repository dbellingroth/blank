package blank.game;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import blank.game.rendering.Drawable;

public class Lines implements GameObject, Drawable {

	private ArrayList<Line> lines;
	
	
	public Lines(ArrayList<Vec2> points, BodyType bodyType) {
		
		lines = new ArrayList<Line>();
		
		for (int p = 0; p < points.size()-1; p++) {
			
			lines.add(new Line(points.get(p), points.get(p+1), bodyType));
		}
		
	}
	

	public void update(int delta) {
		for (Line line : lines) {
			line.update(delta);
		}
	}

	public void draw() {
		for (Line line : lines) {
			line.draw();
		}
	}

	
	public int getZIndex() {
		
		return 0;
	}

	
	public void setZIndex(int zIndex) {
		
	}

	
	public boolean getVisible() {
		
		return false;
	}

	
	public void setVisible(boolean visible) {
		
		
	}

}
