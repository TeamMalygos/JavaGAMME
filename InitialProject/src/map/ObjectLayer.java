package map;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import game.collectibles.Collectible;
import game.collectibles.Diamond;
import game.entities.Player;
import game.entities.UnitDrawable;
import map.parser.ObjectsLayer;
import map.parser.ObjectsLayerObject;
import states.GameState;
import states.StateManager;

public class ObjectLayer implements UnitDrawable {

	private ObjectsLayer layer; 
	private List<ObjectsLayerObject> objects;
	private List<Diamond> diamonds;
	
	public ObjectLayer(ObjectsLayer layer){
		this.layer = layer;
		init();
		
	}

	private void init(){
		
		objects = layer.getObjects();
		diamonds = new ArrayList<Diamond>();
		initDiamonds();
		
		
	}
	
	private void initDiamonds(){
		for(ObjectsLayerObject obj : objects){
			if(obj.getName().equals("diamond")){
				
				Diamond diamond = new Diamond(obj.getGid(),obj.getX(),obj.getY());
				diamonds.add(diamond);
				
			}
		}
	}
	
	public void tickCollectibles(){
		
		GameState state = (GameState)StateManager.getCurrentState();
		Player p = state.getPlayer();
		
		diamonds.forEach(d -> {d.tick(); d.isCollected(p.getBoundingBox());});
		diamonds = diamonds.stream().filter(x -> !x.checkCollected()).collect(Collectors.toList());

	}
	
	@Override
	public void tick(){
		
		tickCollectibles();
		
	}
	
	@Override
	public void render(Graphics g){
		
		//Collectibles
		for(Diamond d : diamonds){
			d.render(g);
		}
		
		
		//Entities
		
	}
	
	
}
