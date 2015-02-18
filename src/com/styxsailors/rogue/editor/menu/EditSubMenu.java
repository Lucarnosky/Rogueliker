package com.styxsailors.rogue.editor.menu;

import java.awt.Graphics2D;

import com.styxsailors.rogue.editor.SubMenu;
import com.styxsailors.rogue.editor.menu.voices.ExportLevelVoice;
import com.styxsailors.rogue.editor.menu.voices.LoadLevelVoice;
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
		addMenuVoice(new ExportLevelVoice(x, y, this, global));
		addMenuVoice(new LoadLevelVoice(x, y, this, global));
	}
	
	public void tick(int x, int y){
		super.tick(x,y);
	}

	public void render(Graphics2D g){
		super.render(g);
	}
}