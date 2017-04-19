package map;

import game.entities.enemies.Bug;
import game.entities.enemies.Enemy;
import map.parser.ObjectsLayerObject;
import states.GameState;

public class HostileEntityFactory {

	public HostileEntityFactory(){
		
	}
	
	public Enemy translateToEnemy(ObjectsLayerObject enemy){
		
		switch(enemy.getName().toLowerCase()){
		
		case "bug":
			return new Bug(GameState.getMap(),enemy);
		
		}
		
		return null;
		
	}
	
}
