package com.styxsailors.rogue.handler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.styxsailors.rogue.editor.Tile;
import com.styxsailors.rogue.entity.RogueEntity;
import com.styxsailors.rogue.utils.Global;

public class MinimapHandler {

	Global global;
	ArrayList<RogueEntity> miniMapGrid = new ArrayList<RogueEntity>();
	ArrayList<Tile> miniMapEditorGrid = new ArrayList<Tile>();
	int reductionScale = 10;
	double minimapTileSize = 3.2;
	
	public MinimapHandler(Global global){
		this.global = global;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics2D g){
		int mapWidth = (int)global.visibleScreen.getWidth() / reductionScale;
		int mapHeight = (int)global.visibleScreen.getHeight() / reductionScale;
		g.setColor(new Color(1.0f,0.3f,1.0f, 0.7f));
		g.fillRect(-global.camX + 50, -global.camY + 50, mapWidth, mapHeight);
		if(!miniMapEditorGrid.isEmpty())
			renderEditorMiniMap(g);
		else if(!miniMapGrid.isEmpty())
			renderInGameMiniMap(g);
	}
	
	private void renderInGameMiniMap(Graphics2D g) {
		double drawX = -global.camX + 50;
		double drawY = -global.camY + 50;
		for(int i = 0; i < miniMapGrid.size();i ++){
			g.setColor(miniMapGrid.get(i).getMinimapColor());
			g.fillRect((int)drawX + miniMapGrid.get(i).getX()/reductionScale, (int)drawY +miniMapGrid.get(i).getY()/reductionScale, (int)minimapTileSize,(int) minimapTileSize);
		}
		
	}

	private void renderEditorMiniMap(Graphics2D g){
		double drawX = -global.camX + 50;
		double drawY = -global.camY + 50;
		for(int i = 0; i < miniMapEditorGrid.size();i ++){
			g.setColor(miniMapEditorGrid.get(i).getMinimapColor());
			g.fillRect((int)drawX + miniMapEditorGrid.get(i).getX()/reductionScale, (int)drawY +miniMapEditorGrid.get(i).getY()/reductionScale, (int)minimapTileSize,(int) minimapTileSize);
		}
		global.console.log("Tile 0 ("+miniMapEditorGrid.get(0).getX()+","+miniMapEditorGrid.get(0).getY()+")");
	}
	
	public void setMinimapGrid(ArrayList<RogueEntity>miniMap){
		miniMapGrid.clear();
		miniMapGrid = new ArrayList<>(miniMap);
	}
	
	public void setEditorMinimapGrid(ArrayList<Tile>miniMap){
		miniMapEditorGrid = new ArrayList<Tile>(miniMap);
	}
}
