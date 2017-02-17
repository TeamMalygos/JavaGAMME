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
import map.parser.TileLayer;
import states.GameState;
import states.StateManager;

public class ObjectLayer implements UnitDrawable {

	private ObjectsLayer layer; 
	private TileLayer lootLayer;
	private List<ObjectsLayerObject> objects;
	private List<Diamond> diamonds;
	private LootMap lootMap;
	
	private int deltaOffsetX;
	private int deltaOffseY;
	
	private boolean isInitialized;
	
	public ObjectLayer(ObjectsLayer layer){
		this.layer = layer;
		this.isInitialized = false;
		
	}

	public void setSecondaryTileLayer(TileLayer t){
		this.lootLayer = t;
		init();
	}
	
	public void setOffset(int x,int y){
		
		this.deltaOffsetX = Math.abs(this.layer.getOffsetX() - x);
		this.deltaOffseY = Math.abs(this.layer.getOffsetY() - y);
		
	}
	
	private void init(){
		diamonds = new ArrayList<Diamond>();
		
		try{
		objects = layer.getObjects();
		}catch(NullPointerException ex){
			ex.printStackTrace();
			return;
		}
		
		try{
			this.lootMap = new LootMap(GameState.getMap());
			this.lootMap.setData(this.lootLayer.getData(), this.lootLayer.getWidth(), this.lootLayer.getHeight());
		}catch(NullPointerException ex){
			ex.printStackTrace();
			return;
		}
		
		isInitialized = true;
		initDiamonds();
		
		
	}
	
	private void initDiamonds(){
		for(ObjectsLayerObject obj : objects){
			if(obj.getName().equals("diamond")){
				
				Diamond diamond = new Diamond(obj.getGid()
						,obj.getX()
						,obj.getY());
				
				diamonds.add(diamond);
				
			}
		}
	}
	
	public void tickCollectibles(){
		Player p = GameState.getPlayer();
		
		diamonds.forEach(d -> {d.tick(); d.isCollected(p.getBoundingBox());});
		diamonds = diamonds.stream().filter(x -> !x.checkCollected()).collect(Collectors.toList());

	}
	
	@Override
	public void tick(){
		if(this.isInitialized){
			tickCollectibles();
		}
		
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
