package states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import components.Button;
import components.CharacterList;
import constants.Constants;
import events.MouseMotionSensitive;
import gfx.Assets;
import utils.ObjectSerializer;


/**
 * 
 * @author G_ANGELOV
 *
 * <p>State which presents to the user all the existing created 
 * characters by traversing the directory and populating
 * the improvised ListView </p>
 *
 */
public class LoadCharacterState extends State implements MouseMotionSensitive{

	private static int ID = 4;
	private Button load;
	private Button quit;
	
	private CharacterList characters;
	
	public LoadCharacterState() {
		super(ID);
		
		//Load button
		load = new Button(Constants.WIDTH / 2 - Constants.MENU_BUTTON_WIDTH - 20
				, Constants.HEIGHT - Constants.MENU_BUTTON_HEIGHT - 50
				, Constants.BUTTON_LOAD);
		load.setFrames(Assets.loadButton);
		load.linkToState(new LevelsState());
		
		//Quit button
		quit = new Button(Constants.WIDTH - Constants.MENU_BUTTON_WIDTH * 2
				,Constants.HEIGHT - Constants.MENU_BUTTON_HEIGHT - 50
				,Constants.BUTTON_QUIT);
		quit.setFrames(Assets.quitButton);
		
		characters = new CharacterList();
		
	}

	@Override
	public void tick() {
		
		if(this.quit.getStateId() != 1){
			this.quit.linkToState(new MenuState());		
		}
		
		this.quit.tick();
		this.load.tick();
		
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.background
				, Constants.BACKGROUND_X
				,Constants.BACKGROUND_Y 
				,Constants.WIDTH	, Constants.HEIGHT, null);
		
		this.characters.render(g);
		this.quit.render(g);
		this.load.render(g);
	}

	@Override
	public void onMouseHover(MouseEvent args) {
		
		if(this.quit.isInside(args.getX(), args.getY())){
			this.quit.onMenuButtonHover();
		}else {
			this.quit.setHover(false);
		}
		
		if(this.load.isInside(args.getX(),args.getY())){
			this.load.onMenuButtonHover();
		}else{
			this.load.setHover(false);
		}
		
	}

	@Override
	public void onMouseRelease(MouseEvent args) {
		if(this.quit.isInside(args.getX(),args.getY())){
			this.quit.onMenuButtonRelease();
		}else {
			this.quit.setHover(false);
			this.quit.setPressed(false);
		}
		
		if(this.load.isInside(args.getX(),args.getY())){
			
			try{
				
				ObjectSerializer.getInstance()
					.loadGameState(this.characters.getCharacterOnFocus());
				this.load.onMenuButtonRelease();
				
			}catch(NullPointerException ex){
				//System.out.println("Null pointer exception / Check your onMenuRelease() method");
			}
		}else {
			this.load.setHover(false);
			this.load.setPressed(false);
		}
		
	}

	@Override
	public void onMouseClick(MouseEvent args) {
		this.characters.onMouseClick(args);
		
		if(this.quit.isInside(args.getX(), args.getY())){
			this.quit.onMenuButtonClick();
		}else {
			this.quit.setPressed(false);
		}
		
		if(this.load.isInside(args.getX(),args.getY())){
			this.load.onMenuButtonClick();
		}else{
			this.load.setPressed(false);
		}
	}

	
	
}
