package com.styxsailors.rogue.editor.menu;

import java.awt.Graphics2D;

import com.styxsailors.rogue.editor.SubMenu;
import com.styxsailors.rogue.editor.menu.voices.ExitVoice;
import com.styxsailors.rogue.editor.menu.voices.NewVoice;
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
		addMenuVoice(new NewVoice(x, y,this, global));
		addMenuVoice(new ExitVoice(x, y,this, global));
	}
	
	public void tick(int x, int y){
		super.tick(x,y);
	}
	
	public void render(Graphics2D g){
		super.render(g);
	}
	
}
