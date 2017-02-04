package states;

import java.awt.*;

public class MenuState extends State{
 
 private Menu startMenu;
 
 public MenuState() {
     startMenu = new Menu();
     startMenu.init();
    }

    @Override
    public void tick() {
     startMenu.tick();
    }

    @Override
    public void render(Graphics g) {
     startMenu.render(g);
    }
}


