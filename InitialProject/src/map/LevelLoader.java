package map;

import utils.Level;

public class LevelLoader {

	private LevelParser mapParser;
	private Level level;
	private String path;
	
	public LevelLoader(int level){
		this.level = Level.values()[level-1];
		path = "/map"+level;
		prepareLevel();
		
	}
	
	public void loadLevel(){
		
	}
	
	public void prepareLevel(){
		
		
		mapParser = new LevelParser(path);
		
		
	}
	
	
	
}
