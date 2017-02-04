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
	
	
}
