package scene;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Curve;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import business.C;


public class SceneGame extends BasicGameState{

	Player player;
	private boolean jumpflag = false;
	private Vector2f p1;
	private Vector2f p2;
	private Vector2f p3;
	private Vector2f p4;
	private Curve jumpcurve;
	private int i = 0;
	private boolean isJumping;
	private boolean walkedLeft = false;
	private boolean walkedRight = true;
	
	
	public SceneGame(){
		player =new Player();
		p1 = new Vector2f(player.posX, player.posY);
		p2 = new Vector2f(player.posX+20, player.posY-C.JUMP_HEIGHT);
		p3 = new Vector2f(player.posX+35, player.posY-C.JUMP_HEIGHT);
		p4 = new Vector2f(player.posX+50, player.posY);
		jumpcurve = new Curve(p1,p2,p3,p4);
		isJumping = false;
	}
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,Graphics g) throws SlickException {
		g.drawImage(new Image(C.PATH+"rainforest.png"), 0, 0);
		g.drawImage(player.getImage(), 0+player.posX,0+(C.SCREEN_HEIGHT+(player.posY-player.image.getHeight())));
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		Input input = container.getInput();
		
		if(input.isKeyDown(Input.KEY_UP )&& !isJumping){
			//for(int i=0; i<jumpcurve.getPointCount(); i++){
				isJumping = true;
				if(walkedRight){
					p1 = new Vector2f(player.posX, player.posY);
					p2 = new Vector2f(player.posX+20, player.posY-C.JUMP_HEIGHT);
					p3 = new Vector2f(player.posX+35, player.posY-C.JUMP_HEIGHT);
					p4 = new Vector2f(player.posX+50, player.posY);
					jumpcurve = new Curve(p1,p2,p3,p4);
				}else if(walkedLeft){
					p1 = new Vector2f(player.posX, player.posY);
					p2 = new Vector2f(player.posX-20, player.posY-C.JUMP_HEIGHT);
					p3 = new Vector2f(player.posX-35, player.posY-C.JUMP_HEIGHT);
					p4 = new Vector2f(player.posX-50, player.posY);
					jumpcurve = new Curve(p1,p2,p3,p4);
				}
			}
		if(isJumping){
		if(i <= 20){
			player.posX = (int) jumpcurve.pointAt(0.05f*i).getX();			
			player.posY = (int) jumpcurve.pointAt(0.05f*i).getY();
			i++;}
		else{i = 0;
	
			isJumping = false;}
			//}
			// do nothing!
			
		}else if(input.isKeyDown(Input.KEY_LEFT)){
			if(!(player.posX-5 < 0)){
			player.posX -= 5;
			walkedLeft = true;
			walkedRight = false;}
			else{
				// do nothing!
			}
		}
		else if(input.isKeyDown(Input.KEY_RIGHT)){
			if(!(player.posX+5 > C.SCREEN_WIDTH-player.image.getWidth())){
			player.posX += 5;
			walkedLeft = false;
			walkedRight = true;}
			else{			
				// do nothing!
			}
		}else{
			// do nothing!
		}
	}

	@Override
	public int getID() {
		return 1;
	}

}
