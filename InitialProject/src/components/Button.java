package components;

import java.awt.Color;
import java.awt.Graphics;

public class Button {

 private int screenPositionX;
 private int screenPositionY;
 
 private int width;
 private int height;
 
 private Color color;
 
 private String name;
 
 public Button(int x,int y,String name){
  screenPositionX = x;
  screenPositionY = y;
  this.name = name;
 }
 
 public void setColor(Color color){
  this.color = color;
 }
 
 public void setSize(int w,int h){
  this.width = w;
  this.height = h;
 }
 
 public void tick(){
  //I have no fucking idea what this does but it looks fabulous
 }
 
 public void render(Graphics g){
  
  g.setColor(color);
  g.drawRect(screenPositionX,screenPositionY,width,height);
  
 }
 
}