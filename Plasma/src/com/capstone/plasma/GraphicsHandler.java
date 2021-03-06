package com.capstone.plasma;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;
import static org.lwjgl.opengl.GL11.*;

public class GraphicsHandler {

	private static final int BYTES_PER_PIXEL = 4;
	public static int wall;
	public static int floor;
	public static int longtile; //gotta remove this
	public static int GlowTile;
	public static int breakable;
	public static int itemHolder;
	public static int selecteditemHolder;
	public static int plasmaPistol;
	public static int InventoryBackground;
	
	public static int crack1;
	public static int crack2;
	public static int crack3;
	
	public static int robotRight;
	public static int angryRobotRight;
	public static int angryRobotLeft;
	
	public static int turret;
	public static int turretClosed;
	
	public static int chair;
	
	public static int player;
	public static int player2;
	
	public static int PlasmaTitleLogo;
	
	public static int TNT;
	
	public static int lastTexture=0;
	
	public static int[] fontTexture;
	
	public static SpriteSheet playerSheet;
	
	public static int SelectionCrosshairLeft,SelectionCrosshairRight;
	
	public static void loadTextures(){
		
		SpriteSheet.AnimateSheet animation = new SpriteSheet.AnimateSheet();
		animation.start();
		
		
		
		
		
		wall=loadTexture("images/wall.jpg");
		floor=loadTexture("images/floor.jpg");
		GlowTile=loadTexture("images/longtile.jpg");
		breakable=loadTexture("images/breakable.png");
		itemHolder=loadTexture("images/itemHolder.png");
		selecteditemHolder=loadTexture("images/selecteditemHolder.png");
		plasmaPistol=loadTexture("images/PlasmaPistol.png");
		InventoryBackground=loadTexture("images/InventoryBackground.png");
		
		robotRight = loadTexture("images/robocalm.png");
		angryRobotRight = loadTexture("images/roboangry2.png");
		angryRobotLeft = loadTexture("images/roboangry1.png");
		

		player = GraphicsHandler.loadTexture("images/GuyProfile2.png");
		player2 = GraphicsHandler.loadTexture("images/guy2.png");


		turret = loadTexture("images/turret.png");
		turretClosed = loadTexture("images/turretClosed.png");

		
		crack1=loadTexture("images/crack1.png");
		crack2=loadTexture("images/crack2.png");
		crack3=loadTexture("images/crack3.png");
		
		TNT = loadTexture("images/TNT.png");
		chair = loadTexture("images/chair.png");
		
		
		PlasmaTitleLogo = loadTexture("images/Plasma Title Logo new new.png");
		playerSheet = new SpriteSheet("images/PlayerSpriteSheet.png",new int[]{10},4);
		
		
		PlasmaTitleLogo = loadTexture("images/Plasma Title Logo new new.png");
		
		loadFontPack();

		SelectionCrosshairLeft = loadTexture("images/Selection Crosshair Left.png");
		SelectionCrosshairRight = loadTexture("images/Selection Crosshair Right.png");
		
	}
	
