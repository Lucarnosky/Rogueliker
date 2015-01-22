package com.styxsailors.rogue.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.styxsailors.rogue.utils.Bounds;
import com.styxsailors.rogue.utils.Global;

public class RogueEntity {
	
	protected Global global;
	protected int x,y,ID;
	protected int width, height;
	protected int cx,cy;
	protected int hsp, vsp;
	protected int maxHsp, maxVsp;
	protected String name;
	protected Bounds bound;
	protected boolean onScreen = false;
	
	public RogueEntity(int x, int y, Global global){
		this.x = x;
		this.y = y;
		this.global=global;
		
	}
	
	protected void init(){
		name = "Rogue Entity";
		ID = -1;
		width = 32;
		height = 32;
		calculateCenterCoords();
		bound.calculateBounds();
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics2D g){
		
	}
	
	protected void calculateCenterCoords(){
		cx = x + width/2;
		cy = y + height/2;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,width,height);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setOnScreen(boolean onScreen){
		this.onScreen = onScreen;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight(){
		return height;
	}
}
