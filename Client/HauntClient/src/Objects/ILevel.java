package Objects;

import java.awt.Graphics;

import Mechanic.Appearence;

public interface ILevel {
	/**
	 * Проверка наличия плитки на карте
	 */
	public boolean hasTile(int x, int y, int layer);

	/**
	 * Количество плиток на карте по ширине
	 */
	public int getWidth(int layer);

	/**
	 * Количество плиток на карте по высоте
	 */
	public int getHeight(int layer);

	/**
	 * Получить номер тайла (его позиция на тайлсете)
	 */
	public int getTileId(int x, int y, int layer);

	/**
	 * Получить номер тайла во втором слое (препятсвия)
	 */
	public int getLetId(int x, int y);

	public boolean paintLevel(Graphics graphic);

	// добавляем слои карты
	public ILevel addLayerFirst(); // первый слой

	public ILevel addLayerSecond(); // второй слой

	public ILevel addLayerThird(); // третий слой

	public ILevel addLayerFourth(); // для молний

	public ILevel addLayerHUD(); // для HUD

	public ILevel addPlayer(Appearence player); // для игроков
}
