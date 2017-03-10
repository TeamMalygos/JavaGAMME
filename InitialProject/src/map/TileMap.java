package map;

import java.awt.image.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.List;

import constants.Constants;
import enums.TileType;
import gfx.Assets;

import java.awt.*;

public class TileMap {
	//Position
	private double x;
	private double y;
	
	private double xmin;
	private double xmax;
	private double ymin;
	private double ymax;
	
	//Original map
	private int tileSize;
	private int tileHeight;
	private int[][] map;
	//Dimensions
	private int mapWidth;
	private int mapHeight;
	
	//Tileset
	private BufferedImage tileSet;
	private Tile[][] tiles;
	
	//Offset drawing
	private int rowOffset;
	private int colOffset;
	private int columnsToDraw;
	private int rowsToDraw;
	
	private int mapRows;
	private int mapCols;
	
	private int numTilesAcross;
	private int numTileRows;
	
	private int offsetX;
	private int offsetY;
	
	//Camera movement
	private double cameraSpeed;
	
	public TileMap(List<Integer> map,int numRows,int numCols){
		
			this.cameraSpeed = 1;
			
			this.tileSize = 32;
			this.tileHeight = 32;
			
			this.mapRows = numRows;
			this.mapCols = numCols;
			
			this.mapWidth = numCols * tileSize;
			this.mapHeight = numRows * tileHeight;
			
			this.columnsToDraw = Constants.WIDTH / this.tileSize;
			this.rowsToDraw = Constants.HEIGHT / this.tileHeight;
			
			this.xmin = Constants.WIDTH - this.mapWidth;
			this.xmax = 0;
			
			this.ymin = 0;
			this.ymax = Constants.HEIGHT - this.mapHeight;
			
			this.map = new int[mapHeight][mapWidth];
			
			int counter = 0;
			for(int row = 0 ; row < numRows; row ++){
				
				for(int col = 0 ; col < numCols; col++){
					this.map[row][col] = map.get(counter);
					counter++;
				}
				
			}
			
	}
	
	public void loadTiles(String s){
		
		try {
			
			tileSet = Assets.tileSheet;
			numTilesAcross = tileSet.getWidth() / tileSize;
			numTileRows = tileSet.getHeight() / tileHeight;
			
			
			tiles = new Tile[numTileRows][numTilesAcross];
			
			BufferedImage subImage;
			int height = 0;
			
			for(int row = 0 ; row < numTileRows; row++){
				for(int col = 0 ; col < numTilesAcross; col++){
				
				TileType type = TileType.valueOf(Constants.TILESET_BLOCK_TYPES_OF_ROW[row]);
				
				subImage = tileSet.getSubimage(
						col * tileSize, 
						height, tileSize, tileHeight);
				
				tiles[row][col] = new Tile(subImage,type);
				
				}
				height += tileHeight;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public int getX() {return (int)x;}
	public int getY() { return (int)y; }
	public int getWidth() {return this.mapWidth;}
	public int getHeight() { return this.mapHeight;}
	public int getTileWidth() {return this.tileSize;}
	public int getTileHeight(){return this.tileHeight;}
	public int getTile(int row, int col){
		return map[row][col];
	}
	public int getColOffset(){return this.colOffset;}
	public int getRowOffset(){return this.rowOffset;}
	public double getCameraSpeed(){return this.cameraSpeed;}	
	public double getXMin(){return this.xmin;}
	public double getYMax(){return this.ymax;}
	public int getOffsetX(){return this.offsetX;}
	public int getOffsetY(){return this.offsetY;}
	
	public TileType getType(int row,int col){
		
		try{
			int rc = map[row][col];
			if(rc == 0){
				return TileType.Background;
			}
			int r = rc / tiles[0].length;
			int c = rc % tiles[0].length;
			return tiles[r][c].getType();
		}catch(ArrayIndexOutOfBoundsException ex){
			
		}
		
		return TileType.Background;
	}
	
	public void setPosition(double x,double y){
		this.x = this.x +  (x - this.x) * this.cameraSpeed;
		this.y = this.y +  (y - this.y) * this.cameraSpeed;

		fixBoundaries();	
		
		//Where to start drawing from
		colOffset = (int)-this.x / tileSize;
		rowOffset = (int)-this.y / tileHeight;
		
	}
	
	private void fixBoundaries(){
		if(this.x > xmax){ x = xmax;}
		if(this.x < xmin){ x = xmin;}
		if(this.y > ymax){ y = ymax;}
		if(this.y < ymin){ y = ymin;}
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics g){

		g.drawImage(Assets.background,Constants.BACKGROUND_X
				,Constants.BACKGROUND_Y
				,Constants.WIDTH
				,Constants.HEIGHT,null);
		
		for(int row = this.rowOffset ; row < this.rowOffset + this.rowsToDraw + 1; row++){
	
			if(row >= this.mapRows){
				break;
			}
			
			for(int col = this.colOffset ; col < this.colOffset + this.columnsToDraw + 1; col++){
				int rc =  map [row][col]-1;
				
				if(rc == -1){
					continue;
				}
				
				if(col >= this.mapCols){break;}
				
				int rw = rc / this.numTilesAcross;
				int cl = rc % this.numTilesAcross;
				
				g.drawImage(
						tiles[rw][cl].getImage(),
						(int)x + col * tileSize,
						(int)y + row * tileSize,
						null);
		
			}
		}
	}

	public void setOffset(int offsetX, int offsetY) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
}
