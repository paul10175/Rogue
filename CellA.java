package engine;

public class CellA {
	private int x, 
				y,  
				parentx, 
				parenty,
				distFromDest,
				distFromSource;
			
	private boolean hasSeen = false, 
					finished = false, 
					isBarrier = false;

	
	public CellA(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getParentX() {
		return parentx;
	}
	
	public int getParentY() {
		return parenty;
	}
	
	public boolean isBarrier() {
		return isBarrier;
	}
	
	public int getF() {
		return distFromSource + distFromDest;
	}
	
	public void setBarrier(boolean set) {
		isBarrier = set;
	}
		
	public void setSeen() {
		this.hasSeen = true;
	}
	
	
	public boolean getHasSeen() {
		return hasSeen;
	}
	
	
	public void setFinished() {
		finished = true;
	}
	
	
	public boolean isFinished() {
		return finished;
	}
	
	
	public int getDistFromDest() {
		return distFromDest;
	}
	
	
	public int getDistFromSource() {
		return distFromSource;
	}
	
	public void setParent(int i, int j) {
		this.parenty = i;
		this.parentx = j;
	}
	
	
	public void setDistFromSource(int incr) {
		this.distFromSource = incr;
	}


	public void setDistFromDest(int destX, int destY) {
		this.distFromDest = calculateDistance(destX, destY);
	}

	/*
	 * if destx == x we know it is a straight line then just return the distance * 10
	 * if desty == y we have the same scenario so we can just mulitply out
	 */
	public int calculateDistance(int destX, int destY) {
		if (destX == x)
			return 10 * Math.abs(destY - y);
		else if (destY == y)
			return 10 * Math.abs(destX - x);
		else {
			//since we know it is a diagnol line we use the slope to find the shortest distance to the end node
			double slope = (double)(destY - y) / (double)(destX - x);
			
			if (Math.abs(slope) == 1)
				return 14 * Math.abs(destX - x);
			else if (slope > 1)
				return (14 * Math.abs(destX - x)) + (10 * (Math.abs(destY - y) - Math.abs(destX - x)));
			else 
				return (14 * Math.abs(destY - y)) + (10 * (Math.abs(destX - x) - Math.abs(destY - y)));
		}
	}
	
}

