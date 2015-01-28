package com.styxsailors.rogue.editor.menu.voices;

import javax.swing.JOptionPane;

import com.styxsailors.rogue.editor.SubMenu;
import com.styxsailors.rogue.editor.menu.SubMenuButton;
import com.styxsailors.rogue.utils.Global;

public class ResizeRowVoice extends SubMenuButton{

	public ResizeRowVoice(int x, int y, SubMenu parentMenu, Global global) {
		super(x, y, parentMenu, global);
		init();
	}
	
	private void init(){
		setName("Add Row");
	}
	
	public void actionOnClick(){
		int rows= Integer.parseInt(JOptionPane.showInputDialog("Number of rows to add "));
		if(rows > 0){
			global.editor.addRows(rows);
		}else{
			global.editor.removeRows(rows);
		}
	}

}
