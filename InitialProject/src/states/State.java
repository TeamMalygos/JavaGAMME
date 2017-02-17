package states;

import java.awt.*;

public abstract class State {
	
	protected int ID;
	
	protected State(int ID){
		this.ID = ID;
	}
	
	public int getID(){
		return this.ID;
	}
	
    public abstract void tick();

    public abstract void render(Graphics g);
}