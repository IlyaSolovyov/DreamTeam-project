package Players;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import Mechanic.*;

enum playerType {
	Player1, Player2, Player3, Player4, Ghost
} // Тип игрока

public abstract class Player implements Movement, Appearence {
	protected playerType plType;
	protected int health; // Healthbar
	protected boolean isDead; // Мёртв ли игрок
	protected boolean isMovableThrough; // Может ли он проходить через других
										// игроков

	protected Image image; // Изображение персонажа
	protected Point pos; // Текущая позиция игрока

	/**
	 * Указывает на передвижение персонажа по оси X. x + 1 - движение вправо, x
	 * - 1 - движение влево, 0 - нет движения. y + 1 - движение вниз, y - 1 -
	 * движение вверх, 0 - нет движения.
	 */
	protected Point direction;
	protected int speed = 1;// 5; // Скорость персонажа перемещения по карте.
	// Размеры персонажа
	protected int width = 49;
	protected int height = 43;

	public Player(String name, Point point) {
		if (name != null)
			this.image = new ImageIcon(getClass().getResource(name)).getImage();
		this.pos = new Point(point);
		this.direction = new Point(0, 0);
	}

	public abstract int GetHealth();

	public abstract void SetHealth(int hp);

	public abstract boolean GetStatus();

	public void setPoint(int x, int y) {
		this.pos.x += x;
		this.pos.y += y;
	}

	public int getPointX() {
		return this.pos.x;
	}

	public int getPointY() {
		return this.pos.y;
	}

	/*
	 * Устанавливаем направление движения по оси X
	 */
	public void setDirectionX(int x) {
		this.direction.x = x;
	}

	/*
	 * Получаем направление движения по оси X
	 */
	public int getDirectionX() {
		return this.direction.x;
	}

	/*
	 * Устанавливаем направление движения по оси Y
	 */
	public void setDirectionY(int y) {
		this.direction.y = y;
	}

	/*
	 * Получаем направление движения по оси Y
	 */
	public int getDirectionY() {
		return this.direction.y;
	}

	/*
	 * Получаем размеры игрока (размеры соответсвуют размерам картинки, которую
	 * мы используем для отображения игрока)
	 */
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/*
	 * Движется игрок или нет
	 */
	public boolean isMoved() {
		return (this.direction.x != 0 || this.direction.y != 0);
	}

	/*
	 * Получаем скорость игрока
	 */
	public int getSpeed() {
		return speed;
	}

	public abstract Player lookUp();

	public abstract Player lookDown();

	public abstract Player lookLeft();

	public abstract Player lookRight();

	public abstract Player setLookUp(String name);

	public abstract Player setLookDown(String name);

	public abstract Player setLookLeft(String name);

	public abstract Player setLookRight(String name);

	public abstract void turnLatern(boolean b);

	public abstract Integer getLaternCharge();
	
	public abstract void setLaternCharge(int value); //добавил метод для взаимодействия с фонариком
	
	public abstract boolean ghostShined(Player ghost);
}
