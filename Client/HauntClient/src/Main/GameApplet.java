package Main;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import Levels.*;
import Mechanic.Battery;
import Menue.*;
import Menue.STATES;
import Objects.*;
import Players.*;

public class GameApplet extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Graphics graphic;
	protected Image img;
	protected Thread thread;
	private boolean running; // Отвечает за запуск или остановку игры
	protected int timeDelay = 5; // Время шага таймера в игре
	protected ILevel level;// Уровень
	public static int WIDTH = 1286;
	public static int HEIGHT = 668;

	private static STATES state = STATES.PLAY;

	private Player player;
	private ArrayList<Player> companions;
	private Player manBlue;
	private Player manBrown;
	private Player womanGreen;
	private Player ghost;


	private Battery battery;

	private Menue menue;
	public static Point coordMouse;

	public void init() {

		this.resize(WIDTH, HEIGHT);
		running = true;
		createGame();

		this.setBounds(0, 0, 1280, 704);
		this.img = createImage(getWidth(), getHeight());
		this.graphic = img.getGraphics();
		coordMouse = new Point(0, 0);
		// this.menue = new Menue().createMainMenue();
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.thread = new Thread(this);
		this.thread.start();
	}

	public void paint(Graphics graphic) {
		if (state.equals(STATES.MENUE)) {
			menue.drawMainMenue(this.graphic);
			graphic.drawImage(img, 0, 0, null);
			this.graphic.clearRect(0, 0, getWidth(), getHeight());
			this.graphic.setColor(Color.orange);
			this.graphic.fillRect(0, 0, getWidth(), getHeight());
		}
		if (state.equals(STATES.PLAY)) {
			this.graphic.clearRect(0, 0, getWidth(), getHeight());
			this.graphic.setColor(Color.BLACK);
			this.graphic.fillRect(0, 0, getWidth(), getHeight());
			if (player.isMoved() && accessMove(player)) {
				player.setPoint(player.getDirectionX() * player.getSpeed(), player.getDirectionY() * player.getSpeed());

			}
			IsPlayerCatched(player);
			for (Player companion : companions) {
				if (companion.isMoved() && accessMove(companion)) {
					companion.setPoint(companion.getDirectionX() * companion.getSpeed(),
							companion.getDirectionY() * companion.getSpeed());

				}
				IsPlayerCatched(companion);
			}
			if (ghost.isMoved() && accessMove(ghost)) {
				ghost.setPoint(ghost.getDirectionX() * ghost.getSpeed(), ghost.getDirectionY() * ghost.getSpeed());
			}
			level.paintLevel(this.graphic);
			battery.render(this.graphic, this);
			graphic.drawImage(img, 0, 0, null);
			// дальше все для отладки
			
			
			// рисуем здоровье призрака
			graphic.setColor(Color.RED);
			graphic.setFont(new Font("Consolas", Font.BOLD, 30));
			Integer temp = ghost.GetHealth();
			graphic.drawString(temp.toString(), Block.SIZE+20, (int) (Block.SIZE/2+10));
			
			graphic.setColor(Color.RED);
			graphic.setFont(new Font("Consolas", Font.BOLD, 20));
			graphic.drawString("Жизнь призрака", Block.SIZE/2-10, (int) (Block.SIZE-50 ));
			
			
			// ИГРОКИ
			//1
			
			temp = player.GetHealth();
			graphic.setFont(new Font("Consolas", Font.BOLD, 18));
			graphic.drawString("игрок 1 ", Block.SIZE * 5+60, Block.SIZE-50);
			if (temp>0)
				graphic.drawString("Жив", Block.SIZE * 6 - 50, Block.SIZE-20);
			else graphic.drawString("Мертв", Block.SIZE * 6 - 50, Block.SIZE-20);
			temp = player.getLaternCharge();
			graphic.setColor(Color.YELLOW);
			
			graphic.drawString("Фонарик:"+temp.toString()+"%", Block.SIZE * 4+130, Block.SIZE-20);
			
			//2
			graphic.setFont(new Font("Consolas", Font.BOLD, 20));
			graphic.setColor(Color.RED);
			temp = manBlue.GetHealth();
			graphic.drawString("игрок 2 ", Block.SIZE * 9+40, Block.SIZE-50);
			if (temp>0)
				graphic.drawString("Жив", Block.SIZE * 9 - 40, Block.SIZE-20);
			else graphic.drawString("Мертв", Block.SIZE * 9 - 40, Block.SIZE-20);
			temp = manBlue.getLaternCharge();
			graphic.setColor(Color.YELLOW);
			graphic.drawString("Фонарик:"+temp.toString()+"%", Block.SIZE * 8+100, Block.SIZE-20);
			
			
			//3
			graphic.setColor(Color.RED);
			temp = womanGreen.GetHealth();
			graphic.drawString("игрок 3 ", Block.SIZE * 13+40, Block.SIZE-50);
			if (temp>0)
				graphic.drawString("Жив", Block.SIZE * 13 - 40, Block.SIZE-20);
			else graphic.drawString("Мертв", Block.SIZE * 13 - 40, Block.SIZE-20);
			temp = womanGreen.getLaternCharge();
			graphic.setColor(Color.YELLOW);
			graphic.drawString("Фонарик:"+temp.toString()+"%", Block.SIZE * 12+100, Block.SIZE-20);
			
			
			//4
			graphic.setColor(Color.RED);
			temp = manBrown.GetHealth();
			graphic.drawString("игрок 4 ", Block.SIZE * 17+40, Block.SIZE-50);
			if (temp>0)
				graphic.drawString("Жив", Block.SIZE * 17 - 40, Block.SIZE-20);
			else graphic.drawString("Мертв", Block.SIZE * 17 - 40, Block.SIZE-20);
			temp = manBrown.getLaternCharge();
			graphic.setColor(Color.YELLOW);
			graphic.drawString("Фонарик:"+temp.toString()+"%", Block.SIZE * 16+100, Block.SIZE-20);
			
			
			/*
			Integer i = 1;
			for (Player companion : companions) {
				temp = companion.GetHealth();
				graphic.drawString("Игрок(" + i.toString() + ")" + temp.toString(), Block.SIZE * (i + i * 3 + 5)+40,
						(int) (Block.SIZE /4));
				i++;
			}*/
			// заряд фонарика
			
		}
	}

	private boolean IsPlayerCatched(Player companion) {
		int a = 10;
		if ((ghost.getPointX() + a >= companion.getPointX() + a - (companion.getWidth() - 2 * a)
				&& ghost.getPointX() + a <= companion.getPointX() + a + (companion.getWidth() - 2 * a))
				&& (ghost.getPointY() + a >= companion.getPointY() + a - (companion.getHeight() - 2 * a)
						&& ghost.getPointY() + a <= companion.getPointY() + a + (companion.getHeight() - 2 * a))) {
			int currentHealth = companion.GetHealth();
			if (currentHealth > 0) {
				companion.SetHealth(currentHealth - 1);
				return true;
			} else
				return false;
		}
		return false;
	}

	public void update(Graphics graphic) {
		paint(graphic);
	}

	public void repaint(Graphics graphic) {
		paint(graphic);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:// влево
			player.moveLeft();
			for (Player companion : companions) {
				companion.lookLeft();
			}
			break;
		case KeyEvent.VK_RIGHT:// вправо
			player.moveRight();
			for (Player companion : companions) {
				companion.lookRight();
			}
			break;
		case KeyEvent.VK_UP:// ввех
			player.moveUp();
			for (Player companion : companions) {
				companion.lookUp();
			}
			break;
		case KeyEvent.VK_DOWN:// вниз
			player.moveDown();
			for (Player companion : companions) {
				companion.lookDown();
			}
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
		case KeyEvent.VK_SPACE:// включаем фонарик
			if (player.getLaternCharge()>0 && player.GetHealth() > 0)
			{
				player.turnLatern(true);
				player.setLaternCharge(player.getLaternCharge()-1);
				player.ghostShined(ghost);//призрак попал на свет
			}
			else player.turnLatern(false); //реализация уменьшения энергии
			break;
		case KeyEvent.VK_E:// хватаем ловца

			for (Player companion : companions) {
				if (IsPlayerCatched(companion)) {
					int life = companion.GetHealth();
					companion.SetHealth(life - 1000);
				}
			}

			if (IsPlayerCatched(player)) {
				int life = player.GetHealth();
				player.SetHealth(life - 1000);
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.setDirectionX(0);
		player.setDirectionY(0);

		ghost.setDirectionY(0);
		ghost.setDirectionX(0);

		player.turnLatern(false);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while (running) {
			try {
				TimeUnit.MILLISECONDS.sleep(timeDelay);
			} catch (InterruptedException ex) {
				Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
			}
			this.repaint();

		}
	}

	private boolean accessMove(Player person) {
		int left, right, top, down;
		boolean isWalkable = true;
		// верх и низ
		// Находим вероятные точки плиток с учетом направления directionX
		int a = 10;
		left = (int) Math.ceil(person.getPointX() + a / Block.SIZE);
		right = (int) Math.floor((person.getPointX() + a + person.getWidth() - 2 * a - 1) / Block.SIZE);
		top = (int) Math.ceil((person.getPointY() + a + person.getSpeed() * person.getDirectionY()) / Block.SIZE);
		down = (int) Math.floor(
				(person.getPointY() + a + person.getHeight() - 2 * a + person.getSpeed() * person.getDirectionY())
						/ Block.SIZE);
		if (person.getDirectionY() == -1 && !(tileIsWalkable(left, top) && tileIsWalkable(right, top))) {
			isWalkable = false;
		} else if (person.getDirectionY() == 1 && !(tileIsWalkable(left, down) && tileIsWalkable(right, down))) {
			isWalkable = false;
		}

		// право и лево
		// Находим вероятные точки плиток с учетом направления directionY
		left = (int) Math.ceil((person.getPointX() + a + person.getSpeed() * person.getDirectionX()) / Block.SIZE);
		right = (int) Math.floor(
				(person.getPointX() + a + person.getWidth() - 2 * a + person.getSpeed() * person.getDirectionX() - 1)
						/ Block.SIZE);
		top = (int) Math.ceil((person.getPointY() + a) / Block.SIZE);
		down = (int) Math.floor((person.getPointY() + a + person.getHeight() - 2 * a - 1) / Block.SIZE);
		if (person.getDirectionX() == -1 && !(tileIsWalkable(left, top) && tileIsWalkable(left, down))) {
			isWalkable = false;
		} else if (person.getDirectionX() == 1 && !(tileIsWalkable(right, top) && tileIsWalkable(right, down))) {
			isWalkable = false;
		}
		return isWalkable;
	}

	protected boolean tileIsWalkable(int x, int y) {
		return (level.getLetId(x, y) == 0);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && state != STATES.PLAY) {
			switch (menue.getButtonStatus()) {
			case START:
				state = STATES.PLAY;
				createGame();
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (coordMouse == null) {
			coordMouse = new Point(e.getX(), e.getY());
		} else {
			coordMouse.move(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (coordMouse == null) {
			coordMouse = new Point(e.getX(), e.getY());
		} else {
			coordMouse.move(e.getX(), e.getY());
		}
	}

	private void createGame() {

		player = new Catcher(null, new Point(Block.SIZE, Block.SIZE + Block.SIZE))
				.setLookDown("/resources/hitman_down.png").setLookLeft("/resources/hitman_left.png")
				.setLookRight("/resources/hitman_right.png").setLookUp("/resources/hitman_up.png").lookRight();
		
		companions = new ArrayList<Player>();
		manBlue = new Catcher(null, new Point(Block.SIZE * 18, Block.SIZE * 2))
				.setLookDown("/resources/manBlue_down.png").setLookLeft("/resources/manBlue_left.png")
				.setLookRight("/resources/manBlue_right.png").setLookUp("/resources/manBlue_up.png").lookDown();
		manBrown = new Catcher(null, new Point(Block.SIZE * 18, Block.SIZE * 9))
				.setLookDown("/resources/manBrown_down.png").setLookLeft("/resources/manBrown_left.png")
				.setLookRight("/resources/manBrown_right.png").setLookUp("/resources/manBrown_up.png").lookLeft();
		womanGreen = new Catcher(null, new Point(Block.SIZE, Block.SIZE * 9))
				.setLookDown("/resources/womanGreen_down.png").setLookLeft("/resources/womanGreen_left.png")
				.setLookRight("/resources/womanGreen_right.png").setLookUp("/resources/womanGreen_up.png").lookUp();
		
		companions.add(manBlue);
		companions.add(manBrown);
		companions.add(womanGreen);

		/*
		 * level = new
		 * LevelFirst("/resources/tilesheet.png").addLayerFirst().addLayerSecond
		 * ().addLayerThird() .addLayerHUD().addPlayer(player);
		 */
		level = new LevelSecond("/resources/tilesheet.png").addLayerFirst().addLayerSecond().addLayerThird()
				.addLayerHUD().addPlayer(player);

		for (Player companion : companions) {
			level.addPlayer(companion);
		}
		ghost = new Ghost(null, new Point(Block.SIZE * 3, Block.SIZE * 2)).setLookDown("/resources/ghost_down.png")
				.setLookLeft("/resources/ghost_left.png").setLookRight("/resources/ghost_right.png")
				.setLookUp("/resources/ghost_up.png").lookUp();
		level.addPlayer(ghost);

		this.battery = new Battery(256, 128);
	}

	/*
	 * private void deleteGame() { player = null; companions.clear(); ghost =
	 * null; level = null; }
	 */

	public static STATES getStatus() {
		return state;
	}
}
