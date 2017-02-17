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
			return StateManager.getCurrentState().getID() == 1;
		}catch(NullPointerException ex){
			
		}
		return false;
	}
	
	private boolean isInGameMenuOpen(){
		try{
			if(StateManager.getCurrentState().getID() == 2){
				GameState state = (GameState)StateManager.getCurrentState();
				return state.isInMenuState();
			}
		}catch(NullPointerException ex){
			ex.printStackTrace();
		}catch(ClassCastException ex){
			ex.printStackTrace();
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
			return;
		}
		
		MenuState menu = (MenuState) StateManager.getCurrentState();
		menu.getMenu().onMenuItemClick(mouseArgs);
	}

	@Override
	public void mouseReleased(MouseEvent mouseArgs) {
		// TODO Auto-generated method stub
		
		this.isInGameMenu = this.isInGameMenuOpen();
		this.isMenu = this.isMenuState();
		
		try{
		
			if(isMenu){
				MenuState menu = (MenuState) StateManager.getCurrentState();
				menu.getMenu().onMenuItemRelease(mouseArgs);
			}
		
			if(isInGameMenu){
				GameState inGameMenu = (GameState) StateManager.getCurrentState();
				inGameMenu.menu().onMenuItemRelease(mouseArgs);
			}
			
		}catch(NullPointerException ex){
			ex.printStackTrace();
		}catch(ClassCastException ex){
			ex.printStackTrace();
		}
		
		
	}

}