package utils;

import game.collectibles.Beer;
import game.collectibles.Diamond;
import game.collectibles.LootObject;
import game.collectibles.PowerProvidable;
import states.GameState;

public class EffectTrigger {

	private EffectTrigger(){
		
	}
	
	public static void triggerEffect(LootObject obj){
		
		if(obj == null){
			return;
		}
		
		if(obj instanceof Diamond){
			GameState.getPlayer().getBag().addDiamond();
		}
		
		if(obj instanceof Beer){
			
			PowerProvidable powerup = (PowerProvidable)obj;
			powerup.onPickUp();
			
		}
		
	}
	
}
