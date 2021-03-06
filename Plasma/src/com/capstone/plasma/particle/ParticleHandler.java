package com.capstone.plasma.particle;

import java.awt.Color;
import java.util.ArrayList;

import com.capstone.plasma.GameScreen;
import com.capstone.plasma.player.Utilities;

public class ParticleHandler {

	public static ArrayList<Particle> particles = new ArrayList <Particle>();
	
	public static ParticleStream createParticleStream(int x,int y,Color color,int minSpeed,int maxSpeed,boolean running){
		ParticleStream p= new ParticleStream(x,y,color,minSpeed,maxSpeed,running);
		p.start();
		return p;
	}
	
	public static ParticleStream createParticleStream(int x,int y,Color color,int minSpeed,int maxSpeed,boolean running,int duration){
		ParticleStream p= new ParticleStream(x,y,color,minSpeed,maxSpeed,running,duration);
		p.start();
		return p;
	}
	
	public static void createParticle(int x,int y,Color color){
		particles.add(new Particle(x,y,color));
	}
	
	public static class ParticleTick extends Thread{ 
		private volatile boolean isRunning = true;
		public void kill(){
			isRunning=false;
		}
		public void run(){
			this.setName("particle tick");
			DebrisParticle.DebrisParticleTick f = new DebrisParticle.DebrisParticleTick();
			f.start();
			while(isRunning){
				
				try {
					Thread.sleep(5);
			
				
				for(int i=0;i<particles.size();i++){
					Particle p=particles.get(i);
					
					if((!(p==null)) && !(p instanceof DebrisParticle)){
						if(p.alpha>-1 && p.x+GameScreen.xCam<900 && p.x+GameScreen.xCam>-60 || p.backgroundTick){
							if(p.tickCount==p.onTick){
								p.tick();
								p.tickCount=0;
							}	
							p.tickCount++;
						}
						
					}
					
					
				}
				for(int i=0;i<particles.size();i++){
					Particle p=particles.get(i);
					if(!(p==null)){
						if(p.remove){
					//		System.out.println("rem");
							particles.remove(i);
						}
					}
				}
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static class ParticleStream extends Thread{
		public int x,y,durationCount,minSpeed,maxSpeed;
		public int duration = -1;
		public Color color;
		boolean running=true;
		boolean rendering=true;
		public ParticleStream(int x,int y,Color color,int minSpeed,int maxSpeed,boolean running){
			this.x=x;
			this.y=y;
			this.color=color;
			this.minSpeed=minSpeed; 
			this.maxSpeed=maxSpeed;
			this.rendering=running;
			this.setName("Misc Particle");
		}
		public ParticleStream(int x,int y,Color color,int minSpeed,int maxSpeed,boolean running,int duration){
			this.x=x;
			this.y=y;
			this.color=color;
			this.minSpeed=minSpeed; 
			this.maxSpeed=maxSpeed;
			this.rendering=running;
			this.duration=duration;
			this.setName("Particle Stream");
		}
		public void run() {
		    	while(running){
		    		try {
		    			Thread.sleep(Utilities.randInt(minSpeed, maxSpeed));
		    		} catch (InterruptedException e) {
		    			e.printStackTrace();
		    		}
		    		if(x+GameScreen.xCam<900 && x+GameScreen.xCam>-60){
		    			if(rendering)particles.add(new Particle(x+Utilities.randInt(-30, 30),y,color));
		    			if(duration!=-1){
		    				durationCount++;
		    				if(durationCount==duration){
		    					endStream();
		    					return;
		    				}
		    			}
		    		}
		
		    	}
		    }
		public void endStream(){
			running=false;
		}
		public void toggleStream(){
			if(rendering){
				rendering=false;
			}else{
				rendering=true;
			}
		}
	}

	
	public static Color getColorAlpha(Color c,int alpha){
		return new Color(c.getRed(),c.getGreen(),c.getBlue(),alpha);
	}
	
  
	public static void paint(){
		try{
			for(int i=0;i<particles.size();i++){
				Particle p=particles.get(i);
				
				if((!(p==null)) ){
					if(p.alpha>-1 && p.x+GameScreen.xCam<900 && p.x+GameScreen.xCam>-60){
						p.paint();
					}
				}
				
			}
		
		}catch(Exception e){
		//	System.out.println("particle error");
			//return;
			e.printStackTrace();
		}
	}
	
	public static void createExplosion(int x, int y,int distance,int speed,int size,Color color){
		
		for(int i=0;i<size;i++){
			particles.add(new DebrisParticle(x,y,Utilities.randInt(0, 360),speed,speed,distance,color));
			//particles.add(new DebrisParticle(x,y,90,speed,speed,distance,color));
		}
		
		
	}
	



	
	
}
