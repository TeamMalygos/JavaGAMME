package game.entities.projectile;

import constants.Constants;
import game.entities.MapObject;
import gfx.Assets;

public class NumpadKeysProjectile extends Projectile {

	private int[] framesCount = { 12 };
	
	public NumpadKeysProjectile(MapObject obj) {
		
		super(obj);
		
		super.loadSprite(Assets.numKeysProjectile, framesCount,
				Constants.PROJECTILE_KEY_WIDTH, Constants.PROJECTILE_KEY_HEIGHT);
		
	}

	
	private void fireProjectile(){
		
		super.getAnimation().update();

	}
	
	
	
	
}
