package states;

import game.entities.EnemyMeleeUnit;
import game.entities.EnemyShootingUnit;
import game.entities.Player;
import game.entities.Stats;
import gfx.Assets;
import map.ObjectLayer;
import map.TileMap;
import map.parser.LevelLoader;
import map.parser.ObjectsLayer;
import utils.Level;
import utils.UserAccount;

import java.awt.*;
import java.io.*;

import constants.Constants;

public class GameState extends State {
    private static final int GRAVITY = 2;
    private final static int ID = 2;
    
    private static TileMap map;
    private static ObjectLayer objects;
    private InGameMenu menu;
    private InGameHUD hud;
    
    private boolean isRunning;
    private boolean isMenuOpen;
    
    private Level level;
    public static Player player;
    public static EnemyShootingUnit firstEnemyShootingUnit;
    public static EnemyMeleeUnit firstMeleeEnemy;

    public GameState(Level level) {
    	super(ID);
    	this.level = level;
    	this.isRunning = false;
    	
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
        
        this.objects = new ObjectLayer(loader.getLevelData().getObjectsLayer());
        this.objects.setSecondaryTileLayer(loader.getLevelData().getLootLayer());
        this.objects.setOffset(loader.getLevelData().getLootLayer().getOffsetX()
        		,loader.getLevelData().getLootLayer().getOffsetY());
        
        this.menu = new InGameMenu();

        this.player = new Player(UserAccount.getStats().getPlayerName()   		
        		,this.map,UserAccount.getStats());
        
        this.hud = new InGameHUD(this.player);
        
        //firstEnemyShootingUnit = new EnemyShootingUnit("NekvaPachaSLesenSpriteSheet", 60, 60, 650, 450, 150, 50, 250);
        //firstMeleeEnemy = new EnemyMeleeUnit("Melee", 100, 134, 450, 400, 5, 35, 700);
        
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
   
        player.tick();
    	map.setPosition(Constants.WIDTH / 2 - player.getX()
    			, Constants.HEIGHT / 2 - player.getY());
    	
    	objects.tick();
   
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
    	objects.render(g);
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

	
}