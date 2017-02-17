package states;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import components.StringButton;
import game.entities.Player;
import game.entities.Stats;
import game.entities.UnitDrawable;

public class InGameMenu implements UnitDrawable{

	private StringButton resume;
	private StringButton pause;
	private StringButton options;
	private StringButton exit;
	
	public InGameMenu(){
		init();
	}

	private void init(){

		resume = new StringButton(350,200,"Resume game");
		resume.setSize(100, 50);
		
		pause = new StringButton(350,250,"Pause game");
		pause.setSize(100, 50);

		options = new StringButton(350,300,"Options");
		options.setSize(100, 50);

		exit = new StringButton(350,350,"Exit level");
		exit.setSize(100, 50);
		exit.linkToState(new MenuState());
	}
	
	
public void onMenuItemRelease(MouseEvent args){
		
		if(isInside(exit,args.getX(),args.getY())){
			saveCurrentPlayer();
			exit.onStringMenuItemRelease();
		}
		
	}

	private void saveCurrentPlayer() {
		Player currentPlayer = GameState.getPlayer();
		Stats st = currentPlayer.getPlayerStats();
		String playerFilePath = System.getProperty("user.dir") + "/resources/players/" + currentPlayer.getName() + ".ser";
		try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(playerFilePath));
            oos.writeObject(st);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private boolean isInside(StringButton button, int mouseX,int mouseY){
		
		Rectangle rect = new Rectangle(button.getPositionX(),button.getPositionY(),100,50);
		if(mouseX >= rect.getMinX() && mouseY >= rect.getMinY()
				&& mouseX <= rect.getMaxX() && mouseY <= rect.getMaxY()){
			return true;
		}
		
		return false;
	}
	
	
	@Override
	public void tick() {
		
		resume.tick();
		pause.tick();
		options.tick();
		exit.tick();
	}

	@Override
	public void render(Graphics g) {
		
		resume.render(g);
		pause.render(g);
		options.render(g);
		exit.render(g);
		
	}
	
	
	
}