package states;

import game.entities.Player;
import game.entities.projectile.NumpadKeysProjectile;
import gfx.Assets;
import map.MapFactory;
import map.ObjectLayer;
import map.TileMap;
import map.parser.LevelLoader;
import map.parser.Loader;
import utils.ObjectSerializer;
import utils.UserAccount;

import java.awt.*;
import java.io.*;

import constants.Constants;
import enums.Level;

public class GameState extends State {
	
    private static final int ID = 2;
    
    private static Level level;
    private static Player player;
    
    private static TileMap map;
    private static ObjectLayer objects;
    private MapFactory mapBuilder;
    
    private Loader loader;
    
    private InGameMenu menu;
    private InGameHUD hud;
    
    private static boolean finished;
    private boolean isRunning;
    private static boolean isMenuOpen;
    
    public GameState(Level level) {

    	super(ID);
    	this.level = level;
        this.loader = new LevelLoader(this.level);
		this.mapBuilder = new MapFactory(this.loader);
        
    	this.isRunning = false;
    	
    }

    @Override
    public void tick() {
    	
    	if(!isRunning){
            try {
                init();
            } catch (IOException e) {
                e.printStackTrace();
            }
            isRunning = true;
    	}
    	this.checkLevelProgress();
    	
        player.tick();
    	map.setPosition(Constants.WIDTH / 2 - player.getX()
    			, Constants.HEIGHT / 2 - player.getY());
    	
    	if(objects != null){
    		objects.tick();
    	}
        hud.tick();
        
        if(this.isMenuOpen){
        	menu.tick();
        }

    }

 

	@Override
    public void render(Graphics g) {
    	
		//Draw map
    	map.draw(g);
    	
    	//Render object layer
    	if(objects != null){
    		objects.render(g);
    	}
    	
    	//Render player HUD
        player.render(g);
        hud.render(g);
        
        if(this.isMenuOpen){
        	menu.render(g);;
        }
    }

    public static Player getPlayer() {
        return player;
    }
    
    public static TileMap getMap(){
    	return map;
    }
    
    public static boolean isInMenuState(){
    	return isMenuOpen;
    }
    
    public void toggleMenu() {
		if(this.isMenuOpen){
			this.isMenuOpen = false;
			return;
		}
		
		this.isMenuOpen = true;
	}
    
    public InGameMenu menu(){
    	return this.menu;
    }
    
    public static ObjectLayer getObjectsLayer(){
    	return objects;
    }

	public InGameMenu getInGameMenu() {
		return this.menu;
	}

	public static void setFinished(boolean b) {
		finished = b;
	}
	

	public static Level getCurrentLevel() {
		return level;
	}

	public static void deactivateInGameMenu() {
		isMenuOpen = false;
	}

    private void init() throws IOException {

        Assets.init();
        map = this.mapBuilder.createNewMap();
        objects = this.mapBuilder.setUpNewObjectLayer();
        
        this.menu = new InGameMenu();

        this.player = new Player(map,UserAccount.getStats()
        		,Constants.LEVEL_START_POSITION_X[this.level.ordinal()]
        		,Constants.LEVEL_START_POSITION_Y[this.level.ordinal()]);
        this.player.setWeapon(new NumpadKeysProjectile());

        this.hud = new InGameHUD(player);
        
        setFinished(false);
        
    }
	
	
    private void checkLevelProgress() {
    	if(player.isDead()){
    		StateManager.setCurrentState(new GameOverState(this.level));
    	}
   
    	if(this.finished){
			
    		this.player.getBag()
    			.rewardWith(Constants.LEVEL_REWARD[this.level.ordinal()]);
    		this.player.getPlayerStats().levelPassed(this.level);
    		
    		ObjectSerializer.getInstance().saveCurrentGameState();
    		StateManager.setCurrentState(new LevelCompletedState());
    		
    	}
	}
    
}