package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class TestCanvas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int titleWidth = 64; // ширина тайла
	private static final int titleHeight = 64; // высота тайла
	// карта
	private Map map;
	private Image tileset; // наш тайлсет

	public TestCanvas() {
		this.map = new Map();
		tileset = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/tilesheet.png"));
	}

	@Override
	protected void paintComponent(Graphics graphic) {
		graphic.setColor(Color.black);
		graphic.fillRect(0, 0, getWidth(), getHeight());

		for (int i = 0; i < 14; i++)
			for (int j = 0; j < 22; j++)
				drawTile(graphic, new Block(tileset, map.getTileId(j, i, 1), j * titleWidth, i * titleHeight));

	}
	// ----------------------------------------------------------------------

	private void drawTile(Graphics graphic, Block tile) {
		tile.render(graphic, this);
	}
}
