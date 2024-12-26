package main.scenes.ui;

import main.GameStates;
import main.objects.Tile;
import main.scenes.Playing;

import java.awt.*;
import java.util.ArrayList;

import static main.GameStates.MAIN_MENU;

public class ActionBar extends Bar
{

	private Button buttonMenu, buttonSave;
	private final Playing playing;

	private final ArrayList<Button> tileButtons = new ArrayList<>();
	private Tile selectedTile;

	public ActionBar(int x, int y, int width, int height, Playing playing)
	{
		super(x, y, width, height);
		this.playing = playing;
		initButtons();
	}

	private void drawButtons(Graphics2D g)
	{
		buttonMenu.draw(g);
		buttonSave.draw(g);
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
		buttonSave = new Button("Save", 2, 680, 100, 32);

		int w = 50;
		int h = 50;
		int xStart = 120;
		int yStart = 645;
		int xOffset = (int) (w * 1.3f);

		int i = 0;
		for (Tile tile : playing.getGame().getTileManager().tiles)
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
		else if (buttonSave.getBounds().contains(x, y))
		{
			playing.saveLevel();
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
		buttonSave.setMouseOver(buttonSave.getBounds().contains(x, y));

		for (Button button : tileButtons)
			button.setMouseOver(button.getBounds().contains(x, y));
	}

	public void mousePressed(int x, int y)
	{
		buttonMenu.setMousePressed(buttonMenu.getBounds().contains(x, y));
		buttonSave.setMousePressed(buttonSave.getBounds().contains(x, y));

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
		buttonSave.resetStates();

		for (Button button : tileButtons)
			button.resetStates();
	}

	public void setBackgroundColor(Color backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}
}
