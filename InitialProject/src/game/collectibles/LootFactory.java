package game.collectibles;

public class LootFactory {

	private int counter;
	
	public LootFactory(){
		this.counter = 0;
	}
	
	public LootObject translateToLootObject(int number){
		
		switch(number){
		
		case 181:
			return new Diamond(counter);
		case 189:
			return new Beer(counter);
		}
		
		return null;
	}
	
	
}
