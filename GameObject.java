package engine;

import java.util.ArrayList;

public abstract class GameObject implements Comparable<GameObject> {
	
	protected ArrayList<Component> components;
	protected int posX,posY;
	protected int priority;
	
	public void graphics() {
		for (Component component: components) {
			component.graphics();
		}
	}
	
	public void logic() {
		for (Component component: components) {
			component.logic();
		}
	}
	
	public int compareTo(GameObject o) {
		return Integer.compare(this.priority, o.priority);
	}
	
	public void addComponent(Component component) {
		components.add(component);
	}
	
	public void removeComponent(Class<Component> type) {
		components.removeIf(comp -> (comp.getClass() == type));
	}

	public Component getComponent(Class<Component> type) {
		for (Component comp: components) {
			if (comp.getClass() == type)
				return comp;
		}
		return null;
	}
}
