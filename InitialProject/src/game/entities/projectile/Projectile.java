package game.entities.projectile;

import gfx.SpriteSheet;
import states.GameState;
import utils.CollisionBox;
import utils.PVector;

import java.awt.*;
import java.awt.image.BufferedImage;

import game.entities.Drawable;
import game.entities.EnemyShootingUnit;
import game.entities.MapObject;

public abstract class Projectile extends MapObject implements Shootable{
    
    
	private int damage;

    private SpriteSheet sheet;
    private Rectangle boundingBox;

    private int width;
    private int height;
    
    private boolean flyingRight;
    
    private int col;

    private MapObject shooter;

    protected Projectile() {
    	super(GameState.getMap());
    }
    
    private void init(){

        
        super.setCollisionBox(new CollisionBox(this.width,this.height));
        super.setPVector(new PVector(0,0));
        
        this.boundingBox = new Rectangle((int)super.getPVector().getPositionX()
        		, (int)super.getPVector().getPositionY(), this.width, this.height);
 
        //this.damage = shooter.getDamage();
    }
    
    private void isFlyingRight(){
    	if(this.shooter.isFacingRight()){
    		this.flyingRight = true;
    		this.getPVector().setDirectionX(1);
    		
    		this.getMovementState().setRight(true);
    		this.getMovementState().setLeft(false);
    	}else {
    		this.flyingRight = false;
    		this.getPVector().setDirectionX(-1);
    		
    		this.getMovementState().setLeft(true);
    		this.getMovementState().setRight(false);
    	}
    }
    

	public void loadSprite(BufferedImage sheet,int[] framesCount,int w, int h){
    	
    	this.sheet = new SpriteSheet(sheet);
    	this.sheet.setFrameLayersCount(framesCount, w,h );
    	
		super.getAnimation().setDelay(150);
		super.getAnimation().setFrames(this.sheet.getFrameSet(0));
    	
    	init();
    }

    @Override
    public boolean intersects(MapObject o) {
        return super.intersectsWith(o);
    }

    @Override
    public boolean isInRange() {
    	EnemyShootingUnit u = (EnemyShootingUnit)this.shooter;
        return super.getPVector().getPositionX() >= shooter.getPVector().getPositionX();
        
    }
}
