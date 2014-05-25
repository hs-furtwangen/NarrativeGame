package scene;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import business.C;

public class Porter extends BoxModell {
	private static Logger logger = LogManager.getRootLogger();
	private Image image;

	public Porter(int x, int y, int width, int height, String src) {
		super(x, y, width, height, false);
		
		try {
			image = new Image(C.IMAGES_PATH+src);
		} catch (SlickException e) {
			logger.error(e.getMessage());
		}
	}
	
	public Image getImage(){
		return image;
	}
	
}
