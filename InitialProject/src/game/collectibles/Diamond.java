package game.collectibles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import constants.Constants;
import game.entities.Player;
import gfx.Assets;
import gfx.SpriteSheet;

public class Diamond extends LootObject {

	private SpriteSheet sheet;
	private int[] frameCount = { 8 };
	
	public Diamond(int id,double x,double y) {
		super(id, Constants.DIAMOND_WIDTH, Constants.DIAMOND_HEIGHT);
		super.setPosition(x, y);
		
		loadSprite();
		
		super.animation.setFrames(sheet.getFrameSet(0));
		super.animation.setFrame(0);
		super.animation.setDelay(65);
		
	}
	
	@Override
	public void tick() {
		super.animation.update();
		

	}

	@Override
	public void render(Graphics g) {
		if(super.animation.getFrame() == -1){
			return;
		}
		
		g.drawImage(super.animation.getImage()
				, (int)super.position.getPositionX()
				, (int)super.position.getPositionY(), null);
	}

	@Override
	public void loadSprite() {
		
		sheet = new SpriteSheet(Assets.diamond);
		sheet.setFrameLayersCount(frameCount,Constants.DIAMOND_WIDTH,Constants.DIAMOND_HEIGHT);
		
	}

}
