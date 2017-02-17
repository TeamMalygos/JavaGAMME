package game.entities;

import gfx.SpriteSheet;
import states.GameState;
import utils.CollisionBox;
import utils.MovementAttributes;
import utils.MovementState;
import utils.PVector;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile extends MapObject implements Shootable{
    
    
	protected int damage;

    protected SpriteSheet sheet;
    protected Rectangle boundingBox;

    protected int width;
    protected int height;
    
    protected boolean flyingRight;
    
    protected int col;

    protected MapObject shooter;

    protected Projectile(MapObject obj) {
    	super(obj.tileMap);
        this.shooter = obj;
        
    	super.position = new PVector(obj.position.getPositionX()
    			,obj.position.getPositionY());
    }
    
    private void init(){

    	super.position.setDirectionY(0);
        
        super.cBox = new CollisionBox(this.width,this.height);

        super.objectMovementAttr = new MovementAttributes();
        super.movementState = new MovementState();
        
        this.boundingBox = new Rectangle((int)super.position.getPositionX()
        		, (int)super.position.getPositionY(), this.width, this.height);

        //this.damage = shooter.getDamage();
    }
    
    private void isFlyingRight(){
    	if(this.shooter.facingRight){
    		this.flyingRight = true;
    		this.position.setDirectionX(1);
    		
    		this.movementState.setRight(true);
    		this.movementState.setLeft(false);
    	}else {
    		this.flyingRight = false;
    		this.position.setDirectionX(-1);
    		
    		this.movementState.setLeft(true);
    		this.movementState.setRight(false);
    	}
    }
    
    public void loadSprite(BufferedImage sheet,int[] framesCount,int w, int h){
    	
    	this.sheet = new SpriteSheet(sheet);
    	this.sheet.setFrameLayersCount(framesCount, w,h );
    	init();
    }

    @Override
    public void tick() {
        // Flying in left direction
        //this.x -= this.velocityX;

        this.boundingBox.setBounds((int)super.position.getPositionX()
        		, (int)super.position.getPositionY(), this.width, this.height);
        
        
    }

        // Projectile hits target
        //if (super.intersects(player.getBoundingBox())) {

           // player.takeDamage(this.damage);
            //GameState.setPlayer(player);

            //System.out.println(GameState.getPlayer().getPlayerStats().getCurrentHealth());

           // this.enemyShootingUnit.getProjectiles().remove(this);

       // } else if (!this.isInRange()) {
       //     // Check whether projectile is out of range
         //   this.enemyShootingUnit.getProjectiles().remove(this);
      //  }
   // }
//
    @Override
    public void render(Graphics g) {

    	super.position.getNewPosition(this.movementState, this.objectMovementAttr);
    	super.tileMap.setPosition(this.position.getTemporaryX(), this.position.getTemporaryY());
    	
        g.drawImage(super.animation.getImage(), (int)super.position.getPositionX(), (int)super.position.getPositionY(), null);

    }

    @Override
    public boolean intersects(MapObject o) {
        return super.intersectsWith(o);
    }

    @Override
    public boolean isInRange() {
    	EnemyShootingUnit u = (EnemyShootingUnit)this.shooter;
        return super.position.getPositionX() >= shooter.position.getPositionX() - u.getShootingRange();
        
    }
}
