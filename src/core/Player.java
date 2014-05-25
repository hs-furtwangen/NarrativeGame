package core;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import business.C;

public class Player {

	private static Logger logger = LogManager.getRootLogger();
	private Map map;
	private Image image;
	public int posX;
	public int posY;
	private boolean isRight;
	private boolean isUp;
	private int frame;

	public Player(Map map) {
		isRight = true;
		isUp = false;
		frame = 0;
		this.map = map;
		posX = C.STARTPOSX;
		posY = C.STARTPOSY;
		try {
			image = new Image(C.IMAGES_PATH+"char.png");
		} catch (SlickException e) {
			logger.error(e.getMessage());
		}
	}

	public Image getImage() {
		int hOffset = 0;
		int vOffset = 0;
		if(isRight)
			hOffset+=48;
		if(isUp)
			vOffset+=32;
		return image.getSubImage(hOffset+(C.TILE_WIDTH*(frame)), vOffset, C.TILE_WIDTH, C.TILE_HEIGHT*2);
	}
	
	public void moveDown(){
		if(map.isPassable(posX,posY+C.STEPWIDTH)){
			isUp = false;
			posY += C.STEPWIDTH;
			updatePosition();
		}
	}
	
	public void moveUp(){
		if(map.isPassable(posX,posY-C.STEPWIDTH)){
			isUp = true;
			posY -= C.STEPWIDTH;
			updatePosition();
		}
	}
	
	public void moveRight(){
		if(map.isPassable(posX+C.STEPWIDTH,posY)){
			isRight = true;
			isUp = false;
			posX += C.STEPWIDTH;
			updatePosition();
		}
	}

	public void moveLeft(){
		if(map.isPassable(posX-C.STEPWIDTH,posY)){
			isRight = false;
			isUp = false;
			posX -= C.STEPWIDTH;
			updatePosition();
		}
	}
	
	private void updatePosition(){
		SoundEngineHandler.getSoundEngine().updatePosition(posX, posY);
	}
	
	public void incFrame(){
		frame = (frame+1)%3;
	}


	
}
