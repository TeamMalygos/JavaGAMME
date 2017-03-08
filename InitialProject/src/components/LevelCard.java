package components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import constants.Constants;
import gfx.ImageLoader;
import utils.Level;

public class LevelCard extends MenuComponent {

	private BufferedImage levelThumb;
	private Level level;

	private File jsonFile;
	private Rectangle body;
	
	public LevelCard(int x, int y, File jsonFile) {
		super(x, y, jsonFile.getName());
		this.setJsonFile(jsonFile);
		
		init();
		this.setBody(new Rectangle(x,y,Constants.LEVEL_CARD_WIDTH,Constants.LEVEL_CARD_HEIGHT));
		
	}
	
	private void init(){
		
		String levelNumber = "1";
		
		try{
			String[] mapArgs = this.jsonFile.getName().split("\\.");
			levelNumber = mapArgs[1];
		}catch(ArrayIndexOutOfBoundsException ex){
			ex.printStackTrace();
		}
		
		//this.setLevelThumb(ImageLoader.loadImage("/levels/map_thumb_" + levelNumber + ".png"));
		this.setLevel(Level.valueOf("Level" + levelNumber));
		
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void tick() {
		
	}

	public BufferedImage getLevelThumb() {
		return levelThumb;
	}

	public void setLevelThumb(BufferedImage levelThumb) {
		this.levelThumb = levelThumb;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		try{
			this.level = level;
		}catch(Exception ex){
			ex.printStackTrace();
			this.level = Level.Level1;
		}
	}

	public File getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(File jsonFile) {
		this.jsonFile = jsonFile;
	}

	public Rectangle getBody() {
		return body;
	}

	public void setBody(Rectangle body) {
		this.body = body;
	}
	

}
