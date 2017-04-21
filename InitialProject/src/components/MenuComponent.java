package components;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import constants.Constants;
import game.entities.Drawable;

public abstract class MenuComponent implements Drawable{

	private BufferedImage frames;
	private BufferedImage currentFrame;
	
	private boolean isPressed;
	private boolean hover;
	
	private int xAxisPosition;
	private int yAxisPosition;
	private int width;
	private  int height;
	
	private Rectangle areaRect;
	
	public MenuComponent(int x,int y){
		
		this.setxAxisPosition(x);
		this.setyAxisPosition(y);
		
		this.setWidth(Constants.MENU_BUTTON_WIDTH);
		this.setHeight(Constants.MENU_BUTTON_HEIGHT);
		
		this.setPressed(false);
		this.setHover(false);
		
		this.setAreaRect(new Rectangle(this.xAxisPosition,this.yAxisPosition,this.width,this.height));
		
	}
	
	/**
	 * 
	 * This method returns weather certain coordinates x and y
	 * are in the button area.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isInside(int x,int y){
		
		Rectangle rect = this.getArea();
		if(x >= rect.getMinX() && y >= rect.getMinY()
				&& x <= rect.getMaxX() && y <= rect.getMaxY()){
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

	private void setxAxisPosition(int xAxisPosition) {
		this.xAxisPosition = xAxisPosition;
	}

	public int getyAxisPosition() {
		return yAxisPosition;
	}
	
	public int getWidth() {
		return width;
	}

	private void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage getCurrentFrame() {
		return this.currentFrame;
	}

	protected void setFrames(BufferedImage frames) {
		this.frames = frames;
	}

	protected BufferedImage getFrames() {
		return this.frames;
	}

	protected void setCurrentFrame(BufferedImage frame) {
		this.currentFrame = frame;
	}

	private void setyAxisPosition(int yAxisPosition) {
		this.yAxisPosition = yAxisPosition;
	}

	private void setHeight(int height) {
		this.height = height;
	}
	
}
