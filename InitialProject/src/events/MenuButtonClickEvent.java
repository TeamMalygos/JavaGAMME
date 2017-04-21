package events;

import java.util.EventObject;

import states.State;
import states.StateManager;

@SuppressWarnings("serial")
public class MenuButtonClickEvent extends EventObject{

	
	/**
	 * <p><em>MenuButtonClickEvent</em> extends EventOject superclass
	 * and provides an easy way to change the GameState from the
	 * menu itself.</p>
	 */

	public MenuButtonClickEvent(Object source,State state) {
		super(source);
		setState(state);
	}
	
	private void setState(State state){
		
		StateManager.setCurrentState(state);
		
	}

}
