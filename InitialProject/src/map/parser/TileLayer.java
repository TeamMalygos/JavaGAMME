package map.parser;

import java.util.List;

public class TileLayer implements Layered{

	private List<Integer> data;
	private int height;
	private String name;
	
	private int offsetx;
	private int offsety;
	
	private int opacity;
	private String type;
	
	private boolean visible;
	private int width;
	private int x;
	private int y;
	public List<Integer> getData() {
		return this.data;
	}
	public void setData(List<Integer> data) {
		this.data = data;
	}
	public int getHeight() {
		return this.height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOffsetX() {
		return this.offsetx;
	}
	public void setOffsetX(int offsetX) {
		this.offsetx = offsetX;
	}
	public int getOffsetY() {
		return this.offsety;
	}
	public void setOffsetY(int offsetY) {
		this.offsety = offsetY;
	}
	public int getOpacity() {
		return this.opacity;
	}
	public void setOpacity(int opacity) {
		this.opacity = opacity;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isVisible() {
		return this.visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public int getWidth() {
		return this.width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getX() {
		return this.x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return this.y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
}
