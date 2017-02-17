package states;

import java.awt.Graphics;

import components.HealthBar;
import constants.Constants;
import game.entities.Player;
import game.entities.UnitDrawable;

public class InGameHUD implements UnitDrawable{

	private HealthBar bar;
	private Player p;

	public InGameHUD(Player p){
		this.p = p;
		init();
	}
	
	private void init(){
		
		this.bar = new HealthBar(Constants.HEALTH_BAR_X
				,Constants.HEALTH_BAR_Y,"HealthBar");
		this.bar.linkPlayer(p);
		
	}
	
	
	@Override
	public void tick() {
		this.bar.tick();
		
	}

	@Override
	public void render(Graphics g) {
		this.bar.render(g);
		
	}

}
