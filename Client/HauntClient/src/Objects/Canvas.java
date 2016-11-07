package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JPanel;

import Mechanic.Appearence;

//import Mechanic.Appearence;

public class Canvas extends JPanel /* implements Runnable */ {

	protected static final long serialVersionUID = 1L;
	protected Color backGround = Color.black; // Фон холста по умолчанию

	// список блоков для отрисовки карты
	protected ArrayList<Appearence> renders = new ArrayList<Appearence>();
	protected ArrayList<Appearence> players = new ArrayList<Appearence>();
	protected ArrayList<Appearence> lightning = new ArrayList<Appearence>();
	protected ArrayList<Appearence> HUD = new ArrayList<Appearence>();
	
	protected Image bufer = null;

	/**
	 * Добавить элемент для рисования
	 */
	public void addRender(Appearence render) {
		renders.add(render);
	}

	public void addPlayerRender(Appearence render) {
		players.add(render);
	}
	public void addLightningRender(Appearence render) {
		lightning.add(render);
	}
	public void addHUDRender(Appearence render) {
		HUD.add(render);
	}

	/**
	 * Очищает список обрисовываемых элементов
	 */
	public void removeRenders() {
		if (renders.size() > 0)
			renders.clear();
	}

	public void removePlayers() {
		if (players.size() > 0)
			players.clear();
	}
	public void removeLightning() {
		if (lightning.size() > 0)
			lightning.clear();
	}
	public void removeHUD() {
		if (HUD.size() > 0)
			HUD.clear();
	}

	/**
	 * Отвечает за вывод графики на компоненте
	 */
	public void paintWorld(Graphics g) {
		if (renders.size() == 0)
			return;
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setColor(backGround);
		g.fillRect(0, 0, getWidth(), getHeight());

		for (Appearence render : renders) {
			render.render(g, this);
		}

		for (Appearence player : players) {
			player.render(g, this);
		}

		for (Appearence light : lightning) {
			light.render(g, this);
		}
		
		for (Appearence hud : HUD) {
			hud.render(g, this);
		}
	}

	/**
	 * Переопределяем метод обрисовки компонента
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (bufer == null) {
			bufer = createImage(getWidth(), getHeight());
		}
		// рисуем мир!
		paintWorld(bufer.getGraphics());
		g.drawImage(bufer, 0, 0, null);
	}
}
