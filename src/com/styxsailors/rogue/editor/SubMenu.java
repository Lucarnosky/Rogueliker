package com.styxsailors.rogue.editor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.styxsailors.rogue.editor.menu.SubMenuButton;
import com.styxsailors.rogue.utils.Global;

public class SubMenu {

	protected int x,y,width,height;
	protected String name = "Gen. Sub Menu";
	protected Global global;
	protected ArrayList<SubMenuButton> menuVoices = new ArrayList<>();
	protected Color clickedColor = new Color(255,51,51), notClickedColor = new Color(153,0,0);
	private boolean clicked = false;
	private int maxWidth = 0;
	
	public SubMenu(int x, int y, Global global){
		this.x= x;
		this.y = y;
		this.global = global;
	}
	
	protected void init(){
		width = 50;
		height = 15;
		
	}
	
	public void tick(int x, int y){
		this.x = x;
		this.y = y;
		int stepY = 18;
		int drawY = 15;
		if(mouseOver()){
			if(global.mouse.left.down){
				setClicked(!isClicked());
				global.mouse.releaseAll();
			}
			global.console.log("Mouse Over " + name);
		}
		if(isClicked()){
			for(int i = 0 ; i < menuVoices.size() ; i++){
				menuVoices.get(i).tick(x, y + drawY);
				drawY += stepY;
			}
		}
	}
	
	public void render(Graphics2D g){
		if(isClicked()){
			renderVoices(g);
			g.setColor(clickedColor);
		}else{
			g.setColor(notClickedColor);
		}
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		g.drawString(name, x + 10 , y + 13 );
			
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
		if(subMenu.calculateWidth() > maxWidth)
			maxWidth = subMenu.getName().length() * 8;
		menuVoices.add(subMenu);
		normalizeWidth();		
	}
	
	private void normalizeWidth(){
		for(int i = 0; i < menuVoices.size(); i++){
			menuVoices.get(i).setWidth(maxWidth);
		}
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
}
