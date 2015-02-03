package com.styxsailors.rogue.handler;

import java.awt.Color;
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
		global.console = new Console(global);
		global.camera = new Camera(global);
		changeState(GameState.EDITOR);
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
			if(levelHandler == null)
				levelHandler = new LevelHandler(global);
			levelHandler.tick();
			break;
		case EDITOR:
			if(global.editor == null)
				global.editor = new EditorHandler(global);
			global.editor.tick();
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
			case EDITOR:
				g.translate(global.camX, global.camY);
				global.editor.render(g);
				break;
			default:
				break;
		}
		g.setColor(Color.white);
		global.console.render(g);
	}
	
	public void changeState(GameState state){
		global.gamestate = state;
	}

}
