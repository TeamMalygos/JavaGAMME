package map;

import java.awt.Graphics;
import java.util.ArrayList;

import game.collectibles.Collectible;
import game.entities.UnitDrawable;

public class ObjectLayer implements UnitDrawable {

	private LootMap lootMap;
	private UnitDrawable[][] entities;
	
	public ObjectLayer(){
		
		init();
		
	}

	public void init(){
		
		
		
	}
	
	@Override
	public void tick(){
		
		lootMap.tick();
		tickEntities();
		
	}
	
	public void tickEntities(){
		
	}
	
	@Override
	public void render(Graphics g){
		
		lootMap.render(g);
		
		
	}
	
	
}
