package states.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import components.Button;
import components.interfaces.IButton;

import constants.Constants;
import enums.Level;
import events.MenuButtonClickEvent;
import gfx.Assets;

import states.GameState;
import states.MenuState;

import utils.UserAccount;

public class LevelSelectionInterface implements Interface {
	
	private IButton pointerLeft;
	private IButton pointerRight;
	private IButton load;
	private IButton back;
	private IButton[] buttonsBundle;
	
	private int indexOnFocus;
	
	private BufferedImage background;
	
	public LevelSelectionInterface(){
		this.background = Assets.levelStatePanel;
		this.indexOnFocus = 0;
		this.init();
	}
	

	@Override
	public void tick() {
		for(IButton b : this.buttonsBundle){
			b.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.background
				, Constants.BACKGROUND_X
				, Constants.BACKGROUND_Y
				, Constants.WIDTH
				, Constants.HEIGHT
				, null);
		
		g.drawImage(this.background
				,Constants.WIDTH / 2 - Constants.LEVELS_STATE_PANEL_WIDTH / 2
				,Constants.HEIGHT / 2 - Constants.LEVELS_STATE_PANEL_HEIGHT /2
				,null);
		
		
		for(IButton b : this.buttonsBundle){
			b.render(g);
		}
		
	}

	public int getIndexOnFocus() {
		return this.indexOnFocus;
	}
	
	@Override
	public void onMouseHoverOverInterface(MouseEvent args) {
		for(IButton b: this.buttonsBundle){
			b.onMenuButtonHover(args.getX(), args.getY());
		}
	}

	@Override
	public void onMouseReleaseOverInterface(MouseEvent args) {
		((Button)this.load).linkToState(new GameState(this.getLevelOnFocus()));

		for(IButton b : this.buttonsBundle){
			b.onMenuButtonRelease(args.getX(), args.getY());
		}
	}

	@Override
	public void onMouseClickOverInterface(MouseEvent args) {
		for(IButton b : this.buttonsBundle){
			b.onMenuButtonClick(args.getX(), args.getY());
		}
	}

	private void init() {
		this.loadPointingButtons();
	
		//Creating load button
		Button load = new Button(Constants.WIDTH / 4
				, Constants.HEIGHT
				- (Constants.HEIGHT / 3) + Constants.MENU_BUTTON_HEIGHT / 3 - Constants.STANDARD_PADDING / 3);
		
		load.setFrames(Assets.loadButton);
		this.load = load;
		
		loadBackButton();
		
		this.buttonsBundle = new IButton[]{this.pointerLeft,this.pointerRight,this.load,this.back};
	}

	
	private void loadPointingButtons() {
		
		loadLeftPointer();
		loadRightPointer();
		
	}


	private void loadRightPointer() {
		//Creating the right pointer button
		Button pointerRight = new Button(
				Constants.WIDTH - Constants.WIDTH / 3 
				+ Constants.POINTER_WIDTH + Constants.STANDARD_MARGIN * 2
				, Constants.HEIGHT / 3 + Constants.POINTER_HEIGHT / 2){
			
			@Override
			public void onMenuButtonRelease(int mouseX, int mouseY) {
				
				if(!this.isInside(mouseX, mouseY)){
					this.setPressed(false);
					this.setHover(false);
					return;
				}
				
				focusRight();
			}
		};
		
		pointerRight.setSize(Constants.POINTER_WIDTH, Constants.POINTER_HEIGHT);
		pointerRight.setFrames(Assets.pointerRight);
		this.pointerRight = pointerRight;
	}


	private void loadLeftPointer() {
		//Creating the left pointer button
		Button pointerLeft = new Button(Constants.WIDTH / 3 - Constants.POINTER_WIDTH * 2
				,Constants.HEIGHT / 3 + Constants.POINTER_HEIGHT / 2){
			
			@Override
			public void onMenuButtonRelease(int mouseX, int mouseY){
				if(!this.isInside(mouseX, mouseY)){
					this.setPressed(false);
					this.setHover(false);
					return;
				}
				
				focusLeft();
			}
			
		};
		
		pointerLeft.setSize(Constants.POINTER_WIDTH, Constants.POINTER_HEIGHT);
		pointerLeft.setFrames(Assets.pointerLeft);
		this.pointerLeft = pointerLeft;
	}
	
	private void loadBackButton() {
		//Creating back button
		Button back = new Button(Constants.WIDTH / 2 + Constants.MENU_BUTTON_MARGIN_BOTTOM / 3
				,Constants.HEIGHT - (Constants.HEIGHT / 3) 
				+ Constants.MENU_BUTTON_HEIGHT / 3 - Constants.STANDARD_PADDING / 3){
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
		back.setFrames(Assets.backButton);
		
		this.back = back;
	}
	
	private void focusLeft() {
		if(this.indexOnFocus > 0){
			this.indexOnFocus -= 1;
		}
		
	}
	
	private void focusRight() {
		if(this.indexOnFocus < UserAccount.getStats().getProgress()){
			this.indexOnFocus +=1;
		}
		
	}
	
	private Level getLevelOnFocus() {
		Level l = Level.Level1;
		try{
			l = Level.valueOf("Level" + (this.getIndexOnFocus()+1));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return l;
	}
	
}
