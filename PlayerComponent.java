package engine;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class PlayerComponent extends Component {

	MyGrid thisGrid;
	GameObject player;
	LinkedList<Integer> keyPresses;
	Game game;
	int direction;
	
	public PlayerComponent(GameObject player, MyGrid grid, LinkedList<Integer> keyPresses, Game game) {
		super(player);
		thisGrid = grid;
		this.player = player;
		this.keyPresses = keyPresses;
		this.game = game;
		this.Priority = 2;
		player.priority = Math.max(this.Priority, player.priority);
	}
	
	public void graphics() {
		// Make a red square, move if player moves (?)
		thisGrid.setColor(player.posY, player.posX, Color.RED);
	}
	
	public void logic() {
		if (!keyPresses.isEmpty()) {
			int oldPosX = player.posX;
			int oldPosY = player.posY;
			int code = keyPresses.pollFirst();
			if (code == KeyEvent.VK_W) {
				player.posY--;
				direction = 1;
			} else if (code == KeyEvent.VK_A) {
				player.posX--;
				direction = 7;
			} else if (code == KeyEvent.VK_S) {
				player.posY++;
				direction = 5;
			} else if (code == KeyEvent.VK_D) {
				player.posX++;
				direction = 3;
			} else if  (code == KeyEvent.VK_SPACE) {
				ArrayList<GameObject> gameObjects = (ArrayList<GameObject>) game.gameObjects.clone();
				Bullet bullet = new Bullet(player.posX, player.posY, direction, thisGrid);
				bullet.addComponent(new BulletMovement(bullet, thisGrid, game));
				bullet.addComponent(new Collider(bullet));
				gameObjects.add(bullet);
				game.gameObjects = gameObjects;
			}
			ArrayList<GameObject> collisions = Collider.collidesWith(player.posX, player.posY);
			for (GameObject obj: collisions) {
				if (obj instanceof Obstacle) {
					player.posX = oldPosX;
					player.posY = oldPosY;
					break;
				}
			}
		}
	}
	
}
