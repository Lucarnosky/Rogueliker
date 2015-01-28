package com.styxsailors.rogue.handler;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.styxsailors.rogue.editor.Bar;
import com.styxsailors.rogue.editor.Tile;
import com.styxsailors.rogue.utils.Global;

public class EditorHandler {
	
	public enum Layer{
		MAIN_GRID, OVER_GRID;
	}
	Layer selectedLayer = Layer.MAIN_GRID; 
	Global global;
	ArrayList<Tile> grid = new ArrayList<Tile>();
	int rows = 4, cols = 10;
	Rectangle visibleScreen ;
	Bar menu;
	
	public EditorHandler(Global global){
		this.global = global;
		init();
	}
	
	private void init(){
		menu = new Bar(global);
		global.camX = 100;
		global.camY = 100;
		global.camera.setEntityToFollow(null);
		for(int i = 0; i < cols * 32; i += 32)
			for(int j = 0; j < rows * 32; j +=32)
				grid.add(new Tile(i, j, global));
			
	}
	
	public void tick(){
		visibleScreen = new Rectangle(-global.camX,-global.camY,global.W_WIDTH*global.W_SCALE, global.W_HEIGHT *global.W_SCALE );
		for(int i = 0 ; i < grid.size(); i ++)
			if(visibleScreen.contains(grid.get(i).getBounds()))
				grid.get(i).tick();
		global.camera.tick();
		global.console.log("Visible Screen" + visibleScreen);
		menu.tick();
	}
	
	public void render(Graphics2D g){
		for(int i = 0 ; i < grid.size(); i ++)
			grid.get(i).render(g);
		menu.render(g);
	}
	
	public void resetGrid(){
		for(int i = 0 ; i < grid.size(); i ++)
			grid.get(i).setId(-1);
	}
	
	public void addRows(int noOfRows){
		for(int i = rows * 32; i < (rows + noOfRows) * 32; i += 32)
			for(int j = 0; j < cols * 32; j+= 32)	
				grid.add(new Tile(j, i, global));
		rows += noOfRows;
	}
	
	public void addCols(int noOfCols){
		for(int i = cols * 32; i < (cols + noOfCols) * 32; i += 32)
			for(int j = 0; j < rows * 32; j+= 32)	
				grid.add(new Tile(i, j, global));
		cols += noOfCols;
	}
	
	public void removeCols(int noOfRows){
		ArrayList<Tile> tmpGrid = new ArrayList<Tile>();
		int tmpIndex = 0;
		for(int i = 0; i < (cols + noOfRows) * 32; i += 32)
			for(int j = 0; j < rows * 32; j+= 32){
				tmpGrid.add(grid.get(tmpIndex));
				tmpIndex += 1;
			}
		grid.clear();
		grid =new ArrayList<Tile>(tmpGrid);
		cols += noOfRows;
	}
	
	public void removeRows(int noOfRows){
		ArrayList<Tile> tmpGrid = new ArrayList<Tile>();
		int tmpIndex = 0;
		for(int i = 0; i < cols * 32; i += 32)
			for(int j = 0; j <  rows * 32; j+= 32){
				if(grid.get(tmpIndex).y < (rows+ noOfRows) * 32)
					tmpGrid.add(grid.get(tmpIndex));
				tmpIndex += 1;
			}
		grid.clear();
		grid =new ArrayList<Tile>(tmpGrid);
		rows += noOfRows;
	}

}
