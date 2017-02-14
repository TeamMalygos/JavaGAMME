package map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class LevelParser {

	private Gson gson;
	private StringBuilder levelJson;
	
	
	public LevelParser(String path){
		
		levelJson = new StringBuilder();
		init(path);
		
	}
	
	public void init(String path){
		try{
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(LevelParser.class.getResourceAsStream(path)));
		
			String line = reader.readLine();
			while(line != null){
				
				levelJson.append(line);
				levelJson.append("\n");
				
			}
		
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	
	
	
}
