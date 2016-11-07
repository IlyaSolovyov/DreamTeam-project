package Players;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

import Mechanic.Appearence;

/**
 * ����� ��������
 */

public class Ghost extends Player implements Appearence {

	private int healthRemains; // Healthbar

	// Изображение призрака
	protected Image imageUp;
	protected Image imageLeft;
	protected Image imageDown;
	protected Image imageRight;

	public Ghost(String name, Point point) {
		super(name, point);
		SetHealth(5);
		isMovableThrough = true; // Может проходить через других игроков
		this.pos = new Point(point);
		this.direction = new Point(0, 0);
	}

	public void objectSettings() {
	}

	public void IsPlayerCatched() {
	} // Функция при столкновении с игроком

	@Override
	public void moveUp() {
		setDirectionY(-1);// меняем напрвление движения
		image = imageUp;// меняем изображение игрока
		// меняем размеры игрока
		width = 43;
		height = 49;
	}

	@Override
	public void moveDown() {
		setDirectionY(1);// меняем напрвление движения
		image = imageDown;// меняем изображение игрока
		// меняем размеры игрока
		width = 43;
		height = 49;
	}

	@Override
	public void moveLeft() {
		setDirectionX(-1);// меняем напрвление движения
		image = imageLeft;// меняем изображение игрока
		// меняем размеры игрока
		width = 49;
		height = 43;
	}

	@Override
	public void moveRight() {
		setDirectionX(1);// меняем напрвление движения
		image = imageRight;// меняем изображение игрока
		// меняем размеры игрока
		width = 49;
		height = 43;
	}

	@Override
	public void render(Graphics graphic, ImageObserver io) {
		graphic.drawImage(image, pos.x, pos.y, width, height, io);
		graphic.setColor(Color.RED);
		graphic.drawRect(pos.x + 10, pos.y + 10, width - 20, height - 20);
	}

	@Override
	public Player lookDown() {
		image = imageDown;
		return this;
	}

	@Override
	public Player lookUp() {
		image = imageUp;
		return this;
	}

	@Override
	public Player lookLeft() {
		image = imageLeft;
		return this;
	}

	@Override
	public Player lookRight() {
		image = imageRight;
		return this;
	}

	@Override
	public Player setLookUp(String name) {
		if (name != null)
			this.imageUp = new ImageIcon(getClass().getResource(name)).getImage();
		return this;
	}

	@Override
	public Player setLookDown(String name) {
		if (name != null)
			this.imageDown = new ImageIcon(getClass().getResource(name)).getImage();
		return this;
	}

	@Override
	public Player setLookLeft(String name) {
		if (name != null)
			this.imageLeft = new ImageIcon(getClass().getResource(name)).getImage();
		return this;
	}

	@Override
	public Player setLookRight(String name) {
		if (name != null)
			this.imageRight = new ImageIcon(getClass().getResource(name)).getImage();
		return this;
	}

	public int getHealthRemains() {
		return healthRemains;
	}

	public void setHealthRemains(int healthRemains) {
		this.healthRemains = healthRemains;
	}

	@Override
	public int GetHealth() {
		return health;
	}

	@Override
	public void SetHealth(int hp) {
		health = hp;
	}

	@Override
	public boolean GetStatus() {
		return isDead;
	}

	@Override
	public void turnLatern(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getLaternCharge() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLaternCharge(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean ghostShined(Player ghost) {
		// TODO Auto-generated method stub
		return false;
	}
}
