package com.styxsailors.rogue.utils;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.styxsailors.rogue.entity.RogueEntity;
import com.styxsailors.rogue.entity.environment.UnpassableBlock;
import com.styxsailors.rogue.handler.EditorHandler;
import com.styxsailors.rogue.handler.InputHandler;
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
	public EditorHandler editor;
	public ArrayList<RogueEntity> ids = new ArrayList<RogueEntity>();
	public int selectedIndex = 0;
	public Rectangle visibleScreen;
	
	public Global(){
		System.out.println("Initializing global variable");
		initIds();
	}
	private void initIds(){
		ids.add(new RogueEntity(this));
		ids.add(new UnpassableBlock(this));
	}
}
