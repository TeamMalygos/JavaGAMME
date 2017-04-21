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
		try{
			return (TileLayer)layers[0];
		}catch(ArrayIndexOutOfBoundsException ex){
			ex.printStackTrace();
		}catch(ClassCastException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public ObjectsLayer getObjectsLayer() {
		try{
			return (ObjectsLayer)layers[1];
		}catch(ArrayIndexOutOfBoundsException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public TileLayer getLootLayer(){
		try{
			return (TileLayer)layers[2];
		}catch(ArrayIndexOutOfBoundsException ex){

		}catch(ClassCastException ex){
			
		}
		return null;
	}
	public int getNextobjectid() {
		return this.nextobjectid;
	}
	public String getOrientation() {
		return this.orientation;
	}
	public String getRenderorder() {
		return this.renderorder;
	}
	public int getTileHeight() {
		return this.tileHeight;
	}
	public List<Tileset> getTilesets() {
		return this.tilesets;
	}
	public int getTileWidth() {
		return this.tileWidth;
	}
	public int getVersion() {
		return this.version;
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
