package com.styxsailors.rogue.editor;

import java.awt.Color;
import java.awt.Graphics2D;

import com.styxsailors.rogue.entity.RogueEntity;
import com.styxsailors.rogue.utils.Global;

public class Tile extends RogueEntity{
	
	private int occupyID = -1;
	private boolean property = false;
	
	public Tile(int x, int y, Global global) {
		super(x, y, global);
		init();
	}
	
	protected void init(){
		setName("Editor Tile");
		width = 32;
		height = 32;
	}

	public void tick(){
		global.noOfEntityUpdating += 1;
		if(mouseOver()){
			global.console.log("Updating tile ID: " + occupyID);
			if(global.mouse.left.down)
				setId(global.selectedIndex);
			if(global.mouse.right.down)
				setId(-1);
			if(global.mouse.middle.down){
				property = !property;
				global.mouse.releaseAll();
			}
		}
		if(occupyID != -1){
			minimapColor = global.ids.get(occupyID).getMinimapColor();
		}else{
			minimapColor = Color.white;
		}
	}
	
	public void render(Graphics2D g){
		if(occupyID == -1){
			g.setColor(Color.white);
			g.drawRect(x, y, width, height);
		}else{
			g.drawImage(global.ids.get(occupyID).getTexture(), x,  y,null);
		}
		if (property){
			g.setColor(new Color(1,1,1,0.4f));
			g.fillRect(x + width / 2, y + height / 2, 50, 50);
		}
	}

	public void setId(int ID){
		occupyID = ID;
	}
	
	public int getId(){
		return occupyID;
	}
	
}
