package states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import events.MouseMotionSensitive;

import states.gui.Interface;
import states.gui.LevelCompletionInterface;


public class LevelCompletedState extends State implements MouseMotionSensitive { 

	private final static int ID = 10;
	
	private Interface levelCompletionInterface;
	
	public LevelCompletedState() {
		super(ID);
		this.levelCompletionInterface = new LevelCompletionInterface();
	}

	
	@Override
	public void tick() {
		this.levelCompletionInterface.tick();
	}

	@Override
	public void render(Graphics g) {
		this.levelCompletionInterface.render(g);
	}

	@Override
	public void onMouseHover(MouseEvent args) {
		this.levelCompletionInterface.onMouseHoverOverInterface(args);
	}

	@Override
	public void onMouseRelease(MouseEvent args) {
		this.levelCompletionInterface.onMouseReleaseOverInterface(args);
	}

	@Override
	public void onMouseClick(MouseEvent args) {
		this.levelCompletionInterface.onMouseClickOverInterface(args);
	}

}