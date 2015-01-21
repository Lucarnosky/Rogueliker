package com.styxsailors.rogue.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Console {
	
	boolean enable = false;
	private Global global;
	ArrayList<String>messages = new ArrayList<>();
	
	public Console(Global global){
		this.global = global;
	}
	
	public void tick(){
		
		if(global.input.console.down){
			enable = !enable;
			global.input.releseKey(global.input.console);
			
		}
		log("///**** Styx Sailor Engine V.01 ****///");
	}
	
	public void render(Graphics2D g){
		
		if(enable){
			g.setColor(new Color(1, 1, 1, 0.7f)); //Red 
			g.fillRect(0,0, (global.W_WIDTH * global.W_SCALE) / 3 * 2,global.W_HEIGHT * global.W_SCALE);	
			g.setColor(Color.white);
			int stepY = 10;
			int drawY = 10;
			for(int i = 0; i < messages.size(); i ++){
				g.drawString(messages.get(i), 1, drawY);
				drawY += stepY;
			}
		}
		messages.clear();
	}
	
	public void log (String message){
		messages.add(message);
	}

}
