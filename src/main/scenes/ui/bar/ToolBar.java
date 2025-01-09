package main.scenes.ui.bar;


import main.GameStates;
import main.managers.TileManager;
import main.objects.Tile;
import main.scenes.Editing;
import main.scenes.ui.Button;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static main.GameStates.MAIN_MENU;

public class ToolBar extends Bar
{
	private main.scenes.ui.Button buttonMenu, buttonSave;

	private final Map<Button, Tile> buttonToTiles = new HashMap<>();
	private final Map<TileManager.TileCategory, ArrayList<Button>> categoryButtons = new HashMap<>();
	private final Map<TileManager.TileCategory, Integer> rotationMap = new HashMap<>();

	private final Editing editing;

	public ToolBar(int x, int y, int width, int height, Editing editing)
	{
		super(x, y, width, height);
		this.editing = editing;
		initButtons();
	}

	public void draw(Graphics2D g)
	{
		super.draw(g);
	}

	@Override
	protected void drawButtons(Graphics2D g)
	{
		buttonMenu.draw(g);
		buttonSave.draw(g);
		drawTileButtons(g);
	}

	private void drawTileButtons(Graphics2D g)
	{
		for (Map.Entry<TileManager.TileCategory, ArrayList<Button>> entry : categoryButtons.entrySet())
		{
			TileManager.TileCategory category = entry.getKey();
			ArrayList<Button> buttons = entry.getValue();
			Integer currentRotation = rotationMap.getOrDefault(category, 0);
			Button buttonToDraw = buttons.get(currentRotation);
			buttonToDraw.draw(g);
		}
	}


	private void initButtons()
	{
		buttonMenu = new Button("Menu", 2, 645, 100, 32);
		buttonSave = new Button("Save", 2, 690, 100, 32);

		int w = 50;
		int h = 50;
		int xStart = 120;
		int yStart = 645;
		int xOffset = (int) (w * 1.3f);

		Map<TileManager.TileCategory, Integer> categoryIndexMap = new HashMap<>();
		int i = 0;

		for (Tile tile : editing.getGame().getTileManager().tiles.values())
		{
			TileManager.TileCategory category = tile.getCategory();

			if (!categoryIndexMap.containsKey(category))
			{
				categoryIndexMap.put(category, i);
				categoryButtons.put(category, new ArrayList<>());
				rotationMap.put(category, 0);
				i++;
			}

			int categoryIndex = categoryIndexMap.get(category);
			int xPosition = xStart + xOffset * categoryIndex;

			Button button = new Button(tile.getSprite(), xPosition, yStart, w, h);
			button.setActive(false);
			categoryButtons.get(category).add(button);
			buttonToTiles.put(button, tile);

			for (ArrayList<Button> buttons : categoryButtons.values())
				buttons.getFirst().setActive(true);
		}
	}


	@Override
	public void mouseClicked(int x, int y)
	{
		if (buttonMenu.getBounds().contains(x, y))
		{
			GameStates.setGameState(MAIN_MENU);
			buttonMenu.resetStates();
		}
		else if (buttonSave.getBounds().contains(x, y))
			editing.saveLevel();
		else
		{
			for (ArrayList<Button> buttons : categoryButtons.values())
			{
				for (Button button : buttons)
				{
					if (button.getBounds().contains(x, y) && button.isActive())
					{
						button.setToggled(!button.isToggled());

						if (button.isToggled())
							editing.setSelectedTile(buttonToTiles.get(button));
						else
							editing.setSelectedTile(null);
					}
					else
						button.setToggled(false);
				}
			}
		}
	}

	@Override
	public void mouseMoved(int x, int y)
	{
		buttonMenu.setMouseOver(buttonMenu.getBounds().contains(x, y));
		buttonSave.setMouseOver(buttonSave.getBounds().contains(x, y));

		for (ArrayList<Button> buttons : categoryButtons.values())
		{
			for (Button button : buttons)
				button.setMouseOver(button.getBounds().contains(x, y));
		}
	}

	@Override
	public void mousePressed(int x, int y)
	{
		buttonMenu.setMousePressed(buttonMenu.getBounds().contains(x, y));
		buttonSave.setMousePressed(buttonSave.getBounds().contains(x, y));

		for (ArrayList<Button> buttons : categoryButtons.values())
		{
			for (Button button : buttons)
				button.setMousePressed(button.getBounds().contains(x, y));
		}
	}

	@Override
	public void mouseReleased(int x, int y)
	{
		resetButtons();
		mouseMoved(x, y);
	}

	@Override
	public void keyPressed(int key)
	{
		if (editing.getSelectedTile() == null)
			return;

		if (key == KeyEvent.VK_Q || key == 113)
		{
			TileManager.TileCategory category = editing.getSelectedTile().getCategory();
			int rotation = rotationMap.get(category);
			if (rotation < categoryButtons.get(category).size() - 1)
				rotationMap.put(category, rotation + 1);
			else
				rotationMap.put(category, 0);

			updateActiveButtons(category);
		}
	}

	private void updateActiveButtons(TileManager.TileCategory category)
	{
		for (Button button : categoryButtons.get(category))
			button.setActive(false);

		Integer currentRotation = rotationMap.getOrDefault(category, 0);
		resetButtons();
		categoryButtons.get(category).get(currentRotation).setActive(true);
		categoryButtons.get(category).get(currentRotation).setToggled(true);
		editing.setSelectedTile(buttonToTiles.get(categoryButtons.get(category).get(currentRotation)));
	}

	@Override
	protected void resetButtons()
	{
		buttonMenu.resetStates();
		buttonSave.resetStates();

		for (ArrayList<Button> buttons : categoryButtons.values())
		{
			for (Button button : buttons)
				button.resetStates();
		}
	}
}
