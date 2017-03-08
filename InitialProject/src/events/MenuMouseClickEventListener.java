package events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import states.GameState;
import states.MenuState;
import states.StateManager;

public class MenuMouseClickEventListener implements MouseListener{

	private boolean isInGameMenu;
	private boolean isMenu;
	
	private boolean isMenuState(){
		try{
			return StateManager.getCurrentState() instanceof MouseMotionSensitive;
		}catch(NullPointerException ex){
			
		}
		return false;
	}
	
	@Override
	public void mouseClicked(MouseEvent mouseArgs) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void mouseEntered(MouseEvent mouseArgs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent mouseArgs) {
		// TODO Auto-generated method stub
		if(!this.isMenuState()){
			if(StateManager.getCurrentState() instanceof GameState){
				GameState g = (GameState)StateManager.getCurrentState();
				
				if(!g.isInMenuState()){
					return;
				}
				
				g.getInGameMenu().onMouseClick(mouseArgs);
			}
			return;
		}
		
		try{
			MouseMotionSensitive sensor = (MouseMotionSensitive)StateManager.getCurrentState();
			sensor.onMouseClick(mouseArgs);
		}catch(ClassCastException ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(MouseEvent mouseArgs) {
		// TODO Auto-generated method stub
		
		if(!this.isMenuState()){
			if(StateManager.getCurrentState() instanceof GameState){
				GameState g = (GameState)StateManager.getCurrentState();
				
				if(!g.isInMenuState()){
					return;
				}
				
				g.getInGameMenu().onMouseRelease(mouseArgs);
			}
			return;
		}
		
		try{
			MouseMotionSensitive sensor = (MouseMotionSensitive)StateManager.getCurrentState();
			sensor.onMouseRelease(mouseArgs);
		}catch(ClassCastException ex){
			ex.printStackTrace();
		}
		
		
	}

}