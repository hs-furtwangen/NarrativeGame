package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import business.C;
import business.LogicalPlayer;

public class CoreGame extends BasicGameState {

	private static Logger logger = LogManager.getRootLogger();

	private Map map;
	private Image alphamap;
	private Image artefactesImg;
	private SoundEngine soundEngine;
	private Player player;
	private int n;

	public CoreGame() {
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		
		logger.info("Initializing CoreGame");
		map = new Map(sbg);
		player = new Player(map);
		alphamap = new Image(C.ALPHA_MAP);
		artefactesImg = new Image(C.IMAGES_PATH+"artefact.png");
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
		// TODO scale down
//		g.translate(-C.SCREEN_WIDTH/2, -C.SCREEN_HEIGHT/2);
//		g.scale(2, 2);
//		g.translate(C.SCREEN_WIDTH/8, C.SCREEN_HEIGHT/8);
		
		// render animation
		n = (n + 1) % C.ANIMATIONTEMPO;
		if (n == 0){
			player.incFrame();
		}
		
		// draw map
		g.drawImage(map.getImage(), C.SCREEN_WIDTH / 2 - (C.TILE_WIDTH / 2) - player.posX, C.SCREEN_HEIGHT / 2 - (C.TILE_HEIGHT / 2) - player.posY);

		// draw player in the center
		g.drawImage(player.getImage(), C.SCREEN_WIDTH / 2 - (C.TILE_WIDTH / 2), C.SCREEN_HEIGHT / 2 - (C.TILE_HEIGHT / 2) - C.TILE_HEIGHT);
		
		// TODO automatisch zentrieren
		g.drawImage(alphamap, 0, 0);
		
		// draw artefacts
		boolean[] artefacts = LogicalPlayer.getArtefacts();
		int hOffset = 0;
		for(int i = 0; i < artefacts.length; i++){
			int vOffset = C.TILE_HEIGHT*2;
			if(artefacts[i]){
				vOffset = 0;
			}
			g.drawImage(artefactesImg.getSubImage(hOffset, vOffset, C.TILE_WIDTH*2, C.TILE_HEIGHT*2), hOffset, C.SCREEN_HEIGHT-(C.TILE_HEIGHT*2));
			hOffset += C.TILE_WIDTH*2;
		}
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		logger.info("Enter CoreGame");
		super.enter(container, game);
		soundEngine = SoundEngineHandler.getSoundEngine();
		soundEngine.playWind();
		soundEngine.updatePosition(player.posX, player.posY);
	}
	
	@Override
	public int getID() {
		return 0;
	}

}