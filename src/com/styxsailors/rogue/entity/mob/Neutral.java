package com.styxsailors.rogue.entity.mob;

import java.awt.Graphics2D;

import com.styxsailors.rogue.entity.RogueEntity;
import com.styxsailors.rogue.utils.Global;

public class Neutral extends RogueEntity{

	public Neutral(int x, int y, Global global) {
		super(x, y, global);
	}
	
	protected void init(){
		setName("Generic Neutral");
		width = 32;
		height = 32;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics2D g){
		
	}

}
