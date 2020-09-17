package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import states.StateManager;
import threads.ProgramThread;

/**
 * A class which implements the interfaces MouseListener and MouseMotionListener
 * in order to be able to use MouseClicks and MouseMovement. At the moment it
 * has responsibility for buttons being clicked also, this feature should exist
 * somewhere else.
 */
public class MyMouseListener implements MouseListener, MouseMotionListener {

	private boolean leftPressed;
	private int mouseX;
	private int mouseY;

	public void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}

	public boolean getLeftPressed() {
		return leftPressed;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = true;
		}

		if (StateManager.getState() == ProgramThread.getMenuState()) {
			if (mouseX > 350 && mouseX < 550 && mouseY > 200 && mouseY < 275) {
				StateManager.setState(ProgramThread.getChoiseOfMapState());
			}
			if (mouseX > 350 && mouseX < 550 && mouseY > 300 && mouseY < 375) {
				ProgramThread.getHighScoreState().loadHighScore();
				StateManager.setState(ProgramThread.getHighScoreState());
			}
			if (mouseX > 350 && mouseX < 550 && mouseY > 400 && mouseY < 475) {
				System.exit(1);
			}
		} else if (StateManager.getState() == ProgramThread.getGameOverState()) {
			if (mouseX > 350 && mouseX < 550 && mouseY > 600 && mouseY < 675) {
				StateManager.setState(ProgramThread.getMenuState());
			}
		} else if (StateManager.getState() == ProgramThread.getHighScoreState()) {
			if (mouseX > 350 && mouseX < 550 && mouseY > 600 && mouseY < 675) {
				StateManager.setState(ProgramThread.getMenuState());
			}
		} else if (StateManager.getState() == ProgramThread.getChoiseOfMapState()) {
			if (mouseX > 350 && mouseX < 550 && mouseY > 200 && mouseY < 275) {
				ProgramThread.getGameState().setCurrentMap("S");
				StateManager.setState(ProgramThread.getGameState());
			}
			if (mouseX > 350 && mouseX < 550 && mouseY > 300 && mouseY < 375) {
				ProgramThread.getGameState().setCurrentMap("N");
				StateManager.setState(ProgramThread.getGameState());
			}
			if (mouseX > 350 && mouseX < 550 && mouseY > 400 && mouseY < 475) {
				StateManager.setState(ProgramThread.getMenuState());
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}
}
