package utils;

import java.awt.image.BufferedImage;

/**
 * 
 * @author G_ANGELOV
 * <p><em>Animation</em> is the base class for every animation
 * our map objects contains and their variations
 * </p>
 *
 */
public class Animation {

	private int currentAction;
	//Should the sprite be facing left or right  *In case you wondering what is this for :D
	private boolean facingRight;
	
	private BufferedImage[] frames;
	private int currentFrame;
	
	private long startTime;
	private long delay;
	
	private boolean playedOnce;
	
	public Animation(){
		playedOnce = false;
	}
	
	public void setFrames(BufferedImage[] frames){
		this.frames = frames;
		this.startTime = System.nanoTime();
		currentFrame = 0;
	}
	
	public void setDelay(long d){
		this.delay = d;
	}
	
	public void setFrame(int f){
		this.currentFrame = f;
	}
	
	public void update(){
		
		if(delay == -1){
			return;
		}
		
		long timeElapsed = (System.nanoTime() - startTime)/1000000;
		
		if(timeElapsed > delay){
			currentFrame++;
			startTime = System.nanoTime();
		}
		
		if(this.currentFrame == frames.length){
			currentFrame = 0;
			playedOnce = true;
		}
		
	}
	
	public int getFrame(){return this.currentFrame;}
	public BufferedImage getImage(){
		return frames[currentFrame];
	}
	public boolean hasPlayedOnce(){
		return playedOnce;
	}
	
}
