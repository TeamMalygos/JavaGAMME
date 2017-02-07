package gfx;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteSheet {
	
	private BufferedImage sheet;
    private ArrayList<BufferedImage[]> frameSet;
    private int[] frameLayers;
    
    private int frameWidth;
    private int frameHeight;

    public SpriteSheet(BufferedImage sheet,int width,int height) {
        this.sheet = sheet;
        this.frameWidth = width;
        this.frameHeight = height;
        frameSet = new ArrayList<BufferedImage[]>();
    }
    
    public void setFrameLayersCount(int[] layersCount){
    	this.frameLayers = layersCount;
    	prepare();
    }
    
    private void prepare(){
    	
    	try{
    		
    		for(int i = 0 ; i < frameLayers.length ; i++){
    			BufferedImage[] actionSet = new BufferedImage[frameLayers[i]];
    			
    			for(int j = 0 ; j < frameLayers[i]; j++){
    				
    				actionSet[j] = this.sheet.getSubimage(
    						i * this.frameWidth,
    						j * this.frameHeight,
    						this.frameWidth,
    						this.frameHeight
    						);
    				
    			}
    			frameSet.add(actionSet);
    		}
    		
    	}catch(NullPointerException ex){
    		ex.printStackTrace();
    	}
    	
    }
    
    public BufferedImage getFrame(int state,int index){
    	return this.frameSet.get(state)[index];}
    public BufferedImage[] getFrameSet(int state){
    	return this.frameSet.get(state);}
    public int getWidth(){return this.frameWidth;}
    public int getHeight(){return this.frameHeight;}
    
    
}
