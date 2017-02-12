package components;

import java.awt.Color;
import java.awt.Graphics;

public class StringButton extends MenuComponent {
	
	public StringButton(int x,int y, String text){
		super(x,y,text);
	}
	
	public void setSize(int width,int height){
		super.setSize(width, height);
	}
	
	public void onStringMenuItemPress(){
		
	}
	
	public void onStringMenuItemRelease(){
	
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
	
	
}
