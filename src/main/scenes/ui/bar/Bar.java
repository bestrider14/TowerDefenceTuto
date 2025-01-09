package main.scenes.ui.bar;

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

	abstract protected void drawButtons(Graphics2D g);

	public void setBackgroundColor(Color backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}

	public void draw(Graphics2D g)
	{
		g.setColor(backgroundColor);
		g.fillRect(x, y, width, height);

		drawButtons(g);
	}

	abstract public void mouseClicked(int x, int y);
	abstract public void mouseMoved(int x, int y);
	abstract public void mousePressed(int x, int y);
	abstract public void mouseReleased(int x, int y);
	abstract public void keyPressed(int key);
	abstract protected void resetButtons();
}
