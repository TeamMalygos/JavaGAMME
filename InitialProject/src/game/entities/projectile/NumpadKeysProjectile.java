package game.entities.projectile;

import constants.Constants;
import game.entities.MapObject;
import game.entities.Projectile;
import gfx.Assets;

public class NumpadKeysProjectile extends Projectile {

	private int[] framesCount = { 12 };
	
	public NumpadKeysProjectile(MapObject obj) {
		
		super(obj);
		
		super.loadSprite(Assets.numKeysProjectile, framesCount,
				Constants.PROJECTILE_KEY_WIDTH, Constants.PROJECTILE_KEY_HEIGHT);
		
		super.getAnimation().setDelay(150);
		super.getAnimation().setFrames(super.sheet.getFrameSet(0));
		
	}

	
	private void fireProjectile(){
		
		super.getAnimation().update();

	}
	
	
	
	
}
