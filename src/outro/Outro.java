package outro;




import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import business.C;

public class Outro extends BasicGameState{
	
	private Image logo;
	
	public Outro(){
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		logo = new Image(C.IMAGES_PATH+"logo.png");
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
	Input input = container.getInput();
	
	if(input.isKeyDown(Input.KEY_SPACE)){
		System.exit(1);
	}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		int offset = 40;
		g.scale(C.ZOOMLEVEL, C.ZOOMLEVEL);
		g.drawImage(logo, (C.SCREEN_WIDTH-logo.getWidth())/2, ((C.SCREEN_HEIGHT-logo.getHeight())/2)-offset);
		g.setColor(Color.white);
		g.drawString("GEWONNEN!\nZum Beenden LEERTASTE drücken!", 30, 190);
	}


	@Override
	public int getID() {
		return C.OUTRO;
	}

}
