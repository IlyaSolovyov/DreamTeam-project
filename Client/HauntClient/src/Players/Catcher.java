package Players;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

import Mechanic.Flashlight;

/**
 * Класс игрока
 */
public class Catcher extends Player {

	private Flashlight latern; // У каждого игрока есть свой объект
								// фонарика
	// Изображение персонажа
	protected boolean flashlightPower;
	protected Image imageUp;
	protected Image imageLeft;
	protected Image imageDown;
	protected Image imageRight;

	public Catcher(String name, Point point) {
		super(name, point);
		SetHealth(1000);
		setpFlashlight(new Flashlight(0, 0)); // Cоздаём этот самый фонарик
		this.pos = new Point(point);
		this.direction = new Point(0, 0);
	}

	/*
	 * Переопределяем методы прорисовки
	 */
	
	@Override
	public void render(Graphics graphic, ImageObserver io) {
		graphic.drawImage(image, pos.x, pos.y, width, height, io);
		graphic.setColor(Color.RED);
		graphic.drawRect(pos.x + 10, pos.y + 10, width - 20, height - 20);
		if (latern.isShines() && latern.getCharge()>0) {
			latern.draw(graphic, pos.x, pos.y, io);
		}
	}
	
	public void objectSettings() {

	}
	
	@Override
	public boolean ghostShined(Player ghost){
		int a = 10;
		if(((latern.getX() + a >= ghost.getPointX() + a - (ghost.getWidth() - 2 * a)
				&& latern.getX() + a <= ghost.getPointX() + a + (ghost.getWidth() - 2 * a))
				&& (latern.getY() + a >= ghost.getPointY() + a - (ghost.getHeight() - 2 * a)
						&& latern.getY() + a <= ghost.getPointY() + a + (ghost.getHeight() - 2 * a)))){
			ghost.SetHealth(ghost.GetHealth()-1);
			return true;
		}
		else return false; //булеан для того что-бы дать призраку неуязвимость после попадания на свет
		
	}
	

	@Override
	public void turnLatern(boolean include) {
		if (GetHealth() > 0)
		this.latern.setInclusion(include);
	}
	
	@Override
	public void moveUp() {
		if (GetHealth() > 0) {
			setDirectionY(-1);// меняем напрвление движения
			image = imageUp;// меняем изображение игрока
			// меняем размеры игрока
			width = image.getWidth(null);
			height = image.getHeight(null);
			latern.setDirectionY(getDirectionY());
		}
	}

	@Override
	public void moveDown() {
		if (GetHealth() > 0) {
			setDirectionY(1);// меняем напрвление движения
			image = imageDown;// меняем изображение игрока
			// меняем размеры игрока
			width = image.getWidth(null);
			height = image.getHeight(null);
			latern.setDirectionY(getDirectionY());
		}
	}

	@Override
	public void moveLeft() {
		if (GetHealth() > 0) {
			setDirectionX(-1);// меняем напрвление движения
			image = imageLeft;// меняем изображение игрока
			// меняем размеры игрока
			width = image.getWidth(null);
			height = image.getHeight(null);
			latern.setDirectionX(getDirectionX());
		}
	}

	@Override
	public void moveRight() {
		if (GetHealth() > 0) {
			setDirectionX(1);// меняем напрвление движения
			image = imageRight;// меняем изображение игрока
			// меняем размеры игрока
			width = image.getWidth(null);
			height = image.getHeight(null);
			latern.setDirectionX(getDirectionX());
		}
	}



	@Override
	public Player lookDown() {
		image = imageDown;
		latern.setDirectionY(1);
		return this;
	}

	@Override
	public Player lookUp() {
		image = imageUp;
		latern.setDirectionY(-1);
		return this;
	}

	@Override
	public Player lookLeft() {
		image = imageLeft;
		latern.setDirectionX(-1);
		return this;
	}

	@Override
	public Player lookRight() {
		image = imageRight;
		latern.setDirectionX(1);
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

	public Flashlight getpFlashlight() {
		return latern;
	}

	public void setpFlashlight(Flashlight pFlashlight) {
		this.latern = pFlashlight;
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
	public Integer getLaternCharge() {
		return latern.getCharge();
	}
	
	@Override
	public void setLaternCharge(int value){
		latern.setCharge(value);
	}

}
