package constants;

import java.awt.Color;

public class Constants {

	//Display
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
	public static final int BACKGROUND_Y = 0;
	public static final int BACKGROUND_X = 0;
    
    // Player initialization constants - all of these are sample values.
    public static final double INITIAL_DAMAGE = 20;
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

    // Spells
    public static final int INITIAL_SPELL_LEVEL = 1;
    public static final String INSUFFICIENT_MANA = "Insufficient mana";
    public static final String INSUFFICIENT_POINTS = "Insufficient points";
    public static final String HEAL_SPELL_NAME = "Heal";
    public static final int HEAL_SPELL_INITIAL_MANA_REQUIRED = 40;
    public static final int HEAL_SPELL_INITIAL_HEAL_AMOUNT = 100;
    public static final int HEAL_SPELL_LEVEL_UP_AMOUNT_INCREASE = 50;
    public static final String FIREWALL_SPELL_NAME = "Firewall";
    public static final int FIREWALL_SPELL_INITIAL_MANA_REQUIRED = 50;
    public static final double FIREWALL_SPELL_INITIAL_ARMOR_INCREASE = 100;
    public static final double FIREWALL_SPELL_LEVEL_UP_AMOUNT_INCREASE = 20;
    public static final int FIREWALL_SPELL_INITIAL_ACTIVE_TIME = 300;
    public static final int FIREWALL_SPELL_LEVEL_UP_ACTIVE_TIME_INCREASE = 50;
    public static final String HARDCORE_ALGORITHM_SPELL_NAME = "HardcoreAlgorithm";
    public static final int HARDCORE_ALGORITHM_SPELL_INITIAL_MANA_REQUIRED = 60;
    public static final double HARDCORE_ALGORITHM_SPELL_INITIAL_DAMAGE_INCREASE = 20;
    public static final int HARDCORE_ALGORITHM_SPELL_INITIAL_ACTIVE_TIME = 300;
    public static final double HARDCORE_ALGORITHM_SPELL_LEVEL_UP_AMOUNT_INCREASE = 20;
    public static final int HARDCORE_ALGORITHM_SPELL_LEVEL_UP_ACTIVE_TIME_INCREASE = 50;


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
    public static final int MENU_BUTTON_WIDTH = 192;
    public static final int MENU_BUTTON_HEIGHT = 56;
	public static final int LEVEL_CARD_HEIGHT = 200;
	public static final int LEVEL_CARD_WIDTH = 100;
    
	//Menu components - names
	public static final String BUTTON_START = "Start";
	public static final String BUTTON_QUIT = "Exit";
	public static final String BUTTON_RESUME = "Resume";
	public static final String BUTTON_LOAD = "Load";
	
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
    
	//DIRS
	public static final String DIRECTORY_PLAYER_SERIALIZATION = "/resources/players";
	
	//PlayersList
	public static final int PLAYERS_LIST_WIDTH = 500;
	public static final int PLAYERS_LIST_HEIGHT = 344;
	public static final int PLAYERS_LIST_Y = HEIGHT/2 - PLAYERS_LIST_HEIGHT / 2;
	public static final int PLAYERS_LIST_X = WIDTH/2 - PLAYERS_LIST_WIDTH / 2;
	public static final Color RADIAN_COLOR_1 = new Color(220f / 255, 253f / 255, 235f / 255, 0.3f);
	public static final Color RADIAN_COLOR_2 = new Color(31f / 255, 243f / 255, 135f / 255, 0.2f);
	public static final Color MAIN_COLOR = new Color(157f / 255, 216f / 255, 196f / 255, 0.7f);
	
	public static final float[] FRACTIONS = new float[] {0.2f,0.3f,0.5f};
	public static final Color[] COLORS = new Color[]{RADIAN_COLOR_1,RADIAN_COLOR_2,MAIN_COLOR};
	public static final float RADIAL_RADIUS = 30;
	
	public static final float BORDER_RADIUS = 10;

	
}
