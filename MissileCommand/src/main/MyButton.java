package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import input.MyMouseListener;
import states.State;
import states.StateManager;

public class MyButton {

	private int xPos, yPos, width, height;
	private String title;
	private State state;
	private MyMouseListener myMouseListener;

	public MyButton(int xPos, int yPos, int width, int height, MyMouseListener myMouseListener, State state, String title) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.state = state;
		this.title = title;
		this.myMouseListener = myMouseListener;
	}

	public void update() {
		if (myMouseListener.getMouseX() > xPos && myMouseListener.getMouseX() < xPos + width && myMouseListener.getMouseY() > yPos && myMouseListener.getMouseY() < yPos + height && myMouseListener.getLeftPressed()) {
			StateManager.setState(state);
		}
	}

	public void repaint(Graphics g) {
		g.setColor(Color.GREEN);
		g.setFont(new Font("monospaced", Font.BOLD, 30));
		g.drawRect(xPos, yPos, width, height);
		g.drawString(title, xPos + width / 4, yPos + height / 2);
	}
}
