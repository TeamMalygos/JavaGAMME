package game.collectibles;

import java.awt.Graphics;
import java.awt.Graphics2D;

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
		
	}
	
	@Override
	public void tick() {
		
		if(!collected){
			
		}
	}

	@Override
	public void render(Graphics g) {
		if(super.animation.getFrame() == -1){
			return;
		}
		
		super.t.translate(this.position.getPositionX(), this.position.getPositionY());
		super.t.scale(1, 1);
		
		super.g2d = (Graphics2D)g;
		g2d.drawImage(super.animation.getImage(),super.t,null);
		
	}

	@Override
	public void loadSprite() {
		
		sheet = new SpriteSheet(Assets.diamond);
		sheet.setFrameLayersCount(frameCount);
		
	}
	
	public boolean collected(Player player){
		super.isCollected(player);
		return super.checkCollected();
	}

}
