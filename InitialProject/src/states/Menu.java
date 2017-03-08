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
import constants.Constants;
import display.Display;
import gfx.Assets;
import utils.Level;
import utils.UserAccount;

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
		
		int offset = Constants.MENU_BUTTON_MARGIN_BOTTOM;
		int yposition = Constants.MENU_BUTTON_Y;
		int xposition = Constants.MENU_BUTTON_X;
		
		start = new Button(xposition,yposition,"Start");
		start.setFrames(Assets.playButton);
		start.setReflectionProperties(0.3f, 0.4f, 10);
		
	  	if(UserAccount.playerExists()){
	  		start.linkToState(new GameState(Level.Level1));
    	}
	  	start.linkToState(new LoadCharacterState());

		create = new Button(xposition,yposition + offset,"New");
		create.setFrames(Assets.newButton);
		create.setReflectionProperties(0.3f, 0.4f, 10);
		create.linkToState(new CreateCharacterState());		
		
		load = new Button(xposition,yposition + (offset * 2),"Load");
		load.setFrames(Assets.loadButton);
		load.setReflectionProperties(0.3f, 0.4f, 10);
		load.linkToState(new LoadCharacterState());
		
		exit = new Button(xposition,yposition + (offset * 3),"Exit");
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