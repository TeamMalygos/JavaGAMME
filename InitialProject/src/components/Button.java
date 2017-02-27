package components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import constants.Constants;

import events.MenuButtonClickEvent;
import events.MenuButtonClickListener;

import states.State;

public class Button extends MenuComponent implements MenuButtonClickListener{
	
	private BufferedImage pressed;
	private BufferedImage normal;
	private BufferedImage hover;
	private Rectangle areaRect;
	private State stateInit;
	
	public Button(int x,int y,String name){
		super(x,y,name);
	}
	
	
	public void setFrames(BufferedImage frames){		
		super.setSize(Constants.MENU_BUTTON_WIDTH, Constants.MENU_BUTTON_HEIGHT);
		super.frames = frames;
		areaRect = new Rectangle(super.xAxisPosition,super.yAxisPosition,super.width,super.height);
		loadFrameHolders();
	}
	
	private void loadFrameHolders(){
		
		normal = super.frames.getSubimage(0,0,super.width,super.height);
		pressed = super.frames.getSubimage(super.width,0,super.width,super.height);
		hover = super.frames.getSubimage(super.width*2, 0, super.width, super.height);
		super.currentFrame = normal;
	}
	
	
	public void linkToState(State state){
		stateInit = state;
	}
	
	public Rectangle getArea(){return areaRect;}
	public void setHover(boolean hover){super.hover = hover;}
	public void setPressed(boolean pressed){super.isPressed = pressed;}
	
	@Override
	public void tick(){
		if(super.hover){
			super.currentFrame = this.hover;
		}else if(super.isPressed){
			super.currentFrame = this.pressed;
		}else {
			super.currentFrame = normal;
		}
	}
	
	@Override
	public void render(Graphics g){
		
		g.drawImage(super.currentFrame, super.xAxisPosition
				, super.yAxisPosition,null);
		

	}

	@Override
	public void onMenuButtonClick() {
		super.isPressed = true;
		super.hover = false;
	}


	@Override
	public void onMenuButtonRelease() {
		if(stateInit != null){
			MenuButtonClickEvent e = new MenuButtonClickEvent(this,stateInit);
		}
	}


	@Override
	public void onMenuButtonHover() {
		super.isPressed = false;
		super.hover = true;
	}
	

}
