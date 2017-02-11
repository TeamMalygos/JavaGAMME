package game.entities;

import java.util.*;
import gfx.SpriteSheet;

public class Shop {
    public static boolean isOpened;

    public void Shop() {

        ArrayList<Weapon> weaponShop = new ArrayList<>();
        Weapon longsword = new Weapon();
        Weapon shortsword = new Weapon();
        Weapon claymore = new Weapon();

        longsword.initialiseLongsword();
        shortsword.initialiseShortsword();
        claymore.initialiseClaymore();

        weaponShop.add(longsword);
        weaponShop.add(shortsword);
        weaponShop.add(claymore);
    }
}