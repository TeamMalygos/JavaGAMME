package map;

import java.awt.image.*;
import java.util.List;

import constants.Constants;
import enums.TileType;
import gfx.Assets;

import java.awt.*;

public class TileMap{
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
			init(numRows, numCols);
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
			
			this.tileSet = Assets.tileSheet;
			this.numTilesAcross = tileSet.getWidth() / tileSize;
			this.numTileRows = tileSet.getHeight() / tileHeight;
			
			
			this.tiles = new Tile[numTileRows][numTilesAcross];
			int height = 0;
			
			for(int row = 0 ; row < numTileRows; row++){
				loadTileRow(height, row);
				height += tileHeight;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	//Getters
	public int getX() {return (int)x;}
	public int getY() { return (int)y; }
	public int getWidth() {return this.mapWidth;}
	public int getHeight() { return this.mapHeight;}
	public int getTileWidth() {return this.tileSize;}
	public int getTileHeight(){return this.tileHeight;}
	public int getTile(int row, int col){
		return this.map[row][col];
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
			int rc = this.map[row][col];
			if(rc == 0){
				return TileType.Background;
			}
			int r = rc / this.tiles[0].length;
			int c = rc % this.tiles[0].length;
			return this.tiles[r][c].getType();
		}catch(ArrayIndexOutOfBoundsException ex){
			
		}
		
		return TileType.Background;
	}
	
	
	/**
	 * <p>This method calculates the offsets it has to use in order
	 * to draw the right piece of the matrix based on coordinates
	 * x and y</p>
	 * 
	 * @param x
	 * @param y
	 */
	public void setPosition(double x,double y){
		this.x = this.x +  (x - this.x) * this.cameraSpeed;
		this.y = this.y +  (y - this.y) * this.cameraSpeed;

		fixBoundaries();	
		
		//Where to start drawing from
		this.colOffset = (int)-this.x / this.tileSize;
		this.rowOffset = (int)-this.y / this.tileHeight;
		
	}
	public void setOffset(int offsetX, int offsetY) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
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
			
			this.drawRows(g, row);
		}
	}

	private void drawRows(Graphics g, int row) {
		for(int col = this.colOffset ; col < this.colOffset + this.columnsToDraw + 1; col++){
			int rc =  this.map [row][col]-1;
			
			if(rc == -1){
				continue;
			}
			
			if(col >= this.mapCols){break;}
			
			int rw = rc / this.numTilesAcross;
			int cl = rc % this.numTilesAcross;
			
			g.drawImage(
					this.tiles[rw][cl].getImage(),
					(int)x + col * this.tileSize,
					(int)y + row * this.tileSize,
					null);

		}
	}

	private void fixBoundaries(){
		if(this.x > this.xmax){ this.x = this.xmax;}
		if(this.x < this.xmin){ this.x = this.xmin;}
		if(this.y > this.ymax){ this.y = this.ymax;}
		if(this.y < this.ymin){ this.y = this.ymin;}
	}
	
	
	private void init(int numRows, int numCols) {
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
	}
	
	private void loadTileRow(int height, int row) {
		BufferedImage subImage;
		for(int col = 0 ; col < numTilesAcross; col++){
		
		TileType type = TileType.valueOf(Constants.TILESET_BLOCK_TYPES_OF_ROW[row]);
		
		subImage = tileSet.getSubimage(
				col * tileSize, 
				height, tileSize, tileHeight);
		
		this.tiles[row][col] = new Tile(subImage,type);
		
		}
	}
	
}
