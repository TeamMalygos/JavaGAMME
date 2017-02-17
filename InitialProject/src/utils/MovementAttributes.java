package utils;


/**
 * 
 * <p><em>Movement Attributes</em> introduces simple physics to the game
 * by providing us with neat way of changing our speed and trajectory overtime.
 * This way our objects won't move or fall with constant speed</p>
 * 
 */
public class MovementAttributes {

	private double acceleration;
	private double maximumSpeed;
	//Optional (decrementing the speed)
	private double stopSpeed;
	
	//Falling speed
	private double gravity;
	//Maximum falling speed*
	private double terminalVelocity;
	
	
 	//The reason for the two attributes is:
 	//When you hold the up arrow for longer you will jump higher.
 	//Eventually reaching the highest possible point
 	private double jumpStart;
 	private double stopJump;
 	
 	/**
 	 * <p>Constructor for MovementAttributes initializes
 	 * the physics for the game as 0.00 double
 	 * values. Use set methods to set the actual
 	 * ones! </p>
 	 */
 	public MovementAttributes(){
 		this.acceleration = 0d;
 		this.maximumSpeed = 0d;
 		this.stopSpeed = 0d;
 		this.gravity = 0d;
 		this.terminalVelocity = 0d;
 		this.jumpStart = 0d;
 		this.stopJump = 0d;
 	}
 	
 	/**
 	 * 
 	 * <p>This method sets the movement rates of a
 	 * particular unit in the game. The values are
 	 * floating point type. </p>
 	 * 
 	 * @param Double acceleration
 	 * @param Double maximumSpeed
 	 * @param Double deacceleration
 	 */
 	public void setMovementRate(double acceleration,double maximumSpeed
 			,double deacceleration){
 		
 		this.acceleration = acceleration;
 		this.maximumSpeed = maximumSpeed;
 		this.stopSpeed = deacceleration;
 		
 	}
 	
 	/**
 	 * <p>This method sets the falling speed of
 	 * a map object and the maximum fall speed
 	 * refered to as "Terminal Velocity"</p>
 	 * 
 	 * @param Double gravity
 	 * @param Double terminalVelocity
 	 */
 	public void setGravity(double gravity, double terminalVelocity){
 		
 		this.gravity = gravity;
 		this.terminalVelocity = terminalVelocity;
 		
 	}
 	
 	/**
 	 * <p>This method sets the jumping rate of an unit
 	 * and the ability to stop it from going up
 	 * more than required using the stop jump attribute.</p>
 	 * 
 	 * @param Double jumpRate
 	 * @param Double stopJumpRate
 	 */
 	public void setJumpRate(double jumpRate,double stopJumpRate){
 		this.jumpStart = jumpRate;
 		this.stopJump = stopJumpRate;
 	}
 	
 	//Getters
 	public double getUnitMaximumSpeed(){return this.maximumSpeed;}	
 	public double getUnitAcceleration(){return this.acceleration;}
 	public double getUnitStopSpeed(){return this.stopSpeed;}
	public double getUnitGravity(){return this.gravity;}
	public double getUnitTerminalVelocity(){return this.terminalVelocity;}
	public double getUnitStartJump(){return this.jumpStart;}
	public double getUnitStopJump(){return this.stopJump;}
 	
}
