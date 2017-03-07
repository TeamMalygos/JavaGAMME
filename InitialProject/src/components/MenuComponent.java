package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

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
	
	protected MenuComponent(int x,int y,String name){
		this.setxAxisPosition(x);
		this.setyAxisPosition(y);
		this.setWidth(0);
		this.setHeight(0);
		this.setName(name);
		this.setPressed(false);
		this.setHover(false);
	}
	
	public boolean isHover() {
		return hover;
	}

	public void setHover(boolean hover) {
		this.hover = hover;
	}

	protected abstract void render(Graphics g);
	protected abstract void tick();
	
	public void setSize(int w,int h){
		width = w;
		height =h;
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
