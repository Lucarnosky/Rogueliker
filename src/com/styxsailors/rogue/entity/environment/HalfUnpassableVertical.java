package com.styxsailors.rogue.entity.environment;

import java.awt.Graphics2D;

import com.styxsailors.rogue.utils.Global;

public class HalfUnpassableVertical extends UnpassableBlock{

	public HalfUnpassableVertical(int x, int y, Global global){
		super(x,y,global);
		init();
	}
	public HalfUnpassableVertical(Global global) {
		super(global);
		init();
	}
	
	protected void init(){
		super.init();
		texture = global.tex.grabImage("halfunpassable");
		ID = 3;
		calculateDefault();
	}
	
	public void render(Graphics2D g){
		g.drawImage(texture, x, y,null);
	}

}
