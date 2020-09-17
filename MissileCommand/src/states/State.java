package states;

import java.awt.Graphics;

import threads.ProgramThread;

/**
 * This class holds certain methods and variables all States should have. This
 * is used to avoid duplicate code and use polymorphism. The class is abstract
 * since it will never be instantiated.
 */
public abstract class State {

	private ProgramThread program;

	public State(ProgramThread program) {
		this.program = program;
	}

	public abstract void update();

	public abstract void repaint(Graphics g);

	public ProgramThread getProgramThread() {
		return program;
	}
}
