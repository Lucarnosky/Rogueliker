package com.styxsailors.rogue.utils;

import com.styxsailors.rogue.handler.InputHandler;

public class Global {

	
	public enum GameState{
		MAIN_MENU,PLAY,PAUSE;
	}
	
	public GameState gamestate;
	public InputHandler input ;
	public int W_WIDTH, W_HEIGHT, W_SCALE;
}
