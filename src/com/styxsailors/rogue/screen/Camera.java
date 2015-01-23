package com.styxsailors.rogue.screen;

import java.awt.Graphics2D;

import com.styxsailors.rogue.entity.RogueEntity;
import com.styxsailors.rogue.utils.Global;

public class Camera {
	
	private Global global;
	private RogueEntity entityToFollow;
	
	public Camera(Global global){
		this.global = global;
	}

	public void tick(){
		if(entityToFollow != null){
			global.camX = -(entityToFollow.getX() - (global.W_WIDTH * global.W_SCALE / 2) );
			global.camY = -(entityToFollow.getY() - (global.W_HEIGHT * global.W_SCALE / 2) );
		}
	}
	
	public void render(Graphics2D g){
		
	}
	
	public void setEntityToFollow(RogueEntity entityToFollow){
		this.entityToFollow = entityToFollow;
	}
}
