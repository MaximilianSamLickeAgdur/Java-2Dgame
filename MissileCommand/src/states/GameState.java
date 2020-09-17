package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import buildings.Base;
import buildings.City;
import config.Config;
import entities.EnemyBomber;
import entities.EnemyMissile;
import entities.FriendlyMissile;
import entities.Missile;
import threads.ProgramThread;

/**
 * This class is the GameState. It has the primary components of the game
 * itself, every update and repaint method of all objects when playing are in
 * there, as well as the loading of the cities which are to be defended in the
 * game.
 * 
 * It also creates a lot of secondary threads which are used to stall some
 * aspects of the game for a fixed time period. Enemy generation is however not
 * made here.
 */
public class GameState extends State {

	private Rectangle panelBar = new Rectangle(0, (int) (Config.WINDOW_SIZE_Y * 0.95), Config.WINDOW_SIZE_X,
			(int) (Config.WINDOW_SIZE_Y * 0.05));

	private static int lifesLeft = Config.STARTING_LIVES;
	private static int score = Config.STARTING_SCORE;
	private int missilesLeft = Config.STARTING_MISSILES;
	private int currentWave = Config.STARTING_WAVE;
	private String currentMap = "N";
	private int bonusCounterMultiplier1 = 1;
	private int bonusCounterMultiplier2 = 1;

	private ArrayList<EnemyMissile> enemyMissiles = new ArrayList<EnemyMissile>();
	private ArrayList<EnemyBomber> enemyBombers = new ArrayList<EnemyBomber>();
	private ArrayList<FriendlyMissile> friendlyMissiles = new ArrayList<FriendlyMissile>();
	private ArrayList<City> cities = new ArrayList<City>();
	private ArrayList<Base> bases = new ArrayList<Base>();

	private ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();

	public GameState(ProgramThread program) {
		super(program);

		cities.add(new City((int) (0.1 * Config.WINDOW_SIZE_X),
				(int) (Config.CITY_POSITION_Y - Config.WINDOW_SIZE_Y * 0.05)));
		cities.add(new City((int) (0.2 * Config.WINDOW_SIZE_X),
				(int) (Config.CITY_POSITION_Y - Config.WINDOW_SIZE_Y * 0.05)));
		cities.add(new City((int) (0.3 * Config.WINDOW_SIZE_X),
				(int) (Config.CITY_POSITION_Y - Config.WINDOW_SIZE_Y * 0.05)));
		cities.add(new City((int) (0.6 * Config.WINDOW_SIZE_X),
				(int) (Config.CITY_POSITION_Y - Config.WINDOW_SIZE_Y * 0.05)));
		cities.add(new City((int) (0.8 * Config.WINDOW_SIZE_X),
				(int) (Config.CITY_POSITION_Y - Config.WINDOW_SIZE_Y * 0.05)));
		
		bases.add(new Base(Config.BASE_POSITON_X, (int) (Config.BASE_POSTION_Y * 0.95), Config.HITBOX_SIZE_BASE));
	}

	@Override
	public synchronized void update() {
		spawnFriendlyMissile();
		updateEnemyMissiles();
		collisionFriendlyMissileIsOutOfBounds();
		collisonBomberIsOutOfBounds();
		collisonFriendlyMissileEnemyBomber();
		collisonFriendlyMissilesEnemyMissiles();
		collisonEnemyMissilesCities();

		if (lifesLeft <= 0) {
			StateManager.setState(ProgramThread.getGameOverState());
		}

		if (score > 1000 * bonusCounterMultiplier1) {
			missilesLeft += 5;
			bonusCounterMultiplier1++;
		}

		if (score > 10000 * bonusCounterMultiplier2) {
			missilesLeft += 20;
			bonusCounterMultiplier2++;
		}
	}

	@Override
	public synchronized void repaint(Graphics g) {
		repaintStatusBar(g);
		repaintGameObjects(g);
	}

	private double calculatedX(int tempX, int tempY) {
		return Config.FRIENDLY_MISSILE_VELOCITY * tempX / Math.sqrt(Math.pow(tempX, 2) + Math.pow(tempY, 2));
	}

	private double calculatedY(int tempX, int tempY) {
		return Config.FRIENDLY_MISSILE_VELOCITY * tempY / Math.sqrt(Math.pow(tempX, 2) + Math.pow(tempY, 2));
	}

	public synchronized ArrayList<EnemyMissile> getEnemyMissiles() {
		return enemyMissiles;
	}

