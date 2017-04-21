package gfx;


import java.awt.image.BufferedImage;


/**
 * <h3>Assets Controller</h3>
 * <p>This class works in correlation with ImageLoader
 * static utility class and loads every drawable resource
 * for the game</p>
 * 
 * @author G_ANGELOV
 *
 */
public class Assets {
	//Scaffold Resources
    public static BufferedImage background;
    public static BufferedImage tileSheet;
    public static BufferedImage finishWizard;
    public static BufferedImage logo;
    
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
    public static BufferedImage retryButton;
    public static BufferedImage backButton;
    
    public static BufferedImage pointerLeft;
    public static BufferedImage pointerRight;
    
    public static BufferedImage textField;
    public static BufferedImage selector;
    
    //InGameMenu resources
	public static BufferedImage resumeButton;
	public static BufferedImage saveButton;
	public static BufferedImage gameover;
	public static BufferedImage levelCompleted;
	public static BufferedImage levelStatePanel;
	
	//ParallaxBackground
	public static BufferedImage backgroundSky;
	public static BufferedImage backgroundMountains;
	public static BufferedImage backgroundTrees;
	public static BufferedImage backgroundGrass;
	
    //HUD resources
    public static BufferedImage healthBar;
    public static BufferedImage diamondsCounter;
	public static BufferedImage diploma;
	public static BufferedImage creators;
	public static BufferedImage pesho;

    public static void init() {
    	loadStatePrereqs();
        
        levelStatePanel = ImageLoader.loadImage("/textures/levelstate.png");
        
        loadEntities();
        
        loadMenuComponents();
        
        //HUD
        healthBar = ImageLoader.loadImage("/textures/healthbarSprite.png");
        diamondsCounter = ImageLoader.loadImage("/textures/counter.png");
        
        diploma = ImageLoader.loadImage("/textures/diploma.png");
    }

	private static void loadEntities() {
		//Entities
        nakov_sheet = ImageLoader.loadImage("/textures/nakov_sheet.png");
        enemyBug = ImageLoader.loadImage("/textures/enemy_bug.png");
        
        //Entities.Collectible
        diamond = ImageLoader.loadImage("/textures/diamond.png");
        beer = ImageLoader.loadImage("/textures/beer.png");
        
        //Entities.Projectile
        projectile = ImageLoader.loadImage("/textures/blast.png");
        numKeysProjectile = ImageLoader.loadImage("/textures/numKeysProjectile.png");
	}

	private static void loadStatePrereqs() {
		//Map
        background = ImageLoader.loadImage("/textures/Back.png");
        tileSheet = ImageLoader.loadImage("/textures/sheet.png");
        logo = ImageLoader.loadImage("/textures/logo.png");
        levelCompleted = ImageLoader.loadImage("/textures/levelCompleted.png");
        gameover = ImageLoader.loadImage("/textures/gameover.png");
        creators = ImageLoader.loadImage("/textures/creators_intro.png");
        pesho = ImageLoader.loadImage("/textures/pesho.png");
        
        backgroundSky = ImageLoader.loadImage("/textures/background_sky.png");
        backgroundMountains = ImageLoader.loadImage("/textures/background_mountains.png");
        backgroundTrees = ImageLoader.loadImage("/textures/background_trees.png");
        backgroundGrass = ImageLoader.loadImage("/textures/background_grass.png");
	}

	private static void loadMenuComponents() {
		//Menu
        playButton = ImageLoader.loadImage("/textures/play.png");
        quitButton = ImageLoader.loadImage("/textures/quit.png");
        newButton = ImageLoader.loadImage("/textures/new.png");
        loadButton = ImageLoader.loadImage("/textures/load.png");
        createButton = ImageLoader.loadImage("/textures/create.png");
        retryButton = ImageLoader.loadImage("/textures/retry.png");
        backButton = ImageLoader.loadImage("/textures/return.png");
        
        pointerLeft = ImageLoader.loadImage("/textures/pointer-left.png");
        pointerRight = ImageLoader.loadImage("/textures/pointer-right.png");
        		
        textField = ImageLoader.loadImage("/textures/textfield.png");
        selector = ImageLoader.loadImage("/textures/selector.png");
        
        //InGameMenu
        saveButton = ImageLoader.loadImage("/textures/save.png");
        resumeButton = ImageLoader.loadImage("/textures/resume.png");
	}
}
