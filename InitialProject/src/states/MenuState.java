package states;

import java.awt.*;
import java.awt.event.MouseEvent;

import constants.Constants;
import display.Display;
import events.MouseMotionSensitive;
import gfx.Assets;

public class MenuState extends State implements MouseMotionSensitive{
 
	private Menu startMenu;

	private final static int STATE_ID = 1;
	
	public MenuState() {
		super(STATE_ID);
		
		Assets.init();
		
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
    	g.drawImage(Assets.background, 0, 0,Constants.WIDTH,Constants.HEIGHT, null);
    	startMenu.render(g);
    }

	@Override
	public void onMouseHover(MouseEvent args) {
		this.startMenu.onMenuItemHover(args);
		
	}

	@Override
	public void onMouseRelease(MouseEvent args) {
		this.startMenu.onMenuItemRelease(args);
	}

	@Override
	public void onMouseClick(MouseEvent args) {
		this.startMenu.onMenuItemClick(args);
	}
}
