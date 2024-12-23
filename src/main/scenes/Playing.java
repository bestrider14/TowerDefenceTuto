package main.scenes;

import main.Game;
import main.managers.TileManager;
import main.objects.Tile;
import main.scenes.ui.BottomBar;
import utils.LevelBuilder;

import java.awt.*;

public class Playing extends GameScene implements SceneMethods
{
	private final int[][] level;
	private final TileManager tileManager;
	private final BottomBar bottomBar;
	private int mouseX, mouseY;
	private boolean drawSelection;

	private Tile selectedTile;
	private int lastTileX, lastTileY, lastTileId;

	private final String ATLAS_PATH = "spriteatlas.png";
	private final int SPRITE_SIZE = 32;

	public Playing(Game game)
	{
		super(game);

		level = LevelBuilder.getLevelData();
		tileManager = new TileManager(ATLAS_PATH);

		bottomBar = new BottomBar(0, 640, 640, 100, this);
	}

	@Override
	public void render(Graphics2D g)
	{
		for (int y = 0; y < level.length; y++)
			for (int x = 0; x < level[y].length; x++)
				g.drawImage(tileManager.getTile(level[y][x]).getSprite(), x * SPRITE_SIZE, y * SPRITE_SIZE, null);

		bottomBar.draw(g);
		drawSelectedTile(g);
	}

	private void drawSelectedTile(Graphics2D g)
	{
		if (selectedTile != null && drawSelection)
			g.drawImage(selectedTile.getSprite(), mouseX, mouseY, selectedTile.getSize(), selectedTile.getSize(), null);
	}

	private void changeTile(int x, int y)
	{
		int tileX = x / SPRITE_SIZE;
		int tileY = y / SPRITE_SIZE;

		if (selectedTile == null)
			return;

		if(lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId())
			return;

		lastTileX = tileX;
		lastTileY = tileY;
		lastTileId = selectedTile.getId();

		level[tileY][tileX] = selectedTile.getId();
	}

	@Override
	public void mouseClicked(int x, int y)
	{
		if (y >= 640)
			bottomBar.mouseClicked(x, y);
		else
			changeTile(x, y);
	}

	@Override
	public void mouseMoved(int x, int y)
	{
		mouseX = (x / SPRITE_SIZE) * SPRITE_SIZE;
		mouseY = (y / SPRITE_SIZE) * SPRITE_SIZE;

		if (y >= 640)
		{
			drawSelection = false;
			bottomBar.mouseMoved(x, y);
		}
		else
			drawSelection = true;
	}

	@Override
	public void mousePressed(int x, int y)
	{
		if (y >= 640)
			bottomBar.mousePressed(x, y);
	}

	@Override
	public void mouseReleased(int x, int y)
	{
		resetButtons();
		bottomBar.mouseReleased(x, y);
	}

	@Override
	public void mouseDragged(int x, int y)
	{
		if (y < 640)
			changeTile(x, y);
	}

	private void resetButtons()
	{
	}

	@Override
	public void keyPressed(int key, int modifiers)
	{

	}

	public void setSelectedTile(Tile selectedTile)
	{
		this.selectedTile = selectedTile;
	}

	public TileManager getTileManager()
	{
		return tileManager;
	}
}
