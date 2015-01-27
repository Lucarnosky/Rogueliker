package com.styxsailors.rogue.editor.menu;

import java.awt.Graphics2D;

import com.styxsailors.rogue.editor.SubMenuButton;
import com.styxsailors.rogue.utils.Global;

public class ExitVoice extends SubMenuButton{

	public ExitVoice(int x, int y, Global global) {
		super(x, y, global);
		init();
	}
	
	protected void init(){
		name = "Exit";
		calculateWidth();
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
