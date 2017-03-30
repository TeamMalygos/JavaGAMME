package components.interfaces;

import java.awt.Graphics;

public interface IButton {

	void tick();
	void draw(Graphics g);
	boolean isInside();
	
}
