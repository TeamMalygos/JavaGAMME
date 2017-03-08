package game;


import display.Display;
import states.CreateCharacterState;
import states.GameState;
import states.StateManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static constants.Constants.FIREWALL_SPELL_NAME;
import static constants.Constants.HARDCORE_ALGORITHM_SPELL_NAME;
import static constants.Constants.HEAL_SPELL_NAME;

public class InputHandler implements KeyListener {

	private GameState state;
	
    public InputHandler(Display display) {
        display.getCanvas().addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    	
    }

    @Override
    public void keyPressed(KeyEvent e) {
    
    	if(StateManager.getCurrentState() instanceof CreateCharacterState){
    		
    		CreateCharacterState ccs = (CreateCharacterState)StateManager.getCurrentState();
    		ccs.writeDown(e.getKeyChar());
    		
    	}
    	
    	if(!isGameState()){
    		return;
    	}
    	
    	state = (GameState) StateManager.getCurrentState();
    	
    	if(state.isInMenuState()){
    		System.out.println("Sorry you are in menu state");
    		return;
    	}
    	
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT){state.player.setLeft(true);}
        if(keyCode == KeyEvent.VK_RIGHT){state.player.setRight(true);}
        if(keyCode == KeyEvent.VK_UP){state.player.setUp(true);}
        if(keyCode == KeyEvent.VK_DOWN){state.player.setDown(true);}
        if(keyCode == KeyEvent.VK_SPACE){state.player.setJumping(true);}
        
        if (keyCode == KeyEvent.VK_Z) {state.player.castSpell(HEAL_SPELL_NAME);}
        if (keyCode == KeyEvent.VK_X) {state.player.castSpell(FIREWALL_SPELL_NAME);}
        if (keyCode == KeyEvent.VK_C) {state.player.castSpell(HARDCORE_ALGORITHM_SPELL_NAME);}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	
    	if(!isGameState()){return;}
    	state = (GameState) StateManager.getCurrentState();
    	
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_ESCAPE){state.toggleMenu();}
        
        if(state.isInMenuState()){
        	return;
        }
        
        if(keyCode == KeyEvent.VK_LEFT){state.player.setLeft(false);}
        if(keyCode == KeyEvent.VK_RIGHT){state.player.setRight(false);}
        if(keyCode == KeyEvent.VK_UP){state.player.setUp(false);}
        if(keyCode == KeyEvent.VK_DOWN){state.player.setDown(false);}
        if(keyCode == KeyEvent.VK_SPACE){state.player.setJumping(false);
        state.player.getPlayerStats().setCurrentHealth(
        		state.player.getPlayerStats().getCurrentHealth() - 50);
        }
      
    }
    
    private boolean isGameState(){
    	return StateManager.getCurrentState().getID() == 2;
    }
}
