package states;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import states.gui.Interface;
import states.gui.MenuInterface;

public class Menu{
	
	private Clip player;
	private AudioInputStream audioStream;
	
	private Interface menuInterface;
	
	public Menu(){
		
	}
	
	public void init(){
		
		this.menuInterface = new MenuInterface();
		initMusic();		
		
	}
	
	
	public void onMenuItemClick(MouseEvent args){	
		this.menuInterface.onMouseClickOverInterface(args);
	}
	
	public void onMenuItemHover(MouseEvent args){
		this.menuInterface.onMouseHoverOverInterface(args);
	}
	
	public void onMenuItemRelease(MouseEvent args){
		this.menuInterface.onMouseReleaseOverInterface(args);
	}
	
	public void tick(){
		this.menuInterface.tick();
	}
	
	public void render(Graphics g){
		this.menuInterface.render(g);
	}
	
	private void initMusic(){

		try{
			player = AudioSystem.getClip();
			audioStream = AudioSystem.getAudioInputStream(Menu.class.getResourceAsStream("/menu.wav"));
			player.open(audioStream);
		}catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){
			ex.printStackTrace();
		}
		
		player.start();
		
	}
	
}