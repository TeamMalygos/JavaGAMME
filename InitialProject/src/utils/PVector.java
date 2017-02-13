package utils;

public class PVector {

	private double x;
	private double y;
	
	private double directionX;
	private double directionY;
	
	private double destX;
	private double destY;

	private double tempX;
	private double tempY;
	
	/**
	 * <p>Position vector constructor - initializes the position to 0/0</p> 
	 */
	public PVector(){
		x = 0;
		y = 0;
	}
	
	/**
	 * <p>Position vector constructor - initializes the position to the input</p>
	 * @param double posX
	 * @param double posY
	 *
	 */
	public PVector(double x,double y){
		this.x = x;
		this.y = y;
		this.destX = x;
		this.destY = y;
		this.directionX = 0;
		this.directionY = 0;
	}
	
	public void getNewPosition(MovementState movementState
			,MovementAttributes objectMovementAttr){
	double newDestX = 0d;
	if(movementState.isGoingLeft()){
		newDestX = this.destX - 
				objectMovementAttr.getUnitAcceleration();
		
		if(newDestX > -objectMovementAttr.getUnitMaximumSpeed()){
			newDestX = -objectMovementAttr.getUnitMaximumSpeed();
		}
		
		this.destX = newDestX;
		
	}else if(movementState.isGoingRight()){
		
		newDestX = this.destX 
				+ objectMovementAttr.getUnitAcceleration();
		
		if(newDestX > objectMovementAttr.getUnitMaximumSpeed()){
			newDestX = objectMovementAttr.getUnitMaximumSpeed();
		}
		this.destX = newDestX;
		//Else if we are not moving left or right
	}else {
		if(this.destX > 0){
		
			newDestX = this.destX
					- objectMovementAttr.getUnitAcceleration();
			if(newDestX < 0){
				newDestX = 0;
			}
			
		}else if(this.destX < 0){
			
			newDestX = this.destX 
					+ objectMovementAttr.getUnitAcceleration();
			if(newDestX > 0){
				newDestX = 0;
			}
			
		}
		this.destX = newDestX;
	}
	
	//TODO: Make it so that player can't attack while moving
}
	
	//Setters
	public void setPositionX(double x){this.x = x;}
	public void setPositionY(double y){this.y = y;}
	public void setTemporaryX(double x) {this.tempX = x;}
	public void setTemporaryY(double y) {this.tempY = y;}
	public void setDirectionX(double x) {this.directionX = x;}
	public void setDirectionY(double y) { this.directionY = y;}
	public void setDestinationX(double x){this.destX = x;}
	public void setDestinationY(double y){this.destY = y;}
	public void setDestinationXY(double x,double y){
		this.destX = x;
		this.destY = y;
	}
	
	public double getPositionX(){return x;}
	public double getPositionY(){return y;}
	public double getDestinationX() { return this.destX;}
	public double getDestinationY(){ return this.destY;}
	public double getTemporaryX() { return this.tempX;}
	public double getTemporaryY() { return this.tempY;}
	public double getDirectionX(){ return this.directionX;}
	public double getDirectionY(){ return this.directionY;}
	
}
