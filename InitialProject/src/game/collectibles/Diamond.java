package game.collectibles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import constants.Constants;
import game.entities.Player;
import gfx.Assets;
import gfx.SpriteSheet;

public class Diamond extends LootObject{

	private SpriteSheet sheet;
	private int[] frameCount = { 8 };
	
	public Diamond(int id,double x,double y) {
		super(id, Constants.DIAMOND_WIDTH, Constants.DIAMOND_HEIGHT);
		super.setPosition(x, y);
		
		loadSprite();
		
		super.getAnimation().setFrames(sheet.getFrameSet(0));
		super.getAnimation().setFrame(0);
		super.getAnimation().setDelay(65);
		
	}
	
	public Diamond(int id){	
		this(id,0,0);
	}
	
	@Override
	public void tick() {
		super.getAnimation().update();
		

	}

	@Override
	public void render(Graphics g) {
		if(super.getAnimation().getFrame() == -1){
			return;
		}
		
		//super.g2d = (Graphics2D)g;
		//super.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//super.g2d.drawImage(super.animation.getImage(),super.t,null);
		g.drawImage(super.getAnimation().getImage()
				, (int)super.getPVector().getPositionX()
				, (int)super.getPVector().getPositionY(), null);
		//super.g2d.draw(new Rectangle2D.Double(super.position.getPositionX(),super.position.getPositionY(),super.width,super.height));
	}

	@Override
	public void loadSprite() {
		
		sheet = new SpriteSheet(Assets.diamond);
		sheet.setFrameLayersCount(this.frameCount,super.getWidth(),super.getHeight());
		
	}

}
