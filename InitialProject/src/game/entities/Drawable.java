package game.entities;


import java.awt.*;

public interface Drawable {
    public abstract void tick();

    public abstract void render(Graphics graphics);
}
