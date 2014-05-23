package core;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import business.C;

public class Player {

	private static Logger logger = LogManager.getRootLogger();
	private Image image;
	public int posX;
	public int posY;

	public Player() {
		posX = 0;
		posY = 0;
		try {
			image = new Image(C.PATH+"player_topdown.png");
		} catch (SlickException e) {
			logger.error(e.getMessage());
		}
	}

	public Image getImage() {
		return image;
	}



	
}
