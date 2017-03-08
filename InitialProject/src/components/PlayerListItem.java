package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import constants.Constants;
import game.entities.playerProperties.Stats;

public class PlayerListItem extends MenuComponent{

	private Rectangle body;
	private int offset;
	private boolean isOnFocus;
	private Stats stat;
	
	public PlayerListItem(int offset,Stats s){
		super(Constants.PLAYERS_LIST_X,Constants.PLAYERS_LIST_Y + offset, s.getPlayerName());
		super.setSize(Constants.PLAYERS_LIST_LISTITEM_WIDTH, Constants.PLAYERS_LIST_LISTITEM_HEIGHT);
		
		this.setStat(s);
		this.body = new Rectangle(super.getxAxisPosition(),super.getyAxisPosition(),super.getWidth(),super.getHeight());
	}

	public void setOffset(int offset){
		
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
	
	public int getOffset(){
		return this.offset;
	}
	
	public void setStat(Stats s){
		
		if(s == null){
			return;
		}
		
		this.stat = s;
	}
	
	@Override
	public void render(Graphics g) {
		
		String name = "Name: " + stat.getPlayerName();
		int padding = Constants.STANDARD_PADDING;
		
		g.setColor(Color.WHITE);
		g.drawString(name, super.getxAxisPosition() + padding, super.getyAxisPosition() + padding);
		
		g.setColor(Color.cyan);
		
		g.drawRect(super.getxAxisPosition(), super.getyAxisPosition(), super.getWidth(), super.getHeight());
		
	}

	@Override
	public void tick() {
		
	}
	
}
