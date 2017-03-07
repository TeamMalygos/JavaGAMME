package states;

import java.awt.Graphics;

import components.Button;
import gfx.Assets;

public class CreateCharacterState extends State {

	private static int ID = 5;
	
	private Button create;
	
	protected CreateCharacterState() {
		super(ID);

		create = new Button(300,450,"New");
		create.setFrames(Assets.newButton);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
	
	}

}
