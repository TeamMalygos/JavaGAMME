package map;
import java.awt.*;
import java.awt.image.*;

import enums.TileType;

public class Tile {

	private BufferedImage image;
	private TileType type;
	
	public static final int FREE = 0;
	public static final int BLOCKED = 1;
	public static final int CLIMBABLE = 2;
	
	/**
	 * 
	 * <p>Constructor for the Tile object</p>
	 * @param BufferedImage tileImage
	 * @param boolean canItCollide
	 * 
	 */
	public Tile(BufferedImage image, TileType type){
		this.setImage(image);
		this.setType(type);
	}
	
	public BufferedImage getImage(){ return image; }
	public TileType getType() { return this.type;}
	public void setImage(BufferedImage image){this.image = image;}
	public void setType(TileType t){this.type = t;}
	
}
