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
	protected abstract void render(Graphics g);
	@Override
	protected abstract void tick();
	
	protected void setToPlayer(Player p){
		this.player = p;
	}
	
	protected void setSize(int w,int h){
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
