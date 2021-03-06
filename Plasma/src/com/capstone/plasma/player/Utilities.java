package com.capstone.plasma.player;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.capstone.plasma.GameScreen;
import com.capstone.plasma.mob.Mob;
import com.capstone.plasma.tiles.Tile;

public class Utilities {
	
	public static int randInt(int min, int max) {
		return (new Random()).nextInt((max - min) + 1) + min;
	}
	
	public static Tile touchBoundsTile(int x, int y,int xn,int yn, int size){
		Rectangle r=  new Rectangle(x+GameScreen.xCam+xn,y+GameScreen.yCam+yn,size,size);
		//looping
		for(int i=0;i<GameScreen.map.tiles.size();i++){
			Tile s = GameScreen.map.tiles.get(i);
			if((s.collide) && r.intersects(s.getBounds())){
				return s;
			}
		}
		return null;
	}
	public static void toString(ArrayList<Tile> t){
		for(int i =0; i<t.size();i++){
			System.out.print("("+t.get(i).x+ " "+ t.get(i).y+") ");
		}
	}
    public static String getInputPop(String message){
    	JFrame frame = new JFrame();
        String result = JOptionPane.showInputDialog(frame, message);
        return result;
        //System.out.println(result);
    }
	
	public static Tile touchBoundsTile(int x, int y,int xn,int yn, int sizeX,int sizeY){
		try{
		Rectangle r=  new Rectangle(x+GameScreen.xCam+xn,y+GameScreen.yCam+yn,sizeX,sizeY);
		//looping
		for(int i=0;i<GameScreen.map.tiles.size();i++){
			Tile s = GameScreen.map.tiles.get(i);
			if((s.collide) && r.intersects(s.getBounds())){
				return s;
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Touch bounds error");
		}
		return null;
	}
	
	
	public static boolean touchMobs(int x, int y,int xn,int yn,int size){
		try{
			Rectangle r=  new Rectangle(x+xn,y+yn,size,size);
			//looping
			for(int i=0;i<GameScreen.map.mobs.size();i++){
				Tile s = GameScreen.map.tiles.get(i);
				if(r.intersects(s.getBounds())  && (s.collide)){
					return true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	

	public static boolean touchPlayer(int x, int y, int size){
		//System.out.println("robx range"+(x)+" "+Player.x);
		Rectangle r = new Rectangle(x,y,size,size);
		//if((x)>Player.x && (x)<(Player.x+Tile.size)){
		Rectangle pl = new Rectangle(Player.x,Player.y,Player.size, Player.size);
		if(r.intersects(pl)){
			return true;
		}
		return false;
	}
	
	public static boolean touchBounds(int x, int y,int xn,int yn,int size){
		try{
			Rectangle r=  new Rectangle(x+GameScreen.xCam+xn,y+GameScreen.yCam+yn,size,size);
			//looping
			for(int i=0;i<GameScreen.map.tiles.size();i++){
				Tile s = GameScreen.map.tiles.get(i);
				
				if((s!=null||r!=null) &&  r.intersects(s.getBounds())  && (s.collide)){
					return true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean touchBoundsMobs(int x, int y,int xn,int yn,int size,Mob you){
		try{
			Rectangle r=  new Rectangle(x+GameScreen.xCam+xn,y+GameScreen.yCam+yn,size,size);
			//looping
	
			for(int i=0;i<GameScreen.map.mobs.size();i++){
				Mob m = GameScreen.map.mobs.get(i);
				if(r.intersects(m.getBounds()) &&m!=you){
					return true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public static Mob touchBoundReturnMobs(int x, int y,int xn,int yn,int size,Mob you){
		try{
			Rectangle r=  new Rectangle(x+GameScreen.xCam+xn,y+GameScreen.yCam+yn,size,size);
			//looping
	
			for(int i=0;i<GameScreen.map.mobs.size();i++){
				Mob m = GameScreen.map.mobs.get(i);
				if(r.intersects(m.getBounds()) &&m!=you){
					return m;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
