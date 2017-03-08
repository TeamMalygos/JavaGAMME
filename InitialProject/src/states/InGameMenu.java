package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import components.Button;
import components.StringButton;
import constants.Constants;
import events.MouseMotionSensitive;
import game.entities.UnitDrawable;
import gfx.Assets;
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
public class InGameMenu implements UnitDrawable,MouseMotionSensitive{

	private static final int ID = 7;
	private Button resume;
	private Button save;
	
	public InGameMenu(){
		init();
	}
	
	@Override
	public void tick() {
		
		this.resume.tick();
		this.save.tick();

	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Constants.COLOR_OPAQUE_BLACK);
		
		g.fillRect(Constants.BACKGROUND_X, Constants.BACKGROUND_Y
				, Constants.WIDTH, Constants.HEIGHT);
		
		this.resume.render(g);
		this.save.render(g);
	}

	@Override
	public void onMouseHover(MouseEvent args) {
		if(this.resume.isInside(args.getX(), args.getY())){
			this.resume.onMenuButtonHover();
		}else{
			this.resume.setHover(false);
		}
		
		if(this.save.isInside(args.getX(), args.getY())){
			this.save.onMenuButtonHover();
		}else{
			this.save.setHover(false);
		}
		
	}

	@Override
	public void onMouseRelease(MouseEvent args) {
		
		if(this.resume.isInside(args.getX(), args.getY())){
			try{
				GameState g = (GameState)StateManager.getCurrentState();
				g.toggleMenu();
			}catch(ClassCastException ex){
				ex.printStackTrace();
			}
		}else{
			this.resume.setPressed(false);
		}
		
		if(this.save.isInside(args.getX(), args.getY())){
			ObjectSerializer.getInstance().saveCurrentGameState();
			StateManager.setCurrentState(new MenuState());
		}else{
			this.save.setPressed(false);
		}
		
	}

	@Override
	public void onMouseClick(MouseEvent args) {
		
		if(this.resume.isInside(args.getX(), args.getY())){
			this.resume.onMenuButtonClick();
		}else{
			this.resume.setHover(false);
			this.resume.setPressed(false);
		}
		
		if(this.save.isInside(args.getX(), args.getY())){
			this.save.onMenuButtonClick();
		}else{
			this.save.setHover(false);
			this.save.setPressed(false);
		}
		
	}
	

	private void init(){

		this.resume = new Button(Constants.MENU_BUTTON_X,Constants.MENU_BUTTON_Y,Constants.BUTTON_RESUME);
		this.resume.setFrames(Assets.resumeButton);
		
		this.save = new Button(Constants.MENU_BUTTON_X
				,Constants.MENU_BUTTON_Y + Constants.MENU_BUTTON_MARGIN_BOTTOM
				,Constants.BUTTON_SAVE);
		this.save.setFrames(Assets.saveButton);
		
		
	}
	
}