package scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import business.C;

public class EnvLoader {
	private static Logger logger = LogManager.getRootLogger();
	private int id;
	private List<BoxModell> modells;
	private int startx;
	private int starty;

	public EnvLoader(int id) {
		this.id = id;
		modells = new ArrayList<BoxModell>();
		init();
	}

	public void init() {
		logger.info("Initializing EnvLoader");

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser;
			saxParser = factory.newSAXParser();
			saxParser.parse(C.SCENE_PATH + "scene" + id + ".xml", new SceneLoader());

		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage());
		} catch (SAXException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public List<BoxModell> getBoxes() {
		return modells;
	}

	private class SceneLoader extends DefaultHandler {
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {
			if (qName.equals("box")) {
				try {
					int xPos = Integer.parseInt(attr.getValue("xPos"));
					int yPos = Integer.parseInt(attr.getValue("yPos"));
					int width = Integer.parseInt(attr.getValue("width"));
					int height = Integer.parseInt(attr.getValue("height"));
					modells.add(new BoxModell(xPos, yPos, width, height));
				} catch (NumberFormatException e) {
					logger.error(e.getMessage());
				}

			} else if (qName.equals("porter")) {
				try {
					int xPos = Integer.parseInt(attr.getValue("xPos"));
					int yPos = Integer.parseInt(attr.getValue("yPos"));
					int width = Integer.parseInt(attr.getValue("width"));
					int height = Integer.parseInt(attr.getValue("height"));
					String src = attr.getValue("src");
					modells.add(new Porter(xPos, yPos, width, height, src));
				} catch (NumberFormatException e) {
					logger.error(e.getMessage());
				}

			} else if (qName.equals("start")){
				startx = Integer.parseInt(attr.getValue("xPos"));
				starty = Integer.parseInt(attr.getValue("yPos"));
			}
		}

	}

	public int getStartY() {
		return starty;
	}
	public int getStartX() {
		return startx;
	}

}
