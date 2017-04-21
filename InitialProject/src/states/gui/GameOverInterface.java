package states.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import components.Button;
import components.interfaces.Clickable;
import constants.Constants;
import events.MenuButtonClickEvent;
import gfx.Assets;
import states.GameState;
import states.MenuState;

public class GameOverInterface implements Interface{

	private BufferedImage gameOver;
	
	private Clickable retry;
	private Clickable exit;
	
	public GameOverInterface(){
		this.gameOver = Assets.gameover;
		init();
		
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
	public void onMouseHoverOverInterface(MouseEvent args) {
		this.exit.onMenuButtonHover(args.getX(), args.getY());
		this.retry.onMenuButtonHover(args.getX(), args.getY());
	}

	@Override
	public void onMouseReleaseOverInterface(MouseEvent args) {
		this.exit.onMenuButtonRelease(args.getX(),args.getY());
		this.retry.onMenuButtonRelease(args.getX(), args.getY());
	}

	@Override
	public void onMouseClickOverInterface(MouseEvent args) {
		this.exit.onMenuButtonClick(args.getX(),args.getY());
		this.retry.onMenuButtonClick(args.getX(), args.getY());
	}

	private void init() {
		Button retry = new Button(Constants.MENU_BUTTON_X
				,Constants.MENU_BUTTON_Y + Constants.MENU_BUTTON_MARGIN_BOTTOM);
		retry.setFrames(Assets.retryButton);
		retry.linkToState(new GameState(GameState.getCurrentLevel()));
		
		Button exit = new Button(
				Constants.MENU_BUTTON_X
				,Constants.MENU_BUTTON_Y + Constants.MENU_BUTTON_MARGIN_BOTTOM * 2){
			@Override
			public void onMenuButtonRelease(int x,int y){
				if(!this.isInside(x, y)){
					this.setPressed(false);
					this.setHover(false);
					return;
				}
				
				new MenuButtonClickEvent(this,new MenuState());
			}
		};
		exit.setFrames(Assets.backButton);
		this.retry = retry;
		this.exit = exit;
	}

	
}
