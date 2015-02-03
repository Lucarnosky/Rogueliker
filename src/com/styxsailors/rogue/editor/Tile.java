package com.styxsailors.rogue.editor;

import java.awt.Color;
import java.awt.Graphics2D;

import com.styxsailors.rogue.entity.RogueEntity;
import com.styxsailors.rogue.utils.Global;

public class Tile extends RogueEntity{
	
	private int occupyID = -1;
	
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
		if(mouseOver()){
			if(global.mouse.left.down)
				setId(global.selectedIndex);
			if(global.mouse.right.down)
				setId(-1);
		}
	}
	
	public void render(Graphics2D g){
		if(occupyID == -1){
			g.setColor(Color.white);
			g.drawRect(x, y, width, height);
		}else{
			g.drawImage(global.ids.get(occupyID).getTexture(), x,  y,null);
		}
	}

	public void setId(int ID){
		occupyID = ID;
	}
	
	public int getId(){
		return occupyID;
	}
	
}
