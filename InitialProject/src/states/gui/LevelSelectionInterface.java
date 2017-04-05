package states.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import components.Button;
import components.interfaces.IButton;
import constants.Constants;
import gfx.Assets;

public class LevelSelectionInterface implements Interface {
	
	private IButton pointerLeft;
	private IButton pointerRight;
	private IButton load;
	private IButton back;
	
	private int indexOnFocus;
	
	private BufferedImage background;
	
	public LevelSelectionInterface(){
		this.background = Assets.levelStatePanel;
		this.indexOnFocus = 0;
		this.init();
	}
	

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics graphics) {
		
	}

	@Override
	public void onMouseHoverOverInterface(MouseEvent args) {
		
	}

	@Override
	public void onMouseReleaseOverInterface(MouseEvent args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseClickOverInterface(MouseEvent args) {
		// TODO Auto-generated method stub
		
	}

	private void init() {
		this.loadPointingButtons();
	
		//Creating load button
		Button load = new Button(Constants.WIDTH / 4
				, Constants.HEIGHT
				- (Constants.HEIGHT / 3) + Constants.MENU_BUTTON_HEIGHT / 3 - Constants.STANDARD_PADDING / 3);
		
		load.setFrames(Assets.loadButton);
		
		//Creating back button
		Button back = new Button(Constants.WIDTH / 2 + Constants.MENU_BUTTON_MARGIN_BOTTOM / 3
				,Constants.HEIGHT - (Constants.HEIGHT / 3) 
				+ Constants.MENU_BUTTON_HEIGHT / 3 - Constants.STANDARD_PADDING / 3);
		back.setFrames(Assets.backButton);
	}

	
	private void loadPointingButtons() {
		
		//Creating the left pointer button
		Button pointerLeft = new Button(Constants.WIDTH / 3 - Constants.POINTER_WIDTH * 2
				,Constants.HEIGHT / 3 + Constants.POINTER_HEIGHT / 2);
		
		pointerLeft.setSize(Constants.POINTER_WIDTH, Constants.POINTER_HEIGHT);
		pointerLeft.setFrames(Assets.pointerLeft);
		
		//Creating the right pointer button
		Button pointerRight = new Button(
				Constants.WIDTH - Constants.WIDTH / 3 
				+ Constants.POINTER_WIDTH + Constants.STANDARD_MARGIN * 2
				, Constants.HEIGHT / 3 + Constants.POINTER_HEIGHT / 2);
		
		pointerRight.setSize(Constants.POINTER_WIDTH, Constants.POINTER_HEIGHT);
		pointerRight.setFrames(Assets.pointerRight);
	}
	
}
