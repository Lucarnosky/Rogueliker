package com.styxsailors.rogue.entity;

import java.awt.Graphics2D;

import com.styxsailors.rogue.utils.Global;

public class RogueEntity {
	
	protected Global global;
	protected int x,y,ID;
	protected int hsp, vsp;
	protected int maxHsp, maxVsp;
	protected String name;
	
	public RogueEntity(int x, int y, Global global){
		this.x = x;
		this.y = y;
		this.global=global;
	}
	
	protected void init(){
		name = "Rogue Entity";
		ID = -1;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics2D g){
		
	}
	

}
