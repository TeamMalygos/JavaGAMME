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
	
	/**
	 * <p>This method sets the basic four states for the
	 * object that is using it. The four basic states
	 * are represented by a <em>boolean</em>1 variable</p>
	 * 
	 * @param boolean up
	 * @param boolean down
	 * @param boolean left
	 * @param boolean right
	 */
	
	public void setLeft(boolean left){this.left = left;}
	public void setRight(boolean right){this.right = right;}
	public void setUp(boolean up){this.up = up;}
	public void setDown(boolean down){this.down = down;}
	public void setJump(boolean jump){this.isJumping = jump;}
	public void setFalling(boolean falling){this.isFalling = falling;}
	
	public boolean isGoingUp(){return this.up;}
	public boolean isGoingLeft(){return this.left;}
	public boolean isGoingRight(){return this.right;}
	public boolean isGoingDown(){return this.down;}
	public boolean isJumping(){return this.isJumping;}
	public boolean isFalling(){return this.isFalling;}
	
}
