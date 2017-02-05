package events;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.sun.glass.ui.Menu;

import components.Button;
import states.MenuState;
import states.StateManager;

public class MenuMouseClickEventListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent mouseArgs) {
		// TODO Auto-generated method stub
		
		if(StateManager.getCurrentState().getID() != 1){
			return;
		}
		
		MenuState menu = (MenuState) StateManager.getCurrentState();
		menu.getMenu().onMenuItemClick(mouseArgs);
		
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
