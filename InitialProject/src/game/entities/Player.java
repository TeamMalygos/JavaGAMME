package game.entities;


import gfx.Assets;
import gfx.SpriteSheet;
import map.TileMap;
import providable.StateProvidable;
import utils.Animation;
import utils.CollisionBox;
import utils.MovementAttributes;
import utils.MovementState;
import utils.ObjectState;
import utils.PVector;

import java.awt.*;

import constants.Constants;

import static constants.Constants.DAMAGE_REDUCE_RATE;

public class Player extends MapObject implements UnitDrawable,StateProvidable{

    private String name;

    private Stats playerStats;

    //private int width, height, x, y, velocityX, velocityY, health;
    
    private boolean isDead;
    
    //Animation frames - Containers and helpful prereqs
    private SpriteSheet sprite;
    private final int[] numFrames = {
    		8,8,2
    };

    //States
    private ObjectState state;

    public Player(String name,TileMap map) {
    	super(map);
        this.name = name;
        super.facingRight = true;
        this.playerStats = new Stats(this);
        state = ObjectState.Idle;
        
        super.cBox = new CollisionBox(Constants.PLAYER_COLLISION_WIDTH,Constants.PLAYER_COLLISION_HEIGHT);
        super.width = Constants.PLAYER_WIDTH;
        super.height = Constants.PLAYER_HEIGHT;
        
        
        
        initPhysics();
        initPosition();

        loadSprites();

        super.movementState = new MovementState();
        //this.boundingBox = new Rectangle(x, y, width, height);
        
    }
    
    private void initPosition(){
        super.position = new PVector();
        super.position.setPositionX(Constants.PLAYER_START_X);
        super.position.setPositionY(Constants.PLAYER_START_Y);
        
    }
    
    private void initPhysics(){
        super.objectMovementAttr = new MovementAttributes();
        
        super.objectMovementAttr.setGravity(Constants.PLAYER_GRAVITY, Constants.PLAYER_TERMINAL_VELOCITY);
        super.objectMovementAttr.setJumpRate(Constants.PLAYER_JUMP, Constants.PLAYER_STOP_JUMP);
        super.objectMovementAttr.setMovementRate(Constants.PLAYER_ACCELERATION
        		, Constants.PLAYER_MAXIMUM_SPEED, Constants.PLAYER_DEACCELERATION);
    }
    
    public void loadSprites(){
    	
    	sprite = new SpriteSheet(Assets.nakov_sheet);
    	sprite.setFrameLayersCount(numFrames,Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT);
    	super.animation = new Animation();
    	super.animation.setDelay(1);
    	
    }
    
    private void getNextPosition(){
    	super.position.getNewPosition(super.movementState
    			, super.objectMovementAttr);
    }
    
    @Override
    public void tick() {
    	
    	super.animation.setFrames(this.sprite.getFrameSet(state.ordinal()));
    	super.animation.update();
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
  
    	g.drawImage(super.animation.getImage()
    			, (int)super.position.getPositionX()
    			, (int)super.position.getPositionY(), null);
    }
    
    public void takeDamage(double damage) {

        this.playerStats.takeDamage(damage);

    }

    public void gainExperience(int experienceAmount) {
        this.playerStats.gainExperience(experienceAmount);
    }

    public void gainHealth(int healthAmount) {
        this.playerStats.gainHealth(healthAmount);
    }

    public void gainMana(int manaAmount) {
        this.playerStats.gainMana(manaAmount);
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
