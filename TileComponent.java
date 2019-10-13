package engine;

import java.awt.Color;

public class TileComponent extends Component {
	
	private MyGrid grid;

	public TileComponent(Tile parent, MyGrid grid) {
		super(parent);
		this.grid = grid;
		parent.priority = Math.max(this.Priority, parent.priority);
	}
	
	public void graphics() {
		grid.setColor(parent.posY, parent.posX, Color.WHITE);
	}
}
