package components;

import java.awt.Graphics;
import java.awt.Rectangle;

import constants.Constants;
import gfx.Assets;

public class TextField extends MenuComponent {
	
	private boolean isFocused;
	private Rectangle box;
	
	public TextField(String name) {
		super(Constants.MENU_BUTTON_X
				,Constants.MENU_BUTTON_Y + Constants.MENU_BUTTON_MARGIN_BOTTOM, name);

		super.setSize(Constants.MENU_BUTTON_WIDTH, Constants.MENU_BUTTON_HEIGHT);
		super.currentFrame = Assets.textField;
		
		this.setFocused(false);
		
		this.box = new Rectangle(super.xAxisPosition
				,super.yAxisPosition,super.width,super.height);
		
	}
	
	public boolean isFocused(){
		return this.isFocused;
	}
	
	public void setFocused(boolean focused){
		this.isFocused = focused;
	}
	
	public Rectangle getBox(){
		return this.box;
	}
	
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(this.currentFrame, super.xAxisPosition, super.yAxisPosition, null);
		
	}

	@Override
	public void tick() {
		
		
	}

}
