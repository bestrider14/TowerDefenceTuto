package main.towers;

import java.awt.geom.Point2D;

public abstract class Tower
{
	private Point2D.Double position;
	private final int id;
	private final TowerType type;

	public Tower(int x, int y, int id, TowerType type)
	{
		this.id = id;
		this.position = new Point2D.Double(x, y);
		this.type = type;
	}

	public Point2D.Double getPosition()
	{
		return position;
	}

	public double getX()
	{
		return position.x;
	}

	public double getY()
	{
		return position.y;
	}

	public int getId()
	{
		return id;
	}

	public TowerType getType()
	{
		return type;
	}

	public enum TowerType
	{
		CANNON, ARCHER, WIZZARD,
	}
}
