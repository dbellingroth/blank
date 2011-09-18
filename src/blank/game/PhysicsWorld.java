package blank.game;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
 
 
public class PhysicsWorld {
    public int delta;

    
    
    private World world;
 

    
    PhysicsWorld(Vec2 size, float gravity, boolean bounded) {
    	
    	initPhysicsWorld();
    	
    }
    
    
    
    private void initPhysicsWorld() {
    	
    	
    	//Physics-World initialisieren   
        Vec2 gravity = new Vec2((float) 0.0, (float) -10.0);  
        boolean doSleep = true;  
        world = new World(gravity, doSleep);  
    	
    	
    	
    }
    
 
    
    public void update() {
       
    }    
    
    
}
