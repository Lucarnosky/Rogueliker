package com.styxsailors.rogue.editor.menu.voices;

import java.util.ArrayList;

import com.styxsailors.rogue.editor.SubMenu;
import com.styxsailors.rogue.editor.Tile;
import com.styxsailors.rogue.editor.menu.SubMenuButton;
import com.styxsailors.rogue.handler.EditorHandler.Layer;
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
		global.editor.setSelectedLayer(Layer.BOTTOM_GRID);
		ArrayList<Tile> bottomGridToExport = new ArrayList<>(global.editor.getGrid());
		global.editor.setSelectedLayer(Layer.MAIN_GRID);
		ArrayList<Tile> mainGridToExport = new ArrayList<>(global.editor.getGrid());
		int rows = global.editor.getRows();
		int cols = global.editor.getCols();
		for(int i = 0; i < mainGridToExport.size(); i++){
			double tileX = mainGridToExport.get(i).getX();
			double tileY = mainGridToExport.get(i).getY();
			if(tileX == 0 || tileX/32 == cols - 1 || tileY == 0 || tileY/32 == rows - 1){ 
				mainGridToExport.get(i).setId(1);
			}
			if(bottomGridToExport.get(i).getId() == -1){
				bottomGridToExport.get(i).setId(2);
			}
		}
		global.editor.setSelectedLayer(Layer.BOTTOM_GRID);
		global.editor.setGrid(bottomGridToExport);
		global.editor.setSelectedLayer(Layer.MAIN_GRID);
		global.editor.setGrid(mainGridToExport);
	}

}
