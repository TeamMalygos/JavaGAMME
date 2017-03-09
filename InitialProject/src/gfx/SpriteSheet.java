package gfx;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import constants.Constants;

public class SpriteSheet {
	
	private BufferedImage sheet;
    private ArrayList<BufferedImage[]> frameSet;
    private int[] frameLayers;
    
    private int frameHeight;
    private int frameWidth;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
        frameSet = new ArrayList<BufferedImage[]>();
    }
    
    public void setFrameLayersCount(int[] layersCount,int frameWidth,int frameHeight){
    	this.frameLayers = layersCount;
    	this.frameWidth = frameWidth;
    	this.frameHeight = frameHeight;
    	prepare();
    }
    
    
    public BufferedImage getFrame(int state,int index){
    	return this.frameSet.get(state)[index];}
    public BufferedImage[] getFrameSet(int state){
    	return this.frameSet.get(state);}
    public int getWidth(){return this.frameWidth;}
    public int getHeight(){return this.frameHeight;}
    
    private void prepare(){
    	
    	try{
    		
    		for(int i = 0 ; i < frameLayers.length ; i++){
    			BufferedImage[] actionSet = new BufferedImage[frameLayers[i]];
    			
    			for(int j = 0 ; j < frameLayers[i]; j++){
    				
    				actionSet[j] = this.sheet.getSubimage(
    						j * this.frameWidth,
    						i * this.frameHeight,
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
    
    
}