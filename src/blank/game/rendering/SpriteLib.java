package blank.game.rendering;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class SpriteLib {

	HashMap<String, Sprite> spriteList;
	HashMap<String, LinkedList<Sprite>> spriteArray;
	
	public SpriteLib() {
		
		spriteList = new HashMap<String, Sprite>();
		spriteArray = new HashMap<String, LinkedList<Sprite>>();
	}
	
	
	public Sprite getSprite( String path ) {
		
		if ( ! spriteList.containsKey( path ) ) {
			spriteList.put( path, new Sprite( path ) );
		}
		
		return spriteList.get( path );
	}
	
	
	public void deleteSprite( String path ) {
		
		if ( spriteList.containsKey(path) ) spriteList.remove( path );
		
	}
	
	
	public SpriteArray getSpriteArray( String path, int nx, int ny) {
		
		if ( ! spriteArray.containsKey(path) ) {
		LinkedList<Sprite> sprites = new LinkedList<Sprite>();
		
		BufferedImage source = null;
		
		URL image_url = getClass().getClassLoader().getResource(path);

		try {
			source = ImageIO.read(image_url);
		} catch (IOException e) {}
				
		for (int y = 0 ; y < ny ; y++) {
			for (int x = 0 ; x < nx ; x++) {
				sprites.add(new Sprite(source.getSubimage((source.getWidth() / nx) * x, (source.getHeight() / ny) * y, 
															source.getWidth() / nx, source.getHeight() / ny)));
			}
		}
		
		spriteArray.put(path, sprites);
		}
		
		return new SpriteArray(spriteArray.get(path));
	}
	
	
	public void deleteSpriteArray( String path ) {
		
		if ( spriteArray.containsKey(path) ) spriteArray.remove( path );
		
	}
	
	public int asdf() {
		return spriteArray.size();
	}
	
}
