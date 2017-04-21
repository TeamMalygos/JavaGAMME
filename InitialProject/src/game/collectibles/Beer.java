package game.collectibles;

import java.awt.Graphics;

import constants.Constants;
import gfx.Assets;
import gfx.SpriteSheet;
import states.GameState;

public class Beer extends LootObject implements PowerProvidable{

	private static final double HEALTH_REGENERATION_AMMOUNT = 50;
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
		
		this.sheet.setFrameLayersCount(this.framesCount, super.getWidth(), super.getHeight());
		
		super.getAnimation().setFrames(this.sheet.getFrameSet(0));
		super.getAnimation().setFrame(0);
		super.getAnimation().setDelay(30);
		
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
		
		g.drawImage(super.getAnimation().getImage()
				, (int)super.getPVector().getPositionX()
				, (int)super.getPVector().getPositionY(), null);
	}

	@Override
	public void onPickUp() {
		
		GameState.getPlayer().getPlayerStats().setCurrentHealth(
				GameState.getPlayer().getPlayerStats().getCurrentHealth()
				+ HEALTH_REGENERATION_AMMOUNT);
		
	}
	
}
