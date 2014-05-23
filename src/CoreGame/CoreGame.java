package CoreGame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import business.C;

public class CoreGame extends BasicGameState{
	
	private static Logger logger = LogManager.getRootLogger();
	
	Player player;
	
	public CoreGame(){
		player = new Player();
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		Input input = container.getInput();
		
		// Change position on map
		if(input.isKeyDown(Input.KEY_DOWN)){
			player.posY += 1;
		}else if(input.isKeyDown(Input.KEY_UP)){
			player.posY -= 1;
		}else if(input.isKeyDown(Input.KEY_LEFT)){
			player.posX -= 1;
		}else if(input.isKeyDown(Input.KEY_RIGHT)){
			player.posX += 1;
		}else{
			// do nothing!
		}
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		// draw map
		
		
		// draw Player in the center
		g.drawImage(player.getImage(), C.SCREEN_WIDTH/2 - (C.TILE_WIDTH/2), C.SCREEN_HEIGHT/2 - (C.TILE_HEIGHT/2));
	}


	@Override
	public int getID() {
		return 0;
	}


	
}