package com.capstone.plasma.mob;

import com.capstone.plasma.GameScreen;
import com.capstone.plasma.GraphicsHandler;
import com.capstone.plasma.player.Player;
import com.capstone.plasma.player.Utilities;
import com.capstone.plasma.tiles.Tile;

public class Robot extends Mob {
	
	public int lowSpeed = 2;
	public int highSpeed = 3;
	public int speed = lowSpeed;
	
	
	public int jumpHeight = 20; //was 20
	public boolean jump=false;
	public long startTime = 10;
	public long endTime;
	public boolean seeking = false;
	public int viewRange = 250;
	public int action = 30;
	
	public int size = Tile.size;
	public int paintSize = size+10;
	public boolean aniStage = true;
	int num=1;
	int count = 0;
	public int hit = 100;
	public int knockBack =15;
	public boolean dead = false;
	public boolean attack = true;
	public int attackTick = 10;
	//public int x; 
	//public int y;
	
	
	public Robot(int x, int y,int num) {
		super(x, y);
		this.num = num;
		maxHp=300;
		hp=maxHp;
	}
	
	public Robot(int x, int y) {
		super(x, y);
	}
	

	public void tick(){
		attack();
		
		if(faceRight){
			speed = Math.abs(speed);
			lowSpeed = Math.abs(lowSpeed);
			highSpeed = Math.abs(highSpeed);
		}else{
			speed = (-1)*Math.abs(speed);
			lowSpeed = (-1)*Math.abs(lowSpeed);
			highSpeed = (-1)*Math.abs(highSpeed);
		}
		
		
		gravity();
		action();
		//startTime = System.nanoTime();
	}
	

	
	public void action(){
		if(attackTick >0 &&!attack){
			attackTick-=1;
		}else{
			attackTick = 10;
			attack = true;
		}
	//	y-=10;
		endTime = System.nanoTime();
		seek();
		if(!(seeking)){
			speed = lowSpeed;
			
			if(!(seeking) && Math.abs(startTime-endTime)>900000000){ //1000000000 is 1 second
				action = (int)(Math.random()*100);
				startTime = System.nanoTime();
				
				
			}
			if(action<60){
				move();
			}else if(action>85){
				
				faceRight ^= true;
				action = (int)(Math.random()*100);
			}
		}else{
			speed = highSpeed;
			move();
		}
	}
	
	public void attack(){
		if(Utilities.touchPlayer(x, y, size)){
			boolean throwUp = false;
			for(int i = 0; i<GameScreen.map.mobs.size(); i++){
				if(Math.abs(x-GameScreen.map.mobs.get(i).x)<=100 && GameScreen.map.mobs.get(i)!=this){
					throwUp = true;
					Player.throwBack(0, knockBack-(knockBack/2), true);
					break;
				}
			}
			if(faceRight && !throwUp){
				Player.throwBack(knockBack,knockBack-(knockBack/2), false);
			}else if(!throwUp){
				Player.throwBack(knockBack,knockBack-(knockBack/2), true);
			}
			if(attack){
				attack = false;
				Player.damage(hit);
			}
		}
			
		//if(x-GameScreen.xCam)
	}
	
	//this basically sets up the robots field of vision and tells it to move.
	public void seek(){
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
		if(seeking){
			if(Player.x<=x+viewRange && Player.x >=x){
				faceRight = true;
			}else if(Player.x >=x-viewRange && Player.x<=x){
				faceRight = false;
			}else{
				seeking = false;
			}
		}else{
		
		// i know this is not effecient. I'll fix it.
			
		if(faceRight && ((Player.x-x))<viewRange &&Player.x>x){
			seeking = true;
		}else if(!(faceRight) && Math.abs((Player.x-x))<viewRange && Player.x<x){
			seeking = true;
		}else{
			seeking = false;
		}
	}
	}
	//left of with jumping
	public void move(){
		if(!(Utilities.touchBounds(x, y, speed, -1,size))){
			
			if(Utilities.touchBoundsMobs(x, y, speed, -1,size,this)){
				x-=speed;
				jump();
			}
			
			if( !(Player.x-Tile.size < x && Player.x+Tile.size > x) ){
				x+=speed;
				}
			
		}else{
			jump();
		}

	}
	
	public void jump(){
		if(onGround){
			yVelocity-=jumpHeight;
		}
		
	}

	public void paint(){
		
		//maxHP
	//	if(!dead){
		
		paintHealthBar();
		
		if(seeking){
			//seeking
			//GraphicsHandler.drawRect(x+GameScreen.xCam, y+GameScreen.yCam, size, size, 0, Color.pink);
			if(aniStage){
				GraphicsHandler.drawImage(GraphicsHandler.angryRobotRight, x+GameScreen.xCam, y+GameScreen.yCam+(size-paintSize), paintSize, paintSize);
			}else{
				GraphicsHandler.drawImage(GraphicsHandler.angryRobotLeft, x+GameScreen.xCam, y+GameScreen.yCam+(size-paintSize), paintSize, paintSize);
			}
			//aniStage ^= true;
		}else{
			if(faceRight){
				//facing right
				GraphicsHandler.drawImage(GraphicsHandler.robotRight, x+GameScreen.xCam, y+GameScreen.yCam+(size-paintSize), paintSize, paintSize);
				
			}else{
				// facing left
				//GraphicsHandler.drawRect(x+GameScreen.xCam, y+GameScreen.yCam, size, size, 0, Color.GREEN);
				GraphicsHandler.drawImage(GraphicsHandler.robotRight, x+GameScreen.xCam, y+GameScreen.yCam+(size-paintSize), paintSize, paintSize);
			}
			
		}
	}
			//GraphicsHandler.drawText("rob"+num, x+GameScreen.xCam, y+GameScreen.yCam);
		
//	}
	
	

	
}