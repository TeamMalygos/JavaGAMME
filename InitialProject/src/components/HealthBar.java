package components;

import java.awt.Graphics;

import constants.Constants;
import enums.HealthState;
import game.entities.Player;
import gfx.Assets;

public class HealthBar extends HUDComponent{
	
	private int initialHealth;
	private HealthState state;
	
	public HealthBar(int x, int y, String name) {
		
		super(x, y, name);
		super.setSize(Constants.HEALTH_BAR_WIDTH, Constants.HEALTH_BAR_HEIGHT);
		
		super.loadSprite(Assets.healthBar, new int[] { 8 });
		
	}

	@Override
	public void render(Graphics g) {

		g.drawImage(this.frames[this.state.ordinal()]
				,this.xAxisPosition,this.yAxisPosition,null);
	}

	@Override
	public void tick() {
		
		double playerCurrentHealth = super.player.getPlayerStats().getCurrentHealth();

		double percentage = (playerCurrentHealth / this.initialHealth) * 100;
		nextFrame(percentage);
		
	}
	
	
	private void nextFrame(double percentage){
		if(percentage >= 90){
			this.state = HealthState.State1;
		}else if(percentage >= 75 && percentage < 90){
			this.state = HealthState.State2;
		}else if(percentage >= 60 && percentage < 75){
			this.state = HealthState.State3;
		}else if(percentage >= 50 && percentage < 60){
			this.state = HealthState.State4;
		}else if(percentage >= 40 && percentage < 50){
			this.state = HealthState.State5;
		}else if(percentage >= 30 && percentage < 40){
			this.state = HealthState.State6;
		}else if(percentage >= 15 && percentage < 30){
			
			this.state = HealthState.State7;
		}else{
			this.state = HealthState.State8;
		}
	}
	

}
