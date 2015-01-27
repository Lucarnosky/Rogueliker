package com.styxsailors.rogue.editor.menu.voices;

import java.awt.Graphics2D;

import com.styxsailors.rogue.editor.SubMenu;
import com.styxsailors.rogue.editor.menu.SubMenuButton;
import com.styxsailors.rogue.utils.Global;

public class ExitVoice extends SubMenuButton{

	public ExitVoice(int x, int y, SubMenu parentMenu,Global global) {
		super(x, y,parentMenu, global);
		init();
	}
	
	protected void init(){
		setName("Exit");
	}
	
	public void tick(int x, int y){
		super.tick(x, y);
	}
	
	public void render(Graphics2D g){
		super.render(g);
	}
	
	protected void actionOnClick(){
		System.exit(0);
	}

}
