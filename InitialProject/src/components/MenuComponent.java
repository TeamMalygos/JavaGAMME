package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class MenuComponent {

	protected BufferedImage frames;
	protected BufferedImage currentFrame;
	protected boolean isPressed;
	protected boolean hover;
	protected int xAxisPosition;
	protected int yAxisPosition;
	protected int width;
	protected int height;
	protected String name;
	
	protected MenuComponent(int x,int y,String name){
		xAxisPosition = x;
		yAxisPosition = y;
		width = 0;
		height = 0;
		this.name = name;
		isPressed = false;
		hover = false;
	}
	
	protected abstract void render(Graphics g);
	protected abstract void tick();
	
	protected void setSize(int w,int h){
		width = w;
		height =h;
	}
	
}
