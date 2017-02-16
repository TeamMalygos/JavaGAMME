package states;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

import components.Button;

import javax.sound.sampled.*;


public class Menu{
	
	private Clip player;
	private AudioInputStream audioStream;
	private FloatControl Volume;

	private Button start;
	private Button load;
	private Button options;
	private Button exit;

	private Button mute;

	private float currentVolume = 0.0f;

	//Min and max volume are added as finals here only to be used in the mute button the currentVolume variable will be used in the volume adjustment
	private final float minVolume = -80.0f;
	private final float maxVolume = 0.0f;
	
	public void init(){
		
		start = new Button(300,250,"Start");
		start.setColor(Color.ORANGE);
		start.setSize(200,25);
		start.linkToState(new GameState());
		
		load = new Button(300,282,"Load");
		load.setColor(Color.ORANGE);
		load.setSize(200, 25);

		options = new Button(300, 314, "Options");
		options.setColor(Color.ORANGE);
		options.setSize(200, 25);

		exit = new Button(300,346,"Exit");
		exit.setColor(Color.ORANGE);
		exit.setSize(200, 25);

		//#NOTE: When you click on the mute button there is about a second and half delay between the muting and un-muting. Not sure why this happens though.
		mute = new Button(0, 0, "Mute");
		mute.setColor(Color.ORANGE);
		mute.setSize(80, 25);

		initMusic();
		
	}
	
	public void initMusic(){

		try{
			player = AudioSystem.getClip();
			audioStream = AudioSystem.getAudioInputStream(Menu.class.getResourceAsStream("/menu.wav"));
			player.open(audioStream);
			Volume = (FloatControl) player.getControl(FloatControl.Type.MASTER_GAIN);
			Volume.setValue(currentVolume);
			player.start();
		}catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){
			ex.printStackTrace();
		}
		
		player.start();
		
	}
	
	public void onMenuItemClick(MouseEvent args){
		
		if(isInside(start,args.getX(),args.getY())){
			start.onMenuButtonClick();
		}

		if(isInside(options, args.getX(), args.getY())){
			options.onMenuButtonClick();
		}

		if(isInside(mute, args.getX(), args.getY())){
			toggleVolume();
		}

		if(isInside(exit,args.getX(),args.getY())){
			System.exit(1);
		}
		
	}
	
	private boolean isInside(Button button, int mouseX,int mouseY){
		
		Rectangle rect = button.getArea();
		if(mouseX >= rect.getMinX() && mouseY >= rect.getMinY()
				&& mouseX <= rect.getMaxX() && mouseY <= rect.getMaxY()){

			/*
			* 	player.stop(); was turned to a comment because it made problems when the user clicked on the mute button.
		    * While player.stop(); was active when the user clicked on the mute button it only muted the music and could not un-mute it.
		    * Also no matter what the arguments where in the mute button it still muted the music and couldn't get it back on unless the music was re-initialized.
			* */
			//player.stop();
			return true;
		}
		
		return false;
	}
	
	public void tick(){

	}
	
	public void render(Graphics g){
		start.render(g);
		load.render(g);
		options.render(g);
		exit.render(g);
		mute.render(g);
	}

	public void toggleVolume(){
		if(getCurrentVolume() == 0.0f){
			setCurrentVolume(minVolume);
			Volume.setValue(getCurrentVolume());
		} else {
			setCurrentVolume(maxVolume);
			Volume.setValue(getCurrentVolume());
		}
	}

	public float getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(float currentVolume) {
		this.currentVolume = currentVolume;
	}
}