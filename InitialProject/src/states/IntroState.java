package states;

import java.awt.Graphics;

import components.ParallaxBackground;
import constants.Constants;
import gfx.Assets;
import gfx.SpriteSheet;
import utils.Animation;

public class IntroState extends State{

	private ParallaxBackground background;
	
	private SpriteSheet creators;
	
	private Animation creatorsAnimationFadeIn;
	
	private final int[] creatorsFramesCount = new int[]{
		22
	};
	
	private final static int ID = 0;
	
	public IntroState() {
		super(ID);
		
		Assets.init();
		
		this.background = new ParallaxBackground();
		
		this.creators = new SpriteSheet(Assets.creators);
		this.creators.setFrameLayersCount(this.creatorsFramesCount
				, Constants.INTRO_CREATORS_WIDTH
				, Constants.INTRO_CREATORS_HEIGHT);
		
		this.creatorsAnimationFadeIn = new Animation();
		this.creatorsAnimationFadeIn.setDelay(Constants.CREATORS_INTRO_DELAY);
		this.creatorsAnimationFadeIn.setFrames(this.creators.getFrameSet(0));
		
	}

	@Override
	public void tick() {
	
		if(!this.creatorsAnimationFadeIn.hasPlayedOnce()){
			this.creatorsAnimationFadeIn.update();
		}
		
	}

	@Override
	public void render(Graphics g) {
		
		this.background.render(g);
		
		if(!this.creatorsAnimationFadeIn.hasPlayedOnce()){
			g.drawImage(this.creatorsAnimationFadeIn.getImage()
					,Constants.WIDTH / 2 - Constants.INTRO_CREATORS_WIDTH /2 
					,Constants.HEIGHT /  2 - Constants.INTRO_CREATORS_HEIGHT /2 
					,null);
		}else {	
			StateManager.setCurrentState(new MenuState());
		}
		
	}

}
