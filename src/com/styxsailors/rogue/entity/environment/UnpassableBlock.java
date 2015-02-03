package com.styxsailors.rogue.entity.environment;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.styxsailors.rogue.utils.Global;

public class UnpassableBlock extends Environment{

	public UnpassableBlock(int x, int y, Global global) {
		super(x, y, global);
		init();
	}
	
	public UnpassableBlock( Global global) {
		super(global);
		init();
	}

	protected void init(){
		setName("unpassable block");
		texture = global.tex.grabImage("unpassableblock");
		width = texture.getWidth();
		height = texture.getHeight();
		canPass = false;
		ID = 1;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics2D g){
		g.drawImage(texture, x, y,null);
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,width,height);
	}

}
