package entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import graphics.Animations;
import graphics.Display;

/**
 * A class responsible for creating a missiles movement method, isOutOfBounds methods, and the explosion animation of a missile.
 */

public abstract class Missile extends Entity {

	private Rectangle collisionBound;
	private int stopX;
	private int stopY;
	private int tempX;
	private int tempY;

	private BufferedImage[] explosionFrames = new BufferedImage[7];
	private Animations explosion = new Animations(explosionFrames);

	public Missile(int xPos, int yPos, int stopX, int stopY, int size) {
		super(xPos, yPos);
		collisionBound = new Rectangle(xPos, yPos, size, size);
		this.stopX = stopX;
		this.stopY = stopY;	
		tempX = xPos;
		tempY = yPos;
		
		explosionFrames[0] = Display.loadImage("/textures/explosion/explosionframe1.png");
		explosionFrames[1] = Display.loadImage("/textures/explosion/explosionframe2.png");
		explosionFrames[2] = Display.loadImage("/textures/explosion/explosionframe3.png");
		explosionFrames[3] = Display.loadImage("/textures/explosion/explosionframe4.png");
		explosionFrames[4] = Display.loadImage("/textures/explosion/explosionframe5.png");
		explosionFrames[5] = Display.loadImage("/textures/explosion/explosionframe6.png");
		explosionFrames[6] = Display.loadImage("/textures/explosion/explosionframe7.png");	
	}

	public void move(double dx, double dy) {
		this.setXPos((int) (getXPos()+dx));
		this.setYPos((int) (getYPos()+dy));
	}

	public boolean isOutOfBounds(int stopX, int stopY) {
		return getXPos() > stopX || getYPos() > stopY;
	}

	public void setCollisionBound(int xPos, int yPos, int size) {
		collisionBound = new Rectangle(xPos, yPos, size, size);
	}

	public Rectangle getCollisionBound() {
		return collisionBound;
	}
	
	public int getTempX() {
		return tempX;
	}
	
	public int getTempY() {
		return tempY;
	}
	
	public int getStopX() {
		return stopX;
	}
	
	public int getStopY() {
		return stopY;
	}
	
	public Animations getExplosion() {
		return explosion;
	}
}
