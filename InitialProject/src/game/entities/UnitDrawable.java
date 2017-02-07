package game.entities;


import java.awt.*;

public interface UnitDrawable {
    public abstract void tick();

    public abstract void render(Graphics graphics);
}
