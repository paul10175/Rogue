package engine;

import java.util.ArrayList;

public class Player extends GameObject {
	
//	int posX, posY;
	MyGrid thisGrid;
	
	// Starting Player in bottom left position
	public Player(MyGrid grid) {
		this.components = new ArrayList<Component>();
		this.thisGrid = grid;
		this.posX = 0;
		this.posY = thisGrid.getScale()-1;
	}
}
