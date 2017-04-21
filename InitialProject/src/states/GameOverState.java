package states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import enums.Level;
import events.MouseMotionSensitive;
import states.gui.GameOverInterface;
import states.gui.Interface;

public class GameOverState extends State implements MouseMotionSensitive{

	private final static int ID =  8;
	
	private Level currentLevel;
	
	private Interface gameOverInterface;
	
	public GameOverState(Level l){
		super(ID);
		
		this.gameOverInterface = new GameOverInterface();
		this.setLevel(l);
		
	}
	
	@Override
	public void tick() {
		this.gameOverInterface.tick();
		
	}

	@Override
	public void render(Graphics g) {
		this.gameOverInterface.render(g);
	}

	@Override
	public void onMouseHover(MouseEvent args) {
		this.gameOverInterface.onMouseHoverOverInterface(args);
	}

	@Override
	public void onMouseRelease(MouseEvent args) {
		this.gameOverInterface.onMouseReleaseOverInterface(args);
	}

	@Override
	public void onMouseClick(MouseEvent args) {
		this.gameOverInterface.onMouseClickOverInterface(args);
	}
	
	private void setLevel(Level l){
		this.currentLevel = l;
	}
	
}
