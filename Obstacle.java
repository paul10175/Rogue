package engine;

import java.util.ArrayList;

public class Obstacle extends GameObject {
		
	// Randomize the location of Obstacle when creating list of GameObjects
	public Obstacle(int x, int y) {
		this.components = new ArrayList<Component>();
		this.posX = x;
		this.posY = y;
	}
}
