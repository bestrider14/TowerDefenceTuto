package main.managers;

import utils.LoadSave;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteManager
{
	static SpriteManager instance;
	private BufferedImage spriteSheet;
	private ArrayList<BufferedImage> sprites = new ArrayList<>();

	private final int SPRITE_SIZE = 32;
	private final int NUMBER_SPRITE_BY_ROW = 10;
	private final int NUMBER_SPRITE_BY_COLUMN = 3;
	private final String ATLAS_PATH = "spriteatlas.png";

	private SpriteManager()
	{
		loadSpritesSheet();
		loadSprites();
	}

	public static SpriteManager getInstance()
	{
		if(instance == null)
			instance = new SpriteManager();
		return instance;
	}

	private void loadSpritesSheet()
	{
		this.spriteSheet = LoadSave.getSpriteAtlas(ATLAS_PATH);
	}

	private void loadSprites()
	{
		for (int y = 0; y < NUMBER_SPRITE_BY_COLUMN; y++)
		{
			for (int x = 0; x < NUMBER_SPRITE_BY_ROW; x++)
				sprites.add(spriteSheet.getSubimage(x * SPRITE_SIZE, y * SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE));
		}
	}

	public BufferedImage[] getSprites(int ... index)
	{
		BufferedImage[] sprites = new BufferedImage[index.length];

		for (int i = 0; i < index.length; i++)
			sprites[i] = this.sprites.get(index[i]);

		return sprites;
	}
}
