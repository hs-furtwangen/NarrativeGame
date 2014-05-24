package core;

import java.awt.Image;

public abstract class WorldObj {
	private boolean isPassable;
	@SuppressWarnings("unused")
	private Image image;
	
	public WorldObj(){
		
	}

	public boolean isPassable() {
		return isPassable;
	}

	public void setPassable(boolean isPassable) {
		this.isPassable = isPassable;
	}
	
	public abstract Image getImage();
	
	
}
