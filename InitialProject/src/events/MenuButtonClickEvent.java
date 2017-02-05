package events;

import java.util.EventObject;

import states.State;
import states.StateManager;

public class MenuButtonClickEvent extends EventObject{

	
	/**
	 * <p><em>MenuButtonClickEvent</em> extends EventOject superclass
	 * and provides an easy way to change the GameState from the
	 * menu itself.</p>
	 */
	private static final long serialVersionUID = 274677211232625364L;

	public MenuButtonClickEvent(Object source,State state) {
		super(source);
		setState(state);
	}
	
	private void setState(State state){
		
		StateManager.setCurrentState(state);
		
	}

}
