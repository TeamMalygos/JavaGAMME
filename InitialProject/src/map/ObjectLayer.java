package map;

import java.awt.Graphics;
import java.util.List;

import constants.Constants;
import game.entities.Drawable;
import map.parser.ObjectsLayer;
import map.parser.ObjectsLayerObject;
import map.parser.TileLayer;
import states.GameState;
import utils.Diploma;

public class ObjectLayer implements Drawable {

	private ObjectsLayer layer; 
	private TileLayer lootLayer;
	private List<ObjectsLayerObject> objects;
	private LootMap lootMap;
	
	private Diploma diploma;
	
	private boolean isInitialized;
	
	public ObjectLayer(ObjectsLayer layer){
		this.layer = layer;
		this.isInitialized = false;
		init();
	}

	public void setSecondaryTileLayer(TileLayer t){
    	this.lootLayer = t;
    	
    	try{
			this.lootMap = new LootMap(GameState.getMap());
			this.lootMap.setData(this.lootLayer.getData(), this.lootLayer.getWidth(), this.lootLayer.getHeight());
		}catch(NullPointerException ex){
			return;
		}
		
    	
	}
	
	public void setOffset(int x,int y){
		if(!this.isInitialized){
			return;
		}
		
		this.lootMap.setOffset(x, y);
		
	}
	
	@Override
	public void tick(){
		if(this.diploma != null){
			this.diploma.tick();
		}
		
		if(!this.isInitialized){
			return;
		}
		
    	this.lootMap
    	.setPosition(Constants.WIDTH/2 - GameState.getPlayer().getX()
    			,Constants.HEIGHT /2 - GameState.getPlayer().getY());
		this.lootMap.tick();
		
	}
	
	@Override
	public void render(Graphics g){
		
		if(this.lootMap != null){
			this.lootMap.render(g);
		}
		if(this.diploma != null){
			
			this.diploma.render(g);
		}
	}
	
	public LootMap getLootMap(){
		return this.lootMap;
	}
	
	private void init(){

		try{
			this.objects = layer.getObjects();
		}catch(NullPointerException ex){
			ex.printStackTrace();
			return;
		}
		
		for(ObjectsLayerObject o : this.objects){
			if(o.getName().equals("diploma")){
				
				this.diploma = new Diploma(o.getX(),o.getY(),GameState.getMap());
			}
		}
		
		isInitialized = true;
		
		
	}
	
	
}
