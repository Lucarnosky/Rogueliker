package com.styxsailors.rogue.utils;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.styxsailors.rogue.entity.Player;
import com.styxsailors.rogue.entity.RogueEntity;
import com.styxsailors.rogue.entity.environment.Floor;
import com.styxsailors.rogue.entity.environment.HalfUnpassableVertical;
import com.styxsailors.rogue.entity.environment.UnpassableBlock;
import com.styxsailors.rogue.handler.EditorHandler;
import com.styxsailors.rogue.handler.InputHandler;
import com.styxsailors.rogue.handler.LevelHandler;
import com.styxsailors.rogue.handler.MouseHandler;
import com.styxsailors.rogue.handler.TextureHandler;
import com.styxsailors.rogue.screen.Camera;

public class Global {

	
	public enum GameState{
		MAIN_MENU,PLAY,PAUSE,EDITOR;
	}
	public int fps = 0;
	public GameState gamestate;
	public InputHandler input ;
	public MouseHandler mouse;
	public int W_WIDTH, W_HEIGHT, W_SCALE;
	public Console console;
	public TextureHandler tex = new TextureHandler();
	public int camX, camY;
	public Camera camera;
	public LevelHandler level;
	public EditorHandler editor;
	public ArrayList<RogueEntity> ids = new ArrayList<RogueEntity>();
	public int selectedIndex = 0;
	public Rectangle visibleScreen;
	public boolean enableMinimap = true;
	public int noOfEntityUpdating = 0;
	
	public Global(){
		System.out.println("Initializing global variable");
		initIds();
	}
	
	private void initIds(){
		ids.add(new Player(this));
		ids.add(new UnpassableBlock(this));
		ids.add(new Floor(this));
		ids.add(new HalfUnpassableVertical(this));
	}
	
	public Color fromRGBToFloat(int r, int g, int b, float alpha){
		float rf = r/255;
		float gf = g / 255;
		float bf = b / 255;
		return new Color(rf,gf,bf,alpha);
	}
	
}
