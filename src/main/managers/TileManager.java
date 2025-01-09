package main.managers;

import main.objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static utils.ImageEdit.*;

public class TileManager
{
	public Tile GRASS, WATER, LR_ROAD, UD_ROAD, BL_WATER_CORNER, TL_WATER_CORNER, BR_WATER_CORNER, TR_WATER_CORNER,
				BEACH_UP, BEACH_DOWN, BEACH_LEFT, BEACH_RIGHT,
				TL_BEACH_CORNER, TR_BEACH_CORNER, BL_BEACH_CORNER, BR_BEACH_CORNER,
				DR_ROAD, DL_ROAD, TL_ROAD, TR_ROAD,
				START,END;

	public Map<Integer, Tile> tiles = new HashMap<>();
	private BufferedImage[] sprites;

	private SpriteManager spriteManager;

	public TileManager()
	{
		spriteManager = SpriteManager.getInstance();
		sprites = spriteManager.getSprites(0,1,2,3,4,5,6,7,8,9,27,28);
		createTiles();
	}

	private void createTiles()
	{
		int id = 0;
		tiles.put(id,GRASS = new Tile(sprites[9], id++, "Grass", TileCategory.MISC));
		tiles.put(id,WATER = new Tile(getWaterSprites(), id++, "Water", TileCategory.MISC));

		tiles.put(id,LR_ROAD = new Tile(sprites[8], id++, "LR_Road", TileCategory.ROADS_STRAIGHT));
		tiles.put(id,UD_ROAD = new Tile(getRotatedImage(sprites[8], 90), id++, "UD_Road", TileCategory.ROADS_STRAIGHT));

		tiles.put(id,DR_ROAD = new Tile(sprites[7], id++, "DR_Road", TileCategory.ROADS_CORNER));
		tiles.put(id,DL_ROAD = new Tile(getRotatedImage(sprites[7], 90), id++, "DL_Road", TileCategory.ROADS_CORNER));
		tiles.put(id,TL_ROAD = new Tile(getRotatedImage(sprites[7], 180), id++, "TL_Road", TileCategory.ROADS_CORNER));
		tiles.put(id,TR_ROAD = new Tile(getRotatedImage(sprites[7], 270), id++, "TR_Road", TileCategory.ROADS_CORNER));

		tiles.put(id,BL_WATER_CORNER = new Tile(buildRotatedAnimatedSprite(getWaterSprites(), sprites[5]), id++, "BL_Water_Corner", TileCategory.WATER_CORNER));
		tiles.put(id,TL_WATER_CORNER = new Tile(buildRotatedAnimatedSprite(getWaterSprites(), sprites[5], 90), id++, "TL_Water_Corner", TileCategory.WATER_CORNER));
		tiles.put(id,TR_WATER_CORNER = new Tile(buildRotatedAnimatedSprite(getWaterSprites(), sprites[5], 180), id++, "TR_Water_Corner", TileCategory.WATER_CORNER));
		tiles.put(id,BR_WATER_CORNER = new Tile(buildRotatedAnimatedSprite(getWaterSprites(), sprites[5], 270), id++, "BR_Water_Corner", TileCategory.WATER_CORNER));

		tiles.put(id,BEACH_UP = new Tile(buildRotatedAnimatedSprite(getWaterSprites(), sprites[6]), id++, "Beach_Up", TileCategory.BEACH));
		tiles.put(id,BEACH_RIGHT = new Tile(buildRotatedAnimatedSprite(getWaterSprites(), sprites[6], 90), id++, "Beach_Right", TileCategory.BEACH));
		tiles.put(id,BEACH_DOWN = new Tile(buildRotatedAnimatedSprite(getWaterSprites(), sprites[6], 180), id++, "Beach_Down", TileCategory.BEACH));
		tiles.put(id,BEACH_LEFT = new Tile(buildRotatedAnimatedSprite(getWaterSprites(), sprites[6], 270), id++, "Beach_Left", TileCategory.BEACH));

		tiles.put(id,TL_BEACH_CORNER = new Tile(buildRotatedAnimatedSprite(getWaterSprites(), sprites[4]), id++, "TL_Beach_Corner", TileCategory.BEACH_CORNER));
		tiles.put(id,TR_BEACH_CORNER = new Tile(buildRotatedAnimatedSprite(getWaterSprites(), sprites[4], 90), id++, "TR_Beach_Corner", TileCategory.BEACH_CORNER));
		tiles.put(id,BR_BEACH_CORNER = new Tile(buildRotatedAnimatedSprite(getWaterSprites(), sprites[4], 180), id++, "BR_Beach_Corner", TileCategory.BEACH_CORNER));
		tiles.put(id,BL_BEACH_CORNER = new Tile(buildRotatedAnimatedSprite(getWaterSprites(), sprites[4], 270), id++, "BL_Beach_Corner", TileCategory.BEACH_CORNER));

		tiles.put(id,START = new Tile(sprites[10], id++, "Start", TileCategory.END_START));
		tiles.put(id,END = new Tile(sprites[11], id++, "End", TileCategory.END_START));
	}

	private BufferedImage[] getImages(int[] ids)
	{
		BufferedImage[] images = new BufferedImage[ids.length];

		for (int i = 0; i < ids.length; i++)
			images[i] = sprites[(ids[i])];

		return images;
	}

	public Tile getTile(int id)
	{
		return tiles.get(id);
	}

	private BufferedImage[] getWaterSprites()
	{
		return getImages(new int[]{0,1,2,3});
	}

	public enum TileCategory
	{
		MISC, ROADS_STRAIGHT, ROADS_CORNER, WATER_CORNER, BEACH, BEACH_CORNER, END_START,
	}
}
