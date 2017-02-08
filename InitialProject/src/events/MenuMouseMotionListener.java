package events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import states.MenuState;
import states.StateManager;

public class MenuMouseMotionListener implements MouseMotionListener{

	private boolean isMenuState(){
		if(StateManager.getCurrentState() == null){
			return false;
		}
		return StateManager.getCurrentState().getID() == 1;
	}
	
	@Override
	public void mouseDragged(MouseEvent mouseArgs) {
		// TODO Auto-generated method stub
		
	}
 
	@Override
	public void mouseMoved(MouseEvent mouseArgs) {
		// TODO Auto-generated method stub
		if(!this.isMenuState()){
			return;
		}
		
		MenuState menu = (MenuState) StateManager.getCurrentState();
		menu.getMenu().onMenuItemHover(mouseArgs);
	}

}
