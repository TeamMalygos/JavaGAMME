package game.entities;

import java.awt.Rectangle;

import map.TileMap;
import utils.CollisionBox;
import utils.MovementAttributes;
import utils.MovementState;
import utils.PVector;
import utils.Animation;
import constants.Constants;
import enums.TileType;

/**
 * 
 * @author G_ANGELOV
 * </p><em>MapObject<em> is the base class for every object in the game
 * excluding the Tile Map and the other prerequisites and resources
 * we have. It is the link between the entities and their movements
 * and actions</p>
 *
 */
public abstract class MapObject {

	private TileMap tileMap;
	private int tileSize;
	private int mapX;
	private int mapY;
	
	private int width;
	private int height;
	private boolean facingRight;
	
	private PVector position;
	private int currentRow;
	private int currentColumn;
	
	private CollisionBox cBox;
	private Animation animation;
	
	private MovementState movementState;
	private MovementAttributes objectMovementAttr;
	
	protected MapObject(TileMap map){
		tileMap = map;
		tileSize = map.getTileHeight();
		
		this.movementState = new MovementState();
		this.objectMovementAttr = new MovementAttributes();
		this.animation = new Animation();
	}
	
	protected boolean intersectsWith(MapObject o){
		return this.getObjectRectangle().intersects(o.getObjectRectangle());
	}

	public void checkCollisionCorners(double x,double y){
		this.cBox.checkCollisionCorners(x,y, this.tileMap);
	}
	
	public TileMap getMap(){
		return this.tileMap;
	}
	
	protected PVector getPVector(){
		return this.position;
	}
	
	protected void setPVector(PVector position){
		this.position = position;
	}
	
	protected void setCollisionBox(CollisionBox box){
		this.cBox = box;
	}

	/**
	 * <p> This method will check weather the object that is using
	 * it is colliding with a blocked tile or not. </p>
	 */
	protected void checkTileMapCollision(){
		
		this.currentRow = (int)this.position.getPositionY() / tileMap.getTileWidth();
		this.currentColumn = (int)this.position.getPositionX() / tileMap.getTileHeight();
		
		//Changing the destination X and Y with -1/1(left/right) or 0(no change)
		this.position.setDestinationX(this.position.getPositionX() 
				+ this.position.getDirectionX());
		this.position.setDestinationY(this.position.getPositionY() 
				+ this.position.getDirectionY());
		
		//Setting temporary position to x and y
		this.position.setTemporaryX(this.position.getPositionX());
		this.position.setTemporaryY(this.position.getPositionY());
		
		
		//Basically we check for blocked corners from the position where you at
		//and the position you are going to
		//First y direction
		checkCollisionCorners(this.position.getPositionX()
				, this.position.getDestinationY());
		calculateYMovement();

		//Secondary is x direction
		checkCollisionCorners(this.position.getDestinationX(),this.position.getPositionY());
		calculateXMovement();
		
		//Check if the blocks below the object are solid and if he should fall
		if(!this.movementState.isFalling()){
			checkCollisionCorners(this.position.getPositionX()
					,this.position.getDestinationY()+1);
			
			if(!this.cBox.isBottomLeftBlocked() && !this.cBox.isBottomRightBlocked()){
				this.movementState.setFalling(true);
			}
			
		}
		
		
	}
	
	public Rectangle getObjectRectangle(){
		return new Rectangle(
				(int)position.getPositionX() - cBox.getCollisionWidth(),
				(int)position.getPositionY() - cBox.getCollisionHeight(),
				cBox.getCollisionWidth(),
				cBox.getCollisionHeight());
		
	}
	
	protected Animation getAnimation(){
		return this.animation;
	}
	

	protected CollisionBox getCollisionBox() {
		return this.cBox;
	}

	
	//Map position
	public void setMapPosition(){
		this.mapX = this.tileMap.getX();
		this.mapY = this.tileMap.getY();
	}

    protected MovementState getMovementState() {
		return this.movementState;
    }	
	
	public boolean isFacingRight() {
		return this.facingRight;
	}
	
	//Check if the object is on screen
	public boolean notOnScreen(){
		
		return this.position.getPositionX() + this.mapX + this.width < 0 ||
				this.position.getPositionX() + this.mapX - this.width > Constants.WIDTH ||
				this.position.getPositionY() + this.mapY + this.height < 0 ||
				this.position.getPositionY() + this.mapY - this.height > Constants.HEIGHT;	
		
	}

	//Analogy with calculateYMovement
		private void calculateXMovement(){
			
			if(this.position.getDirectionX() < 0){
				
				if(this.cBox.isTopLeftBlocked() || this.cBox.isBottomLeftBlocked()){
					
					this.position.setDirectionX(0);
					this.position.setTemporaryX(this.currentColumn * this.tileSize 
							+ this.cBox.getCollisionWidth() / 2);
					
				}else {
					this.position.setTemporaryX(this.position.getTemporaryX() 
							+ this.position.getDirectionX());
				}
				
			}
			if(this.position.getDirectionX() > 0){
				
				if(this.cBox.isTopRightBlocked() || this.cBox.isBottomRightBlocked()){
				
					this.position.setDirectionX(0);
					this.position.setTemporaryX((this.currentColumn+1) * this.tileSize 
							- this.cBox.getCollisionWidth() / 2);
					
				}else {
					this.position.setTemporaryX(this.position.getTemporaryX() 
							+ this.position.getDirectionX());
				}
				
			}
			
		}
	
		private void calculateYMovement(){
			//If you jump (-1 direction y) and topleft and topright corners are blocked you bump
			if(this.position.getDirectionY() < 0){
				
				if(this.cBox.isTopLeftBlocked() || this.cBox.isTopRightBlocked()){
					//Direction y is set to 0
					this.position.setDirectionY(0);
					//Position is set to x/y below the tile you bumped into
					this.position.setTemporaryY(this.currentRow * this.tileSize 
							+ this.cBox.getCollisionHeight()/2);
				}else {
					this.position.setTemporaryY(this.position.getTemporaryY() + this.position.getDirectionY());
				}
			}
			
			//If direction y is 1 - bottom / falling
			if(this.position.getDirectionY() > 0){
				
				if(this.cBox.isBottomLeftBlocked() || this.cBox.isBottomRightBlocked()){
					
					//Setting y direction to 0 and position to (right above the blocked tile)
					this.position.setDirectionY(0);
					this.movementState.setFalling(false);
					this.position.setTemporaryY((this.currentRow + 1) * this.tileSize 
							- this.cBox.getCollisionHeight() /2 );
				}else {
					this.position.setTemporaryY(this.position.getTemporaryY() 
							+ this.position.getDirectionY());
				}
				
			}
		}

		protected void getNewPosition() {
			this.position.getNewPosition(this.movementState, this.objectMovementAttr);
		}

		protected void setFacingRight(boolean facingRight) {
			this.facingRight = facingRight;
			
		}

		protected int getWidth() {
			return this.width;
		}

		protected int getHeight() {
			return this.height;
		}

		public double getMapX() {
			return this.mapX;
		}

		public double getMapY() {
			return this.mapY;
		}

		protected void setHeight(int playerHeight) {
			this.height = playerHeight;
			
		}

		protected void setWidth(int playerWidth) {
			this.width = playerWidth;
		}

		protected MovementAttributes getObjectMovementAttr() {
			return this.objectMovementAttr;
		}
		
}
