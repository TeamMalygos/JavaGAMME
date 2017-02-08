package game;


import display.Display;
import states.GameState;
import states.StateManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    
    	if(!isGameState()){return;}
    	
    	state = (GameState) StateManager.getCurrentState();
    	
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT){state.player.setLeft(true);}
        if(keyCode == KeyEvent.VK_RIGHT){state.player.setRight(true);}
        if(keyCode == KeyEvent.VK_UP){state.player.setUp(true);}
        if(keyCode == KeyEvent.VK_DOWN){state.player.setDown(true);}
        if(keyCode == KeyEvent.VK_SPACE){state.player.setJumping(true);}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	
    	if(!isGameState()){return;}
    	state = (GameState) StateManager.getCurrentState();
    	
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT){state.player.setLeft(false);}
        if(keyCode == KeyEvent.VK_RIGHT){state.player.setRight(false);}
        if(keyCode == KeyEvent.VK_UP){state.player.setUp(false);}
        if(keyCode == KeyEvent.VK_DOWN){state.player.setDown(false);}
        if(keyCode == KeyEvent.VK_SPACE){state.player.setJumping(false);}
      
    }
    
    private boolean isGameState(){
    	return StateManager.getCurrentState().getID() == 2;
    }
}
