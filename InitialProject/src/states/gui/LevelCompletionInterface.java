package states.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import components.Button;
import components.interfaces.Clickable;
import constants.Constants;
import events.MenuButtonClickEvent;
import gfx.Assets;
import states.MenuState;

public class LevelCompletionInterface implements Interface{

	private BufferedImage successBackground;
	private Clickable exit;
	
	public LevelCompletionInterface(){
		this.successBackground = Assets.levelCompleted;
		this.load();
	}
	
	@Override
	public void tick() {
		this.exit.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(this.successBackground
				,Constants.BACKGROUND_X,Constants.BACKGROUND_Y,null);
		this.exit.render(g);
	}

	@Override
	public void onMouseHoverOverInterface(MouseEvent args) {
		this.exit.onMenuButtonHover(args.getX(), args.getY());
		
	}

	@Override
	public void onMouseReleaseOverInterface(MouseEvent args) {
		this.exit.onMenuButtonRelease(args.getX(), args.getY());
	}

	@Override
	public void onMouseClickOverInterface(MouseEvent args) {
		this.exit.onMenuButtonClick(args.getX(), args.getY());
	}
	
	private void load() {
		Button exit = new Button(Constants.MENU_BUTTON_X,Constants.MENU_BUTTON_HEIGHT){
			@Override
			public void onMenuButtonRelease(int mouseX, int mouseY) {
				
				if(!this.isInside(mouseX, mouseY)){
					this.setPressed(false);
					this.setHover(false);
					return;
				}
				
				new MenuButtonClickEvent(this,new MenuState());
				
			}
		};
		exit.setFrames(Assets.quitButton);
		
		this.exit = exit;
	}

}
