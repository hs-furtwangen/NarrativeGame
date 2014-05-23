package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.util.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import business.C;

public class Map {

	private static Logger logger = LogManager.getRootLogger();
	private Image pass_map;
	private Image img_map;

	public Map() {
		loadPass();
		loadImg();
	}

	public void loadImg() {
		try {
			img_map = new Image(C.IMG_MAP);
		} catch (SlickException e) {
			logger.error(e.getMessage());
		}
	}

	public void loadPass() {
		try {
			pass_map = new Image(C.PASS_MAP);
		} catch (SlickException e) {
			logger.error(e.getMessage());
		}
	}

	public Image getImage() {
		return img_map;
	}

	public boolean isPassable(int x, int y) {
		if(!checkColorsForPixel(x, y))
			return false;
		if(!checkColorsForPixel(x+C.TILE_WIDTH, y))
			return false;
		if(!checkColorsForPixel(x, y+C.TILE_HEIGHT))
			return false;
		if(!checkColorsForPixel(x+C.TILE_WIDTH, y+C.TILE_HEIGHT))
			return false;

		return true;

	}
	
	private boolean checkColorsForPixel(int x, int y){
		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);
		Color blue = new Color(0, 0, 255);
		Color black = new Color(0, 0, 0);
		
		try{
			
		if (compareTwoRGBs(pass_map.getColor(x, y), black)) {
			return false;
		} else if (compareTwoRGBs(pass_map.getColor(x, y), red)) {
			// TODO start scene 1
			logger.info("Starte Scene RED");
			return false;
		} else if (compareTwoRGBs(pass_map.getColor(x, y), green)) {
			// TODO start scene 2
			logger.info("Starte Scene GREEN");
			return false;
		} else if (compareTwoRGBs(pass_map.getColor(x, y), blue)) {
			// TODO start scene 3
			logger.info("Starte Scene BLUE");
			return false;
		} else {
			return true;
		}
		}catch(ArrayIndexOutOfBoundsException e){
			// pixel out of range
			return false;
		}
	}

	private boolean compareTwoRGBs(org.newdawn.slick.Color cSlick, Color color) {
		if (cSlick.getBlue() == color.getBlue() && cSlick.getGreen() == color.getGreen() && cSlick.getBlue() == color.getBlue()) {
			return true;
		} else {
			return false;
		}
	}

}
