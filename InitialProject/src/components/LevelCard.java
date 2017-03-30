package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import constants.Constants;
import enums.Level;
import gfx.ImageLoader;

public class LevelCard extends MenuComponent {

	private BufferedImage levelThumb;
	private Level level;
	private int reward;
	
	private File jsonFile;
	private Rectangle body;
	
	private boolean isOnFocus;
	
	public LevelCard(int x, int y, File jsonFile) {
		super(x, y);
		this.setJsonFile(jsonFile);
	
		init();
		this.setBody(new Rectangle(x,y,Constants.LEVEL_CARD_WIDTH,Constants.LEVEL_CARD_HEIGHT));
		this.setFocus(false);
	}
	
	public void setFocus(boolean b) {
		this.isOnFocus = b;
	}
	
	public boolean isOnFocus(){
		return this.isOnFocus;
	}

	@Override
	public void render(Graphics g){
		
		//Level Thumbnail
		g.drawImage(this.levelThumb,super.getxAxisPosition(),super.getyAxisPosition(),null);
		
		//Reward string
		g.setColor(Color.WHITE);
		g.setFont(new Font(Constants.FONT,Font.BOLD,Constants.FONT_SIZE));
		g.drawString("Reward: " + this.reward + " Diamonds"
				, super.getxAxisPosition() + super.getWidth() / 5 
				- Constants.STANDARD_PADDING - Constants.STANDARD_MARGIN / 2 - 3
				, super.getyAxisPosition() + super.getHeight() * 4 
				+ Constants.STANDARD_PADDING + Constants.STANDARD_MARGIN / 2);
		
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
	
	public int getReward(){
		return this.reward;
	}
	
	public void setReward(int r) {
		// TODO Auto-generated method stub
		this.reward = r;
	}	
	
	private void init(){
		
		String levelNumber = "1";
		
		try{
			String[] mapArgs = this.jsonFile.getName().split("\\.");
			levelNumber = mapArgs[1];
		}catch(ArrayIndexOutOfBoundsException ex){
			ex.printStackTrace();
		}
		
		this.setLevelThumb(ImageLoader.loadImage(Constants.LEVEL_THUMBNAILS_DIRECTORY + levelNumber + ".png"));
		this.setReward(Constants.LEVEL_REWARD[Integer.valueOf(levelNumber)-1]);
		this.setLevel(Level.valueOf("Level" + levelNumber));
		
	}


}
