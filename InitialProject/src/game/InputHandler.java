package game;

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
	
    public InputHandler() {
    	
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
    	
    	if(state.isInMenuState()){
    		System.out.println("Sorry you are in menu state");
    		return;
    	}
    	
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT){GameState.getPlayer().setLeft(true);}
        if(keyCode == KeyEvent.VK_RIGHT){GameState.getPlayer().setRight(true);}
        if(keyCode == KeyEvent.VK_UP){GameState.getPlayer().setUp(true);}
        if(keyCode == KeyEvent.VK_DOWN){GameState.getPlayer().setDown(true);}
        if(keyCode == KeyEvent.VK_SPACE){GameState.getPlayer().setJumping(true);}

        // Spell learn
        if (keyCode == KeyEvent.VK_1) {GameState.getPlayer().learnSpell(HEAL_SPELL_NAME);}
        if (keyCode == KeyEvent.VK_2) {GameState.getPlayer().learnSpell(FIREWALL_SPELL_NAME);}
        if (keyCode == KeyEvent.VK_3) {GameState.getPlayer().learnSpell(HARDCORE_ALGORITHM_SPELL_NAME);}

        // Spell cast
        if (keyCode == KeyEvent.VK_Z) {GameState.getPlayer().castSpell(HEAL_SPELL_NAME);}
        if (keyCode == KeyEvent.VK_X) {GameState.getPlayer().castSpell(FIREWALL_SPELL_NAME);}
        if (keyCode == KeyEvent.VK_C) {GameState.getPlayer().castSpell(HARDCORE_ALGORITHM_SPELL_NAME);}

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
        
        if(keyCode == KeyEvent.VK_LEFT){GameState.getPlayer().setLeft(false);}
        if(keyCode == KeyEvent.VK_RIGHT){GameState.getPlayer().setRight(false);}
        if(keyCode == KeyEvent.VK_UP){GameState.getPlayer().setUp(false);}
        if(keyCode == KeyEvent.VK_DOWN){GameState.getPlayer().setDown(false);}
        if(keyCode == KeyEvent.VK_SPACE){GameState.getPlayer().setJumping(false);}
      
    }
    
    private boolean isGameState(){
    	return StateManager.getCurrentState().getID() == 2;
    }
}
