package buildings;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import graphics.Display;

/**
 * Subclass of building, responsible for repainting itself a given image.
 */
public class Base extends Building {

	public Base(int xPos, int yPos, int size) {
		super(xPos, yPos, size);
	}
	
	@Override
	public void repaint(Graphics g) {
		BufferedImage baseImage = Display.loadImage("/textures/testBase.png");
		g.drawImage(baseImage, getXPos(), getYPos(), null);
	}
}
