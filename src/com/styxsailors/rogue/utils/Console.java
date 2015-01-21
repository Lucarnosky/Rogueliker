package com.styxsailors.rogue.utils;

import java.awt.Graphics2D;

public class Console {
	
	boolean enable = false;
	private Global global;
	
	public Console(Global global){
		this.global = global;
	}
	
	public void tick(){
		
		if(global.input.console.down){
			System.out.println("Pressed Consoble button");
		}
	}
	
	public void render(Graphics2D g){
		
	}

}
