package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import events.MenuButtonClickEvent;
import events.MenuButtonClickListener;
import states.GameState;
import states.State;

public class Button extends MenuComponent implements MenuButtonClickListener{
	
	private Color color;
	private Rectangle areaRect;
	private State stateInit;
	
	public Button(int x,int y,String name){
		super(x,y,name);
		areaRect = new Rectangle(x,y,0,0);
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public void setSize(int w,int h){
		super.setSize(w, h);
		areaRect = new Rectangle(super.xAxisPosition,super.yAxisPosition,w,h);
	}
	
	public Rectangle getArea(){
		return areaRect;
	}
	
	public void linkToState(State state){
		stateInit = state;
	}
	
	@Override
	public void tick(){
		//I have no fucking idea what this does but it looks fabulous
	}
	
	@Override
	public void render(Graphics g){
		
		g.setColor(color);
		g.fillRect(super.xAxisPosition,super.yAxisPosition,width,height);
		g.setColor(Color.BLACK);
		g.drawString(super.name, super.xAxisPosition + width/2, super.yAxisPosition + height/2);
	}

	@Override
	public void onMenuButtonClick() {
		// TODO Auto-generated method stub
		if(stateInit != null){
			MenuButtonClickEvent e = new MenuButtonClickEvent(this,stateInit);
		}
	}
	

}