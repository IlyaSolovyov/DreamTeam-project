package Mechanic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

import Objects.Block;

public class Flashlight implements Lighting {

	private Point pos;
	private Point direction;
	protected int charge;
	Image smallLight;
	Image bigVertikal;
	Image bigHorizontal;

	boolean included;

	public Flashlight(int coordX, int coordY) {
		pos = new Point(coordX, coordY);
		smallLight = new ImageIcon(getClass().getResource("/resources/light.png")).getImage();
		bigVertikal = new ImageIcon(getClass().getResource("/resources/lihgt100vertical.png")).getImage();
		bigHorizontal = new ImageIcon(getClass().getResource("/resources/lihgt100horizontal.png")).getImage();
		direction = new Point(0, 0);
		included = false;
		setCharge(100);
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int value) {
		charge = value; //Заряд фонарика
	}
	
	public int getX() {
		return pos.x;
	}

	public int getY() {
		return pos.y;
	}

	public void setDirectionX(int x) {
		direction.move(x, 0);
	}

	public void setDirectionY(int y) {
		direction.move(0, y);
	}

	public boolean isShines() {
		return included;
	}

	public void setInclusion(boolean turn) {
		if (charge > 0)
			included = turn;
	}

	public void draw(Graphics graphic, int x, int y, ImageObserver io) { // рисуем
																			// картинку
		int xCoord = 0;
		int yCoord = 0;
		int width = 0;
		int height = 0;
		graphic.setColor(new Color(255, 255, 255, 60));
		if (direction.getX() == 0) {
			height = 49;
			width = 43;
			if (direction.getY() > 0) {
				xCoord = x;
				yCoord = y + height;
			}
			if (direction.getY() < 0) {
				xCoord = x;
				yCoord = y - Block.SIZE * 2;
			}
			graphic.fillRect(xCoord, yCoord, Block.SIZE, Block.SIZE * 2);

		} else if (direction.getY() == 0) {
			height = 43;
			width = 49;
			if (direction.getX() > 0) {
				xCoord = x + width;
				yCoord = y;
			}
			if (direction.getX() < 0) {
				xCoord = x - Block.SIZE * 2;
				yCoord = y;
			}
			graphic.fillRect(xCoord, yCoord, Block.SIZE * 2, Block.SIZE);
		}

	}

	@Override
	public void lightSource() {
		
		// реализация свечения фонарика
		// здесь же можно указать урон, который наносится призраку и т.д.

	}

	public int Power(int pow) // энергия фонарика
	{
		pow--;
		return pow; // абстрактный пример, реализация может быть другая
	}

	public boolean lightThrough(boolean shine) // свечение через объекты. Может
												// светить через стены или
												// людей, призраков..
	{
		return shine;
	}

}
