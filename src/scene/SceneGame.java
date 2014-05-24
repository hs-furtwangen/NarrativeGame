package scene;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.strategies.BruteCollisionStrategy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import business.C;
import core.SoundEngine;
import core.SoundEngineHandler;
import business.LogicalPlayer;

public class SceneGame extends BasicGameState {

	private static Logger logger = LogManager.getRootLogger();

	Player player;
	World world;
	Body body;
	StaticBody wallLeft;
	StaticBody wallRight;
	StaticBody wallTop;
	StaticBody wallBottom;
	private int id;
	private Body colliders[];
	private ArrayList<Box> boxes;
	private ArrayList<Float> xPosis;
	private ArrayList<Float> yPosis;
	private SoundEngine soundEngine;
	private int n = 0;

	private Image currentImage;

	public SceneGame(int id) {
		this.id = id;
		player = new Player();
		world = new World(new Vector2f(0.0f, C.FORCE_GRAVITY), 20, new BruteCollisionStrategy());
		body = new Body("player", new Box(16, 32), C.PLAYERMASS);
		body.setRotatable(false);
		body.setPosition(320, 30);
		boxes = new ArrayList<Box>();
		xPosis = new ArrayList<Float>();
		yPosis = new ArrayList<Float>();

		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				private float xPos;
				private float yPos;
				private float width;
				private float height;

				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
					if (qName.equals("box")) {
						xPos = Integer.parseInt(attributes.getValue("xPos"));
						yPos = Integer.parseInt(attributes.getValue("yPos"));
						width = Integer.parseInt(attributes.getValue("width"));
						height = Integer.parseInt(attributes.getValue("height"));

						boxes.add(new Box(width, height));
						xPosis.add(xPos);
						yPosis.add(yPos);

					}

				}

				public void endElement(String uri, String localName, String qName) throws SAXException {

				}

			};

			saxParser.parse("ressources/scenes/scene1.xml", handler);

		} catch (Exception e) {
			logger.info("XMLHandling went wrong.");
			logger.error(e.getMessage());
		}

		colliders = new StaticBody[boxes.size()];

		for (int i = 0; i < boxes.size(); i++) {
			colliders[i] = new StaticBody(boxes.get(i));
			colliders[i].setPosition(xPosis.get(i), yPosis.get(i));

		}

		// Bordercollisions
		int borderWidth = 40;
		int borderOffset = (borderWidth / 2) + 1;
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

		for (int k = 0; k < colliders.length; k++) {
			world.add(colliders[k]);
		}

		world.add(wallLeft);
		world.add(wallRight);
		world.add(wallTop);
		world.add(wallBottom);

	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		logger.info("Initialisiere SceneGame ID:" + id);
		currentImage = new Image(C.IMAGES_PATH + "scene1.png");
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		LogicalPlayer.archievedArtefact(id);
		Input input = container.getInput();
		world.step();

		if (input.isKeyDown(Input.KEY_LEFT)) {
			body.addForce(new Vector2f(-C.FORCE_RIGHTLEFT, 0));
			player.moveLeft();
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			body.addForce(new Vector2f(C.FORCE_RIGHTLEFT, 0));
			player.moveRight();
		} else if (input.isKeyDown(Input.KEY_DOWN)) {
			// do nothing
			body.addForce(new Vector2f(0, 0));
		} else if (input.isKeyPressed(Input.KEY_UP)) {
			if (body.getVelocity().getY() <= 0.000001f) {
				body.addForce(new Vector2f(0, -C.FORCE_TOPDOWN));
			}
		} else {
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		n = (n + 1) % C.ANIMATIONTEMPO;
		if (n == 0){
			player.incFrame();
		}
		g.drawImage(currentImage, 0, 0);
		drawPlayer(body, g);
		for (int l = 0; l < colliders.length; l++) {
			drawBody(colliders[l], g);
		}
	}

	/**
	 * Only draws bodies with box-shape
	 * 
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
		g.fillRect(v1.x, v1.y, v2.x - v1.x, v3.y - v1.y);
	}

	public void drawPlayer(Body body, Graphics g) {
		Box box = (Box) body.getShape();
//		g.drawImage(player.image, body.getPosition().getX() - player.image.getWidth() / 2, body.getPosition().getY() - player.image.getHeight() / 2);

		Image subimage = player.getImage();
		g.drawImage(subimage, body.getPosition().getX() - subimage.getWidth() / 2, body.getPosition().getY() - subimage.getHeight() / 2);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		logger.info("Enter DungeonGame");
		super.enter(container, game);
		soundEngine = SoundEngineHandler.getSoundEngine();
		soundEngine.playTheme();
	}

	@Override
	public int getID() {
		return id;
	}

}
