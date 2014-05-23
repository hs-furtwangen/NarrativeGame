package business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import SceneGame.SceneGame;
import CoreGame.CoreGame;

public class StartGame extends StateBasedGame{
	
	private static Logger logger = LogManager.getRootLogger();

	public StartGame(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		// load Games
		this.addState(new CoreGame());
		this.addState(new SceneGame());
		
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new StartGame("Nara"));
			app.setDisplayMode(C.SCREEN_WIDTH, C.SCREEN_HEIGHT, false);
			app.setTargetFrameRate(C.FPS);
			app.setAlwaysRender(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
