package gfx;


import java.awt.image.BufferedImage;

import com.sun.prism.Image;

public class Assets {
    public static BufferedImage background;
    public static BufferedImage nakov_sheet;
    public static BufferedImage enemy;
    public static BufferedImage projectile;
    public static BufferedImage enemyMeleeUnit;
    public static BufferedImage tileSheet;
    public static BufferedImage playButton;
    public static BufferedImage quitButton;

    public static void init() {
        background = ImageLoader.loadImage("/textures/bckg.jpg");
        nakov_sheet = ImageLoader.loadImage("/textures/nakov_sheet.jpg");
        enemy = ImageLoader.loadImage("/textures/firstEnemy.png");
        projectile = ImageLoader.loadImage("/textures/blast.png");
        enemyMeleeUnit = ImageLoader.loadImage("/textures/meleeEnemy.png");
        tileSheet = ImageLoader.loadImage("/textures/sheet.png");
        playButton = ImageLoader.loadImage("/textures/play.png");
        quitButton = ImageLoader.loadImage("/textures/quit.png");
    }
}
