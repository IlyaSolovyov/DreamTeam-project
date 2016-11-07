package Main;

import javax.swing.*;

//import Objects.TestCanvas;


public class Program {
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
		game.setLayout(null);
		game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		game.setResizable(false);
		game.getCanvas().setBounds(0, 0, 1286, 668);
		game.add(game.getCanvas());
		game.setTitle("GhostHaunt");
		game.setBounds(100, 100, 1286, 668);
		game.setVisible(true);

	}
}
