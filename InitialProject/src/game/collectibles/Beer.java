package game.collectibles;

import java.awt.Graphics;

import constants.Constants;
import gfx.Assets;
import gfx.SpriteSheet;

public class Beer extends LootObject implements PowerProvidable{

	private SpriteSheet sheet;
	private final int[] framesCount = {
		19
	};
	
	public Beer(int id){
		super(id,Constants.BEER_WIDTH,Constants.BEER_HEIGHT);
		loadSprite();
	}

	@Override
	public void loadSprite() {
		
		this.sheet = new SpriteSheet(Assets.beer);
		
		this.sheet.setFrameLayersCount(this.framesCount, super.width, super.height);
		
		super.animation.setFrames(this.sheet.getFrameSet(0));
		super.animation.setFrame(0);
		super.animation.setDelay(30);
		
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
	public void onPickUp() {
		
		
		
	}
	
}
