package states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.File;

import components.LevelCard;
import constants.Constants;
import events.MouseMotionSensitive;

import states.gui.Interface;
import states.gui.LevelSelectionInterface;

public class LevelsState extends State implements MouseMotionSensitive{
	
	private static final int ID = 3;
	private LevelCard[] levels;
	
	private Interface loadLevelInterface;
	
	public LevelsState() {
		super(ID);
		this.init();
		
	}

	private void init(){
		this.loadLevelInterface = new LevelSelectionInterface();
		
		String levelsPath = System.getProperty("user.dir") + "/maps";
		File mapsDir = new File(levelsPath);
		
		File[] levels = mapsDir.listFiles();
		this.levels = new LevelCard[levels.length];

		for(int i = 0 ; i < levels.length ; i++){
			this.levels[i] = new LevelCard(Constants.WIDTH / 2 - Constants.LEVEL_CARD_WIDTH / 3 - Constants.STANDARD_PADDING
					, Constants.HEIGHT / 3 - Constants.LEVEL_CARD_HEIGHT / 4 
					+ Constants.STANDARD_PADDING - Constants.STANDARD_PADDING / 4
					,levels[i]);
			
		}
		
	}
	
	@Override
	public void tick() {
		this.loadLevelInterface.tick();
	}

	@Override
	public void render(Graphics g) {
		this.loadLevelInterface.render(g);
		this.levels[this.getIndexOnFocus()].render(g);
	}

	@Override
	public void onMouseHover(MouseEvent args) {
		this.loadLevelInterface.onMouseHoverOverInterface(args);
	}

	@Override
	public void onMouseRelease(MouseEvent args) {
		this.loadLevelInterface.onMouseReleaseOverInterface(args);
	}


	@Override
	public void onMouseClick(MouseEvent args) {
		this.loadLevelInterface.onMouseClickOverInterface(args);
	}
	
	
	private int getIndexOnFocus() {
		return ((LevelSelectionInterface)(this.loadLevelInterface))
    		.getIndexOnFocus();
	}

}
