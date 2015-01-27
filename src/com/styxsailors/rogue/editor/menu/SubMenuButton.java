package com.styxsailors.rogue.editor.menu;

import java.awt.Color;
import java.awt.Graphics2D;

import com.styxsailors.rogue.editor.SubMenu;
import com.styxsailors.rogue.utils.Global;

public class SubMenuButton {

	protected int x,y;
	private String name;
	protected Global global;
	protected int width, height=18;
	protected Color notMouseOveColor = new Color(192,192,192);
	protected Color mouseOverColor = new Color(102,178,255);
	SubMenu parentMenu;
	
	public SubMenuButton(int x, int y ,SubMenu parentMenu, Global global){
		this.x = x;
		this.y = y;
		this.global = global;
		this.parentMenu = parentMenu;
		init();
	}
	
	private void init(){
		setName("Sub Menu Voice");
		//calculateWidth();
	}
	
	
	public void tick(int x, int y){
		this.x = x;
		this.y = y;
		if(global.mouse.left.down){
			if(mouseOver())
				actionOnClick();
			global.mouse.releaseAll();
			parentMenu.setClicked(false);
		}
		if(mouseOver())
			global.console.log("Over " + name);
				
	}
	
	protected void actionOnClick(){
		System.out.println(getName() + " clicked");
	}
	
	public void render(Graphics2D g){
		if(mouseOver())
			g.setColor(mouseOverColor);
		else
			g.setColor(notMouseOveColor);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawString(getName(), x + 5, y + height / 2 + 3);
	}
	
	protected boolean mouseOver(){
		if( global.mouse.x > x && global.mouse.x < x + width && global.mouse.y > y && global.mouse.y < y + height)
			return true;
		return false;
	}
	
	public int calculateWidth(){
		width = getName().length() * 8;
		height = 18;
		return width;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setWidth(int width){
		System.out.println("Setting width");
		this.width = width;
	}

	public int getWidth() {
		return width;
	}
}
