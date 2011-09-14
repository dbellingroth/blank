package blank.game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Game {
	
	Sprite test;
	
	public static void main(String args[]) {
		new Window(new Game(),800,600);
	}
	
	public Game() {
		
	}
	
	public void init() {
		test = new Sprite(100,200);
		Graphics2D g2d = test.getGraphics2D();
		g2d.setColor(Color.GREEN);
		g2d.fillRect(0, 0, 100, 200);
		test.update();
	}
	
	//Einmal pro Frame:
	protected void update(int delta) {
		test.draw();
	}
	
}
