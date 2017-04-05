package states.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import components.Button;
import components.interfaces.IButton;
import constants.Constants;
import gfx.Assets;

public class InGameMenuInterface implements Interface{
	
	private IButton[] buttonBundle;
	
	public InGameMenuInterface(){
		init();
	}


	@Override
	public void tick() {
		
		for(IButton b : this.buttonBundle){
			b.tick();
		}
		
	}

	@Override
	public void render(Graphics g) {
		for(IButton b : this.buttonBundle){
			b.render(g);
		}
	}

	@Override
	public void onMouseHoverOverInterface(MouseEvent args) {
		for(IButton b : this.buttonBundle){
			b.onMenuButtonHover(args.getX(), args.getY());
		}
	}

	@Override
	public void onMouseReleaseOverInterface(MouseEvent args) {
		for(IButton b : this.buttonBundle){
			b.onMenuButtonRelease(args.getX(), args.getY());
		}
	}

	@Override
	public void onMouseClickOverInterface(MouseEvent args) {
		for(IButton b : this.buttonBundle){
			b.onMenuButtonClick(args.getX(), args.getY());
		}
	}
	

	private void init() {

		Button resume = new Button(Constants.MENU_BUTTON_X,Constants.MENU_BUTTON_Y);
		resume.setFrames(Assets.resumeButton);
		
		Button save = new Button(Constants.MENU_BUTTON_X
				,Constants.MENU_BUTTON_Y + Constants.MENU_BUTTON_MARGIN_BOTTOM);
		save.setFrames(Assets.saveButton);
		
		this.buttonBundle = new Button[]{resume,save};
		
	}
	
}
