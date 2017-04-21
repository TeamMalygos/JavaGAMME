package states.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import components.Button;
import components.CharacterList;
import components.interfaces.Clickable;
import constants.Constants;
import events.MenuButtonClickEvent;
import gfx.Assets;
import states.LevelsState;
import states.MenuState;
import utils.ObjectSerializer;

public class LoadCharacterInterface implements Interface{

	
	private CharacterList characters;
	private Clickable load;
	private Clickable quit;
	
	public LoadCharacterInterface(){
		characters = new CharacterList();
		load();
	}

	@Override
	public void tick() {
		if(((Button)this.quit).getStateId() != 1){
			((Button)this.quit).linkToState(new MenuState());		
		}
		
		this.characters.tick();
		this.quit.tick();
		this.load.tick();
	}
	
	@Override
	public void render(Graphics g) {
		//Background
		g.drawImage(Assets.background
				, Constants.BACKGROUND_X
				,Constants.BACKGROUND_Y 
				,Constants.WIDTH	, Constants.HEIGHT, null);
		//Interface componenets
		this.quit.render(g);
		this.load.render(g);
		this.characters.render(g);
	}
	
	@Override
	public void onMouseHoverOverInterface(MouseEvent args) {
		this.load.onMenuButtonHover(args.getX(),args.getY());
		this.quit.onMenuButtonHover(args.getX(), args.getY());
		this.characters.onMouseHover(args);
	}
	
	@Override
	public void onMouseReleaseOverInterface(MouseEvent args) {
		this.load.onMenuButtonRelease(args.getX(), args.getY());
		this.quit.onMenuButtonRelease(args.getX(), args.getY());
		this.characters.onMouseRelease(args);
	}
	
	@Override
	public void onMouseClickOverInterface(MouseEvent args) {
		this.characters.onMouseClick(args);
		this.load.onMenuButtonClick(args.getX(), args.getY());
		this.quit.onMenuButtonClick(args.getX(), args.getY());
	}
	
	private void load() {
		this.overrideLoadFunctionality();
		
		//Quit button
		Button quit = new Button(Constants.WIDTH - Constants.MENU_BUTTON_WIDTH * 2
				,Constants.HEIGHT - Constants.MENU_BUTTON_HEIGHT - 50);
		quit.setFrames(Assets.quitButton);
		this.quit = quit;
	}

	private void overrideLoadFunctionality() {
		Button load = new Button(Constants.WIDTH / 2 - Constants.MENU_BUTTON_WIDTH - 20
				, Constants.HEIGHT - Constants.MENU_BUTTON_HEIGHT - 50){
			@Override
			public void onMenuButtonRelease(int mouseX,int mouseY){
				
				if(!this.isInside(mouseX, mouseY)){
					this.setPressed(false);
					this.setHover(false);
					return;
				}
				try{
					ObjectSerializer.getInstance()
					.loadGameState(characters.getCharacterOnFocus());
				
					new MenuButtonClickEvent(this,new LevelsState());
				}catch(NullPointerException ex){
					System.out.println("Null pointer exception / Check your onMenuRelease() method");
				}
				
			}
			
		};
		load.setFrames(Assets.loadButton);
		this.load = load;
	}
	
}
