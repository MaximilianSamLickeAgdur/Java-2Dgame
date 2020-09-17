package buildings;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import config.Config;
import graphics.Display;

/**
 * Subclass of building, responsible for repainting itself a given image.
 */
public class City extends Building {

	public City(int xPos, int yPos) {
		super(xPos, yPos, Config.HITBOX_SIZE_CITY);
	}

	@Override
	public void repaint(Graphics g) {
		BufferedImage buildingImage = Display.loadImage("/textures/testBuilding.png");
		g.drawImage(buildingImage, getXPos(), getYPos(), null);
	}
}
