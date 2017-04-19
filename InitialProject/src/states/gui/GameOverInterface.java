package states.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import components.Button;
import components.interfaces.IButton;
import constants.Constants;
import gfx.Assets;
import states.GameState;

public class GameOverInterface implements Interface{

	private BufferedImage gameOver;
	
	private IButton retry;
	private IButton exit;
	
	public GameOverInterface(){
		this.gameOver = Assets.gameover;
		init();
		
	}
	
	private void init() {
		Button retry = new Button(Constants.MENU_BUTTON_X
				,Constants.MENU_BUTTON_Y + Constants.MENU_BUTTON_MARGIN_BOTTOM);
		retry.setFrames(Assets.retryButton);
		retry.linkToState(new GameState(GameState.getCurrentLevel()));
		
		Button exit = new Button(
				Constants.MENU_BUTTON_X
				,Constants.MENU_BUTTON_Y + Constants.MENU_BUTTON_MARGIN_BOTTOM * 2);
		exit.setFrames(Assets.backButton);
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
		
	}

	@Override
	public void onMouseReleaseOverInterface(MouseEvent args) {
		
	}

	@Override
	public void onMouseClickOverInterface(MouseEvent args) {
		
	}

}
