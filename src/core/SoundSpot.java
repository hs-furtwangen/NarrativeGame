package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import business.C;

public class SoundSpot {

	private static Logger logger = LogManager.getRootLogger();
	private int posX;
	private int posY;
	private Music music;
	private String src;

	public SoundSpot(String src, int x, int y) {
//		logger.info("New SoundSpot loaded:\nsrc: " + src + " x: " + x + " y: " + y);
		posX = x;
		posY = y;
		this.src = src;
		try {
			music = new Music(C.SOUNDS_PATH + src);
			music.loop(1.0f, 0.0f);
		} catch (SlickException e) {
			logger.error(e.getMessage());
		}
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Music getMusic() {
		return music;
	}

	public String getSrc() {
		return src;
	}

}
