package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import graphics.Display;

/**
 * A subclass of entity, which has a random starting speed. The class is also
 * responsible for repainting the image of the stealth bomber.
 */
public class EnemyBomber extends Entity {

	private Rectangle collisionBound;
	private int dx = -(int) (Math.random() * 6 + 1);
	private int dy = 0;

	public EnemyBomber(int xPos, int yPos, int size) {
		super(xPos, yPos);
		collisionBound = new Rectangle(xPos, yPos, size, size);
	}

	public void move(int dx, int dy) {
		this.setXPos(getXPos() + dx);
		this.setYPos(getYPos() + dy);
	}

	public boolean isOutOfBounds(int stopX) {
		return getXPos() < stopX;
	}

	@Override
	public void update() {
		this.move(dx, dy);
	}

	@Override
	public void repaint(Graphics g) {
		BufferedImage planeImage = Display.loadImage("/textures/testBomber.png");
		g.drawImage(planeImage, getXPos(), getYPos(), null);
	}

	public void setCollisionBound(int xPos, int yPos, int size) {
		collisionBound = new Rectangle(xPos, yPos, size, size);
	}

	public Rectangle getCollisionBound() {
		return collisionBound;
	}
}
