package blank.game;
 
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
 
public class Blank extends BasicGame{
	
	private Image image;
	private Graphics img;
 
    public Blank()
    {
        super("Blank");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
    	image = new Image(800,600);
    	img = image.getGraphics();
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
 
    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
    	img.setBackground(new Color(0,0,0,0));
    	img.clear();
    	img.setColor(Color.green);
    	img.drawLine((int)(Math.random()*800),(int)(Math.random()*600),(int)(Math.random()*800),(int)(Math.random()*600));
    	img.flush();
    	image.draw();
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         AppGameContainer app = 
			new AppGameContainer(new Blank());
 
         app.setDisplayMode(800, 600, false);
         app.setShowFPS(false);
         app.start();
    }
}