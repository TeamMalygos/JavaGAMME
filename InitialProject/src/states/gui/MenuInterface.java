package states.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import components.Button;
import components.Pesho;
import components.interfaces.Clickable;
import constants.Constants;
import enums.Level;
import events.MenuButtonClickEvent;
import gfx.Assets;
import states.CreateCharacterState;
import states.GameState;
import states.LoadCharacterState;
import states.MenuState;
import states.State;
import utils.UserAccount;

public class MenuInterface implements Interface{
	/*	
	private IButton create;
	private IButton start;
	private IButton load;
	//private Button options;
	private IButton exit;
	*/
	private Clickable[] buttonBundle;
	
	private Pesho peshoTheDominator;
	
	public MenuInterface(){
		this.buttonBundle = new Button[4];
		this.peshoTheDominator = new Pesho();
		init();
	}
	
	private void init() {
		int offset = Constants.MENU_BUTTON_MARGIN_BOTTOM;
		int yposition = Constants.MENU_BUTTON_Y;
		int xposition = Constants.MENU_BUTTON_X;
		
		load(offset, yposition, xposition);
		
		Button exit = new Button(xposition,yposition + (offset * 3)){
			@Override
			public void onMenuButtonRelease(int x,int y){
				
				if(!this.isInside(x, y)){
					super.setHover(false);
					super.setPressed(false);
					return;
				}
				
				System.exit(0);
			}
		};
		exit.setFrames(Assets.quitButton);
		this.buttonBundle[0] = exit;
	}


	@Override
	public void tick() {
		for(Clickable b : this.buttonBundle){
			b.tick();
		}
		this.peshoTheDominator.tick();
	}
	@Override
	public void render(Graphics g) {
		for(Clickable b : this.buttonBundle){
			b.render(g);
		}
		
		this.peshoTheDominator.render(g);
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
	
	private void load(int offset, int yposition, int xposition) {
		Button start = new Button(xposition,yposition);
		start.setFrames(Assets.playButton);
		
	  	if(UserAccount.playerExists()){
	  		start.linkToState(new GameState(Level.Level1));
    	}
	  	start.linkToState(new LoadCharacterState());

		Button create = new Button(xposition,yposition + offset);
		create.setFrames(Assets.newButton);
		create.linkToState(new CreateCharacterState());		
		
		Button load = new Button(xposition,yposition + (offset * 2));
		load.setFrames(Assets.loadButton);
		load.linkToState(new LoadCharacterState());
		
		this.buttonBundle[1] = start;
		this.buttonBundle[2] = create;
		this.buttonBundle[3] = load;
	}
}
