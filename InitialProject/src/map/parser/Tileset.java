package map.parser;

public class Tileset {
	
	private int columns;
	private int firstgrid;
	
	private String image;
	private int imageHeight;
	private int imageWidth;
	private int margin;
	
	private String name;	
	
	private int spacing;
	private int tileCount;
	private int tileHeight;
	private int tileWidth;
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	public int getFirstgrid() {
		return this.firstgrid;
	}
	public void setFirstgrid(int firstgrid) {
		this.firstgrid = firstgrid;
	}
	public String getImage() {
		return this.image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getImageHeight() {
		return this.imageHeight;
	}
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
	public int getImageWidth() {
		return this.imageWidth;
	}
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}
	public int getMargin() {
		return this.margin;
	}
	public void setMargin(int margin) {
		this.margin = margin;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSpacing() {
		return this.spacing;
	}
	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}
	public int getTileCount() {
		return this.tileCount;
	}
	public void setTileCount(int tileCount) {
		this.tileCount = tileCount;
	}
	public int getTileHeight() {
		return this.tileHeight;
	}
	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}
	public int getTileWidth() {
		return this.tileWidth;
	}
	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}
	
}
