package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import config.Config;

/**
 * A class made such that it can play an animation by flipping through
 * bufferedImages one at a time, at a certain speed set in the config.
 */
public class Animations {

	private int updateSpeed = Config.ANIMATION_SPEED;
	private long lastTime = System.currentTimeMillis();
	private int currentFrameIndex = 0;
	private long timer = 0;
	private BufferedImage[] frames;

	public Animations(BufferedImage[] frames) {
		this.frames = frames;
	}

	public void update() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if (timer > updateSpeed) {
			timer = 0;
			currentFrameIndex++;
			if (currentFrameIndex >= frames.length) {
				currentFrameIndex = 0;
			}
		}
	}

	public BufferedImage getCurrentFrame() {
		return frames[currentFrameIndex];
	}

	public void playAnimation(Graphics g, int xPos, int yPos) {
		g.drawImage(getCurrentFrame(), xPos, yPos, null);
	}
}
