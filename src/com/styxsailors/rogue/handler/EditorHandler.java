package com.styxsailors.rogue.handler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.styxsailors.rogue.editor.Bar;
import com.styxsailors.rogue.editor.Tile;
import com.styxsailors.rogue.utils.Global;

public class EditorHandler {
	
	public enum Layer{
		BOTTOM_GRID, MAIN_GRID, OVER_GRID;
	}
	
	Layer selectedLayer = Layer.MAIN_GRID; 
	Global global;
	ArrayList<Tile> bottomGrid = new ArrayList<Tile>();
	ArrayList<Tile> grid = new ArrayList<Tile>();
	int rows = 15, cols = 15;
	Bar menu;
	String levelName = "";
	int maxIndex = 0;
	int bottomGridElement = 0, mainGridElement = 0;
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
		
		for(int i = 0; i < cols * 32; i += 32)
			for(int j = 0; j < rows * 32; j +=32)
				bottomGrid.add(new Tile(i, j, global));
			
	}
	
	public void tick(){
		global.visibleScreen = new Rectangle(-global.camX - 32 ,-global.camY - 32 ,global.W_WIDTH*global.W_SCALE + 32, global.W_HEIGHT *global.W_SCALE + 32 );
		global.noOfEntityUpdating = 0;
		visibleGrid.clear();
		switch(selectedLayer){
			case BOTTOM_GRID:
				updateGrid(bottomGrid);
				global.console.log("Updating Bottom Grid");
				break;
			case MAIN_GRID:
				updateGrid(grid);
				global.console.log("Updating Main Grid");
				break;
			case OVER_GRID:
				break;
			default:
				break;
		}
		global.console.log("Updating "+global.noOfEntityUpdating+" entity");
		bottomGridElement = 0;
		mainGridElement = 0;
		for(int i = 0; i < bottomGrid.size(); i ++)
			if(bottomGrid.get(i).getId() != -1)
				bottomGridElement += 1;
		for(int i = 0; i < grid.size(); i ++)
			if(grid.get(i).getId() != -1)
				mainGridElement += 1;
		if(global.input.bottomLayer.down)
			selectedLayer = Layer.BOTTOM_GRID;
		if(global.input.mainLayer.down)
			selectedLayer = Layer.MAIN_GRID;
		
		miniMap.setEditorMinimapGrid(visibleGrid);
		if(global.mouse.getMouseWheel() < 0)
			global.mouse.setMouseWheel(0);
		if(global.mouse.getMouseWheel() > maxIndex)
			global.mouse.setMouseWheel(maxIndex);
		if(global.mouse.middle.down){
			///Open tile property editor
		}
		global.selectedIndex = (int) global.mouse.getMouseWheel();
		global.camera.tick();
		global.console.log("Updating "+global.noOfEntityUpdating+" entity");
		global.console.log("Visible Screen" + global.visibleScreen);
		global.console.log("Selected Index: "+global.selectedIndex);
		global.console.log("Element in Bottom Grid: "+bottomGridElement);
		global.console.log("Element in Main Grid: "+mainGridElement);
		menu.tick();
	}
	
	public void render(Graphics2D g){
		
		renderLayers(g);
		miniMap.render(g);
		g.drawImage(global.ids.get(global.selectedIndex).getTexture(), (int) global.mouse.x - 16, (int) global.mouse.y - 16, null);
		renderToolWindow(g);
		menu.render(g);
	}
	
	private void updateGrid(ArrayList<Tile> gridToUpdate){
		for(int i = 0 ; i < gridToUpdate.size(); i ++){
			if(global.visibleScreen.contains(grid.get(i).getBounds())){
				gridToUpdate.get(i).tick();
				visibleGrid.add(gridToUpdate.get(i));
			}
		}
		
	}
	
	private void renderLayers(Graphics2D g){
		
		switch(selectedLayer){
		case BOTTOM_GRID:
			//SET ALPHA 0
			for(int i = 0 ; i < bottomGrid.size(); i ++)
				bottomGrid.get(i).render(g);
			// SET ALPHA 0.7
			for(int i = 0 ; i < grid.size(); i ++)
				grid.get(i).render(g);
			break;
		case MAIN_GRID:
			///SET ALPHA 0.7
			for(int i = 0 ; i < bottomGrid.size(); i ++)
				bottomGrid.get(i).render(g);
			///SET ALPHA 0
			for(int i = 0 ; i < grid.size(); i ++)
				grid.get(i).render(g);
			break;
		case OVER_GRID:
			///SET ALPHA 0.7
			for(int i = 0 ; i < bottomGrid.size(); i ++)
				bottomGrid.get(i).render(g);
			///SET ALPHA 0.7
			for(int i = 0 ; i < grid.size(); i ++)
				grid.get(i).render(g);
			break;
		default:
			break;
		
		}
	}
	private void renderToolWindow(Graphics2D g) {
		int x = -global.camX;
		int y = -global.camY + 150;
		g.setColor(new Color(1f,1f,1f,0.7f));
		g.fillRect(x , y, 200, global.W_HEIGHT * global.W_SCALE);
		int stepX = x + 10;
		int stepY = y + 20;
		g.setColor(Color.white);
		if(levelName.equals(""))
			levelName = "Unnamed";
		g.drawString("Level Name: " + levelName, stepX, stepY);
		stepY += 12;
		g.drawString("Selected Object: " + global.ids.get(global.selectedIndex).getName(), stepX, stepY);
		stepY += 10;
		for(int i = 0; i < global.ids.size(); i++){
			g.drawImage(global.ids.get(i).getTexture(), stepX, stepY,null);
			if(global.selectedIndex == i){
				g.setColor(Color.yellow);
				g.drawRect(stepX, stepY, 32, 32);
			}
			stepX += 32;
		}
		stepX = x + 10;
		stepY+=52;
		g.setColor(Color.white);
		g.drawString("Selected Layer: " + selectedLayer.toString(), stepX, stepY);
	}

	public void resetGrid(){
		switch(selectedLayer){
		case BOTTOM_GRID:
			for(int i = 0 ; i < grid.size(); i ++)
				bottomGrid.get(i).setId(-1);
			break;
		case MAIN_GRID:
			for(int i = 0 ; i < grid.size(); i ++)
				grid.get(i).setId(-1);
			break;
		case OVER_GRID:
			break;
		default:
			break;
		
		}
		
	}
	
	public void addRows(int noOfRows){
		for(int i = rows * 32; i < (rows + noOfRows) * 32; i += 32)
			for(int j = 0; j < cols * 32; j+= 32){
				bottomGrid.add(new Tile(j, i, global));
				grid.add(new Tile(j, i, global));
			}
		rows += noOfRows;
	}
	
	public void addCols(int noOfCols){
		for(int i = cols * 32; i < (cols + noOfCols) * 32; i += 32)
			for(int j = 0; j < rows * 32; j+= 32){	
				bottomGrid.add(new Tile(i,j,global));
				grid.add(new Tile(i, j, global));
			}
		cols += noOfCols;
	}
	
	public void removeCols(int noOfCols){
		ArrayList<Tile> tmpGrid = new ArrayList<Tile>();
		ArrayList<Tile> tmpBottomGrid = new ArrayList<Tile>();
		cols += noOfCols;
		for(int i = 0; i < grid.size(); i++){
			if(grid.get(i).x <= cols * 32){
				tmpBottomGrid.add(bottomGrid.get(i));
				tmpGrid.add(grid.get(i));
			}
		}
		bottomGrid.clear();
		bottomGrid = new ArrayList<Tile>(tmpBottomGrid);
		grid.clear();
		grid =new ArrayList<Tile>(tmpGrid);
	}
	
	public void removeRows(int noOfRows){
		ArrayList<Tile> tmpGrid = new ArrayList<Tile>();
		ArrayList<Tile> tmpBottomGrid = new ArrayList<Tile>();
		rows += noOfRows;
		for(int i = 0; i < grid.size(); i++){
			if(grid.get(i).y <= rows * 32){
				tmpBottomGrid.add(bottomGrid.get(i));
				tmpGrid.add(grid.get(i));
			}
		}
		bottomGrid.clear();
		bottomGrid = new ArrayList<Tile>(tmpBottomGrid);
		grid.clear();
		grid =new ArrayList<Tile>(tmpGrid);
	}
	
	public ArrayList<Tile> getGrid(){
		switch(selectedLayer){
		case BOTTOM_GRID:
			return bottomGrid;
		case MAIN_GRID:
			return grid;
		case OVER_GRID:
			break;
		default:
			break;
		
		}
		return null;
	}
	
	public void setGrid(ArrayList<Tile> gridToImport){
		switch(selectedLayer){
		case BOTTOM_GRID:
			bottomGrid = new ArrayList<Tile>(gridToImport);
			break;
		case MAIN_GRID:
			grid = new ArrayList<Tile>(gridToImport);
			break;
		case OVER_GRID:
			break;
		default:
			break;
		
		}
		
	}
	
	public Global getGlobal(){
		return global;
	}

	public void setName(String name){
		this.levelName = name;
	}
	
	protected void fillEmptyTile(){
		switch(selectedLayer){
		case BOTTOM_GRID:
			for(int i = 0; i < bottomGrid.size(); i++){
				if(bottomGrid.get(i).getId() == -1)
					bottomGrid.get(i).setId(global.selectedIndex);
			}
			break;
		case MAIN_GRID:
			for(int i = 0; i < grid.size(); i++){
				if(grid.get(i).getId() == -1)
					grid.get(i).setId(global.selectedIndex);
			}
			break;
		case OVER_GRID:
			break;
		default:
			break;
		
		}
		
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getCols(){
		return cols;
	}
	
	public void setSelectedLayer(Layer layerToSelect){
		selectedLayer = layerToSelect;
	}
}
