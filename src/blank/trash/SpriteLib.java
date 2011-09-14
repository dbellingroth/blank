package blank.trash;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class SpriteLib {

	
	public HashMap<String, Sprite> sprites;
	
	
	
	
	
	SpriteLib() {
		sprites = new HashMap<String, Sprite>();
	}
	
	
	public void add(String name, String pfad) {
		
			sprites.put(name, new Sprite(pfad));

	}
	
	
	public Sprite get(String name) {
		
		return sprites.get(name);
		
	}
	
}
