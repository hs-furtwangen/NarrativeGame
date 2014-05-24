package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.util.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import business.C;

public class Map {

	private static Logger logger = LogManager.getRootLogger();
	private Image pass_map;
	private Image img_map;
	private StateBasedGame sbg;

	public Map(StateBasedGame sbg) {
		this.sbg = sbg;
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
		
		// Use this bFlag to check all point of being in scenetrigger
		boolean bFlag = true;
		
		logger.debug("Point left top checked. Position: "+x+","+y);
		if (!checkColorsForPixel(x + C.XOFFSET_LEFT, y + C.YOFFSET_TOP)) {
			logger.debug("Point left top failed. Position: "+x+","+y);
			bFlag = false;
		}
		logger.debug("Point right top checked. Position: "+ (x + C.TILE_WIDTH)+","+y);
		if (!checkColorsForPixel(x + C.TILE_WIDTH - C.XOFFSET_RIGHT, y + C.YOFFSET_TOP )) {
			logger.debug("Point right top failed. Position: "+ (x + C.TILE_WIDTH)+","+y);
			bFlag = false;
		}
		logger.debug("Point left button checked. Position: "+x+","+(y + C.TILE_HEIGHT));
		if (!checkColorsForPixel(x + C.XOFFSET_LEFT, y + C.TILE_HEIGHT - C.YOFFSET_BOTTOM)) {
			logger.debug("Point left button failed. Position: "+x+","+(y + C.TILE_HEIGHT));
			bFlag = false;
		}
		logger.debug("Point right button checked. Position: "+(x + C.TILE_WIDTH)+","+(y + C.TILE_HEIGHT));
		if (!checkColorsForPixel(x + C.TILE_WIDTH  - C.XOFFSET_RIGHT, y + C.TILE_HEIGHT - C.YOFFSET_BOTTOM)) {
			logger.debug("Point right button failed. Position: "+(x + C.TILE_WIDTH)+","+(y + C.TILE_HEIGHT));
			bFlag = false;
		}

		return bFlag;

	}

	private boolean checkColorsForPixel(int x, int y) {
		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);
		Color blue = new Color(0, 0, 255);
		Color black = new Color(0, 0, 0);

		boolean bFlag = true;
		try {

			if (compareTwoRGBs(pass_map.getColor(x, y), red)) {
				logger.info("Start scene RED");
				sbg.enterState(1);
				bFlag = false;
			}
			if (compareTwoRGBs(pass_map.getColor(x, y), green)) {
				logger.info("Start scene GREEN");
				sbg.enterState(2);
				bFlag = false;
			}
			if (compareTwoRGBs(pass_map.getColor(x, y), blue)) {
				logger.info("Start scene BLUE");
				sbg.enterState(3);
				bFlag = false;
			}
			if (compareTwoRGBs(pass_map.getColor(x, y), black)) {
				bFlag = false;
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			// pixel out of range
			bFlag = false;
		}

		return bFlag;
	}

	private boolean compareTwoRGBs(org.newdawn.slick.Color cSlick, Color color) {
//		logger.debug("R:"+cSlick.getRed()+" G:"+cSlick.getGreen()+" B:"+cSlick.getBlue()+" | R:"+color.getRed()+" G:"+color.getGreen()+" B:"+color.getBlue());
		if (cSlick.getRed() == color.getRed() && cSlick.getGreen() == color.getGreen() && cSlick.getBlue() == color.getBlue()) {
			return true;
		} else {
			return false;
		}
	}

}
