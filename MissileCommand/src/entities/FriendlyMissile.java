package entities;

import java.awt.Color;
import java.awt.Graphics;

import config.Config;

/**
 * A subclass of missile which specifies its own isOutOfBounds method and update method, making it able to explode at a certain destination.
 */
public class FriendlyMissile extends Missile {
	
	private double  dx;
	private double  dy;
	private boolean hasExploded = false;
	
	public FriendlyMissile(int xPos, int yPos, int stopX, int stopY, int size) {
		super(xPos, yPos, stopX, stopY, size);
	}
	
	@Override
	public void update() {
		if (hasExploded) {
			getExplosion().update();
		} else {
			this.move(dx, dy);
		}
	}

	@Override
	public boolean isOutOfBounds(int stopX, int stopY) {
		return  getYPos() <= stopY ||  (getYPos() == Config.WINDOW_SIZE_Y + 10 ||  getYPos() == Config.WINDOW_SIZE_Y - 10) ;
	}
	
	@Override
	public void repaint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(getXPos(), getYPos(), 5, 5);
		g.drawLine(this.getTempX(), this.getTempY(), getXPos(), getYPos());		
		if (hasExploded) {
			getExplosion().playAnimation(g, getXPos() - 50, getYPos() - 50);
		}
	}
	
	public void setdX(double dx) {
		this.dx = dx;
	}
	
	public void setdY(double dy) {
		this.dy = dy;
	}
	
	public void setHasExploded(boolean hasExploded) {
		this.hasExploded = hasExploded;
	}
}
