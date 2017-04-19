package game.entities.enemies;

import java.awt.Graphics;

import constants.Constants;
import gfx.Assets;
import map.TileMap;
import map.parser.ObjectsLayerObject;
import utils.CollisionBox;
import utils.PVector;

public class Bug extends Enemy{

	private ObjectsLayerObject objectData;
	
	public Bug(TileMap map,ObjectsLayerObject o) {
		super(map,Assets.enemyBug,Constants.BUG_ENEMY_FRAMES_COUNT
				,Constants.BUG_ENEMY_HEIGHT,
				Constants.BUG_ENEMY_WIDTH);
		this.objectData = o;
		this.init();
		this.initPhysics();
	}

	@Override
	public void tick() {
		super.getAnimation().update();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(super.getAnimation().getImage(),(int)super.getPVector().getPositionX()
				,(int)super.getPVector().getPositionY()
				,null);
	}


	private void init() {
		super.setCollisionBox(new CollisionBox(Constants.BUG_ENEMY_WIDTH,Constants.BUG_ENEMY_HEIGHT));
        super.setWidth(Constants.BUG_ENEMY_WIDTH);
        super.setHeight(Constants.BUG_ENEMY_HEIGHT);
        
        super.setPVector(new PVector());
        super.getPVector().setPositionX(this.objectData.getX());
        super.getPVector().setPositionY(this.objectData.getY());
         
	}
	
	 private void initPhysics(){
	    super.getObjectMovementAttr().setMovementRate(Constants.BUG_ACCELERATION
	        		, Constants.BUG_MAXIMUM_SPEED, Constants.BUG_DEACCELERATION);
	 }
	
}
