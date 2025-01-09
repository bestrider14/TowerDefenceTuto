package main.scenes.ui.bar;

import main.GameStates;
import main.managers.TowerManager;
import main.scenes.Playing;
import main.scenes.ui.Button;
import main.towers.Tower;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static main.GameStates.MAIN_MENU;

public class ActionBar extends Bar
{
	private main.scenes.ui.Button buttonMenu;

	private final Map<Tower.TowerType, Button> buttonToTowerType = new HashMap<>();

	private final Playing playing;

	public ActionBar(int x, int y, int width, int height, Playing playing)
	{
		super(x, y, width, height);
		this.playing = playing;
		initButtons();
	}

	@Override
	public void draw(Graphics2D g)
	{
		super.draw(g);
		for (Button button : buttonToTowerType.values())
			button.draw(g);
	}

	@Override
	protected void drawButtons(Graphics2D g)
	{
		buttonMenu.draw(g);
	}

	private void initButtons()
	{
		buttonMenu = new main.scenes.ui.Button("Menu", 2, 645, 100, 32);
		TowerManager towerManager = playing.getTowerManager();

		int w = 50;
		int h = 50;
		int xStart = 120;
		int yStart = 645;
		int xOffset = (int) (w * 1.3f);
		int i = 0;

		for (Tower.TowerType towerType : Tower.TowerType.values()) {
			int xPosition = xStart + xOffset * i;
			Button button = new Button(towerManager.getSprite(towerType), xPosition, yStart, w, h);
			buttonToTowerType.put(towerType, button);
			i++;
		}
	}

	@Override
	public void mouseClicked(int x, int y)
	{
		if (buttonMenu.getBounds().contains(x, y)) {
			GameStates.setGameState(MAIN_MENU);
			buttonMenu.resetStates();
		}

		for (Map.Entry<Tower.TowerType, Button> entry : buttonToTowerType.entrySet()) {
			Tower.TowerType towerType = entry.getKey();
			Button button = entry.getValue();

			if (button.getBounds().contains(x, y) && button.isActive()) {
				boolean toggled = !button.isToggled();
				button.setToggled(toggled);
				playing.setSelectedTower(toggled ? towerType : null);
			}
			else {
				button.setToggled(false);
			}
		}


	}

	@Override
	public void mouseMoved(int x, int y)
	{
		buttonMenu.setMouseOver(buttonMenu.getBounds().contains(x, y));

		for (Button button : buttonToTowerType.values())
			button.setMouseOver(button.getBounds().contains(x, y));
	}

	@Override
	public void mousePressed(int x, int y)
	{
		buttonMenu.setMousePressed(buttonMenu.getBounds().contains(x, y));

		for (Button button : buttonToTowerType.values())
			button.setMousePressed(button.getBounds().contains(x, y));
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

	}

	@Override
	protected void resetButtons()
	{
		buttonMenu.resetStates();
		for (Button button : buttonToTowerType.values())
			button.resetStates();
	}

	public void unToggle(Tower.TowerType selectedTowerType)
	{
		buttonToTowerType.get(selectedTowerType).setToggled(false);
	}
}
