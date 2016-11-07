package Mechanic;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

public interface Appearence {

	void objectSettings(); // настройки внешнего вида

	void render(Graphics graphic,ImageObserver io);// вместо void drawObject(); // отрисовка объекта

}
