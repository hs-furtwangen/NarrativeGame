package scene;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.CollisionListener;
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

import business.C;
import business.LogicalPlayer;
import core.SoundEngine;
import core.SoundEngineHandler;

public class SceneGame extends BasicGameState implements CollisionListener{

	private static Logger logger = LogManager.getRootLogger();

	private Player player;
	private World world;
	private Body body;
	private Porter porter;
	private Body wallLeft;
	private Body wallRight;
	private Body wallTop;
	private Body wallBottom;
	private SoundEngine soundEngine;
	private StateBasedGame maingame;
	private EnvLoader env;
	private int n = 0;
	private int id;

	private Image currentImage;

	public SceneGame(int id) {
		logger.info("Initializing CoreGame");
		this.id = id;
		// lade datei
		env = new EnvLoader(id);
		player = new Player();
		world = new World(new Vector2f(0.0f, C.FORCE_GRAVITY), 20, new BruteCollisionStrategy());
		world.addListener(this);
		body = new Body("player", new Box(player.getImage().getWidth(), player.getImage().getHeight()), C.PLAYERMASS);
		body.setRotatable(false);
		body.setPosition(env.getStartX(), env.getStartY());
		body.setMaxVelocity(C.MAX_X_VELOCITY, C.MAX_Y_VELOCITY);


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

		// lade boxes
		for (BoxModell boxmodell : env.getBoxes()) {
			world.add(boxmodell.getBody());
		}

		world.add(wallLeft);
		world.add(wallRight);
		world.add(wallTop);
		world.add(wallBottom);

	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		logger.info("Initialisiere SceneGame ID:" + id);
		currentImage = new Image(C.IMAGES_PATH + "scene"+id+".png");
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
		if (n == 0) {
			player.incFrame();
		}
		// draw background
		g.drawImage(currentImage, 0, 0);

		// draw boxes
		for (BoxModell boxmodell : env.getBoxes()) {
			if (boxmodell instanceof Porter && LogicalPlayer.getArtefacts()[id]==false) {
				// TODO set everytime? Performance!
				porter = (Porter) boxmodell;
				drawPorter((Porter)boxmodell, g);
			} else {
				drawBody(boxmodell.getBody(), g);
			}
		}

		// draw player
		drawPlayer(body, g);
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
	
	public void drawPorter(Porter porter, Graphics g){
		Body porterBody = porter.getBody();
		Box box = (Box) porterBody.getShape();
		Vector2f[] pts = box.getPoints(porter.getBody().getPosition(), porter.getBody().getRotation());

		Vector2f v1 = pts[0];
		Vector2f v2 = pts[1];
		Vector2f v3 = pts[2];
		Vector2f v4 = pts[3];

		g.setColor(Color.black);
		g.drawImage(porter.getImage(), v1.x, v1.y);
	}

	public void drawPlayer(Body body, Graphics g) {
		Image subimage = player.getImage();
		g.drawImage(subimage, body.getPosition().getX() - subimage.getWidth() / 2, body.getPosition().getY() - subimage.getHeight() / 2);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		logger.info("Enter DungeonGame");
		super.enter(container, game);
		soundEngine = SoundEngineHandler.getSoundEngine();
		soundEngine.playTheme();
		body.setPosition(env.getStartX(), env.getStartY());
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public void collisionOccured(CollisionEvent e) {
		if(e.getBodyA()==body && e.getBodyB()==porter.getBody() || e.getBodyB()==body && e.getBodyA()==porter.getBody()){
			logger.info("Player hits Porter in Dungeon "+id);
			
			// Entferne Artefact TODO Ich brauche den Porter um wieder auf dem Dungeon zu kommen!
			LogicalPlayer.archievedArtefact(id);
//			world.remove(porter.getBody()); 
//			env.getBoxes().remove(porter);
			maingame.enterState(0);
		}
	}

}
