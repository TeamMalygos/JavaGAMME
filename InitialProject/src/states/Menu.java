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
import display.Display;
import gfx.Assets;
import utils.Level;

public class Menu{
	
	private Clip player;
	private AudioInputStream audioStream;
	
	private Button create;
	private Button start;
	private Button load;
	private Button options;
	private Button exit;
	
	private Button[] buttonBundle;
	
	public void init(){
		
		loadButtons();
		initMusic();		
		
	}
	
	
	public void onMenuItemClick(MouseEvent args){
		
		for(Button b : this.buttonBundle){
			
			if(b.isInside(args.getX(),args.getY())){
				b.onMenuButtonClick();
			}else{
				b.setPressed(false);
			}
			
		}
		
	}
	
	public void onMenuItemHover(MouseEvent args){
		
		for(Button b : this.buttonBundle){
			
			if(b.isInside(args.getX(),args.getY())){
				b.onMenuButtonHover();
			}else{
				b.setHover(false);
			}
			
		}
		
	}
	
	public void onMenuItemRelease(MouseEvent args){
		
		for(Button b : this.buttonBundle){
			
			if(b.isInside(args.getX(),args.getY())){
				
				if(b.getName().equals("Exit")){
					System.exit(0);
				}
				b.onMenuButtonRelease();
				player.stop();
				
			}else {
				b.setHover(false);
				b.setPressed(false);
			}
			
		}
		
	}
	
	public void tick(){
		for(Button b : this.buttonBundle){
			b.tick();
		}
	}
	
	public void render(Graphics g){
		for(Button b : this.buttonBundle){
			b.render(g);
		}
	}
	
	
	private void loadButtons(){
		
		start = new Button(300,250,"Start");
		start.setFrames(Assets.playButton);
		start.setReflectionProperties(0.3f, 0.4f, 10);
		start.linkToState(new GameState(Level.Level1));
		
		create = new Button(300,316,"New");
		create.setFrames(Assets.newButton);
		create.setReflectionProperties(0.3f, 0.4f, 10);
		create.linkToState(new CreateCharacterState());		
		
		load = new Button(300,382,"Load");
		load.setFrames(Assets.loadButton);
		load.setReflectionProperties(0.3f, 0.4f, 10);
		load.linkToState(new LoadCharacterState());
		
		exit = new Button(300,448,"Exit");
		exit.setReflectionProperties(0.3f, 0.4f, 10);
		exit.setFrames(Assets.quitButton);
		
		this.buttonBundle = new Button[]{start,load,exit,create};
		
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