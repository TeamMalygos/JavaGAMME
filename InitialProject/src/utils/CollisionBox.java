package utils;

public class CollisionBox {

	private int colisionWidth;
	private int colisionHeight;
	
	private double destX;
	private double destY;
	
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
	 * <p>This method will prepare the class for collision detection</p>
	 * @param PVector currentObjectPositionOnTheMap
	 * @param double increaseInXAxis
	 * @param double increaseInYAxis 
	 * 
	 */
	public void detectColision(PVector currentPosition
			, double destinationX, double destinationY){
		
		destX = destinationX;
		destY = destinationY;
		this.currentPosition = currentPosition;
		
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
	
	
}
