package blank.game;

import java.awt.geom.Point2D;

import org.lwjgl.opengl.GL11;

/**
 * @deprecated  Nicht mehr notwendig
 */
public class CollideBox implements WantDraw {

	
	GameObject root;			//das GameObject, zudem die CBox gehört
	float angle;				//der Drehwinkel, vom Mittelpunkt, im Uhrzeigersinn von der Grundstellung
	boolean drawMe;
	
	Point2D p1, p2, p3, p4;		//die vier Eckpunkte, von oben links im Uhrzeigersinn durchnummeriert
	
								//die vier Punkte. Merkwüdigerweise erkennt Java nicht
								//javax.vecmath.Vector2d....mysterioes...
	
	
	
	
	CollideBox(GameObject root) {
		
		this.root = root;		
		
	}
	
	
	
	
	public boolean collideTest(CollideBox otherCBox) {
	
		
		return false;
	}
	
	
	public void draw() {
		
		if (drawMe) {
			
			//Wie zeichnet man mit GL11 Linien???
			
		}
		
	}



	
	
	
	
	

	private GameObject getRoot() {
		return root;
	}




	private void setRoot(GameObject root) {
		this.root = root;
	}




	private float getAngle() {
		return angle;
	}




	private void setAngle(float angle) {
		this.angle = angle;
	}




	private boolean isDrawMe() {
		return drawMe;
	}




	private void setDrawMe(boolean drawMe) {
		this.drawMe = drawMe;
	}


	public double getCoord(String corner, String dimension) {
		
		Point2D coord = null;
		
		if (corner == "p1") {
			coord = p1;
		} else if (corner == "p2") {
			coord = p2;
		} else if (corner == "p3") {
			coord = p3;
		} else if (corner == "p4") {
			coord = p4;
		}
		
		if (dimension == "x") {
			return coord.getX();
		} else if (dimension == "y") {
			return coord.getY();
		} else {
			System.out.println("Falsche Argumente in der Methode CollideBox.getCoord() übergeben!");
			return (Double) null;
			
		}
		
	}
	
	
	
	
	
	
	
	
}
