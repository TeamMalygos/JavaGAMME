package game.entities.projectile;

import java.awt.Graphics;

import constants.Constants;
import gfx.Assets;

public class NumpadKeysProjectile extends Projectile implements Weapon {

	private int[] framesCount = { 12 };
	private boolean isFired;
	
	public NumpadKeysProjectile() {
		super();
		this.isFired = false;
		super.loadSprite(Assets.numKeysProjectile, framesCount,
				Constants.PROJECTILE_KEY_WIDTH, Constants.PROJECTILE_KEY_HEIGHT);
	}

	@Override
	public void attack(double x,double y) {
		
		if(this.isFired){
			return;
		}
		
		super.getPVector().setPositionX(x);
		super.getPVector().setPositionY(y);
		
		this.isFired = true;
		tick();
	}

	@Override
	public void tick() {
		super.getPVector().setPositionX(super.getPVector().getPositionX()
				+ Constants.NUMPAD_KEY_PROJECTILE_SPEED);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(super.getAnimation().getImage()
				,(int)super.getPVector().getPositionX()
				,(int)super.getPVector().getPositionY()
				,null);
		tick();
		
	}
	
	
}
