package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import business.C;

public class SoundEngine {

	private static Logger logger = LogManager.getRootLogger();
	private Sound wind;
	private Sound theme;
	private Sound theme2;
	private List<SoundSpot> spots = new ArrayList<SoundSpot>();

	public SoundEngine() {
		logger.info("Initializing SoundEngine");
		loadData();

		try {
			wind = new Sound(C.SOUND_WINDWAV);
			theme = new Sound(C.SOUND_THEME);
			theme2 = new Sound(C.SOUND_THEME2);
		} catch (SlickException e) {
			logger.error(e.getMessage());
		}
	}

	public void playWind() {
		logger.info("Playing Wind");
		theme.stop();
		theme2.stop();

		wind.loop(1.0f, C.VOL_WIND);
	}

	public void playTheme() {
		logger.info("Playing Theme");
		wind.stop();
		theme2.stop();

		theme.loop(1.0f, C.VOL_MUSIC);
	}

	public void playTheme2() {
		logger.info("Playing Theme2");
		wind.stop();
		theme.stop();

		theme2.loop(1.0f, C.VOL_MUSIC);
	}

	public void stopAllEffects() {
		for (SoundSpot spot : spots) {
			spot.getMusic().stop();
		}
	}

	public void loadData() {
		logger.info("Loading SoundSpots");
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			sp.parse(new FileInputStream(new File(C.SOUND_XML)), new SoundSpotHandler());
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage());
		} catch (SAXException e) {
			logger.error(e.getMessage());
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

	}

	public void updatePosition(int x, int y) {
		// TODO neue Volumen und Pitch berechnen
		// logger.info("Update Sounds---------------");
		for (SoundSpot spot : spots) {
			float distance = distance(x, y, spot.getPosX(), spot.getPosY());
			if (distance <= C.MAXDISTANCE) {
				if (!spot.getMusic().playing()) {
					spot.getMusic().loop(1.0f, 0.0f);
				}
				float vol = (((distance / C.MAXDISTANCE) - 1) * -1) * C.VOL_EFFECTS;
				spot.getMusic().fade(C.SOUND_FADETIME, vol, false);
			}
		}
	}

	private class SoundSpotHandler extends DefaultHandler {

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {
			if (qName.equals("spot")) {
				try {
					String src = attr.getValue("src");
					int x = Integer.parseInt(attr.getValue("xPos"));
					int y = Integer.parseInt(attr.getValue("yPos"));

					// logger.info("src=" + src + " x=" + x + " y=" + y);

					SoundSpot spot = new SoundSpot(src, x, y);
					spots.add(spot);
				} catch (NumberFormatException e) {
					logger.error(e.getMessage());
				}
			} else {
				// do nothing
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {

		}
	}

	private float distance(float x1, float y1, float x2, float y2) {
		double dx = x2 - x1;
		double dy = y2 - y1;

		return (float) Math.sqrt(dx * dx + dy * dy);
	}

}
