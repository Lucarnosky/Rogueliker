package com.styxsailors.rogue.editor.menu.voices;

import java.util.ArrayList;

import com.styxsailors.rogue.editor.SubMenu;
import com.styxsailors.rogue.editor.Tile;
import com.styxsailors.rogue.editor.menu.SubMenuButton;
import com.styxsailors.rogue.utils.Global;

public class GenerateRoom extends SubMenuButton{

	public GenerateRoom(int x, int y, SubMenu parentMenu, Global global) {
		super(x, y, parentMenu, global);
		init();
	}
	
	private void init(){
		setName("Generate Room");
	}
	
	public void actionOnClick(){
		ArrayList<Tile> gridToExport = new ArrayList<>(global.editor.getGrid());
		int rows = global.editor.getRows();
		int cols = global.editor.getCols();
		for(int i = 0; i < gridToExport.size(); i++){
			double tileX = gridToExport.get(i).getX();
			double tileY = gridToExport.get(i).getY();
			if(tileX == 0 || tileX/32 == cols - 1 || tileY == 0 || tileY/32 == rows - 1)
				gridToExport.get(i).setId(1);
			else if(gridToExport.get(i).getId() == -1)
				gridToExport.get(i).setId(2);
		}
		global.editor.setGrid(gridToExport);
	}

}
