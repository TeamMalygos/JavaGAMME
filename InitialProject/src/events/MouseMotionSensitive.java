package events;

import java.awt.event.MouseEvent;

public interface MouseMotionSensitive {

	public void onMouseHover(MouseEvent args);
	public void onMouseRelease(MouseEvent args);
	public void onMouseClick(MouseEvent args);
	
	
}
