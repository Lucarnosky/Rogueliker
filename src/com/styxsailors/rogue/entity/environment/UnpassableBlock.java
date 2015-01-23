package com.styxsailors.rogue.entity.environment;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.styxsailors.rogue.utils.Global;

public class UnpassableBlock extends Environment{

	public UnpassableBlock(int x, int y, Global global) {
		super(x, y, global);
		init();
	}
	
	protected void init(){
		setName("unpassable block");
		width = 32;
		height = 32;
		canPass = false;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics2D g){
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,width,height);
	}

}
