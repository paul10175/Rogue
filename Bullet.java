package GridTools;

public class Bullet extends GameObject{

	protected int direction; 
	
	public Bullet(int startX, int startY, int lastDirection, MyGrid grid) {
		this.posX = startX;
		this.posY = startY;
		this.direction = lastDirection;
	}
	
	public int getDirection() {
		return direction;
	}
}
