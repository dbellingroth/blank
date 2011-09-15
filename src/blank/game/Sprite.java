package blank.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class Sprite extends Transformable {

	private int zIndex = 0;
	private int textureID;
	private BufferedImage image;

	public Sprite(int width, int height) {
		IntBuffer textureIDBuffer;
		textureIDBuffer = BufferUtils.createIntBuffer(1);
		textureID = textureIDBuffer.get(0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		update();
	}

	public void update() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		ByteBuffer buf = Tools.convertImageData(image);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER,
				GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER,
				GL11.GL_LINEAR);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA,
				image.getWidth(), image.getHeight(), 0, GL11.GL_RGBA,
				GL11.GL_UNSIGNED_BYTE, buf);
		
	}

	@Override
	public void draw() {

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

		GL11.glPushMatrix();

		super.transform();

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(0, 0);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(image.getWidth(), 0);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(image.getWidth(), image.getHeight());
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(0, image.getHeight());
		GL11.glEnd();

		GL11.glPopMatrix();
	}

	public Graphics2D getGraphics2D() {
		return (Graphics2D) image.getGraphics();
	}

	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	@Override
	public int getZIndex() {
		return zIndex;
	}

}
