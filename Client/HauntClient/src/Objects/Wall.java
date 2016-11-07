package Objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Wall extends Block {

	private int x;
	private int y;
	public Wall(Image image,int coordX, int coordY, boolean light, boolean move) {
		super(image,1, coordX, coordY);
		x = coordX;
		y = coordY;
		lightThrough = light; //можно светить
		walkThrough = move; //можно ходить
	}

	@Override
	public int getCoordinateX() {
		return x;
	}

	@Override
	public int getCoordinateY() {
		return y;
	}

	@Override
	public void render(Graphics graphic,ImageObserver io){
		// отрисовка
		
	}

	@Override
	public void objectSettings() {
		//параметры отрисовки
		
	}

	@Override
	public boolean canWalk() {
		return lightThrough;
	}

	@Override
	public boolean canShine() {
		return walkThrough;
	}

}
