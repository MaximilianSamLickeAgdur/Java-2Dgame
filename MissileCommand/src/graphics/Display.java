package graphics;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import config.Config;

/**
 * This class is the main frame used for the game. It also has the Canvas which
 * is used to paint onto in order to render the game. It has some ordinary
 * boilerplate code for JFrames. This class also has an important method which
 * loads images used throughout the game.
 */
@SuppressWarnings("serial")
public class Display extends JFrame {

	private String title = Config.GAME_TITLE;
	private int width = Config.WINDOW_SIZE_X;
	private int height = Config.WINDOW_SIZE_Y;
	private Canvas canvas;

	public Display() {
		this.setSize(width, height);
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setFocusable(false);
		this.add(canvas, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public static BufferedImage loadImage(String imagePath) {
		try {
			return ImageIO.read(Display.class.getResource(imagePath));
		} catch (Exception e) {
			return null;
		}
	}
}
