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
    	super(obj.getMap());
        this.shooter = obj;
        
    	super.setPVector(new PVector(obj.getPVector().getPositionX()
    			,obj.getPVector().getPositionY()));
    }
    
    private void init(){

    	super.getPVector().setDirectionY(0);
        
        super.setCollisionBox(new CollisionBox(this.width,this.height));
        
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
    	init();
    }

    @Override
    public void tick() {
        // Flying in left direction
        //this.x -= this.velocityX;

        this.boundingBox.setBounds((int)super.getPVector().getPositionX()
        		, (int)super.getPVector().getPositionY(), this.width, this.height);
        
        
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

    	super.getNewPosition();
    	super.getMap().setPosition(this.getPVector().getTemporaryX(), this.getPVector().getTemporaryY());
    	
        g.drawImage(super.getAnimation().getImage(), (int)super.getPVector().getPositionX()
        		, (int)super.getPVector().getPositionY(), null);

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
