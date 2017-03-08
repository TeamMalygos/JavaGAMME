package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.File;

import components.Button;
import components.TextField;
import constants.Constants;
import events.MouseMotionSensitive;
import gfx.Assets;
import utils.ObjectSerializer;

public class CreateCharacterState extends State implements MouseMotionSensitive {

	private static int ID = 5;
	
	private TextField field;
	private Button create;
	private Button exit;
	
	private StringBuilder name;
	private boolean nameExists;
	private boolean isNameShort;
	
	public CreateCharacterState() {
		super(ID);

		this.name = new StringBuilder();
		
		this.create = new Button(Constants.MENU_BUTTON_X
				,Constants.MENU_BUTTON_Y + (Constants.MENU_BUTTON_MARGIN_BOTTOM * 2)
				,Constants.BUTTON_NEW);
		this.create.setFrames(Assets.createButton);
		this.create.linkToState(new LevelsState());
	
		this.exit = new Button(Constants.MENU_BUTTON_X
				,Constants.MENU_BUTTON_Y + (Constants.MENU_BUTTON_MARGIN_BOTTOM * 3)
				,Constants.BUTTON_QUIT);
		this.exit.setFrames(Assets.quitButton);
		
		this.field = new TextField(Constants.TEXT_FIELD_NAME);
		
	}

	@Override
	public void tick() {
		this.create.tick();
		this.exit.tick();
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.background
				, Constants.BACKGROUND_X
				,Constants.BACKGROUND_Y 
				,Constants.WIDTH	, Constants.HEIGHT, null);
		
		g.setColor(Color.WHITE);
		
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
		
		
		g.setFont(new Font(Constants.FONT,Font.PLAIN,Constants.FONT_SIZE));

		this.create.render(g);
		this.field.render(g);
		this.exit.render(g);
		
		g.drawImage(Assets.selector
				,Constants.MENU_BUTTON_X + (Constants.MENU_BUTTON_WIDTH/2) - (Assets.selector.getWidth() / 2)
				,Constants.MENU_BUTTON_Y - Constants.STANDARD_PADDING
				,null);
		
		g.drawString(this.name.toString(), Constants.MENU_BUTTON_X + Constants.STANDARD_PADDING
				, Constants.MENU_BUTTON_Y + Constants.MENU_BUTTON_MARGIN_BOTTOM + Constants.STANDARD_PADDING * 2);
		
    	g.drawString(Constants.CREATORS, 
    			Constants.CREATORS_X,
    			Constants.CREATORS_Y);
		
	}

	@Override
	public void onMouseHover(MouseEvent args) {
		if(this.create.isInside(args.getX(), args.getY())){
			this.create.onMenuButtonHover();
		}else{
			this.create.setHover(false);
		}
		
		if(this.exit.isInside(args.getX(), args.getY())){
			this.exit.onMenuButtonHover();
		}else{
			this.exit.setHover(false);
		}
		
	}

	@Override
	public void onMouseRelease(MouseEvent args) {
		
		this.isNameShort = this.name.length() < Constants.NAME_MIN_LENGTH;
		
		if(this.create.isInside(args.getX(), args.getY())){
			
			try {
				this.nameExists = this.doesNameExist(this.name.toString());
			} catch (Exception e) {
				System.out.println("Something went wrong");
				StateManager.setCurrentState(new MenuState());
			}
			
			if(!this.nameExists && !this.isNameShort){
				ObjectSerializer.getInstance().serializeNewCharacter(this.name.toString());
				this.create.onMenuButtonRelease();
			}
			
		}else {
			this.create.setPressed(false);
		}
		
		if(this.field.isInside(args.getX(),args.getY())){
			this.field.setFocused(true);
		}else {
			this.field.setFocused(false);
		}
		
		if(this.exit.isInside(args.getX(), args.getY())){
			StateManager.setCurrentState(new MenuState());
		}else{
			this.exit.setPressed(false);
		}
		
	}

	@Override
	public void onMouseClick(MouseEvent args) {
		
		if(this.create.isInside(args.getX(), args.getY())){
			this.create.onMenuButtonClick();
		}else{
			this.create.setHover(false);
			this.create.setPressed(false);
		}
		
		if(this.exit.isInside(args.getX(), args.getY())){
			this.exit.onMenuButtonClick();
		}else {
			this.exit.setHover(false);
			this.exit.setPressed(false);
		}
		
	}

	public void writeDown(char keyChar){
			
			if(!this.field.isFocused()){
				return;
			}
		
			if(keyChar == 8 && this.name.length() > 0){
				this.name.deleteCharAt(this.name.length()-1);
				return;
			}
			
			if(this.name.length() >= Constants.NAME_MAX_LENGTH){
				return;
			}
			
			if(keyChar > 90 && keyChar < 97){
				return;
			}
			
			if(keyChar < 32){
				return;
			}
			
			if(keyChar > 32 && keyChar < 65){
				return;
			}
			
			if(keyChar == 65535){
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
		
		File[] players = dir.listFiles();
		
		for(File f : players){
			
			String fileName = f.getName().split("\\.")[0];
			
			if(fileName.equals(name)){
				return true;
			}
			
		}
	
		return false;
		
	}
	
}
