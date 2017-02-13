package states;

import java.awt.Graphics;

import components.StringButton;
import game.entities.UnitDrawable;

public class InGameMenu implements UnitDrawable{

	private StringButton resume;
	private StringButton pause;
	private StringButton options;
	private StringButton exit;
	
	public InGameMenu(){
		init();
	}

	private void init(){

		resume = new StringButton(350,200,"Resume game");
		resume.setSize(100, 50);
		
		pause = new StringButton(350,250,"Pause game");
		pause.setSize(100, 50);

		options = new StringButton(350,300,"Options");
		options.setSize(100, 50);

		exit = new StringButton(350,350,"Exit level");
		exit.setSize(100, 50);
	}
	
	@Override
	public void tick() {
		
		resume.tick();
		pause.tick();
		options.tick();
		exit.tick();
	}

	@Override
	public void render(Graphics g) {
		
		resume.render(g);
		pause.render(g);
		options.render(g);
		exit.render(g);
		
	}
	
	
	
}
