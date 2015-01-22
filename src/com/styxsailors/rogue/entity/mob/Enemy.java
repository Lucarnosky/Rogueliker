package com.styxsailors.rogue.entity.mob;

import java.awt.Graphics2D;

import com.styxsailors.rogue.entity.RogueEntity;
import com.styxsailors.rogue.utils.Global;

public class Enemy extends RogueEntity{

	public Enemy(int x, int y, Global global) {
		super(x, y, global);
		
	}
	
	protected void init(){
		name = "Generic Enemy";
		width = 32;
		height = 32;
		calculateCenterCoords();
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics2D g){
		
	}
	
}
