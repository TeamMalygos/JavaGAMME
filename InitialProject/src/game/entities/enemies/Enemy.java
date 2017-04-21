package game.entities.enemies;

import java.awt.image.BufferedImage;

import enums.ObjectState;
import game.entities.Drawable;
import game.entities.MapObject;
import gfx.SpriteSheet;
import map.TileMap;
import states.GameState;

public abstract class Enemy extends MapObject implements Drawable{

	private SpriteSheet sprite;
	private ObjectState state;
	
	private static final double MOVEMENT_RANGE = 52.20;
	private static final int IDLE_TIMER = 38;
	private static final int ATTACK_TIMER = 55;
	private static final long ENEMY_DEFAULT_ANIMATION_DELAY = 68;
	
	private int idleCount;
	
	private double startX;
	
	private boolean isDead;
	private boolean underAttack;
	
	protected Enemy(TileMap map,double startX) {
		
		super(map);
		
		this.startX = startX + 1;
		this.idleCount = 0;
		
		this.isDead = false;
		this.underAttack = false;
		
		this.state = ObjectState.WALKING;
	}

	public boolean isDead() {
		return this.isDead;
	}

	public boolean isUnderAttack(){
		return this.underAttack;
	}
	
	protected void updateMovement() {
		System.out.println(super.getMapX() + " " + super.getMapY());
		if(super.getPVector().getPositionX() == this.startX + MOVEMENT_RANGE || 
				super.getPVector().getPositionX() == this.startX - MOVEMENT_RANGE){
			this.idleCount += 1;
		}
		
		if(this.idleCount == IDLE_TIMER){
			//this.state = ObjectState.IDLE;
			super.setFacingRight(!super.isFacingRight());
			this.idleCount = 0;
		}
		
		if(this.idleCount > 0){
			return;
		}	
		
		calculateMovement();
		
	}
	
	protected void loadSpriteSheet(BufferedImage sheet
			,int[] framesCount,int width,int height){
		this.loadSpriteSheet(sheet, framesCount, width, height, ENEMY_DEFAULT_ANIMATION_DELAY);
	}
	
	//SpriteSheet overload with optional animation delay
	protected void loadSpriteSheet(BufferedImage sheet
			,int[] framesCount,int width,int height,long delay){
		this.sprite = new SpriteSheet(sheet);
		this.sprite.setFrameLayersCount(framesCount, width, height);
		
		super.getAnimation().setFrames(this.sprite.getFrameSet(this.state.ordinal()));
		super.getAnimation().setDelay(delay);
	}

	
	protected void setStatus(boolean isDead){
		this.isDead = isDead;
	}

	protected void checkPlayerCollision() {
	
		if(super.intersectsWith(GameState.getPlayer())){
	
			this.underAttack = true;
			if(this.idleCount >= ATTACK_TIMER){
				this.idleCount = 0;
				return;
			}
			
			this.idleCount++;
			GameState.getPlayer().takeDamage(100);
			
		}else{
			this.underAttack = false;
			this.idleCount = 0;
		}
		
	}
	
	
	private void calculateMovement() {
		if(super.isFacingRight()){
			if(super.getPVector().getPositionX() < this.startX + MOVEMENT_RANGE){
				super.getPVector().setPositionX(
						super.getPVector().getPositionX() 
						+ super.getObjectMovementAttr().getUnitAcceleration());
			}
			
			if(super.getPVector().getPositionX() > this.startX + MOVEMENT_RANGE){
				super.getPVector().setPositionX(startX + MOVEMENT_RANGE);
			}

			return;
		}
		if(super.getPVector().getPositionX() > this.startX - MOVEMENT_RANGE){
			super.getPVector().setPositionX(super.getPVector().getPositionX()
					-super.getObjectMovementAttr().getUnitAcceleration());
		}
		
		if(super.getPVector().getPositionX() < this.startX - MOVEMENT_RANGE){
			super.getPVector().setPositionX(startX - MOVEMENT_RANGE);
		}
	}
}
