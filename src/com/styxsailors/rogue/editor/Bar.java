package com.styxsailors.rogue.editor;

import java.awt.Color;
import java.awt.Graphics2D;
import com.styxsailors.rogue.utils.Global;

public class Bar{

	Global global;
	int x,y;
	
	public Bar(Global global) {
		this.global = global;
		init();
	}

	protected void init(){
		x = -global.camX;
		y = -global.camY;
	}
	
	public void tick(){
		x = -global.camX;
		y = -global.camY;
	}
	
	public void render(Graphics2D g){
		g.setColor(Color.gray);
		g.fillRect(x, y, global.W_WIDTH * global.W_SCALE + 20, 32);
	}
}
