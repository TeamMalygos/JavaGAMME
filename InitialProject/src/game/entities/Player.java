package game.entities;


import game.entities.playerProperties.Stats;
import game.entities.projectile.Weapon;
import gfx.Assets;
import gfx.SpriteSheet;
import map.TileMap;

import utils.CollisionBox;
import utils.LootBag;

import utils.PVector;

import java.awt.*;

import constants.Constants;
import enums.ObjectState;

public class Player extends MapObject implements Drawable {

    private String name;
    private Rectangle playerPickUpBox;
    
    private Stats playerStats;

    //private int width, height, x, y, velocityX, velocityY, health;
    
    private boolean isDead;
    
    private LootBag bag;
    private Weapon weapon;
    
    //Animation frames - Containers and helpful prereqs
    private SpriteSheet sprite;
    private final int[] numFrames = {
    		8,8,2
    };

    //States
    private ObjectState state;

    public Player(String name,TileMap map,int x,int y) {
    	super(map);
    	
        this.name = name;
        this.playerStats = new Stats(name);
        this.bag = this.playerStats.getBag();
        
        init();
        initPhysics();
        initPosition(x,y);

        loadSprites();

        //this.boundingBox = new Rectangle(x, y, width, height);
        
    }

    public Player(TileMap map, Stats loadedStats,int x,int y) {
        super(map);
        
        this.playerStats = loadedStats;
        this.bag = this.playerStats.getBag();
        this.playerStats.setCurrentHealth(Constants.INITIAL_HEALTH);
        
        init();
        initPhysics();
        initPosition(x,y);

        loadSprites();
        
        //this.boundingBox = new Rectangle(x, y, width, height);

    }
    
    public void loadSprites(){
    	
    	this.sprite = new SpriteSheet(Assets.nakov_sheet);
    	
    	this.sprite.setFrameLayersCount(this.numFrames,Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT);
    	
    	super.getAnimation().setDelay(1);
    	
    }
    
 
    @Override
    public void tick() {
    	
    	///boolean isTileOfTypeDeath = super.getCollisionBox().isOfTypeDeath(
    			//super.getPVector().getPositionX()
    			//, super.getPVector().getDestinationY(), super.getMap());
    	
    	if(this.playerStats.getCurrentHealth() <= 0){
    		this.isDead = true;
    	}
    	
    	this.playerStats.tick();
    	getNextPosition();
    	super.checkTileMapCollision();
    	super.getPVector().setPositionX(super.getPVector().getTemporaryX());
    	super.getPVector().setPositionY(super.getPVector().getTemporaryY());
    	//super.position.determineDirection(this.movementState);
    	
    	changePlayerAnimation();
    	
    	super.getAnimation().update();
    	
    	setDirection();
    	
    }
    
