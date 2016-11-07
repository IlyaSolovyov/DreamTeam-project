package Objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;

import Mechanic.Appearence;

public /* abstract */ class Block implements Appearence {

	boolean walkThrough;
	boolean lightThrough;
	final public static int SIZE = 64; // Размер тайла - ширина, высота
	final public static int titleQUANTITY = 27; // количество блоков по ширине тайлсета

	private Point pos; // координаты тайла по оси X и Y на карте
	private Image tileset; // тайлсет
	private int tilePos;

	public Block(Image tile, int tilePos, int coordX, int coordY) {
		this.tileset = tile;
		this.tilePos = tilePos;
		this.pos = new Point(coordX, coordY);
		this.walkThrough = true;
		this.lightThrough = true;
	}
	
	public /* abstract */ int getTilePos() {
		return tilePos;
	}

	public /* abstract */ int getCoordinateX() {
		return pos.x;
	}

	public /* abstract */ int getCoordinateY() {
		return pos.y;
	}

	public /* abstract */ void objectSettings() {
	}

	public /* abstract */ boolean canWalk() {
		return walkThrough;
	}

	public /* abstract */ boolean canShine() {
		return lightThrough;
	}

	@Override
	public void render(Graphics graphic, ImageObserver io) {
		int x = this.tilePos % titleQUANTITY - 1;
		int y = this.tilePos / titleQUANTITY;
		graphic.drawImage(this.tileset, pos.x, pos.y, pos.x + SIZE, pos.y + SIZE, x * SIZE, y * SIZE, x * SIZE + SIZE,
				y * SIZE + SIZE, io);
	}

}
