package com.styxsailors.rogue.handler;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.styxsailors.rogue.entity.Player;
import com.styxsailors.rogue.entity.RogueEntity;
import com.styxsailors.rogue.entity.environment.UnpassableBlock;
import com.styxsailors.rogue.utils.Global;

public class LevelHandler {

	Global global;
	ArrayList<RogueEntity> entities = new ArrayList<>();
	ArrayList<RogueEntity> levelMap = new ArrayList<>();
	Rectangle visibleScreen;
	int entityCounter = 0;
	Player p;
	
	public LevelHandler(Global global){
		this.global=global;
		init();
	}
	
	private void init(){
		loadLevel();
	}
	
	public void tick(){
		entityCounter = 0;
		visibleScreen = new Rectangle(-global.camX - 100,-global.camY -100,global.W_WIDTH*global.W_SCALE + 200, global.W_HEIGHT *global.W_SCALE + 200);
		global.console.log("Visible Screen: " + visibleScreen.toString());
		global.console.log("Player source box: " + p.getBounds());
		updateLayer(entities);
		updateLayer(levelMap);
		global.camera.tick();
		global.console.log("Entities On Map: " + countEntitiesOnScreen());
	}
	
	public void render(Graphics2D g){
		renderLayer(entities, g);
		renderLayer(levelMap, g);
		
	}
	
	private void loadLevel(){
		p = new Player(100,100,global);
		global.camera.setEntityToFollow(p);
		entities.add(p);
		levelMap.add(new UnpassableBlock(150, 150, global));
		
	}
	
	private void updateLayer(ArrayList<RogueEntity> list){
		
		for(int i = 0; i < list.size(); i++){
			if(visibleScreen.contains(new Rectangle(list.get(i).getX(), list.get(i).getY(), list.get(i).getWidth(), list.get(i).getHeight()))){
				entityCounter += 1;
				list.get(i).tick();
			}
			
		}
	}
	
	private void renderLayer(ArrayList<RogueEntity> list,Graphics2D g){
		for(int i = 0; i < list.size(); i++){
			if(visibleScreen.contains(new Rectangle(list.get(i).getX(), list.get(i).getY(), list.get(i).getWidth(), list.get(i).getHeight()))){
				list.get(i).render(g);
			}
		}
	}
	
	private int countEntitiesOnScreen(){
		return entityCounter;
	}
	
	
	
}
