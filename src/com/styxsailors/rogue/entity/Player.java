package com.styxsailors.rogue.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.styxsailors.rogue.utils.Global;

public class Player extends RogueEntity{

	public boolean stopLeft;
	
	public Player(int x, int y, Global global) {
		super(x, y, global);
		init();
	}
	
	protected void init(){
		name = "Player";
		width = 32;
		height = 32;
		maxHsp = 5;
		maxVsp = 5;
		calculateCenterCoords();
	}
	
	public void tick(){
		global.console.log("Player Coords:("+x+","+y+")");
		global.console.log("Player Speeds:("+hsp+","+vsp+")");
		updateInput();
	}
	
	private void updateInput(){
		//Movimenti Destra/Sinistra
				if(global.input.left.down && !stopLeft){
					if(hsp > -maxHsp)
						hsp -= 1;
					else
						hsp = -maxHsp;
				}else if(global.input.right.down){
					if(hsp < maxHsp)
						hsp += 1;
					else
						hsp = maxHsp;
				}else{
					if(hsp != 0){
						if(hsp > 0){
							hsp -=1;
						}else if(hsp < 0){
							hsp +=1;
						}
					}
				}
				
				//Movimenti Su/Giù
				if(global.input.up.down){
					if(vsp > -maxVsp)
						vsp -= 1;
					else
						vsp = -maxVsp;
				}else if(global.input.down.down){
					if(vsp < maxVsp)
						vsp += 1;
					else
						vsp = maxVsp;
				}else{
					if(vsp != 0){
						if(vsp > 0){
							vsp -=1;
						}else if(vsp < 0){
							vsp +=1;
						}
					}
				}
				
				//Aggiungo le velocità
				x+= hsp;
				y+=vsp;
	}
	
	public void render(Graphics2D g){
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
	}
	
	
}
