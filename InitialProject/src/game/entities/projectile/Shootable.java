package game.entities.projectile;


import game.entities.MapObject;

public interface Shootable {

    boolean intersects(MapObject player);

    boolean isInRange();
}
