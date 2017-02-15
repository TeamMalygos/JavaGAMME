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
import gfx.Assets;
import utils.Level;

public class Menu{
	
	private Clip player;
	private AudioInputStream audioStream;
	
	private Button start;
	private Button load;
	private Button options;
	private Button exit;
	
	public void init(){
		
		start = new Button(300,250,"Start");
		start.setFrames(Assets.playButton);
		start.linkToState(new GameState(Level.Level1));
		load = new Button(300,282,"Load");
	
		exit = new Button(300,314,"Exit");
		exit.setFrames(Assets.quitButton);
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
		}else {
			start.setPressed(false);
		}
		
		if(isInside(exit,args.getX(),args.getY())){
			exit.onMenuButtonClick();
		}else {
			exit.setPressed(false);
		}
	}
	
	public void onMenuItemHover(MouseEvent args){
		
		if(isInside(start,args.getX(),args.getY())){
			start.onMenuButtonHover();
		}else {
			start.setHover(false);
		}
		if(isInside(exit,args.getX(),args.getY())){
			exit.onMenuButtonHover();
		}else {
			exit.setHover(false);
		}
		
	}
	
	public void onMenuItemRelease(MouseEvent args){
		
		if(isInside(start,args.getX(),args.getY())){
			start.onMenuButtonRelease();
			player.stop();
		}else {
			start.setHover(false);
			start.setPressed(false);
		}
		if(isInside(exit,args.getX(),args.getY())){
			System.exit(0);
		}else {
			exit.setHover(false);
			exit.setPressed(false);
			
		}
		
	}
	
	private boolean isInside(Button button, int mouseX,int mouseY){
		
		Rectangle rect = button.getArea();
		if(mouseX >= rect.getMinX() && mouseY >= rect.getMinY()
				&& mouseX <= rect.getMaxX() && mouseY <= rect.getMaxY()){
			return true;
		}
		
		return false;
	}
	
	public void tick(){
		start.tick();
		exit.tick();
	}
	
	public void render(Graphics g){
		start.render(g);
		load.render(g);
		exit.render(g);
	}
	
}