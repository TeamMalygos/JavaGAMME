package map;

import java.awt.Graphics;
import java.util.List;

import constants.Constants;
import game.entities.UnitDrawable;

public class LootMap implements UnitDrawable{

	
	private int[][] lootMap;
	private TileMap tilemap;
	
	private int deltaOffsetY;
	private int deltaOffsetX;
	
	private int width;
	private int height;
	
	private int columnsToDraw;
	private int rowsToDraw;
	
	public LootMap(TileMap map){
		
		this.tilemap = map;
		
	}
	
	public void setData(List<Integer> matrix,int w,int h){
		
		this.width = w;
		this.height = h;
		
		this.columnsToDraw = Constants.WIDTH / this.tilemap.getTileWidth();
		this.rowsToDraw = Constants.HEIGHT / this.tilemap.getTileHeight();
		
		this.lootMap = new int[w-2][h-2];
		
		int counter = 0;
		for(int i = 0 ; i < lootMap.length;i++){
			for(int j = 0 ; j < lootMap[i].length;j++){
				lootMap[i][j] = matrix.get(counter);
				counter++;
			}
		}
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		
		
		
	}
	
	
	
	
}
