package states.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import components.interfaces.IButton;
import constants.Constants;
import gfx.Assets;

public class LevelCompletionInterface implements Interface{

	private BufferedImage successBackground;
	private IButton exit;
	
	public LevelCompletionInterface(){
		this.successBackground = Assets.levelCompleted;
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
	public void onMouseHoverOverInterface(MouseEvent args) {
		this.exit.onMenuButtonHover(args.getX(), args.getY());
		
	}

	@Override
	public void onMouseReleaseOverInterface(MouseEvent args) {
		this.exit.onMenuButtonRelease(args.getX(), args.getY());
	}

	@Override
	public void onMouseClickOverInterface(MouseEvent args) {
		this.exit.onMenuButtonClick(args.getX(), args.getY());
	}

}
