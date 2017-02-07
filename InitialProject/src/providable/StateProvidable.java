package providable;

public interface StateProvidable {

	public boolean isMovingLeft();
	public boolean isMovingRight();
	public boolean isMovingUp();
	public boolean isMovingFalling();
	public boolean isJumping();
	public boolean isGoingDown();
	
	public void setLeft(boolean left);
	public void setRight(boolean right);
	public void setDown(boolean down);
	public void setUp(boolean up);
	public void setJumping(boolean jump);
	public void setFalling(boolean fall);
	
}
