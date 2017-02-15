package game.collectibles;


import java.awt.*;

import game.entities.Player;

public interface Collectible {

    public abstract void tick();

    public abstract void render(Graphics graphics);

    public void isCollected(Rectangle playerBounds);
    
    public void loadSprite();
    
}
