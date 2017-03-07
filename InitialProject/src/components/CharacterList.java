package components;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import constants.Constants;
import events.MouseMotionSensitive;

public class CharacterList extends MenuComponent implements MouseMotionSensitive{

	private File[] players;
	private File dir;
	private RoundRectangle2D body;
	private RadialGradientPaint paint;
	private PlayerListItem[] items;
	
	public CharacterList(String name) {
		super(Constants.PLAYERS_LIST_X, Constants.PLAYERS_LIST_Y, name);
		
		super.setSize(Constants.PLAYERS_LIST_WIDTH, Constants.PLAYERS_LIST_HEIGHT);
		
		this.body = new RoundRectangle2D.Float((float)super.getxAxisPosition(),(float)super.getyAxisPosition()
				,(float)super.getWidth(),(float)super.getHeight()
				,Constants.BORDER_RADIUS
				,Constants.BORDER_RADIUS);
		
		loadRadialGradients();
		
		traversePlayersDir();
		
	}
	
	private void loadRadialGradients(){
		
		this.paint = new RadialGradientPaint((float)super.xAxisPosition,(float)super.yAxisPosition
				,Constants.RADIAL_RADIUS,Constants.FRACTIONS,Constants.COLORS);
		
	}
	
	private void traversePlayersDir(){
	
		this.dir = new File(System.getProperty("user.dir") + Constants.DIRECTORY_PLAYER_SERIALIZATION);
		
		this.players = dir.listFiles();
		this.items = new PlayerListItem[this.players.length];
		
		deserializePlayers();
		
	}

	private void deserializePlayers(){
		
		ObjectInputStream ois = null;
		
		for(int i = 0 ; i < this.items.length ; i++){
			
			try {
				ois = new ObjectInputStream(new FileInputStream(this.players[i]));
			
				
			} catch (IOException e) {
				this.items[i] = null;
				e.printStackTrace();
			}
			
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setPaint(paint);
		g2d.setStroke(new BasicStroke(10));
		g2d.fill(this.body);
		
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void onMouseHover(MouseEvent args) {
		
		
	}

	@Override
	public void onMouseRelease(MouseEvent args) {
		
		
	}

	@Override
	public void onMouseClick(MouseEvent args) {
		
		
	}

}
