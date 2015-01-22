package com.styxsailors.rogue.handler;

import java.awt.Graphics2D;

import com.styxsailors.rogue.screen.Camera;
import com.styxsailors.rogue.utils.Console;
import com.styxsailors.rogue.utils.Global;
import com.styxsailors.rogue.utils.Global.GameState;

public class GameHandler {
	
	Global global;
	LevelHandler levelHandler;
	
	public GameHandler(Global global){
		System.out.println("Creating game handler...");
		this.global = global;
		init();
	}
	
	private void init(){
		System.out.println("Initializing game handler...");
		
		global.gamestate = GameState.PLAY;
		global.console = new Console(global);
		global.camera = new Camera(global);
		levelHandler = new LevelHandler(global);
	}
	
	public void tick(){
		global.input.tick();
		global.console.tick();
		switch(global.gamestate){
		case MAIN_MENU:
			break;
		case PAUSE:
			break;
		case PLAY:
			levelHandler.tick();
			break;
		default:
			break;
		
		}
	}
	
	public void render(Graphics2D g){
		
		switch(global.gamestate){
		case MAIN_MENU:
			break;
		case PAUSE:
			break;
		case PLAY:
			g.translate(global.camX, global.camY);
			levelHandler.render(g);
			break;
		default:
			break;
		
		}
		global.console.render(g);
	}

}
