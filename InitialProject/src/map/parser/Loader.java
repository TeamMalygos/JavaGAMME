package map.parser;

import java.util.List;

/**
 * <h3>Loader Interface</h3>
 * <p>Loader for the levels. Standard interface providing
 * only required methods when loading the map and 
 * hides all the parsing and the parsed JSON 
 * structure.</p>
 * 
 * @author G_ANGELOV
 *
 */
public interface Loader {

	List<Integer> getMapMatrix();
	TileLayer getLootLayer();
	
	int getLootMatrixOffsetX();
	int getLootMatrixOffsetY();
	
	int getMapHeight();
	int getMapWidth();
	
	int getMatrixOffsetX();
	int getMatrixOffsetY();
	
	ObjectsLayer getObjects();
	
}
