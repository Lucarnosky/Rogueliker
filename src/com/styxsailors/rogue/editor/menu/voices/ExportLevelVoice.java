package com.styxsailors.rogue.editor.menu.voices;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.styxsailors.rogue.editor.SubMenu;
import com.styxsailors.rogue.editor.Tile;
import com.styxsailors.rogue.editor.menu.SubMenuButton;
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
		String levelName = JOptionPane.showInputDialog("Give a name to the level");
		ArrayList<Tile> gridToExport = new ArrayList<>(global.editor.getGrid());
		try {
			PrintWriter writer  = new PrintWriter("res/levels/"+ levelName +".lvl", "UTF-8");
			writer.write(levelName + "\n");
			for(int i = 0; i < gridToExport.size(); i ++){
				//writer.append("("+gridToExport.get(i).x+","+gridToExport.get(i).y+","+gridToExport.get(i).getId()+")\n");
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			
			e.printStackTrace();
		};
		
	}
	
//	public void exportLevel(String levelName){
//		PrintWriter writer;
//		try {
//			writer = new PrintWriter("res/levels/"+ levelName +".lvl", "UTF-8");
//			
//			if(levelName != null){ // If not specified a next Level Name, call itself
//				writer.write(nextLevelName + "\n");
//				writer.append(String.valueOf(width)+ "\n");
//			}else{
//				writer.write(levelName + "\n");
//				writer.append(String.valueOf(width)+ "\n");
//			}
//			writer.append(String.valueOf(height) + "\n");
//			for(int i = 0; i < mainTiles.size(); i ++){
//				writer.append(String.valueOf(mainTiles.get(i).getX()) + "\n");
//				writer.append(String.valueOf(mainTiles.get(i).getY()) + "\n");
//				writer.append(String.valueOf(mainTiles.get(i).selectedLayer)+ "\n");
//				writer.append(String.valueOf(mainTiles.get(i).index) + "\n");
//			}
//			writer.close();
//		} catch (FileNotFoundException | UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		
//	}

}
