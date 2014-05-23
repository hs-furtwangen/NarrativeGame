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
		isUp = true;
		frame = 0;
		this.map = map;
		posX = 150;
		posY = 150;
		try {
			image = new Image(C.PATH+"char.png");
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
		if(map.isPassable(posX,posY+1)){
			isUp = false;
			posY += C.STEPWIDTH;
		}
	}
	
	public void moveUp(){
		if(map.isPassable(posX,posY-1)){
			isUp = true;
			posY -= C.STEPWIDTH;
		}
	}
	
	public void moveRight(){
		if(map.isPassable(posX+1,posY)){
			isRight = true;
			isUp = false;
			posX += C.STEPWIDTH;
		}
	}

	public void moveLeft(){
		if(map.isPassable(posX-1,posY)){
			isRight = false;
			isUp = false;
			posX -= C.STEPWIDTH;
		}
	}
	
	public void incFrame(){
		frame = (frame+1)%3;
	}


	
}
