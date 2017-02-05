package components;

import java.awt.Color;
import java.awt.Graphics;

import events.MenuButtonClickEvent;
import events.MenuButtonClickListener;

public class Button extends MenuComponent implements MenuButtonClickListener{
	
	private Color color;
	
	public Button(int x,int y,String name){
		super(x,y,name);
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public void setSize(int w,int h){
		super.setSize(w, h);
	}
	
	@Override
	public void tick(){
		//I have no fucking idea what this does but it looks fabulous
	}
	
	@Override
	public void render(Graphics g){
		
		g.setColor(color);
		g.drawRect(super.xAxisPosition,super.yAxisPosition,width,height);
		
	}

	@Override
	public void onMenuButtonClick(MenuButtonClickEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
