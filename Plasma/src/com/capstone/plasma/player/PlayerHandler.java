package com.capstone.plasma.player;

import java.awt.Color;

import com.capstone.plasma.GameScreen;
import com.capstone.plasma.SpriteSheet;
import com.capstone.plasma.particle.ParticleHandler;

public class PlayerHandler extends Thread{
	
	public static ParticleHandler.ParticleStream playerTrail;
	private volatile boolean isRunning = true;
	
	
	public void run(){
		this.setName("Player Handler");
		playerTrail= ParticleHandler.createParticleStream(Player.x, Player.y,Color.RED,5,10,false);
		try {
			Player.x=GameScreen.map.spawnX;
			Player.y=GameScreen.map.spawnY;
			
			 while (isRunning) {
				Thread.sleep(20);
				Player.tick();
				playerTrail.x=Player.x;
				playerTrail.y=Player.y;
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void kill(){
		isRunning = false;
	}
	
}
