package game.collectibles;


import java.awt.*;

import game.entities.Player;

public interface Collectible {

    public void isCollected(Rectangle playerBounds);
    
    public void loadSprite();
    
}
