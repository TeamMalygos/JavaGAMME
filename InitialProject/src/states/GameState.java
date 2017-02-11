package states;

import game.entities.EnemyMeleeUnit;
import game.entities.EnemyShootingUnit;
import game.entities.Player;
import game.entities.Shop;
import gfx.Assets;
import map.TileMap;

import java.awt.*;
import java.io.File;

public class GameState extends State {
    private static final int GRAVITY = 2;
    private final static int ID = 2;
    
    private TileMap map;
    private boolean isRunning;
    public static Player player;
    public static EnemyShootingUnit firstEnemyShootingUnit;
    public static EnemyMeleeUnit firstMeleeEnemy;
    public static Shop shop;

    public GameState() {
    	super(ID);
    	isRunning = false;
    }

    private void init() {
        Assets.init();
      
        map = new TileMap("/map1", 64, 64);
        map.loadTiles("/textures/Sheet.png");
        player = new Player("Nakovkata", 95, 130, 100, 400);
        firstEnemyShootingUnit = new EnemyShootingUnit("NekvaPachaSLesenSpriteSheet", 60, 60, 650, 450, 150, 50, 250);
        firstMeleeEnemy = new EnemyMeleeUnit("Melee", 100, 134, 450, 400, 5, 35, 700);
        shop = new Shop();
    }


    @Override
    public void tick() {
    	if(!isRunning){
    		init();
    		isRunning = true;
    	}
    	map.update();
        player.tick();
        firstEnemyShootingUnit.tick();
        firstMeleeEnemy.tick();
    }

    @Override
    public void render(Graphics g) {
    	map.draw(g);
        player.render(g);
        firstEnemyShootingUnit.render(g);
        firstMeleeEnemy.render(g);
    }

    public static int getGRAVITY() {
        return GRAVITY;
    }

    public static Player getPlayer() {
        return player;
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
}
