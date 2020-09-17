package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import config.Config;
import threads.ProgramThread;

/**
 * This class is the ChoiseOfMapState. This class is only responsible to display
 * which choices the player has in terms of maps when starting the game.
 */
public class ChoiseOfMapState extends State {

	private Rectangle choise1Button = new Rectangle(350, 200, 200, 75);
	private Rectangle choise2Button = new Rectangle(350, 300, 200, 75);
	private Rectangle menuButton = new Rectangle(350, 400, 200, 75);

	public ChoiseOfMapState(ProgramThread program) {
		super(program);
	}

	@Override
	public void update() {
	}

	@Override
	public void repaint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Config.WINDOW_SIZE_X, Config.WINDOW_SIZE_Y);
		g.setColor(Color.GREEN.darker());
		Graphics2D g2 = (Graphics2D) g;
		Font buttonFont = new Font("monospaced", Font.BOLD, 30);
		g.setFont(buttonFont);

		g2.drawString("NEW YORK", choise1Button.x + 45, choise1Button.y + 50);
		g2.drawString("SAN FRANSISCO", choise2Button.x + 22, choise2Button.y + 50);
		g2.drawString("BACK", menuButton.x + 55, menuButton.y + 50);
		g2.draw(choise1Button);
		g2.draw(choise2Button);
		g2.draw(menuButton);
	}
}
