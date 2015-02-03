package com.styxsailors.rogue.editor.menu.voices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.styxsailors.rogue.editor.SubMenu;
import com.styxsailors.rogue.editor.Tile;
import com.styxsailors.rogue.editor.menu.SubMenuButton;
import com.styxsailors.rogue.utils.Global;

public class LoadLevelVoice extends SubMenuButton{

	public LoadLevelVoice(int x, int y, SubMenu parentMenu, Global global) {
		super(x, y, parentMenu, global);
		init();
	}
	
	private void init(){
		setName("Load Level");
	}
	
	public void actionOnClick(){
		String levelName = JOptionPane.showInputDialog("Level to load name");
		String str = "";
		ArrayList<Tile> loadedGrid = new ArrayList<Tile>();
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("levels/"+levelName+".lvl");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    if (is!=null) {                         
	        try {
				while ((str = reader.readLine()) != null) { 
				   if(str.contains("name")){
					   String[] tmp = str.split("=");
					   global.editor.setName(tmp[1]);
				   }else{
					   String[] tmp = str.split(",");
					   Tile tmpTile = new Tile(Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]), global);
					   tmpTile.setId(Integer.parseInt(tmp[0]));
					   loadedGrid.add(tmpTile);
				   }
				}
				is.close(); 
				global.editor.setGrid(loadedGrid);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}               
	    }       
	}

}
