package map.parser;

import utils.Level;

public class LevelLoader {

	private LevelParser mapParser;
	private Level level;
	private String path;
	
	public LevelLoader(Level level){
		this.level = level;
		path = "/map"+level.ordinal() + ".json";
		prepareLevel();
		
	}
	
	public void prepareLevel(){
		
		mapParser = new LevelParser(path);
		
	}
	
	public MapStructure getLevelData(){
		return mapParser.getMapData();
	}
	
}