package Main;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import Objects.*;
import Players.*;

public class Game extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	private boolean running; // Отвечает за запуск или остановку игры
	protected int timeDelay = 5; // Время шага таймера в игре
	protected Canvas canvas; // Холст для карты
	protected Map map;// Карта
	protected Image tileset; // наш тайлсет
	protected Player player;
	protected Player robot;
	protected Player manBlue;
	protected Player manBrown;
	protected Player womanGreen;
	protected Player ghost;

	private KeyAdapter keyListener = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:// влево
				player.moveLeft();
				//robot.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:// вправо
				player.moveRight();
				//robot.moveRight();
				break;
			case KeyEvent.VK_UP:// ввех
				player.moveUp();
				//robot.moveUp();
				break;
			case KeyEvent.VK_DOWN:// вниз
				player.moveDown();
				//robot.moveDown();
				break;
			// движение призрака
			case KeyEvent.VK_A:// влево
				ghost.moveLeft();
				break;
			case KeyEvent.VK_D:// вправо
				ghost.moveRight();
				break;
			case KeyEvent.VK_W:// ввех
				ghost.moveUp();
				break;
			case KeyEvent.VK_S:// вниз
				ghost.moveDown();
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.setDirectionX(0);
			player.setDirectionY(0);

			//robot.setDirectionX(0);
			//robot.setDirectionY(0);

			ghost.setDirectionY(0);
			ghost.setDirectionX(0);
		}
	};

	/**
	 * Отвечает за запуск игры
	 */
	public void start() {
		if (running) {
			return;
		}

		tileset = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/tilesheet.png"));
		running = true;

		map = new Map();
		player = new Catcher(null, new Point(Block.SIZE, Block.SIZE + Block.SIZE))
				.setLookDown("/resources/hitman_down.png").setLookLeft("/resources/hitman_left.png")
				.setLookRight("/resources/hitman_right.png").setLookUp("/resources/hitman_up.png").lookRight();
		/*robot = new Catcher(null, new Point(Block.SIZE, Block.SIZE * 9)).setLookDown("/resources/robot_down.png")
				.setLookLeft("/resources/robot_left.png").setLookRight("/resources/robot_right.png")
				.setLookUp("/resources/robot_up.png").lookRight();*/
		manBlue = new Catcher(null, new Point(Block.SIZE * 18, Block.SIZE)).setLookDown("/resources/manBlue_down.png")
				.setLookLeft("/resources/manBlue_left.png").setLookRight("/resources/manBlue_right.png")
				.setLookUp("/resources/manBlue_up.png").lookDown();
		manBrown = new Catcher(null, new Point(Block.SIZE * 18, Block.SIZE * 8)).setLookDown("/resources/manBrown_down.png")
				.setLookLeft("/resources/manBrown_left.png").setLookRight("/resources/manBrown_right.png")
				.setLookUp("/resources/manBrown_up.png").lookLeft();
		womanGreen = new Catcher(null, new Point(Block.SIZE , Block.SIZE * 8))
				.setLookDown("/resources/womanGreen_down.png").setLookLeft("/resources/womanGreen_left.png")
				.setLookRight("/resources/womanGreen_right.png").setLookUp("/resources/womanGreen_up.png").lookUp();
		ghost = new Ghost(null, new Point(Block.SIZE * 9, Block.SIZE * 4)).setLookDown("/resources/ghost_down.png")
				.setLookLeft("/resources/ghost_left.png").setLookRight("/resources/ghost_right.png")
				.setLookUp("/resources/ghost_up.png").lookUp();
		canvas = new Canvas();
		Point point = new Point(0, 0);
		for (int i = 1; i <= Map.layerQUANTITY; i++) {
			for (point.y = 0; point.y < map.getHeight(i); point.y++) {
				for (point.x = 0; point.x < map.getWidth(i); point.x++) {
					if (map.getTileId(point.x, point.y, i) != 0) {
						canvas.addRender(new Block(tileset, map.getTileId(point.x, point.y, i), point.x * Block.SIZE,
								point.y * Block.SIZE));
					}
				}
			}
		}
		canvas.addRender(player);
		//canvas.addRender(robot);
		canvas.addRender(ghost);
		setListener();

		new Thread(this).start();
	}

	private void setListener() {
		canvas.setFocusable(true);
		canvas.addKeyListener(keyListener);
	}

	/**
	 * Остановка игры
	 */
	public void stop() {
		unSetListener();
		running = false;
	}

	private void unSetListener() {
		canvas.setFocusable(false);// убираем фокус
		canvas.removeKeyListener(keyListener);
	}

	/**
	 * Получить ссылку на холст игры
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	@Override
	public void run() {
		while (running) {
			try {
				TimeUnit.MILLISECONDS.sleep(timeDelay);
			} catch (InterruptedException ex) {
				Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
			}
			update();
		}
	}

	protected void update() {
		if (player.isMoved() && accessMove(player)) {
			player.setPoint(player.getDirectionX() * player.getSpeed(), player.getDirectionY() * player.getSpeed());
		}
		/*if (robot.isMoved() && accessMove(robot)) {
			robot.setPoint(robot.getDirectionX() * robot.getSpeed(), robot.getDirectionY() * robot.getSpeed());
		}*/
		if (ghost.isMoved() && accessMove(ghost)) {
			ghost.setPoint(ghost.getDirectionX() * ghost.getSpeed(), ghost.getDirectionY() * ghost.getSpeed());
		}
		canvas.repaint();
	}

	private boolean accessMove(Player person) {
		int left, right, top, down;
		boolean isWalkable = true;
		// верх и низ
		// Находим вероятные точки плиток с учетом направления directionX
		left = (int) Math.ceil(person.getPointX() / Block.SIZE);
		right = (int) Math.floor((person.getPointX() + person.getWidth() - 1) / Block.SIZE);
		top = (int) Math.ceil((person.getPointY() + person.getSpeed() * person.getDirectionY()) / Block.SIZE);
		down = (int) Math.floor(
				(person.getPointY() + person.getHeight() + person.getSpeed() * person.getDirectionY()) / Block.SIZE);
		if (person.getDirectionY() == -1 && !(tileIsWalkable(left, top) && tileIsWalkable(right, top))) {
			isWalkable = false;
		} else if (person.getDirectionY() == 1 && !(tileIsWalkable(left, down) && tileIsWalkable(right, down))) {
			isWalkable = false;
		}

		// право и лево
		// Находим вероятные точки плиток с учетом направления directionY
		left = (int) Math.ceil((person.getPointX() + person.getSpeed() * person.getDirectionX()) / Block.SIZE);
		right = (int) Math.floor(
				(person.getPointX() + person.getWidth() + person.getSpeed() * person.getDirectionX() - 1) / Block.SIZE);
		top = (int) Math.ceil(person.getPointY() / Block.SIZE);
		down = (int) Math.floor((person.getPointY() + person.getHeight() - 1) / Block.SIZE);
		if (person.getDirectionX() == -1 && !(tileIsWalkable(left, top) && tileIsWalkable(left, down))) {
			isWalkable = false;
		} else if (person.getDirectionX() == 1 && !(tileIsWalkable(right, top) && tileIsWalkable(right, down))) {
			isWalkable = false;
		}
		return isWalkable; // заглушка
	}

	protected boolean tileIsWalkable(int x, int y) {
		return (map.getTileId(x, y, 2) == 0);
	}

}
