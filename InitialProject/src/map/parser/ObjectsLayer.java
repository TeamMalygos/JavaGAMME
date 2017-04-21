package map.parser;

import java.util.List;

public class ObjectsLayer implements Layered{

	private String draworder;
	private int height;
	private String name;
	
	private String type;
	private boolean visible;
	private int width;
	
	private int x;
	private int y;
	
	List<ObjectsLayerObject> objects;
	
	private int offsetX;
	private int offsetY;
	
	private int opacity;
	
	public String getDraworder() {
		return this.draworder;
	}
	public void setDraworder(String draworder) {
		this.draworder = draworder;
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
	public List<ObjectsLayerObject> getObjects() {
		return this.objects;
	}
	public void setObjects(List<ObjectsLayerObject> objects) {
		this.objects = objects;
	}
	public int getOffsetX() {
		return this.offsetX;
	}
	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}
	public int getOffsetY() {
		return this.offsetY;
	}
	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
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
		return width;
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
