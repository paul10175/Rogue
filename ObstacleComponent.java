package engine;

import java.awt.Color;

public class ObstacleComponent extends Component {

	MyGrid thisGrid;
	GameObject obstacle;
	
	public ObstacleComponent(GameObject obstacle, MyGrid grid) {
		super(obstacle);
		this.obstacle = obstacle;
		thisGrid = grid;
		this.Priority = 1;
		obstacle.priority = Math.max(this.Priority, obstacle.priority);
	}

	public void graphics() {
		// Set the x,y coordinate of obstacle to green
		thisGrid.setColor(obstacle.posY, obstacle.posX, Color.GREEN);
	}
	
	public void logic() {
		// Obstacle does nothing, just set the appropriate coordinate to green
	}
		
}