    @Override
    public void render(Graphics g){
    	
    	super.setMapPosition();
    	
    	drawPlayer(g);
    	
    	this.weapon.render(g);
    	//Player bounding box debug
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

    public double getX() {return super.getPVector().getPositionX();}
    public void setX(int x) {super.getPVector().setPositionX(x);}
    public double getY() {return super.getPVector().getPositionY();}
    public void setY(int y) {super.getPVector().setPositionY(y);}
    public Rectangle getBoundingBox() {
        return new Rectangle(
        		(int)super.getPVector().getPositionX(),
        		(int)super.getPVector().getPositionY(),
        		super.getCollisionBox().getCollisionWidth(),
        		super.getCollisionBox().getCollisionHeight());
    }
    public boolean isDead(){
    	return this.isDead;
    }
    public boolean canPickUp(Rectangle o){
    	this.playerPickUpBox = new Rectangle((super.getMap().getX() 
    			+ (int)super.getPVector().getPositionX()) - super.getCollisionBox().getCollisionWidth() / 2
        		,super.getMap().getY() + (int)super.getPVector().getPositionY() 
        		- super.getCollisionBox().getCollisionHeight() / 2
        		,super.getCollisionBox().getCollisionWidth()
        		,super.getCollisionBox().getCollisionHeight());
    	
    	return this.playerPickUpBox.intersects(o);
    }
    
	public boolean isMovingLeft() {return super.getMovementState().isGoingLeft();}
	public boolean isMovingRight() {return super.getMovementState().isGoingRight();}
	public boolean isMovingUp() {return super.getMovementState().isGoingUp();}
	public boolean isMovingFalling() {return super.getMovementState().isFalling();}
	public boolean isJumping() {return super.getMovementState().isJumping();}
	public boolean isGoingDown() {return super.getMovementState().isGoingDown();}

	//Setters for movement state
	public void setLeft(boolean left) {super.getMovementState().setLeft(left);}
	public void setRight(boolean right) {super.getMovementState().setRight(right);}
	public void setDown(boolean down) {super.getMovementState().setDown(down);}
	public void setUp(boolean up) {super.getMovementState().setUp(up);}
	public void setJumping(boolean jump) {super.getMovementState().setJump(jump);}
	public void setFalling(boolean fall) {super.getMovementState().setFalling(fall);}

	public Rectangle getPickUpRectangle() {
		return new Rectangle((super.getMap().getX() 
					+ (int)super.getPVector().getPositionX()) 
					- super.getCollisionBox().getCollisionWidth() / 2
					
        		,super.getMap().getY() + (int)super.getPVector().getPositionY() 
        			- super.getCollisionBox().getCollisionHeight() / 2
        			
        		,super.getCollisionBox().getCollisionWidth()
        		,super.getCollisionBox().getCollisionHeight());
	}
	
	public boolean canClimb(double destX, double y, TileMap map) {
		return super.getCollisionBox().isClimbable(destX, y, map);
	}


	public void shoot() {
		this.weapon.attack(super.getPVector().getPositionX(),super.getPVector().getPositionY());
	}
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
    private void initPhysics(){
        
        super.getObjectMovementAttr().setGravity(Constants.PLAYER_GRAVITY, Constants.PLAYER_TERMINAL_VELOCITY);
        super.getObjectMovementAttr().setJumpRate(Constants.PLAYER_JUMP, Constants.PLAYER_STOP_JUMP);
        super.getObjectMovementAttr().setMovementRate(Constants.PLAYER_ACCELERATION
        		, Constants.PLAYER_MAXIMUM_SPEED, Constants.PLAYER_DEACCELERATION);
    }
    
    private void getNextPosition(){
    	super.getPVector().getNewPosition(super.getMovementState()
    			, super.getObjectMovementAttr());
    }
    
	private void changePlayerAnimation() {
		if(super.getPVector().getDirectionY() > 0){
    		if(this.state != ObjectState.FALLING){
    			this.state = ObjectState.JUMPING;
    		}
    	}else if(this.getPVector().getDirectionY() < 0){
    		
    		if(this.state != ObjectState.JUMPING){
    			this.state = ObjectState.JUMPING;
    			super.getAnimation().setFrames(this.sprite.getFrameSet(0));
    			
    		}    		
    	}else if(super.getMovementState().isGoingLeft() || super.getMovementState().isGoingRight()){
    		if(this.state != ObjectState.WALKING){
    			this.state = ObjectState.WALKING;
    			super.getAnimation().setFrames(this.sprite.getFrameSet(ObjectState.WALKING.ordinal()));
    		}
    	}else{
    		if(this.state != ObjectState.IDLE){
    			this.state = ObjectState.IDLE;
    		}
    		super.getAnimation().setFrames(this.sprite.getFrameSet(ObjectState.IDLE.ordinal()));
    		
    	}
		super.getAnimation().setDelay(40);
	}
    
    private void setDirection(){
    	
    	if(super.getMovementState().isGoingLeft()){super.setFacingRight(false);}
    	if(super.getMovementState().isGoingRight()){super.setFacingRight(true);}
    	
    }
	
    private void init(){

        super.setFacingRight(true);
    
        state = ObjectState.IDLE;
        
        super.setCollisionBox(new CollisionBox(Constants.PLAYER_COLLISION_WIDTH,Constants.PLAYER_COLLISION_HEIGHT));
        super.setWidth(Constants.PLAYER_WIDTH);
        super.setHeight(Constants.PLAYER_HEIGHT);
        
    }
    
    private void initPosition(int x,int y){
        super.setPVector(new PVector());
        super.getPVector().setPositionX(x);
        super.getPVector().setPositionY(y);
        
    }
    

	private void drawPlayer(Graphics g) {
		if(super.isFacingRight()){
    		
    			g.drawImage(super.getAnimation().getImage()
        				, (int)(super.getPVector().getPositionX() + super.getMapX() - super.getWidth() /2)
        				, (int)(super.getPVector().getPositionY() + super.getMapY() - super.getHeight() / 2)
        				, null);
    		
    	}else {
    		g.drawImage(super.getAnimation().getImage()
    				, (int)(this.getPVector().getPositionX() + super.getMapX() - super.getWidth() / 2 + super.getWidth())
    				, (int)(this.getPVector().getPositionY() + super.getMapY() - super.getHeight() / 2)
    				, -super.getWidth()
    				, super.getHeight()
    				, null);
    	}
	}
    
	
}