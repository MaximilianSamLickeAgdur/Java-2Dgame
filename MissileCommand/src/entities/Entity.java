package entities;

import java.awt.Graphics;

/**
 * An abstract class which contains some fundamental properties of an entity,
 * e.g. its position and ability to update and repaint itself.
 */
public abstract class Entity {

	private int xPos;
	private int yPos;

	public Entity(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public abstract void update();

	public abstract void repaint(Graphics g);

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public void setXPos(int xPos) {
		this.xPos = xPos;
	}

	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
}
