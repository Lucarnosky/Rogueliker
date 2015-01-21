package com.styxsailors.rogue.handler;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextureHandler {

	public BufferedImage grabImage(String fileName){
		BufferedImage tmp;
		if(!fileName.equals("null"))
		try {
			tmp = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("texture/"+fileName+".png"));
			return tmp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public BufferedImage grabSubImage(String filename, int row, int col,int width, int height){
		BufferedImage tmp;
		if(!filename.equals("null"))
		try {
			tmp = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("texture/"+filename+".png"));
			tmp.getSubimage(row * width, col * height, width, height);
			return tmp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
