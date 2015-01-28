package com.styxsailors.rogue.editor.menu.voices;

import javax.swing.JOptionPane;

import com.styxsailors.rogue.editor.SubMenu;
import com.styxsailors.rogue.editor.menu.SubMenuButton;
import com.styxsailors.rogue.utils.Global;

public class ResizeColVoice extends SubMenuButton{

	public ResizeColVoice(int x, int y, SubMenu parentMenu, Global global) {
		super(x, y, parentMenu, global);
		init();
	}
	
	private void init(){
		setName("Add Col");
	}
	
	public void actionOnClick(){
		int cols= Integer.parseInt(JOptionPane.showInputDialog("Number of cols to add "));
		if(cols > 0){
			global.editor.addCols(cols);
		}else{
			global.editor.removeCols(cols);
		}
	}

}
