package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.File;

import constants.Constants;
import events.MouseMotionSensitive;
import gfx.Assets;
import states.gui.CreateInterface;
import states.gui.Interface;
import utils.ObjectSerializer;

public class CreateCharacterState extends State implements MouseMotionSensitive {

	private static int ID = 5;
	
	private Interface stateInterface;
	
	private StringBuilder name;
	private boolean nameExists;
	private boolean isNameShort;
	
	public CreateCharacterState() {
		super(ID);

		this.name = new StringBuilder();
		this.stateInterface = new CreateInterface();
	}

	@Override
	public void tick() {
		this.stateInterface.tick();
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.background
				, Constants.BACKGROUND_X
				,Constants.BACKGROUND_Y 
				,Constants.WIDTH	, Constants.HEIGHT, null);
		
		this.stateInterface.render(g);
		g.setColor(Color.WHITE);
		this.checkForllegalName(g);
		
		g.drawString(this.name.toString(), Constants.MENU_BUTTON_X + Constants.STANDARD_PADDING
				, Constants.MENU_BUTTON_Y + Constants.MENU_BUTTON_MARGIN_BOTTOM + Constants.STANDARD_PADDING * 2);
		
    	g.drawString(Constants.CREATORS, 
    			Constants.CREATORS_X,
    			Constants.CREATORS_Y);
		
	}


	@Override
	public void onMouseHover(MouseEvent args) {
		
		this.stateInterface.onMouseHoverOverInterface(args);
	}

	@Override
	public void onMouseRelease(MouseEvent args) {
		
		this.isNameShort = this.name.length() < Constants.NAME_MIN_LENGTH;
		
		if(this.nameExists || this.isNameShort){
			return;
		}
		
		try {
			this.nameExists = this.doesNameExist(this.name.toString());
		} catch (Exception e) {
			System.out.println("Something went wrong");
			StateManager.setCurrentState(new MenuState());
		}
		
		if(((CreateInterface)this.stateInterface).isInsideClickButton(args.getX(),args.getY())){
			ObjectSerializer.getInstance().serializeNewCharacter(this.name.toString());
		}
		
		this.stateInterface.onMouseReleaseOverInterface(args);
		
	}

	@Override
	public void onMouseClick(MouseEvent args) {
		
		this.stateInterface.onMouseClickOverInterface(args);
	}

	public void writeDown(char keyChar){
			
			if(!((CreateInterface)this.stateInterface).isTextFieldFocused()){
				return;
			}
		
			if(keyChar == 8 && this.name.length() > 0){
				this.name.deleteCharAt(this.name.length()-1);
				return;
			}
			
			if(this.name.length() >= Constants.NAME_MAX_LENGTH){
				return;
			}
			
			if((keyChar > 90 && keyChar < 97) || keyChar < 32){
				return;
			}
			
			if((keyChar > 32 && keyChar < 65) || keyChar == 65535){
				return;
			}
			
			this.name.append(keyChar);
			
	}

	private boolean doesNameExist(String name) throws Exception{
		
		File dir = new File(System.getProperty("user.dir") 
				+ Constants.DIRECTORY_PLAYER_SERIALIZATION);
		
		if(!dir.exists()){
			
			if(!dir.mkdir()){
				throw new Exception(Constants.GENERAL_EXCEPTION);
			}
			
		}
		
		return this.playerNameExistsInDirectory(dir);
	}
	

	private boolean playerNameExistsInDirectory(File dir){
		File[] players = dir.listFiles();
		
		for(File f : players){
			
			String fileName = f.getName().split("\\.")[0];
			
			if(fileName.equals(name)){
				return true;
			}
			
		}
		return false;
	}
	
	private void checkForllegalName(Graphics g) {
		if(this.nameExists){
			g.setFont(new Font(Constants.FONT,Font.BOLD,Constants.FONT_SIZE + Constants.STANDARD_MARGIN));
			g.drawString(Constants.PLAYER_NAME_EXISTS_ERROR
					, Constants.PLAYER_NAME_ERROR_X
					, Constants.PLAYER_NAME_ERROR_Y);
		}else if(this.isNameShort){
			g.setFont(new Font(Constants.FONT,Font.BOLD,Constants.FONT_SIZE + Constants.STANDARD_MARGIN));
			g.drawString(Constants.PLAYER_NAME_TOO_SHORT_ERROR
					, Constants.PLAYER_NAME_ERROR_X
					, Constants.PLAYER_NAME_ERROR_Y);
		}
	}
	
}
