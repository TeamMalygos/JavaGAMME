package map;

import java.awt.image.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.List;

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
	
	//Camera movement
	private double cameraSpeed;
	
	public TileMap(List<Integer> map,int mapWidth,int mapHeight){

			this.cameraSpeed = 0.07;
			
			this.mapWidth = mapWidth;
			this.mapHeight = mapHeight;
			this.tileSize = 32;
			this.tileHeight = 32;
			this.map = new int[mapHeight][mapWidth];
			
			int counter = 0;
			for(int row = 0 ; row < mapHeight; row ++){
				
				for(int col = 0 ; col < mapWidth; col++){
					
					this.map[row][col] = map.get(counter);
					counter++;
				}
				
			}
			
	}
	
	public void loadTiles(String s){
		
		try {
			
			tileSet = Assets.tileSheet;
			int numTilesAcross = tileSet.getWidth() / tileSize;
			int numTileRows = tileSet.getHeight() / tileHeight;
			System.out.println(numTileRows);
			tiles = new Tile[numTileRows][numTilesAcross];
			
			BufferedImage subImage;
			int height = 0;
			
			for(int row = 0 ; row < numTileRows; row++){
				for(int col = 0 ; col < numTilesAcross; col++){
				
				subImage = tileSet.getSubimage(
						col * tileSize, 
						height, tileSize, tileHeight);
				
				tiles[row][col] = new Tile(subImage,false);
				
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
	
	public boolean isBlocked(int row,int col){
		int rc = map[row][col];
		int r = rc / tiles[0].length;
		int c = rc % tiles[0].length;
		return tiles[r][c].getType();
	}
	
	public void setPosition(double x,double y){
		this.x += (x - this.x) * this.cameraSpeed;
		this.y += (y - this.y) * this.cameraSpeed;
		
		fixBoundaries();
		
		//Where to start drawing from
		
		colOffset = (int)-this.x / tileSize;
		rowOffset = (int)-this.y / tileHeight;
		
	}
	
	private void fixBoundaries(){
		if(x > xmax){ x = xmax;}
		if(x < xmin){ x = xmin;}
		if(y > ymax){ y = ymax;}
		if(y < ymin){ y = ymin;}
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics g){
		
		for(int row = this.rowOffset ; row < map.length; row++){
			
			for(int col = this.colOffset ; col < map[0].length; col++){
				int rc =  map [row][col]-1;
				
				if(rc == -2 || rc == -1){
					continue;
				}
				
				int rw = rc / tiles[0].length;
				int cl = rc % tiles[0].length;
				
				g.drawImage(
						tiles[rw][cl].getImage(),
						(int)x + col * tileSize,
						(int)y + row * tileSize,
						null);
		
			}
		}
	}
}
	
	
/*
	//position
	private double x;
	private double y;
	
	//bounds
	private int xmin;
	private int xmax;
	private int ymin;
	private int ymax;
	
	private double tween;
	
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;
	
	//tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;
	
	//recycler view
	private int rowOffset;
	private int colOffset;
	private int numColsToDraw;
	private int numRowsToDraw;
	
	public TileMap(int tileSize){
		this.tileSize = tileSize;
		numRowsToDraw = 800 / tileSize + 2;
		numColsToDraw = 600 / tileSize + 2;
		tween = 0.07;
	}
	
	public void loadTiles(String s){
		
		try{
			
			tileset = ImageIO.read(
					getClass().getResourceAsStream(s)
			);
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];
			
			BufferedImage subImage;
			
			for(int col = 0 ; col < numTilesAcross; col++){
				
				subImage = tileset.getSubimage(
							col * tileSize,
							0,
							tileSize,
							tileSize
						);
				tiles[0][col] = new Tile(subImage,Tile.NORMAL);
				
				subImage = tileset.getSubimage(
						col * tileSize,
						tileSize,
						tileSize,
						tileSize
					);
				
				tiles[1][col] = new Tile(subImage,Tile.BLOCKED);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public void loadMap(String s) throws Exception, IOException{
	
		try{
			
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			
			width = numCols * tileSize;
			height = numRows * tileSize;
			
			for(int col = 0 ; col < numCols ; col++){
				
				String line = br.readLine();
				String[] tokens = line.split("\\s+");
				
				for(int row = 0 ; row < numRows; row++){
					
					map[col][row] = Integer.parseInt(tokens[row]);
					
				}
				
			}
			
			
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
		
	}
	
	public int getTileSize() { return tileSize;}
	public int getX() { return (int)x;}
	public int getY() { return (int)y;}
	public int getWidth() {return width;}
	public int getHeight() { return height;}
	public int getType(int row,int col){
		
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		
		return tiles[r][c].getType();
		
	}
	
	public void setPosition(double x, double y){
		this.x = (x - this.x) * tween;
		this.y = (y - this.y) * tween;
		
		fixBounds();
		
		colOffset = (int)-this.x / tileSize;
		rowOffset = (int)-this.y / tileSize;
		
	}
	
	public void fixBounds(){
		if(x < xmin) x = xmin;
		if(x > xmax) x = xmax;
		if(y < ymin) y = ymin;
		if(y > ymax) y = ymax;
	}
	
	public void draw(Graphics2D g){
		
		for(int row = rowOffset; row < rowOffset + numRowsToDraw; row++){
			if(row >= numRows){
				break;
			}
			for(int col = colOffset; col < colOffset + numColsToDraw; col++){
				if(col >= numCols){
					break;
					
				}
				if(map[row][col] == 0){
					continue;
				}
				
				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				
				g.drawImage(
						tiles[r][c].getImage(), 
						(int)x + col * tileSize, 
						(int)y + row * tileSize,
						null);
				
			}
			
		}
		
	}
	*/
	
