package map.parser;

public class ObjectsLayerObject{

	private int gid;
	
	private int height;
	
	private int id;
	private String name;
	private int rotation;
	
	private String type;
	private boolean visible;
	
	private int width;
	
	private double x;
	private double y;
	
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public int getHeight() {
		return this.height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getWidth() {
		return this.width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRotation() {
		return this.rotation;
	}
	public void setRotation(int rotation) {
		this.rotation = rotation;
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
	public double getX() {
		return this.x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return this.y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
}
