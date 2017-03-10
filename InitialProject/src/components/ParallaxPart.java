package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.entities.UnitDrawable;
import utils.PVector;

public class ParallaxPart implements UnitDrawable {

	private BufferedImage part;
	private int offset;
	
	private PVector position;
	
	public ParallaxPart(int x,int y,int offset,BufferedImage part){
		
		this.setPart(part);
		this.setOffset(offset);
		this.position = new PVector(x,y);
		
	}
	
	public void setPart(BufferedImage part){
		this.part = part;
	}
	public BufferedImage getImage(){
		return this.part;
	}
	
	public PVector getPosition(){
		return this.position;
	}
	
	public void setOffset(int offset){
		this.offset = offset;
	}

	public int getOffset(){
		return this.offset;
	}
	@Override
	public void tick() {
		
		this.position.setPositionX(this.position.getPositionX() - this.offset);
		
		if(this.position.getPositionX() <= -1 * this.part.getWidth()){
			this.position.setPositionX(this.position.getPositionX() + this.part.getWidth() * 2);
		}
		
	}

	@Override
	public void render(Graphics g) {
	
		g.drawImage(this.part
				, (int)this.position.getPositionX()
				, (int)this.position.getPositionY() 
				,null);
		
		this.tick();
		
	}
	
	
}
