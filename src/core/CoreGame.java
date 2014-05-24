package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import business.C;

public class CoreGame extends BasicGameState {

	private static Logger logger = LogManager.getRootLogger();

	private Map map;
	private Player player;
	private int n;

	public CoreGame() {
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		logger.info("Initializing CoreGame");
		map = new Map(sbg);
		player = new Player(map);
		n = 0;
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		Input input = container.getInput();

		// Change position on map
		if (input.isKeyDown(Input.KEY_DOWN)) {
			player.moveDown();
		} else if (input.isKeyDown(Input.KEY_UP)) {
			player.moveUp();
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			player.moveLeft();
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			player.moveRight();
		} else {
			// do nothing!
		}

	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		n = (n + 1) % C.ANIMATIONTEMPO;
		if (n == 0){
			player.incFrame();
		}

		// TODO Check Collision!
		// draw map
		g.drawImage(map.getImage(), C.SCREEN_WIDTH / 2 - (C.TILE_WIDTH / 2) - player.posX, C.SCREEN_HEIGHT / 2 - (C.TILE_HEIGHT / 2) - player.posY);

		// draw Player in the center
		g.drawImage(player.getImage(), C.SCREEN_WIDTH / 2 - (C.TILE_WIDTH / 2), C.SCREEN_HEIGHT / 2 - (C.TILE_HEIGHT / 2) - C.TILE_HEIGHT);
	
	}

	@Override
	public int getID() {
		return 0;
	}

}