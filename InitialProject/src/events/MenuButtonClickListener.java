package events;

import game.entities.Drawable;

/**
 * 
 * @author G_ANGELOV
 *
 * <p><em>MenuButtonClickListener</em> is an interface which provides
 * the menu buttons with basic onClick() functionality</p>
 *
 */
public interface MenuButtonClickListener extends Drawable{

	void onMenuButtonClick(int mouseX,int mouseY);
	void onMenuButtonRelease(int mouseX,int mouseY);
	void onMenuButtonHover(int mouseX, int mouseY);
	
}
