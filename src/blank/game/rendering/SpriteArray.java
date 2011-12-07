package blank.game.rendering;

import java.util.LinkedList;
import org.lwjgl.opengl.GL11;
import blank.game.GameObject;


public class SpriteArray extends Transformable implements GameObject {

	
	private LinkedList<Sprite> sprites;
	private float displayTime, displayedTime;	//Bei ANIMATION: Anzeigedauer in Milisekunden
	private int currentSprite;
	private enum state_enum { SINGLE , ANIMATION };
	private state_enum state;
	private int loopStart, loopEnding;
	private boolean draw, loop;
	
	//Drawable
	private int zIndex;
	private boolean visible;
	
	
	public SpriteArray(LinkedList<Sprite> spriteList) {		
		
		sprites = spriteList;
		displayTime = 1f;
		draw = true;
		loopStart = 0;
		loopEnding = sprites.size()-1;
		
	}
	
	
	public void update(int delta) {
		
		if (state == state_enum.ANIMATION) {
			
			displayedTime += (delta * Math.pow(10, -3));
			
			if (displayedTime >= displayTime) {
				
				displayedTime = 0;
				currentSprite++;
				
				if (currentSprite > (loop ? loopEnding : sprites.size()-1)) {
					if (loop) currentSprite =  (int) loopStart;
					else draw = false;
				}
			}
		}
	}

	public void draw() {
		
		if (draw) {
			GL11.glPushMatrix();
			
			
			super.transform();
			sprites.get(currentSprite).draw();
			
			GL11.glPopMatrix();
		}
	}

	
	public void singleMode(int pic) {
		
		state = state_enum.SINGLE;
		currentSprite = pic%sprites.size();
		
	}
	
	
	public void animationMode(float displayTime, boolean loop) {
		
		state = state_enum.ANIMATION;
		this.displayTime = displayTime;
		this.loop = loop;
		
	}
	
	
	public void animationMode(float displayTime, int loopStart, int loopEnding) {
		
		state = state_enum.ANIMATION;
		this.displayTime = displayTime;
		this.loop = true;
		setLoopPos(loopStart, loopEnding);
		
	}
	
	
	public void loop(boolean loop) {
		
		this.loop = loop;
		
	}
	
	
	public void setLoopPos(int start, int ending) {
		
		loopStart = start;
		loopEnding = ending;
		
	}
	
	
	public int getWidth() {
		return sprites.get(currentSprite).getWidth();
	}
	
	public int getHeight() {
		return sprites.get(currentSprite).getHeight();
	}
	
	public int getZIndex() {
		return zIndex;
	}

	
	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	
	public boolean getVisible() {
		return visible;
	}

	
	public void setVisible(boolean visible) {
		this.visible = visible;	
	}
	
}
