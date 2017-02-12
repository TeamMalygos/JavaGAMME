package utils;

public class CollisionBox {

	private int colisionWidth;
	private int colisionHeight;
	
	private boolean collidesTopLeft;
	private boolean collidesTopRight;
	private boolean collidesBottomLeft;
	private boolean collidesBottomRight;
	
	private PVector currentPosition;
	
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
	
	/**
	 * 
	 * <p>setCollisionBoundaries sets the four angles of collision<p>
	 * @param topLeft
	 * @param topRight
	 * @param bottomRight
	 * @param bottomLeft
	 * 
	 */
	public void setCollisionBoundaries(boolean topLeft,boolean topRight
			,boolean bottomRight,boolean bottomLeft){
		
		this.collidesBottomLeft = bottomLeft;
		this.collidesBottomRight = bottomRight;
		this.collidesTopLeft = topLeft;
		this.collidesTopRight = topRight;
	}
	
	//Getters
	public int getCollisionWidth(){	return this.colisionWidth;}
	public int getCollisionHeight(){return this.colisionHeight;}
	public boolean isTopLeftBlocked(){ return this.collidesTopLeft;}
	public boolean isTopRightBlocked(){return this.collidesTopRight;}
	public boolean isBottomLeftBlocked(){return this.collidesBottomLeft;}
	public boolean isBottomRightBlocked(){return this.collidesBottomRight;}

	
}
