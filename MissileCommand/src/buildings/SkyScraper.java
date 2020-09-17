package buildings;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import graphics.Display;

/**
 * Subclass of building, responsible for repainting itself a given image.
 */
public class SkyScraper extends Building{

	public SkyScraper(int xPos, int yPos, int size) {
		super(xPos, yPos, size);
	}

	@Override
	public void repaint(Graphics g) {
		BufferedImage buildingImage = Display.loadImage("/textures/skyScraper.png");
		g.drawImage(buildingImage, getXPos(), getYPos(), null);
	}
}
