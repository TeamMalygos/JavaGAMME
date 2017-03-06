package constants;


public class Constants {

	//Display
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
	
    // Player initialization constants - all of these are sample values.
    public static final double DAMAGE_REDUCE_RATE = 0.1;
    public static final double INITIAL_HEALTH = 500;
    public static final double INITIAL_MANA = 100;
    public static final double INITIAL_ARMOR = 10;
    public static final double INITIAL_HEALTH_REGENERATION_RATE = 0.01;
    public static final double INITIAL_MANA_REGENERATION_RATE = 0.02;
    public static final int INITIAL_PLAYER_LEVEL = 1;
    public static final int INITIAL_PLAYER_EXPERIENCE = 1;
    public static final int INITIAL_LEVEL_TOTAL_EXPERIENCE = 200;
    public static final int REGENERATION_TIME = 100;
    
    // Player level up values
    public static final double TOTAL_LEVEL_EXPERIENCE_INCREMENT_RATE = 2;
    public static final double LEVEL_UP_HEALTH_INCREASE = 50;
    public static final double LEVEL_UP_MANA_INCREASE = 10;
    
    //Player
    public static final int PLAYER_START_X = 25;
    public static final int PLAYER_START_Y = 200;
    public static final int PLAYER_COLLISION_WIDTH = 25;
    public static final int PLAYER_COLLISION_HEIGHT = 60;
    
    //Player physics
    public static final double PLAYER_GRAVITY = 2;
    public static final double PLAYER_TERMINAL_VELOCITY = 4.0;
    public static final double PLAYER_ACCELERATION = 0.9;
    public static final double PLAYER_STOP_JUMP = 0.5;
    public static final double PLAYER_JUMP = -16;
    public static final double PLAYER_DEACCELERATION = 1.4;
    public static final double PLAYER_MAXIMUM_SPEED = 4;
    
    //Menu components default size
    public static final int MENU_BUTTON_WIDTH = 209;
    public static final int MENU_BUTTON_HEIGHT = 61;
	public static final int LEVEL_CARD_HEIGHT = 200;
	public static final int LEVEL_CARD_WIDTH = 100;
    
    //Projectiles
    public static final int PROJECTILE_KEY_WIDTH = 9;
    public static final int PROJECTILE_KEY_HEIGHT = 9;
    
    
    //Object dimensions
    public static final int DIAMOND_HEIGHT = 33;
    public static final int DIAMOND_WIDTH = 33;
    public static final int PLAYER_WIDTH = 32;
    public static final int PLAYER_HEIGHT = 63;
    public static final int BEER_WIDTH = 10;
    public static final int BEER_HEIGHT = 32;
    
    //HUD dimensions
    public static final int HEALTH_BAR_WIDTH = 54;
    public static final int HEALTH_BAR_HEIGHT = 55;
    
    //HUD Position
    public static final int HEALTH_BAR_X = Constants.WIDTH-60;
    public static final int HEALTH_BAR_Y = 10;
    public static final int DIAMONDS_COUNTER_X = 4;
    public static final int DIAMONDS_COUNTER_Y = 4;    
	public static final int DIAMONDS_COUNTER_HEIGHT = 34;
	public static final int DIAMONDS_COUTNER_WIDTH = 117;
    
}