	public synchronized ArrayList<EnemyBomber> getEnemyBomers() {
		return enemyBombers;
	}

	public synchronized ArrayList<FriendlyMissile> getFriendlyMissiles() {
		return friendlyMissiles;
	}

	public void setCurrentWave(int currentWave) {
		this.currentWave = currentWave;
	}

	public int getCurrentWave() {
		return currentWave;
	}

	public int getMissilesLeft() {
		return missilesLeft;
	}

	public static int getCurrentScore() {
		return score;
	}

	public void setMissilesLeft(int missilesLeft) {
		this.missilesLeft = missilesLeft;
	}

	public void setCurrentMap(String currentMap) {
		this.currentMap = currentMap;
	}

	public String getCurrentMap() {
		return currentMap;
	}

	private void repaintStatusBar(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.DARK_GRAY);
		g2.fill(panelBar);
		Font font = new Font("monospaced", Font.BOLD, 20);
		g2.setColor(Color.GREEN.darker());
		g2.setFont(font);

		g2.drawString("Cities left: " + lifesLeft, (int) (Config.WINDOW_SIZE_X * 0.02),
				(int) (Config.WINDOW_SIZE_Y * 0.98));
		g2.drawString("Current Wave: " + currentWave, (int) (Config.WINDOW_SIZE_X * 0.21),
				(int) (Config.WINDOW_SIZE_Y * 0.98));
		g2.drawString("Missiles left: " + missilesLeft, (int) (Config.WINDOW_SIZE_X * 0.42),
				(int) (Config.WINDOW_SIZE_Y * 0.98));
		g2.drawString("Current Score: " + score, (int) (Config.WINDOW_SIZE_X * 0.65),
				(int) (Config.WINDOW_SIZE_Y * 0.98));

