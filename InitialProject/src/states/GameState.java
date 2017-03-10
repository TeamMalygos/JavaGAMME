package states;

import components.*;
import components.Button;
import game.entities.EnemyMeleeUnit;
import game.entities.EnemyShootingUnit;
import game.entities.Player;
import gfx.Assets;
import map.ObjectLayer;
import map.TileMap;
import map.parser.LevelLoader;
import utils.ObjectSerializer;
import utils.UserAccount;

import java.awt.*;
import java.io.*;

import constants.Constants;
import enums.Level;

public class GameState extends State {
    private static final int GRAVITY = 2;
    private final static int ID = 2;
    
    private static TileMap map;
    private static ObjectLayer objects;
    private InGameMenu menu;
    private InGameHUD hud;
    private static boolean finished;
    
    private boolean isRunning;
    private boolean isMenuOpen;
    
    private Level level;
    public static Player player;
    public static EnemyShootingUnit firstEnemyShootingUnit;
    public static EnemyMeleeUnit firstMeleeEnemy;

    private components.Button healSpell;

    public GameState(Level level) {
    	super(ID);
    	this.level = level;
    	this.isRunning = false;
    	
    }



    /*This will be used later in another state so do not delete it!!!!
     * 
    private void createNewPlayer(BufferedReader reader) throws IOException {
        playerName = reader.readLine();

        if (playerFile.exists()) {
            System.out.println("Player with such name already exists. Please choose new one.");
            createNewPlayer(reader);
        } else {
            player = new Player(playerName,map);

        }
    }
*/

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
    	
    	if(this.player.isDead()){
    		StateManager.setCurrentState(new GameOverState(this.level));
    	}
   
    	if(finished){
			
    		player.getBag()
    			.rewardWith(Constants.LEVEL_REWARD[this.level.ordinal()]);
    		player.getPlayerStats().levelPassed(this.level);
    		ObjectSerializer.getInstance().saveCurrentGameState();
    		StateManager.setCurrentState(new LevelCompletedState());
    		
    	}
    	
        player.tick();
    	map.setPosition(Constants.WIDTH / 2 - player.getX()
    			, Constants.HEIGHT / 2 - player.getY());
    	
    	if(objects != null){
    		objects.tick();
    	}
    	
        hud.tick();
        //firstEnemyShootingUnit.tick();
        //firstMeleeEnemy.tick();
        if(this.isMenuOpen){
        	menu.tick();
        }

    }

    @Override
    public void render(Graphics g) {
    	
    	map.draw(g);
    	
    	if(objects != null){
    		objects.render(g);
    	}
    	
        player.render(g);
        hud.render(g);
        //firstEnemyShootingUnit.render(g);
        //firstMeleeEnemy.render(g);
        if(this.isMenuOpen){
        	menu.render(g);;
        }
    }


    public static int getGRAVITY() {
        return GRAVITY;
    }

    public static Player getPlayer() {
        return player;
    }
    public static TileMap getMap(){
    	return map;
    }
    public boolean isInMenuState(){
    	return this.isMenuOpen;
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

    public static void setPlayer(Player player) {
        GameState.player = player;
    }

    public static EnemyShootingUnit getFirstEnemyShootingUnit() {
        return firstEnemyShootingUnit;
    }

    public static void setFirstEnemyShootingUnit(EnemyShootingUnit firstEnemyShootingUnit) {
        GameState.firstEnemyShootingUnit = firstEnemyShootingUnit;
    }
    
    public static ObjectLayer getObjectsLayer(){
    	return objects;
    }

	public InGameMenu getInGameMenu() {
		// TODO Auto-generated method stub
		return this.menu;
	}

	public static void setFinished(boolean b) {
		finished = b;
	}

	
    private void init() throws IOException {

        Assets.init();
      
        LevelLoader loader = new LevelLoader(this.level);

        this.map = new TileMap(loader.getLevelData().getTileLayer().getData()
        		,loader.getLevelData().getTileLayer().getHeight()
        		,loader.getLevelData().getTileLayer().getWidth());
        
        this.map.setOffset(loader.getLevelData().getTileLayer().getOffsetX(),
        		loader.getLevelData().getTileLayer().getOffsetY());
        this.map.loadTiles("/textures/Sheet.png");
        
        this.map.setPosition(0, 0);

        try{
        	objects = new ObjectLayer(loader.getLevelData().getObjectsLayer());
        }catch(NullPointerException ex){
        	ex.printStackTrace();
        	System.out.println("Current level has no Object layer");
        }
        
        try{
        	objects.setSecondaryTileLayer(loader.getLevelData().getLootLayer());
        	objects.setOffset(loader.getLevelData().getLootLayer().getOffsetX()
        			,loader.getLevelData().getLootLayer().getOffsetY());
        }catch(ArrayIndexOutOfBoundsException ex){
        	System.out.println("Current level has no Loot Layer");
    	}catch(NullPointerException ex){
        	System.out.println("Current level has no Loot Layer");
        }
        
        this.menu = new InGameMenu();

        this.player = new Player(UserAccount.getStats().getPlayerName()   		
        		,this.map,UserAccount.getStats()
        		,Constants.LEVEL_START_POSITION_X[this.level.ordinal()]
        		,Constants.LEVEL_START_POSITION_Y[this.level.ordinal()]);
        
        this.hud = new InGameHUD(this.player);
        
        setFinished(false);
        
    }
	
}