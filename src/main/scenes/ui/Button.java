package main.scenes.ui;

import java.awt.*;

public class Button
{
	private int x, y, width, height;
	private String text;
	private int borderSize = 2;
	private boolean mouseOver = false;
	private boolean mousePressed = false;

	private Color backgroundColor = Color.GRAY;
	private Color backgroundColorOver = new Color(79, 79, 79);
	private Color fontColor = Color.BLACK;
	private Color borderColor = Color.BLACK;

	private Rectangle bounds;

	public Button(String text, int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;

		bounds = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics2D g)
	{
		drawBody(g);
		drawBorder(g);
		drawText(g);
	}

	private void drawBorder(Graphics2D g)
	{
		Stroke defaultStroke = g.getStroke();

		g.setStroke(mousePressed ? new BasicStroke(borderSize + 2) : new BasicStroke(borderSize));
		g.setColor(borderColor);
		g.drawRect(x, y, width, height);

		g.setStroke(defaultStroke);
	}

	private void drawBody(Graphics2D g)
	{
		g.setColor(mouseOver ? backgroundColorOver : backgroundColor);
		g.fillRect(x, y, width, height);
	}

	private void drawText(Graphics2D g)
	{
		int w = g.getFontMetrics().stringWidth(text);
		int h = g.getFontMetrics().getHeight();

		g.setColor(fontColor);
		g.drawString(text, x - w / 2 + width / 2, y + h / 2 + height / 2);
	}

	public Rectangle getBounds()
	{
		return bounds;
	}

	public void setBackgroundColor(Color backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}

	public void setBorderColor(Color borderColor)
	{
		this.borderColor = borderColor;
	}

	public void setMouseOver(boolean mouseOver)
	{
		this.mouseOver = mouseOver;
	}

	public void setBorderSize(int borderSize)
	{
		this.borderSize = borderSize;
	}

	public void setMousePressed(boolean mousePressed)
	{
		this.mousePressed = mousePressed;
	}

	public void setFontColor(Color fontColor)
	{
		this.fontColor = fontColor;
	}

	public void resetStates()
	{
		mouseOver = false;
		mousePressed = false;
	}
}
