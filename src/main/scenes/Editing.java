package main.scenes;

import main.Game;
import main.objects.PathPoint;
import main.objects.Tile;
import main.scenes.ui.bar.ToolBar;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Editing extends GameScene implements SceneMethods
{
	private int[][] level;
	private final ToolBar toolBar;
	private int mouseX, mouseY;
	private boolean drawSelection;

	private Tile selectedTile;
	private PathPoint startingPoint;
	private PathPoint endingPoint;

	private int lastTileX, lastTileY, lastTileId;

	public Editing(Game game)
	{
		super(game);

		loadDefaulLevel();
		toolBar = new ToolBar(0, 640, 640, 100, this);
	}

	private void changeTile(int x, int y)
	{
		int tileX = x / SPRITE_SIZE;
		int tileY = y / SPRITE_SIZE;

		if (selectedTile == null)
			return;

		if (lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId())
			return;

		if (selectedTile.getName().equals("Start"))
		{
			if (startingPoint != null)
				startingPoint.setPoint(tileX * SPRITE_SIZE, tileY * SPRITE_SIZE);
			else
				startingPoint = new PathPoint(tileX * SPRITE_SIZE, tileY * SPRITE_SIZE, PathPoint.PathPointType.START, getSprite(20));
		}
		else if (selectedTile.getName().equals("End"))
		{
			if (endingPoint != null)
				endingPoint.setPoint(tileX * SPRITE_SIZE, tileY * SPRITE_SIZE);
			else
				endingPoint = new PathPoint(tileX * SPRITE_SIZE, tileY * SPRITE_SIZE, PathPoint.PathPointType.END, getSprite(21));
		}
		else
		{
			lastTileX = tileX;
			lastTileY = tileY;
			lastTileId = selectedTile.getId();
			level[tileY][tileX] = selectedTile.getId();
		}
	}

	private void loadDefaulLevel()
	{
		level = LoadSave.getLevelData("new_level");
		startingPoint = LoadSave.getPathPoint("new_level", PathPoint.PathPointType.START);
		endingPoint = LoadSave.getPathPoint("new_level", PathPoint.PathPointType.END);
		startingPoint.setSprite(getSprite(20));
		endingPoint.setSprite(getSprite(21));
	}

	private void drawSelectedTile(Graphics2D g)
	{
		if (selectedTile != null && drawSelection)
			g.drawImage(selectedTile.getSprite(), mouseX, mouseY,null);
	}

	public void saveLevel()
	{
		LoadSave.saveLevel("new_level", level, startingPoint, endingPoint);
		game.getPlaying().reloadLevel(level);
	}

	@Override
	public void render(Graphics2D g)
	{
		drawLevel(g);
		toolBar.draw(g);
		drawPathPoint(g);
		drawSelectedTile(g);
	}

	private void drawPathPoint(Graphics2D g)
	{
		if (startingPoint != null)
			startingPoint.draw(g);

		if (endingPoint != null)
			endingPoint.draw(g);
	}

	private void drawLevel(Graphics2D g)
	{
		for (int y = 0; y < level.length; y++)
			for (int x = 0; x < level[y].length; x++)
				g.drawImage(getSprite(level[y][x]), x * SPRITE_SIZE, y * SPRITE_SIZE, null);

	}

	private BufferedImage getSprite(int id)
	{
		return game.getTileManager().getTile(id).getSprite();
	}

	@Override
	public void mouseClicked(int x, int y)
	{
		if (y >= 640)
			toolBar.mouseClicked(x, y);
		else
			changeTile(x, y);
	}

	@Override
	public void mouseMoved(int x, int y)
	{
		if (y >= 640)
		{
			drawSelection = false;
			toolBar.mouseMoved(x, y);
		}
		else
		{
			mouseX = (x / SPRITE_SIZE) * SPRITE_SIZE;
			mouseY = (y / SPRITE_SIZE) * SPRITE_SIZE;
			drawSelection = true;
		}
	}

	@Override
	public void mousePressed(int x, int y)
	{
		if (y >= 640)
			toolBar.mousePressed(x, y);
	}

	@Override
	public void mouseReleased(int x, int y)
	{
		toolBar.mouseReleased(x, y);
	}

	@Override
	public void mouseDragged(int x, int y)
	{
		if (y < 640)
			changeTile(x, y);
	}

	@Override
	public void keyPressed(int key, int modifiers)
	{
		toolBar.keyPressed(key);
	}

	public void setSelectedTile(Tile selectedTile)
	{
		this.selectedTile = selectedTile;
	}

	public Tile getSelectedTile()
	{
		return selectedTile;
	}
}
