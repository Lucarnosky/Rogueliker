package com.styxsailors.rogue.editor.menu.voices;

import java.awt.Graphics2D;

import com.styxsailors.rogue.editor.SubMenu;
import com.styxsailors.rogue.editor.menu.SubMenuButton;
import com.styxsailors.rogue.utils.Global;

public class NewVoice extends SubMenuButton{

	public NewVoice(int x, int y,SubMenu parentMenu, Global global) {
		super(x, y,parentMenu, global);
	}
	
	public void tick(int x, int y){
		super.tick(x, y);
		init();
	}
	
	private void init(){
		setName("New");
	}
	
	public void render(Graphics2D g){
		super.render(g);
	}
	
	public void actionOnClick(){
		global.editor.resetGrid();
	}

}
