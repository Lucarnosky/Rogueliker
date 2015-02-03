package com.styxsailors.rogue.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.styxsailors.rogue.utils.Global;

public class RogueEntity {
	
	protected Global global;
	public int x,y,ID;
	protected int width, height;
	protected int cx,cy;
	protected int hsp, vsp;
	protected int maxHsp, maxVsp;
	private String name;
	protected boolean onScreen = false;
	private int tolerance = 2;
	public int HP;
	public boolean remove = false;
	protected BufferedImage texture = null;
	
	public RogueEntity(int x, int y, Global global){
		this.x = x;
		this.y = y;
		this.global=global;
		
	}
	
	public RogueEntity(Global global) {
		this.global=global;
	}

	protected void init(){
		setName("Rogue Entity");
		ID = -1;
		width = 32;
		height = 32;
		calculateCenterCoords();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Rectangle getTopBounds(){
		if(vsp < 0)
			return new Rectangle(x, y + vsp - tolerance, width,5);
		return new Rectangle(x, y - tolerance, width,5);
	}
	
	public Rectangle getBottomBounds(){
		if(vsp > 0)
			return new Rectangle(x, y +height-5 + vsp + tolerance , width,5);
		return new Rectangle(x, y + height - 5 + tolerance, width,5);
	}
	
	public Rectangle getLeftBounds(){
		if(hsp < 0)
			return new Rectangle(x + hsp - tolerance, y , 5,height);
		return new Rectangle(x - tolerance, y, 5,height);
	}
	
	public Rectangle getRightBounds(){
		if(hsp > 0)
			return new Rectangle(x + hsp + tolerance + width - 5, y , 5,height);
		return new Rectangle(x + width - 5 + tolerance, y, 5,height);
	}
	
	protected void updateCollision(){
		
	}
	
	protected boolean mouseOver(){
		if( global.mouse.x > x && global.mouse.x < x + width && global.mouse.y > y && global.mouse.y < y + height)
			return true;
		return false;
	}
	
	public BufferedImage getTexture(){
		return texture;
	}
}
