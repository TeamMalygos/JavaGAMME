package game.collectibles;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import game.entities.Player;
import utils.Animation;
import utils.PVector;

public abstract class LootObject implements Collectible {

	protected PVector position;
	
	protected String name;
	
	protected int id;
	protected int width;
	protected int height;
	
	protected boolean collected;
	
	protected Rectangle boundingBox;
	protected Animation animation;
	
	protected Graphics2D g2d;
	
	protected AffineTransform t;
	
	public LootObject(int id,int width,int height){
		
		this.id = id;
		this.width = width;
		this.height = height;
		
		this.animation = new Animation();
		
		this.collected = false;
		
	}
	
	public void setPosition(double x,double y){
		this.position = new PVector(x,y);
		this.t = AffineTransform.getTranslateInstance(x, y);
		
		boundingBox = new Rectangle((int)this.position.getPositionX(),(int)this.position
				.getPositionY(),this.width,this.height);
		
	}

	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public void isCollected(Rectangle playerBounds){
		playerBounds = new Rectangle((int)playerBounds.getX(),(int)playerBounds.getY(),0,(int)playerBounds.getHeight());
		if (this.boundingBox.contains(playerBounds.getX(), playerBounds.getY()) ||
            this.boundingBox.contains(playerBounds.getMaxX(), playerBounds.getY()) ||
            this.boundingBox.contains(playerBounds.getX(), playerBounds.getMaxY()) ||
            this.boundingBox.contains(playerBounds.getMaxX(), playerBounds.getMaxY())) {
			this.collected = true;
			return;
    	}
		
    	this.collected = false;
	}
	
	public boolean checkCollected(){
		return this.collected;
	}
	
	
	
}
