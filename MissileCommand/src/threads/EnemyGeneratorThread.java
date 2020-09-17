package threads;

import config.Config;
import entities.EnemyBomber;
import entities.EnemyMissile;
import states.GameState;

/**
 * This class is a thread which is the primary spawner of enemies in the game.
 * When the arrayLists containing enemies are empty, this will sleep for 5
 * seconds and then spawn new enemies. The number of enemies depend on which
 * round of the game it is.
 */
public class EnemyGeneratorThread implements Runnable {

	private GameState gameState;

	public EnemyGeneratorThread(GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public void run() {

		while (true) {

			boolean missilesEmpty = gameState.getEnemyMissiles().isEmpty();
			boolean bombersEmpty = gameState.getEnemyBomers().isEmpty();
			int currentWave = gameState.getCurrentWave();
			int missilesLeft = gameState.getMissilesLeft();

			if (currentWave == 0) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			if (missilesEmpty && bombersEmpty) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				gameState.setCurrentWave(currentWave + 1);
				gameState.setMissilesLeft(missilesLeft + 5);
				for (int i = 0; i < currentWave + 1; i++) {
					gameState.getEnemyMissiles()
							.add(new EnemyMissile((int) (Math.random() * 500 + 1), Config.ENEMY_MISSILE_STARTING_POS_Y,
									Config.WINDOW_SIZE_X, (int) (Config.WINDOW_SIZE_Y * 0.95),
									Config.HITBOX_SIZE_ENEMY));
				}

				if (currentWave > 4) {
					for (int i = 0; i < (int) currentWave * 2 / 5; i++) {
						gameState.getEnemyBomers().add(new EnemyBomber(Config.ENEMY_BOMBER_STARTING_POS_X,
								(int) (Math.random() * 450 + 50), Config.HITBOX_SIZE_BOMBER));
					}
				}
			}
		}
	}
}
