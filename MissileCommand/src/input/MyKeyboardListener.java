package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A class which implements KeyListener, used to detect usage of the key
 * "space".
 */
public class MyKeyboardListener implements KeyListener {

	private boolean spacedUsed;

	public boolean getSpacedUsed() {
		return spacedUsed;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			spacedUsed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			spacedUsed = false;
		}
	}
}
