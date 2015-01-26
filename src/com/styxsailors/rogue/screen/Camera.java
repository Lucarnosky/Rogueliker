package com.styxsailors.rogue.screen;

import com.styxsailors.rogue.entity.RogueEntity;
import com.styxsailors.rogue.utils.Global;

public class Camera {
	
	private Global global;
	private RogueEntity entityToFollow;
	
	public Camera(Global global){
		this.global = global;
		init();
	}

	private void init(){
		global.camX = 0;
		global.camY = 0;
	}
	public void tick(){
		
		if(entityToFollow != null){
			global.console.log("Following "+entityToFollow.getName());
			global.camX = -(entityToFollow.getX() - (global.W_WIDTH * global.W_SCALE / 2) );
			global.camY = -(entityToFollow.getY() - (global.W_HEIGHT * global.W_SCALE / 2) );
		}else{
			global.console.log("[Camera] No Entity to follow");
			updateInput();
		}
	}
	
	public void setEntityToFollow(RogueEntity entityToFollow){
		this.entityToFollow = entityToFollow;
	}
	
	private void updateInput(){
		if(global.input.left.down){
			global.camX += 5;
		}else if(global.input.right.down ){
			global.camX -= 5;
		}
		
		//Movimenti Su/Giù
		if(global.input.up.down){
			global.camY += 5;
		}else if(global.input.down.down ){
			global.camY -= 5;
		}
	}
}
