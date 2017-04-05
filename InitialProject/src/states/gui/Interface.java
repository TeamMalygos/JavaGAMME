package states.gui;

import java.awt.event.MouseEvent;

import game.entities.Drawable;

public interface Interface extends Drawable{

	void onMouseHoverOverInterface(MouseEvent args);
	void onMouseReleaseOverInterface(MouseEvent args);
	void onMouseClickOverInterface(MouseEvent args);
	
}
