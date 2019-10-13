package engine;

import java.util.ArrayList;

public class Bullet extends GameObject{

	protected int direction; 
	
	public Bullet(int startX, int startY, int lastDirection, MyGrid grid) {
		this.components = new ArrayList<Component>();
		this.posX = startX;
		this.posY = startY;
		this.direction = lastDirection;
	}
	
	public int getDirection() {
		return direction;
	}
}
