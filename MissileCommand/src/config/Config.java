package config;
/**
 * Class with constant value variables for configuration purposes of the program. 
 */
public class Config {
	
	//PROGRAM
	public static final double FRAMES_PER_SECOND = 60.0;
	public static final int ANIMATION_SPEED = 50;
	
	//DISPLAY
	public static final String GAME_TITLE = "Missile Command";
	public static final int WINDOW_SIZE_X = 1000;
	public static final int WINDOW_SIZE_Y = 1000;
	public static final int BASE_POSITON_X = 475;
	public static final int BASE_POSTION_Y = 965;
	public static final int CITY_POSITION_Y = 950;
	
	//GAME MECHANICS
	public static final int FRIENDLY_MISSILE_VELOCITY = 15;
	public static final int STARTING_LIVES = 5;
	public static final int STARTING_SCORE = 0;
	public static final int STARTING_MISSILES = 50;
	public static final int EXPLOSION_TIMER_MISSILE = 410;
	public static final int STARTING_WAVE = 20;
	
	public static final int HITBOX_SIZE_FRIENDLY = 80;
	public static final int HITBOX_SIZE_ENEMY = 10;
	public static final int HITBOX_SIZE_CITY = 75;
	public static final int HITBOX_SIZE_BASE = 60;
	public static final int HITBOX_SIZE_BOMBER = 40;
	public static final int HITBOX_SIZE_SKYSCRAPER = 100;
	
	public static final int ENEMY_MISSILE_STARTING_POS_Y = -150;
	public static final int ENEMY_BOMBER_STARTING_POS_X = 1200;
	public static final int ENEMY_BOMBER_DESPAWN_POS_X = -100;
}
