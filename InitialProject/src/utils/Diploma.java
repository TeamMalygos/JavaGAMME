package utils;

import java.awt.Graphics;
import java.awt.Rectangle;

import constants.Constants;
import game.entities.MapObject;
import game.entities.UnitDrawable;
import gfx.Assets;
import gfx.SpriteSheet;
import map.TileMap;
import states.GameState;
import states.MenuState;
import states.StateManager;

public class Diploma extends MapObject implements UnitDrawable{

	private SpriteSheet sheet;
	private final int[] framesCount = new int[]{
			8
	};
	
	public Diploma(double x, double y,TileMap map){	
		
		super(map);
		
		super.position = new PVector(x,y);
	
		this.sheet = new SpriteSheet(Assets.diploma);
		this.sheet.setFrameLayersCount(framesCount
				, Constants.DIPLOMA_WIDTH
				, Constants.DIPLOMA_HEIGHT);
		
		this.cBox = new CollisionBox(Constants.DIPLOMA_WIDTH,Constants.DIPLOMA_HEIGHT);
		
		this.animation = new Animation();
		this.animation.setFrames(this.sheet.getFrameSet(0));
		this.animation.setDelay(150);
		
	}
	
	@Override
	public void tick() {
		this.animation.update();
		if(GameState.getPlayer().canPickUp(super.getObjectRectangle())){
			GameState.setFinished(true);
			StateManager.setCurrentState(new MenuState());
		}
	}
	
	
	@Override
	public void render(Graphics g) {
		
		g.drawImage(this.animation.getImage()
				,super.tileMap.getX() + (int)this.position.getPositionX()
				,super.tileMap.getY() + (int)this.position.getPositionY(),null);
		
	}
	
	
}
