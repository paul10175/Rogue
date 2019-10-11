package GridTools;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class AdversaryPath extends Component{

	private MyGrid grid; 
	private CellA[][] board;
	private int width, height, endX, endY;
	private List<CellA> open = new ArrayList<CellA>();
	private List<CellA> closed = new ArrayList<CellA>();
	
	public AdversaryPath(GameObject parent, MyGrid grid) {
		super(parent);
		this.grid = grid;
		initialize();
	}
	
	public void graphics() {
		paintPath(height, width, endY, endX);
	}
	
	public void logic() {
		endX = (int)getRandomIntegerBetweenRange(0, width - 1);
		endY = (int)getRandomIntegerBetweenRange(0, height - 1);
		
		aStar(parent.posX, parent.posY, endY, endX);
	}
	
	public double getRandomIntegerBetweenRange(double min, double max){
	    double x = (int)(Math.random()*((max-min)+1))+min;
	    return x;
	}

	public void aStar(int startY, int startX, int endY, int endX) {
		setWeights(endX, endY, startX, startY);
		grid.setColor(startY, startX, Color.red);
		grid.setColor(endY, endX, Color.red);
		open.add(board[startY][startX]);
		
		while (true) {
			int lowestIndex = findLowest(open);
			CellA curr = open.get(lowestIndex);
			grid.setColor(curr.getY(), curr.getX(), Color.blue);
			open.remove(lowestIndex);
			closed.add(curr);
			
			if (curr.getX() == 4 && curr.getY() == 10)
				System.out.println();
			
			if (curr.getX() == endX && curr.getY() == endY) {
				paintPath(startY, startX, endY, endX);
				break;
			}
			
			countNeighbors(curr.getY(), curr.getX());
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	//goes through and paints the path using the parent of each cell in the path 
	public int[] paintPath(int startY, int startX, int endY, int endX) {
		int posx = endX, 
			posy = endY;
		int[] results = new int[2];
		
		while (!(posx == startX && posy== startY)) {
			int temp = posx;
			posx = board[posy][posx].getParentX();
			posy = board[posy][temp].getParentY();
			grid.setColor(posy, posx, Color.blue);
		}
		
		results[0] = posy;
		results[1] = posx;
		
		return results;
	}
	
	//loops through and calculates the distance from the given cell to the end cell
	public void setWeights(int destX, int destY, int sourceX, int sourceY) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				board[i][j].setDistFromDest(destX, destY);
			}
		}
	} 
	
	//goes through and add the neighbors to the open list
	public void countNeighbors(int i, int j) {
		int originalg = board[i][j].getDistFromSource();
		
		//top three blocks above the point
		for (int x = j - 1; x <= j + 1; x++) {
			if (!outOfBounds(i - 1, x) && !open.contains(board[i - 1][x]) && !closed.contains(board[i - 1][x]) && !board[i - 1][x].isBarrier()) {
				open.add(board[i - 1][x]);
				board[i - 1][x].setParent(i, j);
				
				int howMuchToIncr = (x == j) ? 10 : 14;
				board[i - 1][x].setDistFromSource(originalg + howMuchToIncr);
			}
		}
		
		//check the pieces to the left and the right of the point
		if (!outOfBounds(i, j - 1) && !open.contains(board[i][j - 1]) && !closed.contains(board[i][j - 1]) && !board[i][j - 1].isBarrier()) {
			open.add(board[i][j - 1]);
			board[i][j - 1].setParent(i, j);
			board[i][j - 1].setDistFromSource(originalg + 10);
		}
		
		if (!outOfBounds(i, j + 1) && !open.contains(board[i][j + 1]) && !closed.contains(board[i][j + 1]) && !board[i][j + 1].isBarrier()) {
			open.add(board[i][j + 1]);
			board[i][j + 1].setParent(i, j);
			board[i][j + 1].setDistFromSource(originalg + 10);
		}
		
		//check the bottom three blocks under the point
		for (int x = j - 1; x <= j + 1; x++) {
			if (!outOfBounds(i + 1, x) && !open.contains(board[i + 1][x]) && !closed.contains(board[i + 1][x]) && !board[i + 1][x].isBarrier()) {
				open.add(board[i + 1][x]);
				board[i + 1][x].setParent(i, j);
				
				int howMuchToIncr = (x == j) ? 10 : 14;
				board[i + 1][x].setDistFromSource(originalg + howMuchToIncr);
			}
		}
	}
	
	public boolean outOfBounds(int i, int j) {
		return (i < 0 || j < 0 || i >= height || j >= width);
	} 
	
	//finds the smallest f value in the open list
	public int findLowest(List<CellA> list) {
		int min = 100000, index = 0;
		
		for(int i = 0; i < list.size(); i++) {
			if (list.get(i).getF() < min) {
				min = list.get(i).getF();
				index = i;
			} else if (list.get(i).getF() == min) {
				if (list.get(i).getDistFromDest() < list.get(index).getDistFromDest())
					index = i;
			}
		}
		
		return index;
	}
	
	public void initialize() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				board[i][j] = new CellA(j, i);
			}
		}
	}
}