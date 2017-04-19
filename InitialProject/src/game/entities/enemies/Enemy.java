package game.entities.enemies;

import java.awt.image.BufferedImage;

import enums.ObjectState;
import game.entities.Drawable;
import game.entities.MapObject;
import gfx.SpriteSheet;
import map.TileMap;

public abstract class Enemy extends MapObject implements Drawable{

	private SpriteSheet sprite;
	private ObjectState state;
	
	private boolean isDead;
	
	protected Enemy(TileMap map,BufferedImage sheet
			,int[] framesCount,int width,int height) {
		
		super(map);
		
		this.isDead = false;
		this.state = ObjectState.WALKING;
		
		this.sprite = new SpriteSheet(sheet);
		this.sprite.setFrameLayersCount(framesCount, width, height);
		
		super.getAnimation().setFrames(this.sprite.getFrameSet(this.state.ordinal()));
	}

	public boolean isDead() {
		return this.isDead;
	}
	
	
}
