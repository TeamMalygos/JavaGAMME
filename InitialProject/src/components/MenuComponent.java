package components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import constants.Constants;

public abstract class MenuComponent{

	protected BufferedImage frames;
	protected BufferedImage currentFrame;
	protected boolean isPressed;
	protected boolean hover;
	protected int xAxisPosition;
	protected int yAxisPosition;
	protected int width;
	protected int height;
	protected String name;
	private Rectangle areaRect;
	
	public MenuComponent(int x,int y,String name){
		
		this.setxAxisPosition(x);
		this.setyAxisPosition(y);
		this.setWidth(Constants.MENU_BUTTON_WIDTH);
		this.setHeight(Constants.MENU_BUTTON_HEIGHT);
		this.setName(name);
		this.setPressed(false);
		this.setHover(false);
		this.setAreaRect(new Rectangle(this.xAxisPosition,this.yAxisPosition,this.width,this.height));
		
	}
	
	public boolean isInside(int mouseX,int mouseY){
		
		Rectangle rect = this.getArea();
		if(mouseX >= rect.getMinX() && mouseY >= rect.getMinY()
				&& mouseX <= rect.getMaxX() && mouseY <= rect.getMaxY()){
			return true;
		}
		
		return false;
	}
	
	
	public void setAreaRect(Rectangle rectangle) {
		this.areaRect = rectangle;
		
	}

	public Rectangle getArea(){
		return this.areaRect;
	}
	
	public boolean isHover() {
		return hover;
	}

	public void setHover(boolean hover) {
		this.hover = hover;
	}

	public abstract void render(Graphics g);
	public abstract void tick();
	
	public void setSize(int w,int h){
		width = w;
		height =h;
		
		this.setAreaRect(new Rectangle(this.xAxisPosition,this.yAxisPosition,w,h));
	}

	public boolean isPressed() {
		return isPressed;
	}

	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}

	public int getxAxisPosition() {
		return xAxisPosition;
	}

	public void setxAxisPosition(int xAxisPosition) {
		this.xAxisPosition = xAxisPosition;
	}

	public int getyAxisPosition() {
		return yAxisPosition;
	}

	public void setyAxisPosition(int yAxisPosition) {
		this.yAxisPosition = yAxisPosition;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
