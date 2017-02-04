package map;
import java.awt.*;
import java.awt.image.*;

public class Tile {

	private BufferedImage image;
	private boolean blocked;
	
	/**
	 * 
	 * <p>Constructor for the Tile object</p>
	 * @param BufferedImage tileImage
	 * @param boolean canItCollide
	 * 
	 */
	public Tile(BufferedImage image, boolean blocked){
		this.image = image;
		this.blocked = blocked;
	}
	
	public BufferedImage getImage(){ return image; }
	public boolean getType() { return blocked;}
	
}
