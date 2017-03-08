package utils;

import game.entities.playerProperties.Stats;

public class UserAccount {

	private static Stats currentStat;
	
	private UserAccount(){
		
	}
	
	public static boolean playerExists(){
		return currentStat != null;
	}
	
	public static Stats getStats(){
		return currentStat;
	}
	
	public static void setStat(Stats s){
		
		currentStat = s;
		
	}
	
	
}
