package states;

import java.awt.*;

public class MenuState extends State{
 
	private Menu optionsMenu;
	private Menu startMenu;
	private final static int STATE_ID = 1;
	
	public MenuState() {
		super(STATE_ID);
    	startMenu = new Menu();
    	startMenu.init();
	}

	public Menu getMenu(){
		return startMenu;
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


