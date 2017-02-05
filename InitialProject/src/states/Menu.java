package states;
import java.awt.*;
import java.awt.event.MouseEvent;

import components.Button;


public class Menu{
	
	private Button start;
	private Button exit;
	
	public void init(){
		
		start = new Button(500,400,"StartButton");
		start.setColor(Color.BLACK);
		start.setSize(200,50);
		start.linkToState(new GameState());
		exit = new Button(75,75,"ExitButton");
		exit.setColor(Color.YELLOW);
		exit.setSize(100, 50);
		
	}
	
	
	public void onMenuItemClick(MouseEvent args){
		if(isInside(start,args.getX(),args.getY())){
			start.onMenuButtonClick();
		}
		
		if(isInside(exit,args.getX(),args.getY())){
			exit.onMenuButtonClick();
		}
	}
	
	private boolean isInside(Button button, int mouseX,int mouseY){
		
		Rectangle rect = button.getArea();
		if(mouseX >= rect.getMinX() && mouseY >= rect.getMinY()
				&& mouseX <= rect.getMaxX() && mouseY <= rect.getMaxY()){
			return true;
		}
		
		return false;
	}
	
	public void tick(){

	}
	
	public void render(Graphics g){
		start.render(g);
		exit.render(g);
	}
	
	
}