package utils;

import enums.TileType;
import map.TileMap;

public class CollisionBox {

	private int colisionWidth;
	private int colisionHeight;
	
	private boolean collidesTopLeft;
	private boolean collidesTopRight;
	private boolean collidesBottomLeft;
	private boolean collidesBottomRight;
	
	/**
	 * 
	 * <p>CollisionBox constructor
	 * 
	 * @param colisionWidth
	 * @param colisionHeight
	 * 
	 */
	public CollisionBox(int colWidth,int colHeight){
	
		colisionWidth = colWidth;
		colisionHeight = colHeight;
		
	}
	
	public void checkCollisionCorners(double x,double y,TileMap tileMap){
		
		int tileSize = tileMap.getTileWidth();
		
		int leftCorner = (int)(x - this.getCollisionWidth() / 2) / tileSize;
		int rightCorner = (int)(x + this.getCollisionWidth() / 2 - 1) / tileSize;
		int topCorner = (int)(y - this.getCollisionHeight() / 2) / tileSize;
		int bottomCorner = (int)(y + this.getCollisionHeight() / 2 - 1) / tileSize;
		
		TileType topLeft = tileMap.getType(topCorner, leftCorner);
		TileType topRight = tileMap.getType(topCorner, rightCorner);
		TileType bottomLeft = tileMap.getType(bottomCorner, leftCorner);
		TileType bottomRight = tileMap.getType(bottomCorner, rightCorner);
		
		this.setCollisionBoundaries(
				topLeft,
				topRight,
				bottomLeft,
				bottomRight);
		
	}
	
	//Getters
	public int getCollisionWidth(){	return this.colisionWidth;}
	public int getCollisionHeight(){return this.colisionHeight;}
	public boolean isTopLeftBlocked(){ return this.collidesTopLeft;}
	public boolean isTopRightBlocked(){return this.collidesTopRight;}
	public boolean isBottomLeftBlocked(){return this.collidesBottomLeft;}
	public boolean isBottomRightBlocked(){return this.collidesBottomRight;}

	
	public boolean isClimbable(double x,double y,TileMap tileMap){
		
		int tileSize = tileMap.getTileWidth();
		
		int leftCorner = (int)(x - this.getCollisionWidth() / 2) / tileSize;
		int rightCorner = (int)(x + this.getCollisionWidth() / 2 - 1) / tileSize;
		int topCorner = (int)(y - this.getCollisionHeight() / 2) / tileSize;
		int bottomCorner = (int)(y + this.getCollisionHeight() / 2 - 1) / tileSize;
		
		TileType topLeft = tileMap.getType(topCorner, leftCorner);
		TileType topRight = tileMap.getType(topCorner, rightCorner);
		TileType bottomLeft = tileMap.getType(bottomCorner, leftCorner);
		TileType bottomRight = tileMap.getType(bottomCorner, rightCorner);
		
		return (topLeft == TileType.Climbable || bottomLeft == TileType.Climbable) 
				|| (topRight == TileType.Climbable || bottomRight == TileType.Climbable);
		
	}
	
	public boolean isOfTypeDeath(double x,double y,TileMap tileMap){
		
		int tileSize = tileMap.getTileWidth();
		
		int leftCorner = (int)(x - this.getCollisionWidth() / 2) / tileSize;
		int rightCorner = (int)(x + this.getCollisionWidth() / 2 - 1) / tileSize;
		//int topCorner = (int)(y - this.getCollisionHeight() / 2) / tileSize;
		int bottomCorner = (int)(y + this.getCollisionHeight() / 2 - 1) / tileSize;
		
		//TileType topLeft = tileMap.getType(topCorner, leftCorner);
		//TileType topRight = tileMap.getType(topCorner, rightCorner);
		TileType bottomLeft = tileMap.getType(bottomCorner, leftCorner);
		TileType bottomRight = tileMap.getType(bottomCorner, rightCorner);
		//System.out.println(bottomRight.name() + " " + bottomLeft.name());
		
		return bottomLeft == TileType.Death || bottomRight == TileType.Death;
		
	}
	
	/**
	 * 
	 * <p>setCollisionBoundaries sets the four angles of collision<p>
	 * @param topLeft
	 * @param topRight
	 * @param bottomLeft
	 * @param bottomRight
	 * 
	 */
	private void setCollisionBoundaries(TileType topLeft,TileType topRight
			,TileType bottomLeft,TileType bottomRight){
		
		this.collidesBottomLeft = (bottomLeft != TileType.Background);
		this.collidesBottomRight = (bottomRight != TileType.Background);
		this.collidesTopLeft = (topLeft != TileType.Background);
		this.collidesTopRight = (topRight != TileType.Background);
	}
	
}
