package com.styxsailors.rogue.editor.menu.voices;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.styxsailors.rogue.editor.SubMenu;
import com.styxsailors.rogue.editor.Tile;
import com.styxsailors.rogue.editor.menu.SubMenuButton;
import com.styxsailors.rogue.handler.EditorHandler.Layer;
import com.styxsailors.rogue.utils.Global;

public class ExportLevelVoice extends SubMenuButton{

	public ExportLevelVoice(int x, int y, SubMenu parentMenu, Global global) {
		super(x, y, parentMenu, global);
		init();
	}
	
	private void init(){
		setName("Export Level");
	}
	
	public void actionOnClick(){		
		try {
			String levelName = JOptionPane.showInputDialog("Give a name to the level");
			global.editor.setName(levelName);
			global.editor.setSelectedLayer(Layer.BOTTOM_GRID);
			
			ArrayList<Tile> gridToExport = new ArrayList<>(global.editor.getGrid());
			PrintWriter writer  = new PrintWriter("res/levels/"+ levelName +".lvl", "UTF-8");
			writer.write("name="+levelName+"\n");
			writer.write("<BOTTOM LAYER>\n");
			for(int i = 0; i < gridToExport.size(); i ++){
				writer.append(gridToExport.get(i).getId()+","+gridToExport.get(i).x+","+gridToExport.get(i).y+"\n");
			}
			global.editor.setSelectedLayer(Layer.MAIN_GRID);
			writer.write("<MAIN LAYER>\n");
			gridToExport = new ArrayList<>(global.editor.getGrid());
			for(int i = 0; i < gridToExport.size(); i ++){
				writer.append(gridToExport.get(i).getId()+","+gridToExport.get(i).x+","+gridToExport.get(i).y+"\n");
			}
			writer.append("<END>");
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		};
		
	}

}
