package com.styxsailors.rogue.editor;

import java.awt.Color;
import java.awt.Graphics2D;

import com.styxsailors.rogue.utils.Global;

public class SubMenuButton {

	protected int x,y;
	protected String name;
	protected Global global;
	protected int width, height;
	protected Color notMouseOveColor = new Color(192,192,192);
	protected Color mouseOverColor = new Color(102,178,255);
	
	public SubMenuButton(int x, int y , Global global){
		this.x = x;
		this.y = y;
		this.global = global;
		init();
	}
	
	private void init(){
		name = "Generic Sub Menu Voice";
		calculateWidth();
	}
	
	
	public void tick(int x, int y){
		this.x = x;
		this.y = y;
		if(mouseOver())
			if(global.mouse.left.down){
				global.mouse.releaseAll();
				actionOnClick();
			}
				
	}
	
	protected void actionOnClick(){
		System.out.println(name + " clicked");
	}
	
	public void render(Graphics2D g){
		if(mouseOver())
			g.setColor(mouseOverColor);
		else
			g.setColor(notMouseOveColor);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawString(name, x + 5, y + height / 2 + 3);
		
	}
	
	protected boolean mouseOver(){
		if( global.mouse.x > x && global.mouse.x < x + width && global.mouse.y > y && global.mouse.y < y + height)
			return true;
		return false;
	}
	
	protected void calculateWidth(){
		width = name.length() * 8;
		height = 15;
	}
}
