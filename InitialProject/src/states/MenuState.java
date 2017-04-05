package states;

import java.awt.*;
import java.awt.event.MouseEvent;

import components.ParallaxBackground;
import constants.Constants;
import events.MouseMotionSensitive;
import gfx.Assets;

/**
 * <h3>Game Menu</h3>
 * 
 * <p>Game Menu State and all of it's child items reside here.
 * <br>In other words this class is the MainMenu state !</p>
 * 
 * @author G_ANGELOV
 *
 */
public class MenuState extends State implements MouseMotionSensitive{
 
	private ParallaxBackground background;
	private Menu startMenu;

	private final static int STATE_ID = 1;
	
	public MenuState() {
		super(STATE_ID);		
		
		this.background = new ParallaxBackground();

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
    	
    	/*
    	g.drawImage(Assets.background, 0, 0,Constants.WIDTH,Constants.HEIGHT, null);
    	*/
    	this.background.render(g);
    	g.drawImage(Assets.logo, Constants.BACKGROUND_X	- Constants.LOGO_WIDTH / 100
    			, Constants.HEIGHT / 4 - Constants.LOGO_HEIGHT / 2, null);
    	
    	startMenu.render(g);
    	
    	g.setColor(Color.white);
    	g.setFont(new Font(Constants.FONT,Font.BOLD,Constants.FONT_SIZE));
    	g.drawString(Constants.CREATORS, 
    			Constants.CREATORS_X,
    			Constants.CREATORS_Y);
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
