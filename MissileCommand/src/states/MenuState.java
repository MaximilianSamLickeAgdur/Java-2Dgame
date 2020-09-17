package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import config.Config;
import graphics.Display;
import threads.ProgramThread;

public class MenuState extends State {
	
	private Rectangle startButton = new Rectangle(350, 200, 200, 75);
	private Rectangle highScoreButton = new Rectangle(350, 300, 200, 75);
	private Rectangle exitButton = new Rectangle(350, 400, 200, 75);
		
	public MenuState(ProgramThread program) {
		super(program);
	}

	@Override
	public void update() {}

	@Override
	public void repaint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Config.WINDOW_SIZE_X, Config.WINDOW_SIZE_Y);
		BufferedImage background = Display.loadImage("../textures/handBackgroundTest.png");
		g.drawImage(background, 0, 0, null);
		
		Font headerFont = new Font("monospaced", Font.BOLD, 50);
		Font buttonFont = new Font("monospaced", Font.BOLD, 30);
		g.setColor(Color.GREEN);
		g.setFont(headerFont);
		g.drawString("MISSILE COMMAND", Config.BASE_POSITON_X/2, Config.BASE_POSTION_Y/6);	
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(buttonFont);
		g2.drawString("START", startButton.x+45, startButton.y+50);
		g2.drawString("HIGHSCORE", highScoreButton.x+22, highScoreButton.y+50);
		g2.drawString("EXIT", exitButton.x+55, exitButton.y+50);
		g2.draw(startButton);
		g2.draw(highScoreButton);
		g2.draw(exitButton);
	}
}
