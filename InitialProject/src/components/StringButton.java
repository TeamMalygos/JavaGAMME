package components;

import java.awt.Color;
import java.awt.Graphics;

import events.MenuButtonClickEvent;
import states.State;

public class StringButton extends MenuComponent {
	
	private State state;
	
	public StringButton(int x,int y, String text){
		super(x,y,text);
	}
	
	public void setSize(int width,int height){
		super.setSize(width, height);
	}
	
	public void onStringMenuItemPress(){
		
	}
	
	public void onStringMenuItemRelease(){
		if(state != null){
			MenuButtonClickEvent e = new MenuButtonClickEvent(this,state);
		}
	}
	
	public void linkToState(State state){
		this.state = state;
	}
	
	public void onStringMenuItemHover(){
		
	}
	
	@Override
	public void tick(){
		
	}
	
	@Override
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.drawString(super.name, super.xAxisPosition, super.yAxisPosition);
	}
	
	public int getPositionX(){
		return super.xAxisPosition; 
	}
	
	public int getPositionY(){
		return super.yAxisPosition;
	}
	
	
}