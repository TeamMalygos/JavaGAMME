package game.entities;


import java.awt.*;

public interface Shootable {

    void tick();

    void render(Graphics graphics);

    boolean intersects(MapObject player);

    boolean isInRange();
}
