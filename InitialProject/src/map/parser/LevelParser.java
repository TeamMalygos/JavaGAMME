package map.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

public class LevelParser {

	private Gson gson;
	private MapStructure mapData;
	private JsonReader reader;
	
	
	public LevelParser(String path){
		init(path);
		
	}
	
	public void init(String path){
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Layered.class, new LayeredDeserializer());
		
		gson = gsonBuilder.create();
		
		
		try {
			mapData =  gson.fromJson(new FileReader(LevelParser.class.getResource(path).getPath()), MapStructure.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			
		} catch (JsonIOException e) {
			e.printStackTrace();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}
		
	}
	
	public MapStructure getMapData(){
		return mapData;
	}
	
	
	
}
