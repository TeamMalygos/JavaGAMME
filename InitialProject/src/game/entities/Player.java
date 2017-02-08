package game.entities;


import gfx.Assets;
import gfx.SpriteSheet;
import map.TileMap;
import providable.StateProvidable;
import utils.Animation;
import utils.CollisionBox;
import utils.MovementAttributes;
import utils.MovementState;
import utils.PVector;

import java.awt.*;

import static constants.Constants.DAMAGE_REDUCE_RATE;

public class Player extends MapObject implements UnitDrawable,StateProvidable{

    private String name;

    private Stats playerStats;

    //private int width, height, x, y, velocityX, velocityY, health;
    
    private boolean isDead;
    
    //Animation frames - Containers and helpful prereqs
    SpriteSheet sprite;
    private final int[] numFrames = {
    		0 , 0 , 0 , 0
    };

    //States
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;

    public Player(String name,TileMap map) {
    	super(map);
        this.name = name;

        this.playerStats = new Stats(this);
        
        super.cBox = new CollisionBox(20,20);
        super.objectMovementAttr = new MovementAttributes();
        
        super.objectMovementAttr.setGravity(0.21875, 4.0);
        super.objectMovementAttr.setJumpRate(-6.5, 0.3);
        super.objectMovementAttr.setMovementRate(0.046875, 1.6, 0.5);
        super.facingRight = true;
        
        super.position = new PVector();
        super.position.setPositionX(2);
        super.position.setPositionY(5);
        
        
        
        loadSprites();

        super.movementState = new MovementState();
        //this.boundingBox = new Rectangle(x, y, width, height);
        
    }
    
    public void loadSprites(){
    	
    	sprite = new SpriteSheet(Assets.nakov_sheet,32,32);
    	sprite.setFrameLayersCount(numFrames);
    	super.animation = new Animation();
    	
    }
    
    private void getNextPosition(){
    	super.position.getNewPosition(super.movementState
    			, super.objectMovementAttr);
    }
    
    @Override
    public void tick() {
        

        //Isn't this supposed to be below the lower if() ???

        //if (isMovingRight) {
         //   this.x += this.velocityX;
       // } else if (isMovingLeft) {
         //   this.x -= this.velocityX;
        //}
    }

    
    
    @Override
    public void render(Graphics g) {
        
    	getNextPosition();
    	
    }

    public void takeDamage(double damage) {

        this.getPlayerStats().setCurrentHealth(
                this.getPlayerStats().getCurrentHealth() - (damage - this.getPlayerStats().getArmor() * DAMAGE_REDUCE_RATE));

    }

    public boolean intersects(MapObject object) {
        return super.intersectsWith(object);
    }


    public Stats getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(Stats playerStats) {
        this.playerStats = playerStats;
    }

    public double getX() {return super.position.getPositionX();}
    public void setX(int x) {super.position.setPositionX(x);}
    public double getY() {return super.position.getPositionY();}
    public void setY(int y) {super.position.setPositionY(y);}
    public Rectangle getBoundingBox() {
        return new Rectangle(
        		(int)super.position.getPositionX(),
        		(int)super.position.getPositionY(),
        		super.cBox.getCollisionWidth(),
        		super.cBox.getCollisionHeight());
    }

	@Override
	public boolean isMovingLeft() {return super.movementState.isGoingLeft();}
	@Override
	public boolean isMovingRight() {return super.movementState.isGoingRight();}
	@Override
	public boolean isMovingUp() {return super.movementState.isGoingUp();}
	@Override
	public boolean isMovingFalling() {return super.movementState.isFalling();}
	@Override
	public boolean isJumping() {return super.movementState.isJumping();}
	@Override
	public boolean isGoingDown() {return super.movementState.isGoingDown();}

	//Setters for movement state
	@Override
	public void setLeft(boolean left) {super.movementState.setLeft(left);}
	@Override
	public void setRight(boolean right) {super.movementState.setRight(right);}
	@Override
	public void setDown(boolean down) {super.movementState.setDown(down);}
	@Override
	public void setUp(boolean up) {super.movementState.setUp(up);}
	@Override
	public void setJumping(boolean jump) {super.movementState.setJump(jump);}
	@Override
	public void setFalling(boolean fall) {super.movementState.setFalling(fall);}
	
}
