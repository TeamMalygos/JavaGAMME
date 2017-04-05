package map.parser;

import java.util.List;

import enums.Level;

public class LevelLoader implements Loader {

	private LevelParser mapParser;
	private String path;
	
	public LevelLoader(Level level){
		path = "/map."+(level.ordinal()+1) + ".json";
		this.mapParser = new LevelParser(path);
		
	}
	
	@Override
	public List<Integer> getMapMatrix(){
		return this.mapParser.getMapData().getTileLayer().getData();
	}
	
	@Override
	public int getMapHeight(){
		return this.mapParser.getMapData().getTileLayer().getHeight();
	}
	
	@Override
	public int getMapWidth(){
		return this.mapParser.getMapData().getTileLayer().getWidth();
	}

	@Override
	public int getMatrixOffsetX() {
		return this.mapParser.getMapData().getTileLayer().getOffsetX();
	}

	@Override
	public int getMatrixOffsetY() {
		return this.mapParser.getMapData().getTileLayer().getOffsetY();
	}

	@Override
	public ObjectsLayer getObjects() {
		return this.mapParser.getMapData().getObjectsLayer();
	}

	@Override
	public TileLayer getLootLayer() {
		return this.mapParser.getMapData().getLootLayer();
	}

	@Override
	public int getLootMatrixOffsetX() {
		return this.mapParser.getMapData().getLootLayer().getOffsetX();
	}

	@Override
	public int getLootMatrixOffsetY() {
		return this.mapParser.getMapData().getLootLayer().getOffsetY();
	}
	
}