	public static void drawImage(int texture,float x, float y, float width, float height){
		
		//KEEP HERE TO SET COLOR TO WHITE SO RECTANGLE DOES NOT TINT TEXTURE
		glColor4f(1f, 1f, 1f, 1f);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); 
		glEnable(GL_TEXTURE_2D); 
		glPushMatrix();
		glTranslatef(x, y, 0); 
		//glBindTexture(GL_TEXTURE_2D, texture);
		setTexture(texture);
		glBegin(GL_QUADS);
		{
			
          glTexCoord2f(0, 0);
          glVertex2f(0, 0);
          
          glTexCoord2f(1, 0);
          glVertex2f(0, height);
          
          glTexCoord2f(1, 1);
          glVertex2f(width, height);
          
          glTexCoord2f(0, 1);
          glVertex2f(width, 0);
		}
		glEnd();
		glPopMatrix();
		//gluBuild2DMipmaps(GL_TEXTURE_2D);
	}
	
	public static void drawImage(int texture,float x, float y, float width, float height,float rot){
		
		//KEEP HERE TO SET COLOR TO WHITE SO RECTANGLE DOES NOT TINT TEXTURE
		glColor4f(1f, 1f, 1f, 1f);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); 
		glEnable(GL_TEXTURE_2D); 
		glPushMatrix();
		glTranslatef(x, y, 0);
		glTranslatef(x, y, 0); 
		glRotatef(rot, 0, 0, 1);
		//glBindTexture(GL_TEXTURE_2D, texture);
		setTexture(texture);
		glBegin(GL_QUADS);
		{
			
          glTexCoord2f(0, 0);
          glVertex2f(0, 0);
          
          glTexCoord2f(1, 0);
          glVertex2f(0, height);
          
          glTexCoord2f(1, 1);
          glVertex2f(width, height);
          
          glTexCoord2f(0, 1);
          glVertex2f(width, 0);
		}
		glEnd();
		glPopMatrix();
		//gluBuild2DMipmaps(GL_TEXTURE_2D);
	}
	
	public static void setTexture(int texture){
		if(lastTexture == texture)return;
		lastTexture=texture; 
		glBindTexture(GL_TEXTURE_2D, texture);
	}
	
	public static void drawImageOp(float x, float y, float width, float height){

		//KEEP HERE TO SET COLOR TO WHITE SO RECTANGLE DOES NOT TINT TEXTURE
		glColor4f(1f, 1f, 1f, 1f);
		glEnable(GL_BLEND);
		glEnable(GL_TEXTURE_2D); 
		glPushMatrix();
		glTranslatef(x, y, 0); 
		
		glBegin(GL_QUADS);
		{
			
          glTexCoord2f(0, 0);
          glVertex2f(0, 0);
          
          glTexCoord2f(1, 0);
          glVertex2f(0, height);
          
          glTexCoord2f(1, 1);
          glVertex2f(width, height);
          
          glTexCoord2f(0, 1);
          glVertex2f(width, 0);
		}
		glEnd();
		glPopMatrix();
		//gluBuild2DMipmaps(GL_TEXTURE_2D);
	}
	
	
    public static void drawRect(float x, float y, float width, float height, float rot,Color color){
    	glDisable(GL_TEXTURE_2D);
    	glEnable( GL_BLEND );
    	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); 
    	glPushMatrix();
        {
          glTranslatef(x, y, 0); // Shifts the position
          glRotatef(rot, 0, 0, 1);
          
          
          byte red = (byte)(color.getRed()-128);
          byte green = (byte)(color.getGreen()-128);
          byte blue = (byte)(color.getBlue()-128);
          byte alpha = (byte)(color.getAlpha()-128);
          glColor4b(red, green, blue, alpha);
          
          glBegin(GL_QUADS);
          {
             glVertex2f(0, 0);
             glVertex2f(0, height);
             glVertex2f(width, height);
             glVertex2f(width, 0);
          }
          glEnd();
       }
       glPopMatrix();
    //   glColor4f(1f, 0f, 1f, 1f);
    }
    
    
    
    public static void drawEmptyRect(float x, float y, float width, float height, float rot,Color color){
    	glDisable(GL_TEXTURE_2D);
    	glEnable( GL_BLEND );
    	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); 
    	glPushMatrix();
        {
          glTranslatef(x, y, 0); // Shifts the position
          glRotatef(rot, 0, 0, 1);
          
          
          byte red = (byte)(color.getRed()-128);
          byte green = (byte)(color.getGreen()-128);
          byte blue = (byte)(color.getBlue()-128);
          byte alpha = (byte)(color.getAlpha()-128);
          glColor4b(red, green, blue, alpha);
          
          glBegin(GL_LINE_STRIP);
          {
             glVertex2f(0, 0);
             glVertex2f(0, height);
             glVertex2f(width, height);
             glVertex2f(width, 0);
             glVertex2f(0, 0);
          }
          glEnd();
       }
       glPopMatrix();
    //   glColor4f(1f, 0f, 1f, 1f);
    }

    
    public static void drawText(String text,int x,int y,int size){
    	String textMap="! \"#$%&'()*+,-.//0123456789:;<=>?ABCDFEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}";
  //  	System.out.println(textMap.indexOf(text.substring(0,1)));
    	int xOffset=0;
    	for(int i=0;i<text.length();i++){
    		drawImage(fontTexture[textMap.indexOf(text.substring(i,i+1))+32], x+xOffset, y, 25, 25);
    		xOffset+=15;
    	}

    }
    
    public static void drawText(String text,int x,int y,int size,int rot){
    	String textMap="! \"#$%&'()*+,-.//0123456789:;<=>?ABCDFEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}";
  //  	System.out.println(textMap.indexOf(text.substring(0,1)));
    	int xOffset=0;
    	for(int i=0;i<text.length();i++){
    		drawImage(fontTexture[textMap.indexOf(text.substring(i,i+1))+32], x+xOffset, y, 25, 25);
    		xOffset+=15;
    	}

    }

    public static void loadFontPack(){
    	//16 x 16
    	BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/font.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}	
		int textSize=(image.getWidth()/16);
		
		fontTexture=new int[textSize*textSize];
		
		int textureLocation=0;
		
		
		for(int iy=0;iy<textSize;iy++){
			for(int ix=0;ix<textSize;ix++){

				int[] pixels = new int[textSize * textSize];
				
				//for some reason it kept going out of bounds??
				//this is like, really bad programming but it works lol
				if(iy*textSize>=image.getHeight()||ix*textSize>=image.getWidth()){

					continue;
				}
				
		        image.getRGB(ix*textSize, iy*textSize, textSize, textSize, pixels, 0, textSize);
		
		        ByteBuffer buffer = BufferUtils.createByteBuffer(textSize * textSize * BYTES_PER_PIXEL);
		        
		        for(int x = 0; x <textSize; x++){
		        	  for(int y = 0; y < textSize; y++){
		             
		        		  
		                  int pixel = pixels[y * textSize + x];
		                  
		                 
		                  
		                  buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
		                  buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
		                  buffer.put((byte) (pixel & 0xFF));               // Blue component
		                  buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
		              }
		          }
		
		          buffer.flip();
		          
		          int textureID = glGenTextures(); //Generate texture ID
		          glBindTexture(GL_TEXTURE_2D, textureID); //Bind texture ID
		          
		          //Setup wrap mode
		          glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		          glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		
		          //Setup texture scaling filtering
		          glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		          glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		          
		          //Send texel data to OpenGL
		          glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, textSize, textSize, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		          
		          textureLocation++;
		          fontTexture[textureLocation]=textureID;
			}
		}
    }

	public static int loadTexture(String imagePath){
	        
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(imagePath));
		}catch(IOException e) {
			e.printStackTrace();
		}		
	        int[] pixels = new int[image.getWidth() * image.getHeight()];
	          image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
	
	          ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL); //4 for RGBA, 3 for RGB
	          
	          for(int x = 0; x < image.getWidth(); x++){
	        	  for(int y = 0; y < image.getHeight(); y++){
	             
	                  int pixel = pixels[y * image.getWidth() + x];
	                  buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
	                  buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
	                  buffer.put((byte) (pixel & 0xFF));               // Blue component
	                  buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
	              }
	          }
	
	          buffer.flip(); //FOR THE LOVE OF GOD DO NOT FORGET THIS
	
	          // You now have a ByteBuffer filled with the color data of each pixel.
	          // Now just create a texture ID and bind it. Then you can load it using 
	          // whatever OpenGL method you want, for example:
	
	        int textureID = glGenTextures(); //Generate texture ID
	          glBindTexture(GL_TEXTURE_2D, textureID); //Bind texture ID
	          
	          //Setup wrap mode
	          glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
	          glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
	
	          //Setup texture scaling filtering
	          glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	          glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	          
	          //Send texel data to OpenGL
	          glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
	        
	          //Return the texture ID so we can bind it later again
	        return textureID;
	     }
	
	
	
	
}