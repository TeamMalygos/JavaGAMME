package game.entities;

import gfx.SpriteSheet;
import java.awt.*;

public class Weapon {

    private String name;
    private int price, damage, durability, swingSpeed;
    private SpriteSheet weaponImage;
    private Rectangle boundingBox;

    public void initialiseLongsword() {
        this.name = "Longsword";
        this.price = 100;
        this.damage = 10;
        this.durability = 100;
        this.swingSpeed = 2;


    }

    public void initialiseShortsword() {
        this.name = "Shortsword";
        this.price = 80;
        this.damage = 6;
        this.durability = 70;
        this.swingSpeed = 1;


    }

    public void initialiseClaymore() {
        this.name = "Claymore";
        this.price = 160;
        this.damage = 15;
        this.durability = 100;
        this.swingSpeed = 3;


    }
}
