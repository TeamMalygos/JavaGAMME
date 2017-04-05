package utils;

import java.awt.Graphics;
import java.awt.Rectangle;

import constants.Constants;
import game.entities.MapObject;
import game.entities.Drawable;
import gfx.Assets;
import gfx.SpriteSheet;
import map.TileMap;
import states.GameState;

public class Diploma extends MapObject implements Drawable{

	private SpriteSheet sheet;
	private Rectangle area;
	
	private final int[] framesCount = new int[]{
			8
	};
	
	public Diploma(double x, double y,TileMap map){	
		
		super(map);
		
		super.setPVector(new PVector(x,y - Constants.DIPLOMA_HEIGHT));
	
		this.sheet = new SpriteSheet(Assets.diploma);
		this.sheet.setFrameLayersCount(framesCount
				, Constants.DIPLOMA_WIDTH
				, Constants.DIPLOMA_HEIGHT);
		
		this.setCollisionBox(new CollisionBox(Constants.DIPLOMA_WIDTH,Constants.DIPLOMA_HEIGHT));
		
		this.getAnimation().setFrames(this.sheet.getFrameSet(0));
		this.getAnimation().setDelay(150);

		
	}
	
	@Override
	public void tick() {
		this.getAnimation().update();
		
		this.area = new Rectangle(super.getMap().getX() + (int)super.getPVector().getPositionX()
				, super.getMap().getY() + (int)super.getPVector().getPositionY()
				, super.getCollisionBox().getCollisionWidth(), super.getCollisionBox().getCollisionHeight());
			
		if(GameState.getPlayer().canPickUp(this.area)){
			GameState.setFinished(true);
		}
	}
	
	
	@Override
	public void render(Graphics g) {
		
		g.drawImage(this.getAnimation().getImage()
				,super.getMap().getX() + (int)super.getPVector().getPositionX()
				,super.getMap().getY() + (int)super.getPVector().getPositionY(),null);
	}

	
}
