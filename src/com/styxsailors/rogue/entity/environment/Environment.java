package com.styxsailors.rogue.entity.environment;

import java.awt.Graphics2D;

import com.styxsailors.rogue.entity.RogueEntity;
import com.styxsailors.rogue.utils.Global;

public class Environment extends RogueEntity{

	protected boolean canPass = true;
	
	public Environment(int x, int y, Global global) {
		super(x, y, global);
	}
	
	public Environment(Global global) {
		super(global);
	}

	protected void init(){
		setName("Generic Environment");
		width = 32;
		height = 32;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics2D g){
		
	}
	
	public boolean canPass(){
		return canPass;
	}

}
