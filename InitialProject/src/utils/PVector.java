package utils;

public class PVector {

	private double x;
	private double y;
	
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
	}
	
	public void setPositionX(double x){
		this.x = x;
	}
	
	public void setPositionY(double y){
		this.y = y;
	}
	
	public double getPositionX(){
		return x;
	}
	
	public double getPositionY(){
		return y;
	}
	
}
