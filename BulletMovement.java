package engine;

import java.awt.Color;
import java.util.ArrayList;

public class BulletMovement extends Component{
	
	private MyGrid grid;
	private int lastDirection;
	Game game;
	
	public BulletMovement(Bullet parent, MyGrid grid, Game game) {
		super(parent);
		lastDirection = parent.getDirection();
		this.grid = grid;
		this.game = game;
	}
	
	public void graphics() {
		grid.setColor(parent.posY, parent.posX, Color.BLUE);
	}
	
	public void logic() {
		if (lastDirection == 1) {
			parent.posY--;
		} else if (lastDirection == 2) {
			parent.posX++;
			parent.posY--;
		} else if (lastDirection == 3) {
			parent.posX++;
		} else if (lastDirection == 4) {
			parent.posX++;
			parent.posY++;
		} else if (lastDirection == 5) {
			parent.posY++;
		} else if (lastDirection == 6) {
			parent.posY++;
			parent.posX--;
		} else if (lastDirection == 7) {
			parent.posX--;
		} else {
			parent.posX--;
			parent.posY--;
		}
		if (outOfBounds()) {
			destroyBullet();
			return;
		}
		ArrayList<GameObject> collisions = Collider.collidesWith(parent.posX, parent.posY);
		for (GameObject obj: collisions) {
			if (obj instanceof Obstacle) {
				destroyBullet();
				return;
			}
		}
	}
	
	public void destroyBullet() {
		ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects = (ArrayList<GameObject>) game.gameObjects.clone();
		gameObjects.remove(parent);
		game.gameObjects = gameObjects;
	}
	
	public boolean outOfBounds() {
		return (parent.posX < 0 || parent.posY < 0 || parent.posX >= grid.getWd() || parent.posY >= grid.getHt());
	}
}
