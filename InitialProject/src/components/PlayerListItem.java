package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import constants.Constants;
import game.entities.playerProperties.Stats;

/**
 * 
 * @author G_ANGELOV
 *
 * <p>Inspired by FX and Android Studio ways PlayerListItem is 
 * custom made list item for our improvised ListView and
 * it includes just basic Rectangles with some state 
 * properties. </p>
 */

public class PlayerListItem extends MenuComponent{

	private int offset;
	private boolean isOnFocus;
	private Stats stat;
	
	public PlayerListItem(int offset,Stats s){

		super(Constants.PLAYERS_LIST_X,Constants.PLAYERS_LIST_Y + offset);
		super.setSize(Constants.PLAYERS_LIST_LISTITEM_WIDTH, Constants.PLAYERS_LIST_LISTITEM_HEIGHT);
		
		this.setOffset(offset);
		this.setStat(s);
	}

	private void setOffset(int offset){
		
		if(offset < 0){
			offset = 0;
			System.out.println(Constants.NEGATIVE_OFFSET_ERROR);
		}
		
		this.offset = offset;
	}
	
	public void setFocus(boolean focus){
		this.isOnFocus = focus;
	}
	
	public boolean isOnFocus(){
		return this.isOnFocus;
	}
	
	private void setStat(Stats s){
		
		if(s == null){
			return;
		}
		
		this.stat = s;
	}
	
	public Stats getStats(){
		return this.stat;
	}
	
	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.WHITE);
		if(this.isOnFocus){
			g.setColor(Color.BLACK);
		}
		g.setFont(new Font(Constants.FONT,Font.PLAIN,Constants.FONT_SIZE));
		
		String name = stat.getPlayerName();
		String level = "Level: " + stat.getLevel();
		
		int padding = Constants.STANDARD_PADDING;
		
		g.drawString(name, super.getxAxisPosition() + padding, super.getyAxisPosition() + (padding * 2));
		g.drawString(level, super.getxAxisPosition() + padding, super.getyAxisPosition() + (padding * 3));
		
		g.drawRect(super.getxAxisPosition(), super.getyAxisPosition(), super.getWidth(), super.getHeight());
		
	}

	@Override
	public void tick() {
		
	}
	
}