		if (currentWave == 0) {
			Font waveFont = new Font("monospaced", Font.BOLD, 35);
			g2.setColor(Color.GREEN.darker());
			g2.setFont(waveFont);
			g2.drawString("INCOMING ENEMY ICBM:S.", (int) (Config.WINDOW_SIZE_X * 0.25),
					(int) (Config.WINDOW_SIZE_Y * 0.3));
			g2.drawString("DEFEND THE BUNKERS.", (int) (Config.WINDOW_SIZE_X * 0.28),
					(int) (Config.WINDOW_SIZE_Y * 0.35));
		}
	}

	private void repaintGameObjects(Graphics g) {
		for (Missile missile : enemyMissiles) {
			missile.repaint(g);
		}

		for (EnemyBomber bomber : enemyBombers) {
			bomber.repaint(g);
		}

		for (Missile missile : friendlyMissiles) {
			missile.repaint(g);
		}

		for (Base base : bases) {
			base.repaint(g);
		}

		for (City city : cities) {
			city.repaint(g);
		}

	}

	private void updateEnemyMissiles() {
		for (int i = 0; i < enemyMissiles.size(); i++) {
			EnemyMissile enemy = enemyMissiles.get(i);
			enemy.update();
			if (enemy.isOutOfBounds(enemy.getStopX(), enemy.getStopY())) {
				enemyMissiles.remove(enemy);
			}
		}
	}

	private void spawnFriendlyMissile() {
		// Calculations for Friendly Missiles
		if (missilesLeft > 0) {
			if (getProgramThread().getMyKeyboardListener().getSpacedUsed()) {
				int tempStopX = getProgramThread().getMyMouseListener().getMouseX();
				int tempStopY = getProgramThread().getMyMouseListener().getMouseY();
				FriendlyMissile friendlyMissile = new FriendlyMissile(Config.BASE_POSITON_X,
						(int) (Config.BASE_POSTION_Y * 0.95), tempStopX, tempStopY, Config.HITBOX_SIZE_FRIENDLY);
				missilesLeft--;
				friendlyMissile.setdX(calculatedX(tempStopX - Config.BASE_POSITON_X,
						(int) (tempStopY - Config.BASE_POSTION_Y * 0.95)));
				friendlyMissile.setdY(calculatedY(tempStopX - Config.BASE_POSITON_X,
						(int) (tempStopY - Config.BASE_POSTION_Y * 0.95)));
				getFriendlyMissiles().add(friendlyMissile);
				getProgramThread().getMyMouseListener().setLeftPressed(false);
			}
		}

	}

	private void collisonFriendlyMissileEnemyBomber() {
		// Calculations for collision between Friendly Missile and Bomber.
		for (int i = 0; i < friendlyMissiles.size(); i++) {
			FriendlyMissile friendly = friendlyMissiles.get(i);

			for (int j = 0; j < enemyBombers.size(); j++) {
				EnemyBomber bomber = enemyBombers.get(j);

				bomber.setCollisionBound(bomber.getXPos(), bomber.getYPos(), Config.HITBOX_SIZE_BOMBER);
				if (friendly.getCollisionBound().intersects(bomber.getCollisionBound())) {
					score += 500;
					getEnemyBomers().remove(bomber);
					friendly.setHasExploded(true);
					timer.schedule(new Runnable() {
						@Override
						public void run() {
							getFriendlyMissiles().remove(friendly);
						}
					}, Config.EXPLOSION_TIMER_MISSILE, TimeUnit.MILLISECONDS);
				}
			}
		}
	}

	private void collisonBomberIsOutOfBounds() {
		// Calculations for Bomber and Calculation for collision between Bomber and
		// isOutOfBounds.
		for (int i = 0; i < enemyBombers.size(); i++) {
			EnemyBomber bomber = enemyBombers.get(i);
			bomber.update();
			if (bomber.isOutOfBounds(Config.ENEMY_BOMBER_DESPAWN_POS_X)) {
				enemyBombers.remove(bomber);
			}
		}
	}

	private void collisonEnemyMissilesCities() {
		// Calculations for collision between Cities and Enemy Missiles.
		for (int i = 0; i < enemyMissiles.size(); i++) {
			EnemyMissile enemy = enemyMissiles.get(i);
			enemy.setCollisionBound((enemy.getXPos() - Config.HITBOX_SIZE_ENEMY / 2),
					(enemy.getYPos() - Config.HITBOX_SIZE_ENEMY / 2), Config.HITBOX_SIZE_ENEMY);

			for (int j = 0; j < cities.size(); j++) {
				City city = cities.get(j);
				if (enemy.getCollisionBound().intersects(city.getCollsionBound())) {
					lifesLeft--;
					cities.remove(city);
					enemy.setHasExploded(true);
					timer.schedule(new Runnable() {
						@Override
						public void run() {
							// TODO: Remember that this has to use the synchronized getter, else the program
							// will crash due to concurrency errors.
							getEnemyMissiles().remove(enemy);
						}
					}, Config.EXPLOSION_TIMER_MISSILE, TimeUnit.MILLISECONDS);
				}
			}
		}
	}

	private void collisonFriendlyMissilesEnemyMissiles() {
		// Calculations for collision between Friendly Missiles and Enemy Missiles.
		for (int i = 0; i < friendlyMissiles.size(); i++) {
			FriendlyMissile friendly = friendlyMissiles.get(i);
			for (int j = 0; j < enemyMissiles.size(); j++) {
				EnemyMissile enemy = enemyMissiles.get(j);

				friendly.setCollisionBound((friendly.getXPos() - Config.HITBOX_SIZE_FRIENDLY / 2),
						(friendly.getYPos() - Config.HITBOX_SIZE_FRIENDLY / 2), Config.HITBOX_SIZE_FRIENDLY);
				enemy.setCollisionBound((enemy.getXPos() - Config.HITBOX_SIZE_ENEMY / 2),
						(enemy.getYPos() - Config.HITBOX_SIZE_ENEMY / 2), Config.HITBOX_SIZE_ENEMY);

				if (friendly.getCollisionBound().intersects(enemy.getCollisionBound())) {
					score += 100;
					enemyMissiles.remove(enemy);
					friendly.setHasExploded(true);
					timer.schedule(new Runnable() {
						@Override
						public void run() {
							getFriendlyMissiles().remove(friendly);
						}
					}, Config.EXPLOSION_TIMER_MISSILE, TimeUnit.MILLISECONDS);
				}
			}
		}
	}

	private void collisionFriendlyMissileIsOutOfBounds() {
		// Calculations for collision between friendly Missile and isOutOfBounds
		for (int i = 0; i < friendlyMissiles.size(); i++) {
			FriendlyMissile friendly = friendlyMissiles.get(i);
			friendly.update();
			if (friendly.isOutOfBounds(friendly.getStopX(), friendly.getStopY())) {

				friendly.setHasExploded(true);
				timer.schedule(new Runnable() {
					@Override
					public void run() {
						getFriendlyMissiles().remove(friendly);
					}
				}, Config.EXPLOSION_TIMER_MISSILE, TimeUnit.MILLISECONDS);
			}
		}
	}
}
