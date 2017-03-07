package states;

import java.awt.Graphics;
import java.io.File;

import components.LevelCard;

public class LevelsState extends State{
	
	private static final int ID = 3;
	private LevelCard[] levels;
	
	protected LevelsState() {
		super(ID);
		
		init();
		
	}

	private void init(){
		
		String levelsPath = System.getProperty("user.dir") + "/maps";
		File mapsDir = new File(levelsPath);
		
		File[] levels = mapsDir.listFiles();
		this.levels = new LevelCard[levels.length];
		
		int counter = 0;
		for(File level : levels){
			this.levels[counter] = new LevelCard(0,0,level);
			counter++;
		}
		
	}
	
	@Override
	public void tick() {
		
		//Animations tick
		
	}

	@Override
	public void render(Graphics g) {
		
		//Rendering animations
		
	}

}
