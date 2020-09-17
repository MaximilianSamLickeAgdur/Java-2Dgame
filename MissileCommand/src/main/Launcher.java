package main;

import states.GameState;
import threads.EnemyGeneratorThread;
import threads.ProgramThread;

/**
 * Class required for launching the game. This class contains the main method, which starts the program and the enemyGenerator.
 */
public class Launcher {
	
	public static void main(String[] args) {
		ProgramThread program = new ProgramThread();
		Thread programThread = new Thread(program);
		program.setRunning(true);
		programThread.start();
		
		EnemyGeneratorThread generator = new EnemyGeneratorThread((GameState) ProgramThread.getGameState());
		Thread enemyThread = new Thread(generator);
		enemyThread.start();
	}
}
