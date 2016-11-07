package Objects;

import java.awt.Image;
import java.awt.Toolkit;

public class TileSet {

	private String path;
	final private int SIZE; // Размер тайла - ширина, высота
	private int tileQuantity;
	private Image image = null;

	public TileSet(String path, int size, int width) {
		this.path = path;
		image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(this.path));
		this.SIZE = size;
		this.tileQuantity = width;
	}

	public Image getImage() {
		return image;
	}

	public int getSize() {
		return SIZE;
	}

	public int getTileQuantity() {
		return tileQuantity;
	}
}
