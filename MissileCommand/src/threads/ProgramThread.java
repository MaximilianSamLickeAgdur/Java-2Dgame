package threads;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import config.Config;
import graphics.Display;
import input.MyKeyboardListener;
import input.MyMouseListener;
import states.ChoiseOfMapState;
import states.ExitState;
import states.GameOverState;
import states.GameState;
import states.HighScoreState;
import states.MenuState;
import states.StateManager;

/**
 * The class which is the main programThread, i.e. the class implements runnable
 * and is the first thread to be started. This also initializes the state, the
 * display, and adds listeners to the canvas of the display in order to detect
 * user input.
 * 
 * This class also contains the game loop, which is run constantly as the game
 * is being run. In addition this also loads all graphics components the game
 * needs, and creates the bufferStrategy from the canvas of the display.
 */
public class ProgramThread implements Runnable {

	private Display display;
	private boolean running = false;
	private BufferStrategy bufferStrategy = null;
	private Graphics g;
	private static GameState gameState;
	private static HighScoreState highScoreState;
	private static MenuState menuState;
	private static GameOverState gameOverState;
	private static ChoiseOfMapState choiseOfMapState;
	private static ExitState exitState;
	private MyMouseListener myMouseListener;
	private MyKeyboardListener myKeyboradListener;

	public ProgramThread() {
		display = new Display();
		myMouseListener = new MyMouseListener();
		myKeyboradListener = new MyKeyboardListener();
		display.getCanvas().addMouseListener(myMouseListener);
		display.getCanvas().addMouseMotionListener(myMouseListener);
		display.addKeyListener(myKeyboradListener);

		gameState = new GameState(this);
		highScoreState = new HighScoreState(this);
		choiseOfMapState = new ChoiseOfMapState(this);
		gameOverState = new GameOverState(this);
		exitState = new ExitState(this);
		menuState = new MenuState(this);
		highScoreState.loadHighScore();
		StateManager.setState(menuState);
	}

	private void update() {
		StateManager.getState().update();
	}

	private void repaint() {
		bufferStrategy = display.getCanvas().getBufferStrategy();
		if (bufferStrategy == null) {
			display.getCanvas().createBufferStrategy(3);
		} else {
			g = bufferStrategy.getDrawGraphics();
			g.clearRect(0, 0, Config.WINDOW_SIZE_X, Config.WINDOW_SIZE_Y);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Config.WINDOW_SIZE_X, Config.WINDOW_SIZE_Y);

			if (gameState.getCurrentMap().equals("N")) {
				BufferedImage citySkyline = Display.loadImage("/textures/testBackground2.png");
				
				g.drawImage(citySkyline, 50, (int) (1000 - 192 - Config.BASE_POSTION_Y * 0.05), null);
			} else if (gameState.getCurrentMap().equals("S")) {
				BufferedImage citySkyLine2 = Display.loadImage("/textures/skyLine2.png");
				g.drawImage(citySkyLine2, 0, (int) (1000 - 192 - Config.BASE_POSTION_Y * 0.45), null);
			}

			StateManager.getState().repaint(g);
			bufferStrategy.show();
			g.dispose();
		}
	}

	@Override
	public void run() {
		double ms = 1000.0 / Config.FRAMES_PER_SECOND;
		while (running) {
			long lastTime = System.currentTimeMillis();
			update();
			repaint();
			long timer = System.currentTimeMillis() - lastTime;
			Toolkit.getDefaultToolkit().sync();

			try {
				Thread.sleep((long) Math.max(ms - timer, 0));
			} catch (InterruptedException e) {
				System.out.println("Game loop timer error.");
			}
		}
	}

	public MyMouseListener getMyMouseListener() {
		return myMouseListener;
	}

	public MyKeyboardListener getMyKeyboardListener() {
		return myKeyboradListener;
	}

	public Display getDisplay() {
		return display;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public static GameState getGameState() {
		return gameState;
	}

	public static HighScoreState getHighScoreState() {
		return highScoreState;
	}

	public static MenuState getMenuState() {
		return menuState;
	}

	public static GameOverState getGameOverState() {
		return gameOverState;
	}

	public static ChoiseOfMapState getChoiseOfMapState() {
		return choiseOfMapState;
	}

	public static ExitState getExitState() {
		return exitState;
	}
}
