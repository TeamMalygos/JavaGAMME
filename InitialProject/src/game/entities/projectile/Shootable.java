package game.entities.projectile;


import java.awt.*;

import game.entities.MapObject;

public interface Shootable {

    void tick();

    void render(Graphics graphics);

    boolean intersects(MapObject player);

    boolean isInRange();
}
