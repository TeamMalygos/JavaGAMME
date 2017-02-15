package map.parser;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * <p>Custom deserializer for layers section in the .json
 * map file. It decides how to distribute the nested objects</p>
 * @author G_ANGELOV
 *
 */
public class LayeredDeserializer implements JsonDeserializer<Layered> {

	@Override
	public Layered deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		
		JsonObject jObject = (JsonObject)json;
		JsonElement typeObj = jObject.get("type");
		
		if(typeObj != null){
			
			String typeValue = typeObj.getAsString();
			
			switch(typeValue){
			
			case "tilelayer":
				return context.deserialize(json, TileLayer.class);
			case "objectgroup":
				return context.deserialize(json, ObjectsLayer.class);
			
			}		
			
		}
		
		return null;	
	}
		
		
}

