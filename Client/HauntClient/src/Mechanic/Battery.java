package Mechanic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Battery implements Appearence {

	private Point pos;
	Image image;

	public Battery(int coordX, int coordY) {
		this.pos = new Point(coordX, coordY);
		this.image = new ImageIcon(getClass().getResource("/resources/battery.png")).getImage();
	}

	public int getX() {
		return pos.x;
	}

	public int getY() {
		return pos.y;
	}

	@Override
	public void render(Graphics graphic, ImageObserver io) {
		graphic.drawImage(image, pos.x, pos.y, image.getWidth(null), image.getHeight(null), io);
	}

	@Override
	public void objectSettings() {
	}

	public int resotorePower(int pow) {
		pow = 100;
		return pow; // ��� ������ � ���������
	}
}
