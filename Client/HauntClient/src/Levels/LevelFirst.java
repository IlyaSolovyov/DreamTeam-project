package Levels;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import Mechanic.Appearence;
import Objects.*;

public class LevelFirst extends Canvas implements ILevel {
	/**
	 * 
	 */

	protected static final long serialVersionUID = 1L;
	protected int[][] layer1; // слой для отрисовки пола
	protected int[][] layer2; // слой для отрисовки препятствий
	protected int[][] layer3; // слой для отрисовки разной мелочи (чашка на столе,
	protected int[][] layer4; // слой для отрисовки молний
	protected int[][] layerHUD; // слой для HUD
	protected Image tileset; // наш тайлсет // пятна, прочее)
	// public final static int layerQUANTITY = 5; // количество слоев карты

	public LevelFirst(String resourceName) {
		tileset = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(resourceName));
		layer1 = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 69, 69, 69, 73, 73, 73, 70, 70, 69, 69, 69, 69, 69, 69, 69, 69, 69, 69, 69, 69 },
				{ 69, 73, 73, 73, 69, 370, 371, 371, 372, 73, 73, 73, 73, 71, 69, 73, 73, 73, 69, 69 },
				{ 69, 73, 69, 73, 71, 424, 425, 425, 426, 71, 73, 71, 71, 69, 71, 71, 71, 69, 69, 69 },
				{ 69, 69, 69, 71, 73, 71, 73, 73, 69, 73, 71, 71, 71, 69, 69, 69, 71, 71, 69, 69 },
				{ 69, 69, 71, 71, 69, 69, 71, 69, 69, 73, 73, 69, 373, 374, 375, 69, 69, 69, 71, 71 },
				{ 69, 69, 71, 71, 69, 71, 69, 71, 73, 71, 69, 69, 400, 401, 402, 73, 73, 73, 71, 71 },
				{ 69, 69, 71, 69, 69, 73, 69, 71, 69, 69, 71, 71, 427, 428, 429, 71, 69, 73, 69, 69 },
				{ 69, 73, 73, 73, 69, 71, 73, 69, 69, 73, 69, 69, 71, 73, 71, 69, 71, 69, 73, 71 },
				{ 69, 343, 344, 345, 71, 71, 71, 71, 71, 71, 71, 71, 71, 69, 69, 73, 71, 73, 73, 69 },
				{ 69, 69, 69, 69, 69, 69, 69, 69, 69, 69, 69, 69, 71, 71, 71, 71, 71, 73, 73, 69 } };

		layer2 = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 142, 111, 111, 114, 464, 196, 469, 469, 196, 464, 109, 111, 111, 111, 111, 111, 111, 111, 111, 114 },
				{ 491, 000, 000, 000, 000, 000, 000, 000, 000, 000, 115, 000, 000, 000, 000, 000, 000, 000, 000, 492 },
				{ 141, 000, 196, 000, 134, 000, 000, 000, 000, 000, 000, 000, 159, 160, 000, 141, 000, 506, 000, 492 },
				{ 115, 000, 000, 000, 197, 134, 000, 000, 000, 197, 000, 000, 000, 000, 000, 138, 000, 000, 000, 141 },
				{ 491, 000, 000, 000, 000, 000, 000, 506, 000, 000, 000, 000, 000, 529, 000, 136, 114, 000, 142, 137 },
				{ 141, 000, 000, 000, 141, 000, 000, 000, 000, 141, 000, 453, 454, 455, 000, 000, 000, 000, 000, 492 },
				{ 139, 114, 000, 142, 137, 450, 000, 000, 450, 138, 000, 000, 529, 000, 000, 141, 000, 48, 000, 196 },
				{ 115, 000, 000, 506, 000, 000, 000, 000, 000, 115, 000, 000, 000, 000, 000, 138, 000, 102, 000, 492 },
				{ 491, 000, 000, 000, 000, 000, 453, 455, 000, 000, 000, 141, 501, 502, 503, 138, 000, 000, 000, 492 },
				{ 142, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 113, 111, 111, 111, 113, 111, 111, 111,
						114 } };

		layer3 = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 320, 0, 0, 293, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 188, 0, 0, 0, 0, 480, 482, 0, 0, 0, 241, 0, 0 },
				{ 0, 320, 0, 0, 0, 0, 0, 0, 0, 0, 0, 188, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 132, 0, 0, 187, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 320, 0, 0, 0, 0, 318, 318, 215, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 320, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 264, 240, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 264, 0 },
				{ 0, 291, 0, 0, 0, 0, 532, 533, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 291, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

		layerHUD = new int[][] { { 313, 282, 285, 313, 282, 282, 282, 285, 313, 282, 282, 285, 313, 282, 282, 282, 285,
				313, 282, 285 } };
	}

	@Override
	public boolean hasTile(int x, int y, int layer) {
		return (x >= 0 && y >= 0 && y < getHeight(layer) && x < getWidth(layer));
	}

	@Override
	public int getWidth(int layer) {
		switch (layer) {
		case 1:
			return layer1[0].length;
		case 2:
			return layer2[0].length;
		case 3:
			return layer3[0].length;
		case 4:
			return layer4[0].length;
		case 5:
			return layerHUD[0].length;
		}
		return 0;
	}

	@Override
	public int getHeight(int layer) {
		switch (layer) {
		case 1:
			return layer1.length;
		case 2:
			return layer2.length;
		case 3:
			return layer3.length;
		case 4:
			return layer4.length;
		case 5:
			return layerHUD.length;
		}
		return 0;

	}

	@Override
	public int getTileId(int x, int y, int layer) {
		if (layer == 1) {
			return hasTile(x, y, layer) ? layer1[y][x] : 0;
		} else if (layer == 2) {
			return hasTile(x, y, layer) ? layer2[y][x] : 0;
		} else if (layer == 3) {
			return hasTile(x, y, layer) ? layer3[y][x] : 0;
		} else if (layer == 4) {
			return hasTile(x, y, layer) ? layer4[y][x] : 0;
		} else if (layer == 5) {
			return hasTile(x, y, layer) ? layerHUD[y][x] : 0;
		} else {
			return 0;
		}
	}

	@Override
	public int getLetId(int x, int y) {
		return getTileId(x, y, 2);
	}

	@Override
	public boolean paintLevel(Graphics graphic) {
		try {
			paintWorld(graphic);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addLayer(int layer) {
		try {
			Point point = new Point(0, 0);
			for (point.y = 0; point.y < this.getHeight(layer); point.y++) {
				for (point.x = 0; point.x < this.getWidth(layer); point.x++) {
					if (this.getTileId(point.x, point.y, layer) != 0) {
						// можно блоки для отрисовки забить в одну колекцию
						this.addRender(new Block(tileset, this.getTileId(point.x, point.y, layer), point.x * Block.SIZE,
								point.y * Block.SIZE));
						// или так, создавая отдельно колекции для отрисовки
						// карты, молнии и HUD-а
						/*
						 * switch (layer) { case 1: case 2: case 3:
						 * this.addRender(new Block(tileset,
						 * this.getTileId(point.x, point.y, layer), point.x *
						 * Block.SIZE, point.y * Block.SIZE)); break; case 4:
						 * this.addLightningRender(new Block(tileset,
						 * this.getTileId(point.x, point.y, layer), point.x *
						 * Block.SIZE, point.y * Block.SIZE); break; case 5:
						 * this.addHUDRender(new Block(tileset,
						 * this.getTileId(point.x, point.y, layer), point.x *
						 * Block.SIZE, point.y * Block.SIZE)); break; default:
						 * return false; }
						 */

					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ILevel addLayerFirst() {
		this.addLayer(1);
		return this;
	}

	@Override
	public ILevel addLayerSecond() {
		this.addLayer(2);
		return this;
	}

	@Override
	public ILevel addLayerThird() {
		this.addLayer(3);
		return this;
	}

	@Override
	public ILevel addLayerFourth() {
		this.addLayer(4);
		return this;
	}

	@Override
	public ILevel addLayerHUD() {
		this.addLayer(5);
		return this;
	}

	@Override
	public ILevel addPlayer(Appearence player) {
		// this.addPlayerRender(player);
		this.addRender(player);
		return this;
	}
}
