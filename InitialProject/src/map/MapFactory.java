package map;

import annotations.Inject;
import constants.Constants;
import map.parser.Loader;

/**
 * 
 * <h3>Map Factory</h3>
 * 
 * <p>Provides and crafts the objects required for
 * basic level set-up</p>
 * 
 * <h3>Dependencies</h3>
 * <p><i><b>1.Loader.class</b></i></p>
 * 
 * @author G_ANGELOV
 *
 */
public class MapFactory {

	private Loader loader;
	
	@Inject public MapFactory(Loader loader){
		this.loader = loader;
	}
	
	public TileMap createNewMap(){
		
		TileMap map = new TileMap(this.loader.getMapMatrix(),this.loader.getMapHeight()
        		,this.loader.getMapWidth());
        
        map.setOffset(this.loader.getMatrixOffsetX(),
        		this.loader.getMatrixOffsetY());
        map.loadTiles(Constants.TILE_SHEET_PATH);
		
        map.setPosition(0, 0);
		
        return map;
	}
	
	public ObjectLayer setUpNewObjectLayer(){

		ObjectLayer objectsLayer = null;
		
		try{
			objectsLayer = new ObjectLayer(loader.getObjects());
        	objectsLayer.setSecondaryTileLayer(loader.getLootLayer());
        	objectsLayer.setOffset(loader.getLootMatrixOffsetX()
        			,loader.getLootMatrixOffsetY());
        }catch(ArrayIndexOutOfBoundsException ex){
        	System.out.println("Current level has no Loot Layer");
    	}catch(NullPointerException ex){
        	System.out.println("Current level has no Loot Layer");
        }
        
        return objectsLayer;
	}
	
	
}
