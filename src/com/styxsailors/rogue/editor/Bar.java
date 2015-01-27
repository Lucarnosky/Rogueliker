package com.styxsailors.rogue.editor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.styxsailors.rogue.editor.menu.FileSubMenu;
import com.styxsailors.rogue.utils.Global;

public class Bar{

	Global global;
	int x,y;
	ArrayList<SubMenu> selections = new ArrayList<>();
	
	public Bar(Global global) {
		this.global = global;
		init();
	}

	protected void init(){
		x = -global.camX;
		y = -global.camY;
		selections.add(new FileSubMenu(x + 10 , y, global));
	}
	
	public void tick(){
		x = -global.camX;
		y = -global.camY;
		for(int i = 0 ; i < selections.size(); i++){
			selections.get(i).tick(x, y);
		}
	}
	
	public void render(Graphics2D g){
		g.setColor(Color.gray);
		g.fillRect(x, y, global.W_WIDTH * global.W_SCALE + 20, 32);
		for(int i = 0 ; i < selections.size(); i++){
			selections.get(i).render(g);;
		}
	}
	
	
}
