package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import components.PlayerListItem;
import constants.Constants;
import game.entities.playerProperties.Stats;

/**
 * 
 * @author G_ANGELOV
 *
 *<p>Well, i realized that it would be better
 * to create a singleton class which will
 * handle further serialization and deserialization
 * of game objects and it would be far more convinient
 * to have pre-definde methods.</p> 
 * 
 * <h4>Manual:</h4>
 * <p><i>1.Get the singleton instance</i></p>
 * <p><i>2.Call ser or deser methods using the instance<i></p>
 *
 */
public class ObjectSerializer {

	private static ObjectSerializer serializer = new ObjectSerializer();
	private ObjectOutputStream objectWriter;
	private ObjectInputStream objectReader; 
	
	private FileInputStream fileIn;
	private FileOutputStream fileOut;
	
	private ObjectSerializer(){
		
	}
	
	public static ObjectSerializer getInstance(){
		return serializer;
	}
	
	public void saveCurrentGameState(){
		
		Stats st = UserAccount.getStats();
		
		String playerFilePath = System.getProperty("user.dir") +
				Constants.DIRECTORY_PLAYER_SERIALIZATION + "/"
				+ UserAccount.getStats().getPlayerName() + ".ser";
		
		try {
            this.objectWriter = new ObjectOutputStream(new FileOutputStream(playerFilePath));
            this.objectWriter.writeObject(st);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	public void serializeNewCharacter(String name){
		
		if(!UserAccount.playerExists()){
			
			Stats s = new Stats(name);
			UserAccount.setStat(s);
			
		}
		
		this.saveCurrentGameState();
		
	}
	
	public void loadGameState(PlayerListItem playerListItem){
		
		if(playerListItem == null){
			throw new NullPointerException();
		}
		
		UserAccount.setStat(playerListItem.getStats());
		
	}
	
}
