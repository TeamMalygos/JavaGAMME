package states;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StartMenu extends JFrame {
	public void initMenu(){
		JPanel optionsPane = new JPanel(new GridLayout(8,1));
		JPanel pane = null;
		//IP address input
		pane= new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton button = new JButton("Menu");
		button.addActionListener(new ActoinAdopter());
		button.setActionCommand("Menu");
		pane = new JPanel(new GridLayout(1,1));
		pane.add(button);
		optionsPane.add(pane);
		add(optionsPane);
	}

}
class ActoinAdopter implements ActionListener{
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("")){
		}else if(e.getActionCommand().equals("")){
			
		}
	}
	
}
