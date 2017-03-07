package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.entities.Player;
import gfx.SpriteSheet;

public abstract class HUDComponent extends MenuComponent {

	protected Player player;
	protected BufferedImage[] frames;
	private SpriteSheet sheet;
	
	protected HUDComponent(int x,int y,String name){
		super(x,y,name);
	}

	@Override
	public abstract void render(Graphics g);
	@Override
	public abstract void tick();
	
	public void linkToPlayer(Player p){
		this.player = p;
	}
	
	public void setSize(int w,int h){
		super.setSize(w, h);
	}
	
	protected void loadSprite(BufferedImage sprite,int[] frameCount){
		this.sheet = new SpriteSheet(sprite);
		this.sheet.setFrameLayersCount(frameCount
				, super.width
				, super.height);
		
		this.frames = this.sheet.getFrameSet(0);
		
	}
	
	
}
