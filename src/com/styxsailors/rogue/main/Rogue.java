package com.styxsailors.rogue.main;

import java.awt.Color;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;

import com.styxsailors.rogue.handler.GameHandler;
import com.styxsailors.rogue.handler.InputHandler;
import com.styxsailors.rogue.handler.MouseHandler;
import com.styxsailors.rogue.utils.Global;

import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.image.BufferStrategy;


public class Rogue extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	Global global = new Global();
	public static final String NAME = "Rogue";
	public static final int HEIGHT = 320;
	public static final int WIDTH = 320;
	public static final int SCALE = 2;
	private boolean running = false;
	
	static JFrame frame;
	private Font defaultFont = new Font("TimesRoman", Font.PLAIN, 15);
	GameHandler gameHandler;
	
	public void start() {
		running = true;
		new Thread(this).start();
	}

	public void stop() {
		running = false;
	}
	
	private void init() {
		global.input = new InputHandler();
		global.mouse = new MouseHandler(global);
		global.W_HEIGHT = HEIGHT;
		global.W_WIDTH = WIDTH;
		global.W_SCALE  = SCALE;
		addKeyListener(global.input);
		addMouseListener(global.mouse);
		addMouseMotionListener(global.mouse);
		addMouseWheelListener(global.mouse);
		gameHandler = new GameHandler(global);
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int ticks = 0;
		long lastTimer1 = System.currentTimeMillis();

		init();

		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (unprocessed >= 1) {
				ticks++;
				tick();
				unprocessed -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				//System.out.println(ticks + " ticks, " + frames + " fps");
				global.fps = frames;
				frames = 0;
				ticks = 0;
			}
		}
	}
	

	public void tick() {
		gameHandler.tick();		
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		g.setColor(Color.blue);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setFont(defaultFont);
		//g.translate(global.camX, global.camY);

		g.setColor(Color.white);
		gameHandler.render(g);
		//console.render(g);
		
		g.dispose();
		frame.setVisible(true);
		bs.show();
	}

	public static void main(String[] args) {
		Rogue game = new Rogue();
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame(Rogue.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		

		game.start();
	}
	
}