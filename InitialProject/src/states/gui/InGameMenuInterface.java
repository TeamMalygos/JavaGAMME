package states.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import components.Button;
import components.interfaces.Clickable;
import constants.Constants;
import events.MenuButtonClickEvent;
import gfx.Assets;
import states.GameState;
import states.MenuState;

public class InGameMenuInterface implements Interface{
	
	private Clickable[] buttonBundle;
	
	public InGameMenuInterface(){
		init();
	}


	@Override
	public void tick() {
		
		for(Clickable b : this.buttonBundle){
			b.tick();
		}
		
	}

	@Override
	public void render(Graphics g) {
		for(Clickable b : this.buttonBundle){
			b.render(g);
		}
	}

	@Override
	public void onMouseHoverOverInterface(MouseEvent args) {
		for(Clickable b : this.buttonBundle){
			b.onMenuButtonHover(args.getX(), args.getY());
		}
	}

	@Override
	public void onMouseReleaseOverInterface(MouseEvent args) {
		for(Clickable b : this.buttonBundle){
			b.onMenuButtonRelease(args.getX(), args.getY());
		}
	}

	@Override
	public void onMouseClickOverInterface(MouseEvent args) {
		for(Clickable b : this.buttonBundle){
			b.onMenuButtonClick(args.getX(), args.getY());
		}
	}
	

	private void init() {

		Button resume = loadResumeButton();
		Button save = loadSaveButton();
		
		
		this.buttonBundle = new Button[]{resume,save};
		
	}


	private Button loadResumeButton() {
		Button resume = new Button(Constants.MENU_BUTTON_X,Constants.MENU_BUTTON_Y){
			@Override
			public void onMenuButtonRelease(int x,int y){
				if(!this.isInside(x, y)){
					this.setPressed(false);
					this.setHover(false);
					return;
				}
				GameState.deactivateInGameMenu();
			}
			
		};
		resume.setFrames(Assets.resumeButton);
		return resume;
	}


	private Button loadSaveButton() {
		Button save = new Button(Constants.MENU_BUTTON_X
				,Constants.MENU_BUTTON_Y + Constants.MENU_BUTTON_MARGIN_BOTTOM){
			@Override
			public void onMenuButtonRelease(int x,int y){
				if(!this.isInside(x, y)){
					this.setPressed(false);
					this.setHover(false);
					return;
				}
				new MenuButtonClickEvent(this,new MenuState());
			}
		};
		save.setFrames(Assets.saveButton);
		return save;
	}
	
}
