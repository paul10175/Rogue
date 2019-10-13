package engine;

import java.util.ArrayList;

public class Collider extends Component {
	
	public static ArrayList<Collider> colliders;

	public Collider(GameObject object) {
		super(object);
		if (colliders == null) colliders = new ArrayList<Collider>();
		colliders.add(this);
	}
	
	public static ArrayList<GameObject> collidesWith(int x, int y) {
		ArrayList<GameObject> collisions = new ArrayList<GameObject>();
		for (Collider collider: colliders) {
			if (collider.parent.posX == x && collider.parent.posY == y) {
				collisions.add(collider.parent);
			}
		}
		return collisions;
	}

}
