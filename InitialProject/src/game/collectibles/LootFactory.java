package game.collectibles;

public class LootFactory {

	private int counter;
	
	public LootFactory(){
		this.counter = 0;
	}
	
	public LootObject translateToLootObject(int number){
		
		switch(number){
		
		case 33:
			return new Diamond(counter);
		case 41:
			return new Beer(counter);
		}
		
		return null;
	}
	
	
}
