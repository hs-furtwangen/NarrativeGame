package scene;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import business.C;

public class Player {

	private static Logger logger = LogManager.getRootLogger();
	public Image image;
	public int posX;
	public int posY;
	private boolean isRight;
	

	private int frame;

	public Player() {
		posX = 0;
		posY = 0;
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
		return image.getSubImage(hOffset+(C.TILE_WIDTH*(frame)), vOffset, C.TILE_WIDTH, C.TILE_HEIGHT*2);
	}

	public void incFrame(){
		frame = (frame+1)%3;
	}

	public void moveRight(){
			isRight = true;
	}

	public void moveLeft(){
			isRight = false;	
	}
}
