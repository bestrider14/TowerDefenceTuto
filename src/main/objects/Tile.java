package main.objects;

import main.managers.TileManager;

import java.awt.image.BufferedImage;

public class Tile
{
	private final BufferedImage[] sprites;
	private int id;
	private int size;
	private String name;
	private TileManager.TileCategory category;
	private int animatedIndex = 0;
	private int tick = 0;
	private int speed = 30;

	public Tile(BufferedImage sprite, int id, String name, TileManager.TileCategory category)
	{
		sprites = new BufferedImage[1];
		this.sprites[0] = sprite;
		init(id, name, category);
	}

	public Tile(BufferedImage[] sprites, int id, String name, TileManager.TileCategory category)
	{
		this.sprites = sprites;
		init(id, name, category);
	}

	private void init(int id, String name, TileManager.TileCategory category)
	{
		this.id = id;
		this.name = name;
		this.size = this.sprites[0].getWidth();
		this.category = category;
	}

	public BufferedImage getSprite()
	{
		if(sprites.length == 1)
			return sprites[0];

		return sprites[animatedIndex];
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public int getSize()
	{
		return size;
	}

	public TileManager.TileCategory getCategory()
	{
		return category;
	}

	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	public void updateTick()
	{
		tick++;

		if(tick >= speed)
		{
			tick = 0;
			if(animatedIndex < sprites.length-1)
				animatedIndex++;
			else
				animatedIndex = 0;
		}

	}

	@Override
	public String toString()
	{
		return "Tile{" + "id=" + id + ", name='" + name + '\'' + '}';
	}
}
