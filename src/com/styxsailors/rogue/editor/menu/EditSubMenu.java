package com.styxsailors.rogue.editor.menu;

import java.awt.Graphics2D;

import com.styxsailors.rogue.editor.SubMenu;
import com.styxsailors.rogue.editor.menu.voices.ResizeColVoice;
import com.styxsailors.rogue.editor.menu.voices.ResizeRowVoice;
import com.styxsailors.rogue.utils.Global;

public class EditSubMenu extends SubMenu{

	public EditSubMenu(int x, int y , Global global){
		super(x,y,global);
		init();
	}
	
	protected void init(){
		name = "Edit";
		width = 50;
		height = 15;
		addMenuVoice(new ResizeColVoice(x, y, this, global));
		addMenuVoice(new ResizeRowVoice(x, y, this, global));
	}
	
	public void tick(int x, int y){
		super.tick(x,y);
	}

	public void render(Graphics2D g){
		super.render(g);
	}
}