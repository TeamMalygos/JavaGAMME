package map;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import constants.Constants;
import game.entities.Drawable;
import game.entities.enemies.Enemy;
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
	
	private HostileEntityFactory factory;
	private List<Enemy> enemies;
	
	private Diploma diploma;
	
	private boolean isInitialized;
	
	public ObjectLayer(ObjectsLayer layer){
		this.layer = layer;
		this.isInitialized = false;
		
		this.factory = new HostileEntityFactory();
		this.enemies = new ArrayList<Enemy>();
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
		this.enemies.forEach(x -> x.tick());
		this.enemies = this.enemies.stream().filter(x -> !x.isDead()).collect(Collectors.toList());
		
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
		this.enemies.stream().forEach(x -> x.render(g));
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
			System.out.println(o.getName());
			if(o.getName().equals("diploma")){
				this.diploma = new Diploma(o.getX(),o.getY(),GameState.getMap());
			}
			
			if(o.getType().toLowerCase().equals("enemy")){
				this.enemies.add(this.factory.translateToEnemy(o));
			}
		}
		
		isInitialized = true;
		
		
	}
	
	
}
