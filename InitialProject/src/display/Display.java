package display;


import javax.swing.*;

import constants.Constants;
import events.MenuMouseClickEventListener;
import events.MenuMouseMotionListener;

import java.awt.*;
import java.awt.event.MouseListener;

public class Display {

    private String name;
    private JFrame frame;
    private Canvas canvas;
    private GlassPane pane;

    public Display(String name) {
        this.name = name;
        this.pane = new GlassPane();
        init(name);
    }

    public Canvas getCanvas() {
        return canvas;
    }
    
    public JFrame getFrame(){
    	return this.frame;
    }

    private void init(String name) {
        this.frame = new JFrame(name);
        this.frame.setSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        this.frame.setFocusable(true);

        this.frame.setGlassPane(pane);
        this.frame.getGlassPane().setVisible(true);
        
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);

        this.canvas = new Canvas();
        this.canvas.setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        this.canvas.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        this.canvas.setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        this.canvas.setVisible(true);
        
        this.canvas.addMouseListener(new MenuMouseClickEventListener());
        this.canvas.addMouseMotionListener(new MenuMouseMotionListener());
        
        this.frame.add(canvas);
        this.frame.pack();
    }
}
