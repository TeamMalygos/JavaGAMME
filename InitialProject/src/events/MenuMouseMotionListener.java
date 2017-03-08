package events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import states.GameState;
import states.StateManager;

public class MenuMouseMotionListener implements MouseMotionListener{

	private boolean isMenuState(){
		if(StateManager.getCurrentState() == null){
			return false;
		}
		
		return StateManager.getCurrentState() instanceof MouseMotionSensitive;
		
	}
	
	
	@Override
	public void mouseDragged(MouseEvent mouseArgs) {
		// TODO Auto-generated method stub
		
	}
 
	@Override
	public void mouseMoved(MouseEvent mouseArgs) {
		// TODO Auto-generated method stub
		if(!this.isMenuState()){
			if(StateManager.getCurrentState() instanceof GameState){
				GameState g = (GameState)StateManager.getCurrentState();
				
				if(!g.isInMenuState()){
					return;
				}
				
				g.getInGameMenu().onMouseHover(mouseArgs);
			}
			return;
		}
	
		try{		
			MouseMotionSensitive sensor = (MouseMotionSensitive)StateManager.getCurrentState();
			sensor.onMouseHover(mouseArgs);
		}catch(ClassCastException ex){
			ex.printStackTrace();
		}
		
		
	}

}
