package game.collectibles;


import java.awt.*;

public interface Collectible {

    public void isCollected(Rectangle playerBounds);
    
    public void loadSprite();
    
}
