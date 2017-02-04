package states;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import components.Button;

import javax.swing.*;

public class Menu {
 
 private Button start;
 private Button exit;
 
 public void init(){
  
  start = new Button(500,400,"StartButton");
  start.setColor(Color.BLACK);
  start.setSize(200,50);
  exit = new Button(75,75,"ExitButton");
  exit.setColor(Color.YELLOW);
  exit.setSize(100, 50);
  
 }
 
 
 public void tick(){

 }
 
 public void render(Graphics g){
  start.render(g);
  exit.render(g);
 }
 
 
}