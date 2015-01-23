package com.styxsailors.rogue.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.styxsailors.rogue.utils.Global;

public class Player extends RogueEntity{

	public boolean stopLeft,stopUp,stopRight,stopDown;
	protected boolean colliding = false;
	protected RogueEntity collidingEntity;
	
	public Player(int x, int y, Global global) {
		super(x, y, global);
		init();
	}
	
	protected void init(){
		setName("Player");
		width = 32;
		height = 32;
		maxHsp = 5;
		maxVsp = 5;
		calculateCenterCoords();
	}
	
	public void tick(){
		global.console.log("Player Coords:("+x+","+y+")");
		global.console.log("Player Speeds:("+hsp+","+vsp+")");
		updateCollision();
		updateInput();
	}
	
	private void updateInput(){
		//Movimenti Destra/Sinistra
				if(global.input.left.down && !stopLeft){
					if(hsp > -maxHsp)
						hsp -= 1;
					else
						hsp = -maxHsp;
				}else if(global.input.right.down && !stopRight){
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
				if(global.input.up.down && !stopUp){
					if(vsp > -maxVsp)
						vsp -= 1;
					else
						vsp = -maxVsp;
				}else if(global.input.down.down && !stopDown){
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
	
	protected void updateCollision(){
		if(colliding){
			if(getTopBounds().intersects(collidingEntity.getBounds())){
				stopUp = true;
				global.console.log("Colliding on top with " + collidingEntity.getName());
				vsp = 0;
			}else{
				stopUp= false;
			}
			if(getBottomBounds().intersects(collidingEntity.getBounds())){
				stopDown = true;
				global.console.log("Colliding on bottom with " + collidingEntity.getName());
				vsp = 0;
			}else{
				stopDown= false;
			}
			if(getLeftBounds().intersects(collidingEntity.getBounds())){
				stopLeft = true;
				global.console.log("Colliding on left with " + collidingEntity.getName());
				hsp = 0;
			}else{
				stopLeft= false;
			}
			if(getRightBounds().intersects(collidingEntity.getBounds())){
				stopRight = true;
				global.console.log("Colliding on right with " + collidingEntity.getName());
				hsp = 0;
			}else{
				stopRight= false;
			}
		}
	}
	
	public void render(Graphics2D g){
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
		
	}
	
	public void setColliding(boolean colliding){
		this.colliding=colliding;
	}

	public void setCollidingEntity(RogueEntity collidingEntity) {
		this.collidingEntity=collidingEntity;
	}
	
}
