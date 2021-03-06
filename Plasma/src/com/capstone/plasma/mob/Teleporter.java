package com.capstone.plasma.mob;

import java.awt.Color;

import com.capstone.plasma.GameScreen;
import com.capstone.plasma.GraphicsHandler;
import com.capstone.plasma.Settings;
import com.capstone.plasma.mapmaker.Map;
import com.capstone.plasma.player.Player;
import com.capstone.plasma.player.Utilities;

public class Teleporter extends Mob {
	
	public Teleporter(int x, int y) {
		super(x, y);
	}
	
	public void paint(){
		GraphicsHandler.drawRect(x+GameScreen.xCam, y+GameScreen.yCam, size, size*2, 0, Color.PINK);
		//GraphicsHandler.drawRect(x, y, size, size*2, 0, Color.PINK);
	}
	
	public void death(){
		
	}
	
	public void tick(){
		if(Utilities.touchPlayer(x, y, size)){
	    	for(int  b=0; b<GameScreen.map.mobs.size();b++){
	    		GameScreen.map.mobs.get(b).death();
	    		Player.kills--;
	    	}
			System.out.println("teleporter away!");
			//GameScreen.map=Map.load("map1.ser");
			Player.level++;
			String map = Settings.world+"/level"+Player.level;
			//System.out.println()
			GameScreen.map=Map.load(map+".ser");
			Player.x = GameScreen.map.spawnX;
			Player.y = GameScreen.map.spawnY;
			GameScreen.xCam = 0;
			GameScreen.yCam = 105;
	        for(int i =0; i<GameScreen.map.mobs.size();i ++){
	        	GameScreen.map.mobs.get(i).run();
	        }
		}
	}
}