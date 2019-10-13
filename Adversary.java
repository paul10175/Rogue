package engine;

import java.util.ArrayList;

public class Adversary extends GameObject{
	private int width, 
				height;
	public Adversary(int width, int height, MyGrid grid) {
		this.components = new ArrayList<Component>();
		this.posX = width - 1;
		this.posY = 0;
		this.height = height;
		this.width = width;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
