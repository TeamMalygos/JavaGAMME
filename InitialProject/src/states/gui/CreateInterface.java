package states.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import components.Button;
import components.TextField;
import components.interfaces.IButton;
import constants.Constants;
import gfx.Assets;
import states.LevelsState;

public class CreateInterface implements Interface {

	private TextField field;
	private IButton create;
	private IButton exit;
	
	public CreateInterface(){
		this.init();		
	}

	@Override
	public void tick() {
		this.create.tick();
		this.exit.tick();
		this.field.tick();	
	}

	@Override
	public void render(Graphics g) {
		
		g.setFont(new Font(Constants.FONT,Font.PLAIN,Constants.FONT_SIZE));

		this.create.render(g);
		this.field.render(g);
		this.exit.render(g);
		
		g.drawImage(Assets.selector
				,Constants.MENU_BUTTON_X + (Constants.MENU_BUTTON_WIDTH/2) - (Assets.selector.getWidth() / 2)
				,Constants.MENU_BUTTON_Y - Constants.STANDARD_PADDING
				,null);
	}


	@Override
	public void onMouseHoverOverInterface(MouseEvent args) {
		
		this.create.onMenuButtonHover(args.getX(),args.getY());
		this.exit.onMenuButtonHover(args.getX(), args.getY());
	}

	@Override
	public void onMouseReleaseOverInterface(MouseEvent args) {	
		
		if(this.field.isInside(args.getX(),args.getY())){
			this.field.setFocused(true);
		}else {
			this.field.setFocused(false);
		}
		
		this.exit.onMenuButtonRelease(args.getX(), args.getY());
		
	}

	@Override
	public void onMouseClickOverInterface(MouseEvent args){
		
		this.create.onMenuButtonClick(args.getX(), args.getY());
		this.exit.onMenuButtonClick(args.getX(), args.getY());
		
	}

	public boolean isTextFieldFocused() {
		return this.field.isFocused();
	}
	
	public boolean isInsideClickButton(int x, int y) {
		
		return ((Button)this.create).isInside(x, y);
	}
	
	
	private void init() {
		
		Button create = new Button(Constants.MENU_BUTTON_X
				,Constants.MENU_BUTTON_Y + (Constants.MENU_BUTTON_MARGIN_BOTTOM * 2));
		create.setFrames(Assets.createButton);
		create.linkToState(new LevelsState());
	
		Button exit = new Button(Constants.MENU_BUTTON_X
				,Constants.MENU_BUTTON_Y + (Constants.MENU_BUTTON_MARGIN_BOTTOM * 3));
		exit.setFrames(Assets.quitButton);
		
		TextField field = new TextField();
		
		this.create = create;
		this.exit = exit;
		this.field = field;
	}
}
