package states;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import components.Button;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;


public class Menu{
	
	private Clip player;
	private AudioInputStream audioStream;
	
	private Button start;
	private Button load;
	private Button options;
	private Button exit;
	
	public void init(){
		
		start = new Button(300,250,"Start");
		start.setColor(Color.ORANGE);
		start.setSize(200,25);
		start.linkToState(new GameState());
		
		load = new Button(300,282,"Load");
		load.setColor(Color.ORANGE);
		load.setSize(200, 25);
		
		exit = new Button(300,314,"Exit");
		exit.setColor(Color.ORANGE);
		exit.setSize(200, 25);
		
		initMusic();
		
	}
	
	public void initMusic(){

		try{
			player = AudioSystem.getClip();
			audioStream = AudioSystem.getAudioInputStream(Menu.class.getResourceAsStream("/menu.wav"));
			player.open(audioStream);
		}catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){
			ex.printStackTrace();
		}
		
		player.start();
		
	}
	
	
	public void onMenuItemClick(MouseEvent args){
		
		if(isInside(start,args.getX(),args.getY())){
			start.onMenuButtonClick();
		}
		
		if(isInside(exit,args.getX(),args.getY())){
			System.exit(1);
		}
		
	}
	
	private boolean isInside(Button button, int mouseX,int mouseY){
		
		Rectangle rect = button.getArea();
		if(mouseX >= rect.getMinX() && mouseY >= rect.getMinY()
				&& mouseX <= rect.getMaxX() && mouseY <= rect.getMaxY()){
			player.stop();
			return true;
		}
		
		return false;
	}
	
	public void tick(){

	}
	
	public void render(Graphics g){
		start.render(g);
		load.render(g);
		exit.render(g);
	}
	
	
}