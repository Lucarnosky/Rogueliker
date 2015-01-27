package com.styxsailors.rogue.editor.menu.voices;

import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import com.styxsailors.rogue.editor.SubMenu;
import com.styxsailors.rogue.editor.menu.SubMenuButton;
import com.styxsailors.rogue.utils.Global;

public class ResizeColVoice extends SubMenuButton{

	public ResizeColVoice(int x, int y, SubMenu parentMenu, Global global) {
		super(x, y, parentMenu, global);
	}
	
	public void tick(int x, int y){
		super.tick(x,y);
		init();
	}
	
	private void init(){
		setName("Add Cols");
	}
	
	public void render(Graphics2D g){
		super.render(g);
	}
	
	public void actionOnClick(){
		System.out.println("Click");
		int cols= Integer.parseInt(JOptionPane.showInputDialog("Number of cols to add "));
		global.editor.addCols(cols);
	}

}
