package scene;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import business.C;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.shapes.Box;

public class BoxModell {

	private static Logger logger = LogManager.getRootLogger();
	protected Body body;

	public BoxModell(int x, int y, int width, int height) {
		this(x,y,width,height,true);
	}

	protected BoxModell(int x, int y, int width, int height, boolean stat) {
		if (stat) {
			body = new StaticBody(new Box(width, height));
		} else {
			body = new Body(new Box(width, height), C.ARTEFACT_MASS);
		}
		body.setPosition(x, y);
	}

	public Body getBody() {
		return body;
	}

}
