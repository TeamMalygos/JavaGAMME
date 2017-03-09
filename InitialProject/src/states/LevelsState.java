package states;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.File;

import components.Button;
import components.LevelCard;
import constants.Constants;
import enums.Level;
import events.MouseMotionSensitive;
import gfx.Assets;
import utils.UserAccount;

public class LevelsState extends State implements MouseMotionSensitive{
	
	private static final int ID = 3;
	private LevelCard[] levels;
	
	private Button pointerLeft;
	private Button pointerRight;
	private Button load;
	
	private int indexOnFocus;
	
	protected LevelsState() {
		super(ID);
		indexOnFocus = 0;
		init();
		
	}

	private void init(){
		
		String levelsPath = System.getProperty("user.dir") + "/maps";
		File mapsDir = new File(levelsPath);
		
		File[] levels = mapsDir.listFiles();
		this.levels = new LevelCard[levels.length];

		for(int i = 0 ; i < levels.length ; i++){
			this.levels[i] = new LevelCard(Constants.WIDTH / 2
					, Constants.HEIGHT / 2
					,levels[i]);
			
		}
		
		loadButtons();
		
	}
	
	@Override
	public void tick() {
		
		//Animations tick
		this.pointerLeft.tick();
		this.pointerRight.tick();
		this.load.tick();
		
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.background
				, Constants.BACKGROUND_X
				,Constants.BACKGROUND_Y 
				,Constants.WIDTH
				, Constants.HEIGHT, null);
		
		this.pointerLeft.render(g);
		this.pointerRight.render(g);
		this.load.render(g);
		
		this.levels[this.indexOnFocus].render(g);
		
	}
	
	private void loadButtons(){
		
		this.pointerLeft = new Button(Constants.WIDTH / 4 - Constants.POINTER_WIDTH
				,Constants.HEIGHT / 2 - Constants.POINTER_HEIGHT
				, Constants.POINTER_LEFT);
		
		this.pointerLeft.setSize(Constants.POINTER_WIDTH, Constants.POINTER_HEIGHT);
		this.pointerLeft.setFrames(Assets.pointerLeft);
		
		this.pointerRight = new Button(
				Constants.WIDTH/2 + Constants.POINTER_WIDTH * 2
				, Constants.HEIGHT/2 - Constants.POINTER_HEIGHT * 2
				,Constants.POINTER_RIGHT);
		
		this.pointerRight.setSize(Constants.POINTER_WIDTH, Constants.POINTER_HEIGHT);
		this.pointerRight.setFrames(Assets.pointerRight);
	
		this.load = new Button(Constants.WIDTH / 2, Constants.HEIGHT 
				- (Constants.HEIGHT / 3)
				,Constants.BUTTON_LOAD);
		
		this.load.setFrames(Assets.loadButton);
		
	}
	
	@Override
	public void onMouseHover(MouseEvent args) {
		
		if(this.pointerLeft.isInside(args.getX(), args.getY())){
			this.pointerLeft.onMenuButtonHover();
		}else{
			this.pointerLeft.setHover(false);
		}
		
		if(this.pointerRight.isInside(args.getX(), args.getY())){
			this.pointerRight.onMenuButtonHover();
		}else{
			this.pointerRight.setHover(false);
		}
		
		if(this.load.isInside(args.getX(), args.getY())){
			this.load.onMenuButtonHover();
		}else{
			this.load.setHover(false);
		}
		
	}

	@Override
	public void onMouseRelease(MouseEvent args) {
		
		if(this.pointerLeft.isInside(args.getX(), args.getY())){
			focusLeft();
		}else{
			this.pointerLeft.setPressed(false);
		}
		
		if(this.pointerRight.isInside(args.getX(), args.getY())){
			focusRight();
		}else{
			this.pointerRight.setPressed(false);
		}
		
		if(this.load.isInside(args.getX(), args.getY())){
			Level l = getLevelOnFocus();
			StateManager.setCurrentState(new GameState(l));
		}else{
			this.load.setPressed(false);
		}
		
	}


	@Override
	public void onMouseClick(MouseEvent args) {
		if(this.pointerLeft.isInside(args.getX(), args.getY())){
			this.pointerLeft.onMenuButtonClick();
		}else{
			this.pointerLeft.setHover(false);
			this.pointerLeft.setPressed(false);
		}
		
		if(this.pointerRight.isInside(args.getX(), args.getY())){
			this.pointerRight.onMenuButtonClick();
		}else{
			this.pointerRight.setHover(false);
			this.pointerRight.setPressed(false);
		}
		
		if(this.load.isInside(args.getX(), args.getY())){
			this.load.onMenuButtonClick();
		}else{
			this.load.setHover(false);
			this.load.setPressed(false);
		}
		
	}
	
	private Level getLevelOnFocus() {
		Level l = Level.Level1;
		try{
			l = Level.valueOf("Level" + (this.indexOnFocus+1));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return l;
	}
	
	private void focusLeft() {
		if(this.indexOnFocus > 0){
			this.indexOnFocus -= 1;
		}
		
	}
	
	private void focusRight() {
		if(this.indexOnFocus < UserAccount.getStats().getProgress()){
			this.indexOnFocus +=1;
		}
		
	}


}
