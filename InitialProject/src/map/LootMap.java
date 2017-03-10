package map;

import java.awt.Graphics;
import java.util.List;

import constants.Constants;
import game.collectibles.Diamond;
import game.collectibles.LootFactory;
import game.collectibles.LootObject;
import game.entities.UnitDrawable;
import states.GameState;
import utils.EffectTrigger;

public class LootMap implements UnitDrawable{

	private double x;
	private double y;
	
	private double xmax;
	private double xmin;
	private double ymax;
	private double ymin;
	
	private int[][] lootMap;
	private TileMap tilemap;
	private LootObject[][] lootObjects;
	
	private int deltaOffsetY;
	private int deltaOffsetX;
	
	private int cols;
	private int rows;
	
	private int columnsToDraw;
	private int rowsToDraw;
	
	private int colOffset;
	private int rowOffset;
	
	public LootMap(TileMap map){
		
		this.tilemap = map;
		
		this.x = 0;
		this.y = 0;
		
		this.xmin = this.tilemap.getXMin();
		this.xmax = 0;
		
		this.ymin = 0;
		this.ymax = this.tilemap.getYMax();
		
		this.colOffset = 0;
		this.rowOffset = 0;
	}
	
	public void setData(List<Integer> matrix,int w,int h){
		
		this.cols = w;
		this.rows = h;
		
		this.columnsToDraw = Constants.WIDTH / this.tilemap.getTileWidth();
		this.rowsToDraw = Constants.HEIGHT / this.tilemap.getTileHeight();
		
		this.lootMap = new int[h][w];
		
		int counter = 0;
		for(int i = 0 ; i < lootMap.length;i++){
			for(int j = 0 ; j < lootMap[i].length;j++){
				lootMap[i][j] = matrix.get(counter);
				counter++;
			}
		}
		
		populateObjectsMatrix();
		
	}
	
	public void setPosition(double x,double y){
		
		this.x = this.x + (x - this.x) * this.tilemap.getCameraSpeed();
		this.y = this.y + (y - this.y) * this.tilemap.getCameraSpeed();

		fixBoundaries();

		this.colOffset = (int)-this.x / this.tilemap.getTileWidth();
		this.rowOffset = (int)-this.y / this.tilemap.getTileHeight();

	}
	


	@Override
	public void tick() {
		
		for(int row = this.rowOffset; row < this.rowOffset + this.rowsToDraw; row++){
			
			for(int col = this.colOffset; col < this.colOffset + this.columnsToDraw; col++){
				if(this.lootObjects[row][col] == null){
					continue;
				}
				
				this.lootObjects[row][col].setPosition(this.x + col * Constants.DIAMOND_WIDTH + this.deltaOffsetX,
						this.y + row * Constants.DIAMOND_HEIGHT - this.deltaOffsetY);
				
				this.lootObjects[row][col]
						.isCollected(GameState.getPlayer().getPickUpRectangle());
					
				if(!this.lootObjects[row][col].checkCollected()){
					this.lootObjects[row][col].tick();
				}else{
					EffectTrigger.triggerEffect(this.lootObjects[row][col]);
					this.lootObjects[row][col] = null;
				}
			}
			
		}
		
	}

	@Override
	public void render(Graphics g) {
		for(int row = this.rowOffset; row < this.rowOffset + this.rowsToDraw;row++){
			
			for(int col = this.colOffset ; col < this.colOffset + this.columnsToDraw;col++){
					
				if(this.lootObjects[row][col] == null){
					continue;
				}
				
				if(!this.lootObjects[row][col].checkCollected()){
					this.lootObjects[row][col].render(g);
					
					/*
					g.drawRect((int)this.lootObjects[row][col].getPosition().getPositionX()
							, (int)this.lootObjects[row][col].getPosition().getPositionY()
							, this.lootObjects[row][col].getWidth()
							, this.lootObjects[row][col].getHeight());
					 */	
					
				}
				
			}
		}
		
	}

	public void setOffset(int offsetX, int offsetY) {
		
		int mapOffsetX = this.tilemap.getOffsetX();
		int mapOffsetY = this.tilemap.getOffsetY();
		this.deltaOffsetX = Math.abs(offsetX - mapOffsetX);
		this.deltaOffsetY = Math.abs(offsetY - mapOffsetY);
		
	}
	
	private void fixBoundaries(){
		if(this.x > xmax){ x = xmax;}
		if(this.x < xmin){ x = xmin;}
		if(this.y > ymax){ y = ymax;}
		if(this.y < ymin){ y = ymin;}
	}
	
	private void populateObjectsMatrix(){
		
		this.lootObjects = new LootObject[this.lootMap.length][this.lootMap[0].length];
		LootFactory lootFactory = new LootFactory();
		
		for(int i = 0 ; i < this.lootMap.length;i++){
			for(int j = 0 ; j < this.lootMap[0].length;j++){
				System.out.println(this.lootMap[i][j]);
				this.lootObjects[i][j] = lootFactory.translateToLootObject(this.lootMap[i][j]);	
			}
		}
		
		
	}
	
	
}
