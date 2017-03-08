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
    public static BufferedImage enemyBug;
    
    //Entites.Collectible
    public static BufferedImage diamond;
    public static BufferedImage beer;
    
    //Entities.Projectile
    public static BufferedImage projectile;
    public static BufferedImage numKeysProjectile;
    
    //Menu resources
    public static BufferedImage playButton;
    public static BufferedImage quitButton;
    public static BufferedImage loadButton;
    public static BufferedImage newButton;
    public static BufferedImage createButton;
    
    public static BufferedImage pointerLeft;
    public static BufferedImage pointerRight;
    
    public static BufferedImage textField;
    public static BufferedImage selector;
    
    //InGameMenu resources
	public static BufferedImage resumeButton;
	public static BufferedImage saveButton;
    
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
        enemyBug = ImageLoader.loadImage("/textures/enemy_bug.png");
        
        //Entities.Collectible
        diamond = ImageLoader.loadImage("/textures/diamond.png");
        beer = ImageLoader.loadImage("/textures/beer.png");
        
        //Entities.Projectile
        projectile = ImageLoader.loadImage("/textures/blast.png");
        numKeysProjectile = ImageLoader.loadImage("/textures/numKeysProjectile.png");
        
        //Menu
        playButton = ImageLoader.loadImage("/textures/play.png");
        quitButton = ImageLoader.loadImage("/textures/quit.png");
        newButton = ImageLoader.loadImage("/textures/new.png");
        loadButton = ImageLoader.loadImage("/textures/load.png");
        createButton = ImageLoader.loadImage("/textures/create.png");
        
        pointerLeft = ImageLoader.loadImage("/textures/pointer-left.png");
        pointerRight = ImageLoader.loadImage("/textures/pointer-right.png");
        		
        textField = ImageLoader.loadImage("/textures/textfield.png");
        selector = ImageLoader.loadImage("/textures/selector.png");
        
        //InGameMenu
        saveButton = ImageLoader.loadImage("/textures/save.png");
        resumeButton = ImageLoader.loadImage("/textures/resume.png");
        
        //HUD
        healthBar = ImageLoader.loadImage("/textures/healthbarSprite.png");
        diamondsCounter = ImageLoader.loadImage("/textures/counter.png");
    }
}
