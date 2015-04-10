package business;

import intro.Intro;

import java.io.PrintStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import outro.Outro;
import scene.SceneGame;
import core.CoreGame;

public class StartGame extends StateBasedGame {

	private static Logger logger = LogManager.getRootLogger();

	public StartGame(String name) {
		super(name);

	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		// load Games
		this.addState(new Intro());
		this.addState(new CoreGame());
		this.addState(new SceneGame(C.SCENERED)); // red
		this.addState(new SceneGame(C.SCENEGREEN)); // green
		this.addState(new SceneGame(C.SCENEBLUE)); // blue
		this.addState(new Outro());

	}

	public static void main(String[] args) {
		System.out.println("Starting");
		logger.info("Starting up application");
		PrintStream lwjglStream = new PrintStream(System.out) {
			public void print(final String message) {
				logger.info(message);
			}
		};

		System.setOut(lwjglStream);
		System.setErr(lwjglStream);
		logger.info("Initializing LogicalPlayer");
		LogicalPlayer.init();

		try {
			AppGameContainer app = new AppGameContainer(new StartGame(
					"Black Forest Ghost"));
			app.setDisplayMode((int) (C.SCREEN_WIDTH * C.ZOOMLEVEL),
					(int) (C.SCREEN_HEIGHT * C.ZOOMLEVEL), false);
			app.setTargetFrameRate(C.FPS);
			app.setAlwaysRender(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
