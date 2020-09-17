package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JOptionPane;

import config.Config;
import graphics.Display;
import threads.ProgramThread;

/**
 * This class is the GameOverState and is responsible for taking the name of the
 * player, appending their name and score into the highscore.txt file as well as
 * displaying the "game over" screen.
 */
public class GameOverState extends State {

	private boolean hasAcceptedNewScore = true;
	private Rectangle menuButton = new Rectangle(350, 600, 200, 75);

	public GameOverState(ProgramThread program) {
		super(program);
	}

	@Override
	public void update() {
		if (hasAcceptedNewScore) {
			String newAddedHighScore;
			newAddedHighScore = "\r\n" + JOptionPane.showInputDialog(this, "") + " " + GameState.getCurrentScore()
					+ "\r\n";
			addString("./src/highscores.txt", newAddedHighScore);
			hasAcceptedNewScore = false;
		}
	}

	@Override
	public void repaint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Config.WINDOW_SIZE_X, Config.WINDOW_SIZE_Y);
		BufferedImage background = Display.loadImage("/textures/theEndTest.png");
		g.drawImage(background, 0, 0, null);

		Font endFont = new Font("monospaced", Font.BOLD, 75);
		g.setColor(Color.GREEN);
		g.setFont(endFont);
		g.drawString("THE END", Config.BASE_POSITON_X / 2 + 100, Config.BASE_POSTION_Y / 6);

		Graphics2D g2 = (Graphics2D) g;
		Font buttonFont = new Font("monospaced", Font.BOLD, 30);
		g2.setFont(buttonFont);
		g2.drawString("MENU", menuButton.x + 45, menuButton.y + 50);
		g2.draw(menuButton);
	}

	private void addString(String highScoreFile, String playerName) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(highScoreFile, true));
			bufferedWriter.write(playerName);
			bufferedWriter.close();
		} catch (Exception e) {
			System.out.println("Something went wrong.");
		}
	}
}
