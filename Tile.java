package engine;

import java.util.ArrayList;

public class Tile extends GameObject {
	public Tile(int x, int y) {
		this.components = new ArrayList<Component>();
		this.posX = x;
		this.posY = y;
	}
}
