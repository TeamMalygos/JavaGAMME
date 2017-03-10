package game.entities;


import game.entities.playerProperties.Stats;
import game.entities.playerProperties.spells.abstractions.Spell;
import gfx.Assets;
import gfx.SpriteSheet;
import map.TileMap;
import providable.StateProvidable;
import utils.Animation;
import utils.CollisionBox;
import utils.LootBag;
import utils.MovementAttributes;
import utils.MovementState;
import utils.PVector;

import java.awt.*;

import constants.Constants;
import enums.ObjectState;

public class Player extends MapObject implements UnitDrawable,StateProvidable {

    private String name;
    private Rectangle playerPickUpBox;
    
    private Stats playerStats;

    //private int width, height, x, y, velocityX, velocityY, health;
    
    private boolean isDead;
    
    private LootBag bag;
    
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
        this.playerStats = new Stats(name);
        this.bag = this.playerStats.getBag();
        
        init();
        initPhysics();
        initPosition();

        loadSprites();

        super.movementState = new MovementState();
        //this.boundingBox = new Rectangle(x, y, width, height);
        
    }

    public Player(String name,TileMap map, Stats loadedStats) {
        super(map);

        this.name = name;
        this.playerStats = loadedStats;
        
        this.bag = this.playerStats.getBag();
        this.playerStats.setCurrentHealth(Constants.INITIAL_HEALTH);
        
        init();
        initPhysics();
        initPosition();

        loadSprites();
        
        super.movementState = new MovementState();
        //this.boundingBox = new Rectangle(x, y, width, height);

    }
    
    private void init(){

        super.facingRight = true;
    
        state = ObjectState.Idle;
        
        super.cBox = new CollisionBox(Constants.PLAYER_COLLISION_WIDTH,Constants.PLAYER_COLLISION_HEIGHT);
        super.width = Constants.PLAYER_WIDTH;
        super.height = Constants.PLAYER_HEIGHT;
        
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
    	
    	if(this.playerStats.getCurrentHealth() <= 0){
    		this.isDead = true;
    	}
    	
    	this.playerStats.tick();
    	getNextPosition();
    	super.checkTileMapCollision();
    	super.position.setPositionX(super.position.getTemporaryX());
    	super.position.setPositionY(super.position.getTemporaryY());
    	//super.position.determineDirection(this.movementState);
    	
    	if(super.position.getDirectionY() > 0){
    		if(this.state != ObjectState.Falling){
    			
    			this.state = ObjectState.Falling;
    			//Changing to falling animation
    			
    		}
    	}else if(this.position.getDirectionY() < 0){
    		
    		if(this.state != ObjectState.Jumping){
    			
    			this.state = ObjectState.Jumping;
    			this.animation.setFrames(this.sprite.getFrameSet(0));
    			this.animation.setDelay(40);
    			
    		}
    		
    	}else if(super.movementState.isGoingLeft() || super.movementState.isGoingRight()){
    		
    		if(this.state != ObjectState.Walking){
    			
    			this.state = ObjectState.Walking;
    			this.animation.setFrames(this.sprite.getFrameSet(ObjectState.Walking.ordinal()));
    			this.animation.setDelay(40);  
    		}
    		
    	}else{
    		if(this.state != ObjectState.Idle){
    			this.state = ObjectState.Idle;
    		}
    		this.animation.setFrames(this.sprite.getFrameSet(ObjectState.Idle.ordinal()));
    		this.animation.setDelay(40);
    	
    	}
    	
    	super.animation.update();
    	
    	setDirection();
    	
    }

    
    private void setDirection(){
    	
    	if(super.movementState.isGoingLeft()){super.facingRight = false;}
    	if(super.movementState.isGoingRight()){super.facingRight = true;}
    	
    }
    
    
    @Override
    public void render(Graphics g){
    	
    	super.setMapPosition();
    	
    	if(super.facingRight){
    		
    			g.drawImage(this.animation.getImage()
        				, (int)(super.position.getPositionX() + super.mapX - super.width /2)
        				, (int)(super.position.getPositionY() + super.mapY - super.height / 2)
        				, null);
    		
    	}else {
    		g.drawImage(this.animation.getImage()
    				, (int)(this.position.getPositionX() + super.mapX - super.width / 2 + super.width)
    				, (int)(this.position.getPositionY() + super.mapY - super.height / 2)
    				, -super.width
    				, super.height
    				, null);
    	}
    	/*
    	g.drawRect((int)(this.position.getPositionX() + super.mapX - super.width / 2 + super.width)
    			,(int)(this.position.getPositionY() + super.mapY - super.height / 2)
    			, width
    			, height);
    	*/
    	
    }
    
    public void takeDamage(double damage) {

        this.playerStats.takeDamage(damage);

    }

    public void castSpell(String spellType) {
        if (!this.getPlayerStats().getSpells().containsKey(spellType)) {
            System.out.println("You haven't learned " + spellType);
            return;
        }
        this.getPlayerStats().getSpells().get(spellType).cast();
    }

    public void learnSpell(String spellType) {
        this.playerStats.learnSpell(spellType);
    }

    public void levelUpSpell(String spellType) {
        this.playerStats.levelUpSpell(spellType);
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

    public String getName() {
        return name;
    }
    
    public LootBag getBag(){
    	return this.bag;
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
    public boolean isDead(){
    	return this.isDead;
    }
    public boolean canPickUp(Rectangle o){
    	this.playerPickUpBox = new Rectangle((super.tileMap.getX() + (int)super.position.getPositionX()) - super.cBox.getCollisionWidth() / 2
        		,super.tileMap.getY() + (int)super.position.getPositionY() - super.cBox.getCollisionHeight() / 2
        		,super.cBox.getCollisionWidth()
        		,super.cBox.getCollisionHeight());
    	
    	return this.playerPickUpBox.intersects(o);
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

	public Rectangle getPickUpRectangle() {
		return new Rectangle((super.tileMap.getX() + (int)super.position.getPositionX()) - super.cBox.getCollisionWidth() / 2
        		,super.tileMap.getY() + (int)super.position.getPositionY() - super.cBox.getCollisionHeight() / 2
        		,super.cBox.getCollisionWidth()
        		,super.cBox.getCollisionHeight());
	}
}