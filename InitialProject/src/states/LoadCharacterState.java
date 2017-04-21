package states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import events.MouseMotionSensitive;
import states.gui.Interface;
import states.gui.LoadCharacterInterface;


/**
 * 
 * @author G_ANGELOV
 *
 * <p>State which presents to the user all the existing created 
 * characters by traversing the directory and populating
 * the improvised ListView </p>
 *
 */
public class LoadCharacterState extends State implements MouseMotionSensitive{

	private static int ID = 4;
	
	private Interface loadCharacterInterface;
	
	public LoadCharacterState() {
		super(ID);
		
		this.loadCharacterInterface = new LoadCharacterInterface();
		
	}

	@Override
	public void tick() {
		this.loadCharacterInterface.tick();
	}

	@Override
	public void render(Graphics g) {
		
		this.loadCharacterInterface.render(g);
	}

	@Override
	public void onMouseHover(MouseEvent args) {
		this.loadCharacterInterface.onMouseHoverOverInterface(args);
	}

	@Override
	public void onMouseRelease(MouseEvent args) {
		
		this.loadCharacterInterface.onMouseReleaseOverInterface(args);
	}

	@Override
	public void onMouseClick(MouseEvent args) {
		this.loadCharacterInterface.onMouseClickOverInterface(args);
		
	}

	
	
}
