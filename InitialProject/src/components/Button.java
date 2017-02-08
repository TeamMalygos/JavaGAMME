package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import events.MenuButtonClickEvent;
import events.MenuButtonClickListener;
import states.GameState;
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
		super.setSize(96, 28);
		super.frames = frames;
		areaRect = new Rectangle(super.xAxisPosition,super.yAxisPosition,96,28);
		loadFrameHolders();
	}
	
	private void loadFrameHolders(){
		
		normal = super.frames.getSubimage(0, 0, 96, 28);
		pressed = super.frames.getSubimage(96,0,96,28);
		hover = super.frames.getSubimage(192, 0, 96, 28);
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
		//I have no fucking idea what this does but it looks fabulous
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		super.isPressed = false;
		super.hover = true;
	}
	

}
