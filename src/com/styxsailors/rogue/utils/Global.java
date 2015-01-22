package com.styxsailors.rogue.utils;

import com.styxsailors.rogue.handler.InputHandler;
import com.styxsailors.rogue.handler.TextureHandler;
import com.styxsailors.rogue.screen.Camera;

public class Global {

	
	public enum GameState{
		MAIN_MENU,PLAY,PAUSE;
	}
	public GameState gamestate;
	public InputHandler input ;
	public int W_WIDTH, W_HEIGHT, W_SCALE;
	public Console console;
	public TextureHandler tex = new TextureHandler();
	public int camX, camY;
	public Camera camera;
	
}
