package com.styxsailors.rogue.editor.menu;

import java.awt.Color;
import java.awt.Graphics2D;

import com.styxsailors.rogue.editor.SubMenu;
import com.styxsailors.rogue.utils.Global;

public class FileSubMenu extends SubMenu{

	public FileSubMenu(int x, int y, Global global) {
		super(x, y, global);
		init();
	}
	
	protected void init(){
		name = "File";
		width = 50;
		height = 15;
	}
	
	public void tick(int x, int y){
		super.tick(x,y);
	}
	
	public void render(Graphics2D g){
		super.render(g);
//		if(clicked){
//			renderVoices(g);
//			g.setColor(clickedColor);
//		}else{
//			g.setColor(notClickedColor);
//		}
//		g.fillRect(x, y, width, height + 10);
//		g.setColor(Color.white);
//		g.drawString(name, x, y + 10);
		
	}
	
}
