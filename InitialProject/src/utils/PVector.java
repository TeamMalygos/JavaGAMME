package utils;

import states.GameState;

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
		this.x = 0;
		this.y = 0;
		this.tempX = 0;
		this.tempY = 0;
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
		this.destX = 0;
		this.destY = 0;
		this.directionX = 0;
		this.directionY = 0;
	}
	/*
	public void determineDirection(MovementState s){
		
		if(s.isJumping()){this.directionY = -1;}
		else if(s.isFalling()){this.directionY = 1;}
		else if(s.isGoingLeft()){this.directionX = -1;}
		else if(s.isGoingRight()){this.directionX = 1;}else {
			this.directionX = 0;
			this.directionY = 0;
		}
		
	}*/
	
	public void getNewPosition(MovementState movementState
			,MovementAttributes objectMovementAttr){

	double newDestX = 0d;
	desideMovement(movementState, objectMovementAttr, newDestX);
	
	//TODO: Make it so that player can't attack while moving
}

	private void desideMovement(MovementState movementState
			, MovementAttributes objectMovementAttr, double newDestX) {	
		if(movementState.isGoingLeft()){
			newDestX = this.directionX - 
					objectMovementAttr.getUnitAcceleration();
			if(newDestX < -objectMovementAttr.getUnitMaximumSpeed()){
				newDestX = -objectMovementAttr.getUnitMaximumSpeed();
			}
			
			this.directionX = newDestX;
			
		}else if(movementState.isGoingRight()){

			newDestX = this.directionX 
					+ objectMovementAttr.getUnitAcceleration();
			
			if(newDestX > objectMovementAttr.getUnitMaximumSpeed()){
				newDestX = objectMovementAttr.getUnitMaximumSpeed();
			}
			this.directionX = newDestX;
		}else {
			directionUpOrDown(objectMovementAttr, newDestX);
		}
		jumpingOrFalling(movementState, objectMovementAttr);
		checkIfClimbing(movementState, objectMovementAttr);
	}

	private void directionUpOrDown(MovementAttributes objectMovementAttr, double newDestX) {
		if(this.directionX > 0){
		
			newDestX = this.directionX
					- objectMovementAttr.getUnitStopSpeed();
			if(newDestX < 0){
				newDestX = 0;
			}
			
		}else if(this.directionX < 0){
			
			newDestX = this.directionX 
					+ objectMovementAttr.getUnitStopSpeed();
			System.out.println("DirectionX: " + this.directionX);
			if(newDestX > 0){

				newDestX = 0;
			}
			
		}
		this.directionX = newDestX;
	}

	private void jumpingOrFalling(MovementState movementState, MovementAttributes objectMovementAttr) {
		if(movementState.isJumping() && !movementState.isFalling()){
			this.directionY += objectMovementAttr.getUnitStartJump();
			movementState.setFalling(true);
		}
		if(movementState.isFalling()){	
			this.directionY += objectMovementAttr.getUnitStopJump();
			
			if(this.directionY > 0){
				movementState.setJump(false);
			}
			
			if(this.directionY > 0 && !movementState.isJumping()){
				this.directionY += objectMovementAttr.getUnitStopJump();
			}
			if(this.directionY > objectMovementAttr.getUnitTerminalVelocity()){
				this.directionY = objectMovementAttr.getUnitTerminalVelocity();
			}	
		}
	}

	private void checkIfClimbing(MovementState movementState, MovementAttributes objectMovementAttr) {
		if(movementState.isGoingUp() && (movementState.isGoingRight() || movementState.isGoingLeft())){		
			boolean isTileClimbable = GameState.getPlayer().canClimb(this.destX, this.y, GameState.getMap());
			
			if(isTileClimbable){
			this.directionY -= objectMovementAttr.getUnitAcceleration();
				
				if(this.directionY > 0){
					movementState.setJump(false);
				}

				if(!movementState.isGoingUp()){
					movementState.setFalling(true);
				}
				if(this.directionY > objectMovementAttr.getUnitMaximumSpeed()){
					this.directionY = objectMovementAttr.getUnitMaximumSpeed();
				}	
			}		
		}
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
