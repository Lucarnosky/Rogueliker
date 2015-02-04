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
	Bar menu;
	String levelName = "";
	int maxIndex = 0;
	ArrayList<Tile> visibleGrid = new ArrayList<Tile>();
	MinimapHandler miniMap;
	
	public EditorHandler(Global global){
		this.global = global;
		init();
	}
	
	private void init(){
		global.camX = 100;
		global.camY = 100;
		menu = new Bar(global);
		miniMap = new MinimapHandler(global);
		global.camera.setEntityToFollow(null);
		maxIndex = global.ids.size() - 1;
		for(int i = 0; i < cols * 32; i += 32)
			for(int j = 0; j < rows * 32; j +=32)
				grid.add(new Tile(i, j, global));
			
	}
	
	public void tick(){
		global.visibleScreen = new Rectangle(-global.camX - 32 ,-global.camY - 32 ,global.W_WIDTH*global.W_SCALE + 32, global.W_HEIGHT *global.W_SCALE + 32 );
		visibleGrid.clear();
		for(int i = 0 ; i < grid.size(); i ++){
			if(global.visibleScreen.contains(grid.get(i).getBounds())){
				grid.get(i).tick();
				visibleGrid.add(grid.get(i));
			}
		}
		miniMap.setEditorMinimapGrid(visibleGrid);
		if(global.mouse.getMouseWheel() < 1)
			global.mouse.setMouseWheel(1);
		if(global.mouse.getMouseWheel() > maxIndex)
			global.mouse.setMouseWheel(maxIndex);
		global.selectedIndex = (int) global.mouse.getMouseWheel();
		global.camera.tick();
		global.console.log("Visible Screen" + global.visibleScreen);
		global.console.log("Number of tile selectable: " + maxIndex);
		global.console.log("Selected Index: "+global.selectedIndex);
		menu.tick();
	}
	
	public void render(Graphics2D g){
		
		for(int i = 0 ; i < grid.size(); i ++)
			grid.get(i).render(g);
		miniMap.render(g);
		g.drawImage(global.ids.get(global.selectedIndex).getTexture(), (int) global.mouse.x - 16, (int) global.mouse.y - 16, null);
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
	
	public ArrayList<Tile> getGrid(){
		return grid;
	}
	
	public void setGrid(ArrayList<Tile> gridToImport){
		grid = new ArrayList<Tile>(gridToImport);
	}
	
	public Global getGlobal(){
		return global;
	}

	public void setName(String name){
		this.levelName = name;
	}
}
