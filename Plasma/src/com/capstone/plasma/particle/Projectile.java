package com.capstone.plasma.particle;

import java.awt.Color;

import com.capstone.plasma.GameScreen;
import com.capstone.plasma.GraphicsHandler;
import com.capstone.plasma.tiles.Tile;

public class Projectile extends Particle{
	public int maxRange = 500;
	public int initX = 0;
	//public int maxSpeed = 6;
	public int speed = 1;

	public Projectile(int x, int y) {
		super(x, y, null);
		
		initX=x;
		onTick=3;
		
	}
	
	public void paint(){
		//GraphicsHandler.drawImage(item.texture, x+GameScreen.xCam, y+GameScreen.yCam+up, Tile.size, Tile.size);
		GraphicsHandler.drawRect(x+GameScreen.xCam, y+GameScreen.yCam, 20, 10, 0, Color.CYAN);
	}
	
	public void tick(){
		
		if(Math.abs(initX-x)<maxRange){
			x+=speed;
			speed++;
		}else{
			remove=true;
		}
		
		
	}

}