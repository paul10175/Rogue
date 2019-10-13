package engine;
/*
 * Who did what
 * 
 * Jason Kuo: Run.java, GameObject.java, Game.java, InputHandler.java, Collider.java
 * Fritz Wiltman: Player.java, PlayerComponent.java, Obstacle.java, ObstacleComponent.java
 * Paul Hendriksen: Adversary.java, AdversaryPath.java, Bullet.java, BulletMovement.java
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Game {
	
	private int width;
	private int height;
	public MyGrid grid;
	public boolean[][] hasBlock;
	public ArrayList<GameObject> gameObjects;
	public Player player;
	public Adversary adversary;
	private InputHandler input;
	public LinkedList<Integer> keyPresses;
	private GraphicsThread graphics;
	private LogicThread logic;
	
	public Game(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid = new MyGrid(height);
		this.gameObjects = new ArrayList<GameObject>();
		this.input = new InputHandler(this);
		grid.addKeyListener(input);
		this.keyPresses = new LinkedList<Integer>();
		hasBlock = new boolean[grid.getHt()][grid.getWd()];
		grid.setFocusable(true);
		logic = new LogicThread(this);
		graphics = new GraphicsThread(this);
		setInitialGridComponents();
	}
	
	public void runGame() {
		logic.start();
		graphics.start();
	}
	
	private void setInitialGridComponents() {
		
		//Set all tiles to white initially
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Tile temp = new Tile(j,i);
				temp.addComponent(new TileComponent(temp, grid));
				gameObjects.add(temp);
				hasBlock[i][j] = false;

			}
		}
		
		//Create and add the player GameObject
		player = new Player(grid);
		player.addComponent(new PlayerComponent(player, grid, keyPresses, this));
		player.addComponent(new Collider(player));
//		Add Component here------------------------
		
		
		
		
//		---------------------------
		gameObjects.add(player);

		//Create and add the obstacles
		for (int i = 0; i < (int)(0.1f*height*width); i++) {
			int y = (int)(height*Math.random()),
				x = (int)(width*Math.random());
			System.out.println("x is " + x + "and y is " + y);
			Obstacle temp = new Obstacle(x, y);
			temp.addComponent(new ObstacleComponent(temp,grid));
			temp.addComponent(new Collider(temp));
			gameObjects.add(temp);
			hasBlock[y][x] = true;
		}
		
		
		//Create and add the adversary GameObject
		adversary = new Adversary(width, height, grid);
//		adversary.addComponent(new AdversaryPath(adversary, grid, hasBlock));
		adversary.addComponent(new Collider(adversary));
		gameObjects.add(adversary);
		
	}

}

class GraphicsThread extends Thread {
	
	private Game game;
	
	public GraphicsThread(Game game) {
		super();
		this.game = game;
	}
	
	public void run() {
		while (true) {
			ArrayList<GameObject> temp = new ArrayList<GameObject>();
			for (GameObject obj: game.gameObjects) {
				temp.add(obj);
			}
			temp.sort(null);
			for (GameObject obj: temp) {
				obj.graphics();
			}
			game.grid.repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class LogicThread extends Thread {
	
	private Game game;
	
	public LogicThread(Game game) {
		super();
		this.game = game;
	}
	
	public void run() {
		while (true) {
			for (GameObject obj: game.gameObjects) {
				obj.logic();
			}
			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}