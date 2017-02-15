package map.parser;

import java.util.List;

public class MapStructure {

	private int height;
	private int width;
	
	private Layered[] layers;
	
	private int nextobjectid;
	
	private String orientation;
	private String renderorder;
	
	private int tileHeight;
	private List<Tileset> tilesets;
	private int tileWidth;
	private int version;
	
	
	
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public TileLayer getTileLayer() {
		return (TileLayer)layers[0];
	}
	public ObjectsLayer getObjectsLayer() {
		return (ObjectsLayer)layers[1];
	}
	public int getNextobjectid() {
		return nextobjectid;
	}
	public String getOrientation() {
		return orientation;
	}
	public String getRenderorder() {
		return renderorder;
	}
	public int getTileHeight() {
		return tileHeight;
	}
	public List<Tileset> getTilesets() {
		return tilesets;
	}
	public int getTileWidth() {
		return tileWidth;
	}
	public int getVersion() {
		return version;
	}
	
	public void setHeight(int height){this.height = height;}
	public void setWidth(int width){this.width = width;}
	public void setLayer(Layered[] layers){this.layers = layers;}
	public void setNextObjectId(int nextobjectid){this.nextobjectid = nextobjectid;}
	public void setOrientation(String orientation){this.orientation = orientation;}
	public void setRenderOrder(String renderorder){this.renderorder = renderorder;}
	public void setTileHeight(int tileHeight){this.tileHeight = tileHeight;}
	public void setTileSets(List<Tileset> tilesets){this.tilesets = tilesets;}
	public void setTileWidth(int tileWidth){this.tileWidth = tileWidth;}
	public void setVersion(int version){this.version = version;} 
	
	
}
