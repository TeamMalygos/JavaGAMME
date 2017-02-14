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
		this.t = new AffineTransform();
		
	}
	
	public void setPosition(double x,double y){
		this.position = new PVector(x,y);
		
		boundingBox = new Rectangle((int)this.position.getPositionX(),(int)this.position
				.getPositionY(),this.width,this.height);
		
	}

	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public void isCollected(Player player){
		if (this.boundingBox.contains(player.getX(), player.getY()) ||
            this.boundingBox.contains(player.getBoundingBox().getMaxX(), player.getBoundingBox().getY()) ||
            this.boundingBox.contains(player.getBoundingBox().getX(), player.getBoundingBox().getMaxY()) ||
            this.boundingBox.contains(player.getBoundingBox().getMaxX(), player.getBoundingBox().getMaxY())) {
			this.collected = true;
    	}

    	this.collected = false;
	}
	
	public boolean checkCollected(){
		return this.collected;
	}
	
	
	
}
