package events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import states.MenuState;
import states.StateManager;

public class MenuMouseClickEventListener implements MouseListener{

	private boolean isMenuState(){
		return StateManager.getCurrentState().getID() == 1;
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
			return;
		}
		
		MenuState menu = (MenuState) StateManager.getCurrentState();
		menu.getMenu().onMenuItemClick(mouseArgs);
	}

	@Override
	public void mouseReleased(MouseEvent mouseArgs) {
		// TODO Auto-generated method stub
		if(!this.isMenuState()){
			return;
		}
		
		MenuState menu = (MenuState) StateManager.getCurrentState();
		menu.getMenu().onMenuItemRelease(mouseArgs);
	}

}
