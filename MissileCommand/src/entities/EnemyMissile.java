package entities;

import java.awt.Color;
import java.awt.Graphics;
import entities.Missile;

/**
 * A subclass of missile which specifies its own isOutOfBounds method and update
 * method, making it able to explode at a certain destination. Also its
 * direction is randomly generated as should be.
 */
public class EnemyMissile extends Missile {

	private int dx = (int) (Math.random() * 3 - 1);
	private int dy = (int) (Math.random() * 4 + 1);
	private boolean hasExploded = false;

	public EnemyMissile(int xPos, int yPos, int stopX, int stopY, int size) {
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
	public void repaint(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(getXPos(), getYPos(), 5, 5);
		g.drawLine(this.getTempX(), this.getTempY(), getXPos(), getYPos());
		if (hasExploded) {
			getExplosion().playAnimation(g, getXPos() - 50, getYPos() - 50);
		}
	}

	@Override
	public boolean isOutOfBounds(int stopX, int stopY) {
		return getXPos() > stopX || getYPos() > stopY;
	}

	public void setHasExploded(boolean hasExploded) {
		this.hasExploded = hasExploded;
	}
}
