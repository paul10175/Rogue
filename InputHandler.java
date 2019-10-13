package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	
	private Game game;
	
	public InputHandler(Game game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		game.keyPresses.addLast(code);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
