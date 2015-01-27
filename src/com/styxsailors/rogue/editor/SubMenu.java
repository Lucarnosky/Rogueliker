package com.styxsailors.rogue.editor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.styxsailors.rogue.utils.Global;

public class SubMenu {

	protected int x,y,width,height;
	protected String name = "Gen. Sub Menu";
	protected Global global;
	protected ArrayList<SubMenuButton> menuVoices = new ArrayList<>();
	protected Color clickedColor = new Color(255,51,51), notClickedColor = new Color(153,0,0);
	protected boolean clicked = false;
	
	public SubMenu(int x, int y, Global global){
		this.x= x;
		this.y = y;
		this.global = global;
	}
	
	protected void init(){
		width = 50;
		height = 15;
		addMenuVoice(new SubMenuButton(x, y, global));
	}
	
	public void tick(int x, int y){
		this.x = x;
		this.y = y;
		int stepY = 15;
		int drawY = 0;
		if(mouseOver()){
			if(global.mouse.left.clicked)
				clicked = !clicked;
			global.console.log("Mouse Over " + name);
		}
		if(clicked)
			for(int i = 0 ; i < menuVoices.size() ; i++){
				drawY += stepY;
				menuVoices.get(i).tick(x, y + drawY);
			}
	}
	
	public void render(Graphics2D g){
		if(clicked){
			renderVoices(g);
			g.setColor(clickedColor);
		}else{
			g.setColor(notClickedColor);
		}
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		g.drawString(name, x + 10 , y + 15 );
			
	}
	
	protected boolean mouseOver(){
		if( global.mouse.x > x && global.mouse.x < x + width && global.mouse.y > y && global.mouse.y < y + height)
			return true;
		return false;
	}
	
	protected void renderVoices(Graphics2D g){
		for(int i = 0 ; i < menuVoices.size() ; i++){
			menuVoices.get(i).render(g);
		}
	}
	
	protected void addMenuVoice(SubMenuButton subMenu){
		menuVoices.add(subMenu);
	}
}
