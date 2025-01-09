package main.objects;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class PathPoint
{
	private Point2D.Double point;
	private final PathPointType type;
	private BufferedImage sprite;

	public PathPoint(int x, int y, PathPointType type, BufferedImage sprite)
	{
		this.point = new Point2D.Double(x, y);
		this.type = type;
		this.sprite = sprite;
	}

	public Point2D.Double getPoint()
	{
		return point;
	}

	public void setPoint(int x, int y)
	{
		this.point = new Point2D.Double(x, y);
	}

	public PathPointType getType()
	{
		return type;
	}

	public int getX()
	{
		return (int)point.getX();
	}

	public int getY()
	{
		return (int)point.getY();
	}

	public BufferedImage getSprite()
	{
		return sprite;
	}

	public void setSprite(BufferedImage sprite)
	{
		this.sprite = sprite;
	}

	public void draw(Graphics2D g)
	{
		if (sprite != null)
			g.drawImage(sprite, (int) point.x, (int) point.y, sprite.getWidth(), sprite.getHeight(), null);
	}

	public enum PathPointType
	{
		START, END
	}

	@Override
	public String toString() {
		return "PathPoint{" + "x=" + point.x + ", y=" + point.y + ", type=" + type + '}';
	}
}
