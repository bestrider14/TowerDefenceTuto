package main.scenes.ui;

import java.awt.*;

public abstract class Bar
{
	protected final int x, y, width, height;

	protected Color backgroundColor = new Color(7, 54, 61);

	public Bar(int x, int y, int width, int height)
	{
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
	}
}
