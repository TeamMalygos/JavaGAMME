package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import constants.Constants;
import game.entities.Drawable;
import gfx.Assets;

public class ParallaxBackground implements Drawable{

	private final BufferedImage[] PARTS = new BufferedImage[]{
			Assets.backgroundSky,Assets.backgroundMountains,Assets.backgroundTrees,Assets.backgroundGrass
	};
	
	private ParallaxPart[] parts;
	private ParallaxPart[] partsDuplicate;
	
	public ParallaxBackground(){
		
		this.parts = new ParallaxPart[PARTS.length];
		this.partsDuplicate = new ParallaxPart[PARTS.length];
		
		for(int i = 0 ; i < PARTS.length;i++){
			
			parts[i] = new ParallaxPart(Constants.BACKGROUND_X
					,Constants.HEIGHT - PARTS[i].getHeight()
					,Constants.PARALLAX_DELAY[i]
					,PARTS[i]);
			
			partsDuplicate[i] = new ParallaxPart(parts[i].getImage().getWidth()
					,(int)parts[i].getPosition().getPositionY()
					,Constants.PARALLAX_DELAY[i]
					,PARTS[i]);
			
		}
		
	}

	@Override
	public void tick() {
		//Parts are moving
		
		
	}
	@Override
	public void render(Graphics g) {
		//Generating parts
		
		for(int i = 0 ; i < this.PARTS.length;i++){
			
			this.parts[i].render(g);
			this.partsDuplicate[i].render(g);
			
		}
		
	}
	

}
