package com.styxsailors.rogue.handler;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.styxsailors.rogue.entity.Player;
import com.styxsailors.rogue.entity.RogueEntity;
import com.styxsailors.rogue.utils.Global;

public class LevelHandler {

	Global global;
	ArrayList<RogueEntity> entities = new ArrayList<>();
	
	public LevelHandler(Global global){
		this.global=global;
		init();
	}
	
	private void init(){
		loadLevel();
	}
	
	public void tick(){
		updateLayer(entities);
	}
	
	public void render(Graphics2D g){
		renderLayer(entities, g);
	}
	
	private void loadLevel(){
		entities.add(new Player(100, 100, global));
	}
	
	private void updateLayer(ArrayList<RogueEntity> list){
		for(int i = 0; i < list.size(); i++){
			list.get(i).tick();
		}
	}
	
	private void renderLayer(ArrayList<RogueEntity> list,Graphics2D g){
		for(int i = 0; i < list.size(); i++){
			list.get(i).render(g);
		}
	}
}
