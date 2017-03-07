package events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

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
