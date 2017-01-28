package states;

import display.Display;
import game.entities.Player;
import gfx.Assets;

import java.awt.*;

public class GameState extends State {
    private static final int GRAVITY = 2;
    public static Player player;

    public GameState() {
        init();

        player = new Player("Nakovkata", 95, 130, 100, 400);
    }

    private void init() {
        Assets.init();
    }


    @Override
    public void tick() {
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        player.render(g);

    }
}
