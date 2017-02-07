package gfx;


import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage background;
    public static BufferedImage nakov_sheet;
    public static BufferedImage enemy;
    public static BufferedImage projectile;
    public static BufferedImage enemyMeleeUnit;
    public static BufferedImage tileSheet;

    public static void init() {
        background = ImageLoader.loadImage("/textures/bckg.jpg");
        nakov_sheet = ImageLoader.loadImage("/textures/nakov_sheet.jpg");
        enemy = ImageLoader.loadImage("/textures/firstEnemy.png");
        projectile = ImageLoader.loadImage("/textures/blast.png");
        enemyMeleeUnit = ImageLoader.loadImage("/textures/meleeEnemy.png");
        tileSheet = ImageLoader.loadImage("/textures/Sheet.png");
    }
}
