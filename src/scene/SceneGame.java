package scene;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.BroadCollisionStrategy;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.strategies.BruteCollisionStrategy;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Order;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import business.C;

public class SceneGame extends BasicGameState {

	private static Logger logger = LogManager.getRootLogger();

	Player player;
	World world;
	Body body;
	Body colBody;
	StaticBody wallLeft;
	StaticBody wallRight;
	StaticBody wallTop;
	StaticBody wallBottom;
	private int id;

	private Image currentImage;

	public SceneGame(int id) {
		this.id = id;
		player = new Player();
		// world = new World(new Vector2f(0.0f, 1000f), 10, new
		// QuadSpaceStrategy(20, 5));
		world = new World(new Vector2f(0.0f, C.FORCE_GRAVITY), 20, new BruteCollisionStrategy());
		body = new Body("player", new Box(40, 40), C.PLAYERMASS);
		body.setRotatable(false);
		body.setPosition(250, 200);

		// TODO load from file
		colBody = new StaticBody(new Box(10, 10));
		colBody.setPosition(260, 400);

		
		// Bordercollisions
		int borderWidth = 40;
		int borderOffset = borderWidth / 2;
		wallLeft = new StaticBody(new Box(borderWidth, 2 * C.SCREEN_HEIGHT));
		wallLeft.setPosition(0 - borderOffset, 0 - borderOffset);
		wallRight = new StaticBody(new Box(borderWidth, 2 * C.SCREEN_HEIGHT));
		wallRight.setPosition(C.SCREEN_WIDTH + borderOffset, 0 - borderOffset);
		wallTop = new StaticBody(new Box(2 * C.SCREEN_WIDTH, borderWidth));
		wallTop.setPosition(0 - borderOffset, 0 - borderOffset);
		wallBottom = new StaticBody(new Box(2 * C.SCREEN_WIDTH, borderWidth));
		wallBottom.setPosition(0 - borderOffset, C.SCREEN_HEIGHT + borderOffset);

		// Add to world
		world.add(body);
		world.add(colBody);
		world.add(wallLeft);
		world.add(wallRight);
		world.add(wallTop);
		world.add(wallBottom);

	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		logger.info("Initialisiere SceneGame ID:" + id);
		// TODO Lade alle Frame. Array[]?
		currentImage = new Image(C.IMAGES_PATH + "scene" + id + "_1.png");
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		Input input = container.getInput();
		world.step();

		if (input.isKeyDown(Input.KEY_LEFT)) {
			body.addForce(new Vector2f(-C.FORCE_RIGHTLEFT,0));
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			body.addForce(new Vector2f(C.FORCE_RIGHTLEFT,0));
		} else if (input.isKeyDown(Input.KEY_DOWN)){
			// do nothing
			body.addForce(new Vector2f(0,0));
		} else if (input.isKeyDown(Input.KEY_UP)) {
			body.addForce(new Vector2f(0,-C.FORCE_TOPDOWN));
		} else {
			// do nothing!
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(currentImage, 0, 0);
		g.setColor(Color.white);
		g.fillRect(0, 0, C.SCREEN_WIDTH, C.SCREEN_HEIGHT);

		g.setColor(Color.black);
		drawBody(body, g);
		drawBody(colBody, g);

		// g.drawImage(player.getImage(), 0 + player.posX, 0 + (C.SCREEN_HEIGHT
		// + (player.posY - player.image.getHeight())));
	}

	/**
	 * Only draws bodies with box-shape
	 * @param body
	 * @param g
	 */
	public void drawBody(Body body, Graphics g) {
		Box box = (Box) body.getShape();
		Vector2f[] pts = box.getPoints(body.getPosition(), body.getRotation());

		Vector2f v1 = pts[0];
		Vector2f v2 = pts[1];
		Vector2f v3 = pts[2];
		Vector2f v4 = pts[3];

		g.setColor(Color.black);
		g.drawLine((int) v1.x, (int) v1.y, (int) v2.x, (int) v2.y);
		g.drawLine((int) v2.x, (int) v2.y, (int) v3.x, (int) v3.y);
		g.drawLine((int) v3.x, (int) v3.y, (int) v4.x, (int) v4.y);
		g.drawLine((int) v4.x, (int) v4.y, (int) v1.x, (int) v1.y);
	}

	@Override
	public int getID() {
		return id;
	}

}
