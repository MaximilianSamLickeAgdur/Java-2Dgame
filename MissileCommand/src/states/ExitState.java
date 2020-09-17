package states;

import java.awt.Graphics;

import threads.ProgramThread;

/**
 * This class is the ExitState class. It is only used to quit the game and
 * shutdown the application.
 */
public class ExitState extends State {

	public ExitState(ProgramThread program) {
		super(program);
	}

	@Override
	public void update() {
		System.exit(1);
	}

	@Override
	public void repaint(Graphics g) {
	}
}
