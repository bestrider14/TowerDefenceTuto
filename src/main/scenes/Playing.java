package main.scenes;

import main.Game;
import main.GameStates;
import main.managers.TileManager;
import main.scenes.ui.Button;
import utils.LevelBuilder;

import java.awt.*;

import static main.GameStates.MAIN_MENU;
import static main.GameStates.PLAYING;

public class Playing extends GameScene implements SceneMethods
{
	private int[][] level;
	private TileManager tileManager;

	private Button buttonMenu;

	private final String ATLAS_PATH = "spriteatlas.png";
	private final int SPRITE_SIZE = 32;

	public Playing(Game game)
	{
		super(game);

		initButtons();

		level = LevelBuilder.getLevelData();
		tileManager = new TileManager(ATLAS_PATH);
	}

	private void initButtons()
	{
		buttonMenu = new Button("Menu", 25, 25, 100, 30);
	}

	@Override
	public void render(Graphics2D g)
	{
		for (int y = 0; y < level.length; y++)
			for (int x = 0; x < level[y].length; x++)
				g.drawImage(tileManager.getTile(level[y][x]).getSprite(), x * SPRITE_SIZE, y * SPRITE_SIZE, null);

		drawButtons(g);
	}

	private void drawButtons(Graphics2D g)
	{
		buttonMenu.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y)
	{
		if(buttonMenu.getBounds().contains(x, y))
			GameStates.setGameState(MAIN_MENU);
	}

	@Override
	public void mouseMoved(int x, int y)
	{
		buttonMenu.setMouseOver(buttonMenu.getBounds().contains(x, y));
	}

	@Override
	public void mousePressed(int x, int y)
	{
		buttonMenu.setMousePressed(buttonMenu.getBounds().contains(x, y));
	}

	@Override
	public void mouseReleased(int x, int y)
	{
		resetButtons();
	}

	private void resetButtons()
	{
		buttonMenu.resetStates();
	}

	@Override
	public void keyPressed(int key, int modifiers)
	{

	}
}
