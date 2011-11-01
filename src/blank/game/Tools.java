package blank.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Hashtable;
import org.jbox2d.common.Vec2;
import blank.game.physics.PhysicsWorld;

/**
 * 
 * @author David Bellingroth & Kilian Helmenstein
 * 
 */
public class Tools {

	/**
	 * Macht aus einem BufferedImage einen ByteBuffer um es als OpenGL Textur zu
	 * laden.
	 */
	@SuppressWarnings("rawtypes")
	public static ByteBuffer convertImageData(BufferedImage bufferedImage) {
		ByteBuffer imageBuffer;
		WritableRaster raster;
		BufferedImage texImage;

		ColorModel glAlphaColorModel = new ComponentColorModel(
				ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] { 8, 8,
						8, 8 }, true, false, Transparency.TRANSLUCENT,
				DataBuffer.TYPE_BYTE);

		raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,
				bufferedImage.getWidth(), bufferedImage.getHeight(), 4, null);
		texImage = new BufferedImage(glAlphaColorModel, raster, true,
				new Hashtable());

		// copy the source image into the produced image
		Graphics g = texImage.getGraphics();
		g.setColor(new Color(0f, 0f, 0f, 0f));
		g.fillRect(0, 0, 256, 256);
		g.drawImage(bufferedImage, 0, 0, null);

		// build a byte buffer from the temporary image
		// that be used by OpenGL to produce a texture.
		byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer())
				.getData();

		imageBuffer = ByteBuffer.allocateDirect(data.length);
		imageBuffer.order(ByteOrder.nativeOrder());
		imageBuffer.put(data, 0, data.length);
		imageBuffer.flip();

		return imageBuffer;
	}

	/**
	 * berechnet die nächstgrößer 2er-Potenz für die Seitenlänge eines Quadrats
	 * 
	 * @return nächstgrößere 2er-Potenz für den größten Wert der beiden
	 *         Argumente
	 */
	public static int next_powerOfTwo_square(int width, int height) {

		/*
		 * die längste Kante der box wird ermittelt... Test ? Ergebnis im
		 * true-Falle : Ergebnis im false-Falle
		 */
		int longest_edge = width > height ? width : height;

		/*
		 * ...und das nächstgrößere Quadrat mit einer Kantenlänge der nächsten
		 * Zweierpotenz
		 */
		int sprite_edge = (int) Math.pow(2,
				Math.round((Math.log(longest_edge) / Math.log(2)) + 0.499999));

		return sprite_edge;

	}

	/**
	 * berechnet die nächstgrößer 2er-Potenz für das Argument
	 * 
	 * @return nächstgrößere 2er-Potenz Argument
	 */
	public static int next_powerOfTwo_square(int longest_edge) {

		/*
		 * ...und das nächstgrößere Quadrat mit einer Kantenlänge der nächsten
		 * Zweierpotenz
		 */
		int sprite_edge = (int) Math.pow(2,
				Math.round((Math.log(longest_edge) / Math.log(2)) + 0.499999));

		return sprite_edge;

	}

	/**
	 * Macht aus einer ArrayList von Positionen ein Array aus Positionen mit
	 * bereits konvertierten Werten für die Physikengine
	 * 
	 * @param positions_arrayList
	 *            Die zu konvertierende ArrayList
	 * @return konvertiertes Array
	 */
	public static Vec2[] arrayList_to_array(ArrayList<Vec2> positions_arrayList) {

		Vec2[] positions_array = new Vec2[positions_arrayList.size()];

		for (int i = 0; i < positions_arrayList.size(); i++) {
			positions_array[i] = new Vec2(positions_arrayList.get(i).x
					/ PhysicsWorld.pixelsPerMeter, positions_arrayList.get(i).y
					/ PhysicsWorld.pixelsPerMeter);
		}

		return positions_array;
	}

	public static Vec2[] arrayList_to_array(
			ArrayList<Vec2> positions_arrayList, Vec2 factor) {

		Vec2[] positions_array = new Vec2[positions_arrayList.size()];
		for (int i = 0; i < positions_arrayList.size(); i++) {
			positions_array[i] = new Vec2(positions_arrayList.get(i).x
					* factor.x, positions_arrayList.get(i).y * factor.y);
		}

		return positions_array;
	}

	public static Vec2 convertVectorPhys2Pix(Vec2 vector) {

		return new Vec2(vector.x * PhysicsWorld.pixelsPerMeter, vector.y
				* PhysicsWorld.pixelsPerMeter);
	}

	public static Vec2 convertVectorPix2Phys(Vec2 vector) {

		return new Vec2(vector.x / PhysicsWorld.pixelsPerMeter, vector.y
				/ PhysicsWorld.pixelsPerMeter);
	}
	
	public int fak(int x) {
		if (x==1) return 1;
		else return x*fak(x-1);
	}
	
	
	public static float getValue(Vec2 vector) {
		return (float) Math.sqrt(Math.pow(vector.x,2)+Math.pow(vector.y,2));		
	}

	
	public static float getScalarProduct(Vec2 vec1, Vec2 vec2) {
		return  ( vec1.x*vec2.x + vec1.y*vec2.y );
	}
	
	
	
	

}

	
