package buildings;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Class responsible for creating a generic building, having memory of its position and creating its hitbox.
 */
public class Building {
	
	private Rectangle collisionBound;
	private int xPos;
	private int yPos;
	
	public Building(int xPos, int yPos, int size) {
		this.xPos = xPos;
		this.yPos = yPos;
		collisionBound = new Rectangle(xPos, yPos, size, size);
	}
	
	public void repaint(Graphics g) {};
	
	public Rectangle getCollsionBound() {
		return collisionBound;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
}
