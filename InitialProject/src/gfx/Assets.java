package gfx;


import java.awt.image.BufferedImage;

import com.sun.prism.Image;

public class Assets {
	//Scaffold Resources
    public static BufferedImage background;
    public static BufferedImage tileSheet;
    
    //Entities
    public static BufferedImage nakov_sheet;
    public static BufferedImage enemy;
    public static BufferedImage enemyMeleeUnit;
    
    //Entites.Collectible
    public static BufferedImage diamond;
    public static BufferedImage beer;
    
    //Entities.Projectile
    public static BufferedImage projectile;
    public static BufferedImage numKeysProjectile;
    
    //Menu resources
    public static BufferedImage playButton;
    public static BufferedImage quitButton;
    
    //HUD resources
    public static BufferedImage healthBar;
    public static BufferedImage diamondsCounter;

    public static void init() {
    	//Map
        background = ImageLoader.loadImage("/textures/Back.png");
        tileSheet = ImageLoader.loadImage("/textures/sheet.png");
        
        //Entities
        nakov_sheet = ImageLoader.loadImage("/textures/nakov_sheet.png");
        enemy = ImageLoader.loadImage("/textures/firstEnemy.png");
        enemyMeleeUnit = ImageLoader.loadImage("/textures/meleeEnemy.png");
        
        //Entities.Collectible
        diamond = ImageLoader.loadImage("/textures/diamond.png");
        beer = ImageLoader.loadImage("/textures/beer.png");
        
        //Entities.Projectile
        projectile = ImageLoader.loadImage("/textures/blast.png");
        numKeysProjectile = ImageLoader.loadImage("/textures/numKeysProjectile.png");
        
        //Menu
        playButton = ImageLoader.loadImage("/textures/play.png");
        quitButton = ImageLoader.loadImage("/textures/quit.png");
        
        //HUD
        healthBar = ImageLoader.loadImage("/textures/healthbarSprite.png");
        diamondsCounter = ImageLoader.loadImage("/textures/counter.png");
    }
}
