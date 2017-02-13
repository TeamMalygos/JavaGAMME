package gfx;


import java.awt.image.BufferedImage;

import com.sun.prism.Image;

public class Assets {
	//Scaffold Resources
    public static BufferedImage background;
    public static BufferedImage tileSheet;
    
    //Object resources
    public static BufferedImage nakov_sheet;
    public static BufferedImage enemy;
    public static BufferedImage projectile;
    public static BufferedImage enemyMeleeUnit;
    public static BufferedImage diamond;
    
    //Menu resources
    public static BufferedImage playButton;
    public static BufferedImage quitButton;

    public static void init() {
    	//Map
        background = ImageLoader.loadImage("/textures/bckg.jpg");
        tileSheet = ImageLoader.loadImage("/textures/sheet.png");
        
        //Entities
        nakov_sheet = ImageLoader.loadImage("/textures/nakov_sheet.png");
        enemy = ImageLoader.loadImage("/textures/firstEnemy.png");
        projectile = ImageLoader.loadImage("/textures/blast.png");
        enemyMeleeUnit = ImageLoader.loadImage("/textures/meleeEnemy.png");
        diamond = ImageLoader.loadImage("/textures/diamond.png");
        
        
        //Menu
        playButton = ImageLoader.loadImage("/textures/play.png");
        quitButton = ImageLoader.loadImage("/textures/quit.png");
    }
}
