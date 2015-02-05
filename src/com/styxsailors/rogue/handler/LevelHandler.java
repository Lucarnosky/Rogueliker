package com.styxsailors.rogue.handler;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.styxsailors.rogue.editor.Tile;
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
		loadLevel("test");
	}
	
	public void tick(){
		entityCounter = 0;
		visibleScreen = new Rectangle(-global.camX - 100,-global.camY -100,global.W_WIDTH*global.W_SCALE + 200, global.W_HEIGHT *global.W_SCALE + 200);
		global.console.log("Visible Screen: " + visibleScreen.toString());
		updateLayer(entities);
		updateLayer(levelMap);
		global.camera.tick();
		global.console.log("Entities On Map: " + countEntitiesOnScreen());
	}
	
	public void render(Graphics2D g){
		renderLayer(entities, g);
		renderLayer(levelMap, g);
	}
	
	private void loadLevel(String levelName){
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("levels/"+levelName+".lvl");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String str = "";
	    if (is!=null) {                         
	        try {
				while ((str = reader.readLine()) != null) { 
				   if(str.contains("name")){
					  
				   }else{
					   String[] tmp = str.split(",");
					   if(!tmp[0].equals("-1")){
						   	RogueEntity tmpClass = null;
						   	tmpClass = global.ids.get(Integer.parseInt(tmp[0])).getClass().asSubclass(global.ids.get(Integer.parseInt(tmp[0])).getClass()).getConstructor(int.class,int.class,Global.class).newInstance(Integer.parseInt(tmp[1]),Integer.parseInt(tmp[2]),global);
						   	tmpClass.setX(Integer.parseInt(tmp[1]));
						   	tmpClass.setY(Integer.parseInt(tmp[2]));
					   		System.out.println("Tmp Class: " +tmpClass.getName()+ " ("+tmpClass.getX()+","+tmpClass.getY()+")");
					   		if(tmpClass.ID == 0){
					   			global.camera.setEntityToFollow(tmpClass);
					   			p = (Player) tmpClass;
					   			entities.add(tmpClass);
					   		}else{
					   			levelMap.add(tmpClass);
					   		}
					   }
				   }
				}
				is.close(); 
				for(int i = 0; i < levelMap.size(); i++){
					System.out.println("Tmp Class: " +levelMap.get(i).getName()+ " ("+levelMap.get(i).getX()+","+levelMap.get(i).getY()+")");
				}
			} catch (IOException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				JOptionPane.showConfirmDialog(null, "Unable to load the specified level");
				e.printStackTrace();
			}               
	    }       
		entities.add(p);
		
	}
	
	private void updateLayer(ArrayList<RogueEntity> list){
		
		for(int i = 0; i < list.size(); i++){
			if(visibleScreen.contains(new Rectangle(list.get(i).getX(), list.get(i).getY(), list.get(i).getWidth(), list.get(i).getHeight()))){
				entityCounter += 1;
				list.get(i).tick();
				if(list.get(i) != p && list.get(i).getBounds().intersects(p.getBounds())){
					p.setColliding(true);
					p.setCollidingEntity(list.get(i));
					global.console.log("Player Collision With " + list.get(i).getName());
				}
			}
			if(list.get(i).remove)
				list.remove(list.get(i));
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
