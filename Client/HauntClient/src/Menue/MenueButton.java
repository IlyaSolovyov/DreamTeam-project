package Menue;

import java.awt.Point;

public class MenueButton {
	private Point point;
	private int width;
	private int height;
	private String name;
	private STATES status = null;

	public MenueButton(Point point, int width, int height) {
		this.point = new Point(point);
		this.width = width;
		this.height = height;
	}

	public MenueButton setMenueButtonName(String name) {
		this.name = name;
		return this;
	}

	public int getX() {
		return point.x;
	}

	public int getY() {
		return point.y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getName() {
		return name;
	}

	public MenueButton setMenueButtonStatus(STATES status) {
		this.status = status;
		return this;
	}

	public STATES getMenueButtonStatus() {
		return this.status;
	}
}
