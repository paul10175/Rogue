package GridTools;

import java.awt.Color;

public class BulletMovement extends Component{
	
	private MyGrid grid;
	private int lastDirection;
	
	public BulletMovement(Bullet parent, MyGrid grid) {
		super(parent);
		lastDirection = parent.getDirection();
		this.grid = grid;
	}
	
	public void graphics() {
		
	}
	
	public void logic() {
		while (!outOfBounds()) {
			int oldPosX = parent.posX, 
				oldPosY = parent.posY;
			
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
			
			grid.setColor(oldPosY, oldPosX, Color.white);
			
			grid.setColor(parent.posY, parent.posX, Color.blue);
		}
	}
	
	public boolean outOfBounds() {
		return (parent.posX < 0 || parent.posY < 0 || parent.posX > MyGrid.WIDTH || parent.posY > MyGrid.HEIGHT);
	}
}
