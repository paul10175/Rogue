package engine;
//You may need to import game object based on your package structure 

public class Component {
	
	public GameObject parent = null; 
	public boolean active = true;
	public int Priority = 0 ;
	
	
	public Component(GameObject object){
		parent = object;
	}
	
	public void graphics() {
		
	}
	
	public void logic() {
		
	}
}
