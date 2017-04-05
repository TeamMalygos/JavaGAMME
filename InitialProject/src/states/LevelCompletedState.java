package states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import components.Button;
import constants.Constants;
import events.MouseMotionSensitive;
import gfx.Assets;


public class LevelCompletedState extends State implements MouseMotionSensitive { 

	private final static int ID = 10;
	
	private BufferedImage successBackground;
	private Button exit;
	
	public LevelCompletedState() {
		super(ID);
		this.setBackground(Assets.levelCompleted);
		
		this.exit = new Button(
				Constants.MENU_BUTTON_X
				, Constants.MENU_BUTTON_Y + Constants.MENU_BUTTON_MARGIN_BOTTOM * 2);
		
		this.exit.setFrames(Assets.backButton);
		
	}

	public void setBackground(BufferedImage bg){
		this.successBackground = bg;
	}
	
	
	@Override
	public void tick() {
		
		this.exit.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(this.successBackground
				,Constants.BACKGROUND_X,Constants.BACKGROUND_Y,null);
	
		this.exit.render(g);
	}

	@Override
	public void onMouseHover(MouseEvent args) {
		
		if(this.exit.isInside(args.getX(), args.getY())){
			this.exit.onMenuButtonHover();
		}else{
			this.exit.setHover(false);
		}
		
	}

	@Override
	public void onMouseRelease(MouseEvent args) {
		
		if(this.exit.isInside(args.getX(), args.getY())){
			StateManager.setCurrentState(new MenuState());
		}else{
			this.exit.setPressed(false);
		}
		
	}

	@Override
	public void onMouseClick(MouseEvent args) {

		if(this.exit.isInside(args.getX(), args.getY())){
			this.exit.onMenuButtonClick();
		}else{
			this.exit.setPressed(false);
			this.exit.setHover(false);
		}
		
	}

}