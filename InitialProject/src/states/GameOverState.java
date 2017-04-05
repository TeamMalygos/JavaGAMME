package states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import components.Button;
import constants.Constants;
import enums.Level;
import events.MouseMotionSensitive;
import gfx.Assets;

public class GameOverState extends State implements MouseMotionSensitive{

	private final static int ID =  8;
	
	private Level currentLevel;
	
	private BufferedImage gameOver;
	
	private Button retry;
	private Button exit;
	
	public GameOverState(Level l){
		super(ID);
		this.setBackground(Assets.gameover);
		this.setLevel(l);
		
		this.retry = new Button(Constants.MENU_BUTTON_X
				,Constants.MENU_BUTTON_Y + Constants.MENU_BUTTON_MARGIN_BOTTOM);
		this.retry.setFrames(Assets.retryButton);
		this.retry.linkToState(new GameState(this.currentLevel));
		
		this.exit = new Button(
				Constants.MENU_BUTTON_X
				,Constants.MENU_BUTTON_Y + Constants.MENU_BUTTON_MARGIN_BOTTOM * 2);
		this.exit.setFrames(Assets.backButton);
		
		
	}
	
	public void setBackground(BufferedImage gameOver){
		this.gameOver = gameOver;
	}
	
	public void setLevel(Level l){
		this.currentLevel = l;
	}
	
	@Override
	public void tick() {
		this.exit.tick();
		this.retry.tick();
		
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(this.gameOver, Constants.BACKGROUND_X
				, Constants.BACKGROUND_Y
				,Constants.WIDTH,Constants.HEIGHT, null);
		
		this.exit.render(g);
		this.retry.render(g);
	}

	@Override
	public void onMouseHover(MouseEvent args) {
		
		if(this.retry.isInside(args.getX(), args.getY())){
			this.retry.onMenuButtonHover();
		}else{
			this.retry.setHover(false);
		}
		
		if(this.exit.isInside(args.getX(), args.getY())){
			this.exit.onMenuButtonHover();
		}else{
			this.exit.setHover(false);
		}
		
	}

	@Override
	public void onMouseRelease(MouseEvent args) {
		if(this.retry.isInside(args.getX(), args.getY())){
			this.retry.onMenuButtonRelease();
		}else{
			this.retry.setPressed(false);
		}
		
		if(this.exit.isInside(args.getX(), args.getY())){
			StateManager.setCurrentState(new MenuState());
		}else{
			this.exit.setPressed(false);
		}
		
	}

	@Override
	public void onMouseClick(MouseEvent args) {
		
		if(this.retry.isInside(args.getX(), args.getY())){
			this.retry.onMenuButtonClick();
		}else{
			this.retry.setPressed(false);
			this.retry.setHover(false);
		}
		
		
		if(this.exit.isInside(args.getX(), args.getY())){
			this.exit.onMenuButtonClick();
		}else{
			this.exit.setPressed(false);
			this.exit.setHover(false);
		}
	}
	
	
}
