package main.managers;

import main.objects.Tile;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager
{
	public Tile GRASS, WATER, ROAD;
	public BufferedImage atlas;
	public ArrayList<Tile> tiles = new ArrayList<>();
	private ArrayList<BufferedImage> sprites = new ArrayList<>();

	private final int SPRITE_SIZE = 32;
	private final int NUMBER_SPRITE_BY_ROW = 10;
	private final int NUMBER_SPRITE_BY_COLUMN = 3;

	public TileManager(String path)
	{
		loadAtlas(path);
		loadSprites(SPRITE_SIZE, NUMBER_SPRITE_BY_ROW, NUMBER_SPRITE_BY_COLUMN);
		createTiles();
	}

	private void createTiles()
	{
		int id = 0;
		tiles.add(GRASS = new Tile(sprites.get(9), id++, "Grass"));
		tiles.add(WATER = new Tile(sprites.get(0), id++, "Water"));
		tiles.add(ROAD = new Tile(sprites.get(8), id++, "Road"));
	}

	private void loadAtlas(String path)
	{
		atlas = LoadSave.getSpriteAtlas(path);
	}

	private void loadSprites(int spriteSize, int numSpritesByRow, int numSpritesByColumn)
	{
		for (int y = 0; y < numSpritesByColumn; y++)
		{
			for (int x = 0; x < numSpritesByRow; x++)
			{
				sprites.add(atlas.getSubimage(x * spriteSize, y * spriteSize, spriteSize, spriteSize));
			}
		}
	}

	public Tile getTile(int id)
	{
		return tiles.get(id);
	}
}
