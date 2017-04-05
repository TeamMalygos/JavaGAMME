package gfx;


import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 * 
 * <h3>Sprite Sheet Assembler</h3>
 * 
 * <p>This class chops the  sprite sheets into
 * frames. It's flexible and you can chop sprite sheets
 * in different ways. After being loaded as resources
 * the sprite sheets are being sent here to undergo
 * a surgical sub-imaging and framing</p>
 * 
 * @author G_ANGELOV
 *
 */
public class SpriteSheet {
	
	private BufferedImage sheet;
    private ArrayList<BufferedImage[]> frameSet;
    private int[] frameLayers;
    
    private int frameHeight;
    private int frameWidth;

    /**
     * <p>With the constructor you provide the initial sprite sheet</p>
     * @param sheet
     */
    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
        frameSet = new ArrayList<BufferedImage[]>();
    }
    
    /**
     * <p>Through this method the class requires
     * a set of values representing the number of frames
     * in each row of the sprtesheet and the width/height
     * of each frame</p>
     * 
     * <h3>Example</h3>
     * <p><i><b>int[] exampleArray = new int[] { 3,4,5 } , width: 50, height: 50<br>
     * With this particular set of values the class will chop the sprite sheet<br>
     * into three rows consisting of the relative number of frames on each row<br>
     * <u>first row - 3 frames, seconds row - 4 frames, third row - 5 frames<br>
     * each width:50,height:50</u></b></i></p>
     *
     * <p><i><b>Going out of the image boundaries is possible so be careful
     * of unexpected Exceptions</b></i></p>
     * 
     * @param int[] layersCount
     * @param int frameWidth
     * @param int frameHeight
     *
     */
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