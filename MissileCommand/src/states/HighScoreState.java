package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import threads.ProgramThread;

/**
 * This class extends State, but also has the responsibility to have the score
 * board loaded, sorted and printed onto the Canvas.
 */
public class HighScoreState extends State {

	private Map<String, Integer> highScores = new HashMap<String, Integer>();
	private Rectangle menuButton = new Rectangle(350, 600, 200, 75);

	public HighScoreState(ProgramThread program) {
		super(program);
	}

	@Override
	public void update() {}

	@Override
	public void repaint(Graphics g) {
		g.setColor(Color.GREEN);
		g.setFont(new Font("monospaced", Font.PLAIN, 50));
		g.drawString("HIGHSCORE", 350, 150);
		int lineFixerCounter = 0;
		int scoreCounter = 1;
		List<String> sortedList = highScores.entrySet().stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).map(entry -> entry.getKey())
				.collect(Collectors.toList());
		for (int i = 0; i < 5; i++) {
			String key = sortedList.get(i);
			g.drawString(scoreCounter + ":" + key + ":" + highScores.get(key), 300, 200 + lineFixerCounter);
			lineFixerCounter += 50;
			scoreCounter++;
		}
		Graphics2D g2 = (Graphics2D) g;
		Font buttonFont = new Font("monospaced", Font.BOLD, 30);
		g2.setFont(buttonFont);
		g2.drawString("BACK", menuButton.x + 45, menuButton.y + 50);
		g2.draw(menuButton);

	}

	public void loadHighScore() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("MissileCommand/src/highscores.txt"));
			while (scanner.hasNext()) {
				String name = scanner.next();
				int score = scanner.nextInt();
				highScores.put(name, score);
			}
		} catch (Exception e) {
			System.out.println("Could not scan file.");
		} finally {
			scanner.close();
		}
	}
}
