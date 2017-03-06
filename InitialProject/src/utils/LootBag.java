package utils;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LootBag implements Serializable{

	private int diamonds;
	
	public LootBag(){}
	
	public void addDiamond(){
		diamonds++;
	}
	
	public void useDiamonds(int cost){
		if(cost <= diamonds){
			diamonds -= cost;
			return;
		}
		
		System.out.println("Insufficient currency");
		
	}
	
	public int getDiamondsCount(){
		return this.diamonds;
	}
	
}
