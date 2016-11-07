package Menue;

import java.awt.*;
import java.util.ArrayList;

import Main.GameApplet;

public class Menue {
	private Point coord;
	private int btnWidth;
	private int btnHeight;
	private Color color;

	private String[] main = new String[] { "СТАРТ", "ОБ ИГРЕ", "ВЫХОД" };
	private STATES[] mainStatus = new STATES[] { STATES.START, STATES.ABOUT, STATES.EXIT };

	private String[] player = new String[] { "ИГРОК 1", "ИГРОК 1", "ИГРОК 1", "ИГРОК 1", "ИГРОК 1" };
	private STATES[] playerStatus = new STATES[] { STATES.FIRST, STATES.SECOND, STATES.THIRD, STATES.FOURTH,
			STATES.BACK };

	private ArrayList<MenueButton> mainMenue;
	private ArrayList<MenueButton> playerMenue;
	private ArrayList<MenueButton> activeMenue;

	private int transp;

	public Menue() {
		this.mainMenue = new ArrayList<MenueButton>();
		this.activeMenue = new ArrayList<MenueButton>();
		this.transp = 0;
		this.color = Color.WHITE;
		this.coord = new Point(0, 0);
	}

	public Menue createMainMenue() {
		if (activeMenue.size() > 0)
			activeMenue.clear();
		if (mainMenue.size() == 0) {
			this.btnWidth = 240;
			this.btnHeight = 60;
			coord.x = GameApplet.WIDTH / 2 - btnWidth / 2;
			int height = GameApplet.HEIGHT / (main.length + 2);
			for (int i = 0; i < main.length; i++) {
				coord.y = (height - btnHeight) / 2 + height * (i + 1);
				mainMenue.add(new MenueButton(new Point(coord.x, coord.y), btnWidth, btnHeight)
						.setMenueButtonName(main[i]).setMenueButtonStatus(mainStatus[i]));
			}
		}
		activeMenue.addAll(mainMenue);
		return this;
	}

	public Menue createPlayerMenue() {
		if (activeMenue.size() > 0)
			activeMenue.clear();
		if (playerMenue.size() == 0) {
			this.btnWidth = 60;
			this.btnHeight = 60;
			coord.x = GameApplet.WIDTH / 2 - btnWidth / 2;

			int height = GameApplet.HEIGHT / (main.length + 2);
			for (int i = 0; i < main.length; i++) {
				coord.y = (height - btnHeight) / 2 + height * (i + 1);
				playerMenue.add(new MenueButton(new Point(coord.x, coord.y), btnWidth, btnHeight)
						.setMenueButtonName(main[i]).setMenueButtonStatus(mainStatus[i]));
			}
		}
		activeMenue.addAll(playerMenue);
		return this;
	}

	public void update(MenueButton menueButton) {
		if ((GameApplet.coordMouse.getX() >= menueButton.getX()
				&& GameApplet.coordMouse.getX() <= menueButton.getX() + menueButton.getWidth())
				&& (GameApplet.coordMouse.getY() >= menueButton.getY()
						&& GameApplet.coordMouse.getY() <= menueButton.getY() + menueButton.getHeight())) {
			transp = 100;
		} else {
			transp = 0;
		}
	}

	public STATES getButtonStatus() {
		for (MenueButton menueButton : activeMenue) {
			if ((GameApplet.coordMouse.getX() >= menueButton.getX()
					&& GameApplet.coordMouse.getX() <= menueButton.getX() + menueButton.getWidth())
					&& (GameApplet.coordMouse.getY() >= menueButton.getY()
							&& GameApplet.coordMouse.getY() <= menueButton.getY() + menueButton.getHeight())) {
				return menueButton.getMenueButtonStatus();
			}
		}
		return null;
	}

	public void drawMainMenue(Graphics grahic) {
		grahic.setColor(Color.RED);

		grahic.setFont(new Font("Consolas", Font.BOLD, 60));
		int length = (int) grahic.getFontMetrics().getStringBounds("--- HAUNT ---", grahic).getWidth();
		grahic.drawString("--- HAUNT ---", (int) GameApplet.WIDTH / 2 - length / 2, 50);

		grahic.setColor(color);
		int height = GameApplet.HEIGHT / (mainMenue.size() + 2);
		int i = 0;
		for (MenueButton menueButton : mainMenue) {

			((Graphics2D) grahic).setStroke(new BasicStroke(3));
			grahic.drawRect(menueButton.getX(), menueButton.getY(), menueButton.getWidth(), menueButton.getHeight());

			update(menueButton);

			grahic.setColor(new Color(255, 255, 255, transp));
			grahic.fillRect(menueButton.getX(), menueButton.getY(), menueButton.getWidth(), menueButton.getHeight());

			grahic.setColor(color);
			((Graphics2D) grahic).setStroke(new BasicStroke(1));
			grahic.setFont(new Font("Consolas", Font.BOLD, 40));

			length = (int) grahic.getFontMetrics().getStringBounds(menueButton.getName(), grahic).getWidth();

			grahic.drawString(menueButton.getName(), (int) GameApplet.WIDTH / 2 - length / 2,
					(int) (height - menueButton.getHeight()) / 2 + height * (i + 1) + menueButton.getHeight()
							- menueButton.getHeight() / 3);
			i++;
		}

	}
}
