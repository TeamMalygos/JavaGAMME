package utils;

/**
 * 
 * <p><em>MovementState<em> contains the current state of a particular map
 * object and allows change.</p>
 * 
 */
public class MovementState {

	private boolean up;
	private boolean left;
	private boolean right;
	private boolean down;
	private boolean isJumping;
	private boolean isFalling;
	
	public MovementState(){
		up = false;
		left = false;
		right = false;
		down = false;
		isJumping =  false;
		isFalling = false;
	}
	
	
	
}
