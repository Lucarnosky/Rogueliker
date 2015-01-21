package com.styxsailors.rogue.handler;

import java.awt.Graphics2D;

import com.styxsailors.rogue.utils.Console;
import com.styxsailors.rogue.utils.Global;
import com.styxsailors.rogue.utils.Global.GameState;

public class GameHandler {
	
	Global global;
	Console console;
	
	public GameHandler(Global global){
		System.out.println("Creating game handler...");
		this.global = global;
		init();
	}
	
	private void init(){
		System.out.println("Initializing game handler...");
		global.gamestate = GameState.MAIN_MENU;
		console = new Console(global);
	}
	
	public void tick(){
		global.input.tick();
		console.tick();
	}
	
	public void render(Graphics2D g){
		console.render(g);
	}

}
