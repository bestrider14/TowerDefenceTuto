package main.objects;

import java.awt.image.BufferedImage;

public class Tile
{
	private BufferedImage sprite;
	private int id;
	private int size;
	private String name;

	public Tile(BufferedImage sprite, int id, String name)
	{
		this.sprite = sprite;
		this.id = id;
		this.name = name;
		this.size = sprite.getWidth();
	}

	public BufferedImage getSprite()
	{
		return sprite;
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

	@Override
	public String toString()
	{
		return "Tile{" + "id=" + id + ", name='" + name + '\'' + '}';
	}
}
