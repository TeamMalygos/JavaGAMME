package components;

import java.awt.Graphics;
import java.awt.Rectangle;

import constants.Constants;

public class TextField extends MenuComponent{
	
	private boolean isFocused;
	private Rectangle box;
	
	public TextField() {
		super(Constants.MENU_BUTTON_X
				,Constants.MENU_BUTTON_Y + Constants.MENU_BUTTON_MARGIN_BOTTOM);

		super.setSize(Constants.MENU_BUTTON_WIDTH, Constants.MENU_BUTTON_HEIGHT);
		
		this.setFocused(false);
		
		this.box = new Rectangle(super.getxAxisPosition()
				,super.getyAxisPosition(),super.getWidth(),super.getHeight());
		
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
		g.drawImage(super.getCurrentFrame(), super.getxAxisPosition(), super.getyAxisPosition(), null);
		
	}

	@Override
	public void tick() {
		
		
	}

}
