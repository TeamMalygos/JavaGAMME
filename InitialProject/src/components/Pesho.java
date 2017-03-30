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
			
	};
	
	public Pesho(String name){
		
		this.sheet = new SpriteSheet(Assets.pesho);
		this.sheet.setFrameLayersCount(this.framesCount, Constants.PESHO_WIDTH, Constants.PESHO_HEIGHT);
		
		this.animation = new Animation();
		this.animation.setFrames(this.sheet.getFrameSet(0));
		this.animation.setDelay(75);
		
	}

	@Override
	public void render(Graphics g) {
		
		
	}

	@Override
	public void tick() {
		
		
	}
	
	
	
}
