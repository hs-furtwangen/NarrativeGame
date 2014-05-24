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
		logger.info("Loading map");
		try {
			img_map = new Image(C.IMG_MAP);
		} catch (SlickException e) {
			logger.error(e.getMessage());
		}
	}

	public void loadPass() {
		logger.info("Loading logical map");
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
		if (!checkColorsForPixel(x, y))
			return false;
		if (!checkColorsForPixel(x + C.TILE_WIDTH, y))
			return false;
		if (!checkColorsForPixel(x, y + C.TILE_HEIGHT))
			return false;
		if (!checkColorsForPixel(x + C.TILE_WIDTH, y + C.TILE_HEIGHT))
			return false;

		return true;

	}

	private boolean checkColorsForPixel(int x, int y) {
		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);
		Color blue = new Color(0, 0, 255);
		Color black = new Color(0, 0, 0);

		try {

			if (compareTwoRGBs(pass_map.getColor(x, y), red)) {
				// TODO start scene 1
				logger.info("Start scene RED");
				return false;
			} 
			if (compareTwoRGBs(pass_map.getColor(x, y), green)) {
				// TODO start scene 2
				logger.info("Start scene GREEN");
				return false;
			} 
			if (compareTwoRGBs(pass_map.getColor(x, y), blue)) {
				// TODO start scene 3
				logger.info("Start scene BLUE");
				return false;
			} 
			if (compareTwoRGBs(pass_map.getColor(x, y), black)) {
				return false;
			}
			
		} catch (ArrayIndexOutOfBoundsException e) {
			// pixel out of range
			return false;
		}
		
		return true;
	}

	private boolean compareTwoRGBs(org.newdawn.slick.Color cSlick, Color color) {
		if (cSlick.getRed() == color.getRed() && cSlick.getGreen() == color.getGreen() && cSlick.getBlue() == color.getBlue()) {
			return true;
		} else {
			return false;
		}
	}

}
