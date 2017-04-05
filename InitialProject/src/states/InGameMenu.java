package states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import components.Button;
import constants.Constants;
import events.MouseMotionSensitive;
import game.entities.Drawable;
import gfx.Assets;
import states.gui.InGameMenuInterface;
import states.gui.Interface;
import utils.ObjectSerializer;

/**
 * 
 * @author G_ANGELOV
 *
 * <p>InGameMenu is the basic implementation of 
 * in-game menu we have. It contains couple buttons
 * and in it gives you the opportunity to control
 * the game while playing </p>
 *
 */
public class InGameMenu implements Drawable,MouseMotionSensitive{
	
	private Interface inGameInterface;
	
	public InGameMenu(){
		this.inGameInterface = new InGameMenuInterface();
	}
	
	@Override
	public void tick() {
		
		this.inGameInterface.tick();
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Constants.COLOR_OPAQUE_BLACK);
		
		g.fillRect(Constants.BACKGROUND_X, Constants.BACKGROUND_Y
				, Constants.WIDTH, Constants.HEIGHT);
		this.inGameInterface.render(g);

	}

	@Override
	public void onMouseHover(MouseEvent args) {
		this.inGameInterface.onMouseHoverOverInterface(args);
	}

	@Override
	public void onMouseRelease(MouseEvent args) {
		this.inGameInterface.onMouseReleaseOverInterface(args);
	}

	@Override
	public void onMouseClick(MouseEvent args) {
		this.inGameInterface.onMouseClickOverInterface(args);
	}
	
	
}