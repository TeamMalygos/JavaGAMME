package components;

import java.awt.Graphics;

import constants.Constants;
import game.entities.Drawable;
import gfx.Assets;
import gfx.SpriteSheet;
import utils.Animation;

public class Pesho implements Drawable {

	private SpriteSheet sheet;
	private Animation animation;
	private final int[] framesCount = new int[]{
			4
	};
	
	public Pesho(){
		
		this.sheet = new SpriteSheet(Assets.pesho);
		this.sheet.setFrameLayersCount(this.framesCount, Constants.PESHO_WIDTH, Constants.PESHO_HEIGHT);
		
		this.animation = new Animation();
		this.animation.setFrames(this.sheet.getFrameSet(0));
		this.animation.setDelay(Constants.PESHO_ANIMATION_DELAY);
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(this.animation.getImage(), Constants.PESHO_X, Constants.PESHO_Y, null);
	}

	@Override
	public void tick() {
		this.animation.update();
	}
	
	
	
}
