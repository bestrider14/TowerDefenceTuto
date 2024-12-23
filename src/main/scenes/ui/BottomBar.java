package main.scenes.ui;

import main.GameStates;
import main.objects.Tile;
import main.scenes.Playing;

import java.awt.*;
import java.util.ArrayList;

import static main.GameStates.MAIN_MENU;

public class BottomBar
{
	private final int x, y, width, height;

	private Button buttonMenu;
	private final Playing playing;

	private final ArrayList<Button> tileButtons = new ArrayList<>();
	private Tile selectedTile;

	private Color backgroundColor = new Color(7, 54, 61);

	public BottomBar(int x, int y, int width, int height, Playing playing)
	{
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
		this.playing = playing;

		initButtons();
	}

	private void drawButtons(Graphics2D g)
	{
		buttonMenu.draw(g);
		drawTileButtons(g);
	}

	private void drawTileButtons(Graphics2D g)
	{
		for (Button button : tileButtons)
			button.draw(g);
	}

	private void initButtons()
	{
		buttonMenu = new Button("Menu", 2, 645, 100, 32);

		int w = 50;
		int h = 50;
		int xStart = 120;
		int yStart = 645;
		int xOffset = (int) (w * 1.3f);

		int i = 0;
		for (Tile tile : playing.getTileManager().tiles)
			tileButtons.add(new Button(tile.getSprite(), xStart + xOffset * i++, yStart, w, h));
	}

	public void draw(Graphics2D g)
	{
		g.setColor(backgroundColor);
		g.fillRect(x, y, width, height);

		drawButtons(g);
	}

	public void mouseClicked(int x, int y)
	{
		if (buttonMenu.getBounds().contains(x, y))
		{
			GameStates.setGameState(MAIN_MENU);
			buttonMenu.resetStates();
		}
		else
		{
			for (Button button : tileButtons)
			{
				if (button.getBounds().contains(x, y))
				{
					button.setToggled(!button.isToggled());
					if(button.isToggled())
					{
						selectedTile = playing.getTileManager().getTile(tileButtons.indexOf(button));
						playing.setSelectedTile(selectedTile);
					}
					else
					{
						selectedTile = null;
						playing.setSelectedTile(null);
					}
				}
				else
				{
					button.setToggled(false);
				}
			}
		}
	}

	public void mouseMoved(int x, int y)
	{
		buttonMenu.setMouseOver(buttonMenu.getBounds().contains(x, y));

		for (Button button : tileButtons)
			button.setMouseOver(button.getBounds().contains(x, y));
	}

	public void mousePressed(int x, int y)
	{
		buttonMenu.setMousePressed(buttonMenu.getBounds().contains(x, y));

		for (Button button : tileButtons)
			button.setMousePressed(button.getBounds().contains(x, y));
	}

	public void mouseReleased(int x, int y)
	{
		resetButtons();
		mouseMoved(x, y);
	}

	private void resetButtons()
	{
		buttonMenu.resetStates();

		for (Button button : tileButtons)
			button.resetStates();
	}

	public void setBackgroundColor(Color backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}
}
