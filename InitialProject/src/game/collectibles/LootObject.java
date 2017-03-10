package game.collectibles;

import java.awt.Graphics;
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
	
	public LootObject(int id,int w,int h){
		
		this.id = id;
		this.width = w;
		this.height = h;
		
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
		
		if(this.boundingBox.intersects(playerBounds)){
			this.collected = true;
			return;
		}
		
    	this.collected = false;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public PVector getPosition() {
		return position;
	}

	public void setPosition(PVector position) {
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}

	public String getName() {
		return name;
	}

	public boolean checkCollected(){
		return this.collected;
	}
	
	
	
}
