package com.styxsailors.rogue.entity.environment;

import java.awt.Graphics2D;

import com.styxsailors.rogue.utils.Global;

public class Floor extends Environment{

	public Floor(int x, int y, Global global) {
		super(x, y, global);
		init();
	}
	
	public Floor(Global global){
		super(global);
		init();
	}
	
	protected void init(){
		setName("floor");
		texture = global.tex.grabImage("floor");
		canPass = true;
		ID = 2;
		minimapColor = global.fromRGBToFloat(76, 255, 0, 0.7f);
		calculateDefault();
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics2D g){
		g.drawImage(texture, x, y, null);
	}

}
