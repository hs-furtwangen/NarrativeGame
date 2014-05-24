package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import business.C;

public class SoundEngine {
	
	private static Logger logger = LogManager.getRootLogger();
	private Sound sound;
	
	public void start(){
		logger.info("Startup SoundEngine");
		try {
			sound = new Sound(C.SOUNDS_PATH+C.SOUND_WIND);
		} catch (SlickException e) {
			logger.error(e.getMessage());
		}
		sound.playAt(1.0f, -100.0f, 0.0f);
	}
	
	public void updatePosition(int x, int y){
		// TODO neue Volumen und Pitch berechnen
		logger.info("Update Sounds");
	}

}
